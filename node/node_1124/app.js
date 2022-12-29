const express = require('express');
const morgan = require('morgan');
const compression = require('compression');
const path = require('path');
const mysql = require('mysql');
const cookieParser = require('cookie-parser');
const session = require('express-session');
const multer = require('multer');
const dotenv = require('dotenv');
const FileStreamRotator = require('file-stream-rotator');   // 로그를 매일 기록하기 위한 모듈
const fs = require('fs');
const bodyParser = require('body-parser');
let util = require('util'); // 파일 다운로드를 위한 모듈
let mime = require('mime'); // 파일 다운로드를 위한 모듈


//설정 파일의 내용 가져오기
dotenv.config();

//서버 설정
const app = express();
app.set('port', process.env.PORT || 9000);

//로그를 기록할 디렉토리 경로 생성
let logDirectory = path.join(__dirname, 'log');

fs.existsSync(logDirectory) || fs.mkdirSync(logDirectory);  //디렉토리가 없으면 생성

//로그 파일 옵션을 설정
let accessLogStream = FileStreamRotator.getStream({
    date_format:'YYYYMMDD',
    filename:path.join(logDirectory, 'access-%DATE%.log'),
    frequency:'daily',
    verbose:false
});
//로그 기록 설정
app.use(morgan('combined', {stream:accessLogStream}));


//압축해서 전송하는 옵션 설정
app.use(compression());


//POST 방식의 파라미터 읽을 수 있도록 설정
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({
    extended:true
}));


//세션을 데이터베이스에 저장하는 작업
let options = { //데이터베이스 접속 정보
    host: process.env.HOST,
    port: process.env.MYSQLPORT,
    user: process.env.USERNAME,
    password: process.env.PASSWORD,
    database: process.env.DATABASE
};
//세션을 저장하기 위한 MySQL 데이터베이스 저장소 생성
const MariaDBStore = require('express-mysql-session')(session);
//세션 설정
app.use(session({
    secret:process.env.COOKIE_SECRET,
    resave:false,
    saveUninitialized:true,
    store:new MariaDBStore(options)
}));

let connection = mysql.createConnection(options);   // mysql 접속 정보 설정
connection.connect((error) => { // 데이터베이스 연결
    if(error){
        console.log(error);
        throw error;
    }
})


//파일 업로드 설정
const upload = multer({
    storage:multer.diskStorage({
        destination(req, file, done){
            done(null, 'public/img');
        },
        filename(req, file, done){
            const ext = path.extname(file.originalname);
            done(null, path.basename(file.originalname, ext) + Date.now() + ext);
        }
    }),
    limits:{fileSize: 10*1024*1024}
});

//정적 파일의 경로를 설정
app.use('/', express.static('public'));


// 기본 요청을 처리
app.get('/', (req, res) => {
    res.sendFile(path.join(__dirname, 'index.html'));
});


// 데이터 모두 가져오는 요청을 처리
app.get('/item/all', (req, res) => {
    connection.query("select * from goods order by itemid desc", (err, results, fields) => {
        if(err){
            //에러가 발생했다고 데이터를 전송하지 않으면 안됨
            res.json({'result':false})

        }else{
            res.json({'result':true, 'list':results});
        }
    });
});

// 데이터 일부분 가져오기
// 페이지 번호, 한 페이지에 출력할 데이터 개수가 필요하다
app.get('/item/list', (req, res) => {
    let pageno = req.query.pageno;  // URL에 포함된 파라미터 읽기
    if(pageno == undefined) {
        pageno = 1; // 페이지번호를 전달하지 않으면 1페이지 디폴트
    }

    let result = true;
    let list;   // 연결 성공하고 정상 응답했을때 데이터를 저장

    // goods테이블에서 itemid를 가지고 내림차순 정렬해서 페이지 단위로 데이터 가져오기
    // 시작번호 = (pageno-1)*5
    // 데이터 5개씩
    connection.query("select * from goods order by itemid desc limit ?, 5", [(parseInt(pageno)-1)*5], (err, results, fields) => {
        if(err) {
            //res.json({"result":false});
            console.log(err);
            result = false;
        } else {
            //res.json({"result":true, "list":results});
            list = results;
        }

        let cnt = 0;    // 전체 데이터 개수
        connection.query("select count(*) cnt from goods", [], (err, results, fields)=>{
            if(err){
                res.json({'result':false});
                console.log(err);
                result = false;
            }else{
                cnt = results[0].cnt;
                res.json({'result':true, 'list':list, "count":cnt});
            }
        });
    });
});

// 상세보기 
app.get('/item/detail/:itemid', (req, res) => {
    let itemid = req.params.itemid;
    connection.query("select * from goods where itemid=?", [itemid], (err, results, fields) => {    // itemid를 이용해서 1개의 데이터를 찾아오기
        if(err) {
            console.log(err);
            res.json({"result":false});
        } else {
            res.json({"result":true, "item":results[0]});
        }
    });
});

// 이미지 다운로드 처리
app.get('/img/:pictureurl', (req, res) => {
    let pictureurl = req.params.pictureurl;
    //이미지 파일의 절대경로를 생성
    let file = 
    "/Users/sollyj/Desktop/KakaoCloudSchool/node/node_1124/public/img" + "/" + pictureurl; 
    console.log(__dirname);
    //파일 이름을 가지고 타입을 생성
    let mimetype = mime.lookup(pictureurl);
    res.setHeader('Content-disposition', 'attachment; filename=' + pictureurl);
    res.setHeader('Content-type', mimetype);
    //파일의 내용을 읽어서 res에 전송
    let filestream = fs.createReadStream(file);
    filestream.pipe(res);
});

// 현재 날짜를 문자열로 리턴하는 함수
const getDate = () => {
    let date = new Date();
    let year = date.getFullYear();
    // 월은 +1을 해야 우리가 사용하는 월이 됩니다.
    let month = date.getMonth() + 1;
    let day = date.getDate();
    month = month >= 10 ? month : '0' + month;
    day = day >= 10 ? day : '0' + day;
    return year + "-" + month + "-" + day;
}

// 날짜와 시간을 리턴하는 함수
const getTime = () => {
    let date = new Date();
    let hour = date.getHours();
    let minute = date.getMinutes();
    let second = date.getSeconds();

    hour >= 10 ? hour : '0' + hour;
    minute >= 10 ? minute : '0' + minute;
    second >= 10 ? second : '0' + second;

    return getDate() + " " + hour + ":" + minute + ":" + second;
}

// 데이터 삽입을 처리해주는 함수
app.post('/item/insert', upload.single('pictureurl'), (req, res) => {
    //파라미터 읽어오기
    const itemname = req.body.itemname;
    const description = req.body.description;
    const price = req.body.price;

    //파일 이름 - 업로드하는 파일이 없으면 default.png
    let pictureurl;
    if(req.file){
        pictureurl = req.file.filename;
    }else{
        pictureurl = 'default.jpg';
    }

    //가장 큰 itemid 찾기
    connection.query("select max(itemid) maxid from goods", [], (err, results, fields) => {
        let itemid;
        //최대값이 있으면 + 1 하고 없으면 1로 설정
        if(results.length > 0 ){
            itemid = results[0].maxid + 1;
        }else{
            itemid = 1;
        }

        //데이터 삽입
        connection.query("insert into goods(itemid, itemname, price, description, pictureurl, updatedate) values(?, ?, ?, ?, ?, ?)",
            [itemid, itemname, price, description, pictureurl, getDate()],
            (err, results, fields) => {
                if(err){
                    console.log(err);
                    res.json({"result":false});
                }else{
                    //현재 날짜 및 시간을 update.txt에 기록
                    const writeStream = fs.createWriteStream('./update.txt');
                    writeStream.write(getTime());
                    writeStream.end();

                    res.json({"result":true});
                }
            });
    });
});

// 데이터를 삭제하는 함수
app.post('/item/delete', (req, res) => {
    //post 방식으로 전송된 데이터 읽기
    let itemid = req.body.itemid;

    //itemid를 받아서 goods 테이블에서 삭제하기
    connection.query("delete from goods where itemid=?",
     [itemid], (err, results, fields)=>{
        if(err){
            console.log(err);
            res.json({"result":false});
        }else{
            //현재 날짜 및 시간을 update.txt에 기록
            const writeStream = fs.createWriteStream(
                './update.txt');
            writeStream.write(getTime());
            writeStream.end();

            res.json({"result":true});
        }
    });
});

// 수정을 get으로 요청했을때 - 수정화면으로 이동
app.get('/item/update', (req, res) => {
    //public 디렉토리의 update.html을 읽어내서 리턴
    fs.readFile('./public/update.html', (err, data)=>{
        res.end(data);
    });
});

app.post('item/update', upload.single('pictureurl'), (req, res) => {
    const itemid = req.body.itemid;
    const itemname = req.body.itemname;
    const price = req.body.price;
    const description = req.body.description;
    const oldpictureurl = req.body.oldpictureurl;

    // 수정할 파일 이름 만들기
    let pictureurl;
    if(req.file) {  // 업로드한 파일이 있는지 확인
        pictureurl = req.file.filename;
    } else {
        pictureurl = oldpictureurl;
    }

    connection.query("update goods set itemname=?, price=?, description=?, pictureurl=?, updatedate=? where itemid=?", [itemname, price, description, pictureurl, getDate(), itemid], (error, results, fields) => {
        if(error) {
            console.log(error);
            res.json({"result":false});
        } else {
            const writeStream = fs.createWriteStream("./update.txt");
            writeStream.write(getTime());
            writeStream.end();
            res.json({"result":true});
        }
    });
});

app.get("/item/updatedate", (req, res) => {
    fs.readFile('./update.txt', (err, data) => {
        res.json({"result":data.toString()});
    });
});



// 에러 발생시 처리
app.use((err, req, res, next)=>{
    console.log(err);
    res.status(500).send(err.message);
});

// 서버 구동
app.listen(app.get('port'), () => {
    console.log(app.get('port'), '번 포트에서 대기 중');
});