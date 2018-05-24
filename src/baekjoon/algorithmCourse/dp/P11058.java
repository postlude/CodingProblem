package baekjoon.algorithmCourse.dp;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 크리보드
 * 
 * 문제
 * 크리보드는 kriii가 만든 신기한 키보드이다. 크리보드에는 버튼이 4개만 있으며, 하는 역할은 다음과 같다.
 * 
 * 화면에 A를 출력한다.
 * Ctrl-A: 화면을 전체 선택한다
 * Ctrl-C: 전체 선택한 내용을 버퍼에 복사한다
 * Ctrl-V: 버퍼가 비어있지 않은 경우에는 화면에 출력된 문자열의 바로 뒤에 버퍼의 내용을 붙여넣는다.
 * 
 * 크리보드의 버튼을 총 N번 눌러서 화면에 출력된 A개수를 최대로하는 프로그램을 작성하시오.
 * 
 * 
 * 입력
 * 첫째 줄에 N(1 ≤ N ≤ 100)이 주어진다.
 * 
 * 출력
 * 크리보드의 버튼을 총 N번 눌러서 화면에 출력할 수 있는 A 개수의 최대값을 출력한다.
 * 
 * 
 * 예제 입력 1
 * 3
 * 
 * 예제 출력 1
 * 3
 * 
 * 
 * 예제 입력 2
 * 7
 * 
 * 예제 출력 2
 * 9
 * 
 * 
 * 예제 입력 3
 * 11
 * 
 * 예제 출력 3
 * 27
 */
/*
 * D[n] : n번 눌렀을 때 화면에 찍힌 A의 최대 값
 * 마지막에 누른 버튼이
 * 화면에 A를 출력하는 버튼이거나
 * ca - cc - cv 를 누른 경우에만 의미가 있음
 * 
 * 화면에 A를 출력하는 버튼을 누른 경우 : D[n-1] + 1
 * ca-cc-cv 누른 경우 : D[n-3] * 2 (현재 화면에 있는 내용 전체를 복붙해서 추가하므로)
 * ca-cc-cv-cv : D[n-4] * 3
 * ca-cc-cv-cv-cv : D[n-5] * 4
 * 
 * cv를 j번 눌렀을 때 : D[n-(j+2)] * (j+1)
 * 
 * D[n] = max(D[n-1]+1, D[n-(j+2)] * (j+1)) (1 <= j <= n-3)
 */
public class P11058 {
	static int[] MAX_A = new int[101];
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		scan.close();
		
		P11058 p11058 = new P11058();
		System.out.println(p11058.calcMaxA(n));
	}

	public int calcMaxA(int n) {
		P11058.MAX_A[0] = 0;
		P11058.MAX_A[1] = 1;
		P11058.MAX_A[2] = 2;
		P11058.MAX_A[3] = 3;
		
		for(int index=4; index<=n; index++) {
			ArrayList<Integer> numOfA = new ArrayList<>();
			numOfA.add(P11058.MAX_A[index-1] + 1);
			
			for(int cvPress=1; cvPress<=index-3; cvPress++) {
				int num = P11058.MAX_A[index-(cvPress+2)] * (cvPress+1);
				numOfA.add(num);
			}
			
			int maxNum = -1;
			for(int numA : numOfA) {
				if(numA > maxNum) {
					maxNum = numA;
				}
			}
			
			P11058.MAX_A[index] = maxNum;
		}
		
		return P11058.MAX_A[n];
	}
	
}
