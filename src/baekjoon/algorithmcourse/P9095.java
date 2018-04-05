package baekjoon.algorithmcourse;

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

//최대 3^10=59049 < 1억 이므로 전부 계산
public class P9095 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int T = Integer.parseInt(scan.nextLine());
		
		ArrayList<Integer> testCaseList = new ArrayList<>();
		for(int count=0; count<T; count++) {
			int testCase = Integer.parseInt(scan.nextLine());
			testCaseList.add(testCase);
		}
		scan.close();
		
		P9095 p9095 = new P9095();
		for(int testCase : testCaseList) {
			System.out.println(p9095.calc(testCase));
		}
		
	}

	public int calc(int testCase) {
		int caseNum = 0;
		for(int num1=1; num1<=3; num1++) {
			if(num1 == testCase) {
				caseNum++;
			}
			for(int num2=1; num2<=3; num2++) {
				if(num1+num2 == testCase) {
					caseNum++;
				}
				for(int num3=1; num3<=3; num3++) {
					if(num1+num2+num3 == testCase) {
						caseNum++;
					}
					for(int num4=1; num4<=3; num4++) {
						if(num1+num2+num3+num4 == testCase) {
							caseNum++;
						}
						for(int num5=1; num5<=3; num5++) {
							if(num1+num2+num3+num4+num5 == testCase) {
								caseNum++;
							}
							for(int num6=1; num6<=3; num6++) {
								if(num1+num2+num3+num4+num5+num6 == testCase) {
									caseNum++;
								}
								for(int num7=1; num7<=3; num7++) {
									if(num1+num2+num3+num4+num5+num6+num7 == testCase) {
										caseNum++;
									}
									for(int num8=1; num8<=3; num8++) {
										if(num1+num2+num3+num4+num5+num6+num7+num8 == testCase) {
											caseNum++;
										}
										for(int num9=1; num9<=3; num9++) {
											if(num1+num2+num3+num4+num5+num6+num7+num8+num9 == testCase) {
												caseNum++;
											}
											for(int num10=1; num10<=3; num10++) {
												if(num1+num2+num3+num4+num5+num6+num7+num8+num9+num10 == testCase) {
													caseNum++;
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return caseNum;
	}
}
