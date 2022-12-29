const express = require("express");
const User = require("../models/user");
const { isLoggedIn } = require("./middlewares");
const router = express.Router();

router.post("/:id/follow", isLoggedIn, async (req, res, next) => {
  try {
    const user = await User.findOne(
      // 요청내용 안의 유저의 아이디가 유저테이블에 있는지 확인
      { where: { id: req.user.id } }
    );

    if (user) {
      // 팔로우로 추가
      await user.addFollowing(parseInt(req.params.id, 10));
    } else {
      // 유저의 아이디가 없다면 404에러
      res.status(404).send("no user");
    }
  } catch (err) {
    console.error(err);
    next(err);
  }
});

module.exports = router;
