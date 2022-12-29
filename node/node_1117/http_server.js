const fs = require('fs').promises;
const http = require('http');

// 서버 생성
http.createServer(async(request, response) => { // 비동기적으로 동작하라
    try{
        // 파일의 내용을 읽어서 data에 저장
        // 다음 명령은 이 명령이 끝나면 실행
        const data = await fs.readFile('./index.html');
        response.writeHead(200, {'Content-Type': 'text/html; charset=utf-8'});    // 200이면 성공
        response.end(data);
    } catch(error){
        response.writeHead(500, {'Content-Type': 'text/html; charset=utf-8'});
        response.end(error.message);
    }   
}).listen(8000, () => { // 포트번호는 일반적으로 1024번까지는 예약되어있다. 1521, 3306, 27017, 8080번은 사용중이므로 다른 것을 사용하도록 권함
    console.log("8000번 포트에서 서버 대기중");
});