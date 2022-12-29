exports.isLoggedIn = (req, res, next) => {
  // 로그인 되어있으면
  if (req.isAuthenticated()) {
    next(); // 다음 라우터 처리
  } else {
    res.status(403).send("로그인 필요");
  }
};

exports.isNotLoggedIn = (req, res, next) => {
  if (!req.isAuthenticated()) {
    next(); // 다음 라우터 처리
  } else {
    //메시지를 생성하는 query string으로 사용할 것이라서 encoding을 해줘야 한다.
    const message = encodeURIComponent("로그인한 상태입니다.");
    res.redirect(`/?error=${message}`); // ${}안은 자바스크립트의 템플릿을 이용하겠다는 것
  }
};
