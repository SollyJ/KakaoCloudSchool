const Sequelize = require("sequelize");

module.exports = class Hashtag extends Sequelize.Model {
  static init(sequelize) {
    return super.init(
      {
        title: {
          type: Sequelize.STRING(15),
          allowNull: false,
          unique: true,
        },
      },
      {
        sequelize,
        modelName: "Hashtag",
        tableName: "hashtags",
        timestamps: true,
        underscored: false,
        paranoid: false, // 삭제할때 실제로 삭제하는 것이 아닌 삭제시간을 timestamps에 넣는다.
        charset: "utf8mb4",
        collate: "utf8mb4_general_ci",
      }
    );
  }

  static associate(db) {
    db.Hashtag.belongsToMany(db.Post, { through: "PostHashtag" }); // post:hashtag n:m
  }
};
