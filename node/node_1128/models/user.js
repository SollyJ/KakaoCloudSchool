const Sequelize = require('sequelize');

module.exports = class User extends Sequelize.Model {
    static init(sequelize){
        // 테이블에 대한 설정
        return super.init({
            // 컬럼에 대한 설정
            // 외래키는 여기서 만들지 않는다.
            name:{
                type: Sequelize.STRING(20),
                allowNull: false,
                unique: true
            },
            age:{   
                type: Sequelize.INTEGER,
                allowNull: false
            }
        },{
            // 테이블에 대한 설정
            sequelize,
            timestamps: true,   // 생성시간와 수정시간 자동으로 생성해주는 것
            modelName: 'User',
            tableName: 'users',
            paranoid: false,    // 삭제시간 자동으로 생성, 실제 삭제되는 것이 아니고 삭제시간만 생성됨
            charset: 'utf8',
            collate: 'utf8_general_ci'
        })
    }

    static associate(db) {
        // 외래키에 대한 설정
        // User모델의 id키는 Comment모델의 commenter키를 외래키로 참조하겠다.
        db.User.hasMany(db.Comment, {foreignKey:'commenter', targetKey:'id'});
    }
}