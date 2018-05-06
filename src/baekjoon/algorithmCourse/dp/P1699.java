package baekjoon.algorithmCourse.dp;

import java.util.Scanner;

/**
 * 제곱수의 합
 * 
 * 문제
 * 어떤 자연수 N은 그보다 작거나 같은 제곱수들의 합으로 나타낼 수 있다. 예를 들어 11=3^2+1^2+1^2(3개 항)이다. 이런 표현방법은 여러 가지가 될 수 있는데, 11의 경우 11=2^2+2^2+1^2+1^2+1^2(5개 항)도 가능하다.
 * 이 경우, 수학자 숌크라테스는 “11은 3개 항의 제곱수 합으로 표현할 수 있다.”라고 말한다. 또한 11은 그보다 적은 항의 제곱수 합으로 표현할 수 없으므로, 11을 그 합으로써 표현할 수 있는 제곱수 항의 최소 개수는 3이다.
 * 주어진 자연수 N을 이렇게 제곱수들의 합으로 표현할 때에 그 항의 최소개수를 구하는 프로그램을 작성하시오.
 * 
 * 
 * 입력
 * 첫째 줄에 자연수 N이 주어진다. (1 ≤ N ≤ 100,000)
 * 
 * 출력
 * 주어진 자연수를 제곱수의 합으로 나타낼 때에 그 제곱수 항의 최소 개수를 출력한다.
 * 
 * 
 * 예제 입력 1
 * 7
 * 
 * 예제 출력 1
 * 4
 */
/*
 * D[n] = min(D[n-m^2] + 1) (1 <= m^2 <= n)
 */
public class P1699 {
	static int[] POWER_SUM; 
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		P1699.POWER_SUM = new int[n+1];
		scan.close();
		
		P1699 p1699 = new P1699();
		
		// for문
//		System.out.println(p1699.calcPowerSum(n));
		
		// 재귀
		System.out.println(p1699.calcPowerSum2(n));
	}

	public int calcPowerSum(int n) {
		P1699.POWER_SUM[1] = 1;
		
		for(int num=2; num<=n; num++) {
			int rootNumMax = (int)Math.sqrt(num);
			int minRootSum = 100000;
			
			for(int rootNum=rootNumMax; rootNum>=1; rootNum--) {
				int powerSum = P1699.POWER_SUM[num - rootNum*rootNum] + 1;
				if(powerSum < minRootSum) {
					minRootSum = powerSum;
				}
			}
			P1699.POWER_SUM[num] = minRootSum;
		}
		
		return P1699.POWER_SUM[n];
	}
	
	public int calcPowerSum2(int n) {
		if(n == 1) {
			return 1;
		}
		if(P1699.POWER_SUM[n] > 0) {
			return P1699.POWER_SUM[n];
		}
		
		int rootNumMax = (int)Math.sqrt(n);
		int minRootSum = 100000;
		
		for(int rootNum=rootNumMax; rootNum>=1; rootNum--) {
			int powerSum = 0;
			if(n-rootNum*rootNum == 0) {
				powerSum = 1;
			}else if(P1699.POWER_SUM[n - rootNum*rootNum] > 0) {
				powerSum = P1699.POWER_SUM[n - rootNum*rootNum] + 1;
			}else {
				powerSum = calcPowerSum2(n - rootNum*rootNum) + 1;
			}
			
			if(powerSum < minRootSum) {
				minRootSum = powerSum;
			}
		}
		P1699.POWER_SUM[n] = minRootSum;
		
		return minRootSum;
	}
}



// 정답 코드
/*import java.util.*;

public class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] d = new int[n+1];
        for (int i=1; i<=n; i++) {
            d[i] = i;
            for (int j=1; j*j <= i; j++) {
                if (d[i] > d[i-j*j] + 1) {
                    d[i] = d[i-j*j] + 1;
                }
            }
        }
        System.out.println(d[n]);
    }
}*/