package baekjoon.algorithmCourse.dp;

import java.util.Scanner;

/**
 * 연속합
 * 
 * 문제
 * n개의 정수로 이루어진 임의의 수열이 주어진다. 우리는 이 중 연속된 몇 개의 숫자를 선택해서 구할 수 있는 합 중 가장 큰 합을 구하려고 한다. 단, 숫자는 한 개 이상 선택해야 한다.
 * 예를 들어서 10, -4, 3, 1, 5, 6, -35, 12, 21, -1 이라는 수열이 주어졌다고 하자. 여기서 정답은 12+21인 33이 정답이 된다.
 * 
 * 입력
 * 첫째 줄에 정수 n(1≤n≤100,000)이 주어지고 둘째 줄에는 n개의 정수로 이루어진 수열이 주어진다. 수는 -1,000보다 크거나 같고, 1,000보다 작거나 같은 정수이다.
 * 
 * 출력
 * 첫째 줄에 답을 출력한다.
 * 
 * 
 * 예제 입력 1
 * 10
 * 10 -4 3 1 5 6 -35 12 21 -1
 * 
 * 예제 출력 1
 * 33
 */
/*
 * D[n] : n개의 정수로 만들수 있는 연속합의 최대 값
 * 1. n번째 수를 추가해 합이 a[n]보다 커지는 경우
 * 2. n번째 수를 추가해 합이 a[n]보다 작아지는 경우(a[n]부터 새로운 연속합 계산)
 * D[n] = max(D[n-1]+a[n], a[n])
 */
public class P1912 {
	static int[] MAX_CONTINUOUS_SUM;
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		
		P1912.MAX_CONTINUOUS_SUM = new int[n+1];
		
		int[] a = new int[n+1];
		for(int index=1; index<=n; index++) {
			a[index] = scan.nextInt();
		}
		scan.close();
		
		P1912 p1912 = new P1912();
		
		// for문 이용 메소드
//		System.out.println(p1912.calcMaxContinuousSum(n, a));
		
		// 재귀
		P1912.MAX_CONTINUOUS_SUM[1] = a[1];
		// 음수가 포함될 수 있으므로 합이 0이거나 음수도 가능하게 된다. int 최소값으로 초기화
		for(int index=2; index<=n; index++) {
			P1912.MAX_CONTINUOUS_SUM[index] = Integer.MIN_VALUE;
		}
		
		p1912.calcMaxContinuousSum2(n, a);
		int maxSum = Integer.MIN_VALUE;
		for(int index=1; index<=n; index++) {
			int sum = P1912.MAX_CONTINUOUS_SUM[index];
			if(sum > maxSum) {
				maxSum = sum;
			}
		}
		System.out.println(maxSum);
	}
	
	/**
	 * for문
	 * @param n
	 * @param a
	 * @return 최대 연속합
	 */
	public int calcMaxContinuousSum(int n, int[] a) {
		P1912.MAX_CONTINUOUS_SUM[1] = a[1];
		
		for(int index=2; index<=n; index++) {
			int sum = P1912.MAX_CONTINUOUS_SUM[index-1] + a[index];
			
			if(sum > a[index]) {
				P1912.MAX_CONTINUOUS_SUM[index] = sum;
			}else {
				P1912.MAX_CONTINUOUS_SUM[index] = a[index];
			}
		}
		
		int maxSum = Integer.MIN_VALUE;
		for(int index=1; index<=n; index++) {
			int sum = P1912.MAX_CONTINUOUS_SUM[index];
			if(sum > maxSum) {
				maxSum = sum;
			}
		}
		
		return maxSum;
	}
	
	/**
	 * 재귀
	 * @param n
	 * @param a
	 */
	public void calcMaxContinuousSum2(int n, int[] a) {
		if(n == 1) {
			return;
		}
		
		if(P1912.MAX_CONTINUOUS_SUM[n-1] == Integer.MIN_VALUE) {
			calcMaxContinuousSum2(n-1, a);
		}
		
		int sum = P1912.MAX_CONTINUOUS_SUM[n-1] + a[n];
		if(sum > a[n]) {
			P1912.MAX_CONTINUOUS_SUM[n] = sum;
		}else {
			P1912.MAX_CONTINUOUS_SUM[n] = a[n];
		}
	}
}



// 정답 코드
/*import java.util.*;

public class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i=0; i<n; i++) {
            a[i] = sc.nextInt();
        }
        int[] d = new int[n];
        for (int i=0; i<n; i++) {
            d[i] = a[i];
            if (i == 0) {
                continue;
            }
            if (d[i] < d[i-1] + a[i]) {
                d[i] = d[i-1] + a[i];
            }
        }
        int ans = d[0];
        for (int i=0; i<n; i++) {
            if (ans < d[i]) {
                ans = d[i];
            }
        }
        System.out.println(ans);
    }
}*/