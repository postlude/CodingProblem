package baekjoon.algorithmCourse.bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

/**
 * 숨바꼭질 4
 * 
 * 문제
 * 수빈이는 동생과 숨바꼭질을 하고 있다. 수빈이는 현재 점 N(0 ≤ N ≤ 100,000)에 있고, 동생은 점 K(0 ≤ K ≤ 100,000)에 있다.
 * 수빈이는 걷거나 순간이동을 할 수 있다. 만약, 수빈이의 위치가 X일 때 걷는다면 1초 후에 X-1 또는 X+1로 이동하게 된다. 순간이동을 하는 경우에는 1초 후에 2*X의 위치로 이동하게 된다.
 * 수빈이와 동생의 위치가 주어졌을 때, 수빈이가 동생을 찾을 수 있는 가장 빠른 시간이 몇 초 후인지 구하는 프로그램을 작성하시오.
 * 
 * 
 * 입력
 * 첫 번째 줄에 수빈이가 있는 위치 N과 동생이 있는 위치 K가 주어진다. N과 K는 정수이다.
 * 
 * 출력
 * 첫째 줄에 수빈이가 동생을 찾는 가장 빠른 시간을 출력한다.
 * 둘째 줄에 어떻게 이동해야 하는지 공백으로 구분해 출력한다.
 * 
 * 
 * 예제 입력 1
 * 5 17
 * 
 * 예제 출력 1
 * 4
 * 5 10 9 18 17
 * 
 * 
 * 예제 입력 2
 * 5 17
 * 
 * 예제 출력 2
 * 4
 * 5 4 8 16 17
 */
public class P13913 {
	static int MAX = 100000;
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int k = scan.nextInt();
		scan.close();
		
		int[] distance = new int[P13913.MAX+1];
		boolean[] isVisited = new boolean[P13913.MAX+1];
		isVisited[n] = true;
		
		// 해당 index의 위치가 그 전에 어디서부터 왔는지를 기록하는 배열
		int[] from = new int[P13913.MAX+1];
		
		Queue<Integer> queue = new LinkedList<>();
		
		// 시작 위치
		queue.add(n);
		
		while(!queue.isEmpty()) {
			int nowPosition = queue.remove();
			
			// -1 위치로 이동
			if(nowPosition-1 >= 0) {
				if(!isVisited[nowPosition-1]) {
					queue.add(nowPosition-1);
					isVisited[nowPosition-1] = true;
					distance[nowPosition-1] = distance[nowPosition] + 1; 
					// 다음 위치가 현재 위치에서부터 이동했음을 저장
					from[nowPosition-1] = nowPosition;
				}
			}
			
			// +1 위치로 이동
			if(nowPosition+1 <= P13913.MAX) {
				if(!isVisited[nowPosition+1]) {
					queue.add(nowPosition+1);
					isVisited[nowPosition+1] = true;
					distance[nowPosition+1] = distance[nowPosition] + 1; 
					from[nowPosition+1] = nowPosition;
				}
			}
			
			// *2 위치로 이동
			if(nowPosition*2 <= P13913.MAX) {
				if(!isVisited[nowPosition*2]) {
					queue.add(nowPosition*2);
					isVisited[nowPosition*2] = true;
					distance[nowPosition*2] = distance[nowPosition] + 1; 
					from[nowPosition*2] = nowPosition;
				}
			}
		}
		
		// 도착 위치에서부터 시작 위치까지 찾아가야 하므로 stack 이용
		Stack<Integer> path = new Stack<>();
		path.push(k);
		int prevPosition = from[k];
		do {
			path.push(prevPosition);
			prevPosition = from[prevPosition];
		}while(prevPosition != n);
		path.push(n);
		
		
		System.out.println(distance[k]);
		// stack을 pop() 메소드로 빼지 않고 아래와 같이 for문으로 빼면 역순으로 출력되지 않음
		// 넣은 순서 그대로 출력됨
//		for(int position : path) {
//			System.out.print(position + " ");
//		}
		while(!path.isEmpty()) {
			System.out.print(path.pop() + " ");
		}
	}
}