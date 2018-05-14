package baekjoon.algorithmCourse.bfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 숨바꼭질 2
 * 
 * 문제
 * 수빈이는 동생과 숨바꼭질을 하고 있다. 수빈이는 현재 점 N(0 ≤ N ≤ 100,000)에 있고, 동생은 점 K(0 ≤ K ≤ 100,000)에 있다. 수빈이는 걷거나 순간이동을 할 수 있다.
 * 만약, 수빈이의 위치가 X일 때 걷는다면 1초 후에 X-1 또는 X+1로 이동하게 된다. 순간이동을 하는 경우에는 1초 후에 2*X의 위치로 이동하게 된다.
 * 수빈이와 동생의 위치가 주어졌을 때, 수빈이가 동생을 찾을 수 있는 가장 빠른 시간이 몇 초 후인지 그리고, 가장 빠른 시간으로 찾는 방법이 몇 가지 인지 구하는 프로그램을 작성하시오.
 * 
 * 
 * 입력
 * 첫 번째 줄에 수빈이가 있는 위치 N과 동생이 있는 위치 K가 주어진다. N과 K는 정수이다.
 * 
 * 출력
 * 첫째 줄에 수빈이가 동생을 찾는 가장 빠른 시간을 출력한다.
 * 둘째 줄에는 가장 빠른 시간으로 수빈이가 동생을 찾는 방법의 수를 출력한다.
 * 
 * 
 * 예제 입력 1
 * 5 17
 * 
 * 예제 출력 1
 * 4
 * 2
 * 
 */
public class P12851 {
	static final int MAX = 100000;
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int k = scan.nextInt();
		scan.close();
		
		int[] time = new int[P12851.MAX+1];
		boolean[] isVisited = new boolean[P12851.MAX+1];
		Queue<Integer> queue = new LinkedList<>();
		int[] numOfWay = new int[P12851.MAX+1];
		
		queue.add(n);
		isVisited[n] = true;
		numOfWay[n] = 1;
		
		while(!queue.isEmpty()) {
			int nowPosition = queue.remove();
			
			// -1 위치로 이동
			if(nowPosition-1 >= 0) {
				if(!isVisited[nowPosition-1]) {
					queue.add(nowPosition-1);
					isVisited[nowPosition-1] = true;
					time[nowPosition-1] = time[nowPosition] + 1; 
					numOfWay[nowPosition-1] = numOfWay[nowPosition];
				}else if(numOfWay[nowPosition-1] != numOfWay[nowPosition]){
					numOfWay[nowPosition-1] += numOfWay[nowPosition];
				}
			}
			
			// +1 위치로 이동
			if(nowPosition+1 <= P12851.MAX) {
				if(!isVisited[nowPosition+1]) {
					queue.add(nowPosition+1);
					isVisited[nowPosition+1] = true;
					time[nowPosition+1] = time[nowPosition] + 1; 
					numOfWay[nowPosition+1] = numOfWay[nowPosition];
				}else if(numOfWay[nowPosition+1] != numOfWay[nowPosition]){
					numOfWay[nowPosition+1] += numOfWay[nowPosition];
				}
			}
			
			// *2 위치로 이동
			if(nowPosition*2 <= P12851.MAX) {
				if(!isVisited[nowPosition*2]) {
					queue.add(nowPosition*2);
					isVisited[nowPosition*2] = true;
					time[nowPosition*2] = time[nowPosition] + 1; 
					numOfWay[nowPosition*2] = numOfWay[nowPosition];
				}else if(numOfWay[nowPosition*2] != numOfWay[nowPosition]){
					numOfWay[nowPosition*2] += numOfWay[nowPosition];
				}
			}
		}
		
		System.out.println(time[k]);
		System.out.println(numOfWay[k]);
	}

}
