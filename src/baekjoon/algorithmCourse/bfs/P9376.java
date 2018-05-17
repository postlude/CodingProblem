package baekjoon.algorithmCourse.bfs;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 탈옥
 * 
 * 문제
 * 상근이는 감옥에서 죄수 두 명을 탈옥시켜야 한다. 이 감옥은 1층짜리 건물이고, 상근이는 방금 평면도를 얻었다.
 * 평면도에는 모든 벽과 문이 나타나있고, 탈옥시켜야 하는 죄수의 위치도 나타나 있다. 감옥은 무인 감옥으로 죄수 두 명이 감옥에 있는 유일한 사람이다.
 * 문은 중앙 제어실에서만 열 수 있다. 상근이는 특별한 기술을 이용해 제어실을 통하지 않고 문을 열려고 한다. 하지만, 문을 열려면 시간이 매우 많이 걸린다.
 * 두 죄수를 탈옥시키기 위해서 열어야 하는 문의 개수를 구하는 프로그램을 작성하시오.
 * 
 * 
 * 입력
 * 첫째 줄에 테스트 케이스의 개수가 주어진다. 테스트 케이스의 수는 100개를 넘지 않는다.
 * 첫째 줄에는 평면도의 높이 h와 너비 w가 주어진다. (2 ≤ h, w ≤ 100) 다음 h개 줄에는 감옥의 평면도 정보가 주어지며, 빈 공간은 '.', 지나갈 수 없는 벽은 '*', 문은 '#', 죄수의 위치는 '$'이다.
 * 상근이는 감옥 밖을 자유롭게 이동할 수 있고, 평면도에 표시된 죄수의 수는 항상 두 명이다. 각 죄수와 감옥의 바깥을 연결하는 경로가 항상 존재하는 경우만 입력으로 주어진다.
 * 
 * 출력
 * 각 테스트 케이스마다 두 죄수를 탈옥시키기 위해서 열어야 하는 문의 최소값을 출력한다.
 * 
 * 
 * 예제 입력 1
 * 3
 * 5 9
 * ****#****
 * *..#.#..*
 * ****.****
 * *$#.#.#$*
 * *********
 * 5 11
 * *#*********
 * *$*...*...*
 * *$*.*.*.*.*
 * *...*...*.*
 * *********.*
 * 9 9
 * *#**#**#*
 * *#**#**#*
 * *#**#**#*
 * *#**.**#*
 * *#*#.#*#*
 * *$##*##$*
 * *#*****#*
 * *.#.#.#.*
 * *********
 * 
 * 예제 출력 1
 * 4
 * 0
 * 9
 */
/*
 * 탈옥이 입력 범위를 벗어나는 경우를 의미하므로 입력 범위에 .(빈공간)으로 한줄을 더 둘러서 계산
 * 
 * 탈옥수1, 탈옥수2가 도착지점으로 갈 때 중간 지점에서 반드시 만나게 된다(도착지점에서라도 반드시 만나게 됨) 
 * 탈옥수1 -> 중간지점 -> 도착지점
 * 탈옥수2 ↗
 * 
 * 이걸 반대로 생각하면
 * 탈옥수1 -> 중간지점 <- 도착지점
 * 탈옥수2 ↗
 * 이렇게 생각하는 것과 같음
 * 
 * 도착지점은 외부에 추가한 지점 중 하나인데 어차피 외부에는 문이 없으므로 (0, 0)으로 고정해서 생각해도 됨
 */
public class P9376 {
	// up, down, right, left
	static final int[] ROW_MOVE = {-1, 1, 0, 0};
	static final int[] COL_MOVE = {0, 0, 1, -1};
		
	public static void main(String[] args) {
		P9376 p9376 = new P9376();
		
		Scanner scan = new Scanner(System.in);
		int testNum = scan.nextInt();
		
		for(int test=0; test<testNum; test++) {
			int h = scan.nextInt();
			int w = scan.nextInt();
			
			String[] prison = new String[h+2];
			Position2[] prisoner = new Position2[2];
			int prisonerIndex = 0;
			for(int row=1; row<=h; row++) {
				String input = scan.next();
				// 양 끝쪽에 빈 공간 추가
				prison[row] = ".".concat(input).concat(".");
				for(int col=1; col<=w; col++) {
					if(prison[row].charAt(col) == '$') {
						prisoner[prisonerIndex] = new Position2(row, col);
						prisonerIndex++;
					}
				}
			}
			// 맨 위와 아래에 추가할 String
			String topBottomRow = "";
			for(int col=0; col<w+2; col++) {
				topBottomRow = topBottomRow.concat(".");
			}
			prison[0] = topBottomRow;
			prison[h+1] = topBottomRow;
			// input end
			
			h += 2;
			w += 2;
			
			int[][] prisoner1Bfs = p9376.bfs(prison, h, w, prisoner[0]);
			int[][] prisoner2Bfs = p9376.bfs(prison, h, w, prisoner[1]);
			int[][] destinationBfs = p9376.bfs(prison, h, w, new Position2(0, 0));
			
			// 결과 계산
			// 탈옥수 1, 2만 계산해서는 밖에 나가지 않는 점이 최소값이 될 수 있으므로
			// 도착지점에서 계산한 bfs에서 -2하는 것으로 계산
			int resultDoorNum = h * w;
			for(int row=0; row<h; row++) {
				for(int col=0; col<w; col++) {
					if(prison[row].charAt(col) == '*') {
						continue;
					}
					int doorNum = prisoner1Bfs[row][col] + prisoner2Bfs[row][col] + destinationBfs[row][col];
					if(prison[row].charAt(col) == '#') {
						doorNum -= 2;
					}

					if(doorNum < resultDoorNum) {
						resultDoorNum = doorNum;
					}
				}
			}
			System.out.println(resultDoorNum);
		}
		scan.close();
	}

	public int[][] bfs(String[] prison, int h, int w, Position2 prisoner) {
		ArrayDeque<Position2> deque = new ArrayDeque<>();
		int[][] distance = new int[h][w];
		
		deque.add(prisoner);
		
		// 벽으로 둘러싼 빈공간이 있는 경우를 위해 -1로 초기화
		// ex)
		// ***
		// *.*
		// ***
		for(int row=0; row<h; row++) {
			Arrays.fill(distance[row], -1);
		}
		distance[prisoner.row][prisoner.col] = 0;
		
		while(!deque.isEmpty()) {
			Position2 nowPosition = deque.poll();
			int nowRow = nowPosition.row;
			int nowCol = nowPosition.col;
			
			for(int index=0; index<4; index++) {
				int nextRow = nowRow + P9376.ROW_MOVE[index];
				int nextCol = nowCol + P9376.COL_MOVE[index];
				
				if(nextRow<0 || nextRow>=h || nextCol<0 || nextCol>=w) {
					continue;
				}
				// 벽인 경우 이동 불가
				if(prison[nextRow].charAt(nextCol) == '*') {
					continue;
				}
				
				// 벽인 경우는 위에서 걸러진다. 이동하지 않은 곳인 경우
				if(distance[nextRow][nextCol] == -1) {
					Position2 nextPosition = new Position2(nextRow, nextCol);
					
					if(prison[nextRow].charAt(nextCol) == '#') {
						// 문인 경우 덱의 마지막에 넣음
						distance[nextRow][nextCol] = distance[nowRow][nowCol] + 1;
						deque.add(nextPosition);
					}else {
						// 문이 아닌 경우 덱의 처음에 넣음
						distance[nextRow][nextCol] = distance[nowRow][nowCol];
						deque.addFirst(nextPosition);
					}
				}
			}
		}
		
		return distance;
	}

}

class Position2{
	int row;
	int col;
	
	public Position2(int row, int col) {
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
    public static int[][] bfs(String[] a, int x, int y) {
        int n = a.length;
        int m = a[0].length();
        int[][] d = new int[n][m];
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                d[i][j] = -1;
            }
        }
        ArrayDeque<Pair> deque = new ArrayDeque<Pair>();
        deque.add(new Pair(x, y));
        d[x][y] = 0;
        while (!deque.isEmpty()) {
            Pair p = deque.poll();
            x = p.x;
            y = p.y;
            for (int k=0; k<4; k++) {
                int nx = x+dx[k];
                int ny = y+dy[k];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if (d[nx][ny] != -1) continue;
                char c = a[nx].charAt(ny);
                if (c == '*') continue;
                if (c == '#') {
                    d[nx][ny] = d[x][y] + 1;
                    deque.addLast(new Pair(nx, ny));
                } else {
                    d[nx][ny] = d[x][y];
                    deque.addFirst(new Pair(nx, ny));
                }
            }
        }
        return d;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            sc.nextLine();
            String[] a = new String[n+2];
            for (int i=1; i<=n; i++) {
                a[i] = sc.nextLine();
                a[i] = "." + a[i] + ".";
            }
            n += 2;
            m += 2;
            a[0] = a[n-1] = "";
            for (int j=0; j<m; j++) {
                a[0] += ".";
                a[n-1] += ".";
            }
            int[][] d0 = bfs(a, 0, 0);
            int x1, y1, x2, y2;
            x1 = y1 = x2 = y2 = -1;
            for (int i=0; i<n; i++) {
                for (int j=0; j<m; j++) {
                    if (a[i].charAt(j) == '$') {
                        if (x1 == -1) {
                            x1 = i;
                            y1 = j;
                        } else {
                            x2 = i;
                            y2 = j;
                        }
                    }
                }
            }
            int[][] d1 = bfs(a, x1, y1);
            int[][] d2 = bfs(a, x2, y2);
            int ans = n*m;
            for (int i=0; i<n; i++) {
                for (int j=0; j<m; j++) {
                    char c = a[i].charAt(j);
                    if (c == '*') continue;
                    int cur = d0[i][j] + d1[i][j] + d2[i][j];
                    if (c == '#') cur -= 2;
                    if (ans > cur) ans = cur;
                }
            }
            System.out.println(ans);
        }
    }
}*/