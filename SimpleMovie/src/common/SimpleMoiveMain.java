package common;

import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Scanner;

import daum.BoxOfficeDaum;
import daum.ReplyCrawlerDaum;
import naver.BoxOfficeNaver;
import naver.ReplyCrawlerNaver;
import persistence.ReplyDAO;

public class SimpleMoiveMain {
	public static void main(String[] args) throws Exception {
		BoxOfficeParser bParser = new BoxOfficeParser();
		BoxOfficeNaver bon = new BoxOfficeNaver();
		BoxOfficeDaum don = new BoxOfficeDaum();
		ReplyCrawlerNaver nCrawler = new ReplyCrawlerNaver();
		ReplyCrawlerDaum dCrawler = new ReplyCrawlerDaum();
		ReplyDAO rDao = new ReplyDAO();
		
		// 1.박스오피스 정보 + 네이버 영화 정보 + 다음 영화 정보(1~10위)
		String[][] movieRank = new String[10][12];

		// 순위, 영화제목, 예매율, 장르, 상영시간, 개봉일자 ,감독
		// 출연진, 누적관객수, 누적매출액, 네이버코드, 다음코드

		// 1-1. BoxOffice Parsing :)
		movieRank = bParser.getParser();

		// 1-2. Naver BoxOffice Crawling
		movieRank = bon.naverMovieRank(movieRank);

		// 1-3. Daum BoxOffice Crawling
		movieRank = don.daumMovieRank(movieRank);
		
		// 2. View단 실행
		// userVal = 사용자가 입력한 영화번호
		int userVal = userInterface(movieRank);
		
		// 3. 사용자가 선택한 영화의 네이버, 다음 댓글 정보를 수집 및 분석
		// 3-1. MongoDB 데이터 삭제
		//  수집하는 댓글의 영화가 MongoDB에 저장되어 있는 영화라면
		//  해당 영화 댓글 우선 삭제 후 새로운 댓글 저장
		rDao.deleteReply(movieRank[userVal-1][1]);
		
		// 3-2. naver 댓글 수집 + MongoDB 저장
		HashMap<String, Integer> nMap = nCrawler.naverCrawler(movieRank[userVal-1][1], movieRank[userVal-1][10]);
		// 3-3. daum 댓글 수집 + MongoDB 저장
		HashMap<String, Integer> dMap = dCrawler.daumCrawler(movieRank[userVal-1][1], movieRank[userVal-1][11]);
		
		// 4. 사용자에게 결과출력
		double nTotal = nMap.get("total"); 
		double avgNaver = nTotal / nMap.get("cnt");
		
		double dTotal = dMap.get("total"); 
		double avgDaum = dTotal / dMap.get("cnt");
		
		DecimalFormat dropDot = new DecimalFormat(".#");
		DecimalFormat threeDot = new DecimalFormat("###,###");
		BigInteger money = new BigInteger(movieRank[userVal-1][9]);
		
		System.out.println("◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆");
		System.out.println("<< Description of " + movieRank[userVal-1][1] + " >>");
		System.out.println("-------------------------------------------------------------------");
		System.out.println("◆ 장르: " + movieRank[userVal-1][3] + "   (예매율: " + movieRank[userVal-1][2]+"%)");
		System.out.println("◆ 상영시간: " + movieRank[userVal-1][4]);
		System.out.println("◆ 개봉일자: " + movieRank[userVal-1][5]);
		System.out.println("◆ 감독: " + movieRank[userVal-1][6]);
		System.out.println("◆ 출연진: " + movieRank[userVal-1][7]);
		System.out.println("◆ 누적:   [관객수: " + threeDot.format(Long.parseLong(movieRank[userVal-1][8])) 
							+ "명] [매출액: " + threeDot.format(money) + "원]");
		System.out.println("◆ 네이버: [댓글수: " + nMap.get("cnt") + "건] [평점: " + dropDot.format(avgNaver) + "점]");
		System.out.println("◆ 다음:   [댓글수: " + dMap.get("cnt") + "건] [평점: " + dropDot.format(avgDaum) + "점]");
		System.out.println("◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆");
	}

	// VIEW : 프로그램 시작 인터페이스 + 사용자 값 입력
	public static int userInterface(String[][] movieRank) {
		// 2. View단
		// 2-1. 유저에게 BoxOffice 예매율 1~10위까지의 정보 제공

		Scanner sc = new Scanner(System.in);
		int userVal = 0;

		// 현재 날짜 계산하기
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
		String today = sdf.format(cal.getTime());

		System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
		System.out.println("\t\t\t◆ SimpleMovie ver 1.2 ◆");
		System.out.println();
		System.out.println("                                      Developer: Hyunju Shin(shju0317) ");
		System.out.println("========================================================================");
		System.out.println("◆ Today: " + today);
		System.out.println("◆ Real-time BoxOffice Rank (" + (cal.get(Calendar.MONTH) + 1) + "월 " + cal.get(Calendar.DATE) + "일)");
		System.out.println("------------------------------------------------------------------------");

		for (int i = 0; i < movieRank.length; i++) {

			String info = "";
			if (movieRank[i][10] == null) {
				info = " (※상영정보없음)";
			}
			System.out.println("   [No." + movieRank[i][0] + "]   " + movieRank[i][1] + info);
		}

		// 2-2. 사용자가 입력하는 부분
		while (true) {
			System.out.println("------------------------------------------------------------------------");
			System.out.println(">> 보고싶은 영화 번호(순위)를 입력하세요 :)");
			System.out.print(">> 번호: ");
			userVal = sc.nextInt();

			// 유효성 체크
			// >> 1~10까지의 값(정상)
			// 1. 1~10 이외의 숫자를 넣었을 때
			if (userVal < 0 || userVal > 10) {
				System.out.println(">> [WARNING] 1~10 사이의 숫자를 입력하세요 :(");
				continue;
				// 2. 정보없는 영화 선택했을 때
			} else if (movieRank[userVal - 1][10] == null) { // 사용자가 입력한 번호의 영화의 정보 유무 체크
				System.out.println(">> [WARNING] 해당 영화는 상영정보가 없습니다 :( 다른 영화를 선택해주세요!");
			} else {
				sc.close();
				break;
			} // 통과 = 사용자의 입력값이 0~10

		}

		System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
		return userVal;
	}

	public static void printArr(String[][] movieRank) { // movieRank 출력하는 코드
		System.out.println(
				"▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
		for (int i = 0; i < movieRank.length; i++) {
			System.out.print(movieRank[i][0] + "\t");
			System.out.print(movieRank[i][1] + "\t");
			System.out.print(movieRank[i][2] + "\t");
			System.out.print(movieRank[i][3] + "\t");
			System.out.print(movieRank[i][4] + "\t");
			System.out.print(movieRank[i][5] + "\t");
			System.out.print(movieRank[i][6] + "\t");
			System.out.print(movieRank[i][7] + "\t");
			System.out.print(movieRank[i][8] + "\t");
			System.out.print(movieRank[i][9] + "\t");
			System.out.print(movieRank[i][10] + "\t");
			System.out.println(movieRank[i][11]);
		}
		System.out.println(
				"▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
	}
}
