package baekjoon.algorithmCourse.dp;

import java.util.Scanner;

/**
 * 붕어빵 판매하기
 * 
 * 문제
 * 강남역에서 붕어빵 장사를 하고 있는 해빈이는 지금 붕어빵이 N개 남았다.
 * 해빈이는 적절히 붕어빵 세트 메뉴를 구성해서 붕어빵을 팔아서 얻을 수 있는 수익을 최대로 만드려고 한다. 붕어빵 세트 메뉴는 붕어빵을 묶어서 파는 것을 의미하고, 세트 메뉴의 가격은 이미 정해져 있다.
 * 붕어빵 i개로 이루어진 세트 메뉴의 가격은 Pi 원이다.
 * 붕어빵이 4개 남아 있고, 1개 팔 때의 가격이 1, 2개는 5, 3개는 6, 4개는 7인 경우에 해빈이가 얻을 수 있는 최대 수익은 10원이다. 2개, 2개로 붕어빵을 팔면 되기 때문이다.
 * 1개 팔 때의 가격이 5, 2개는 2, 3개는 8, 4개는 10 인 경우에는 20이 된다. 1개, 1개, 1개, 1개로 붕어빵을 팔면 되기 때문이다.
 * 마지막으로, 1개 팔 때의 가격이 3, 2개는 5, 3개는 15, 4개는 16인 경우에는 정답은 18이다. 붕어빵을 3개, 1개로 팔면 되기 때문이다.
 * 세트 메뉴의 가격이 주어졌을 때, 해빈이가 얻을 수 있는 최대 수익을 구하는 프로그램을 작성하시오.
 * 
 * 입력
 * 첫째 줄에 해빈이가 가지고 있는 붕어빵의 개수 N이 주어진다. (1 ≤ N ≤ 1,000)
 * 둘째 줄에는 Pi가 P1부터 PN까지 순서대로 주어진다. (1 ≤ Pi ≤ 10,000)
 * 
 * 출력
 * 해빈이가 얻을 수 있는 최대 수익을 출력한다.
 * 
 * 
 * 예제 입력 1
 * 4
 * 1 5 6 7
 * 
 * 예제 출력 1
 * 10
 * 
 * 
 * 예제 입력 2
 * 5
 * 10 9 8 7 6
 * 
 * 예제 출력 2
 * 50
 * 
 * 
 * 예제 입력 3
 * 10
 * 1 1 2 3 5 8 13 21 34 55
 * 
 * 예제 출력 3
 * 55
 * 
 * 
 * 예제 입력 4
 * 10
 * 5 10 11 12 13 30 35 40 45 47
 * 
 * 예제 출력 4
 * 50
 * 
 * 
 * 예제 입력 5
 * 4
 * 5 2 8 10
 * 
 * 예제 출력 5
 * 20
 * 
 * 
 * 예제 입력 6
 * 4
 * 3 5 15 16
 * 
 * 예제 출력 6
 * 18
 * 
 */
/*
 * D[n] = max(D[n-1]+P1, D[n-2]+P2 ... D[0]+Pn)
 */
public class P11052 {
	static int[] MAX_PROFIT;
	static int[] Pi;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt();
		
		P11052.MAX_PROFIT = new int[N+1];
		P11052.Pi = new int[N+1];
		
		for(int index=1; index<N+1; index++) {
			P11052.Pi[index] = scan.nextInt();
		}
		scan.close();
		
		P11052.MAX_PROFIT[1] = P11052.Pi[1];
		
		P11052 p11052 = new P11052();
//		p11052.calcMaxProfit2(N);
		p11052.calcMaxProfit(N);
		
		System.out.println(P11052.MAX_PROFIT[N]);
	}

	public int calcMaxProfit(int N) {
		if(P11052.MAX_PROFIT[N] > 0) {
			return P11052.MAX_PROFIT[N];
		}
		
		for(int index=0; index<N; index++) {
			int profit = 0;
			if(P11052.MAX_PROFIT[index] > 0) {
				profit = P11052.MAX_PROFIT[index] + P11052.Pi[N-index];
			}else {
				profit = calcMaxProfit(index) + P11052.Pi[N-index];
			}
			
			if(profit > P11052.MAX_PROFIT[N]) {
				P11052.MAX_PROFIT[N] = profit;
			}
		}
		
		return P11052.MAX_PROFIT[N];
	}
	
	public int calcMaxProfit2(int N) {
		for(int num=2; num<=N; num++) {
			for(int index=1; index<=num; index++) {
				int profit = P11052.MAX_PROFIT[num-index] + P11052.Pi[index];
				if(profit > P11052.MAX_PROFIT[num]) {
					P11052.MAX_PROFIT[num] = profit;
				}
			}
		}
		
		return P11052.MAX_PROFIT[N];
	}
	
}
