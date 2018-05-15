package baekjoon.algorithmCourse.bfs;

import java.util.ArrayDeque;
import java.util.Scanner;

/**
 * 숨바꼭질 3
 * 
 * 문제
 * 수빈이는 동생과 숨바꼭질을 하고 있다. 수빈이는 현재 점 N(0 ≤ N ≤ 100,000)에 있고, 동생은 점 K(0 ≤ K ≤ 100,000)에 있다. 수빈이는 걷거나 순간이동을 할 수 있다.
 * 만약, 수빈이의 위치가 X일 때 걷는다면 1초 후에 X-1 또는 X+1로 이동하게 된다. 순간이동을 하는 경우에는 0초 후에 2*X의 위치로 이동하게 된다.
 * 수빈이와 동생의 위치가 주어졌을 때, 수빈이가 동생을 찾을 수 있는 가장 빠른 시간이 몇 초 후인지 구하는 프로그램을 작성하시오.
 * 
 * 
 * 입력
 * 첫 번째 줄에 수빈이가 있는 위치 N과 동생이 있는 위치 K가 주어진다. N과 K는 정수이다.
 * 
 * 출력
 * 수빈이가 동생을 찾는 가장 빠른 시간을 출력한다.
 * 
 * 
 * 예제 입력 1
 * 5 17
 * 
 * 예제 출력 1
 * 2
 */
public class P13549 {
	static int MAX = 100000;
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int k = scan.nextInt();
		scan.close();
		
		// queue 이용
		/*int[] time = new int[P13549.MAX+1];
		boolean[] isVisited = new boolean[P13549.MAX+1];
		Queue<Integer> queue = new LinkedList<>();
		
		queue.add(n);
		isVisited[n] = true;
		
		while(!queue.isEmpty()) {
			int nowPosition = queue.remove();
			
			// *2 위치로 이동
			// 반드시 *2를 먼저 확인해야 한다.
			// ex) 3(0) - 2(1), 4(1), 6(0) - 1(2), 5(2), 8(2), 7(1), 12(0)
			//     3(0) - 6(0), 2(1), 4(1) - 12(0), 5(1), 7(1), 1(2), 8(1)
			if(nowPosition*2 <= P13549.MAX) {
				if(!isVisited[nowPosition*2]) {
					queue.add(nowPosition*2);
					isVisited[nowPosition*2] = true;
					time[nowPosition*2] = time[nowPosition]; 
				}
			}
			
			// -1 위치로 이동
			if(nowPosition-1 >= 0) {
				if(!isVisited[nowPosition-1]) {
					queue.add(nowPosition-1);
					isVisited[nowPosition-1] = true;
					time[nowPosition-1] = time[nowPosition] + 1; 
				}
			}
			
			// +1 위치로 이동
			if(nowPosition+1 <= P13549.MAX) {
				if(!isVisited[nowPosition+1]) {
					queue.add(nowPosition+1);
					isVisited[nowPosition+1] = true;
					time[nowPosition+1] = time[nowPosition] + 1;
				}
			}
		}
		
		System.out.println(time[k]);*/
		
		
		// deque 이용
		int[] time2 = new int[P13549.MAX+1];
		boolean[] isVisited2 = new boolean[P13549.MAX+1];
		ArrayDeque<Integer> deque = new ArrayDeque<>();
		
		isVisited2[n] = true;
		deque.add(n);
		
		while(!deque.isEmpty()) {
			int nowPosition = deque.remove();
			
			// 99 line에 *2와 비교하는 부분이 있기 때문에 nowPosition*2가 마지막에 있어도 된다.
			// ex) 1 -> 2 에서 +1이 먼저 계산되더라도 if문 안으로 들어가게 됨
			/*for (int next : new int[]{nowPosition-1, nowPosition+1, nowPosition*2}) {
                if (next >= 0 && next <= MAX) {
                    if (isVisited2[next] == false) {
                        isVisited2[next] = true;
                        if (next == nowPosition*2) {
                        	deque.addFirst(next);
                        	time2[next] = time2[nowPosition];
                        } else {
                        	deque.addLast(next);
                        	time2[next] = time2[nowPosition] + 1;
                        }
                    }
                }  
            }*/
			
			// *2 위치로 이동
			// *2 위치 계산을 가장 먼저 해야한다.
			// 덱 사용 여부와 상관없이 +1, -1 계산에서 방문을 하게 되면 *2에서 아예 방문하지 못하는 경우가 생기기 때문
			if(nowPosition*2 <= P13549.MAX) {
				if(!isVisited2[nowPosition*2]) {
					isVisited2[nowPosition*2] = true;
					deque.addFirst(nowPosition*2);
					time2[nowPosition*2] = time2[nowPosition]; 
				}
			}
			
			// -1 위치로 이동
			if(nowPosition-1 >= 0) {
				if(!isVisited2[nowPosition-1]) {
					isVisited2[nowPosition-1] = true;
					deque.add(nowPosition-1);
					time2[nowPosition-1] = time2[nowPosition] + 1; 
				}
			}
			
			// +1 위치로 이동
			if(nowPosition+1 <= P13549.MAX) {
				if(!isVisited2[nowPosition+1]) {
					isVisited2[nowPosition+1] = true;
					deque.add(nowPosition+1);
					time2[nowPosition+1] = time2[nowPosition] + 1;
				}
			}
		}
		
		System.out.println(time2[k]);
	}
}
