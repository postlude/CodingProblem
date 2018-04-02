package baekjoon.algorithmcourse;

import java.util.Scanner;

/**
 * 문제
 * 수빈이는 TV를 보고 있다. 수빈이는 채널을 돌리려고 했지만, 버튼을 너무 세게 누르는 바람에, 일부 숫자 버튼이 고장났다.
 * 리모컨에는 버튼이 0부터 9까지 숫자, +와 -가 있다. +를 누르면 현재 보고있는 채널에서 +1된 채널로 이동하고, -를 누르면 -1된 채널로 이동한다. 채널 0에서 -를 누른 경우에는 채널이 변하지 않고,
 * 채널은 무한대 만큼 있다.
 * 수빈이가 지금 이동하려고 하는 채널은 N이다. 어떤 버튼이 고장났는지 주어졌을 때, 채널 N으로 이동하기 위해서 버튼을 최소 몇 번 눌러야하는지 구하는 프로그램을 작성하시오. 
 * 수빈이가 지금 보고 있는 채널은 100번이다.
 * 
 * 입력
 * 첫째 줄에 수빈이가 이동하려고 하는 채널 N (0 ≤ N ≤ 500,000)이 주어진다.  둘째 줄에는 고장난 버튼의 개수 M (0 ≤ M ≤ 10)이 주어진다.
 * 고장난 버튼이 있는 경우에는 셋째 줄에는 고장난 버튼이 주어지며, 같은 버튼이 중복되서 주어지는 경우는 없다.
 * 
 * 출력
 * 첫째 줄에 채널 N으로 이동하기 위해 버튼을 최소 몇 번 눌러야 하는지를 출력한다.
 * 
 * 
 * 예제 입력 1
 * 5457
 * 3
 * 6 7 8
 * 예제 출력 1
 * 6
 * 
 * 예제 입력 2
 * 100
 * 5
 * 0 1 2 3 4
 * 예제 출력 2
 * 0
 * 
 * 예제 입력 3
 * 500000
 * 8
 * 0 2 3 4 6 7 8 9
 * 예제 출력 3
 * 11117
 * 
 */
public class P1107 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		//input
		int targetCh = scan.nextInt();
		int numOfBrokenButton = scan.nextInt();
		
		//고장난 번호 자체를 배열에 넣을게 아니라 인덱스로 사용하면 정보를 넣을게 줄어들게 됨
		boolean[] brokenButtonAry = new boolean[10];
		
		for(int count=0; count<numOfBrokenButton; count++) {
//			brokenButtonAry[index] = scan.nextInt();
			int index = scan.nextInt();
			brokenButtonAry[index] = true;
		}
		scan.close();
		
		
		P1107 p1107 = new P1107();
		System.out.println(p1107.calcButtonPress(targetCh, numOfBrokenButton, brokenButtonAry));
		
	}
	
	/**
	 * 해당 숫자로 이동가능한지 체크
	 * @param targetNum
	 * @param brokenButtonAry
	 * @param moveNum
	 * @return 불가능하면 0, 가능하면 이동 횟수(자리수)
	 */
	public int isPossible(int targetNum, boolean[] brokenButtonAry) {
		int moveNum = 0;
		
		//밑의 while 문 조건식을 >= 로 바꾸면 0일 때 무한 루프돌게 되므로 0일 때처리 따로
		if (targetNum == 0) {
            if (brokenButtonAry[0]) {
                return 0;
            } else {
                return 1;
            }
        }
		
		while(targetNum > 0) {
			if(brokenButtonAry[targetNum%10]) {
				return 0;
			}
			targetNum /= 10;
			moveNum++;
		}
		return moveNum;
	}
	
	public int calcButtonPress(int targetCh, int numOfBrokenButton, boolean[] brokenButtonAry) {
//		int minButtonPress = 1000000;
		
		//기존 채널 100 고려
		//100에서 숫자를 누르지 않고 +,- 만 눌러서 이동하는 횟수를 최소 값으로 잡아놓고
		//아래 for문에서 어떤 숫자로 이동 -> +,-로 이동 하는 횟수가 더 크면 어차피 100에서 바로 이동하는게 최소값으로 될 것이므로 아래와 같이 계산
		int minButtonPress = targetCh - 100;
        if (minButtonPress < 0) {
            minButtonPress = -minButtonPress;
        }
		
		
		//채널 자체는 무한대이기 때문에 이동하려는 최대값(50만)을 넘어가서 -로 이동하는게 최소값이 되는 경우도 발생할 수 있음
		//1억보다 작기 때문에 모든 채널로 이동해서 +,-로 이동하는 횟수를 모두 계산
		for(int num=0; num<1000000; num++) {
			//일단 num으로 이동 가능한지 체크
			int moveNum = isPossible(num, brokenButtonAry);
			
			//이동 횟수 계산
			if(moveNum > 0) {//num으로 이동 가능한 경우
				int buttonPress = num - targetCh;
                if (buttonPress < 0) {
                	buttonPress = -buttonPress;
                }
                if (minButtonPress > moveNum+buttonPress) {
                	minButtonPress = moveNum+buttonPress;
                }
			}
		}
		
		return minButtonPress;
	}
}
