const express = require("express");
const morgan = require("morgan");
const path = require("path");
const multer = require("multer");
const fs = require("fs");
const app = express();
let bodyParser = require("body-parser");

// 포트번호 설정
app.set("port", process.env.PORT || 9000);

// 로그 기록
app.use(morgan("dev"));

// form이 아닌 형태의 POST방식의 파라미터를 읽기 위한 설정
app.use(bodyParser.json());
app.use(
  bodyParser.urlencoded({
    extended: true,
  })
);

//파일 다운로드를 위한 모듈
let util = require("util");
let mime = require("mime");

// 파일 업로드를 위한 디렉토리를 읽음 없으면 생성
try {
  fs.readdirSync("img");
} catch (error) {
  console.error("img 폴더가 없어 img 폴더를 생성합니다.");
  fs.mkdirSync("img");
}

const upload = multer({
  storage: multer.diskStorage({
    destination(req, file, done) {
      done(null, "img/");
    },
    filename(req, file, done) {
      const ext = path.extname(file.originalname);
      done(null, path.basename(file.originalname, ext) + Date.now() + ext);
    },
  }),
  limits: { fileSize: 10 * 1024 * 1024 },
});

// 템플릿 엔진 설정
// 서버의 데이터를 html에 출력하기 위한 모듈
app.set("view engine", "html");
app.engine("html", require("ejs").renderFile);

let MongoClient = require("mongodb").MongoClient; // mongodb 불러오기
let databaseUrl = "mongodb://localhost:27017/";

// item컬렉션에 존재하는 모든 데이터를 리턴
app.get("/item/all", (req, res) => {
  // mongodb연결
  MongoClient.connect(
    databaseUrl,
    { useNewUrlParser: true },
    (error, database) => {
      if (error) {
        console.log(error);
        res.json({ result: false });
      } else {
        let db = database.db("mydb"); // db 가져오기
        db.collection("item")
          .find()
          .sort({ itemid: -1 }) // 모든 컬렉션 가져오기
          .toArray((error, items) => {
            if (error) {
              console.log(error);
              res.json({ result: false });
            } else {
              res.json({ result: true, list: items, count: items.length });
            }
          });
      }
    }
  );
});

// 페이지 단위로 가져오기
// 데이터베이스에서 페이지단위로 데이터를 가져올때는 건너띌 개수와 가져올 데이터 개수가 필요하다
app.get("/item/list", (req, res) => {
  // 클라이언트의 데이터 받아오기
  // url의 ?뒤에 있는 데이터는 query로 받아온다.
  let pageno = req.query.pageno;
  let count = req.query.count;

  if (pageno == undefined) {
    pageno = 1;
  }
  if (count == undefined) {
    count = 2;
  }
  // 페이지 번호에 따른 건너띌 개수 구하기
  let start = (parseInt(pageno) - 1) * parseInt(count);

  // mongodb연결
  MongoClient.connect(
    databaseUrl,
    { useNewUrlParser: true },
    (error, database) => {
      if (error) {
        console.log(error);
        res.json({ result: false });
      } else {
        let db = database.db("mydb"); // db 가져오기
        db.collection("item")
          .find()
          .sort({ itemid: -1 })
          .skip(start)
          .limit(count) // start만큼 건너뛰고 count만큼 가져와라
          .toArray((error, items) => {
            if (error) {
              console.log(error);
              res.json({ result: false });
            } else {
              res.json({ result: true, list: items, count: items.length });
            }
          });
      }
    }
  );
});

// 상세보기
app.get("/item/:itemid", (req, res) => {
  // 클라이언트의 데이터 받아오기
  // url에 포함된 데이터 받을땐 params
  let itemid = req.params.itemid;

  // mongodb연결
  MongoClient.connect(
    databaseUrl,
    { useNewUrlParser: true },
    (error, database) => {
      if (error) {
        console.log(error);
        res.json({ result: false });
      } else {
        let db = database.db("mydb"); // db 가져오기
        db.collection("item").findOne(
          { itemid: Number(itemid) },
          (error, item) => {
            if (error) {
              console.log(error);
              res.json({ result: false });
            } else {
              res.json({ result: true, item: item });
            }
          }
        );
      }
    }
  );
});

// 데이터 삽입
app.post("/item", upload.single("pictureurl"), (req, res) => {
  // 파라미터 읽어오기
  const itemname = req.body.itemname;
  const description = req.body.description;
  const price = req.body.price;
  let pictureurl;

  if (req.file) {
    // 업로드한 파일이 있는지 확인
    pictureurl = req.file.filename;
  } else {
    // 없으면 기본이미지 설정
    pictureurl = "default.jpg";
  }

  MongoClient.connect(
    databaseUrl,
    { useNewUrlParser: true },
    (error, database) => {
      if (error) {
        console.log(error);
        res.json({ result: false });
      } else {
        let db = database.db("mydb");

        db.collection("item")
          .find({}, { projection: { _id: 0, itemid: 1 } })
          .sort({ itemid: -1 })
          .limit(1)
          .toArray((error, result) => {
            let itemid = 1;
            if (result[0] != null) itemid = result[0].itemid + 1;

            db.collection("item").insertOne(
              {
                itemid: itemid,
                itemname: itemname,
                description: description,
                price: price,
                pictureurl: pictureurl,
              },
              (error, result) => {
                if (error) res.json({ result: false });
                else res.json({ result: true });
              }
            );
          });
      }
    }
  );
});

// 데이터 수정

// 데이터 삭제

app.use((err, req, res, next) => {
  console.error(err);
  res.status(500).send(message);
});

app.listen(app.get("port"), () => {
  console.log(app.get("port"), "번 포트에서 대기 중");
});
