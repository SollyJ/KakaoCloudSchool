const express = require("express");

const multer = require("multer"); // 파일 업로드를 위한 모듈
const path = require("path"); // 파일 경로 설정 위한 모듈
const fs = require("fs"); // 파일 읽기 위한 모듈

const { Post, Hashtag } = require("../models"); // 데이터 삽입을 위한 모듈
const { isLoggedIn } = require("./middlewares");

const router = express.Router();

// 파일을 업로드할 디렉토리가 없으면 생성
try {
  fs.readdirSync("public/img");
} catch (err) {
  fs.mkdirSync("public/img");
}

// 파일 업로드 객체
const upload = multer({
  storage: multer.diskStorage({
    destination(req, file, cb) {
      cb(null, "public/img/");
    },
    filename(req, file, cb) {
      const ext = path.extname(file.originalname);
      cb(null, path.basename(file.originalname, ext) + Date.now() + ext);
    },
  }),
  limits: { fileSize: 10 * 1024 * 1024 },
});

// 이미지 업로드
router.post("/img", isLoggedIn, upload.single("img"), (req, res) => {
  console.log(req.file);
  res.json({
    url: `/img/${req.file.filename}`,
  });
});

const upload2 = multer(); // 게시글 업로드 객체
// 게시글 업로드
router.post("/", isLoggedIn, upload2.none(), async (req, res, next) => {
  try {
    const post = await Post.create({
      content: req.body.content,
      img: req.body.url,
      UserId: req.user.id,
    });
    // 해시태그 찾기
    const hashtags = req.body.content.match(/#[^\s#]*/g); // #다음에 나오는 문자를 #는 제외하고 다 찾음
    if (hashtags) {
      const result = await Promise.all(
        // Promise.all: 배열 전체 데이터를 순서대로 대입해서 내용을 수행하고 다 성공하면 배열로 반환된다
        hashtags.map((tag) => {
          return Hashtag.findOrCreate({
            // 해쉬태그 테이블에 찾거나 생성
            where: {
              title: tag.slice(1).toLowerCase(), // 해쉬태그는 대소문자를 구분할 필요가 없으므로 다 소문자로 바꿈
            },
          });
        })
      );
      await post.addHashtags(result.map((r) => r[0]));
    }
    res.redirect("/");
  } catch (err) {
    console.error(err);
  }
});

module.exports = router;
