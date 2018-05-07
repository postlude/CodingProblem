package baekjoon.algorithmCourse.dp;

import java.util.Scanner;

/**
 * 합분해
 * 
 * 문제
 * 0부터 N까지의 정수 K개를 더해서 그 합이 N이 되는 경우의 수를 구하는 프로그램을 작성하시오.
 * 덧셈의 순서가 바뀐 경우는 다른 경우로 센다(1+2와 2+1은 서로 다른 경우). 또한 한 개의 수를 여러 번 쓸 수도 있다.
 * 
 * 
 * 입력
 * 첫째 줄에 두 정수 N(1 ≤ N ≤ 200), K(1 ≤ K ≤ 200)가 주어진다.
 * 
 * 출력
 * 첫째 줄에 답을 1,000,000,000으로 나눈 나머지를 출력한다.
 * 
 *  
 * 예제 입력 1
 * 20 2
 * 
 * 예제 출력 1
 * 21
 */
/*
 *  + + ... + l = n (0 <= l <= n)
 * D[k][n] = sum(D[k-1][n-l]) (0 <= l <= n)
 */
public class P2225 {
	static long[][] NUMBER_OF_SUM;
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int k = scan.nextInt();
		scan.close();
		
		P2225.NUMBER_OF_SUM = new long[k+1][n+1];
		P2225 p2225 = new P2225();
		
		// for 문
//		System.out.println(p2225.calcNumOfSum(n, k));
		
		// 재귀
//		P2225.NUMBER_OF_SUM[0][0] = 1;
//		System.out.println(p2225.calcNumOfSum2(n, k));
		
		
//		System.out.println(p2225.calcNumOfSum3(n, k));
		System.out.println(p2225.calcNumOfSum4(n, k));
	}

	public long calcNumOfSum(int n, int k) {
		// 모든 D[1][n] = 1, 모든 D[k][0] = 1
		// 이걸 따로 초기화하기 보다는 D[0][0]만 1로 초기화하고 for문 돌게 하면 알아서 계산 됨
		P2225.NUMBER_OF_SUM[0][0] = 1;
		
		for(int kNum=1; kNum<=k; kNum++) {
			for(int nNum=0; nNum<=n; nNum++) {
				for(int lastNum=0; lastNum<=nNum; lastNum++) {
					P2225.NUMBER_OF_SUM[kNum][nNum] += P2225.NUMBER_OF_SUM[kNum-1][nNum-lastNum]; 
				}
				P2225.NUMBER_OF_SUM[kNum][nNum] %= 1000000000L;
			}
		}
		return P2225.NUMBER_OF_SUM[k][n];
	}
	
	public long calcNumOfSum2(int n, int k) {
		if(k==0 && n>=1) {
			return 0L;
		}else if(P2225.NUMBER_OF_SUM[k][n] > 0) {
			return P2225.NUMBER_OF_SUM[k][n];
		}
		
		if(P2225.NUMBER_OF_SUM[k-1][n] == 0) {
			calcNumOfSum2(n, k-1);
		}
		
		for(int nNum=0; nNum<=n; nNum++) {
			for(int lastNum=0; lastNum<=nNum; lastNum++) {
				P2225.NUMBER_OF_SUM[k][nNum] += P2225.NUMBER_OF_SUM[k-1][nNum-lastNum]; 
			}
			P2225.NUMBER_OF_SUM[k][nNum] %= 1000000000L;
		}
		
		return P2225.NUMBER_OF_SUM[k][n];
	}
	
	
	/*
	 * D[k][n]을 그림으로 나타내면 (k,n) 값은 k-1열에 있는 값들을 n=0부터 n까지 모두 더한 값(D[k][n] = sum(D[k-1][n-l])) 
	 * (k,n-1) 값 : k-1열에 있는 값들을 n=0부터 n-1까지 모두 더한 값 
	 * D[k][n] = D[k][n-1] + D[k-1][n]
	 */
	public long calcNumOfSum3(int n, int k) {
		P2225.NUMBER_OF_SUM[1][0] = 1;
		
		for(int kNum=1; kNum<=k; kNum++) {
			for(int nNum=1; nNum<=n; nNum++) {
				if(nNum == 1) {
					P2225.NUMBER_OF_SUM[kNum][nNum] = 1 + P2225.NUMBER_OF_SUM[kNum-1][nNum];
				}else {
					P2225.NUMBER_OF_SUM[kNum][nNum] = P2225.NUMBER_OF_SUM[kNum][nNum-1] + P2225.NUMBER_OF_SUM[kNum-1][nNum];
				}
				P2225.NUMBER_OF_SUM[kNum][nNum] %= 1000000000L;
			}
		}
		return P2225.NUMBER_OF_SUM[k][n];
	}
	
	public long calcNumOfSum4(int n, int k) {
		if(k==0 || P2225.NUMBER_OF_SUM[k][n]>0) {
			return P2225.NUMBER_OF_SUM[k][n];
		}
		
		if(P2225.NUMBER_OF_SUM[k-1][n] == 0) {
			calcNumOfSum4(n, k-1);
		}
		
		for(int nNum=1; nNum<=n; nNum++) {
			if(nNum == 1) {
				P2225.NUMBER_OF_SUM[k][nNum] = 1 + P2225.NUMBER_OF_SUM[k-1][nNum];
			}else {
				P2225.NUMBER_OF_SUM[k][nNum] = P2225.NUMBER_OF_SUM[k][nNum-1] + P2225.NUMBER_OF_SUM[k-1][nNum];
			}
			P2225.NUMBER_OF_SUM[k][nNum] %= 1000000000L;
		}
		
		return P2225.NUMBER_OF_SUM[k][n];
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
        int k = sc.nextInt();
        long[][] d = new long[k+1][n+1];
        d[0][0] = 1;
        for (int i=1; i<=k; i++) {
            for (int j=0; j<=n; j++) {
                for (int l=0; l<=j; l++) {
                    d[i][j] += d[i-1][j-l];
                    d[i][j] %= mod;
                }
            }
        }
        System.out.println(d[k][n]);
    }
}*/


/*import java.util.*;
public class Main {
    public static long mod = 1000000000L;
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        long[][] d = new long[k+1][n+1];
        d[0][0] = 1;
        for (int i=1; i<=k; i++) {
            for (int j=0; j<=n; j++) {
                d[i][j] = d[i-1][j];
                if (j-1 >= 0) {
                    d[i][j] += d[i][j-1];
                }
                d[i][j] %= mod;
            }
        }
        System.out.println(d[k][n]);
    }
}*/