// 토큰을 발급하는 처리를 수행
const express = require("express");
const { Domain, Post, User } = require("../models");
const jwt = require("jsonwebtoken");
const { verifyToken, apiLimiter } = require("./middlewares");
const cors = require("cors");
const url = require("url");
const { urlencoded } = require("express");
const router = express.Router();

// Domain에 등록된 경우만 전송할 수 있도록 cors 설정하기
router.use(async (req, res, next) => {
  // 현재 요청 도메인이 데이터베이스에 등록되어있는지 찾아오기
  const domain = await Domain.findOne({
    where: { host: url.parse(req.get("origin")).host },
  });

  if (domain) {
    cors({
      origin: req.get("origin"),
      credentials: true,
    })(req, res, next);
  } else {
    next();
  }
});

// 데이터를 리턴하는 요청 처리
// 내가 올린 포스트를 클라이언트에 보냄
router.get("/posts/my", apiLimiter, verifyToken, (req, res) => {
  Post.findAll({ where: { userId: req.decoded.id } })
    .then((posts) => {
      console.log(posts);
      res.json({ code: 200, payload: posts });
    })
    .catch((err) => {
      console.error(err);
      return res.status(500).json({
        code: 500,
        message: "서버에러",
      });
    });
});

// 토큰 발급
router.post("/token", apiLimiter, async (req, res) => {
  const { clientSecret } = req.body;
  try {
    // 도메인 찾아오기
    const domain = await Domain.findOne({
      where: { clientSecret },
      include: {
        model: User,
        attribute: ["nick", "id"],
      },
    });
    if (!domain) {
      return res.status(401).json({
        code: 401,
        message: "등록되지 않은 도메인입니다.",
      });
    }
    // 토큰 생성
    const token = jwt.sign(
      {
        id: domain.User.id,
        nick: domain.User.nick,
      },
      process.env.JWT_SECRET,
      {
        expiresIn: "10m", // 유효기간
        issuer: "solly", // 발급자
      }
    );
    // 토큰 발급
    return res.json({
      code: 200,
      message: "토큰이 발급되었습니다.",
      token,
    });
  } catch (err) {
    console.error(err);
    return res.status(500).json({
      code: 500,
      message: "서버에러",
    });
  }
});

router.get("/test", apiLimiter, verifyToken, (req, res) => {
  res.json(req.decoded);
});

module.exports = router;
