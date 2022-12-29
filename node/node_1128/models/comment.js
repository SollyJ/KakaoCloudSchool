const Sequelize = require('sequelize');

module.exports = class Comment extends Sequelize.Model {
    static init(sequelize){
        // 테이블에 대한 설정
        return super.init({
            // 컬럼에 대한 설정
            comment:{
                type: Sequelize.STRING(100),
                allowNull: false,
                unique: true
            }
        },{
            // 테이블에 대한 설정
            sequelize,
            timestamps: true,   // 생성날짜와 지운날짜 자동으로 생성해주는 것
            modelName: 'Comment',
            tableName: 'comments',
            paranoid: false,
            charset: 'utf8',
            collate: 'utf8_general_ci'
        })
    }

    static associate(db) {
        console.log(db.Comment);
        // 외래키에 대한 설정
        // Comment모델은 User모델의 id키와 매핑이 되는 외래키를 갖고있다.
        db.Comment.belongsTo(db.User, {foreignKey:'commenter', targetKey:'id'});
    }
}