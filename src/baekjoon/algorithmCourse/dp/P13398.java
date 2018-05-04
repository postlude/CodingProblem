package baekjoon.algorithmCourse.dp;

import java.util.Scanner;

/**
 * 연속합 2
 * 
 * 문제
 * n개의 정수로 이루어진 임의의 수열이 주어진다. 우리는 이 중 연속된 몇 개의 숫자를 선택해서 구할 수 있는 합 중 가장 큰 합을 구하려고 한다.
 * 단, 숫자는 한 개 이상 선택해야 한다. 또, 수열에서 수를 하나 제거할 수 있다. (제거하지 않아도 된다)
 * 예를 들어서 10, -4, 3, 1, 5, 6, -35, 12, 21, -1 이라는 수열이 주어졌다고 하자. 여기서 수를 제거하지 않았을 때의 정답은 12+21인 33이 정답이 된다.
 * 만약, -35를 제거한다면, 수열은 10, -4, 3, 1, 5, 6, 12, 21, -1이 되고, 여기서 정답은 10-4+3+1+5+6+12+21인 54가 된다.
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
 * 54
 */
/*
 * k번째를 제거했을때 최대 연속합 = max(왼쪽에서부터 k-1까지 구한 연속합 + 오른쪽에서부터 k+1까지 구한 연속합)
 * 제거 했을 때와 하지 않을 때의 합 중 최대값
 */
public class P13398 {
	static int[] LEFT_MAX_CONTINUOUS_SUM;
	static int[] RIGHT_MAX_CONTINUOUS_SUM;
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		
		P13398.LEFT_MAX_CONTINUOUS_SUM = new int[n+1];
		P13398.RIGHT_MAX_CONTINUOUS_SUM = new int[n+1];
		
		int[] a = new int[n+1];
		for(int index=1; index<=n; index++) {
			a[index] = scan.nextInt();
		}
		scan.close();
		
		P13398 p13398 = new P13398();
		
		// for문 이용 메소드
//		System.out.println(p13398.calcMaxContinuousSum(n, a));
		
		P13398.LEFT_MAX_CONTINUOUS_SUM[1] = a[1];
		// 재귀 메소드에서 n번째부터 채운다
//		P13398.RIGHT_MAX_CONTINUOUS_SUM[n] = a[n];
		// 음수가 포함될 수 있으므로 합이 0이거나 음수도 가능하게 된다. int 최소값으로 초기화
		for(int index=2; index<=n; index++) {
			P13398.LEFT_MAX_CONTINUOUS_SUM[index] = Integer.MIN_VALUE;
			P13398.RIGHT_MAX_CONTINUOUS_SUM[index] = Integer.MIN_VALUE;
		}
		
		p13398.calcMaxContinuousSum2(n, a);
		
		int maxSum = a[1];
		for(int index=2; index<=n; index++) {
			// index번째의 값을 제거했을 때의 연속합
			int sumDelete = P13398.LEFT_MAX_CONTINUOUS_SUM[index-1];
			if(index != n) {
				sumDelete += P13398.RIGHT_MAX_CONTINUOUS_SUM[index+1];
			}
			// 제거하지 않았을 때의 연속합
			int sumNoneDelete = P13398.LEFT_MAX_CONTINUOUS_SUM[index];
			
			if(sumDelete > maxSum) {
				maxSum = sumDelete;
			}
			if(sumNoneDelete > maxSum) {
				maxSum = sumNoneDelete;
			}
		}
		System.out.println(maxSum);
	}

	/**
	 * 
	 * @param n
	 * @param a
	 * @return 최대 연속합
	 */
	public int calcMaxContinuousSum(int n, int[] a) {
		P13398.LEFT_MAX_CONTINUOUS_SUM[1] = a[1];
		for(int index=2; index<=n; index++) {
			int sum = P13398.LEFT_MAX_CONTINUOUS_SUM[index-1] + a[index];
			if(sum > a[index]) {
				P13398.LEFT_MAX_CONTINUOUS_SUM[index] = sum;
			}else {
				P13398.LEFT_MAX_CONTINUOUS_SUM[index] = a[index];
			}
		}
		
		P13398.RIGHT_MAX_CONTINUOUS_SUM[n] = a[n];
		for(int index=n-1; index>=1; index--) {
			int sum = P13398.RIGHT_MAX_CONTINUOUS_SUM[index+1] + a[index];
			if(sum > a[index]) {
				P13398.RIGHT_MAX_CONTINUOUS_SUM[index] = sum;
			}else {
				P13398.RIGHT_MAX_CONTINUOUS_SUM[index] = a[index];
			}
		}
		
		
		int maxSum = a[1];
		for(int index=2; index<=n; index++) {
			// index번째의 값을 제거했을 때의 연속합
			int sumDelete = P13398.LEFT_MAX_CONTINUOUS_SUM[index-1];
			if(index != n) {
				sumDelete += P13398.RIGHT_MAX_CONTINUOUS_SUM[index+1];
			}
			// 제거하지 않았을 때의 연속합
			int sumNoneDelete = P13398.LEFT_MAX_CONTINUOUS_SUM[index];
			
			if(sumDelete > maxSum) {
				maxSum = sumDelete;
			}
			if(sumNoneDelete > maxSum) {
				maxSum = sumNoneDelete;
			}
		}
		
		return maxSum;
	}
	
	/**
	 * 재귀 이용
	 * @param n
	 * @param a
	 */
	public void calcMaxContinuousSum2(int n, int[] a) {
		if(n == 1) {
			return;
		}
		
		// 오른쪽에서부터의 연속합
		// n, n-1, n-2.. 1 로 진행되는 재귀 함수이므로  오른쪽에서 왼쪽으로 증가하는 부분 수열은 재귀 호출되는 부분보다 위에서 계산 
		int sum = a[n];
		if(n != P13398.RIGHT_MAX_CONTINUOUS_SUM.length-1) {
			sum += P13398.RIGHT_MAX_CONTINUOUS_SUM[n+1];
		}
		if(sum > a[n]) {
			P13398.RIGHT_MAX_CONTINUOUS_SUM[n] = sum;
		}else {
			P13398.RIGHT_MAX_CONTINUOUS_SUM[n] = a[n];
		}
		
		
		if(P13398.LEFT_MAX_CONTINUOUS_SUM[n-1] == Integer.MIN_VALUE) {
			calcMaxContinuousSum2(n-1, a);
		}
		
		// 왼쪽에서부터 연속합
		sum = P13398.LEFT_MAX_CONTINUOUS_SUM[n-1] + a[n];
		if(sum > a[n]) {
			P13398.LEFT_MAX_CONTINUOUS_SUM[n] = sum;
		}else {
			P13398.LEFT_MAX_CONTINUOUS_SUM[n] = a[n];
		}
	}
}
