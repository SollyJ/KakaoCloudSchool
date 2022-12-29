const fs = require('fs');

/* 동기적으로 읽기
let data = fs.readFileSync('./text.txt');
console.log(data.toString());

// enter 단위로 분할해서 읽기
console.log(data.toString().split('\n')[0]); */

/* 비동기적으로 읽기 
fs.readFile('./text.txt', (error, data) => {
    if(error) {
        console.log(error.message);
    } else {
        console.log(data.toString());
    }
}); */

/* 비동기적으로 읽기 - promise 사용 */
//const fs = require('fs').promises;
fs.readFile('./text.txt')
    .then((data) => {console.log(data.toString());})
    .catch((error) => {console.log(error.message);})
