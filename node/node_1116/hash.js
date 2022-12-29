const crypto = require('crypto');   // 모듈 가져오기
let password = "1234";

/* 단방향 암호화
let p1 = crypto.createHash("sha256").update(password).digest("base64");
console.log(p1);

let password_compare1 = "1234";
let p2 = crypto.createHash("sha256").update(password_compare1).digest("base64");
console.log(p1 === p2); // 동일한 데이터를 암호화하면 같은 결과가 나온다. 따라서 동일 데이터인지 비교할 수 있다.

let password_compare2 = "456";
let p3 = crypto.createHash("sha256").update(password_compare2).digest("base64");
console.log(p1 === p3); */

/* 양방향 암호화 */
const fs = require("fs");
let data = fs.readFileSync("./key.txt").toString().split("\n"); // 암호화 관련 키 텍스트 파일 읽어오기
let key = [];
for(var i=0; i<data.length; i++) {
    key[i] = data[i].split(' ')[1];
}

let cipher = crypto.createCipheriv(key[0], key[1], key[2]); // 암호화 객체 생성
cipher.update(password, 'utf-8', 'base64');
let result_cipher = cipher.final('base64');
console.log(result_cipher);

let decipher = crypto.createDecipheriv(key[0], key[1], key[2]); // 복호화 객체 생성
decipher.update(result_cipher, 'base64', 'utf-8');
let result_decipher = decipher.final('utf-8');
console.log(result_decipher);