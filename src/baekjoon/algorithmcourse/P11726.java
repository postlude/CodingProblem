package baekjoon.algorithmcourse;

import java.util.Scanner;

/**
 * 2×n 타일링
 * 
 * 문제
 * 2×n 크기의 직사각형을 1×2, 2×1 타일로 채우는 방법의 수를 구하는 프로그램을 작성하시오.
 * 아래 그림은 2×5 크기의 직사각형을 채운 한 가지 방법의 예이다.
 * 
 * 
 * 입력
 * 첫째 줄에 n이 주어진다. (1 ≤ n ≤ 1,000)
 * 
 * 출력
 * 첫째 줄에 2×n 크기의 직사각형을 채우는 방법의 수를 10,007로 나눈 나머지를 출력한다.
 * 
 * 
 * 예제 입력 1
 * 2
 * 
 * 예제 출력 1
 * 2
 * 
 * 
 * 예제 입력 2
 * 9
 * 
 * 예제 출력 2
 * 55
 * 
 */
/*
 * D[n] = 2×n 크기의 직사각형을 채우는 방법의 수
 * D[n] = D[n-1] + D[n-2]
 */
public class P11726 {
	static int[] RECTANGLE_WAY;
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		scan.close();
		
		P11726.RECTANGLE_WAY = new int[n+1];
		
		P11726 p11726 = new P11726();
//		System.out.println(p11726.calcRectangleWay(n));
		System.out.println(p11726.calcRectangleWay2(n));
	}
	
	/**
	 * 재귀 : 시간초과
	 * @param n
	 * @return
	 */
	/*public int calcRectangleWay(int n) {
		if(n == 1) {
			return 1;
		}else if(n == 2) {
			return 2;
		}
		
		if(P11726.RECTANGLE_WAY[n] > 0) {
			return P11726.RECTANGLE_WAY[n];
		}
		
		return (calcRectangleWay(n-1) + calcRectangleWay(n-2))%10007;
	}*/
	
	/**
	 * for문
	 * @param n
	 * @return 2×n 크기의 직사각형을 채우는 방법의 수
	 */
	public int calcRectangleWay2(int n) {
		P11726.RECTANGLE_WAY[0] = 1;
		P11726.RECTANGLE_WAY[1] = 1;
//		P11726.RECTANGLE_WAY[2] = 2;
		
		for(int num=2; num<=n; num++) {
			P11726.RECTANGLE_WAY[num] = P11726.RECTANGLE_WAY[num-1] + P11726.RECTANGLE_WAY[num-2];
			P11726.RECTANGLE_WAY[num] %= 10007;
		}
		
		return P11726.RECTANGLE_WAY[n];
	}
}
