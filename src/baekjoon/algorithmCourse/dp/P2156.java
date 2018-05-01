package baekjoon.algorithmCourse.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

/**
 * 포도주 시식
 * 
 * 문제
 * 효주는 포도주 시식회에 갔다. 그 곳에 갔더니, 테이블 위에 다양한 포도주가 들어있는 포도주 잔이 일렬로 놓여 있었다. 효주는 포도주 시식을 하려고 하는데, 여기에는 다음과 같은 두 가지 규칙이 있다.
 * 포도주 잔을 선택하면 그 잔에 들어있는 포도주는 모두 마셔야 하고, 마신 후에는 원래 위치에 다시 놓아야 한다.
 * 연속으로 놓여 있는 3잔을 모두 마실 수는 없다.
 * 효주는 될 수 있는 대로 많은 양의 포도주를 맛보기 위해서 어떤 포도주 잔을 선택해야 할지 고민하고 있다.
 * 1부터 n까지의 번호가 붙어 있는 n개의 포도주 잔이 순서대로 테이블 위에 놓여 있고, 각 포도주 잔에 들어있는 포도주의 양이 주어졌을 때, 효주를 도와 가장 많은 양의 포도주를 마실 수 있도록 하는 프로그램을 작성하시오. 
 * 예를 들어 6개의 포도주 잔이 있고, 각각의 잔에 순서대로 6, 10, 13, 9, 8, 1 만큼의 포도주가 들어 있을 때, 첫 번째, 두 번째, 네 번째, 다섯 번째 포도주 잔을 선택하면 총 포도주 양이 33으로 최대로 마실 수 있다.
 * 
 * 입력
 * 첫째 줄에 포도주 잔의 개수 n이 주어진다. (1≤n≤10,000) 둘째 줄부터 n+1번째 줄까지 포도주 잔에 들어있는 포도주의 양이 순서대로 주어진다. 포도주의 양은 1,000 이하의 음이 아닌 정수이다.
 * 
 * 출력
 * 첫째 줄에 최대로 마실 수 있는 포도주의 양을 출력한다.
 * 
 * 
 * 예제 입력 1
 * 6
 * 6
 * 10
 * 13
 * 9
 * 8
 * 1
 * 
 * 예제 출력 1
 * 33
 * 
 */
/*
 * D[n][m] : n개의 잔, 마지막 잔을 먹었는지 안먹었는지(m==0 || m==1) 
 * D[n][m]
 */
public class P2156 {
	static int[][] MAX_WINE = new int[10001][2];
	
	public static void main(String[] args) {
		/*Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		
		int[] wine = new int[n+1];
		for(int index=1; index<=n; index++) {
			wine[index] = scan.nextInt();
		}
		scan.close();
		
		P2156 p2156 = new P2156();
		
//		int m0 = p2156.maxWine(n, 0, wine);
//		int m1 = p2156.maxWine(n, 1, wine);
//		System.out.println(Math.max(m0, m1));
		
//		int[][] maxWine = new int[n+1][2];
		System.out.println(p2156.maxWine2(n, wine));*/
		
		
		P2156 p2156 = new P2156();
		
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		){
			int n = Integer.parseInt(br.readLine());
			int[] wine = new int[n+1];
			for(int index=1; index<=n; index++) {
				wine[index] = Integer.parseInt(br.readLine());
			}
			
			int m0 = p2156.maxWine(n, 0, wine);
			int m1 = p2156.maxWine(n, 1, wine);
			int max = (m0>m1)?m0:m1;
			bw.write(max + "\n");
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	public int maxWine(int n, int m, int[] wine) {
		if(n==1 && m==0) {
			return 0;
		}else if(n==1 && m==1) {
			return wine[1];
		}else if(n==2 && m==0) {
			return wine[1];
		}else if(n==2 && m==1) {
			return wine[1] + wine[2];
		}
		
		if(P2156.MAX_WINE[n][m] > 0) {
			return P2156.MAX_WINE[n][m];
		}
		
		
		if(m == 0) {
			int m0 = 0;
			if(P2156.MAX_WINE[n-1][0] > 0) {
				m0 = P2156.MAX_WINE[n-1][0];
			}else {
				m0 = maxWine(n-1, 0, wine);
			}
			
			int m1 = 0;
			if(P2156.MAX_WINE[n-1][1] > 0) {
				m1 = P2156.MAX_WINE[n-1][1];
			}else {
				m1 = maxWine(n-1, 1, wine);
			}
			
			return (m0>m1)?m0:m1;
		}else {
			int oxo = 0;
			if(P2156.MAX_WINE[n-2][1] > 0) {
				oxo = P2156.MAX_WINE[n-2][1];
			}else {
				oxo = maxWine(n-2, 1, wine);
			}
			oxo += wine[n];
			
			int m2 = 0;
			if(P2156.MAX_WINE[n-2][0] > 0) {
				m2 = P2156.MAX_WINE[n-2][0];
			}else {
				m2 = maxWine(n-2, 0, wine);
			}
			
			int xoo = m2 + wine[n-1] + wine[n];
			int xxo = m2 + wine[n];
			
			return Math.max(oxo, Math.max(xoo, xxo));
		}
	}
	
	public int maxWine2(int n, int[] wine) {
		P2156.MAX_WINE[1][0] = 0;
		P2156.MAX_WINE[1][1] = wine[1];
		P2156.MAX_WINE[2][0] = wine[1];
		P2156.MAX_WINE[2][1] = wine[1] + wine[2];
		
		for(int index=3; index<=n; index++) {
			P2156.MAX_WINE[index][0] = Math.max(P2156.MAX_WINE[index-1][0], P2156.MAX_WINE[index-1][1]);
			
			int oxo = P2156.MAX_WINE[index-2][1] + wine[index];
			int xoo = P2156.MAX_WINE[index-2][0] + wine[index-1] + wine[index];
			int xxo = P2156.MAX_WINE[index-2][0] + wine[index];
			P2156.MAX_WINE[index][1] = Math.max(oxo, Math.max(xoo, xxo));
		}
		
		return Math.max(P2156.MAX_WINE[n][0], P2156.MAX_WINE[n][1]);
	}
}
