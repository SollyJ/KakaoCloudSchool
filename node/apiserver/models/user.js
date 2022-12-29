const Sequelize = require("sequelize");

module.exports = class User extends Sequelize.Model {
  static init(sequelize) {
    return super.init(
      {
        email: {
          type: Sequelize.STRING(40),
          allowNull: true,
          unique: true,
        },
        nick: {
          type: Sequelize.STRING(15),
          allowNull: false,
        },
        password: {
          type: Sequelize.STRING(100),
          allowNull: true,
        },
        provider: {
          type: Sequelize.STRING(10),
          allowNull: false,
          defaultValue: "local",
        },
        snsId: {
          type: Sequelize.STRING(30),
          allowNull: true,
        },
      },
      {
        sequelize,
        modelName: "User",
        tableName: "snsuser",
        timestamps: true,
        underscored: false,
        paranoid: true,
        charset: "utf8",
        collate: "utf8_general_ci",
      }
    );
  }

  static associate(db) {
    db.User.hasMany(db.Post); // user:post 1:n
    db.User.hasMany(db.Domain); // user:domain 1:n
    db.User.belongsToMany(db.User, {
      // user:user(팔로워) n:m
      foreignKey: "followerId",
      as: "Followings",
      through: "Follow",
    });
    db.User.belongsToMany(db.User, {
      // user:user(팔로잉) n:m
      foreignKey: "followingId",
      as: "Followers",
      through: "Follow",
    });
  }
};
