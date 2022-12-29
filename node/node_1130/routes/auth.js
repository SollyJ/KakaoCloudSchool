const express = require("express");
const passport = require("passport"); // 로그인 및 로그아웃 처리를 위해
const bcrypt = require("bcrypt"); // 회원가입 위해
const { isLoggedIn, isNotLoggedIn } = require("./middlewares");
const User = require("../models/user");
const router = express.Router();

// 회원가입 - /auth/join인데 라우팅할때 /auth를 추가하므로
router.post("/join", isNotLoggedIn, async (req, res, next) => {
  // 데이터 찾아오기
  const { email, nick, password } = req.body; // 구조분해할당
  try {
    const exUser = await User.findOne({ where: { email } }); // email 존재 여부 확인
    if (exUser) {
      // 이미 존재하는 이메일이면 회원가입 페이지로 redirect, error키에 exist메시지를 가지고 이동
      return res.redirect("/join?error=exist");
    } else {
      const hash = await bcrypt.hash(password, 12); // 비밀번호를 해싱
      await User.create({
        // user에 저장
        email,
        nick,
        password: hash,
      });
      return res.redirect("/");
    }
  } catch (err) {
    console.error(err);
    return next(err);
  }
});

// 로그인
router.post("/login", isNotLoggedIn, async (req, res, next) => {
  // passport를 이용해서 로그인 (로컬로그인전략 사용)
  passport.authenticate("local", (authError, user, info) => {
    if (authError) {
      console.error(authError);
      return next(authError);
    }
    // 일치하는 user가 없을때
    if (!user) {
      return res.redirect(`/?loginError=${info.message}`);
    }
    // 로그인 성공하면
    return req.login(user, (loginError) => {
      if (loginError) {
        console.error(loginError);
        return next(loginError);
      }
      return res.redirect("/");
    });
  })(req, res, next); // 미들웨어 내의 미들웨어에는 이것을 붙인다.
});

// 카카오로그인을 눌렀을 때 처리
router.get("/kakao", passport.authenticate("kakao"));

// 카카오로그인 실패했을 때와 성공했을 때 처리
router.get(
  "/kakao/callback",
  passport.authenticate("kakao", { failureRedirect: "/" }),
  (req, res) => {
    res.redirect("/");
  }
);

// 로그아웃
router.get("/logout", isLoggedIn, (req, res, next) => {
  req.logout(function (err) {
    if (err) return next(err);
    req.session.destroy(); // 세션 초기화
    res.redirect("/");
  });
});

module.exports = router;
