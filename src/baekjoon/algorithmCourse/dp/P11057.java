package baekjoon.algorithmCourse.dp;

import java.util.Scanner;

/**
 * 오르막 수
 * 
 * 문제
 * 오르막 수는 수의 자리가 오름차순을 이루는 수를 말한다. 이 때, 인접한 수가 같아도 오름차순으로 친다.
 * 예를 들어, 2234와 3678, 11119는 오르막 수이지만, 2232, 3676, 91111은 오르막 수가 아니다.
 * 수의 길이 N이 주어졌을 때, 오르막 수의 개수를 구하는 프로그램을 작성하시오. 수는 0으로 시작할 수 있다.
 * 
 * 입력
 * 첫째 줄에 N (1 ≤ N ≤ 1,000)이 주어진다.
 * 
 * 출력
 * 첫째 줄에 길이가 N인 오르막 수의 개수를 10,007로 나눈 나머지를 출력한다.
 * 
 * 
 * 예제 입력 1
 * 1
 * 
 * 예제 출력 1
 * 10
 * 
 * 
 * 예제 입력 2
 * 2
 * 
 * 예제 출력 2
 * 55
 * 
 * 
 * 예제 입력 3
 * 3
 * 
 * 예제 출력 3
 * 220
 * 
 */
/*
 * D[N][L] : 길이가 N이고 끝자리 수가 L인 오르막 수의 개수
 * D[N][L] = sum(D[N-1][i]) (0<=i<=L)
 */
public class P11057 {
	static int[][] ASC_NUM_COUNT = new int[1001][10];
	public static void main(String[] args) {
		 Scanner scan = new Scanner(System.in);
		 int N = scan.nextInt();
		 scan.close();
		 
		 for(int lastNum=0; lastNum<=9; lastNum++) {
			 P11057.ASC_NUM_COUNT[1][lastNum] = 1;
		 }
		 
		 P11057 p11057 = new P11057();
		/* int sum = 0;
		 for(int lastNum=0; lastNum<=9; lastNum++) {
			 sum += p11057.countAscNum(N, lastNum);
		 }
		 System.out.println(sum%10007);*/
		 
		 System.out.println(p11057.countAscNum2(N));
	}

	/**
	 * 재귀
	 * @param n
	 * @param lastNum
	 * @return 길이가 n, 마지막 숫자가 lastNum인 오르막 수의 개수
	 */
	public int countAscNum(int n, int lastNum) {
		if(n == 0) {
			return 0;
		}
		if(P11057.ASC_NUM_COUNT[n][lastNum] > 0) {
			return P11057.ASC_NUM_COUNT[n][lastNum];
		}
		
		for(int prevLastNum=0; prevLastNum<=lastNum; prevLastNum++) {
			if(P11057.ASC_NUM_COUNT[n-1][prevLastNum] > 0) {
				P11057.ASC_NUM_COUNT[n][lastNum] += P11057.ASC_NUM_COUNT[n-1][prevLastNum];
			}else {
				P11057.ASC_NUM_COUNT[n][lastNum] += countAscNum(n-1, prevLastNum);
			}
		}
		P11057.ASC_NUM_COUNT[n][lastNum] %= 10007;
		
		return P11057.ASC_NUM_COUNT[n][lastNum];
	}
	
	/**
	 * for문
	 * @param n
	 * @return 길이가 n인 오르막 수의 개수
	 */
	public int countAscNum2(int n) {
		int sum = 0;
		for(int length=1; length<=n; length++) {
			for(int lastNum=0; lastNum<=9; lastNum++) {
				if(n != 1) {
					for(int prevLastNum=0; prevLastNum<=lastNum; prevLastNum++) {
						P11057.ASC_NUM_COUNT[length][lastNum] += P11057.ASC_NUM_COUNT[length-1][prevLastNum];
					}
					P11057.ASC_NUM_COUNT[length][lastNum] %= 10007;
				}
				if(length == n) {
					sum += P11057.ASC_NUM_COUNT[length][lastNum];
				}
			}
		}
		return sum%10007;
	}
}
