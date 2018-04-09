package baekjoon.algorithmcourse;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 차이를 최대로
 * 
 * 문제
 * N개의 정수로 이루어진 배열 A가 주어진다. 이 때, 배열에 들어있는 정수의 순서를 적절히 바꿔서 다음 식의 최대값을 구하는 프로그램을 작성하시오.
 * 
 * |A[0] - A[1]| + |A[1] - A[2]| + ... + |A[N-2] - A[N-1]|
 * 
 * 입력
 * 첫째 줄에 N (3 ≤ N ≤ 8)이 주어진다. 둘째 줄에는 배열 A에 들어있는 정수가 주어진다. 배열에 들어있는 정수는 -100보다 크거나 같고, 100보다 작거나 같다.
 * 
 * 출력
 * 첫째 줄에 배열에 들어있는 수의 순서를 적절히 바꿔서 얻을 수 있는 식의 최댓값을 출력한다.
 * 
 * 
 * 예제 입력 1
 * 6
 * 20 1 15 8 4 10
 * 
 * 예제 출력 1
 * 62
 * 
 */
//N이 8보다 작으므로 N*N!=40320 이므로 모든 경우를 다 해봐도 됨
public class P10819 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int N = Integer.parseInt(scan.nextLine());
		
		int[] permutationArray = new int[N];
		for(int index=0; index<N; index++) {
			permutationArray[index] = scan.nextInt();
		}
		scan.close();
		
		//입력으로 주어지는 배열이 맨 처음 순열인지 아닌지 모르므로 정렬 후 연산
		Arrays.sort(permutationArray);
		
		P10819 p10819 = new P10819();
		int maxSum = -1;
		do {
			int sum = p10819.calcSum(permutationArray);
			if(sum > maxSum) {
				maxSum = sum;
			}
		}while(p10819.nextPermutation(permutationArray));
		
		System.out.println(maxSum);
	}
	
	public int calcSum(int[] permutationArray) {
		int sum = 0;
		
		for(int index=0; index<permutationArray.length-1; index++) {
			sum += Math.abs(permutationArray[index] - permutationArray[index+1]);
		}
		
		return sum;
	}
	
	public boolean nextPermutation(int[] permutationArray) {
		int sizeOfAry = permutationArray.length;
		
		//부호 방향이 < 인 순간을 찾을 index
		int index = sizeOfAry - 1;
		//맨 뒤에서부터 이전 값과 비교하여 현재 값이 더 커지는 순간에 while 종료
		while(index>0 && permutationArray[index-1]>=permutationArray[index]) {
			index--;
		}
		//while이 끝까지 돌았다는 것은 제일 마지막 순열이라는 의미이므로 다음 순열이 존재하지 않는다.
		if(index == 0) {
			return false;
		}
		
		//permutationArray[index-1] 보다 크면서 가장 작은 수의 index
		int swapIndex = sizeOfAry - 1;
		while(permutationArray[index-1] >= permutationArray[swapIndex]) {
			swapIndex--;
		}
		
		//index-1의 값과 swapIndex의 값을 swap
		int temp = permutationArray[index-1];
		permutationArray[index-1] = permutationArray[swapIndex];
		permutationArray[swapIndex] = temp;
		
		//permutationArray의 index부터 마지막까지 값의 순서를 거꾸로
		int endIndex = sizeOfAry - 1;
		while(index < endIndex) {
			//swap
			temp = permutationArray[index];
			permutationArray[index] = permutationArray[endIndex];
			permutationArray[endIndex] = temp;
			
			//index 변경
			index++;
			endIndex--;
		}
		return true;
	}
}
