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
	static boolean[][] CHESS;
	static int N;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		P9663.N = scan.nextInt();
		scan.close();
		P9663.CHESS = new boolean[N][N];
		
		P9663 p9663 = new P9663();
		System.out.println(p9663.calcQueen(0));
	}
	
	public boolean isPossiblePosition(int row, int col) {
		// 가로 세로
		/*for(int index=0; index<P9663.N; index++) {
			if(P9663.CHESS[row][index]) {
				return false;
			}
			if(P9663.CHESS[index][col]) {
				return false;
			}
		}*/
		
		// calcQueen() 메소드에서 row, col이 차례로 증가하는 순서로 검사하므로 가로는 검사할 필요가 없고
		// 세로도 현재 row, col보다 위에 있는 것만 검사하면 된다.
		
		// 세로 위
		for(int index=0; index<row; index++) {
			if(P9663.CHESS[index][col]) {
				return false;
			}
		}
		
		
		// 대각선
		/*int calcIndex = 1;
		boolean rightDown = true;
		boolean leftUp = true;
		boolean rightUp = true;
		boolean leftDown = true;
		while(rightDown || leftUp || rightUp || leftDown) {
			// \ 대각선
			// 우하단
			if(rightDown && row+calcIndex<P9663.N && col+calcIndex<P9663.N) {
				if(P9663.CHESS[row+calcIndex][col+calcIndex]) {
					return false;
				}
			}else {
				rightDown = false;
			}
			
			// 좌상단
			if(leftUp && row-calcIndex>=0 && col-calcIndex>=0) {
				if(P9663.CHESS[row-calcIndex][col-calcIndex]) {
					return false;
				}
			}else {
				leftUp = false;
			}
			
			// / 대각선
			// 우상단
			if(rightUp && row-calcIndex>=0 && col+calcIndex<P9663.N) {
				if(P9663.CHESS[row-calcIndex][col+calcIndex]) {
					return false;
				}
			}else {
				rightUp = false;
			}
			
			// 좌하단
			if(leftDown && row+calcIndex<P9663.N && col-calcIndex>=0) {
				if(P9663.CHESS[row+calcIndex][col-calcIndex]) {
					return false;
				}
			}else {
				leftDown = false;
			}
			
			calcIndex++;
		}*/
		
		
		// 대각선도 마찬가지로 우하단 쪽은 검사할 필요가 없음
		int calcIndex = 1;
		boolean leftUp = true;
		boolean rightUp = true;
		boolean leftDown = true;
		while(leftUp || rightUp || leftDown) {
			// 좌상단
			if(leftUp && row-calcIndex>=0 && col-calcIndex>=0) {
				if(P9663.CHESS[row-calcIndex][col-calcIndex]) {
					return false;
				}
			}else {
				leftUp = false;
			}
			
			// 우상단
			if(rightUp && row-calcIndex>=0 && col+calcIndex<P9663.N) {
				if(P9663.CHESS[row-calcIndex][col+calcIndex]) {
					return false;
				}
			}else {
				rightUp = false;
			}
			
			// 좌하단
			if(leftDown && row+calcIndex<P9663.N && col-calcIndex>=0) {
				if(P9663.CHESS[row+calcIndex][col-calcIndex]) {
					return false;
				}
			}else {
				leftDown = false;
			}
			
			calcIndex++;
		}
		return true;
	}

	public int calcQueen(int row) {
		if(row == P9663.N) {
			return 1;
		}
		
		int count = 0;
		
		for(int col=0; col<P9663.N; col++) {
			if(isPossiblePosition(row, col)) {
				P9663.CHESS[row][col] = true;
				count += calcQueen(row+1);
				P9663.CHESS[row][col] = false;
			}
		}
		return count;
	}
}



