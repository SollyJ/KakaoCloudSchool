# Board (게시판)
| 프로젝트 | 설정 |
|--|--|
|빌드 도구| gradle-groovy |
|packaging|jar|
|버전|3.0.1|
|의존성|lombok, spring web, thymeleaf, spring data jpa, mariadb|
|DB|mariaDB|

## 애플리케이션 구조
Controller ↔️ Service ↔️ Repository
						DTO            Entity
						
## 데이터베이스 구조
회원(Member) 테이블 : 게시글(Board) 테이블 1 : N
게시글 테이블 : 댓글(Reply) 테이블 1 : N
회원 테이블 : 댓글 테이블 1 : N (위 과정에서 자동으로 1 : N이 된다.)

## Repository
테이블 Entity 생성 ➡️ 관계 설정
Repository 생성 ➡️ join query

## Service
***service는 repository를 주입받는다.***
데이터 전송할때 필요한 DTO생성
Service 인터페이스
ServiceImpl

## Controller
***controller는 service를 주입받는다.***

## Test
Repository의 쿼리들을 테스트
Service의 메서드들을 테스트

