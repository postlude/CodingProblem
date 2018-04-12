package baekjoon.algorithmcourse;

import java.util.Scanner;

/**
 * 연산자 끼워넣기
 * 
 * 문제
 * N개의 수로 이루어진 수열 A1, A2, ..., AN이 주어진다. 또, 수와 수 사이에 끼워넣을 수 있는 N-1개의 연산자가 주어진다. 연산자는 덧셈(+), 뺄셈(-), 곱셈(×), 나눗셈(÷)로만 이루어져 있다.
 * 우리는 수와 수 사이에 연산자를 하나씩 넣어서, 수식을 하나 만들 수 있다. 이 때, 주어진 수의 순서를 바꾸면 안된다.
 * 예를 들어, 6개의 수로 이루어진 수열이 1, 2, 3, 4, 5, 6이고, 주어진 연산자가 덧셈(+) 2개, 뺄셈(-) 1개, 곱셈(×) 1개, 나눗셈(÷) 1개인 경우에는 총 60가지의 식을 만들 수 있다.
 * 예를 들어, 아래와 같은 식을 만들 수 있다.
 * 
 * 1+2+3-4×5÷6
 * 1÷2+3+4-5×6
 * 1+2÷3×4-5+6
 * 1÷2×3-4+5+6
 * 
 * 식의 계산은 연산자 우선순위를 무시하고 앞에서부터 진행해야 한다. 또, 나눗셈은 정수 나눗셈으로 몫만 취한다.
 * 음수를 양수로 나눌 때는 C의 기준을 따른다. 즉 양수로 바꾼 뒤 몫을 취하고, 그 몫을 음수로 바꾼 것과 같다. 이에 따라서, 위의 식 4개의 결과를 계산해보면 아래와 같다.
 * 
 * 1+2+3-4×5÷6 = 1
 * 1÷2+3+4-5×6 = 12
 * 1+2÷3×4-5+6 = 5
 * 1÷2×3-4+5+6 = 7
 * 
 * N개의 수와 N-1개의 연산자가 주어졌을 때, 만들 수 있는 식의 결과가 최대인 것과 최소인 것을 구하는 프로그램을 작성하시오.
 * 
 * 입력
 * 첫째 줄에 수의 개수 N(2 ≤ N ≤ 11)가 주어진다. 둘째 줄에는 A1, A2, ..., AN이 주어진다. (1 ≤ Ai ≤ 100)
 * 셋째 줄에는 합이 N-1인 4개의 정수가 주어지는데, 차례대로 덧셈(+)의 개수, 뺄셈(-)의 개수, 곱셈(×)의 개수, 나눗셈(÷)의 개수이다. 
 * 
 * 출력
 * 첫째 줄에 만들 수 있는 식의 결과의 최대값을, 둘째 줄에는 최소값을 출력한다. 최대값과 최소값은 항상 -10억보다 크거나 같고, 10억보다 작거나 같은 결과가 나오는 입력만 주어진다.
 * 또한, 앞에서 부터 계산했을 때, 중간에 계산되는 식의 결과도 항상 -10억보다 크거나 같고, 10억보다 작거나 같다.
 * 
 * 
 * 예제 입력 1
 * 2
 * 5 6
 * 0 0 1 0
 * 
 * 예제 출력 1
 * 30
 * 30
 * 
 * 
 * 예제 입력 2
 * 3
 * 3 4 5
 * 1 0 1 0
 * 
 * 예제 출력 2
 * 35
 * 17
 * 
 * 예제 입력 3
 * 6
 * 1 2 3 4 5 6
 * 2 1 1 1
 * 
 * 예제 출력 3
 * 54
 * -24
 * 
 */
public class P14888 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int N = Integer.parseInt(scan.nextLine());
		int[] numAry = new int[N];
		for(int index=0; index<N; index++) {
			numAry[index] = scan.nextInt();
		}
		
		// 연산자 개수만큼 숫자를 채워넣는 배열
		// 0:+, 1:-, 2:*, 3:/
		int[] operAry = new int[N-1];
		int operIndex = 0;
		for(int index=0; index<4; index++) {
			int operNum = scan.nextInt();

			for(int index2=0; index2<operNum; index2++) {
				operAry[operIndex] = index;
				operIndex++;
			}
		}
		scan.close();
		
		P14888 p14888 = new P14888();
		int max = -1000000000;
		int min = 1000000000;
		do {
			int calcNum = p14888.calc(numAry, operAry);
			if(calcNum > max) {
				max = calcNum;
			}
			if(calcNum < min) {
				min = calcNum;
			}
		}while(p14888.nextPermutation(operAry));
		
		System.out.println(max);
		System.out.println(min);
	}
	
	public int calc(int[] numAry, int[] operAry) {
		int result = numAry[0];
		
		for(int index=0; index<operAry.length; index++) {
			switch(operAry[index]) {
				case 0 : {
					result = result + numAry[index+1];
					break;
				}
				case 1 : {
					result = result - numAry[index+1];
					break;
				}
				case 2 : {
					result = result * numAry[index+1];
					break;
				}
				case 3 : {
					result = result / numAry[index+1];
					break;
				}
			}
		}
		
		return result;
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
