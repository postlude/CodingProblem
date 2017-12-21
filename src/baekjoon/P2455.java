package baekjoon;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author HSD
 * 
 * 문제
최근에 개발된 지능형 기차가 1번역(출발역)부터 4번역(종착역)까지 4개의 정차역이 있는 노선에서 운행되고 있다. 이 기차에는 타거나 내리는 사람 수를 자동으로 인식할 수 있는 장치가 있다. 이 장치를 이용하여 출발역에서 종착역까지 가는 도중 기차 안에 사람이 가장 많을 때의 사람 수를 계산하려고 한다. 단, 이 기차를 이용하는 사람들은 질서 의식이 투철하여, 역에서 기차에 탈 때, 내릴 사람이 모두 내린 후에 기차에 탄다고 가정한다.



 	내린 사람 수	탄 사람 수
1번역(출발역)	0	32
2번역	3	13
3번역	28	25
4번역(종착역)	39	0
예를 들어, 위와 같은 경우를 살펴보자. 이 경우, 기차 안에 사람이 가장 많은 때는 2번역에서 3명의 사람이 기차에서 내리고, 13명의 사람이 기차에 탔을 때로, 총 42명의 사람이 기차 안에 있다.

이 기차는 다음 조건을 만족하면서 운행된다고 가정한다.

기차는 역 번호 순서대로 운행한다.
출발역에서 내린 사람 수와 종착역에서 탄 사람 수는 0이다.
각 역에서 현재 기차에 있는 사람보다 더 많은 사람이 내리는 경우는 없다.
기차의 정원은 최대 10,000명이고, 정원을 초과하여 타는 경우는 없다.
4개의 역에 대해 기차에서 내린 사람 수와 탄 사람 수가 주어졌을 때, 기차에 사람이 가장 많을 때의 사람 수를 계산하는 프로그램을 작성하시오.

입력
각 역에서 내린 사람 수와 탄 사람 수가 빈칸을 사이에 두고 첫째 줄부터 넷째 줄까지 역 순서대로 한 줄에 하나씩 주어진다. 

출력
첫째 줄에 최대 사람 수를 출력한다.  


input
0 32
3 13
28 25
39 0

output
42
 */
public class P2455 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		P2455 p2455 = new P2455();
		
		ArrayList<Integer> numOfPeopleList = new ArrayList<>();
		int currentPeople = 0;
		
		
		for(int count=0; count<4; count++) {
			String inputStr = scan.nextLine();
			String[] inputAry = inputStr.split(" ");
			
			int dropOffPeople = Integer.parseInt(inputAry[0]);
			int ridePeople = Integer.parseInt(inputAry[1]);
			
			if(count != 0){
				currentPeople = numOfPeopleList.get(count-1);
			}
			
			numOfPeopleList.add(currentPeople - dropOffPeople + ridePeople);
		}
		scan.close();
		
		
		System.out.println(p2455.calcMaxPeopleNum(numOfPeopleList));
	}

	public int calcMaxPeopleNum(ArrayList<Integer> numOfPeopleList) {
		int maxNum = 0;
		
		for(int numOfPeople : numOfPeopleList) {
			if(numOfPeople > maxNum) {
				maxNum = numOfPeople;
			}
		}
		
		return maxNum;
	}
}
