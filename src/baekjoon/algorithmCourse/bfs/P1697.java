package baekjoon.algorithmCourse.bfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 숨바꼭질
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
 * 수빈이가 동생을 찾는 가장 빠른 시간을 출력한다.
 * 
 * 
 * 예제 입력 1
 * 5 17
 * 
 * 예제 출력 1
 * 4
 * 
 */
public class P1697 {
	static int MAX = 100000;
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int k = scan.nextInt();
		scan.close();
		
		int[] distance = new int[P1697.MAX+1];
		boolean[] isVisited = new boolean[P1697.MAX+1];
		
		// position[n] = 0;
		isVisited[n] = true;
		
		Queue<Integer> queue = new LinkedList<>();
		
		// 시작 위치
		queue.add(n);
		
		while(!queue.isEmpty()) {
			// 큐에 들어가 있는 값의 제일 앞의 값을 빼냄(현재 위치)
			int nowPosition = queue.remove();
			
			// -1 위치로 이동
			if(nowPosition-1 >= 0) {
				// 방문한 적이 없어야 함
				if(!isVisited[nowPosition-1]) {
					queue.add(nowPosition-1);
					isVisited[nowPosition-1] = true;
					distance[nowPosition-1] = distance[nowPosition] + 1; 
				}
			}
			
			// +1 위치로 이동
			if(nowPosition+1 <= P1697.MAX) {
				if(!isVisited[nowPosition+1]) {
					queue.add(nowPosition+1);
					isVisited[nowPosition+1] = true;
					distance[nowPosition+1] = distance[nowPosition] + 1; 
				}
			}
			
			// *2 위치로 이동
			if(nowPosition*2 <= P1697.MAX) {
				if(!isVisited[nowPosition*2]) {
					queue.add(nowPosition*2);
					isVisited[nowPosition*2] = true;
					distance[nowPosition*2] = distance[nowPosition] + 1; 
				}
			}
		}
		System.out.println(distance[k]);
	}
}



// 정답 코드
/*import java.util.*;

public class Main {
    public static final int MAX = 1000000;
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        boolean[] check = new boolean[MAX];
        int[] dist = new int[MAX];
        check[n] = true;
        dist[n] = 0;
        Queue<Integer> q = new LinkedList<Integer>();
        q.add(n);
        while (!q.isEmpty()) {
            int now = q.remove();
            if (now-1 >= 0) {
                if (check[now-1] == false) {
                    q.add(now-1);
                    check[now-1] = true;
                    dist[now-1] = dist[now] + 1;
                }
            }
            if (now+1 < MAX) {
                if (check[now+1] == false) {
                    q.add(now+1);
                    check[now+1] = true;
                    dist[now+1] = dist[now] + 1;
                }
            }
            if (now*2 < MAX) {
                if (check[now*2] == false) {
                    q.add(now*2);
                    check[now*2] = true;
                    dist[now*2] = dist[now] + 1;
                }
            }
        }
        System.out.println(dist[m]);
    }
}*/