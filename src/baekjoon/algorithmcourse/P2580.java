package baekjoon.algorithmcourse;

import java.util.Scanner;

/**
 * 스도쿠
 * 
 * 문제
 * 스도쿠는 18세기 스위스 수학자가 만든 '라틴 사각형'이랑 퍼즐에서 유래한 것으로 현재 많은 인기를 누리고 있다.
 * 이 게임은 아래 그림과 같이 가로, 세로 각각 9개씩 총 81개의 작은 칸으로 이루어진 정사각형 판 위에서 이뤄지는데, 게임 시작 전 몇 몇 칸에는 1부터 9까지의 숫자 중 하나가 쓰여 있다.
 * 나머지 빈 칸을 채우는 방식은 다음과 같다.
 * 
 * 각각의 가로줄과 세로줄에는 1부터 9까지의 숫자가 한 번씩만 나타나야 한다.
 * 굵은 선으로 구분되어 있는 3x3 정사각형 안에도 1부터 9까지의 숫자가 한 번씩만 나타나야 한다.
 * 위의 예의 경우, 첫째 줄에는 1을 제외한 나머지 2부터 9까지의 숫자들이 이미 나타나 있으므로 첫째 줄 빈칸에는 1이 들어가야 한다.
 * 
 * 또한 위쪽 가운데 위치한 3x3 정사각형의 경우에는 3을 제외한 나머지 숫자들이 이미 쓰여있으므로 가운데 빈 칸에는 3이 들어가야 한다.
 * 
 * 이와 같이 빈 칸을 차례로 채워 가면 다음과 같은 최종 결과를 얻을 수 있다.
 * 게임 시작 전 스도쿠 판에 쓰여 있는 숫자들의 정보가 주어질 때 모든 빈 칸이 채워진 최종 모습을 출력하는 프로그램을 작성하시오.
 * 
 * 입력
 * 아홉 줄에 걸쳐 한 줄에 9개씩 게임 시작 전 스도쿠판 각 줄에 쓰여 있는 숫자가 한 칸씩 띄워서 차례로 주어진다. 스도쿠 판의 빈 칸의 경우에는 0이 주어진다. 스도쿠 판을 규칙대로 채울 수 없는 경우의 입력은 주어지지 않는다.
 * 
 * 출력
 * 모든 빈 칸이 채워진 스도쿠 판의 최종 모습을 아홉줄에 걸쳐 한 줄에 9개씩 한 칸씩 띄워서 출력한다.
 * 스도쿠 판을 채우는 방법이 여럿인 경우는 그 중 하나만을 출력한다.
 * 
 * 
 * 예제 입력 1
 * 0 3 5 4 6 9 2 7 8
 * 7 8 2 1 0 5 6 0 9
 * 0 6 0 2 7 8 1 3 5
 * 3 2 1 0 4 6 8 9 7
 * 8 0 4 9 1 3 5 0 6
 * 5 9 6 8 2 0 4 1 3
 * 9 1 7 6 5 2 0 8 0
 * 6 0 3 7 0 1 9 5 2
 * 2 5 8 3 9 4 7 6 0
 * 
 * 
 * 예제 출력 1
 * 1 3 5 4 6 9 2 7 8
 * 7 8 2 1 3 5 6 4 9
 * 4 6 9 2 7 8 1 3 5
 * 3 2 1 5 4 6 8 9 7
 * 8 7 4 9 1 3 5 2 6
 * 5 9 6 8 2 7 4 1 3
 * 9 1 7 6 5 2 3 8 4
 * 6 4 3 7 8 1 9 5 2
 * 2 5 8 3 9 4 7 6 1
 * 
 */
public class P2580 {
	static int[][] SUDOKU = new int[9][9];  
	static boolean[][] CHECK_ROW = new boolean[9][9];
	static boolean[][] CHECK_COL= new boolean[9][9];
	static boolean[][] CHECK_SQUARE = new boolean[9][9];
	
	static int NUM_OF_ZERO = 0;
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		for(int row=0; row<9; row++) {
			for(int col=0; col<9; col++) {
				int num = scan.nextInt();
				P2580.SUDOKU[row][col] = num;
				
				if(num != 0) {
					P2580.CHECK_ROW[row][num-1] = true;
					P2580.CHECK_COL[num-1][col] = true;
					P2580.CHECK_SQUARE[row/3*3+col/3][num-1] = true;
				}else {
					P2580.NUM_OF_ZERO++;
				}
			}
		}
		scan.close();
		// input end
		
		
		P2580 p2580 = new P2580();
		p2580.solveSudoku(0);
	}

	public void printArray() {
		for(int row=0; row<9; row++) {
			for(int col=0; col<9; col++) {
				System.out.print(P2580.SUDOKU[row][col] + " ");
			}
			System.out.println();
		}
	}
	
	public boolean isCollect(int row, int col, int num) {
		// 가로
		if(P2580.CHECK_ROW[row][num-1]) {
			return false;
		}
		
		// 세로
		if(P2580.CHECK_COL[num-1][col]) {
			return false;
		}
		
		// 작은 사각형
		if(P2580.CHECK_SQUARE[row/3*3+col/3][num-1]) {
			return false;
		}
		
		return true;
	}
	
	public boolean solveSudoku(int point) {
		if(point == 81) {
			printArray();
			return true;
		}
		
		int row = point / 9;
		int col = point % 9;
		
		if(P2580.SUDOKU[row][col] != 0) {
			return solveSudoku(point+1);
		}
		
		for(int num=1; num<=9; num++) {
			if(isCollect(row, col, num)) {
				P2580.SUDOKU[row][col] = num;
				P2580.CHECK_ROW[row][num-1] = true;
				P2580.CHECK_COL[num-1][col] = true;
				P2580.CHECK_SQUARE[row/3*3+col/3][num-1] = true;
				
				if(!solveSudoku(point+1)) {
					P2580.SUDOKU[row][col] = 0;
					P2580.CHECK_ROW[row][num-1] = false;
					P2580.CHECK_COL[num-1][col] = false;
					P2580.CHECK_SQUARE[row/3*3+col/3][num-1] = false;
				}
			}
		}
		return false;
	}
}



// 정답 코드
/*import java.util.*;

public class Main {
    public static final int n = 9;
    public static int square(int x, int y) {
        return (x/3)*3+(y/3);
    }
    public static boolean go(int[][] a, boolean[][][] c, int z) {
        if (z == 81) {
            for (int i=0; i<n; i++) {
                for (int j=0; j<n; j++) {
                    System.out.print(a[i][j] + " ");
                }
                System.out.println();
            }
            return true;
        }
        int x = z/n;
        int y = z%n;
        if (a[x][y] != 0) {
            return go(a, c, z+1);
        } else {
            for (int i=1; i<=9; i++) {
                if (!c[0][x][i] && !c[1][y][i] && !c[2][square(x,y)][i]) {
                    c[0][x][i] = c[1][y][i] = c[2][square(x,y)][i] = true;
                    a[x][y] = i;
                    if (go(a, c, z+1)) {
                        return true;
                    }
                    a[x][y] = 0;
                    c[0][x][i] = c[1][y][i] = c[2][square(x,y)][i] = false;
                }
            }
        }
        return false;
    }
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int[][] a = new int[n][n];
        boolean[][][] c = new boolean[3][n][10];
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                a[i][j] = sc.nextInt();
                if (a[i][j] != 0) {
                    c[0][i][a[i][j]] = true;
                    c[1][j][a[i][j]] = true;
                    c[2][square(i,j)][a[i][j]] = true;
                }
            }
        }
        go(a, c, 0);
    }
}*/
