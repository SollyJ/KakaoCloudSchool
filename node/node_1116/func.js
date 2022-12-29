const {odd, even} = require('./export');    // {}로 묶어서 보낸 것은 객체기때문에 이름이 똑같아야 한다.

const checkOddorEven = (num) => {
    if(num % 2 == 0)    return even;
    else                return odd;
}

module.exports = checkOddorEven;  // 이렇게 내보내면 가져올때는 아무이름이나 사용해서 받으면 된다.