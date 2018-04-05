package baekjoon.algorithmcourse;

import java.util.Scanner;

/**
 * 다음 순열
 * 
 * 문제
 * 1부터 N까지의 수로 이루어진 순열이 있다. 이 때, 사전순으로 다음에 오는 순열을 구하는 프로그램을 작성하시오.
 * 사전 순으로 가장 앞서는 순열은 오름차순으로 이루어진 순열이고, 가장 마지막에 오는 순열은 내림차순으로 이루어진 순열이다.
 * N = 3인 경우에 사전순으로 순열을 나열하면 다음과 같다.
 * 
 * 1, 2, 3
 * 1, 3, 2
 * 2, 1, 3
 * 2, 3, 1
 * 3, 1, 2
 * 3, 2, 1
 * 
 * 입력
 * 첫째 줄에 N(1 ≤ N ≤ 10,000)이 주어진다. 둘째 줄에 순열이 주어진다.
 * 
 * 출력
 * 첫째 줄에 입력으로 주어진 순열의 다음에 오는 순열을 출력한다. 만약, 사전순으로 마지막에 오는 순열인 경우에는 -1을 출력한다.
 * 
 * 
 * 예제 입력 1
 * 4
 * 1 2 3 4
 * 
 * 예제 출력 1
 * 1 2 4 3
 * 
 * 
 * 예제 입력 2
 * 5
 * 5 4 3 2 1
 * 
 * 예제 출력 2
 * -1
 * 
 */
//다음 수열의 시간 복잡도 : O(N)
public class P10972 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int N = Integer.parseInt(scan.nextLine());
		
		int[] permutationAry = new int[N];
		for(int index=0; index<N; index++) {
			permutationAry[index] = scan.nextInt();
		}
		scan.close();
		
		P10972 p10972 = new P10972();
		if(p10972.nextPermutation(permutationAry)) {
			p10972.printArray(permutationAry);
		}else {
			System.out.println(-1);
		}
	}
	
	public void printArray(int[] array) {
		for(int num : array) {
			System.out.print(num + " ");
		}
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
