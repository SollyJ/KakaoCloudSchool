// 오늘 날짜로 된 디렉토리 없으면 생성
const fs = require('fs');
let today = new Date();
let today_string = today.getFullYear()+"" + today.getMonth().toString() + today.getDate().toString();   // 오늘 날짜 문자열 만들기

fs.access(today_string, (error, accessed) => {  // 디렉토리명 있는지 찾기
    if(error) {
        fs.mkdir(today_string, (error, created) => {    // 없으면 생성
            if(error) {
                console.log(error);
            } else{
                console.log('Directory Created');
            }

        });
    }  
    else console.log(accessed); 
});