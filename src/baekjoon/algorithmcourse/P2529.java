package baekjoon.algorithmcourse;

import java.util.Scanner;

/**
 * 부등호
 * 
 * 문제
 * 두 종류의 부등호 기호 ‘<’와 ‘>’가 k개 나열된 순서열  A가 있다. 우리는 이 부등호 기호 앞뒤에 서로 다른 한 자릿수 숫자를 넣어서 모든 부등호 관계를 만족시키려고 한다.
 * 예를 들어, 제시된 부등호 순서열 A가 다음과 같다고 하자. 
 * 
 *   A =>  < < < > < < > < >
 * 
 * 부등호 기호 앞뒤에 넣을 수 있는 숫자는 0부터 9까지의 정수이며 선택된 숫자는 모두 달라야 한다. 아래는 부등호 순서열 A를 만족시키는 한 예이다. 
 * 
 *   3 < 4 < 5 < 6 > 1 < 2 < 8 > 7 < 9 > 0
 * 
 * 이 상황에서 부등호 기호를 제거한 뒤, 숫자를 모두 붙이면 하나의 수를 만들 수 있는데 이 수를 주어진 부등호 관계를 만족시키는 정수라고 한다.
 * 그런데 주어진 부등호 관계를 만족하는 정수는 하나 이상 존재한다. 예를 들어 3456128790 뿐만 아니라 5689023174도 아래와 같이 부등호 관계 A를 만족시킨다. 
 * 
 *    5 < 6 < 8 < 9 > 0 < 2 < 3 > 1 < 7 > 4
 * 
 * 여러분은 제시된 k개의 부등호 순서를 만족하는 (k+1)자리의 정수 중에서 최대값과 최소값을 찾아야 한다.
 * 앞서 설명한 대로 각 부등호의 앞뒤에 들어가는 숫자는 { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 }중에서 선택해야 하며 선택된 숫자는 모두 달라야 한다. 
 * 
 * 입력
 * 첫 줄에 부등호 문자의 개수를 나타내는 정수 k가 주어진다. 그 다음 줄에는 k개의 부등호 기호가 하나의 공백을 두고 한 줄에 모두 제시된다. k의 범위는 2<=k<=9 이다. 
 * 
 * 출력
 * 여러분은 제시된 부등호 관계를 만족하는 k+1 자리의 최대, 최소 정수를 첫째 줄과 둘째 줄에 각각 출력해야 한다.
 * 단 아래 예(1)과 같이 첫 자리가 0인 경우도 정수에 포함되어야 한다. 모든 입력에 답은 항상 존재하며 출력 정수는 하나의 문자열이 되도록 해야 한다. 
 * 
 * 
 * 예제 입력 1
 * 2
 * < > 
 * 
 * 예제 출력 1
 * 897
 * 021
 * 
 */
public class P2529 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		//부등호 개수
		int k = Integer.parseInt(scan.nextLine());
		String inequalitySign = scan.nextLine();
		scan.close();
		
		String[] inequalitySignAry = inequalitySign.split(" ");
	
		
		int[] maxNumAry = new int[10];
		int[] minNumAry = new int[10];
		
		//숫자를 만들때 0~9가 한번씩만 들어가므로 최대값은 무조건 9로부터 시작하고, 최소값은 0으로 시작
		for(int num=0; num<10; num++) {
			maxNumAry[num] = 9-num;
			minNumAry[num] = num;
		}

		P2529 p2529 = new P2529();
		
		do {
			if(p2529.isSignTrue(inequalitySignAry, maxNumAry, k)) {
				p2529.printArray(maxNumAry, k);
				break;
			}
		}while(p2529.prevPermutation(maxNumAry));
		
		System.out.println();
		
		do {
			if(p2529.isSignTrue(inequalitySignAry, minNumAry, k)) {
				p2529.printArray(minNumAry, k);
				break;
			}
		}while(p2529.nextPermutation(minNumAry));
	}
	
	public void printArray(int[] array, int k) {
		for(int index=0; index<k+1; index++) {
			System.out.print(array[index]);
		}
	}
	
	public boolean isSignTrue(String[] inequalitySignAry, int[] numAry, int k) {
		for(int index=0; index<k; index++) {
			String inequalitySign = inequalitySignAry[index];
			switch(inequalitySign) {
				case "<" : {
					if(numAry[index] > numAry[index+1]) {
						return false;
					}
					break;
				}
				case ">" : {
					if(numAry[index] < numAry[index+1]) {
						return false;
					}
					break;
				}
			}
		}
		return true;
	}
	
	public boolean prevPermutation(int[] permutationArray) {
		int sizeOfAry = permutationArray.length;
		
		//부호 방향이 > 인 순간을 찾을 index
		int index = sizeOfAry - 1;
		//맨 뒤에서부터 이전 값과 비교하여 현재 값이 더 작아지는 순간에 while 종료
		while(index>0 && permutationArray[index-1]<=permutationArray[index]) {
			index--;
		}
		//while이 끝까지 돌았다는 것은 제일 첫 순열이라는 의미이므로 이전 순열이 존재하지 않는다.
		if(index == 0) {
			return false;
		}
		
		//permutationArray[index-1] 보다 작으면서 가장 큰 수의 index
		int swapIndex = sizeOfAry - 1;
		while(permutationArray[index-1] <= permutationArray[swapIndex]) {
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
