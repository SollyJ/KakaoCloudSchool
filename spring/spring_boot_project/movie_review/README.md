# Movie Review
| 프로젝트 | 설정 |
|--|--|
|빌드 도구| gradle-groovy |
|packaging|jar|
|버전|3.0.1|
|의존성|spring boot devtools, lombok, spring web, thymeleaf, spring data jpa, mariadb|
|DB|MariaDB|


## 데이터베이스 구조
### N:M 관계
### 영화 - 회원의 리뷰관계
1:N을 두개로 분할해서 표현한다.
영화 : 리뷰  1 : N
회원 : 리뷰  1 : N


## Repository
### Entity
- BaseEntity
- Movie
- MovieImage
- Member
- Review

### Repository
- MovieRepository
- MovieImageRepository
- MemberRepository
- ReviewRepository


## Service
***service는 repository를 주입받는다.***
### DTO
- MovieDTO
- MovieImageDTO
- PageRequestDTO: 영화 목록 요청 정보를 담을 DTO
- PageResponseDTO: 영화 목록 결과 정보를 담을 DTO
- ReviewDTO
- UploadResultDTO: 파일 업로드 결과 정보를 담을 DTO

### Service 인터페이스
- MovieService
- ReviewService


## Controller
***controller는 service를 주입받는다.***
### UploadController
파일 업로드 처리하기 위한 컨트롤러
View Name: uploadajax.html
- 날짜 별로 디렉토리를 생성하는 메서드
- 파일 업로드 메서드
- 파일 내용을 전송해주는 메서드
- Thumbnail 업로드
- 업로드한 파일 삭제

### UploadTestController
파일 업로드 창으로 이동하기 위한 컨트롤러

### MovieController
영화 관련 컨트롤러
- 영화 등록(View Name: register.html)
- 영화 목록(View Name: list.html)
- 영화 상세보기(View Name: read.html)

### ReviewController
리뷰 관련 컨트롤러ajax로 동작하므로 @RestController로 설계한다.
영화 상세보기에서 리뷰를 작성하므로 View가 같다. 👉 read.html에 2개의 모달창을 만들어준다.
1. 리뷰 작업
2. 이미지 원본 보기

- 리뷰 목록
- 리뷰 등록
- 리뷰 수정
- 리뷰 삭제
