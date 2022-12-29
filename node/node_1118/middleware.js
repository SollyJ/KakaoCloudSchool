const express = require('express');
const morgan = require('morgan');
const cookieParser = require('cookie-parser');
const dotenv = require('dotenv');

dotenv.config();    // .env 읽어오기

const app = express();
app.set('port', process.env.PORT);

// app.use 미들웨어 장착
// 로그 출력
app.use(morgan('dev'));
// post방식의 파라미터를 읽을 수 있도록 설정 
app.use(express.json());
app.use(express.urlencoded({extended: true}));
// 쿠키 사용이 가능하도록 설정
app.use(cookieParser(process.env.COOKIE_SECRET));