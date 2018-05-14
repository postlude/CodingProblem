package baekjoon.algorithmCourse.bfs;

import java.util.ArrayDeque;
import java.util.Scanner;

/**
 * 알고스팟
 * 
 * 문제
 * 알고스팟 운영진이 모두 미로에 갇혔다. 미로는 N*M 크기이며, 총 1*1크기의 방으로 이루어져 있다. 미로는 빈 방 또는 벽으로 이루어져 있고, 빈 방은 자유롭게 다닐 수 있지만, 벽은 부수지 않으면 이동할 수 없다.
 * 알고스팟 운영진은 여러명이지만, 항상 모두 같은 방에 있어야 한다. 즉, 여러 명이 다른 방에 있을 수는 없다. 어떤 방에서 이동할 수 있는 방은 상하좌우로 인접한 빈 방이다.
 * 즉, 현재 운영진이 (x, y)에 있을 때, 이동할 수 있는 방은 (x+1, y), (x, y+1), (x-1, y), (x, y-1) 이다. 단, 미로의 밖으로 이동 할 수는 없다.
 * 벽은 평소에는 이동할 수 없지만, 알고스팟의 무기 AOJ를 이용해 벽을 부수어 버릴 수 있다. 벽을 부수면, 빈 방과 동일한 방으로 변한다.
 * 만약 이 문제가 알고스팟에 있다면, 운영진들은 궁극의 무기 sudo를 이용해 벽을 한 번에 다 없애버릴 수 있지만, 안타깝게도 이 문제는 Baekjoon Online Judge에 수록되어 있기 때문에, sudo를 사용할 수 없다.
 * 현재 (1, 1)에 있는 알고스팟 운영진이 (N, M)으로 이동하려면 벽을 최소 몇 개 부수어야 하는지 구하는 프로그램을 작성하시오.
 * 
 * 
 * 입력
 * 첫째 줄에 미로의 크기를 나타내는 가로 크기 M, 세로 크기 N (1 ≤ N, M ≤ 100)이 주어진다. 다음 N개의 줄에는 미로의 상태를 나타내는 숫자 0과 1이 주어진다. 0은 빈 방을 의미하고, 1을 벽을 의미한다.
 * (1, 1)과 (N, M)은 항상 뚫려있다.
 * 
 * 출력
 * 첫째 줄에 알고스팟 운영진이 (N, M)으로 이동하기 위해 벽을 최소 몇 개 부수어야 하는지 출력한다.
 * 
 * 
 * 예제 입력 1
 * 3 3
 * 011
 * 111
 * 110
 * 
 * 예제 출력 1
 * 3
 * 
 * 
 * 예제 입력 2
 * 4 2
 * 0001
 * 1000
 * 
 * 예제 출력 2
 * 0
 * 
 * 
 * 예제 입력 3
 * 6 6
 * 001111
 * 010000
 * 001111
 * 110001
 * 011010
 * 100010
 * 
 * 예제 출력 3
 * 2
 * 
 */
public class P1261 {
	// up, down, right, left
	static final int[] ROW_MOVE = {-1, 1, 0, 0};
	static final int[] COL_MOVE = {0, 0, 1, -1};
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int m = scan.nextInt();
		
		int[][] maze = new int[m][n];
		for(int row=0; row<m; row++) {
			char[] input = scan.next().toCharArray();
			for(int col=0; col<n; col++) {
				maze[row][col] = input[col] - '0';
			}
		}
		scan.close();
		// input end
		
		int targetPosition = n * m;
		ArrayDeque<Integer> deque = new ArrayDeque<>();
		int[] wallCount = new int[targetPosition];
		boolean[] isVisited = new boolean[targetPosition];
		
		deque.add(0);
		wallCount[0] = 0;
		isVisited[0] = true;
		
		while(!deque.isEmpty()) {
			int nowPosition = deque.remove();
			
			int row = nowPosition / n;
			int col = nowPosition % n;
			
			for(int index=0; index<4; index++) {
				int nextRow = row + P1261.ROW_MOVE[index];
				int nextCol = col + P1261.COL_MOVE[index];
				
				if(nextRow>=0 && nextRow<m && nextCol>=0 && nextCol<n) {
					int nextPosition = nextRow*n + nextCol;
					if(!isVisited[nextPosition]) {
						isVisited[nextPosition] = true;
						if(maze[nextRow][nextCol] == 1) {
							deque.add(nextPosition);
							wallCount[nextPosition] = wallCount[nowPosition] + 1;
						}else {
							deque.addFirst(nextPosition);
							wallCount[nextPosition] = wallCount[nowPosition];
						}
					}
				}
			}
		}
		
		System.out.println(wallCount[targetPosition-1]);
	}

}



// 정답 코드
/*import java.util.*;
class Pair {
    int x, y;
    Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
public class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        sc.nextLine();
        int[][] a = new int[n][m];
        for (int i=0; i<n; i++) {
            String s = sc.nextLine();
            for (int j=0; j<m; j++) {
                a[i][j] = s.charAt(j) - '0';
            }
        }
        int[] dx = {0,0,1,-1};
        int[] dy = {1,-1,0,0};
        int[][] d = new int [n][m];
        ArrayDeque<Pair> deque = new ArrayDeque<Pair>();
        deque.add(new Pair(0, 0));
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                d[i][j] = -1;
            }
        }
        d[0][0] = 0;
        while (!deque.isEmpty()) {
            Pair p = deque.poll();
            int x = p.x;
            int y = p.y;
            for (int k=0; k<4; k++) {
                int nx = x+dx[k];
                int ny = y+dy[k];
                if (0 <= nx && nx < n && 0 <= ny && ny < m) {
                    if (d[nx][ny] == -1) {
                        if (a[nx][ny] == 0) {
                            d[nx][ny] = d[x][y];
                            deque.addFirst(new Pair(nx, ny));
                        } else {
                            d[nx][ny] = d[x][y]+1;
                            deque.addLast(new Pair(nx, ny));
                        }
                    }
                }
            }
        }
        System.out.println(d[n-1][m-1]);
    }
}*/
