package baekjoon.algorithmcourse;

import java.util.Scanner;

/**
 * 쉬운 계단 수
 * 
 * 문제
 * 45656이란 수를 보자.
 * 이 수는 인접한 모든 자리수의 차이가 1이 난다. 이런 수를 계단 수라고 한다.
 * 세준이는 수의 길이가 N인 계단 수가 몇 개 있는지 궁금해졌다.
 * N이 주어질 때, 길이가 N인 계단 수가 총 몇 개 있는지 구하는 프로그램을 작성하시오. (0으로 시작하는 수는 없다.)
 * 
 * 입력
 * 첫째 줄에 N이 주어진다. N은 1보다 크거나 같고, 100보다 작거나 같은 자연수이다.
 * 
 * 출력
 * 첫째 줄에 정답을 1,000,000,000으로 나눈 나머지를 출력한다.
 * 
 * 
 * 예제 입력 1
 * 1
 * 
 * 예제 출력 1
 * 9
 * 
 * 
 * 예제 입력 1
 * 2
 * 
 * 예제 출력 1
 * 17
 * 
 */
/*
 * D[i][j] : 길이 i, 끝자리가 j인 계단수의 개수
 * D[N][L] = D[N-1][L-1] + D[N-1][L+1] (1<=L<=8)
 * D[N][L] = D[N-1][L-1] (L==9)
 * D[N][L] = D[N-1][L+1] (L==0)
 */
public class P10844 {
	// 큰 숫자가 나와서 long 타입으로 해야 함
	static long[][] STAIR_NUM_COUNT = new long[101][10];
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt();
		scan.close();
		
		for(int lastNum=1; lastNum<=9; lastNum++) {
			P10844.STAIR_NUM_COUNT[1][lastNum] = 1;
		}
		
		P10844 p10844 = new P10844();
		
		/*long sum = 0;
		for(int lastNum=0; lastNum<=9; lastNum++) {
			sum += p10844.countStairNum(N, lastNum);
		}
		// 합계도 mod 연산 필요
		System.out.println(sum%1000000000);*/
		
		P10844.STAIR_NUM_COUNT[1][0] = 0;
		System.out.println(p10844.countStairNum2(N));
	}

	/**
	 * 재귀
	 * @param N
	 * @param lastNum
	 * @return 길이가 N, 마지막 숫자가 lastNum인 계단 수의 개수
	 */
	public long countStairNum(int N, int lastNum) {
		if(N == 0) {
			return 0;
		}
		if(P10844.STAIR_NUM_COUNT[N][lastNum] > 0) {
			return P10844.STAIR_NUM_COUNT[N][lastNum];
		}
		
		if(lastNum == 0) {
			if(P10844.STAIR_NUM_COUNT[N-1][1] > 0) {
				P10844.STAIR_NUM_COUNT[N][lastNum] = P10844.STAIR_NUM_COUNT[N-1][1];
			}else {
				P10844.STAIR_NUM_COUNT[N][lastNum] = countStairNum(N-1, 1);
			}
		}else if (lastNum == 9) {
			if(P10844.STAIR_NUM_COUNT[N-1][9] > 0) {
				P10844.STAIR_NUM_COUNT[N][lastNum] = P10844.STAIR_NUM_COUNT[N-1][8];
			}else {
				P10844.STAIR_NUM_COUNT[N][lastNum] = countStairNum(N-1, 8);
			}
		}else {
			if(P10844.STAIR_NUM_COUNT[N-1][lastNum-1]>0 && P10844.STAIR_NUM_COUNT[N-1][lastNum+1]>0) {
				P10844.STAIR_NUM_COUNT[N][lastNum] = P10844.STAIR_NUM_COUNT[N-1][lastNum-1] + P10844.STAIR_NUM_COUNT[N-1][lastNum+1];
			}else {
				P10844.STAIR_NUM_COUNT[N][lastNum] = countStairNum(N-1, lastNum-1) + countStairNum(N-1, lastNum+1);
			}
		}
		
		P10844.STAIR_NUM_COUNT[N][lastNum] %= 1000000000;
		
		return P10844.STAIR_NUM_COUNT[N][lastNum];
	}
	
	/**
	 * for문
	 * @param N
	 * @return 길이가 N인 계단수의 개수
	 */
	public long countStairNum2(int N) {
		long sum = 0;
		for(int length=1; length<=N; length++) {
			for(int lastNum=0; lastNum<=9; lastNum++) {
				if(length > 1) {
					if(lastNum == 0) {
						P10844.STAIR_NUM_COUNT[length][lastNum] = P10844.STAIR_NUM_COUNT[length-1][1]; 
					}else if (lastNum == 9) {
						P10844.STAIR_NUM_COUNT[length][lastNum] = P10844.STAIR_NUM_COUNT[length-1][8];
					}else {
						P10844.STAIR_NUM_COUNT[length][lastNum] = P10844.STAIR_NUM_COUNT[length-1][lastNum-1] + P10844.STAIR_NUM_COUNT[length-1][lastNum+1];
					}
					P10844.STAIR_NUM_COUNT[length][lastNum] %= 1000000000;
				}
				
				if(length == N) {
					sum += P10844.STAIR_NUM_COUNT[length][lastNum];
				}
			}
		}
		sum %= 1000000000;
		return sum;
	}
}



// 정답 코드
/*import java.util.*;
import java.math.*;

public class Main {
    public static long mod = 1000000000L;
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[][] d = new long[n+1][10];
        for (int i=1; i<=9; i++) {
            d[1][i] = 1;
        }
        for (int i=2; i<=n; i++) {
            for (int j=0; j<=9; j++) {
                d[i][j] = 0;
                if (j-1 >= 0) {
                    d[i][j] += d[i-1][j-1];
                }
                if (j+1 <= 9) {
                    d[i][j] += d[i-1][j+1];
                }
                d[i][j] %= mod;
            }
        }
        long ans = 0;
        for (int i=0; i<=9; i++) {
            ans += d[n][i];
        }
        ans %= mod;
        System.out.println(ans);
    }
}*/
