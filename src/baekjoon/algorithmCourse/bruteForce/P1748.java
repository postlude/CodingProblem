package baekjoon.algorithmCourse.bruteForce;

import java.util.Scanner;

/**
 * 수 이어 쓰기 1
 * 문제
 * 1부터 N까지의 수를 이어서 쓰면 다음과 같이 새로운 하나의 수를 얻을 수 있다.
 * 1234567891011121314151617181920212223...
 * 이렇게 만들어진 새로운 수는 몇 자리 수일까? 이 수의 자릿수를 구하는 프로그램을 작성하시오.
 * 
 * 입력
 * 첫째 줄에 N(1≤N≤100,000,000)이 주어진다.
 * 
 * 출력
 * 첫째 줄에 새로운 수의 자릿수를 출력한다.
 * 
 * 
 * 예제 입력 1
 * 120
 * 
 * 예제 출력 1
 * 252
 * 
 */
public class P1748 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int N = scan.nextInt();
		scan.close();
		
		
		//입력 수의 자리수
		int inputDigit = 0;
		int n = N;
		do{
			inputDigit++;
			n /= 10;
		}while(n > 0);
		
		/*
		 * 한 자리수부터 입력 수의 자리수보다 1 작은 자리수
		 * 9 * 10의 (k-1)제곱 * k [1 <= k <= N-1] 까지 누적
		 * 
		 * 입력 수의 자리수
		 * (N - 10의 (입력수의 자리수-1)제곱  + 1) * 입력수의 자리수
		 */
		
		//입력 수의 자리수보다 한자리 더 작은 수까지의 자리수 총합
		int prevDigitCount = 0;
		//10의 제곱승 계산을 위한 변수
		int prevDigitpower = 1;
		//입력 수와 같은 자리수를 계산하기 위한 10의 제곱승 변수 
		int presentDigitpower = 1;
		for(int i=1; i<=inputDigit-1; i++) {
			presentDigitpower *= 10;
			if(i > 1) {
				prevDigitpower *= 10;
			}
			
			prevDigitCount += (9 * prevDigitpower * i);
		}
		int presentDigitCount = (N - presentDigitpower + 1) * inputDigit;
		
		int resultDigit = prevDigitCount + presentDigitCount;
		System.out.println(resultDigit);
	}
}




//정답 코드
/*import java.util.*;
public class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long ans = 0;
        for (int start=1, len=1; start<=n; start*=10, len++) {
            int end = start*10-1;
            if (end > n) {
                end = n;
            }
            ans += (long)(end - start + 1) * len;
        }
        System.out.println(ans);
    }
}*/