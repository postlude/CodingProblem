package baekjoon.algorithmCourse.dp;

import java.util.Scanner;

/**
 * 가장 긴 감소하는 부분 수열
 * 
 * 문제
 * 수열 A가 주어졌을 때, 가장 긴 감소하는 부분 수열을 구하는 프로그램을 작성하시오.
 * 예를 들어, 수열 A = {10, 30, 10, 20, 20, 10} 인 경우에 가장 긴 감소하는 부분 수열은 A = {30, 20, 10}  이고, 길이는 3이다.
 * 
 * 
 * 입력
 * 첫째 줄에 수열 A의 크기 N (1 ≤ N ≤ 1,000)이 주어진다.
 * 둘째 줄에는 수열 A를 이루고 있는 Ai가 주어진다. (1 ≤ Ai ≤ 1,000)
 * 
 * 출력
 * 첫째 줄에 수열 A의 가장 긴 감소하는 부분 수열의 길이를 출력한다.
 * 
 * 
 * 예제 입력 1
 * 6
 * 10 30 10 20 20 10
 * 
 * 예제 출력 1
 * 3
 */
/*
 * D[i] : A[i]가 마지막인 가장 긴 감소 수열의 길이
 * D[i] = max(D[j]) + 1 (j<i, A[j]>A[i]) 
 */
public class P11722 {
	static int[] SUB_SEQ_LENGTH;
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		
		P11722.SUB_SEQ_LENGTH = new int[n+1];
		
		int[] a = new int[n+1];
		for(int index=1; index<=n; index++) {
			a[index] = scan.nextInt();
		}
		scan.close();
		
		P11722 p11722 = new P11722();
		
		// for문 이용 메소드
		System.out.println(p11722.calcLongestSubSeq(n, a));
		
		// 재귀 메소드
		P11722.SUB_SEQ_LENGTH[1] = 1;
		p11722.calcLongestSubSeq2(n, a);
		
		int maxLength = 1;
		for(int subSeqLength : P11722.SUB_SEQ_LENGTH) {
			if(subSeqLength > maxLength) {
				maxLength = subSeqLength;
			}
		}
		System.out.println(maxLength);
	}

	public int calcLongestSubSeq(int n, int[] a) {
		P11722.SUB_SEQ_LENGTH[1] = 1;
		
		for(int index=2; index<=n; index++) {
			P11722.SUB_SEQ_LENGTH[index] = 1;
			for(int subSeqIndex=1; subSeqIndex<index; subSeqIndex++) {
				if(a[index]<a[subSeqIndex] && P11722.SUB_SEQ_LENGTH[index]<P11722.SUB_SEQ_LENGTH[subSeqIndex]+1) {
					P11722.SUB_SEQ_LENGTH[index] = P11722.SUB_SEQ_LENGTH[subSeqIndex] + 1;
				}
			}
		}
		
		int maxLength = 1;
		for(int subSeqLength : P11722.SUB_SEQ_LENGTH) {
			if(subSeqLength > maxLength) {
				maxLength = subSeqLength;
			}
		}
		
		return maxLength;
	}
	
	public void calcLongestSubSeq2(int n, int[] a) {
		if(n == 1) {
			return;
		}
		if(P11722.SUB_SEQ_LENGTH[n-1] == 0) {
			calcLongestSubSeq2(n-1, a);
		}
		
		P11722.SUB_SEQ_LENGTH[n] = 1;
		for(int subSeqIndex=1; subSeqIndex<n; subSeqIndex++) {
			if(a[n]<a[subSeqIndex] && P11722.SUB_SEQ_LENGTH[n]<P11722.SUB_SEQ_LENGTH[subSeqIndex]+1) {
				P11722.SUB_SEQ_LENGTH[n] = P11722.SUB_SEQ_LENGTH[subSeqIndex] + 1;
			}
		}
	}
}
