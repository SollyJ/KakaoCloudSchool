const jwt = require("jsonwebtoken");

// 토큰 인증
exports.verifyToken = (req, res, next) => {
  try {
    // 토큰 확인
    req.decoded = jwt.verify(req.headers.authorization, process.env.JWT_SECRET);
    // 인증에 성공하면 다음 작업 수행
    return next();
  } catch (err) {
    if (err.name === "TokenExpiredError") {
      // 토큰 만료 에러
      return res.status(419).json({
        code: 419,
        message: "Token Expired",
      });
    }
    return res.status(401).json({
      // 권한 없음 에러
      code: 401,
      message: "Token Invalid",
    });
  }
};

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

// 사용량 제한을 위한 미들웨어
const RateLimit = require("express-rate-limit");
exports.apiLimiter = RateLimit({
  windowMs: 60 * 1000, // 1분
  max: 10,
  delayMs: 0,
  handler(req, res) {
    res.status(this.statusCode).json({
      code: this.statusCode.json,
      message: "1분 단위로 요청해야 한다.",
    });
  },
});

// 구버전 API 요청시 동작할 미들웨어
exports.deprecated = (req, res) => {
  res.status(410).json({
    code: 410,
    message: "새로운 버전이 나왔습니다. 새버전을 사용하세요.",
  });
};
