package baekjoon.algorithmCourse.dp;

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
/*
 * dp로 풀기
 * 점화식
 * D[n] = D[n-1] + D[n-2] + D[n-3]
 */
public class P9095_3 {
	static int[] SUM_123;
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int T = scan.nextInt();
		
		
		P9095_3.SUM_123 = new int[12];
		P9095_3.SUM_123[0] = 0;
		P9095_3.SUM_123[1] = 1;
		P9095_3.SUM_123[2] = 2;
		P9095_3.SUM_123[3] = 4;
		
		P9095_3 p9095_3 = new P9095_3();
		
		for(int count=0; count<T; count++) {
			int n = scan.nextInt();
//			p9095_3.calcSum123(n);
			p9095_3.calcSum123_2(n);
			System.out.println(P9095_3.SUM_123[n]);
		}
		scan.close();
	}

	public int calcSum123(int n) {
		if(P9095_3.SUM_123[n] > 0) {
			return P9095_3.SUM_123[n];
		}
		
		if(P9095_3.SUM_123[n-1] > 0) {
			P9095_3.SUM_123[n] += P9095_3.SUM_123[n-1];
		}else {
			P9095_3.SUM_123[n] += calcSum123(n-1);
		}
		
		if(P9095_3.SUM_123[n-2] > 0) {
			P9095_3.SUM_123[n] += P9095_3.SUM_123[n-2];
		}else {
			P9095_3.SUM_123[n] += calcSum123(n-2);
		}
		
		if(P9095_3.SUM_123[n-3] > 0) {
			P9095_3.SUM_123[n] += P9095_3.SUM_123[n-3];
		}else {
			P9095_3.SUM_123[n] += calcSum123(n-3);
		}
		
		return P9095_3.SUM_123[n];
	}
	
	
	public int calcSum123_2(int n) {
		for(int num=4; num<=n; num++) {
			P9095_3.SUM_123[num] = P9095_3.SUM_123[num-1] + P9095_3.SUM_123[num-2] + P9095_3.SUM_123[num-3];
		}
		return P9095_3.SUM_123[n];
	}
}



// 정답 코드
/*import java.util.*;
import java.math.*;

public class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int[] d = new int[11];
        d[0] = 1;
        for (int i=1; i<=10; i++) {
            for (int j=1; j<=3; j++) {
                if (i-j >= 0) {
                    d[i] += d[i-j];
                }
            }
        }
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            System.out.println(d[n]);
        }
    }
}*/