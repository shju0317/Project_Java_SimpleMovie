package naver;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class BoxOfficeNaver {
	String url = "https://movie.naver.com/movie/running/current.nhn";
	String title = ""; // 영화제목
	String score = ""; // 평점
	String bookRate = ""; // 예매율
	String genre = ""; // 장르
	String actor = ""; // 출연진
	String runTime = ""; // 상영시간
	String release = ""; // 개봉일
	String director = ""; // 감독
	String naverCode = ""; // 네이버 영화코드
	int finalCnt = 0; // 수집을 멈추기 위한 변수(1~10위까지 완료)

	public String[][] naverMovieRank(String[][] movieRank) throws IOException {

		Document doc = Jsoup.connect(url).get();
		Elements movieList = doc.select("ul.lst_detail_t1 > li > dl.lst_dsc");

		// Label : JAVA v8 부터 생긴 기능
		for (Element movie : movieList) {
			if (finalCnt == 10) { // 1~10위까지의 영화정보 수집완료! 빠져나가세요~
				break;
			}

			// 네이버 영화정보 크롤링
			title = movie.select("dt.tit > a").text(); // 영화제목

			int flag = 999;

			for (int i = 0; i < movieRank.length; i++) {
				if (movieRank[i][1].equals(title)) {
					// BoxOffice 1~10위권내의 영화로 판별 크롤링
					flag = i; // i = 영화의 순위. 0~9까지의 값.
					break;
				}
			}

			// 1~10위권 외의 영화는 크롤링x
			// flag가 0~9 사이의 값이면 크롤링 시작.
			// 조건주는방법1 : flag < 0 || flag > 9
			// 조건주는방법2 : flag == 999
			if (flag == 999) {
				continue;
			}

			// 예매율, 감독, 출연진 초기화
			bookRate = "0";
			actor = "";
			director = "";

			score = movie.select("span.num").get(0).text(); // 평점

			if (movie.select("span.num").size() == 2) { // 예매율 -> 결측치(N/A)
				bookRate = movie.select("span.num").get(1).text();
			}

			genre = movie.select("dl.info_txt1 span.link_txt").get(0).text(); // 장르

			actor = movie.select("dl.info_txt1 span.link_txt[1] > a").text(); // 출연진

			runTime = movie.select("dl.info_txt1 > dd").text(); // 상영시간

			String temp = movie.select("dl.info_txt1 > dd").text(); // 상영시간

			int beginTimeIndex = temp.indexOf("|");
			int endTimeIndex = temp.lastIndexOf("|");

			if (beginTimeIndex == endTimeIndex) { // 상영시간
				runTime = temp.substring(0, endTimeIndex);
			} else {
				runTime = temp.substring(beginTimeIndex + 2, endTimeIndex);
			}

			int releaseTxtIndex = temp.lastIndexOf("개봉"); // 개봉날짜
			release = temp.substring(endTimeIndex + 2, releaseTxtIndex);

			// 0:없음 1:있음
			int dCode = 0; // 감독 유무 확인
			int aCode = 0; // 출연진 유무 확인

			if (!movie.select("dt.tit_t2").text().equals("")) {
				dCode = 1; // 감독 있음
			}

			if (!movie.select("dt.tit_t3").text().equals("")) {
				aCode = 1; // 출연진 있음
			}

			if (dCode == 1 && aCode == 1) {
				director = movie.select("dd > span.link_txt").get(1).text(); // 감독
				actor = movie.select("dd > span.link_txt").get(2).text(); // 출연진
			} else if (dCode == 0 && aCode == 1) {
				actor = movie.select("dd > span.link_txt").get(1).text(); // 출연진
			} else if (dCode == 1 && aCode == 0) {
				director = movie.select("dd > span.link_txt").get(1).text(); // 감독
			}

			String naverHref = movie.select("dt.tit > a").attr("href");
			naverCode = naverHref.substring(naverHref.lastIndexOf("=") + 1); // 네이버 영화코드

			// 수집된 영화정보를 movieRank INPUT

			movieRank[flag][2] = bookRate;
			// movieRank[flag][] = score;
			movieRank[flag][3] = genre;
			movieRank[flag][4] = runTime.trim();
			movieRank[flag][5] = release.trim();
			movieRank[flag][6] = director;
			movieRank[flag][7] = actor;
			movieRank[flag][10] = naverCode;
			finalCnt += 1;

		}
		return movieRank;
	}
}
