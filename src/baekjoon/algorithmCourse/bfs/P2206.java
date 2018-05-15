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
		int[][][] roomCount = new int[n][m][2];
		
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
				int nextWallBreak = nowWallBreak;
				
				if(nextRow<0 || nextRow>=n || nextCol<0 || nextCol>=m) {
					continue;
				}else if(nowWallBreak == 0) {
					if(maze[nextRow][nextCol] == 1) {
						nextWallBreak = 1;
					}
				}else if(maze[nextRow][nextCol] == 1) {
					continue;
				}
				queue.add(new Room(nextRow, nextCol, nextWallBreak));
				roomCount[nextRow][nextCol][nextWallBreak] = roomCount[nowRow][nowCol][nowWallBreak] + 1; 
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
