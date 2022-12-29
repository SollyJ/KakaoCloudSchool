// 계산작업 대신해주는 worker파일을 따로 생성
onmessage = (e) => {    // html에서 요청이 오면 (이벤트 처리 함수)
    let num = e.data;   // 이벤트객체.data로 요청 데이터 받기
    let result = 0;
    for(var i = 0; i < num; i++) {
        result += i;
    }
    postMessage(result);    // html파일로 결과를 전송
}