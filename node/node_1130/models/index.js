"use strict";

const Sequelize = require("sequelize");
const env = process.env.NODE_ENV || "development";
const config = require("../config/config.json")[env];
const User = require("./user");
const Post = require("./post");
const Hashtag = require("./hashtag");

const db = {};
const sequelize = new Sequelize(
  config.database,
  config.username,
  config.password,
  config
);

db.sequelize = sequelize;
db.Sequelize = Sequelize;

db.User = User;
db.Post = Post;
db.Hashtag = Hashtag;

// init은 초기화
User.init(sequelize);
Post.init(sequelize);
Hashtag.init(sequelize);

// associate는 관계 설정
User.associate(db);
Post.associate(db);
Hashtag.associate(db);

module.exports = db;
