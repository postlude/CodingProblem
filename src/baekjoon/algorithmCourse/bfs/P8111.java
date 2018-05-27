package baekjoon.algorithmCourse.bfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 0과 1
 * 
 * 문제
 * 폴란드 왕자 구사과는 다음과 같은 수를 좋아한다.
 * 
 * 0과 1로만 이루어져 있어야 한다.
 * 1이 적어도 하나 있어야 한다.
 * 수의 길이가 100 이하이다.
 * 수가 0으로 시작하지 않는다.
 * 
 * 예를 들어, 101은 구사과가 좋아하는 수이다.
 * 자연수 N이 주어졌을 때, N의 배수 중에서 구사과가 좋아하는 수를 구하는 프로그램을 작성하시오.
 * 
 * 
 * 입력
 * 첫째 줄에 테스트 케이스의 개수 T(T < 1,000)가 주어진다.
 * 둘째 줄부터 T개의 줄에는 자연수 N이 한 줄에 하나씩 주어진다. N은 20,000보다 작거나 같은 자연수이다.
 * 
 * 출력
 * 각각의 테스트 케이스마다 N의 배수이면서, 구사과가 좋아하는 수를 아무거나 출력한다. 만약, 그러한 수가 없다면 BRAK을 출력한다.
 * 
 * 
 * 예제 입력 1
 * 6
 * 17
 * 11011
 * 17
 * 999
 * 125
 * 173
 * 
 * 예제 출력 1
 * 11101
 * 11011
 * 11101
 * 111111111111111111111111111
 * 1000
 * 1011001101
 * 
 */
/*
 * 1, 0으로 이루어진 숫자는 정해져 있다.
 * 1 - 10, 11 - 100, 101, 110, 111 - ...
 * 이전 숫자에서 *10 + 0 or 1
 * % 입력 값을 해서 나온 나머지에 위 연산을 반복(*10 + 0 or 1)
 * 
 * ex) 3 일 때
 * 1 : 나머지 1
 * 10 : 나머지 1(이미 존재)
 * 11 : 나머지 2(10 + 1 : 더한 값이 1)
 * 20 : 나머지 2(이미 존재)
 * 21 : 나머지 0(20 + 1 : 더한 값이 1)
 * 0 : 나머지 0(이미 존재)
 * 1 : 나머지 1(이미 존재)
 * 
 * (1*10 + 1)*10 + 1 = 111
 * 
 */
public class P8111 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int t = scan.nextInt();
		
		for(int testCase=0; testCase<t; testCase++) {
			int num = scan.nextInt();
			
			Queue<Integer> queue = new LinkedList<>();
			int[] modAry = new int[num];
			int[] from = new int[num];
			int[] count = new int[num];
			
			Arrays.fill(modAry, -1);
			Arrays.fill(count, -1);
			Arrays.fill(from, -1);
			
			queue.add(1);
			modAry[1] = 1;
			count[1] = 0;
			
			while(!queue.isEmpty()) {
				int nowMod = queue.remove();
				
				for(int modNum=0; modNum<=1; modNum++) {
					int nextMod = (nowMod*10+modNum)%num;
					
					if(modAry[nextMod] == -1) {
						queue.add(nextMod);
						count[nextMod] = count[nowMod] + 1;
						modAry[nextMod] = modNum;
						from[nextMod] = nowMod;
					}
				}
			}
			
			System.out.println(Arrays.toString(from));
			
			if(count[0] == -1) {
				System.out.println("BRAK");
			}else {
				StringBuilder sb = new StringBuilder();
				for(int index=0; index!=-1; index=from[index]) {
					sb.append(modAry[index]);
				}
				System.out.println(sb.reverse());
			}
		}
		scan.close();
	}
}
