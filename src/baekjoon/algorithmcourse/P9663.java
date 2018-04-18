package baekjoon.algorithmcourse;

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
public class P9663 {
	static int[][] CHESS;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt();
		scan.close();
		
		P9663.CHESS = new int[N][N];
	}
	
	public boolean setQueen(int row, int col, int queen) {
		
	}

	public int calcQueen(int row, int N) {
		if(row == N) {
			return 1;
		}
		
		int count = 0;
		
		for(int col=0; col<N; col++) {
			if(P9663.CHESS[row][col] == 0) {
				if(setQueen(row, col, 1)) {
					count += calcQueen(row+1, N);
				}
				setQueen(row, col, 0);
			}
		}
		return count;
	}
}
