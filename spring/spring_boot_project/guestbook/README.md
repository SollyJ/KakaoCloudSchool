# Guest Book 
| 프로젝트 | 설정 |
|--|--|
|빌드 도구| gradle-groovy |
|packaging|jar|
|버전|2.7.7|
|의존성|lombok, spring web, thymeleaf, spring data jpa, mariadb|
|DB|mariaDB|


## 애플리케이션 개요
- 목록
list - `GET`
- 등록
register - `GET`, `POST`
- 조회
read - `GET`
- 수정
modify - `GET`, `POST`
- 삭제
remove - `POST`

## 애플리케이션 구조
Controller ↔️ Service ↔️ Repository
						DTO            Entity
                         
## 환경설정
환경설정 파일 application.properties → application.yml로 변경
포트번호(80), 데이터베이스 접속 정보, jpa, thymeleaf, logging 설정

