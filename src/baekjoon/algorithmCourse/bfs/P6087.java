package baekjoon.algorithmCourse.bfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 레이저 통신
 * 
 * 문제
 * 크기가 1×1인 정사각형으로 나누어진 W×H 크기의 지도가 있다. 지도의 각 칸은 빈 칸이거나 벽이며, 두 칸은 'C'로 표시되어 있는 칸이다.
 * 'C'로 표시되어 있는 두 칸을 레이저로 통신하기 위해서 설치해야 하는 거울 개수의 최소값을 구하는 프로그램을 작성하시오. 레이저로 통신한다는 것은 두 칸을 레이저로 연결할 수 있음을 의미한다.
 * 레이저는 C에서만 발사할 수 있고, 빈 칸에 거울('/', '\')을 설치해서 방향을 90도 회전시킬 수 있다. 
 * 아래 그림은 H = 8, W = 7인 경우이고, 빈 칸은 '.', 벽은 '*'로 나타냈다. 왼쪽은 초기 상태, 오른쪽은 최소 개수의 거울을 사용해서 두 'C'를 연결한 것이다.
 * 
 * 7 . . . . . . .         7 . . . . . . .
 * 6 . . . . . . C         6 . . . . . /-C
 * 5 . . . . . . *         5 . . . . . | *
 * 4 * * * * * . *         4 * * * * * | *
 * 3 . . . . * . .         3 . . . . * | .
 * 2 . . . . * . .         2 . . . . * | .
 * 1 . C . . * . .         1 . C . . * | .
 * 0 . . . . . . .         0 . \-------/ .
 *   0 1 2 3 4 5 6           0 1 2 3 4 5 6
 * 
 * 
 * 입력
 * 첫째 줄에 W와 H가 주어진다. (1 ≤ W, H ≤ 100)
 * 둘째 줄부터 H개의 줄에 지도가 주어진다. 지도의 각 문자가 의미하는 것은 다음과 같다.
 * 
 * .: 빈 칸
 * *: 벽
 * C: 레이저로 연결해야 하는 칸
 * 'C'는 항상 두 개이고, 레이저로 연결할 수 있는 입력만 주어진다.
 * 
 * 출력
 * 첫째 줄에 C를 연결하기 위해 설치해야 하는 거울 개수의 최소값을 출력한다.
 * 
 * 
 * 예제 입력 1
 * 7 8
 * .......
 * ......C
 * ......*
 * *****.*
 * ....*..
 * ....*..
 * .C..*..
 * .......
 * 
 * 예제 출력 1
 * 3
 */
public class P6087 {
	// up, down, right, left
	static final int[] ROW_MOVE = {-1, 1, 0, 0};
	static final int[] COL_MOVE = {0, 0, 1, -1};
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int w = scan.nextInt();
		int h = scan.nextInt();
		
		Position4[] laserPosition = new Position4[2];
		int laserIndex = 0;
		
		char[][] map = new char[h][w];
		for(int row=0; row<h; row++) {
			char[] input = scan.next().toCharArray();
			for(int col=0; col<w; col++) {
				map[row][col] = input[col];
				if(input[col] == 'C') {
					laserPosition[laserIndex] = new Position4(row, col);
					laserIndex++;
				}
			}
		}
		scan.close();
		// input end
		
		
		Queue<Position4> queue = new LinkedList<>();
		boolean[][] isVisited = new boolean[h][w];
		int[][] mirrorCount = new int[h][w];
		
		Position4 startPosition = laserPosition[0]; 
		queue.add(startPosition);
		isVisited[startPosition.row][startPosition.col] = true;
		
		// 시작 위치에서 -1 로 시작해야 다음 이동하는 거울 갯수가 0으로 계산 된다.
		mirrorCount[startPosition.row][startPosition.col] = -1;
		
		while(!queue.isEmpty()) {
			Position4 nowPosition = queue.remove();
			int nowRow = nowPosition.row;
			int nowCol = nowPosition.col;
			
			for(int index=0; index<4; index++) {
				int nextRow = nowRow + P6087.ROW_MOVE[index];
				int nextCol = nowCol + P6087.COL_MOVE[index];
				
				// 다음 위치로 이동할 수 있는 점들을 4개만 queue에 추가하는 것이 아니라
				// 일직선으로 이동할 수 있는 모든 점들을 추가
				while(nextRow>=0 && nextRow<h && nextCol>=0 && nextCol<w) {
					if(map[nextRow][nextCol] == '*') {
						break;
					}
					
					if(!isVisited[nextRow][nextCol]) {
						queue.add(new Position4(nextRow, nextCol));
						isVisited[nextRow][nextCol] = true;
						mirrorCount[nextRow][nextCol] = mirrorCount[nowRow][nowCol] + 1;
					}
					
					nextRow = nextRow + P6087.ROW_MOVE[index];
					nextCol = nextCol + P6087.COL_MOVE[index];
				}
			}
		}
		
		Position4 endPosition = laserPosition[1];
		System.out.println(mirrorCount[endPosition.row][endPosition.col]);
	}
}

class Position4{
	int row;
	int col;
	
	public Position4(int row, int col) {
		this.row = row;
		this.col = col;
	}
}


// 정답 코드
/*import java.util.*;
class Pair {
    int first;
    int second;
    Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }
}
public class Main {
    static final int[] dx = {0,0,1,-1};
    static final int[] dy = {1,-1,0,0};
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        String[] a = new String[n];
        int sx,sy,ex,ey;
        sx=sy=ex=ey=-1;
        for (int i=0; i<n; i++) {
            a[i] = sc.next();
            for (int j=0; j<m; j++) {
                if (a[i].charAt(j) == 'C') {
                    if (sx == -1) {
                        sx = i;
                        sy = j;
                    } else {
                        ex = i;
                        ey = j;
                    }
                }
            }
        }
        int[][] d = new int[n][m];
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                d[i][j] = -1;
            }
        }
        Queue<Pair> q = new LinkedList<Pair>();
        q.add(new Pair(sx,sy));
        d[sx][sy] = 0;
        while (!q.isEmpty()) {
            Pair p = q.remove();
            int x = p.first;
            int y = p.second;
            for (int k=0; k<4; k++) {
                int nx = x+dx[k];
                int ny = y+dy[k];
                while (0 <= nx && nx < n && 0 <= ny && ny < m) {
                    if (a[nx].charAt(ny) == '*') break;
                    if (d[nx][ny] == -1) {
                        d[nx][ny] = d[x][y] + 1;
                        q.add(new Pair(nx,ny));
                    }
                    nx += dx[k];
                    ny += dy[k];
                }
            }
        }
        System.out.println(d[ex][ey]-1);
    }
}
*/