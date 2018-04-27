package baekjoon.algorithmCourse.bruteForce;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 1, 2, 3 더하기
 * 
 * 문제
 * 정수 4를 1, 2, 3의 조합으로 나타내는 방법은 총 7가지가 있다.
 * 
 * 1+1+1+1
 * 1+1+2
 * 1+2+1
 * 2+1+1
 * 2+2
 * 1+3
 * 3+1
 * 
 * 정수 n이 주어졌을 때, n을 1,2,3의 합으로 나타내는 방법의 수를 구하는 프로그램을 작성하시오.
 * 
 * 
 * 입력
 * 첫째 줄에 테스트 케이스의 개수 T가 주어진다. 각 테스트 케이스는 한 줄로 이루어져 있고, 정수 n이 주어진다. n은 양수이며 11보다 작다.
 * 
 * 출력
 * 각 테스트 케이스마다, n을 1,2,3의 합으로 나타내는 방법의 수를 출력한다.
 * 
 * 
 * 예제 입력 1
 * 3
 * 4
 * 7
 * 10
 * 
 * 예제 출력 1
 * 7
 * 44
 * 274
 * 
 */

//재귀 함수로 풀기
public class P9095_2 {
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int T = Integer.parseInt(scan.nextLine());
		
		ArrayList<Integer> testCaseList = new ArrayList<>();
		for(int count=0; count<T; count++) {
			int testCase = Integer.parseInt(scan.nextLine());
			testCaseList.add(testCase);
		}
		scan.close();
		//input end
		
		
		P9095_2 p9095_2 = new P9095_2();
		for(int index=0; index<T; index++) {
			System.out.println(p9095_2.calcCount(0, testCaseList.get(index)));
		}
	}

	/**
	 * 
	 * @param sum(계산중인 합계)
	 * @param target(만들어야할 입력 값)
	 * @return 가능한 방법의 가지수
	 */
	public int calcCount(int sum, int target) {
		// sum이 target을 넘어가면 불가능한 경우이므로 count를 누적하지 않는다.
		if(sum > target) {
			return 0;
		}
		
		// sum이 target과 같으면 가능한 경우라는 뜻이므로 count를 1 증가 시킨다.
		if(sum == target) {
			return 1;
		}
		
		int count = 0;
		
		for(int num=1; num<=3; num++) {
			count += calcCount(sum + num, target);
		}
		
		return count;
	}
}
