package baekjoon.algorithmCourse.bruteForce;

import java.util.Scanner;

/**
 * 문제
 * 폴리오미노란 크기가 1×1인 정사각형을 여러 개 이어서 붙인 도형이며, 다음과 같은 조건을 만족해야 한다.
 * 
 * 정사각형은 서로 겹치면 안된다.
 * 도형은 모두 연결되어 있어야 한다.
 * 정사각형의 꼭지점끼리 연결되어 있어야 한다. 즉, 변과 꼭지점이 맞닿아있으면 안된다.
 * 정사각형 4개를 이어 붙인 폴리오미노는 테트로미노라고 하며, 다음과 같은 5가지가 있다.
 * 
 * 아름이는 크기가 N×M인 종이 위에 테트로미노 하나를 놓으려고 한다. 종이는 1×1 크기의 칸으로 나누어져 있으며, 각각의 칸에는 정수가 하나 써 있다.
 * 테트로미노 하나를 적절히 놓아서 테트로미노가 놓인 칸에 쓰여 있는 수들의 합을 최대로 하는 프로그램을 작성하시오.
 * 테트로미노는 반드시 한 정사각형이 정확히 하나의 칸을 포함하도록 놓아야 하며, 회전이나 대칭을 시켜도 된다.
 * 
 * 입력
 * 첫째 줄에 종이의 세로 크기 N과 가로 크기 M이 주어진다. (4 ≤ N, M ≤ 500)
 * 둘째 줄부터 N개의 줄에 종이에 써 있는 수가 주어진다. i번째 줄의 j번째 수는 위에서부터 i번째 칸, 왼쪽에서부터 j번째 칸에 써 있는 수이다. 입력으로 주어지는 수는 1,000을 넘지 않는 자연수이다.
 * 
 * 출력
 * 첫째 줄에 테트로미노가 놓인 칸에 쓰인 수들의 합의 최댓값을 출력한다.
 * 
 * 
 * 예제 입력 1
 * 5 5
 * 1 2 3 4 5
 * 5 4 3 2 1
 * 2 3 4 5 6
 * 6 5 4 3 2
 * 1 2 1 2 1
 * 
 * 예제 출력 1
 * 19
 * 
 * 
 * 예제 입력 2
 * 4 5
 * 1 2 3 4 5
 * 1 2 3 4 5
 * 1 2 3 4 5
 * 1 2 3 4 5
 * 
 * 예제 출력 2
 * 20
 * 
 * 
 * 예제 입력 3
 * 4 10
 * 1 2 1 2 1 2 1 2 1 2
 * 2 1 2 1 2 1 2 1 2 1
 * 1 2 1 2 1 2 1 2 1 2
 * 2 1 2 1 2 1 2 1 2 1
 * 
 * 예제 출력 3
 * 7
 * 
 */
//테트로미노 가지수 = 19
//가능한 가지수 = 19*N*M = 4750000 이므로 모든 경우에 수에 대하여 다 해본다
//시간복잡도 : O(NM)
public class P14500 {
	public static void main(String[] args) {
		//input
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt();
		int M = scan.nextInt();
		
		int[][] tetrominoAry = new int[N][M];
		
		for(int n=0; n<N; n++) {
			for(int m=0; m<M; m++) {
				tetrominoAry[n][m] = scan.nextInt();
			}
		}
		scan.close();
		
		P14500 p14500 = new P14500();
		System.out.println(p14500.calcMaxSum(N, M, tetrominoAry));
	}
	
	public int calcMaxSum(int N, int M, int[][] tetrominoAry) {
		int maxSum = 0;
		
		for(int n=0; n<N; n++) {
			for(int m=0; m<M; m++) {
				//ㅁㅁㅁㅁ
				if(m+3 < M) { 
					int sum = tetrominoAry[n][m] + tetrominoAry[n][m+1] + tetrominoAry[n][m+2] + tetrominoAry[n][m+3];
					if(sum > maxSum) {
						maxSum = sum;
					}
				}
				
				//ㅁ
				//ㅁ
				//ㅁ
				//ㅁ
				if(n+3 < N) { 
					int sum = tetrominoAry[n][m] + tetrominoAry[n+1][m] + tetrominoAry[n+2][m] + tetrominoAry[n+3][m];
					if(sum > maxSum) {
						maxSum = sum;
					}
				}
				
				//ㅁㅁ
				//ㅁㅁ
				if(n+1<N && m+1<M) { 
					int sum = tetrominoAry[n][m] + tetrominoAry[n+1][m] + tetrominoAry[n][m+1] + tetrominoAry[n+1][m+1];
					if(sum > maxSum) {
						maxSum = sum;
					}
				}
				
				//ㅁ
				//ㅁ
				//ㅁㅁ
				if(n+2<N && m+1<M) { 
					int sum = tetrominoAry[n][m] + tetrominoAry[n+1][m] + tetrominoAry[n+2][m] + tetrominoAry[n+2][m+1];
					if(sum > maxSum) {
						maxSum = sum;
					}
				}
				
				//   ㅁ
				//ㅁㅁㅁ
				if(n-1>=0 && m+2<M) { 
					int sum = tetrominoAry[n][m] + tetrominoAry[n][m+1] + tetrominoAry[n][m+2] + tetrominoAry[n-1][m+2];
					if(sum > maxSum) {
						maxSum = sum;
					}
				}
				
				//ㅁ ㅁ
				//  ㅁ 
				//  ㅁ 
				if(n+2<N && m+1<M) { 
					int sum = tetrominoAry[n][m] + tetrominoAry[n][m+1] + tetrominoAry[n+1][m+1] + tetrominoAry[n+2][m+1];
					if(sum > maxSum) {
						maxSum = sum;
					}
				}
				
				//ㅁㅁㅁ
				//ㅁ
				if(n+1<N && m+2<M) { 
					int sum = tetrominoAry[n][m] + tetrominoAry[n][m+1] + tetrominoAry[n][m+2] + tetrominoAry[n+1][m];
					if(sum > maxSum) {
						maxSum = sum;
					}
				}
				
				//  ㅁ
				//  ㅁ
				//ㅁ ㅁ
				if(n-2>=0 && m+1<M) { 
					int sum = tetrominoAry[n][m] + tetrominoAry[n][m+1] + tetrominoAry[n-1][m+1] + tetrominoAry[n-2][m+1];
					if(sum > maxSum) {
						maxSum = sum;
					}
				}
				
				//ㅁㅁㅁ
				//   ㅁ
				if(n+1<N && m+2<M) { 
					int sum = tetrominoAry[n][m] + tetrominoAry[n][m+1] + tetrominoAry[n][m+2] + tetrominoAry[n+1][m+2];
					if(sum > maxSum) {
						maxSum = sum;
					}
				}
				
				//ㅁ ㅁ
				//ㅁ
				//ㅁ
				if(n+2<N && m+1<M) { 
					int sum = tetrominoAry[n][m] + tetrominoAry[n][m+1] + tetrominoAry[n+1][m] + tetrominoAry[n+2][m];
					if(sum > maxSum) {
						maxSum = sum;
					}
				}
				
				//ㅁ
				//ㅁㅁㅁ
				if(n+1<N && m+2<M) { 
					int sum = tetrominoAry[n][m] + tetrominoAry[n+1][m] + tetrominoAry[n+1][m+1] + tetrominoAry[n+1][m+2];
					if(sum > maxSum) {
						maxSum = sum;
					}
				}
				
				//ㅁ
				//ㅁ ㅁ
				//  ㅁ
				if(n+2<N && m+1<M) { 
					int sum = tetrominoAry[n][m] + tetrominoAry[n+1][m] + tetrominoAry[n+1][m+1] + tetrominoAry[n+2][m+1];
					if(sum > maxSum) {
						maxSum = sum;
					}
				}
				
				//  ㅁ ㅁ
				//ㅁ ㅁ
				if(n-1>=0 && m+2<M) { 
					int sum = tetrominoAry[n][m] + tetrominoAry[n][m+1] + tetrominoAry[n-1][m+1] + tetrominoAry[n-1][m+2];
					if(sum > maxSum) {
						maxSum = sum;
					}
				}
				
				//  ㅁ
				//ㅁ ㅁ
				//ㅁ
				if(n-1>=0 && n+1<N && m+1<M) { 
					int sum = tetrominoAry[n][m] + tetrominoAry[n][m+1] + tetrominoAry[n-1][m+1] + tetrominoAry[n+1][m];
					if(sum > maxSum) {
						maxSum = sum;
					}
				}
				
				//ㅁ ㅁ
				//  ㅁ ㅁ
				if(n+1<N && m+2<M) { 
					int sum = tetrominoAry[n][m] + tetrominoAry[n][m+1] + tetrominoAry[n+1][m+1] + tetrominoAry[n+1][m+2];
					if(sum > maxSum) {
						maxSum = sum;
					}
				}
				
				//ㅁ ㅁ ㅁ
				//  ㅁ
				if(n+1<N && m+2<M) { 
					int sum = tetrominoAry[n][m] + tetrominoAry[n][m+1] + tetrominoAry[n][m+2] + tetrominoAry[n+1][m+1];
					if(sum > maxSum) {
						maxSum = sum;
					}
				}
				
				//ㅁ
				//ㅁㅁ
				//ㅁ
				if(n+2<N && m+1<M) { 
					int sum = tetrominoAry[n][m] + tetrominoAry[n+1][m] + tetrominoAry[n+2][m] + tetrominoAry[n+1][m+1];
					if(sum > maxSum) {
						maxSum = sum;
					}
				}
				
				//  ㅁ
				//ㅁ ㅁ ㅁ
				if(n-1>=0 && m+2<M) { 
					int sum = tetrominoAry[n][m] + tetrominoAry[n][m+1] + tetrominoAry[n][m+2] + tetrominoAry[n-1][m+1];
					if(sum > maxSum) {
						maxSum = sum;
					}
				}
				
				//  ㅁ
				//ㅁ ㅁ
				//  ㅁ
				if(n-1>=0 && n+1<N && m+1<M) { 
					int sum = tetrominoAry[n][m] + tetrominoAry[n][m+1] + tetrominoAry[n-1][m+1] + tetrominoAry[n+1][m+1];
					if(sum > maxSum) {
						maxSum = sum;
					}
				}
				
			}
		}
		
		return maxSum;
	}
}
