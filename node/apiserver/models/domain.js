const Sequelize = require("sequelize");

module.exports = class Domain extends Sequelize.Model {
  static init(sequelize) {
    return super.init(
      {
        host: {
          type: Sequelize.STRING(100),
          allowNull: false,
        },
        clientSecret: {
          type: Sequelize.STRING(36),
          allowNull: false,
        },
        type: {
          type: Sequelize.ENUM("free", "premium"),
          allowNull: false,
        },
      },
      {
        sequelize,
        modelName: "Domain",
        tableName: "domains",
        timestamps: true,
        underscored: false,
        paranoid: true,
      }
    );
  }
  static associate(db) {
    // Domain은 User의 기본키를 외래키로 갖고있다.
    db.Domain.belongsTo(db.User);
  }
};
