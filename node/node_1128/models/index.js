const Sequelize = require('sequelize');
const User = require('./user');  // 모델 가져오기
const Comment = require('./comment');

const env = process.env.NODE_ENV || 'development';  // 환경설정
const config = require('../config/config')[env]; // 환경설정 내용 가져오기

const db = {};  // 내보낼 객체

// ORM 설정
const sequelize = new Sequelize(config.database, config.username, config.password, config);

db.sequelize = sequelize;
db.Sequelize = Sequelize;

// 모델 만들때마다 추가해주면됨
db.User = User;
User.init(sequelize);

db.Comment = Comment;
Comment.init(sequelize);

Comment.associate(db);
User.associate(db);

module.exports = db;