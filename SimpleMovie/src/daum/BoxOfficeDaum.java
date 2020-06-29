package daum;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class BoxOfficeDaum {

	String baseUrl = "http://ticket2.movie.daum.net/Movie/MovieRankList.aspx";
	int finalCnt = 0; // 수집을 멈추기 위한 변수

	public String[][] daumMovieRank(String[][] movieRank) throws IOException {

		Document doc = Jsoup.connect(baseUrl).get();
		Elements movieList = doc.select("div.desc_boxthumb > strong.tit_join > a");

		for (Element movie : movieList) {
			if (finalCnt == 10) { // 1~10위까지의 영화정보 수집완료! 빠져나가세요~
				break;
			}
			
			// 제목 가져오기
			String title = movie.text();
			
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

			
			String url = movie.attr("href");
			Document movieDoc = Jsoup.connect(url).get();

			// 상세영화 페이지가 없는 영화는 생략
			if (movieDoc.select("span.txt_name").size() == 0) {
				continue;
			}

			
			String daumHref = movieDoc.select("a.area_poster").get(0).attr("href");
			String daumCode = daumHref.substring(daumHref.lastIndexOf("=") + 1, daumHref.lastIndexOf("#")); // 영화코드

			movieRank[flag][11] = daumCode;
			finalCnt += 1;
		}
		return movieRank;
	}
}