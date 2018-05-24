package baekjoon.algorithmCourse.etc;

import java.util.Scanner;

/**
 * 동전 1
 * 
 * 문제
 * n가지 종류의 동전이 있다. 각각의 동전이 나타내는 가치는 다르다. 이 동전들을 적당히 사용해서, 그 가치의 합이 k원이 되도록 하고 싶다. 그 경우의 수를 구하시오. (각각의 동전은 몇 개라도 사용할 수 있다.)
 * 
 * 
 * 입력
 * 첫째줄에 n, k가 주어진다. (1 ≤ n ≤ 100, 1 ≤ k ≤ 10,000) 다음 n개의 줄에는 각각의 동전의 가치가 주어진다. 동전의 가치는 100,000보다 작거나 같은 자연수이다.
 * 
 * 출력
 * 첫째 줄에 경우의 수를 출력한다. 경우의 수는 2^31보다 작다.
 * 
 * 
 * 예제 입력 1
 * 3 10
 * 1
 * 2
 * 5
 * 
 * 예제 출력 1
 * 10
 * 
 */
public class P2293 {
	static int[] PRICE_MAKE_WAY = new int[10001];
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int k = scan.nextInt();
		
		int[] coin = new int[k+1];
		for(int index=0; index<n; index++) {
			coin[index] = scan.nextInt();
		}
		scan.close();
		// input end
		
		P2293 p2293 = new P2293();
		System.out.println(p2293.calcWay(n, k, coin));
	}

	public int calcWay(int n, int k, int[] coin) {
		P2293.PRICE_MAKE_WAY[0] = 1;
		
		// 예제 입력에서 5+2+2+1 과 5+2+1+2 는 같은 경우로 취급해야 한다.
		// for 문 위치를 서로 바꾸면 중복된 경우를 허용하게 됨
		for(int coinIndex=0; coinIndex<n; coinIndex++) {
			for(int price=0; price<=k; price++) {
				if(price-coin[coinIndex] >= 0) {
					P2293.PRICE_MAKE_WAY[price] += P2293.PRICE_MAKE_WAY[price-coin[coinIndex]];
				}
			}
		}
		
		return P2293.PRICE_MAKE_WAY[k];
	}
}
