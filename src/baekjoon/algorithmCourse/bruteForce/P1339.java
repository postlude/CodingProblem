package baekjoon.algorithmCourse.bruteForce;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

/**
 * 단어 수학
 * 
 * 문제
 * 민식이는 수학학원에서 단어 수학 문제를 푸는 숙제를 받았다.
 * 단어 수학 문제는 N개의 단어로 이루어져 있으며, 각 단어는 알파벳 대문자로만 이루어져 있다. 이 때, 각 알파벳 대문자를 0부터 9까지의 숫자 중 하나로 바꿔서 N개의 수를 합하는 문제이다.
 * 같은 알파벳은 같은 숫자로 바꿔야 하며, 두 개 이상의 알파벳이 같은 숫자로 바뀌어지면 안된다.
 * 
 * 예를 들어, GCF + ACDEB를 계산한다고 할 때, A = 9, B = 4, C = 8, D = 6, E = 5, F = 3, G = 7로 결정한다면, 두 수의 합은 99437이 되어서 최대가 될 것이다.
 * 
 * N개의 단어가 주어졌을 때, 그 수의 합을 최대로 만드는 프로그램을 작성하시오.
 * 
 * 입력
 * 첫째 줄에 단어의 개수 N(1 ≤ N ≤ 10)이 주어진다. 둘째 줄부터 N개의 줄에 단어가 한 줄에 하나씩 주어진다. 단어는 알파벳 대문자로만 이루어져있다.
 * 모든 단어에 포함되어 있는 알파벳은 최대 10개이고, 수의 최대 길이는 8이다. 서로 다른 문자는 서로 다른 숫자를 나타낸다.
 * 
 * 출력
 * 첫째 줄에 주어진 단어의 합의 최대값을 출력한다.
 * 
 * 
 * 예제 입력 1
 * 2
 * AAA
 * AAA
 * 
 * 예제 출력 1
 * 1998
 * 
 * 
 * 예제 입력 2
 * 2
 * GCF
 * ACDEB
 * 
 * 예제 출력 2
 * 99437
 * 
 * 
 * 예제 입력 3
 * 10
 * A
 * B
 * C
 * D
 * E
 * F
 * G
 * H
 * I
 * J
 * 
 * 예제 출력 3
 * 45
 * 
 * 
 * 예제 입력 4
 * 2
 * AB
 * BA
 * 
 * 예제 출력 4
 * 187
 *
 */
public class P1339 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		//단어 개수
		int N = Integer.parseInt(scan.nextLine());
		
		String[] wordAry = new String[N];
		
		//입력 단어들의 알파벳을 set에 저장
		HashSet<Character> alphabetSet = new HashSet<>();

		for(int index=0; index<N; index++) {
			wordAry[index] = scan.nextLine();
			
			char[] alphaAry = wordAry[index].toCharArray();
			for(int alphaIndex=0; alphaIndex<alphaAry.length; alphaIndex++) {
				alphabetSet.add(alphaAry[alphaIndex]);
			}
		}
		scan.close();
		//input end

		
		Character[] alphaAry = alphabetSet.toArray(new Character[0]);
		P1339 p1339 = new P1339();
		
		int[] numAry = new int[alphaAry.length];
		for(int index=0; index<numAry.length; index++) {
			numAry[index] = 9 - index;
		}
		Arrays.sort(numAry);
		
		int maxSum = -1;
		do {
			int sum = p1339.calcSum(wordAry, alphaAry, numAry);
			if(sum > maxSum) {
				maxSum = sum;
			}
		}while(p1339.nextPermutation(numAry));
		
		System.out.println(maxSum);
	}

	public int calcSum(String[] wordAry, Character[] alphaAry, int[] numAry) {
		int[] alphaNumAry = new int[256];
		
		for(int index=0; index<alphaAry.length; index++) {
			alphaNumAry[alphaAry[index]] = numAry[index]; 
		}
		
		
		int sum = 0;
		
		for(String word : wordAry) {
			char[] charAry = word.toCharArray();
			int calcNum = 0;
			
			for(char alpha : charAry) {
				calcNum = calcNum*10 + alphaNumAry[alpha];
			}
			
			sum += calcNum;
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
