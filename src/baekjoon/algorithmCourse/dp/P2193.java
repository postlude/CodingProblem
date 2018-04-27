package baekjoon.algorithmCourse.dp;

import java.util.Scanner;

/**
 * 이친수
 * 
 * 문제
 * 0과 1로만 이루어진 수를 이진수라 한다. 이러한 이진수 중 특별한 성질을 갖는 것들이 있는데, 이들을 이친수(pinary number)라 한다. 이친수는 다음의 성질을 만족한다.
 * 이친수는 0으로 시작하지 않는다.
 * 이친수에서는 1이 두 번 연속으로 나타나지 않는다. 즉, 11을 부분 문자열로 갖지 않는다.
 * 예를 들면 1, 10, 100, 101, 1000, 1001 등이 이친수가 된다. 하지만 0010101이나 101101은 각각 1, 2번 규칙에 위배되므로 이친수가 아니다.
 * N(1≤N≤90)이 주어졌을 때, N자리 이친수의 개수를 구하는 프로그램을 작성하시오.
 * 
 * 입력
 * 첫째 줄에 N이 주어진다.
 * 
 * 출력
 * 첫째 줄에 N자리 이친수의 개수를 출력한다.
 * 
 * 
 * 예제 입력 1
 * 3
 * 
 * 예제 출력 1
 * 2
 * 
 */
/*
 * D[N][L] : 길이가 N, 끌자리가 L인 이친수의 개수
 * D[N][0] = D[N-1][0] + D[N-1][1];
 * D[N][1] = D[N-1][0];
 */
/*
 * 끝자리가 1인 경우에는 앞자리가 무조건 0 = D[n-2]
 * 끝자리가 0인 경우 : 앞자리가 1, 0 둘다 가능 = D[n-1]
 * D[n] = D[n-1] + D[n-2]
 */
public class P2193 {
	static long[][] PINARY_NUM = new long[91][2];
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt();
		scan.close();
		
		P2193.PINARY_NUM[1][0] = 0;
		P2193.PINARY_NUM[1][1] = 1;
		
		P2193 p2193 = new P2193();
		long sum = p2193.calcPinaryNum(N, 0) + p2193.calcPinaryNum(N, 1);
		System.out.println(sum);
		System.out.println(p2193.calcPinaryNum2(N));
	}

	/**
	 * 재귀
	 * @param n
	 * @param lastNum
	 * @return 길이 n, 끝자리 수가 lastNum인 이친수의 개수
	 */
	public long calcPinaryNum(int n, int lastNum) {
		if(n==1 || P2193.PINARY_NUM[n][lastNum] > 0) {
			return P2193.PINARY_NUM[n][lastNum];
		}
		
		if(lastNum == 0) {
			if(P2193.PINARY_NUM[n-1][0] > 0) {
				P2193.PINARY_NUM[n][lastNum] += P2193.PINARY_NUM[n-1][0];
			}else {
				P2193.PINARY_NUM[n][lastNum] += calcPinaryNum(n-1, 0);
			}
		
			if(P2193.PINARY_NUM[n-1][1] > 0) {
				P2193.PINARY_NUM[n][lastNum] += P2193.PINARY_NUM[n-1][1];
			}else {
				P2193.PINARY_NUM[n][lastNum] += calcPinaryNum(n-1, 1);
			}
		}else {
			if(P2193.PINARY_NUM[n-1][0] > 0) {
				P2193.PINARY_NUM[n][lastNum] += P2193.PINARY_NUM[n-1][0];
			}else {
				P2193.PINARY_NUM[n][lastNum] += calcPinaryNum(n-1, 0);
			}
		}
		
		return P2193.PINARY_NUM[n][lastNum];
	}
	
	/**
	 * for 문
	 * @param n
	 * @return 길이 n인 이친수의 개수
	 */
	public long calcPinaryNum2(int n) {
		if(n != 1) {
			for(int length=2; length<=n; length++) {
				P2193.PINARY_NUM[length][0] = P2193.PINARY_NUM[length-1][0] + P2193.PINARY_NUM[length-1][1];
				P2193.PINARY_NUM[length][1] = P2193.PINARY_NUM[length-1][0];
			}
		}
		
		return P2193.PINARY_NUM[n][0] + P2193.PINARY_NUM[n][1];
	}
}



// 정답 코드
/*import java.util.*;
import java.math.*;

public class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[] d = new long[n+1];
        d[1] = 1;
        if (n >= 2) {
            d[2] = 1;
        }
        for (int i=3; i<=n; i++) {
            d[i] = d[i-1] + d[i-2];
        }
        System.out.println(d[n]);
    }
}*/
