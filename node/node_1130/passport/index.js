const passport = require("passport");
const local = require("./localStrategy"); // 로컬로그인 전략
const kakao = require("./kakaoStrategy"); // 카카오로그인 전략
const User = require("../models/user");

module.exports = () => {
  // 로그인 성공했을 때 정보를 deserializeUser 함수에게 넘기는 함수
  passport.serializeUser((user, done) => {
    done(null, user.id);
  });

  // 넘어온 id에 해당하는 데이터가 있으면 데이터베이스에서 찾아서 세션에 저장
  passport.deserializeUser((id, done) => {
    User.findOne({
      where: { id },
      include: [
        {
          model: User,
          attributes: ["id", "nick"],
          as: "Followers",
        },
        {
          model: User,
          attributes: ["id", "nick"],
          as: "Followings",
        },
      ],
    })
      .then((user) => done(null, user)) // 세션에 저장
      .catch((err) => done(err));
  });
  local();
  kakao();
};
