package baekjoon.algorithmcourse;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 로또
 * 
 * 문제
 * 독일 로또는 {1, 2, ..., 49}에서 숫자 6개를 고른다.
 * 로또 번호를 선택하는데 사용되는 가장 유명한 전략은 49가지 숫자 중 k(k>6)개의 숫자를 골라 집합 S를 만든 다음 그 숫자만 가지고 번호를 선택하는 것이다.
 * 예를 들어, k=8, S={1,2,3,5,8,13,21,34}인 경우 이 집합 S에서 숫자를 고를 수 있는 경우의 수는 총 28가지이다.
 * ([1,2,3,5,8,13], [1,2,3,5,8,21], [1,2,3,5,8,34], [1,2,3,5,13,21], ..., [3,5,8,13,21,34])
 * 집합 S와 k가 주어졌을 때, 숫자를 고르는 모든 방법을 구하는 프로그램을 작성하시오.
 * 
 * 입력
 * 입력은 여러 개의 테스트 케이스로 이루어져 있다. 각 테스트 케이스는 한 줄로 이루어져 있다. 첫 번째 숫자는 k (6 < k < 13)이고, 다음 k개 숫자는 집합 S에 포함되는 수이다.
 * S의 원소는 오름차순으로 주어진다.
 * 입력의 마지막 줄에는 0이 하나 주어진다. 
 * 
 * 출력
 * 각 테스트 케이스 마다 숫자를 고르는 모든 방법을 출력한다. 이 때, 사전 순으로 출력한다.
 * 각 테스트 케이스 사이에는 빈 줄을 하나 출력한다.
 * 
 * 
 * 예제 입력 1
 * 7 1 2 3 4 5 6 7
 * 8 1 2 3 5 8 13 21 34
 * 0
 * 
 * 예제 출력 1
 * 1 2 3 4 5 6
 * 1 2 3 4 5 7
 * 1 2 3 4 6 7
 * 1 2 3 5 6 7
 * 1 2 4 5 6 7
 * 1 3 4 5 6 7
 * 2 3 4 5 6 7
 * 
 * 1 2 3 5 8 13
 * 1 2 3 5 8 21
 * 1 2 3 5 8 34
 * 1 2 3 5 13 21
 * 1 2 3 5 13 34
 * 1 2 3 5 21 34
 * 1 2 3 8 13 21
 * 1 2 3 8 13 34
 * 1 2 3 8 21 34
 * 1 2 3 13 21 34
 * 1 2 5 8 13 21
 * 1 2 5 8 13 34
 * 1 2 5 8 21 34
 * 1 2 5 13 21 34
 * 1 2 8 13 21 34
 * 1 3 5 8 13 21
 * 1 3 5 8 13 34
 * 1 3 5 8 21 34
 * 1 3 5 13 21 34
 * 1 3 8 13 21 34
 * 1 5 8 13 21 34
 * 2 3 5 8 13 21
 * 2 3 5 8 13 34
 * 2 3 5 8 21 34
 * 2 3 5 13 21 34
 * 2 3 8 13 21 34
 * 2 5 8 13 21 34
 * 3 5 8 13 21 34
 */
// 재귀로 풀기
public class P6603_2 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		ArrayList<String[]> testCase = new ArrayList<>();
		while(true){
			String input = scan.nextLine();
			if(input.equals("0")) {
				break;
			}
			String[] inputAry = input.split(" ");
			testCase.add(inputAry);
		}
		scan.close();
		//input end
		
		P6603_2 p6603_2 = new P6603_2();
		for(String[] caseNum : testCase) {
			ArrayList<String> lottery = new ArrayList<>();
			// 첫 번째 값은 숫자 개수이므로 index 1부터 시작
			p6603_2.makeLottery(caseNum, 1, lottery);
			System.out.println();
		}
	}
	
	public void printList(List<String> list) {
		for(String num : list) {
			System.out.print(num + " ");
		}
		System.out.println();
	}

	public void makeLottery(String[] caseNum, int numIndex, ArrayList<String> lottery) {
		if(lottery.size() == 6) {
			printList(lottery);
			return;
		}else if(numIndex >= caseNum.length) {
			return;
		}
		
		// caseNum[numIndex] 사용하는 경우
		lottery.add(caseNum[numIndex]);
		makeLottery(caseNum, numIndex+1, lottery);
		
		// caseNum[numIndex] 사용하지 않는 경우
		lottery.remove(lottery.size()-1);
		makeLottery(caseNum, numIndex+1, lottery);
	}
}
