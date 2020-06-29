# :movie_camera: Project_SimpleMovie

Java 기반의 한국 영화 박스오피스 1~10위까지의 정보를 parsing 및 crawling하고 MongoDB에 저장 후 사용자에게 정보를 출력해주는 콘솔프로그래밍

## :heavy_check_mark:Developer Environment

  - Language: [:coffee:JAVA 1.8](https://www.oracle.com/kr/index.html)
  - IDE Tool: [:computer:Eclipse](https://www.eclipse.org/)
  - Package Manager: [MavenRepository](https://mvnrepository.com/)
  - Using Package: [jsoup, json-simple, Mongo-java-driver]
  - Version Tools: [Github, Sourcetree](https://www.sourcetreeapp.com/)
  - Parsing URL: [한국 영화진흥위원회](https://www.kofic.or.kr/kofic/business/infm/introBoxOffice.do)
  - Crawling URL: 
    + [네이버 영화](https://movie.naver.com/)
    + [다음 영화](https://movie.daum.net/main/new#slide-1-0)

## :floppy_disk:Repository structure description
#### 1. src/common
  - [SimpleMovieMain](): 프로그램 시작하는 곳 + 콘솔 프로그래밍 view단
  - [BoxOfficeParser](): 한국영화진흥위원회에서 일별 박스오피스 정보 수집(랭크, 영화제목, 누적 관객수, 누적 매출액)
#### 2. src/naver
  - [BoxOfficeNaver](): Naver에서 Boxoffice 1~10위까지 영화 정보(제목, 상영일자, 감독, 출연진 등) 및 영화 코드(네이버 고유 영화코드) 수집
  - [ReplyCrawlNaver](): Naver에서 해당 영화의 댓글, 평점, 작성자, 작성일자 수집해서 MongoDB에 저장
#### 3. src/daum
  - [BoxOfficeDaum](): Daum에서 Boxoffice 1~10위까지 영화 코드(다음 고유 영화코드) 수집
  - [ReplyCrawlDaum](): Daum에서 해당 영화의 댓글, 평점, 작성자, 작성일자 수집해서 MongoDB에 저장
#### 4. src/persistence
  - [ReplyDAO](): 네이버, 다음에서 수집한 영화 댓글 저장 또는 삭제할 때 사용하는 DAO
#### 5. src/domain 
  - [ReplyDTO](): 네이버, 다음에서 영화 댓글 수집 후 MongoDB에 저장할 때 사용하는 DTO
#### 6. pom.xml 
  - [pom.xml](): Maven에서 build할 Library 설정하는 장소
  
## :speech_balloon:How to use?

    First, you should goet a key form [한국영화진흥위원회](https://www.kofic.or.kr/kofic/business/infm/introBoxOffice.do)
    
1. BoxOfficeParser에서 발급받은 key를 교체한다.
2. ReplyDAO에서 MongoDB를 세팅한다(connect, DB, Collection 등).
3. 메인 프로그램을 실행한다.
4. 1-10위 중 원하는 영화를 선택한다 → 1-10의 숫자를 입력
5. Run the program!

