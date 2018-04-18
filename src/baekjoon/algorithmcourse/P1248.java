package baekjoon.algorithmcourse;

import java.util.Scanner;

/**
 * 맞춰봐
 * 
 * 문제
 * -10~10까지의 N개의 정수(중복 가능)로 이루어진 수열 A(N<=10)
 * S[i][j] = A[i] + A[i+1] + ... A[j] 가 0보다 크면 +, 같으면 0, 작으면 -
 * S가 주어졌을 때 가능한 A를 아무거나 찾는 문제
 * 
 * A = {-2, 5, -3, 1}
 * 
 * S
 * i\j 1  2  3  4
 *  1  -  +  0  +
 *  2     +  +  +
 *  3        -  -
 *  4           +
 *  
 * 
 * 입력
 * 첫째 줄에 수열의 크기 N이 주어진다. N은 10보다 작거나 같은 자연수이다. 둘째 줄에는 N(N+1)/2 길이의 문자열이 주어진다
 * 처음 N개의 문자는 부호 배열의 첫 번째 줄에 해당하고, 다음 N-1개의 문자는 두 번째 줄에 해당한다. 마찬가지로 마지막 문자는 N번째 줄에 해당하는 문자다.
 * 
 * 출력
 * 첫째 줄에 수열의 원소 N개를 빈 칸을 사이에 두고 출력한다. 답이 여러 가지 일 경우에는 아무거나 출력하면 된다.
 * 
 * 
 * 예제 입력 1
 * 4
 * -+0++++--+
 * 
 * 예제 출력 1
 * -2 5 -3 1
 * 
 */

/*
 * 주석 처리한 코드로 풀면 시간 초과
 * 
 * index번째 숫자를 결정하면 0~index까지의 숫자는 변하지 않으므로 합을 미리 판단할 수 있음
 */
public class P1248 {
	static int[] A;
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int N = Integer.parseInt(scan.nextLine());
		P1248.A = new int[N];
		char[] input = scan.nextLine().toCharArray();
		scan.close();
		
		int[][] S = new int[N][N];
		int chIndex = 0;
		for(int index1=0; index1<N; index1++) {
			for(int index2=index1; index2<N; index2++) {
				char ch = input[chIndex];
				switch(ch) {
					case '+' : {
						S[index1][index2] = 1;
						break;
					}
					case '-' : {
						S[index1][index2] = -1;
						break;
					}
					case '0' : {
						S[index1][index2] = 0;
						break;
					}
				}
				chIndex++;
			}
		}
		
		
		P1248 p1248 = new P1248();
		p1248.findA(S, 0, N);
		p1248.printArray(A);
	}
	
	public void printArray(int[] array) {
		for(int num : array) {
			System.out.print(num + " ");
		}
	}
	
	public boolean isCollect(int[][] S, int index) {
		/*int N = S[0].length;
		for(int i=0; i<N; i++) {
			int sum = 0;
			for(int j=i; j<N; j++) {
				sum += P1248.A[j];
				switch(S[i][j]) {
					case -1 : {
						if(sum >= 0) {
							return false;
						}
						break;
					}
					case 0 : {
						if(sum != 0) {
							return false;
						}
						break;
					}
					case 1 : {
						if(sum <= 0) {
							return false;
						}
						break;
					}
				}
			}
		}
		return true;*/
		
		
		int sum = 0;
		for(int i=index; i>=0; i--) {
			sum += P1248.A[i];
			
			switch(S[i][index]) {
				case -1 : {
					if(sum >= 0) {
						return false;
					}
					break;
				}
				case 0 : {
					if(sum != 0) {
						return false;
					}
					break;
				}
				case 1 : {
					if(sum <= 0) {
						return false;
					}
					break;
				}
			}
		}
		return true;
	}
	
	
	public boolean findA(int[][] S, int index, int N) {
		if(index == N) {
//			return isCollect(S);
			return true;
		}
		if(S[index][index] == 0) {
			P1248.A[index] = 0;
//			return findA(S, index+1, N);
			return isCollect(S, index) && findA(S, index+1, N);
		}
		
		for(int num=1; num<=10; num++) {
			P1248.A[index] = S[index][index]*num;
//			if(findA(S, index+1, N)) {
			if(isCollect(S, index) && findA(S, index+1, N)) {
				return true;
			}
		}
		return false;
	}
}
