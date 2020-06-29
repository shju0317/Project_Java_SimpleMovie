# :movie_camera: Project_SimpleMovie

Java 기반의 한국 영화 박스오피스 1~10위까지의 정보를 parsing 및 crawling하고 MongoDB에 저장 후 사용자에게 정보를 출력해주는 콘솔프로그래밍

## :heavy_check_mark:Developer Environment

  - Language: [:coffee:JAVA 1.8](https://www.oracle.com/kr/index.html)
  - IDE Tool: [:computer:Eclipse](https://www.eclipse.org/)
  - Package Manager: [MavenRepository](https://mvnrepository.com/)
  - Using Package: [jsoup, json-simple, Mongo-java-driver]
  - Version Tools: [Github, Sourcetree]()
  - Parsing URL: [한국 영화진흥위원회](https://www.kofic.or.kr/kofic/business/infm/introBoxOffice.do)
  - Crawling URL: 
    + [네이버 영화](https://movie.naver.com/)
    + [다음 영화](https://movie.daum.net/main/new#slide-1-0)

## :floppy_disk:Repository structure description
#### 1. practice
  - [chapter01_crawl](https://github.com/ChoLong02/Project_Python_InstagramMacro/blob/master/practice/chapter01_crawl.py)
  - [chapter02_webdriver](https://github.com/ChoLong02/Project_Python_InstagramMacro/blob/master/practice/chapter02_webdriver.py)
  - [chapter03_selenium_crawl](https://github.com/ChoLong02/Project_Python_InstagramMacro/blob/master/practice/chapter03_selenium_crawl.py)
  - [chapter04_facebook_login](https://github.com/ChoLong02/Project_Python_InstagramMacro/blob/master/practice/chapter04_facebook_login.py)
#### 2. libs
  - [crawler](https://github.com/ChoLong02/Project_Python_InstagramMacro/blob/master/libs/crawler.py)
#### 3. instagram
  - [hashtag_reply_macro](https://github.com/ChoLong02/Project_Python_InstagramMacro/blob/master/instagram/hashtag_reply_macro.py)

## :speech_balloon:How to use?

    Prepare your Instagram ID and password.
    
1. Download the same version of the [chrome driver](https://chromedriver.chromium.org/downloads) as your Chrome browser
2. Import the [project](https://github.com/ChoLong02/Project_Python_InstagramMacro) into Pycharm tool
3. Create webdriver directory within project
4. Drag and Drop the downloaded chrome driver to the webdriver directory
5. Go to Instagram's Feed and check the selector(Refer to [instagram/hashtag_reply_macro.py](https://github.com/ChoLong02/Project_Python_InstagramMacro/blob/master/instagram/hashtag_reply_macro.py) 33 Line)
6. Run the program!

