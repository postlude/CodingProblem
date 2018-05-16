package baekjoon.algorithmCourse.bfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 탈출
 * 
 * 문제
 * 사악한 암흑의 군주 이민혁은 드디어 마법 구슬을 손에 넣었고, 그 능력을 실험해보기 위해 근처의 티떱숲에 홍수를 일으키려고 한다.
 * 이 숲에는 고슴도치가 한 마리 살고 있다.고슴도치는 제일 친한 친구인 비버의 굴로 가능한 빨리 도망가 홍수를 피하려고 한다.
 * 티떱숲의 지도는 R행 C열로 이루어져 있다. 비어있는 곳은 '.'로 표시되어 있고, 물이 차있는 지역은 '*', 돌은 'X'로 표시되어 있다. 비버의 굴은 'D'로, 고슴도치의 위치는 'S'로 나타내어져 있다.
 * 매 분마다 고슴도치는 현재 있는 칸과 인접한 네 칸 중 하나로 이동할 수 있다. (위, 아래, 오른쪽, 왼쪽) 물도 매 분마다 비어있는 칸으로 확장한다.
 * 물이 있는 칸과 인접해있는 비어있는 칸(적어도 한 변을 공유)은 물이 차게 된다. 물과 고슴도치는 돌을 통과할 수 없다. 또, 고슴도치는 물로 차있는 구역으로 이동할 수 없고, 물도 비버의 소굴로 이동할 수 없다.
 * 티떱숲의 지도가 주어졌을 때, 고슴도치가 안전하게 비버의 굴로 이동하기 위해 필요한 최소 시간을 구하는 프로그램을 작성하시오.
 * 고슴도치는 물이 찰 예정인 칸으로 이동할 수 없다. 즉, 다음 시간에 물이 찰 예정인 칸으로 고슴도치는 이동할 수 없다. 이동할 수 있으면 고슴도치가 물에 빠지기 때문이다. 
 * 
 * 
 * 입력
 * 첫째 줄에 50보다 작거나 같은 자연수 R과 C가 주어진다.
 * 다음 R개 줄에는 티떱숲의 지도가 주어지며, 문제에서 설명한 문자만 주어진다. 'D'와 'S'는 하나씩만 주어진다.
 * 
 * 출력
 * 첫째 줄에 고슴도치가 비버의 굴로 이동할 수 있는 가장 빠른 시간을 출력한다. 만약, 안전하게 비버의 굴로 이동할 수 없다면, "KAKTUS"를 출력한다.
 * 
 * 
 * 예제 입력 1
 * 3 3
 * D.*
 * ...
 * .S.
 * 
 * 예제 출력 1
 * 3
 * 
 * 
 * 예제 입력 2
 * 3 3
 * D.*
 * ...
 * ..S
 * 
 * 예제 출력 2
 * KAKTUS
 * 
 * 
 * 예제 입력 3
 * 3 6
 * D...*.
 * .X.X..
 * ....S.
 * 
 * 예제 출력 3
 * 6
 * 
 * 
 * 예제 입력 4
 * 5 4
 * .D.*
 * ....
 * ..X.
 * S.*.
 * ....
 * 
 * 예제 출력 4
 * 4
 */
public class P3055 {
	// up, down, right, left
	static final int[] ROW_MOVE = {-1, 1, 0, 0};
	static final int[] COL_MOVE = {0, 0, 1, -1};

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int r = scan.nextInt();
		int c = scan.nextInt();
		scan.nextLine();
		
		char[][] forest = new char[r][c];
		Position hedgehogStart = null;
		int destRow = -1;
		int destCol = -1;
		
		Queue<Position> waterQueue = new LinkedList<>();
		boolean[][] isFlood = new boolean[r][c];
		int[][] floodTime = new int[r][c];
		
		for(int row=0; row<r; row++) {
			char[] inputRow = scan.nextLine().toCharArray();
			for(int col=0; col<c; col++) {
				char input = inputRow[col];
				forest[row][col] = input;
				// 기본적으로 홍수시간은 전부 불가능한 경우인 -1로 초기화
				// 아래와 같은 경우 X나 D가 아니더라도 물이 갈 수 없는 위치가 존재 
				// S    D
				// XXXXXX
				// *.....
				floodTime[row][col] = -1;
				
				if(input == 'S') {
					hedgehogStart = new Position(row, col);
				}else if(input == 'D') {
					destRow = row;
					destCol = col;
				}else if(input == '*') {
					// 홍수 시작점인 경우 큐에 추가하고 홍수시간을 0으로 초기화
					waterQueue.add(new Position(row, col));
					isFlood[row][col] = true;
					floodTime[row][col] = 0;
				}
			}
		}
		scan.close();
		// input end
		
		
		// 물 확장 시간을 계산
		while(!waterQueue.isEmpty()) {
			Position nowPosition = waterQueue.remove();
			int nowRow = nowPosition.row;
			int nowCol = nowPosition.col;
			
			for(int index=0; index<4; index++) {
				int nextRow = nowRow + P3055.ROW_MOVE[index];
				int nextCol = nowCol + P3055.COL_MOVE[index];
				
				if(nextRow<0 || nextRow>=r || nextCol<0 || nextCol>=c) {
					continue;
				}
				
				if(forest[nextRow][nextCol]=='X' || forest[nextRow][nextCol]=='D') {
					continue;
				}else if(!isFlood[nextRow][nextCol]) {
					waterQueue.add(new Position(nextRow, nextCol));
					isFlood[nextRow][nextCol] = true;
					floodTime[nextRow][nextCol] = floodTime[nowRow][nowCol] + 1;
				}
			}
		}
		
		
		// 고슴도치 이동
		Queue<Position> hedgehogQueue = new LinkedList<>();
		boolean[][] isMoved = new boolean[r][c];
		int[][] moveTime = new int[r][c];
		
		moveTime[hedgehogStart.row][hedgehogStart.col] = 0;
		hedgehogQueue.add(hedgehogStart);
		isMoved[hedgehogStart.row][hedgehogStart.col] = true;
		
		while(!hedgehogQueue.isEmpty()) {
			Position nowPosition = hedgehogQueue.remove();
			int nowRow = nowPosition.row;
			int nowCol = nowPosition.col;
			
			for(int index=0; index<4; index++) {
				int nextRow = nowRow + P3055.ROW_MOVE[index];
				int nextCol = nowCol + P3055.COL_MOVE[index];
				
				if(nextRow<0 || nextRow>=r || nextCol<0 || nextCol>=c) {
					continue;
				}
				
				// 이동 불가한 경우
				if(forest[nextRow][nextCol] == 'X') {
					continue;
				}
				
				// 물이 올 수 있는 위치인데 홍수시간이 고슴도치 이동시간보다 짧은 경우 이동불가
				if(floodTime[nextRow][nextCol]!=-1 && moveTime[nowRow][nowCol]+1>=floodTime[nextRow][nextCol]) {
					continue;
				}
				
				if(!isMoved[nextRow][nextCol]) {
					hedgehogQueue.add(new Position(nextRow, nextCol));
					isMoved[nextRow][nextCol] = true;
					moveTime[nextRow][nextCol] = moveTime[nowRow][nowCol] + 1;
				}
			}
		}
		
		// 출력
		int hedgehogMoveTime = moveTime[destRow][destCol];
		if(hedgehogMoveTime > 0) {
			System.out.println(hedgehogMoveTime);
		}else {
			System.out.println("KAKTUS");
		}
	}
}

class Position{
	int row;
	int col;
	
	public Position() {
		
	}
	
	public Position(int row, int col) {
		this.row = row;
		this.col = col;
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
    public static int[] dx = {1, -1, 0, 0};
    public static int[] dy = {0, 0, 1, -1};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        sc.nextLine();
        String[] a = new String[n];
        int[][] water = new int[n][m];
        int[][] d = new int[n][m];
        for (int i=0; i<n; i++) {
            a[i] = sc.nextLine();
            for (int j=0; j<m; j++) {
                water[i][j] = -1;
                d[i][j] = -1;
            }
        }
        Queue<Pair> q = new LinkedList<Pair>();
        int sx=0,sy=0,ex=0,ey=0;
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                if (a[i].charAt(j) == '*') {
                    q.offer(new Pair(i, j));
                    water[i][j] = 0;
                } else if (a[i].charAt(j) == 'S') {
                    sx = i;
                    sy = j;
                } else if (a[i].charAt(j) == 'D') {
                    ex = i;
                    ey = j;
                }
            }
        }
        while (!q.isEmpty()) {
            Pair p = q.remove();
            int x = p.x;
            int y = p.y;
            for (int k=0; k<4; k++) {
                int nx = x+dx[k];
                int ny = y+dy[k];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }
                if (water[nx][ny] != -1) continue;
                if (a[nx].charAt(ny) == 'X') continue;
                if (a[nx].charAt(ny) == 'D') continue;
                water[nx][ny] = water[x][y] + 1;
                q.offer(new Pair(nx, ny));
            }
        }
        q.offer(new Pair(sx, sy));
        d[sx][sy] = 0;
        while (!q.isEmpty()) {
            Pair p = q.remove();
            int x = p.x;
            int y = p.y;
            for (int k=0; k<4; k++) {
                int nx = x+dx[k];
                int ny = y+dy[k];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }
                if (d[nx][ny] != -1) continue;
                if (a[nx].charAt(ny) == 'X') continue;
                if (water[nx][ny] != -1 && d[x][y]+1 >= water[nx][ny]) continue;

                d[nx][ny] = d[x][y] + 1;
                q.offer(new Pair(nx, ny));
            }
        }
        if (d[ex][ey] == -1) {
            System.out.println("KAKTUS");
        } else {
            System.out.println(d[ex][ey]);
        }
    }
}*/