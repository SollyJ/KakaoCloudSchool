const express = require("express");
const { v4: uuidv4 } = require("uuid");
const { User, Domain } = require("../models");
const { isLoggedIn } = require("./middlewares");
const router = express.Router();

router.get("/", async (req, res, next) => {
  try {
    // 로그인한 유저가 있으면 유저의 모든 데이터를 찾아서 대입
    const user = await User.findOne({
      where: { id: (req.user && req.user.id) || null },
      include: { model: Domain },
    });
    console.log(user);

    res.render("login", {
      user,
      domains: user && user.Domains,
    });
  } catch (err) {
    console.error(err);
    next(err);
  }
});

// 도메인 등록 처리
router.post("/domain", isLoggedIn, async (req, res, next) => {
  try {
    await Domain.create({
      // 도메인 생성
      UserId: req.user.id,
      host: req.body.host,
      type: req.body.type,
      clientSecret: uuidv4(),
    });
    res.redirect("/");
  } catch (err) {
    console.error(err);
    next(err);
  }
});

module.exports = router;
