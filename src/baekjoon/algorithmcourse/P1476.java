package baekjoon.algorithmcourse;

import java.util.Scanner;

/**
 * 문제
 * 준규가 사는 나라는 우리가 사용하는 연도와 다른 방식을 이용한다. 준규가 사는 나라에서는 수 3개를 이용해서 연도를 나타낸다. 각각의 수는 지구, 태양, 그리고 달을 나타낸다.
 * 지구를 나타내는 숫자를 E, 태양을 나타내는 숫자를 S, 달을 나타내는 숫자를 M이라고 했을 때, 이 세 수는 서로 다른 범위를 가진다. (1 ≤ E ≤ 15, 1 ≤ S ≤ 28, 1 ≤ M ≤ 19)
 * 우리가 알고있는 1년은 준규가 살고있는 나라에서는 1 1 1로 나타낼 수 있다. 1년이 지날 때마다, 세 수는 모두 1씩 증가한다. 만약, 어떤 수가 범위를 넘어가는 경우에는 1이 된다.
 * 예를 들어, 15년은 15 15 15로 나타낼 수 있다. 하지만, 1년이 지나서 16년이 지나면 16 16 16이 아니라 1 16 16이 된다. 이유는 1 ≤ E ≤ 15 라서 범위를 넘어가기 때문이다.
 * E, S, M이 주어졌고, 1년이 준규가 사는 나라에서 1 1 1일때, 준규가 사는 나라에서 E S M이 우리가 알고 있는 연도로 몇 년인지 구하는 프로그램을 작성하시오.
 * 
 * 입력
 * 첫째 줄에 세 수 E, S, M이 주어진다. 문제에 나와있는 범위를 지키는 입력만 주어진다.
 * 
 * 출력
 * 첫째 줄에 E S M으로 표시되는 가장 빠른 연도를 출력한다. 1 1 1은 항상 1이기 때문에, 정답이 음수가 나오는 경우는 없다.
 * 
 * 
 * 입력1
 * 1 16 16
 * 출력1
 * 16
 * 
 * 입력2
 * 1 1 1
 * 출력2
 * 1
 * 
 * 입력3
 * 1 2 3
 * 출력3
 * 5266
 * 
 * 입력4
 * 15 28 19
 * 출력4
 * 7980
 * 
 */
public class P1476 {

	public static void main(String[] args) {
//		System.out.println(15*28*19);
		
		int[] esmAry = new int[3];
		Scanner scan = new Scanner(System.in);
		
		for(int index=0; index<3; index++) {
			esmAry[index] = scan.nextInt();
		}
		scan.close();
		
		P1476 p1476 = new P1476();
		System.out.println(p1476.calcYear(esmAry));
	}

	
	public int calcYear(int[] esmAry) {
		int[] yearAry = {1, 1, 1};
		int year = 1;
		
		while(true) {
			if(esmAry[0]==yearAry[0] && esmAry[1]==yearAry[1] && esmAry[2]==yearAry[2]) {
				return year;
			}
			
			year++;
			for(int index=0; index<3; index++) {
				yearAry[index]++;
				switch(index) {
					case 0:{
						if(yearAry[index] == 16) {
							yearAry[index] = 1;
						}
						break;
					}
					case 1:{
						if(yearAry[index] == 29) {
							yearAry[index] = 1;
						}
						break;
					}
					case 2:{
						if(yearAry[index] == 20) {
							yearAry[index] = 1;
						}
						break;
					}
				}
			}
		}
	}
}
