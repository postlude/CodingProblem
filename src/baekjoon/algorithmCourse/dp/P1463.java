package baekjoon.algorithmCourse.dp;

import java.util.Scanner;

/**
 * 1로 만들기
 * 
 * 문제
 * 정수 X에 사용할 수 있는 연산은 다음과 같이 세 가지 이다.
 * 
 * X가 3으로 나누어 떨어지면, 3으로 나눈다.
 * X가 2로 나누어 떨어지면, 2로 나눈다.
 * 1을 뺀다.
 * 
 * 정수 N이 주어졌을 때, 위와 같은 연산 세 개를 적절히 사용해서 1을 만들려고 한다. 연산을 사용하는 횟수의 최소값을 출력하시오.
 * 
 * 입력
 * 첫째 줄에 1보다 크거나 같고, 10^6보다 작거나 같은 자연수 N이 주어진다.
 * 
 * 출력
 * 첫째 줄에 연산을 하는 횟수의 최소값을 출력한다.
 * 
 * 
 * 예제 입력 1
 * 2
 * 
 * 예제 출력 1
 * 1
 * 
 * 
 * 예제 입력 2
 * 10
 * 
 * 예제 출력 2
 * 3
 * 
 */
/*
 * D[N] = N을 1로 만드는 최소 연산 횟수
 * 1. 1을 빼는 경우 : D[N-1] + 1
 * 2. 3을 나누는 경우 : D[N/3] + 1
 * 3. 2를 나누는 경우 : D[N/2] + 1
 * 점화식 : D[N] = min(1, 2, 3) 
 */
public class P1463 {
	static int[] MIN_CALC;
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt();
		scan.close();
		
		P1463.MIN_CALC = new int[N+1];
		
		P1463 p1463 = new P1463();
		System.out.println(p1463.calcOperationCount(N));
		System.out.println(p1463.calcOperationCount2(N));
	}

	/**
	 * 재귀로 푸는 방법(top-down)
	 * @param N
	 * @return N을 1로 만드는 최소 연산 횟수
	 */
	public int calcOperationCount(int N) {
		// N이 1이 되었으면 더이상 연산할 필요 없으므로(연산 횟수가 증가하지 않음) 0 리턴
		if(N == 1) {
			return 0;
		}
		
		// 이미 연산한 값이면(배열에 있는 값이면) 그 값을 그대로 리턴
		if(P1463.MIN_CALC[N] > 0) {
			return P1463.MIN_CALC[N];
		}
		
		int n1 = 1000000;
		int n2 = 1000000;
		int n3 = 1000000;
		
		// 3으로 나누어 떨어지는 경우
		if(N%3 == 0) {
			// N/3 을 만드는 최소 연산 수 + 1회가 N을 만드는 최소 연산 수
			n3 = calcOperationCount(N/3) + 1;
		}
		
		// 2로 나누어 떨어지는 경우
		if(N%2 == 0) {
			n2 = calcOperationCount(N/2) + 1;
		}
		
		// 1을 빼는 경우
		n1 = calcOperationCount(N-1) + 1;
		
		// 최소 값을 배열에 저장
		P1463.MIN_CALC[N] = Math.min(n3, Math.min(n1, n2));
		
		return P1463.MIN_CALC[N];
	}
	
	/**
	 * for문으로 푸는 방법(bottom-up)
	 * @param N
	 * @return N을 1로 만드는 최소 연산 횟수
	 */
	public int calcOperationCount2(int N) {
		for(int num=2; num<=N; num++) {
			int n1 = P1463.MIN_CALC[num-1] + 1;
			
			int n2 = 1000000;
			if(num%2 == 0) {
				n2 = P1463.MIN_CALC[num/2] + 1;
			}
			
			int n3 = 1000000;
			if(num%3 == 0) {
				n3 = P1463.MIN_CALC[num/3] + 1;
			}
			
			P1463.MIN_CALC[num] = Math.min(n3, Math.min(n1, n2));
		}
		
		return P1463.MIN_CALC[N];
	}
}





// 재귀 정답 코드
/*import java.util.*;

public class Main {
    public static int[] d;
    public static int go(int n) {
        if (n == 1) {
            return 0;
        }
        if (d[n] > 0) {
            return d[n];
        }
        d[n] = go(n-1) + 1;
        if (n%2 == 0) {
            int temp = go(n/2)+1;
            if (d[n] > temp) {
                d[n] = temp;
            }
        }
        if (n%3 == 0) {
            int temp = go(n/3)+1;
            if (d[n] > temp) {
                d[n] = temp;
            }
        }
        return d[n];
    }
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        d = new int[n+1];
        System.out.println(go(n));
    }
}*/


// for문 정답 코드
/*import java.util.*;

public class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] d = new int[n+1];
        d[1] = 0;
        for (int i=2; i<=n; i++) {
            d[i] = d[i-1] + 1;
            if (i%2 == 0 && d[i] > d[i/2] + 1) {
                d[i] = d[i/2] + 1;
            }
            if (i%3 == 0 && d[i] > d[i/3] + 1) {
                d[i] = d[i/3] + 1;
            }
        }
        System.out.println(d[n]);
    }
}*/
