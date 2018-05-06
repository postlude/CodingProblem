package baekjoon.algorithmCourse.dp;

import java.util.Scanner;

/**
 * 계단 오르기
 * 
 * 문제
 * 계단 오르기 게임은 계단 아래 시작점부터 계단 꼭대기에 위치한 도착점까지 가는 게임이다. <그림 1>과 같이 각각의 계단에는 일정한 점수가 쓰여 있는데 계단을 밟으면 그 계단에 쓰여 있는 점수를 얻게 된다.
 * 
 * 예를 들어 <그림 2>와 같이 시작점에서부터 첫 번째, 두 번째, 네 번째, 여섯 번째, 계단을 밟아 도착점에 도달하면 총 점수는 10 + 20 + 25 + 20 = 75점이 된다.
 * 
 * 계단 오르는 데는 다음과 같은 규칙이 있다.
 * 
 * 계단은 한 번에 한 계단씩 또는 두 계단씩 오를 수 있다. 즉, 한 계단을 밟으면서 이어서 다음 계단이나, 다음 다음 계단으로 오를 수 있다.
 * 연속된 세 개의 계단을 모두 밟아서는 안된다. 단, 시작점은 계단에 포함되지 않는다.
 * 마지막 도착 계단은 반드시 밟아야 한다.
 * 따라서 첫 번째 계단을 밟고 이어 두 번째 계단이나, 세 번째 계단으로 오를 수 있다. 하지만, 첫 번째 계단을 밟고 이어 네 번째 계단으로 올라가거나, 첫 번째, 두 번째, 세번째 계단을 연속해서 모두 밟을 수는 없다.
 * 
 * 각 계단에 쓰여 있는 점수가 주어질 때 이 게임에서 얻을 수 있는 총 점수의 최대값을 구하는 프로그램을 작성하시오.
 * 
 * 
 * 입력
 * 입력의 첫째 줄에 계단의 개수가 주어진다.
 * 둘째 줄부터 한 줄에 하나씩 제일 아래에 놓인 계단부터 순서대로 각 계단에 쓰여 있는 점수가 주어진다. 계단의 개수는 300이하의 자연수이고, 계단에 쓰여 있는 점수는 10,000이하의 자연수이다.
 * 
 * 출력
 * 첫째 줄에 계단 오르기 게임에서 얻을 수 있는 총 점수의 최대값을 출력한다.
 * 
 * 
 * 예제 입력 1
 * 6
 * 10
 * 20
 * 15
 * 25
 * 10
 * 20
 * 
 * 예제 출력 1
 * 75
 */
/*
 * D[n][i] : n번째가 i번 연속으로 계단을 밟았을 때 합 최대값
 * D[n][0] : 마지막 계단을 밟아야 하므로 불가능
 * D[n][1] : 한 개 연속이므로 마지막 계단을 밟고, n-1번째를 밟지 않을 것. n-2번째는 1번 연속이거나 2번연속이거나 상관없음(ooxo / xoxo)
 * D[n][1] = max(D[n-2][0], D[n-2][1]) + point[n]
 * D[n][2] : 2개 연속이므로 n, n-1번째를 밟고, n-2번째를 밟지 않을 것
 * D[n][2] = D[n-1][1] + point[n]
 * 
 * 0번 연속은 불가능하므로 i=0(1번 연속), i=1(2번 연속)으로 표현
 */
public class P2579 {
	static int[][] MAX_POINT_SUM;
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int stairNum = scan.nextInt();
		
		P2579.MAX_POINT_SUM = new int[stairNum+1][2];
		
		int[] point = new int[stairNum+1];
		for(int index=1; index<=stairNum; index++) {
			point[index] = scan.nextInt();
		}
		scan.close();
		// input end
		
		P2579 p2579 = new P2579();
		
		// for문 이용 메소드
//		System.out.println(p2579.calcMaxPoint(stairNum, point));
		
		// 재귀
		P2579.MAX_POINT_SUM[1][0] = point[1];
		P2579.MAX_POINT_SUM[1][1] = 0;
		if(stairNum > 1) {
			P2579.MAX_POINT_SUM[2][0] = point[2];
			P2579.MAX_POINT_SUM[2][1] = point[1] + point[2];
		}
		
		int pointSum0 = p2579.calcMaxPoint2(stairNum, point, 0);
		int pointSum1 = p2579.calcMaxPoint2(stairNum, point, 1);
		System.out.println((pointSum0>pointSum1)?pointSum0:pointSum1);
	}

	public int calcMaxPoint(int stairNum, int[] point) {
		P2579.MAX_POINT_SUM[1][0] = point[1];
		P2579.MAX_POINT_SUM[1][1] = 0;
		if(stairNum > 1) {
			P2579.MAX_POINT_SUM[2][0] = point[2];
			P2579.MAX_POINT_SUM[2][1] = point[1] + point[2];
		}
		
		for(int index=3; index<=stairNum; index++) {
			P2579.MAX_POINT_SUM[index][0] = Math.max(P2579.MAX_POINT_SUM[index-2][0], P2579.MAX_POINT_SUM[index-2][1]) + point[index];
			P2579.MAX_POINT_SUM[index][1] = P2579.MAX_POINT_SUM[index-1][0] + point[index];
		}
		return Math.max(P2579.MAX_POINT_SUM[stairNum][0], P2579.MAX_POINT_SUM[stairNum][1]);
	}
	
	public int calcMaxPoint2(int stairNum, int[] point, int lastStairState) {
		if(P2579.MAX_POINT_SUM[stairNum-1][0] > 0) {
			P2579.MAX_POINT_SUM[stairNum][1] = P2579.MAX_POINT_SUM[stairNum-1][0] + point[stairNum];
		}else {
			P2579.MAX_POINT_SUM[stairNum][1] = calcMaxPoint2(stairNum-1, point, 0) + point[stairNum];
		}
		
		if(P2579.MAX_POINT_SUM[stairNum-2][0] > 0) {
			P2579.MAX_POINT_SUM[stairNum][0] = Math.max(P2579.MAX_POINT_SUM[stairNum-2][0], P2579.MAX_POINT_SUM[stairNum-2][1]) + point[stairNum];
		}else {
			int point0 = calcMaxPoint2(stairNum-2, point, 0);
			int point1 = calcMaxPoint2(stairNum-2, point, 1);
			P2579.MAX_POINT_SUM[stairNum][0] = Math.max(point0, point1) + point[stairNum];
		}
		
		return P2579.MAX_POINT_SUM[stairNum][lastStairState];
	}
}




// 정답 코드
/*import java.util.*;

public class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n+1];
        for (int i=1; i<=n; i++) {
            a[i] = sc.nextInt();
        }
        int[][] d = new int[n+1][3];
        d[1][1] = a[1];
        for (int i=2; i<=n; i++) {
            d[i][2] = d[i-1][1] + a[i];
            d[i][1] = Math.max(d[i-2][1], d[i-2][2]) + a[i];
        }
        System.out.println(Math.max(d[n][1], d[n][2]));
    }
}*/