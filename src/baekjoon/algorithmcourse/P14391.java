package baekjoon.algorithmcourse;

import java.util.Scanner;

/**
 * 종이 조각
 * 
 * 문제
 * 영선이는 숫자가 써있는 직사각형 종이를 가지고 있다. 종이는 1×1 크기의 정사각형 칸으로 나누어져 있고, 숫자는 각 칸에 하나씩 써있다.
 * 행은 위에서부터 아래까지 번호가 매겨져 있고, 열은 왼쪽부터 오른쪽까지 번호가 매겨져 있다.
 * 영선이는 직사각형을 겹치지 않는 조각으로 자르려고 한다. 각 조각은 크기가 세로나 가로 크기가 1인 직사각형 모양이다. 길이가 N인 조각은 N자리 수로 나타낼 수 있다.
 * 가로 조각은 왼쪽부터 오른쪽까지 수를 이어 붙인 것이고, 세로 조각은 위에서부터 아래까지 수를 이어붙인 것이다.
 * 
 * 아래 그림은 4×4 크기의 종이를 자른 한 가지 방법이다.
 * 
 * 
 * 각 조각의 합은 493 + 7160 + 23 + 58 + 9 + 45 + 91 = 7879 이다.
 * 종이를 적절히 잘라서 조각의 합을 최대로 하는 프로그램을 작성하시오.
 * 
 * 입력
 * 첫째 줄에 종이 조각의 세로 크기 N과 가로 크기 M이 주어진다. (1 ≤ N, M ≤ 4)
 * 둘째 줄부터 종이 조각이 주어진다. 각 칸에 써있는 숫자는 0부터 9까지 중 하나이다.
 * 
 * 출력
 * 영선이가 얻을 수 있는 점수의 최대값을 출력한다.
 * 
 * 
 * 예제 입력 1
 * 2 3
 * 123
 * 312
 * 
 * 예제 출력 1
 * 435
 * 
 * 
 * 예제 입력 2
 * 2 2
 * 99
 * 11
 * 
 * 예제 출력 2
 * 182
 * 
 * 
 * 예제 입력 3
 * 4 3
 * 001
 * 010
 * 111
 * 100
 * 
 * 예제 출력 3
 * 1131
 * 
 * 
 * 예제 입력 4
 * 1 1
 * 8
 * 
 * 예제 출력 4
 * 8
 * 
 */
public class P14391 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt();
		int M = scan.nextInt();
		
		int[][] paper = new int[N][M];
		for(int row=0; row<N; row++) {
			char[] input = scan.next().toCharArray();
			for(int col=0; col<M; col++) {
				paper[row][col] = (int)(input[col] - '0');
			}
		}
		scan.close();
		
		
		int maxSum = 0;
		
		
		// 모든 집합
		// 가로는 0, 세로는 1
		for(int set=0; set<(1<<N*M); set++) {
			// 현재 집합에서 계산한 합계
			int sum = 0;
			
			// 가로 계산
			for(int row=0; row<N; row++) {
				int subSumRow = 0;
				for(int col=0; col<M; col++) {
					// paper의 0 row 부터 set의 앞에 붙는다고 생각하고 계산한 index
					// paper[0]paper[1]...paper[N-1]
					int index = (N-row)*M - col - 1;
					
					// paper의 마지막 row부터 set의 앞에 붙는다고 가정하면 아래처럼 표현 가능
//					int index = row*M + col;
					
					int subset = 1 << index;
					
					// 가로일 경우 subSumRow에 누적
					if((set&subset) == 0) {
						subSumRow = subSumRow*10 + paper[row][col];
					}else {// 세로일 경우 sum에 누적하고 다시 가로가 나올 경우를 대비해 subSum 0으로
						sum += subSumRow;
						subSumRow = 0;
					}
				}
				// 가로로 끝난 경우를 위해 계산한 subSumRow를 sum에 누적
				sum += subSumRow;
			}
			
			// 세로
			for(int col=0; col<M; col++) {
				int subSumCol = 0;
				for(int row=0; row<N; row++) {
					int index = (N-row)*M - col - 1;
//					int index = row*M + col;
					
					int subset = 1 << index;
					if((set&subset) != 0) {
						subSumCol = subSumCol*10 + paper[row][col];
					}else {
						sum += subSumCol;
						subSumCol = 0;
					}
				}
				sum += subSumCol;
			}
			
			if(sum > maxSum) {
				maxSum = sum;
			}
			
		}
		
		System.out.println(maxSum);
	}
}
