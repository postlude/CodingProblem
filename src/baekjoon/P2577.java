package baekjoon;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author SDH
 * 
 * 문제
세 개의 자연수 A, B, C가 주어질 때 A×B×C를 계산한 결과에 0부터 9까지 각각의 숫자가 몇 번씩 쓰였는지를 구하는 프로그램을 작성하시오.

예를 들어 A = 150, B = 266, C = 427 이라면 

A × B × C = 150 × 266 × 427 = 17037300 이 되고, 

계산한 결과 17037300 에는 0이 3번, 1이 1번, 3이 2번, 7이 2번 쓰였다.

입력
첫째 줄에 A, 둘째 줄에 B, 셋째 줄에 C가 주어진다. A, B, C는 모두 100보다 같거나 크고, 1,000보다 작은 자연수이다.

출력
첫째 줄에는 A×B×C의 결과에 0 이 몇 번 쓰였는지 출력한다. 마찬가지로 둘째 줄부터 열 번째 줄까지 A×B×C의 결과에 1부터 9까지의 숫자가 각각 몇 번 쓰였는지 차례로 한 줄에 하나씩 출력한다.


입력
150
266
427


출력
3
1 
0 
2 
0 
0 
0 
2 
0 
0
 */
public class P2577 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		P2577 p2577 = new P2577();
		
//		int A = Integer.parseInt(scan.nextLine());
//		int B = Integer.parseInt(scan.nextLine());
//		int C = Integer.parseInt(scan.nextLine());
		
		int A = scan.nextInt();
		int B = scan.nextInt();
		int C = scan.nextInt();
		
		int multiplyNum = A * B * C;
		
		ArrayList<Integer> countList = new ArrayList<>();
		
		//init list
		for(int index=0; index<10; index++){
			countList.add(0);
		}
		
		p2577.countNum(multiplyNum, countList);
		
		for(int count : countList){
			System.out.println(count);
		}
		
		scan.close();
	}
	
	public void countNum(int num, ArrayList<Integer> countList){
		String numStr = String.valueOf(num);
		
		char[] numChAry = numStr.toCharArray();
		
		for(int index=0; index<numChAry.length; index++){
			switch(numChAry[index]){
				case '0' : {
					countList.set(0, countList.get(0) + 1);
					break;
				}
				case '1' : {
					countList.set(1, countList.get(1) + 1);
					break;
				}
				case '2' : {
					countList.set(2, countList.get(2) + 1);
					break;
				}
				case '3' : {
					countList.set(3, countList.get(3) + 1);
					break;
				}
				case '4' : {
					countList.set(4, countList.get(4) + 1);
					break;
				}
				case '5' : {
					countList.set(5, countList.get(5) + 1);
					break;
				}
				case '6' : {
					countList.set(6, countList.get(6) + 1);
					break;
				}
				case '7' : {
					countList.set(7, countList.get(7) + 1);
					break;
				}
				case '8' : {
					countList.set(8, countList.get(8) + 1);
					break;
				}
				case '9' : {
					countList.set(9, countList.get(9) + 1);
					break;
				}
			}
		}
	}
}
