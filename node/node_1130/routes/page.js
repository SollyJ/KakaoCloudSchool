const express = require("express");
const router = express.Router();
const { isLoggedIn, isNotLoggedIn } = require("./middlewares"); // middlewares에서 여러개 export했으므로 객체형태로 받는다.
const { Post, User, Hashtag } = require("../models");

// 공통된 처리 - 무조건 수행
router.use((req, res, next) => {
  res.locals.user = req.user; // 로그인한 user정보
  res.locals.followCount = req.user ? req.user.Followers.length : 0; // 게시글을 follow하고 있는 개수
  res.locals.followingCount = req.user ? req.user.Followings.length : 0;
  res.locals.followerIdList = req.user
    ? req.user.Followings.map((f) => f.id)
    : []; // 게시글을 follow하고 있는 user들 목록
  next();
});

// 해쉬태그 읽어오기
router.get("/hashtag", async (req, res, next) => {
  const query = req.query.hashtag;
  if (!query) return res.redirect("/");
  try {
    const hashtag = await Hashtag.findOne({ where: { title: query } });

    let posts = []; // hashtag에 해당하는 포스트를 다 찾아오기
    if (hashtag) {
      posts = await hashtags.getPosts({ include: [{ model: User }] });
    }
    return res.render("main", {
      title: `${query} | NodeAuthentication`,
      twits: posts,
    });
  } catch (err) {
    console.error(err);
    return next(err);
  }
});

// 메인화면
router.get("/", async (req, res, next) => {
  try {
    const posts = await Post.findAll({
      // Post모델의 모든 데이터를 가져오는데 User정보의 id, nick도 가져오기
      include: {
        model: User,
        attributes: ["id", "nick"],
      },
      order: [["createdAt", "DESC"]],
    });

    res.render("main", {
      title: "NodeAuthentication",
      twits: posts,
    });
  } catch (err) {
    console.error(err);
    next(err);
  }
});

// 회원가입 - 로그인이 되어있지 않은 경우만 수행
router.get("/join", isNotLoggedIn, (req, res) => {
  res.render("join", {
    title: "회원가입",
  });
});

// 프로필 - 로그인이 되어있는 경우만
router.get("/profile", isLoggedIn, (req, res) => {
  res.render("profile", {
    title: "나의 정보",
  });
});

module.exports = router;
