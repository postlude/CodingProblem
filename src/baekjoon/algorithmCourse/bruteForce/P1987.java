package baekjoon.algorithmCourse.bruteForce;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 알파벳
 * 
 * 문제
 * 세로 R칸, 가로 C칸으로 된 표 모양의 보드가 있다. 보드의 각 칸에는 대문자 알파벳이 하나씩 적혀 있고, 좌측 상단 칸 (1행 1열) 에는 말이 놓여 있다.
 * 말은 상하좌우로 인접한 네 칸 중의 한 칸으로 이동할 수 있는데, 새로 이동한 칸에 적혀 있는 알파벳은 지금까지 지나온 모든 칸에 적혀 있는 알파벳과는 달라야 한다. 즉, 같은 알파벳이 적힌 칸을 두 번 지날 수 없다.
 * 좌측 상단에서 시작해서, 말이 최대한 몇 칸을 지날 수 있는지를 구하는 프로그램을 작성하시오. 말이 지나는 칸은 좌측 상단의 칸도 포함된다.
 * 
 * 입력
 * 첫째 줄에 R과 C가 빈칸을 사이에 두고 주어진다. (1<=R,C<=20) 둘째 줄부터 R개의 줄에 걸쳐서 보드에 적혀 있는 C개의 대문자 알파벳들이 빈칸 없이 주어진다.
 * 
 * 출력
 * 첫째 줄에 말이 지날 수 있는 최대의 칸 수를 출력한다.
 * 
 * 
 * 예제 입력 1
 * 2 4
 * CAAB
 * ADCB
 * 
 * 예제 출력 1
 * 3
 * 
 */
public class P1987 {
	static int R;
	static int C;
	static int MAX_PATH = 0;
	static char[][] BOARD;
	static ArrayList<Character> PATH = new ArrayList<>();
	/**
	 * 동일한 index의 PATH에 있는 문자가 상하좌우 순서로 이동이 가능한지를 저장하고 있는 리스트 
	 */
	static ArrayList<boolean[]> POSSIBLE_DIRECTION = new ArrayList<>();
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		P1987.R = scan.nextInt();
		P1987.C = scan.nextInt();
		P1987.BOARD = new char[R][C];
		
		for(int row=0; row<R; row++) {
			char[] chAry = scan.next().toCharArray();
			P1987.BOARD[row] = Arrays.copyOf(chAry, chAry.length);
		}
		scan.close();
		
		
		P1987 p1987 = new P1987();
		// (0, 0)에 있는 문자 미리 추가
		P1987.PATH.add(P1987.BOARD[0][0]);
		P1987.POSSIBLE_DIRECTION.add(new boolean[4]);
		
		p1987.calcMaxPath(0, 0, 0);
		System.out.println(P1987.MAX_PATH);
	}

	public void decidePossibleDirection(int row, int col, int index) {
		// 해당 index에 있는 boolean 배열을 true로 초기화
		for(int aryIndex=0; aryIndex<4; aryIndex++) {
			P1987.POSSIBLE_DIRECTION.get(index)[aryIndex] = true;
		}
		
		for(char letter : P1987.PATH) {
			// 순서대로 현재 위치에서 상하좌우로 이동이 가능한지를 검사
			if(row-1<0 || letter==P1987.BOARD[row-1][col]) {
				P1987.POSSIBLE_DIRECTION.get(index)[0] = false;
			}
			if(row+1>=P1987.R || letter==P1987.BOARD[row+1][col]) {
				P1987.POSSIBLE_DIRECTION.get(index)[1] = false;
			}
			if(col-1<0 || letter==P1987.BOARD[row][col-1]) {
				P1987.POSSIBLE_DIRECTION.get(index)[2] = false;
			}
			if(col+1>=P1987.C || letter==P1987.BOARD[row][col+1]) {
				P1987.POSSIBLE_DIRECTION.get(index)[3] = false;
			}
		}
	}
	
	public void calcMaxPath(int row, int col, int index) {
		// 상하좌우 이동할 수 없는 경우 종료
		decidePossibleDirection(row, col, index);
		if(!P1987.POSSIBLE_DIRECTION.get(index)[0] && !P1987.POSSIBLE_DIRECTION.get(index)[1] && !P1987.POSSIBLE_DIRECTION.get(index)[2] && !P1987.POSSIBLE_DIRECTION.get(index)[3]) {
			if(P1987.PATH.size() > P1987.MAX_PATH) {
				P1987.MAX_PATH = P1987.PATH.size();
			}
			return;
		}
		
		
		// 위쪽으로 이동
		if(P1987.POSSIBLE_DIRECTION.get(index)[0]) {
			P1987.POSSIBLE_DIRECTION.add(new boolean[4]);
			P1987.PATH.add(P1987.BOARD[row-1][col]);
			calcMaxPath(row-1, col, index+1);
			P1987.PATH.remove(index+1);
			P1987.POSSIBLE_DIRECTION.remove(index+1);
		}
		
		// 아래쪽으로 이동
		if(P1987.POSSIBLE_DIRECTION.get(index)[1]) {
			P1987.POSSIBLE_DIRECTION.add(new boolean[4]);
			P1987.PATH.add(P1987.BOARD[row+1][col]);
			calcMaxPath(row+1, col, index+1);
			P1987.PATH.remove(index+1);
			P1987.POSSIBLE_DIRECTION.remove(index+1);
		}
		
		// 왼쪽으로 이동
		if(P1987.POSSIBLE_DIRECTION.get(index)[2]) {
			P1987.POSSIBLE_DIRECTION.add(new boolean[4]);
			P1987.PATH.add(P1987.BOARD[row][col-1]);
			calcMaxPath(row, col-1, index+1);
			P1987.PATH.remove(index+1);
			P1987.POSSIBLE_DIRECTION.remove(index+1);
		}
		
		// 오른쪽으로 이동
		if(P1987.POSSIBLE_DIRECTION.get(index)[3]) {
			P1987.POSSIBLE_DIRECTION.add(new boolean[4]);
			P1987.PATH.add(P1987.BOARD[row][col+1]);
			calcMaxPath(row, col+1, index+1);
			P1987.PATH.remove(index+1);
			P1987.POSSIBLE_DIRECTION.remove(index+1);
		}
	}
}




// 정답 코드
/*import java.util.*;

public class Main {
    public static final int[] dx = {0, 0, 1, -1};
    public static final int[] dy = {1, -1, 0, 0};
    public static int go(String[] board, boolean[] check, int x, int y) {
        int ans = 0;
        for (int k=0; k<4; k++) {
            int nx = x+dx[k];
            int ny = y+dy[k];
            if (nx >= 0 && nx < board.length && ny >= 0 && ny < board[0].length()) {
                if (check[board[nx].charAt(ny)-'A'] == false) {
                    check[board[nx].charAt(ny)-'A'] = true;
                    int next = go(board, check, nx, ny);
                    if (ans < next) {
                        ans = next;
                    }
                    check[board[nx].charAt(ny)-'A'] = false;
                }
            }
        }
        return ans + 1;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        sc.nextLine();
        String[] board = new String[n];
        for (int i=0; i<n; i++) {
            board[i] = sc.nextLine();
        }
        boolean[] check = new boolean[26];
        check[board[0].charAt(0)-'A'] = true;
        System.out.println(go(board, check, 0, 0));
    }
}*/