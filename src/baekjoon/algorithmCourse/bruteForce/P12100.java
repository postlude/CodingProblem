package baekjoon.algorithmCourse.bruteForce;

import java.util.Scanner;

/**
 * 2048 (Easy)
 * 
 * 문제
 * 2048 게임은 4×4 크기의 보드에서 혼자 즐기는 재미있는 게임이다. 이 링크를 누르면 게임을 해볼 수 있다.
 * 이 게임에서 한 번의 이동은 보드 위에 있는 전체 블럭을 상하좌우 네 방향 중 하나로 이동시키는 것이다. 이 때, 같은 값을 갖는 두 블럭이 충돌하면 두 블럭은 하나로 합쳐지게 된다.
 * 한 번의 이동에서 이미 합쳐진 블럭은 또 다른 블럭과 다시 합쳐질 수 없다. (실제 게임에서는 이동을 한 번 할 때마다 블럭이 추가되지만, 이 문제에서 블럭이 추가되는 경우는 없다)
 * 
 * 
 * 마지막으로, 똑같은 수가 세 개가 있는 경우에는 이동하려고 하는 쪽의 칸이 먼저 합쳐진다. 예를 들어, 위로 이동시키는 경우에는 위쪽에 있는 블럭이 먼저 합쳐지게 된다.
 * 이 문제에서 다루는 2048 게임은 보드의 크기가 N×N 이다. 보드의 크기와 보드판의 블럭 상태가 주어졌을 때, 최대 5번 이동해서 만들 수 있는 가장 큰 블럭의 값을 구하는 프로그램을 작성하시오.
 * 
 * 
 * 입력
 * 첫째 줄에 보드의 크기 N (1 ≤ N ≤ 20)이 주어진다. 둘째 줄부터 N개의 줄에는 게임판의 초기 상태가 주어진다. 0은 빈 칸을 나타내며, 이외의 값은 모두 블럭을 나타낸다.
 * 블럭에 써있는 수는 2보다 크거나 같고, 1024보다 작거나 같은 2의 제곱꼴이다. 블럭은 적어도 하나 주어진다.
 * 
 * 
 * 출력
 * 최대 5번 이동시켜서 얻을 수 있는 가장 큰 블럭을 출력한다.
 * 
 * 
 * 예제 입력 1
 * 3
 * 2 2 2
 * 4 4 4
 * 8 8 8
 * 
 * 예제 출력 1
 * 16
 */
public class P12100 {
	// up, down, right, left
	static final int[] ROW_MOVE = {-1, 1, 0, 0};
	static final int[] COL_MOVE = {0, 0, 1, -1};
	static final int MOVE_LIMIT = 5;
	static int BOARD_SIZE;
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		P12100.BOARD_SIZE = scan.nextInt();
		
		int[][] originBoard = new int[P12100.BOARD_SIZE][P12100.BOARD_SIZE];
		for(int row=0; row<P12100.BOARD_SIZE; row++) {
			for(int col=0; col<P12100.BOARD_SIZE; col++) {
				originBoard[row][col] = scan.nextInt();
			}
		}
		scan.close();
		// input end
		
		
		P12100 p12100 = new P12100();
		int maxPoint = -1;
		
		for(int directionNum=0; directionNum<(1<<P12100.MOVE_LIMIT*2); directionNum++) {
			int[] directionAry = p12100.makeDirectionAry(directionNum);
			
			// 이동할 때 map이 바뀌므로 매번 새로운 board 생성
			int[][] checkBoard = new int[P12100.BOARD_SIZE][P12100.BOARD_SIZE];
			for(int row=0; row<P12100.BOARD_SIZE; row++) {
				for(int col=0; col<P12100.BOARD_SIZE; col++) {
					checkBoard[row][col] = originBoard[row][col];
				}
			}
			
			int currentMaxPoint = p12100.calcMaxPoint(checkBoard, directionAry);
			if(currentMaxPoint > maxPoint) {
				maxPoint = currentMaxPoint;
			}
		}
		
		System.out.println(maxPoint);
	}

	/**
	 * directionAry 방향으로 이동 후 최대 점수를 찾는 메소드
	 * @param checkBoard
	 * @param directionAry
	 * @return
	 */
	public int calcMaxPoint(int[][] checkBoard, int[] directionAry) {
		// directionAry 방향으로 전부 이동 후
		for(int direction : directionAry) {
			move(checkBoard, direction);
		}
		
		// checkBoard에서 최대 점수 계산
		int maxPoint = -1;
		for(int row=0; row<P12100.BOARD_SIZE; row++) {
			for(int col=0; col<P12100.BOARD_SIZE; col++) {
				if(checkBoard[row][col] > maxPoint) {
					maxPoint = checkBoard[row][col];
				}
			}
		}
		return maxPoint;
	}
	
	/**
	 * direction 방향으로 모든 점 이동시키는 메소드
	 * @param checkBoard
	 * @param direction
	 */
	public void move(int[][] checkBoard, int direction) { 
		boolean[][] isConflict = new boolean[P12100.BOARD_SIZE][P12100.BOARD_SIZE];
		switch(direction) {
			case 0 : {
				// up
				for(int col=0; col<P12100.BOARD_SIZE; col++) {
					for(int row=1; row<P12100.BOARD_SIZE; row++) {
						conflict(row, col, checkBoard, direction, isConflict);
					}
				}
				break;
			}
			case 1 : {
				// down
				for(int col=0; col<P12100.BOARD_SIZE; col++) {
					for(int row=P12100.BOARD_SIZE-2; row>=0; row--) {
						conflict(row, col, checkBoard, direction, isConflict);
					}
				}
				break;
			}
			case 2 : {
				// right
				for(int row=0; row<P12100.BOARD_SIZE; row++) {
					for(int col=P12100.BOARD_SIZE-2; col>=0; col--) {
						conflict(row, col, checkBoard, direction, isConflict);
					}
				}
				break;
			}
			case 3 : {
				//left
				for(int row=0; row<P12100.BOARD_SIZE; row++) {
					for(int col=1; col<P12100.BOARD_SIZE; col++) {
						conflict(row, col, checkBoard, direction, isConflict);
					}
				}
				break;
			}
		}
	}
	
	/**
	 * [row, col] 위치에 있는 점을 direction 방향으로 이동시키고 충돌할 경우 점수 계산하는 메소드
	 * @param row
	 * @param col
	 * @param checkBoard
	 * @param direction
	 * @param isConflict
	 */
	public void conflict(int row, int col, int[][] checkBoard, int direction, boolean[][] isConflict) {
		int nextRow = row + P12100.ROW_MOVE[direction];
		int nextCol = col + P12100.COL_MOVE[direction];
		
		// 합치기
		boolean isMoved = true;
		while(isMoved) {
			if(nextRow<0 || nextRow>=P12100.BOARD_SIZE || nextCol<0 || nextCol>=P12100.BOARD_SIZE) {
				break;
			}else if(checkBoard[nextRow][nextCol] == 0) {
				checkBoard[nextRow][nextCol] = checkBoard[row][col];
				checkBoard[row][col] = 0;
				row = nextRow;
				col = nextCol;
				nextRow += P12100.ROW_MOVE[direction];
				nextCol += P12100.COL_MOVE[direction];
			}else if(!isConflict[nextRow][nextCol] && checkBoard[row][col]==checkBoard[nextRow][nextCol]) {
				checkBoard[nextRow][nextCol] *= 2;
				checkBoard[row][col] = 0;
				isConflict[nextRow][nextCol] = true;
				break;
			}else {
				break;
			}
		}
	}
	
	/**
	 * 파라미터로 받은 숫자로 이동방향 배열 만드는 메소드
	 * @param directionNum
	 * @return
	 */
	public int[] makeDirectionAry(int directionNum) {
		int[] direction = new int[P12100.MOVE_LIMIT];
		
		for(int index=0; index<P12100.MOVE_LIMIT; index++) {
			// 상하좌우 이동의 index값(0~3)을 만들기 위해 3(2진수로 11)과 &연산 후 2bit씩 뺀다.
			int move = directionNum & 3;
			direction[index] = move;
			directionNum = directionNum >> 2;
		}
		
		return direction;
	}
}




// 정답 코드
/*import java.util.*;
public class Main {
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static final int LIMIT = 5;
    static int[] gen(int k) {
        int[] a = new int[LIMIT];
        for (int i=0; i<LIMIT; i++) {
            a[i] = (k&3);
            k >>= 2;
        }
        return a;
    }
    static int check(int[][] a, int[] dirs) {
        int n = a.length;
        int[][] d = new int[n][n];
        boolean[][] merged = new boolean[n][n];
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                d[i][j] = a[i][j];
            }
        }
        // 0: down, 1: up, 2: left, 3: right
        for (int dir : dirs) {
            boolean ok = false;
            for (int i=0; i<n; i++) {
                for (int j=0; j<n; j++) {
                    merged[i][j] = false;
                }
            }
            while (true) {
                ok = false;
                if (dir == 0) {
                    for (int i=n-2; i>=0; i--) {
                        for (int j=0; j<n; j++) {
                            if (d[i][j] == 0) continue;
                            if (d[i+1][j] == 0) {
                                d[i+1][j] = d[i][j];
                                merged[i+1][j] = merged[i][j];
                                d[i][j] = 0;
                                ok = true;
                            } else if (d[i+1][j] == d[i][j]) {
                                if (merged[i][j] == false && merged[i+1][j] == false) {
                                    d[i+1][j] *= 2;
                                    merged[i+1][j] = true;
                                    d[i][j] = 0;
                                    ok = true;
                                }
                            }
                        }
                    }
                } else if (dir == 1) {
                    for (int i=1; i<n; i++) {
                        for (int j=0; j<n; j++) {
                            if (d[i][j] == 0) continue;
                            if (d[i-1][j] == 0) {
                                d[i-1][j] = d[i][j];
                                merged[i-1][j] = merged[i][j];
                                d[i][j] = 0;
                                ok = true;
                            } else if (d[i-1][j] == d[i][j]) {
                                if (merged[i][j] == false && merged[i-1][j] == false) {
                                    d[i-1][j] *= 2;
                                    merged[i-1][j] = true;
                                    d[i][j] = 0;
                                    ok = true;
                                }
                            }
                        }
                    }
                } else if (dir == 2) {
                    for (int j=1; j<n; j++) {
                        for (int i=0; i<n; i++) {
                            if (d[i][j] == 0) continue;
                            if (d[i][j-1] == 0) {
                                d[i][j-1] = d[i][j];
                                merged[i][j-1] = merged[i][j];
                                d[i][j] = 0;
                                ok = true;
                            } else if (d[i][j-1] == d[i][j]) {
                                if (merged[i][j] == false && merged[i][j-1] == false) {
                                    d[i][j-1] *= 2;
                                    merged[i][j-1] = true;
                                    d[i][j] = 0;
                                    ok = true;
                                }
                            }
                        }
                    }
                } else if (dir == 3) {
                    for (int j=n-2; j>=0; j--) {
                        for (int i=0; i<n; i++) {
                            if (d[i][j] == 0) continue;
                            if (d[i][j+1] == 0) {
                                d[i][j+1] = d[i][j];
                                merged[i][j+1] = merged[i][j];
                                d[i][j] = 0;
                                ok = true;
                            } else if (d[i][j+1] == d[i][j]) {
                                if (merged[i][j] == false && merged[i][j+1] == false) {
                                    d[i][j+1] *= 2;
                                    merged[i][j+1] = true;
                                    d[i][j] = 0;
                                    ok = true;
                                }
                            }
                        }
                    }
                } 
                if (ok == false) break;
            }
        }
        int ans = 0;
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                if (ans < d[i][j]) {
                    ans = d[i][j];
                }
            }
        }
        return ans;
    }
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] a = new int[n][n];
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                a[i][j] = sc.nextInt();
            }
        }
        int ans = 0;
        for (int k=0; k<(1<<(LIMIT*2)); k++) {
            int[] dir = gen(k);
            int cur = check(a, dir);
            if (ans < cur) ans = cur;
        }
        System.out.println(ans);
    }
}
*/