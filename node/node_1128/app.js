const express = require('express');
const path = require('path');
// const morgan = require('morgan');
// const mysql = require('mysql');
// const cookieParser = require('cookie-parser');
// const session = require('express-session');
// const multer = require('multer');
// const dotenv = require('dotenv');
// const FileStreamRotator = require('file-stream-rotator');   // 로그를 매일 기록하기 위한 모듈
// const fs = require('fs');
// const bodyParser = require('body-parser');
// let util = require('util'); // 파일 다운로드를 위한 모듈
// let mime = require('mime'); // 파일 다운로드를 위한 모듈
const {sequelize} = require('./models');
// const {Good} = require('./models/good.js');
const User = require('./models/user');
const Comment = require('./models/comment');

sequelize.sync({force:false})
    .then(() => {
        console.log('데이터 베이스 연결 성공');
    })
    .catch((err) => {
        console.log("데이터 베이스 연결 실패");
    });

//서버 설정
const app = express();
app.set('port', process.env.PORT || 9000);

//로그를 기록할 디렉토리 경로 생성
// let logDirectory = path.join(__dirname, 'log');

// fs.existsSync(logDirectory) || fs.mkdirSync(logDirectory);  //디렉토리가 없으면 생성

//로그 파일 옵션을 설정
// let accessLogStream = FileStreamRotator.getStream({
//     date_format:'YYYYMMDD',
//     filename:path.join(logDirectory, 'access-%DATE%.log'),
//     frequency:'daily',
//     verbose:false
// });
//로그 기록 설정
// app.use(morgan('combined', {stream:accessLogStream}));


//압축해서 전송하는 옵션 설정
//app.use(compression());


//POST 방식의 파라미터 읽을 수 있도록 설정
// app.use(bodyParser.json());
// app.use(bodyParser.urlencoded({
//     extended:true
// }));

//파일 업로드 설정
// const upload = multer({
//     storage:multer.diskStorage({
//         destination(req, file, done){
//             done(null, 'public/img');
//         },
//         filename(req, file, done){
//             const ext = path.extname(file.originalname);
//             done(null, path.basename(file.originalname, ext) + Date.now() + ext);
//         }
//     }),
//     limits:{fileSize: 10*1024*1024}
// });

//정적 파일의 경로를 설정
// app.use('/', express.static('public'));


// 기본 요청을 처리
// app.get('/', async(req, res) => {
//     res.sendFile(path.join(__dirname, 'index.html'));
// });

// 데이터 모두 가져오는 요청을 처리
// app.get('/item/all', async(req, res) => {
//     try{
//         let list = await Good.findAll();
//         res.json({"result":true, "list":list});
//     }catch(error){
//         console.log(error);
//         res.json({"result":true});
//     }
// });

// 데이터 일부분 가져오기
// 페이지 번호, 한 페이지에 출력할 데이터 개수가 필요하다
// app.get('/item/list', async(req, res) => {
//     let pageno = req.query.pageno;  // URL에 포함된 파라미터 읽기
//     if(pageno == undefined) {
//         pageno = 1; // 페이지번호를 전달하지 않으면 1페이지 디폴트
//     }

//     try{
//         let cnt = await Good.count();   // 테이블의 데이터 개수 

//         let list = await Good.findAll({
//             offset:((parseInt(pageno)-1)*5),
//             limit: 5
//         });
//         res.json({"result":true, "count":cnt, "list":list});
//     } catch(err) {
//         console.log(err);
//         res.json({"result":false});
//     }
// });

// 현재 날짜를 문자열로 리턴하는 함수
// const getDate = () => {
//     let date = new Date();
//     let year = date.getFullYear();
//     // 월은 +1을 해야 우리가 사용하는 월이 됩니다.
//     let month = date.getMonth() + 1;
//     let day = date.getDate();
//     month = month >= 10 ? month : '0' + month;
//     day = day >= 10 ? day : '0' + day;
//     return year + "-" + month + "-" + day;
// }

// 날짜와 시간을 리턴하는 함수
// const getTime = () => {
//     let date = new Date();
//     let hour = date.getHours();
//     let minute = date.getMinutes();
//     let second = date.getSeconds();

//     hour >= 10 ? hour : '0' + hour;
//     minute >= 10 ? minute : '0' + minute;
//     second >= 10 ? second : '0' + second;

//     return getDate() + " " + hour + ":" + minute + ":" + second;
// }

// 데이터 삽입 처리
// app.post('/item/insert', upload.single('pictureurl'), async(req, res) => {
//     //파라미터 읽어오기
//     const itemname = req.body.itemname;
//     const description = req.body.description;
//     const price = req.body.price;
    
//     //파일 이름 - 업로드하는 파일이 없으면 default.png
//     let pictureurl;
//     if(req.file){
//         pictureurl = req.file.filename;
//     }else{
//         pictureurl = 'default.jpg';
//     }

//     // 가장 큰 itemid를 이용해서 itemid 생성
//     let itemid = 1;
//     try{
//         let x = await Good.max('itemid');
//         itemid = x + 1;
//     } catch(err){
//         console.log(err);
//     }

//     Good.create({
//         itemid: itemid,
//         itemname: itemname,
//         price: price,
//         description: description,
//         pictureurl: pictureurl,
//         updatedate: getDate()
//     });

//     const writeStream = fs.createWriteStream('./update.txt');
//     writeStream.write(getTime());
//     writeStream.end();

//     res.json({"result":true});
// });

// 데이터 상세보기 처리
// app.get('/item/detail/:itemid', async(req, res) => {
//     let itemid = req.params.itemid;
//     try{
//         let item = await Good.findOne({
//             where:{
//                 itemid:itemid
//             }
//         })
//         res.json({"result":true, "item":item});
//     }catch(error){
//         console.log(error);
//         res.json({"result":false});
//     }
// });

// 이미지 다운로드 처리
// app.get('/img/:pictureurl', (req, res) => {
//     let pictureurl = req.params.pictureurl;
//     //이미지 파일의 절대경로를 생성
//     let file = 
//     "/Users/sollyj/Desktop/KakaoCloudSchool/node/node_1124/public/img" + "/" + pictureurl; 
//     console.log(__dirname);
//     //파일 이름을 가지고 타입을 생성
//     let mimetype = mime.lookup(pictureurl);
//     res.setHeader('Content-disposition', 'attachment; filename=' + pictureurl);
//     res.setHeader('Content-type', mimetype);
//     //파일의 내용을 읽어서 res에 전송
//     let filestream = fs.createReadStream(file);
//     filestream.pipe(res);
// });

// 데이터 수정
// 수정을 get으로 요청했을때 - 수정화면으로 이동
// app.get('/item/update', async(req, res) => {
//     //public 디렉토리의 update.html을 읽어내서 리턴
//     fs.readFile('./public/update.html', (err, data)=>{
//         res.end(data);
//     });
// });

// app.post('item/update', upload.single('pictureurl'), async(req, res) => {
//     const itemid = req.body.itemid;
//     const itemname = req.body.itemname;
//     const price = req.body.price;
//     const description = req.body.description;
//     const oldpictureurl = req.body.oldpictureurl;

//     // 수정할 파일 이름 만들기
//     let pictureurl;
//     if(req.file) {  // 업로드한 파일이 있는지 확인
//         pictureurl = req.file.filename;
//     } else {
//         pictureurl = oldpictureurl;
//     }

//     try{
//         await Good.update({
//             itemname:itemname,
//             price:price,
//             description:description,
//             pictureurl:pictureurl,
//             updatedate:getDate()
//         }, {where:{itemid:itemid}})
//         res.json({"result":true});
//     }catch(error){
//         console.log(error);
//         res.json({"result":false});
//     }
// });

// 데이터 삭제
// 데이터를 삭제하는 함수
// app.post('/item/delete', async(req, res) => {
//     //post 방식으로 전송된 데이터 읽기
//     let itemid = req.body.itemid;

//     try{
//         await Good.destroy({
//             where:{
//                 itemid:itemid
//             }
//         });
//         res.json({"result":true});
//     }catch(err){
//         console.log(err);
//         res.json({"result":false});
//     }
// });


// 에러 발생시 처리
app.use((err, req, res, next)=>{
    console.log(err);
    res.status(500).send(err.message);
});

// 서버 구동
app.listen(app.get('port'), () => {
    console.log(app.get('port'), '번 포트에서 대기 중');
});