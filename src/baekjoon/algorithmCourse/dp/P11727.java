package baekjoon.algorithmCourse.dp;

import java.util.Scanner;

/**
 * 2×n 타일링 2
 * 
 * 문제
 * 2×n 직사각형을 2×1과 2×2 타일로 채우는 방법의 수를 구하는 프로그램을 작성하시오.
 * 아래 그림은 2×17 직사각형을 채운 한가지 예이다.
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
 * 3
 * 
 * 
 * 예제 입력 2
 * 8
 * 
 * 예제 출력 2
 * 171
 * 
 * 
 * 예제 입력 3
 * 12
 * 
 * 예제 출력 3
 * 2731
 * 
 */
/*
 * 점화식
 * D[n] = D[n-1] + D[n-2]*2 
 */
public class P11727 {
	static int[] RECTANGLE_WAY;
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		scan.close();
		
		P11727.RECTANGLE_WAY = new int [n+1];
		P11727.RECTANGLE_WAY[0] = 1;
		P11727.RECTANGLE_WAY[1] = 1;
		
		P11727 p11727 = new P11727();
//		p11727.calcRectangleWay(n);
		p11727.calcRectangleWay2(n);
		System.out.println(P11727.RECTANGLE_WAY[n]);
	}

	
	public int calcRectangleWay(int n) {
		if(P11727.RECTANGLE_WAY[n] != 0) {
			return P11727.RECTANGLE_WAY[n];
		}
		
		
		if(P11727.RECTANGLE_WAY[n-1] != 0) {
			P11727.RECTANGLE_WAY[n] += P11727.RECTANGLE_WAY[n-1];
		}else {
			P11727.RECTANGLE_WAY[n] += calcRectangleWay(n-1);
		}
		
		if(P11727.RECTANGLE_WAY[n-2] != 0) {
			P11727.RECTANGLE_WAY[n] += P11727.RECTANGLE_WAY[n-2]*2;
		}else {
			P11727.RECTANGLE_WAY[n] += calcRectangleWay(n-2);
		}
		
		P11727.RECTANGLE_WAY[n] %= 10007;
		
		return P11727.RECTANGLE_WAY[n];
	}
	
	public int calcRectangleWay2(int n) {
		for(int num=2; num<=n; num++) {
			P11727.RECTANGLE_WAY[num] = P11727.RECTANGLE_WAY[num-1] + P11727.RECTANGLE_WAY[num-2]*2;
			P11727.RECTANGLE_WAY[num] %= 10007;
		}
		return P11727.RECTANGLE_WAY[n];
	}
}
