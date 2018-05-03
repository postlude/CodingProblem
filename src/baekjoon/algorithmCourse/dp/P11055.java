package baekjoon.algorithmCourse.dp;

import java.util.Scanner;

/**
 * 가장 큰 증가 부분 수열
 * 
 * 문제
 * 수열 A가 주어졌을 때, 그 수열의 증가 부분 수열 중에서 합이 가장 큰 것을 구하는 프로그램을 작성하시오.
 * 예를 들어, 수열 A = {1, 100, 2, 50, 60, 3, 5, 6, 7, 8} 인 경우에 합이 가장 큰 증가 부분 수열은 A = {1, 2, 50, 60} 이고, 합은 113이다.
 * 
 * 입력
 * 첫째 줄에 수열 A의 크기 N (1 ≤ N ≤ 1,000)이 주어진다.
 * 둘째 줄에는 수열 A를 이루고 있는 Ai가 주어진다. (1 ≤ Ai ≤ 1,000)
 * 
 * 출력
 * 첫째 줄에 수열 A의 합이 가장 큰 증가 부분 수열의 합을 출력한다.
 * 
 * 
 * 예제 입력 1
 * 10
 * 1 100 2 50 60 3 5 6 7 8
 * 
 * 예제 출력 1
 * 113
 */
/*
 * D[i] : A[i]가 마지막인 수열의 합 중 최대값
 * D[i] = max(D[j]) + A[i] (j<i, A[j]<A[i]) 
 */
public class P11055 {
	static int[] SUB_SEQ_SUM;
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		
		P11055.SUB_SEQ_SUM = new int[n+1];
		
		int[] a = new int[n+1];
		for(int index=1; index<=n; index++) {
			a[index] = scan.nextInt();
		}
		scan.close();
		
		P11055 p11055 = new P11055();
		// for문 이용 메소드
//		System.out.println(p11055.calcLargestSubSeqSum(n, a));
		
		// 재귀 메소드
		P11055.SUB_SEQ_SUM[1] = a[1];
		p11055.calcLargestSubSeqSum2(n, a);
		
		int maxSum = P11055.SUB_SEQ_SUM[1];
		for(int subSeqSum : P11055.SUB_SEQ_SUM) {
			if(subSeqSum > maxSum) {
				maxSum = subSeqSum;
			}
		}
		System.out.println(maxSum);
	}

	/**
	 * for문
	 * @param n
	 * @param a
	 * @return 합이 가장 큰 증가 수열의 합
	 */
	public int calcLargestSubSeqSum(int n, int[] a) {
		P11055.SUB_SEQ_SUM[1] = a[1];
		
		for(int index=2; index<=n; index++) {
			P11055.SUB_SEQ_SUM[index] = a[index];
			for(int subSeqIndex=1; subSeqIndex<index; subSeqIndex++) {
				if(a[index]>a[subSeqIndex] && P11055.SUB_SEQ_SUM[index]<P11055.SUB_SEQ_SUM[subSeqIndex]+a[index]) {
					P11055.SUB_SEQ_SUM[index] = P11055.SUB_SEQ_SUM[subSeqIndex] + a[index];
				}
			}
		}
		
		int maxSum = P11055.SUB_SEQ_SUM[1];
		for(int subSeqSum : P11055.SUB_SEQ_SUM) {
			if(subSeqSum > maxSum) {
				maxSum = subSeqSum;
			}
		}
		
		return maxSum;
	}
	
	/**
	 * 재귀
	 * @param n
	 * @param a
	 */
	public void calcLargestSubSeqSum2(int n, int[] a) {
		if(n == 1) {
			return;
		}
		if(P11055.SUB_SEQ_SUM[n-1] == 0) {
			calcLargestSubSeqSum2(n-1, a);
		}
		
		P11055.SUB_SEQ_SUM[n] = a[n];
		for(int subSeqIndex=1; subSeqIndex<n; subSeqIndex++) {
			if(a[n]>a[subSeqIndex] && P11055.SUB_SEQ_SUM[n]<P11055.SUB_SEQ_SUM[subSeqIndex]+a[n]) {
				P11055.SUB_SEQ_SUM[n] = P11055.SUB_SEQ_SUM[subSeqIndex] + a[n];
			}
		}
	}
}
