// 토큰을 발급하는 처리를 수행
const express = require("express");
const { Domain, Post, User } = require("../models");
const jwt = require("jsonwebtoken");
const { verifyToken, deprecated } = require("./middlewares");
const router = express.Router();

// 모든 라우팅 처리에서 deprecated 적용
router.use(deprecated); // v1은 이제 서비스 안됨

// 데이터를 리턴하는 요청 처리
// 내가 올린 포스트를 클라이언트에 보냄
router.get("/posts/my", verifyToken, (req, res) => {
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
router.post("/token", async (req, res) => {
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
        expiresIn: "1m", // 유효기간
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

router.get("/test", verifyToken, (req, res) => {
  res.json(req.decoded);
});

module.exports = router;
