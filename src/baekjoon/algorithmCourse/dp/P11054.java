package baekjoon.algorithmCourse.dp;

import java.util.Scanner;

/**
 * 가장 긴 바이토닉 부분 수열
 * 
 * 문제
 * 수열 S가 어떤 수 Sk를 기준으로 S1 < S2 < ... Sk-1 < Sk > Sk+1 > ... SN-1 > SN을 만족한다면, 그 수열을 바이토닉 수열이라고 한다.
 * 예를 들어, {10, 20, 30, 25, 20}과 {10, 20, 30, 40}, {50, 40, 25, 10} 은 바이토닉 수열이지만, {1, 2, 3, 2, 1, 2, 3, 2, 1}과 {10, 20, 30, 40, 20, 30} 은 바이토닉 수열이 아니다.
 * 수열 A가 주어졌을 때, 그 수열의 부분 수열 중 바이토닉 수열이면서 가장 긴 수열의 길이를 구하는 프로그램을 작성하시오.
 * 
 * 입력
 * 첫째 줄에 수열 A의 크기 N이 주어지고, 둘째 줄에는 수열 A를 이루고 있는 Ai가 주어진다. (1 ≤ N ≤ 1,000, 1 ≤ Ai ≤ 1,000)
 * 
 * 출력
 * 첫째 줄에 수열 A의 부분 수열 중에서 가장 긴 바이토닉 수열의 길이를 출력한다.
 * 
 * 
 * 예제 입력 1
 * 10
 * 1 5 2 1 4 3 4 5 2 1
 * 
 * 예제 출력 1
 * 7
 */
/*
 * k를 기준으로 왼쪽은 왼->오 로 가는 증가하는 부분 수열, 오른쪽은 오->왼 으로 가는 증가하는 부분 수열
 * D1[n] : 왼쪽에서 시작해서 오른쪽으로 증가하는 가장 긴 부분 수열
 * D2[n] : 오른쪽에서 시작해서 왼쪽으로 증가하는 가장 긴 부분 수열
 * D[n] = D1[n] + D2[n] - 1
 */
public class P11054 {
	static int[] LEFT_SUB_SEQ_LENGTH;
	static int[] RIGHT_SUB_SEQ_LENGTH;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		
		P11054.LEFT_SUB_SEQ_LENGTH = new int[n+1];
		P11054.RIGHT_SUB_SEQ_LENGTH = new int[n+1];
		
		int[] a = new int[n+1];
		for(int index=1; index<=n; index++) {
			a[index] = scan.nextInt();
		}
		scan.close();
		// input end
		
		P11054 p11054 = new P11054();
		
		// for문 사용 메소드
//		System.out.println(p11054.calcLongestBitonicSubSeq(n, a));
		
		// 재귀 메소드
		P11054.LEFT_SUB_SEQ_LENGTH[1] = 1;
		P11054.RIGHT_SUB_SEQ_LENGTH[n] = 1;
		
		p11054.calcLongestBitonicSubSeq2(n, a);
		
		int maxLength = 1;
		for(int index=1; index<=n; index++) {
			int bitonicSubSeqLength = P11054.LEFT_SUB_SEQ_LENGTH[index] + P11054.RIGHT_SUB_SEQ_LENGTH[index] - 1;
			if(bitonicSubSeqLength > maxLength) {
				maxLength = bitonicSubSeqLength;
			}
		}
		System.out.println(maxLength);
	}
	
	/**
	 * for문 이용
	 * @param n
	 * @param a
	 * @return 가장 긴 바이토닉 부분 수열 길이
	 */
	public int calcLongestBitonicSubSeq(int n, int[] a) {
		// 왼쪽에서 시작해서 오른쪽으로 증가하는 부분 수열 계산
		P11054.LEFT_SUB_SEQ_LENGTH[1] = 1;
		for(int index=2; index<=n; index++) {
			P11054.LEFT_SUB_SEQ_LENGTH[index] = 1;
			
			for(int subSeqIndex=1; subSeqIndex<index; subSeqIndex++) {
				if(a[index]>a[subSeqIndex] && P11054.LEFT_SUB_SEQ_LENGTH[index]<P11054.LEFT_SUB_SEQ_LENGTH[subSeqIndex]+1) {
					P11054.LEFT_SUB_SEQ_LENGTH[index] = P11054.LEFT_SUB_SEQ_LENGTH[subSeqIndex] + 1;
				}
			}
		}
		
		
		// 오른쪽에서 시작해서 왼쪽으로 증가하는 부분 수열 계산
		P11054.RIGHT_SUB_SEQ_LENGTH[n] = 1;
		for(int index=n-1; index>=1; index--) {
			P11054.RIGHT_SUB_SEQ_LENGTH[index] = 1;
			
			for(int subSeqIndex=n; subSeqIndex>index; subSeqIndex--) {
				if(a[index]>a[subSeqIndex] && P11054.RIGHT_SUB_SEQ_LENGTH[index]<P11054.RIGHT_SUB_SEQ_LENGTH[subSeqIndex]+1) {
					P11054.RIGHT_SUB_SEQ_LENGTH[index] = P11054.RIGHT_SUB_SEQ_LENGTH[subSeqIndex] + 1;
				}
			}
		}
		
		// 최대 바이토닉 부분 수열 계산
		int maxLength = 1;
		for(int index=1; index<=n; index++) {
			int bitonicSubSeqLength = P11054.LEFT_SUB_SEQ_LENGTH[index] + P11054.RIGHT_SUB_SEQ_LENGTH[index] - 1;
			if(bitonicSubSeqLength > maxLength) {
				maxLength = bitonicSubSeqLength;
			}
		}
		
		return maxLength;
	}
	
	/**
	 * 재귀 이용
	 * @param n : input으로 주어지는 n
	 * @param a
	 */
	public void calcLongestBitonicSubSeq2(int n, int[] a) {
		if(n == 1) {
			return;
		}
		
		// n, n-1, n-2.. 1 로 진행되는 재귀 함수이므로
		// 오른쪽에서 왼쪽으로 증가하는 부분 수열은 재귀 호출되는 부분보다 위에서 계산 
		P11054.RIGHT_SUB_SEQ_LENGTH[n] = 1;
		for(int subSeqIndex=P11054.RIGHT_SUB_SEQ_LENGTH.length-1; subSeqIndex>n; subSeqIndex--) {
			if(a[n]>a[subSeqIndex] && P11054.RIGHT_SUB_SEQ_LENGTH[n]<P11054.RIGHT_SUB_SEQ_LENGTH[subSeqIndex]+1) {
				P11054.RIGHT_SUB_SEQ_LENGTH[n] = P11054.RIGHT_SUB_SEQ_LENGTH[subSeqIndex] + 1;
			}
		}
		
		if(P11054.LEFT_SUB_SEQ_LENGTH[n-1] == 0) {
			calcLongestBitonicSubSeq2(n-1, a);
		}
		
		P11054.LEFT_SUB_SEQ_LENGTH[n] = 1;
		for(int subSeqIndex=1; subSeqIndex<n; subSeqIndex++) {
			if(a[n]>a[subSeqIndex] && P11054.LEFT_SUB_SEQ_LENGTH[n]<P11054.LEFT_SUB_SEQ_LENGTH[subSeqIndex]+1) {
				P11054.LEFT_SUB_SEQ_LENGTH[n] = P11054.LEFT_SUB_SEQ_LENGTH[subSeqIndex] + 1;
			}
		}
		
	}
}
