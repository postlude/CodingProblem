package baekjoon.algorithmCourse.bfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 벽 부수고 이동하기
 * 
 * 문제
 * N×M의 행렬로 표현되는 맵이 있다. 맵에서 0은 이동할 수 있는 곳을 나타내고, 1은 이동할 수 없는 벽이 있는 곳을 나타낸다.
 * 당신은 (1, 1)에서 (N, M)의 위치까지 이동하려 하는데, 이 때 최단 경로로 이동하려 한다. 최단경로는 맵에서 가장 적은 개수의 칸을 지나는 경로를 말하는데, 이 때 시작하는 칸과 끝나는 칸도 포함해서 센다.
 * 만약에 이동하는 도중에 한 개의 벽을 부수고 이동하는 것이 좀 더 경로가 짧아진다면, 벽을 한 개 까지 부수고 이동하여도 된다.
 * 맵이 주어졌을 때, 최단 경로를 구해 내는 프로그램을 작성하시오.
 * 
 * 
 * 입력
 * 첫째 줄에 N(1≤N≤1,000), M(1≤M≤1,000)이 주어진다. 다음 N개의 줄에 M개의 숫자로 맵이 주어진다. (1, 1)과 (N, M)은 항상 0이라고 가정하자.
 * 
 * 출력
 * 첫째 줄에 최단 거리를 출력한다. 불가능할 때는 -1을 출력한다.
 * 
 * 
 * 예제 입력 1
 * 6 4
 * 0100
 * 1110
 * 1000
 * 0000
 * 0111
 * 0000
 * 
 * 
 * 예제 출력 1
 * 15
 */
public class P2206 {
	// up, down, right, left
	static final int[] ROW_MOVE = {-1, 1, 0, 0};
	static final int[] COL_MOVE = {0, 0, 1, -1};
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int m = scan.nextInt();
		
		int[][] maze = new int[n][m];
		for(int row=0; row<n; row++) {
			char[] input = scan.next().toCharArray();
			for(int col=0; col<m; col++) {
				maze[row][col] = input[col] - '0';
			}
		}
		scan.close();
		// input end
		
		Queue<Room> queue = new LinkedList<>();
		
		// 벽을 한 번밖에 부수지 못하므로 그 전까지 벽을 0번 부순 경우와 1번 부순 경우를 따로 생각해야 함
		int[][][] roomCount = new int[n][m][2];
		
		// 다른 문제처럼 boolean 배열을 사용할수는 없다.
		// 어떤 한 방을 방문하는 경우가 이전에 벽을 부수고 온 경우와 벽을 부수지 않고 오는 경우 모두 존재할 수 있기 때문 
		
		queue.add(new Room(0, 0, 0));
		roomCount[0][0][0] = 1;
		
		while(!queue.isEmpty()) {
			Room nowRoom = queue.remove();
			int nowRow = nowRoom.row;
			int nowCol = nowRoom.col;
			int nowWallBreak = nowRoom.wallBreak;
			
			for(int index=0; index<4; index++) {
				int nextRow = nowRow + P2206.ROW_MOVE[index];
				int nextCol = nowCol + P2206.COL_MOVE[index];
				
				if(nextRow<0 || nextRow>=n || nextCol<0 || nextCol>=m) {
					continue;
				}
				
				// 다음 방이 벽이 없는 경우 (0->0, 1->0)
				// 이전에 왔던 곳을 다시 방문하는 걸 방지하기 위해 다음 방의 roomCount가 0인 곳일 경우에만 방문 
				if(maze[nextRow][nextCol]==0 && roomCount[nextRow][nextCol][nowWallBreak]==0) {
					queue.add(new Room(nextRow, nextCol, nowWallBreak));
					roomCount[nextRow][nextCol][nowWallBreak] = roomCount[nowRow][nowCol][nowWallBreak] + 1;
				}else if(maze[nextRow][nextCol]==1 && nowWallBreak==0 && roomCount[nextRow][nextCol][1]==0) { 
					// 다음 방이 벽이 있는 경우(0->1)
					// 이전에 벽을 부순 적이 없어야 하고(nowWallBreak==0)
					// 마찬가지로 재방문 방지를 위해 roomCount가 0인 곳일 경우에만 방문
					queue.add(new Room(nextRow, nextCol, 1));
					roomCount[nextRow][nextCol][1] = roomCount[nowRow][nowCol][nowWallBreak] + 1;
				}
			}
		}
		
		int notBreakRoomCount = roomCount[n-1][m-1][0];
		int breakRoomCount = roomCount[n-1][m-1][1];
		if(notBreakRoomCount!=0 && breakRoomCount!=0) {
			System.out.println(Math.min(notBreakRoomCount, breakRoomCount));
		}else if(breakRoomCount != 0){
			System.out.println(breakRoomCount);
		}else if(notBreakRoomCount != 0){
			System.out.println(notBreakRoomCount);
		}else {
			System.out.println(-1);
		}
	}

}

class Room{
	int row;
	int col;
	int wallBreak;
	
	public Room() {
		
	}
	
	public Room(int row, int col, int wallBreak) {
		this.row = row;
		this.col = col;
		this.wallBreak = wallBreak;
	}
}




// 정답 코드
/*import java.util.*;
class Pair {
    int x, y, z;
    Pair(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
public class Main {
    public static int[] dx = {1, -1, 0, 0};
    public static int[] dy = {0, 0, 1, -1};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        sc.nextLine();
        int[][] a = new int[n][m];
        int[][][] d = new int[n][m][2];
        for (int i=0; i<n; i++) {
            String s = sc.nextLine();
            for (int j=0; j<m; j++) {
                a[i][j] = s.charAt(j) - '0';
            }
        }
        d[0][0][0] = 1;
        Queue<Pair> q = new LinkedList<Pair>();
        q.offer(new Pair(0, 0, 0));
        while (!q.isEmpty()) {
            Pair p = q.remove();
            int x = p.x;
            int y = p.y;
            int z = p.z;
            for (int k=0; k<4; k++) {
                int nx = x+dx[k];
                int ny = y+dy[k];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if (a[nx][ny] == 0 && d[nx][ny][z] == 0) {
                    d[nx][ny][z] = d[x][y][z] + 1;
                    q.offer(new Pair(nx, ny, z));
                }
                if (z == 0 && a[nx][ny] == 1 && d[nx][ny][z+1] == 0) {
                    d[nx][ny][z+1] = d[x][y][z] + 1;
                    q.offer(new Pair(nx, ny, z+1));
                }
            }
        }
        if (d[n-1][m-1][0] != 0 && d[n-1][m-1][1] != 0) {
            System.out.println(Math.min(d[n-1][m-1][0], d[n-1][m-1][1]));
        } else if (d[n-1][m-1][0] != 0) {
            System.out.println(d[n-1][m-1][0]);
        } else if (d[n-1][m-1][1] != 0) {
            System.out.println(d[n-1][m-1][1]);
        } else {
            System.out.println(-1);
        }
    }
}*/
