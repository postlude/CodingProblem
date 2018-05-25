package baekjoon.algorithmCourse.etc;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 로봇 청소기
 * 
 * 문제
 * 오늘은 직사각형 모양의 방을 로봇 청소기를 이용해 청소하려고 한다. 이 로봇 청소기는 유저가 직접 경로를 설정할 수 있다.
 * 방은 크기가 1×1인 정사각형 칸으로 나누어져 있으며, 로봇 청소기의 크기도 1×1이다. 칸은 깨끗한 칸과 더러운 칸으로 나누어져 있으며, 로봇 청소기는 더러운 칸을 방문해서 깨끗한 칸으로 바꿀 수 있다.
 * 일부 칸에는 가구가 놓여져 있고, 가구의 크기도 1×1이다. 로봇 청소기는 가구가 놓여진 칸으로 이동할 수 없다. 
 * 로봇은 한 번 움직일 때, 인접한 칸으로 이동할 수 있다. 또, 로봇은 같은 칸을 여러 번 방문할 수 있다.
 * 방의 정보가 주어졌을 때, 더러운 칸을 모두 깨끗한 칸으로 만드는데 필요한 이동 횟수의 최소값을 구하는 프로그램을 작성하시오.
 * 
 * 
 * 입력
 * 입력은 여러 개의 테스트케이스로 이루어져 있다.
 * 각 테스트 케이스의 첫째 줄에는 방의 가로 크기 w와 세로 크기 h가 주어진다. (1 ≤ w, h ≤ 20) 둘째 줄부터 h개의 줄에는 방의 정보가 주어진다. 방의 정보는 4가지 문자로만 이루어져 있으며, 각 문자의 의미는 다음과 같다.
 * 
 * .: 깨끗한 칸
 * *: 더러운 칸
 * x: 가구
 * o: 로봇 청소기의 시작 위치
 * 
 * 더러운 칸의 개수는 10개를 넘지 않으며, 로봇 청소기의 개수는 항상 하나이다.
 * 입력의 마지막 줄에는 0이 두 개 주어진다.
 * 
 * 출력
 * 각각의 테스트 케이스마다 더러운 칸을 모두 깨끗한 칸으로 바꾸는 이동 횟수의 최소값을 한 줄에 하나씩 출력한다. 만약, 방문할 수 없는 더러운 칸이 존재하는 경우에는 -1을 출력한다.
 * 
 * 
 * 예제 입력 1
 * 7 5
 * .......
 * .o...*.
 * .......
 * .*...*.
 * .......
 * 15 13
 * .......x.......
 * ...o...x....*..
 * .......x.......
 * .......x.......
 * .......x.......
 * ...............
 * xxxxx.....xxxxx
 * ...............
 * .......x.......
 * .......x.......
 * .......x.......
 * ..*....x....*..
 * .......x.......
 * 10 10
 * ..........
 * ..o.......
 * ..........
 * ..........
 * ..........
 * .....xxxxx
 * .....x....
 * .....x.*..
 * .....x....
 * .....x....
 * 0 0
 * 
 * 예제 출력 1
 * 8
 * 49
 * -1
 */
public class P4991 {
	// up, down, right, left
	static final int[] ROW_MOVE = {-1, 1, 0, 0};
	static final int[] COL_MOVE = {0, 0, 1, -1};
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		P4991 p4991 = new P4991();
		
		while(true) {
			int w = scan.nextInt();
			int h = scan.nextInt();
			
			// 종료 조건
			if(w==0 && h==0) {
				break;
			}
			
			ArrayList<Position> dirtPosition = new ArrayList<>();
			// 지저분한 칸 개수
			int dirtCount = 0;
			
			char[][] room = new char[h][w];
			int roboStartRow = -1;
			int roboStartCol = -1;
			
			for(int row=0; row<h; row++) {
				char[] input = scan.next().toCharArray();
				for(int col=0; col<w; col++) {
					room[row][col] = input[col];
					if(input[col] == '*') {
						dirtPosition.add(new Position(row, col));
						dirtCount++;
					}else if(input[col] == 'o') {
						roboStartRow = row;
						roboStartCol = col;
					}
				}
			}
			// input end
			
			
			// 로봇청소기 시작 위치에서의 bfs
			int[][] roboBfs = p4991.bfs(room, new Position(roboStartRow, roboStartCol), w, h);
			
			// 더러운 칸에서 시작하는 모든 bfs
			LinkedList<int[][]> bfsList = new LinkedList<>();
			for(int index=0; index<dirtCount; index++) {
				Position dirt = dirtPosition.get(index);
				bfsList.add(p4991.bfs(room, dirt, w, h));
			}
			
			// index를 뽑아서 사용할 배열
			int[] dirtNum = new int[dirtCount];
			for(int index=0; index<dirtCount; index++) {
				dirtNum[index] = index;
			}
			
			int minMoveCount = w*h;
			
			OUTER_LOOP :
			do {
				// 로봇청소기 -> 처음 청소할 더러운 칸
				Position firstDirt = dirtPosition.get(dirtNum[0]);
				int moveCount = roboBfs[firstDirt.row][firstDirt.col];
				
				for(int index=0; index<dirtCount-1; index++) {
					// 현재 위치해 있는 더러운 칸의 index 
					int nowDirtIndex = dirtNum[index];
					// 다음으로 이동할 더러운 칸의 index
					int nextDirtIndex = dirtNum[index+1];
					
					// 현 위치에서 출발하는 bfs
					int[][] nowBfs = bfsList.get(nowDirtIndex);
					// 다음 이동할 더러운 칸의 위치
					Position nextDirt = dirtPosition.get(nextDirtIndex);
					
					// 현재 위치에서 이동할 수 없는 칸이면
					if(nowBfs[nextDirt.row][nextDirt.col] == 0) {
						minMoveCount = 0;
						break OUTER_LOOP;
					}else {
						// 이동할 수 있으면 이동 횟수 추가
						moveCount += nowBfs[nextDirt.row][nextDirt.col];
					}
				}
				
				if(moveCount < minMoveCount) {
					minMoveCount = moveCount;
				}
			}while(p4991.nextPermutation(dirtNum));
			
			if(minMoveCount != 0) {
				System.out.println(minMoveCount);
			}else {
				System.out.println(-1);
			}
		}
		scan.close();
	}

	public int[][] bfs(char[][] room, Position start, int w, int h) {
		Queue<Position> queue = new LinkedList<>();
		boolean[][] isVisited = new boolean[h][w];
		int[][] moveCount = new int[h][w];
		
		queue.add(start);
		isVisited[start.row][start.col] = true;
		
		while(!queue.isEmpty()) {
			Position nowPosition = queue.remove();
			int nowRow = nowPosition.row;
			int nowCol = nowPosition.col;
					
			for(int index=0; index<4; index++) {
				int nextRow = nowRow + P4991.ROW_MOVE[index];
				int nextCol = nowCol + P4991.COL_MOVE[index];
				
				if(nextRow<0 || nextRow>=h || nextCol<0 || nextCol>=w || room[nextRow][nextCol]=='x') {
					continue;
				}
				
				if(!isVisited[nextRow][nextCol]) {
					queue.add(new Position(nextRow, nextCol));
					isVisited[nextRow][nextCol] = true;
					moveCount[nextRow][nextCol] = moveCount[nowRow][nowCol] + 1;
				}
			}
		}
		
		return moveCount;
	}
	
	public boolean nextPermutation(int[] permutationArray) {
		int sizeOfAry = permutationArray.length;
		
		//부호 방향이 < 인 순간을 찾을 index
		int index = sizeOfAry - 1;
		//맨 뒤에서부터 이전 값과 비교하여 현재 값이 더 커지는 순간에 while 종료
		while(index>0 && permutationArray[index-1]>=permutationArray[index]) {
			index--;
		}
		//while이 끝까지 돌았다는 것은 제일 마지막 순열이라는 의미이므로 다음 순열이 존재하지 않는다.
		if(index == 0) {
			return false;
		}
		
		//permutationArray[index-1] 보다 크면서 가장 작은 수의 index
		int swapIndex = sizeOfAry - 1;
		while(permutationArray[index-1] >= permutationArray[swapIndex]) {
			swapIndex--;
		}
		
		//index-1의 값과 swapIndex의 값을 swap
		int temp = permutationArray[index-1];
		permutationArray[index-1] = permutationArray[swapIndex];
		permutationArray[swapIndex] = temp;
		
		//permutationArray의 index부터 마지막까지 값의 순서를 거꾸로
		int endIndex = sizeOfAry - 1;
		while(index < endIndex) {
			//swap
			temp = permutationArray[index];
			permutationArray[index] = permutationArray[endIndex];
			permutationArray[endIndex] = temp;
			
			//index 변경
			index++;
			endIndex--;
		}
		return true;
	}
}

class Position {
	int row;
	int col;
	
	public Position(int row, int col) {
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
    static int[][] bfs(String[] a, int sx, int sy) {
        int n = a.length;
        int m = a[0].length();
        int[][] dist = new int[n][m];
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                dist[i][j] = -1;
            }
        }
        Queue<Pair> q = new LinkedList<Pair>();
        q.add(new Pair(sx,sy));
        dist[sx][sy] = 0;
        while (!q.isEmpty()) {
            Pair p = q.remove();
            int x = p.first;
            int y = p.second;
            for (int k=0; k<4; k++) {
                int nx = x+dx[k];
                int ny = y+dy[k];
                if (0 <= nx && nx < n && 0 <= ny && ny < m) {
                    if (dist[nx][ny] == -1 && a[nx].charAt(ny) != 'x') {
                        dist[nx][ny] = dist[x][y] + 1;
                        q.add(new Pair(nx,ny));
                    }
                }
            }
        }
        return dist;
    }
static boolean next_permutation(int[] a) {
        int i = a.length-1;
        while (i > 0 && a[i-1] >= a[i]) {
            i -= 1;
        }
        if (i <= 0) {
            return false;
        }
        int j = a.length-1;
        while (a[j] <= a[i-1]) {
            j -= 1;
        }
        int temp = a[i-1];
        a[i-1] = a[j];
        a[j] = temp;
        j = a.length-1;
        while (i < j) {
            temp = a[i];
            a[i] = a[j];
            a[j] = temp;
            i += 1;
            j -= 1;
        }
        return true;
    }
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            int m = sc.nextInt();
            int n = sc.nextInt();
            if (n == 0 && m == 0) break;
            String[] a = new String[n];
            for (int i=0; i<n; i++) {
                a[i] = sc.next();
            }
            ArrayList<Pair> b = new ArrayList<>();
            b.add(new Pair(0,0));
            for (int i=0; i<n; i++) {
                for (int j=0; j<m; j++) {
                    char x = a[i].charAt(j);
                    if (x == 'o') {
                        b.set(0, new Pair(i,j));
                    } else if (x == '*') {
                        b.add(new Pair(i,j));
                    }
                }
            }
            int l = b.size();
            int[][] d = new int[l][l];
            boolean ok = true;
            for (int i=0; i<l; i++) {
                int[][] dist = bfs(a, b.get(i).first, b.get(i).second);
                for (int j=0; j<l; j++) {
                    d[i][j] = dist[b.get(j).first][b.get(j).second];
                    if (d[i][j] == -1) {
                        ok = false;
                    }
                }
            }
            if (ok == false) {
                System.out.println(-1);
                continue;
            }
            int[] p = new int[l-1];
            for (int i=0; i<l-1; i++) {
                p[i] = i+1;
            }
            int ans = -1;
            do {
                int now = d[0][p[0]];
                for (int i=0; i<l-2; i++) {
                    now += d[p[i]][p[i+1]];
                }
                if (ans == -1 || ans > now) {
                    ans = now;
                }
            } while(next_permutation(p));
            System.out.println(ans);
        }
    }
}
*/