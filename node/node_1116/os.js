// os타입 구하기
const os = require('os');

// 문자열을 비교할 때는 일치하는 것을 찾는 경우 보다는 포함된 경우를 찾는경우가 많다.
// indexOf
// 소문자로 다 바꿔서 비교하는 것이 좋다.
let position = os.type().toLocaleLowerCase().indexOf("windows");
if(position >= 0)   console.log("windows");
else                console.log("windows 아님");