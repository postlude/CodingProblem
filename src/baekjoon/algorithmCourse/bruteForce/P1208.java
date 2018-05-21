package baekjoon.algorithmCourse.bruteForce;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 부분집합의 합 2
 * 
 * 문제
 * N개의 정수로 이루어진 집합이 있을 때, 이 집합의 공집합이 아닌 부분집합 중에서 그 집합의 원소를 다 더한 값이 S가 되는 경우의 수를 구하는 프로그램을 작성하시오.
 * 
 * 
 * 입력
 * 첫째 줄에 정수의 개수를 나타내는 N과 정수 S가 주어진다. (1≤N≤40, |S|≤1,000,000) 둘째 줄에 N개의 정수가 빈 칸을 사이에 두고 주어진다.
 * 주어지는 정수의 절대값은 100,000을 넘지 않는다. 같은 수가 여러번 주어질 수도 있다.
 * 
 * 출력
 * 첫째 줄에 합이 S가 되는 부분집합의 개수를 출력한다.
 * 
 * 
 * 예제 입력 1
 * 5 0
 * -7 -3 -2 5 8
 * 
 * 예제 출력 1
 * 1
 */
public class P1208 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int s = scan.nextInt();
		
		int[] numAry1 = new int[n];
		int[] numAry2 = new int[n];
		int ary1Size = n / 2;
		int ary2Size = n - n/2;
		for(int index=0; index<ary1Size; index++) {
			numAry1[index] = scan.nextInt();
		}	
		for(int index=0; index<ary2Size; index++) {
			numAry2[index] = scan.nextInt();
		}
		scan.close();
		
		
		ArrayList<Integer> list1 = new ArrayList<>();
		list1.add(0);
		for(int index1=0; index1<ary1Size; index1++) {
			for(int index2=index1+1; index2<ary1Size; index2++) {
				
			}
		}
	}

}
