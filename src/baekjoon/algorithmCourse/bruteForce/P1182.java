package baekjoon.algorithmCourse.bruteForce;

import java.util.Scanner;

/**
 * 부분집합의 합
 * 
 * 문제
 * N개의 정수로 이루어진 집합이 있을 때, 이 집합의 공집합이 아닌 부분집합 중에서 그 집합의 원소를 다 더한 값이 S가 되는 경우의 수를 구하는 프로그램을 작성하시오.
 * 
 * 입력
 * 첫째 줄에 정수의 개수를 나타내는 N과 정수 S가 주어진다. (1≤N≤20, |S|≤1,000,000) 둘째 줄에 N개의 정수가 빈 칸을 사이에 두고 주어진다.
 * 주어지는 정수의 절대값은 100,000을 넘지 않는다. 같은 수가 여러번 주어질 수도 있다.
 * 
 * 출력
 * 첫째 줄에 합이 S가 되는 부분집합의 개수를 출력한다.
 * 
 * 
 * 예제 입력 1
 * 5 0
 * -7 -3 -2 5 8
 * 
 * 예제 출력 1
 * 1
 * 
 */
public class P1182 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int N = scan.nextInt();
		int S = scan.nextInt();
		
		int[] num = new int[N];
		for(int index=0; index<N; index++) {
			num[index] = scan.nextInt();
		}
		scan.close();
		// input end
		
		
		P1182 p1182 = new P1182();
		int subsetCount = p1182.countSubSetSum(S, 0, num, 0);
		
		// 합이 0인 경우에는 어떤 정수도 포함하지 않는 경우도 count된다. 공집합을 제외하라고 했으므로 -1
		if(S == 0) {
			subsetCount--;
		}
		System.out.println(subsetCount);
	}

	public int countSubSetSum(int targetSum, int sum, int[] num, int numIndex) {
		// 모든 정수에 대해 합에 넣을지 말지 결정한 후에 sum이 targetSum과 같은지 확인
		if(numIndex == num.length) {
			if(targetSum == sum) {
				return 1;
			}else {
				return 0;
			}
		}
		
		int subsetCount = countSubSetSum(targetSum, sum+num[numIndex], num, numIndex+1) + countSubSetSum(targetSum, sum, num, numIndex+1);
		return subsetCount;
	}
}
