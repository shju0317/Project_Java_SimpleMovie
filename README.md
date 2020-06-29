# :movie_camera:Project_SimpleMovie

Python 기반의 Selenium(feat: Chrome Driver)을 사용하여 인스타그램에 로그인하고, 원하는 해시태그로 피드를 검색한 후 피드별로 좋아요를 클릭하고 댓글 등록을 반복하는 매크로 프로그램.

## :heavy_check_mark:Developer Environment

  - Language: [:crocodile:Python 3.7](https://www.python.org/)
  - IDE Tool: [:computer:Pycharm](https://www.jetbrains.com/ko-kr/pycharm/download/#section=windows)
  - Package Manager: [:snake:Anaconda](https://www.anaconda.com/)
  - Using Package: [requests, selenium, beautifulsoup4, time, random](https://anaconda.org/)
  - Using WebDriver: [Chrome Driver](https://chromedriver.chromium.org/downloads) (
Use the same version as the Chrome browser version you use!)

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

