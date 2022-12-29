const express = require('express');
const path = require('path');
const session = require('express-session');
const filestore = require("session-file-store")(session);
const fs = require('fs');
const multer = require('multer');

// 웹 서버 객체 생성과 포트 설정
const app = express();
app.set('port', 3000);

// 세션 사용을 위한 미들웨어 장착⭐️
// 세션은 서버에 저장하기 때문에 서버가 꺼지면 소멸된다.
// 이를 방지하기 위해서 파일에 저장하기
app.use(session({
    secret: 'keyboard cat',
    resave: false,
    saveUninitialized:true,
    store: new filestore()
}));

// 파일을 업로드 하기 위한 디렉토리 생성
// 디렉토리를 읽는데 없으면 생성
try{
    fs.readdirSync('uploads');
}catch(error){
    fs.mkdirSync('uploads');
}

// 파일을 업로드해주는 설정
const upload = multer({
    storage: multer.diskStorage({
        destination(req, file, done) {
            done(null, 'uploads/');
        },
        filename(req, file, done) {
            file.originalname = Buffer.from(file.originalname, 'latin1').toString('utf8');

            const ext = path.extname(file.originalname);    // 파일 확장자

            done(null, path.basename(file.originalname, ext) + Date.now() + ext);   // 파일이름을 유일무이하게
        }
    }),
    limits: {fileSize: 1024 * 1024 * 10}    // 10 megabytes
});

// 하나의 파일 업로드 처리 포트번호뒤에 /single이라고 쳐야 파일이 업로드됨
app.get('/single', (req, res) => {
    res.sendFile(path.join(__dirname, './filepost.html'));   // 해당 html파일에 있는 파일을 get함
});

app.post('/single', upload.single('image'), (req, res) => {  // 해당id의 파일을 서버에 업로드함
    console.log(req.body.title);    
    res.send('성공');
}); 

/* 라우터 가져오기
const indexRouter = require('./routes/index.js');
const userRouter = require('./routes/user.js');
const boardRouter = require('./routes/board.js');

app.use("/", indexRouter);
app.use("/user", userRouter);
app.use("/board", boardRouter);*/

/* 사용자 요청 처리 (기본))
app.get('/', (req, res) => {    // 요청 처리 메서드: get post pul patch delete options
    // req에 대한 처리

    // 응답 send
    res.sendFile(path.join(__dirname, './print.html')); // 절대경로를 쓰기위해서 path모듈이 필요하다
}); */

// 서버 실행
app.listen(app.get('port'), () => {
    console.log(app.get('port'), "포트에서 서버 대기중");
}); 