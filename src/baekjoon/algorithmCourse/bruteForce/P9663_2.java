package baekjoon.algorithmCourse.bruteForce;

import java.util.Scanner;

/**
 * N-Queen
 * 
 * 문제
 * N-Queen 문제는 크기가 N × N인 체스판 위에 퀸 N개를 서로 공격할 수 없게 놓는 문제이다.
 * N이 주어졌을 때, 퀸을 놓는 방법의 수를 구하는 프로그램을 작성하시오.
 * 
 * 입력
 * 첫째 줄에 N이 주어진다. (1 ≤ N < 15)
 * 
 * 출력
 * 첫째 줄에 퀸 N개를 서로 공격할 수 없게 놓는 경우의 수를 출력한다.
 * 
 * 
 * 예제 입력 1
 * 8
 * 
 * 예제 출력 1
 * 92
 * 
 */
// O(1) 복잡도로 계산
public class P9663_2 {
	static int N;
	static boolean[][] CHESS;
	/**
	 * 세로 라인에 퀸이 있는지를 체크하는 배열
	 */
	static boolean[] CHECK_COL;
	/**
	 * / 방향 대각선에 퀸이 있는지 체크하는 배열
	 */
	static boolean[] CHECK_SLASH;
	/**
	 * \ 방향 대각선에 퀸이 있는지 체크하는 배열
	 */
	static boolean[] CHECK_BACKSLASH;
	

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		P9663_2.N = scan.nextInt();
		scan.close();
		
		P9663_2.CHESS = new boolean[P9663_2.N][P9663_2.N];
		P9663_2.CHECK_COL = new boolean[P9663_2.N];
		P9663_2.CHECK_SLASH = new boolean[P9663_2.N*2+1];
		P9663_2.CHECK_BACKSLASH = new boolean[P9663_2.N*2+1];
		
		P9663_2 p9663_2 = new P9663_2();
		System.out.println(p9663_2.calcQueen(0));
	}
	
	public boolean isPossiblePosition(int row, int col) {
		// 세로
		if(P9663_2.CHECK_COL[col]) {
			return false;
		}
		
		// 대각선
		if(P9663_2.CHECK_SLASH[row+col]) {
			return false;
		}
		if(P9663_2.CHECK_BACKSLASH[row+P9663_2.N-col]) {
			return false;
		}
		
		return true;
	}

	public int calcQueen(int row) {
		if(row == P9663_2.N) {
			return 1;
		}
		
		int count = 0;
		
		for(int col=0; col<P9663_2.N; col++) {
			if(isPossiblePosition(row, col)) {
				P9663_2.CHESS[row][col] = true;
				P9663_2.CHECK_COL[col] = true;
				P9663_2.CHECK_SLASH[row+col] = true;
				P9663_2.CHECK_BACKSLASH[row+P9663_2.N-col] = true;
				
				count += calcQueen(row+1);
				
				P9663_2.CHESS[row][col] = false;
				P9663_2.CHECK_COL[col] = false;
				P9663_2.CHECK_SLASH[row+col] = false;
				P9663_2.CHECK_BACKSLASH[row+P9663_2.N-col] = false;
			}
		}
		return count;
	}
}



