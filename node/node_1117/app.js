//모듈 가져오기
const express = require('express');
const path = require('path');
const morgan = require('morgan');
const fs = require('fs');
const fileStreamRotator = require('file-stream-rotator');

//express 모듈 객체 생성
const app = express();

//웹 서버가 실행할 포트 설정
app.set('port', process.env.PORT || 8000);  // process.env: 환경변수 접근 객체 

//요청 함수
app.get('/index', (req, res) => {    // /뒤에 아무것도 없으면 포트번호까지만 요청, /index라고 하면 포트번호뒤에 /index를 붙여야 요청 가능
    //res.send('Hello, Express'); // res: 클라이언트에게 응답할 내용
    res.sendFile(path.join(__dirname, './index.html'));
});

//서버 실행
app.listen(app.get('port'), () => {
    console.log(app.get('port'), '번 포트에서 대기 중');
});

// 로그 파일을 저장할 디렉토리 생성
const logDirectory = path.join(__dirname, 'log');
fs.existsSync(logDirectory) || fs.mkdirSync(logDirectory);  // 디렉토리가 없으면 설정

// 일단위 로그 기록 설정
const accessLogStream = fileStreamRotator.getStream({
    date_format: 'YYYYMMDD',
    filename: path.join(logDirectory, 'access-%DATE%.log'),
    frequency: 'daily', // 일단위
    verbose: true
});

app.use(morgan('combined', {stream: accessLogStream}));