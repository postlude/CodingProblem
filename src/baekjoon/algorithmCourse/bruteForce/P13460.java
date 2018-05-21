package baekjoon.algorithmCourse.bruteForce;

import java.util.Scanner;

/**
 * 구슬 탈출 2
 * 
 * 문제
 * 스타트링크에서 판매하는 어린이용 장남감 중에서 가장 인기가 많은 제품은 구슬 탈출이다. 구슬 탈출은 직사각형 보드에 빨간 구슬과 파란 구슬을 하나씩 넣은 다음, 빨간 구슬을 구멍을 통해 빼내는 게임이다.
 * 보드의 세로 크기는 N, 가로 크기는 M이고, 편의상 1×1크기의 칸으로 나누어져 있다. 가장 바깥 행과 열은 모두 막혀져 있고, 보드에는 구멍이 하나 있다.
 * 빨간 구슬과 파란 구슬의 크기는 보드에서 1×1크기의 칸을 가득 채우는 사이즈이고, 각각 하나씩 들어가 있다. 게임의 목표는 빨간 구슬을 구멍을 통해서 빼내는 것이다. 이 때, 파란 구슬이 구멍에 들어가면 안된다.
 * 이 때, 구슬을 손으로 건드릴 수는 없고, 중력을 이용해서 이리 저리 굴려야 한다. 왼쪽으로 기울이기, 오른쪽으로 기울이기, 위쪽으로 기울이기, 아래쪽으로 기울이기와 같은 네 가지 동작이 가능하다.
 * 각각의 동작에서 공은 동시에 움직인다. 빨간 구슬이 구멍에 빠지면 성공이지만, 파란 구슬이 구멍에 빠지면 실패이다. 빨간 구슬과 파란 구슬이 동시에 구멍에 빠져도 실패이다.
 * 빨간 구슬과 파란 구슬은 동시에 같은 칸에 있을 수 없다. 또, 빨간 구슬과 파란 구슬의 크기는 한 칸을 모두 차지한다. 기울이는 동작을 그만하는 것은 더 이상 구슬이 움직이지 않을 때 까지이다.
 * 보드의 상태가 주어졌을 때, 최소 몇 번 만에 빨간 구슬을 구멍을 통해 빼낼 수 있는지 구하는 프로그램을 작성하시오.
 * 
 * 
 * 입력
 * 첫 번째 줄에는 보드의 세로, 가로 크기를 의미하는 두 정수 N, M (3 ≤ N, M ≤ 10)이 주어진다. 다음 N개의 줄에 보드의 모양을 나타내는 길이 M의 문자열이 주어진다.
 * 이 문자열은 '.', '#', 'O', 'R', 'B' 로 이루어져 있다. '.'은 빈 칸을 의미하고, '#'은 공이 이동할 수 없는 장애물 또는 벽을 의미하며, 'O'는 구멍의 위치를 의미한다. 'R'은 빨간 구슬의 위치, 'B'는 파란 구슬의 위치이다.
 * 입력되는 모든 보드의 가장자리에는 모두 '#'이 있다. 구멍의 개수는 한 개 이며, 빨간 구슬과 파란 구슬은 항상 1개가 주어진다.
 * 
 * 출력
 * 최소 몇 번 만에 빨간 구슬을 구멍을 통해 빼낼 수 있는지 출력한다. 만약, 10번 이하로 움직여서 빨간 구슬을 구멍을 통해 빼낼 수 없으면 -1을 출력한다.
 * 
 * 
 * 예제 입력 1
 * 5 5
 * #####
 * #..B#
 * #.#.#
 * #RO.#
 * #####
 * 
 * 예제 출력 1
 * 1
 * 
 * 
 * 예제 입력 2
 * 7 7
 * #######
 * #...RB#
 * #.#####
 * #.....#
 * #####.#
 * #O....#
 * #######
 * 
 * 예제 출력 2
 * 5
 * 
 * 
 * 예제 입력 3
 * 7 7
 * #######
 * #..R#B#
 * #.#####
 * #.....#
 * #####.#
 * #O....#
 * #######
 * 
 * 예제 출력 3
 * 5
 * 
 * 
 * 예제 입력 4
 * 10 10
 * ##########
 * #R#...##B#
 * #...#.##.#
 * #####.##.#
 * #......#.#
 * #.######.#
 * #.#....#.#
 * #.#.#.#..#
 * #...#.O#.#
 * ##########
 * 
 * 예제 출력 4
 * -1
 * 
 * 
 * 예제 입력 5
 * 3 7
 * #######
 * #R.O.B#
 * #######
 * 
 * 예제 출력 5
 * 1
 * 
 * 
 * 예제 입력 6
 * 10 10
 * ##########
 * #R#...##B#
 * #...#.##.#
 * #####.##.#
 * #......#.#
 * #.######.#
 * #.#....#.#
 * #.#.##...#
 * #O..#....#
 * ##########
 * 
 * 예제 출력 6
 * 7
 * 
 * 
 * 예제 입력 7
 * 3 10
 * ##########
 * #.O....RB#
 * ##########
 * 
 * 예제 출력 7
 * -1
 * 
 */
public class P13460 {
	// up, down, right, left
	static final int[] ROW_MOVE = {-1, 1, 0, 0};
	static final int[] COL_MOVE = {0, 0, 1, -1};
	static final int MOVE_LIMIT = 10;
	
	// 각각의 공과 구멍 위치
	static Position RED_BALL;
	static Position BLUE_BALL;
		
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int m = scan.nextInt();
		
		char[][] originMap = new char[n][m];
		
		for(int row=0; row<n; row++) {
			String input = scan.next();
			for(int col=0; col<m; col++) {
				originMap[row][col] = input.charAt(col);
				if(originMap[row][col] == 'R') {
					P13460.RED_BALL = new Position(row, col);
				}else if(originMap[row][col] == 'B') {
					P13460.BLUE_BALL = new Position(row, col);
				}
			}
		}
		scan.close();
		// input end
		
		
		P13460 p13460 = new P13460();
		int minMoveCount = -1;
		
		// 최대 10회만 이동하면 되므로 10번의 이동방향의 종류를 모두 만든다.
        // 상하좌우 4개 방향으로 이동 가능하므로 2bit가 필요
        // 10번의 방향을 만들기 위해선 총 20bit가 필요
		for(int directionNum=0; directionNum<(1<<P13460.MOVE_LIMIT*2); directionNum++) {
			int[] directionAry = p13460.make10Direction(directionNum);
			
			// 의미없는 이동이 포함된 경우에는 다음 이동 경우의 수로 넘어감
			if(!p13460.isValidDirection(directionAry)) {
				continue;
			}
			
			// 이동할 때 map이 바뀌므로 매번 새로운 map 생성
			char[][] checkMap = new char[n][m];
			for(int row=0; row<n; row++) {
				for(int col=0; col<m; col++) {
					checkMap[row][col] = originMap[row][col];
				}
			}
			
			int currentMoveCount = p13460.countMove(checkMap, directionAry);
			
			// currentMoveCount가 -1일 경우(불가능한 경우)에는 비교하지 않음
			if(currentMoveCount == -1) {
				continue;
			}
			
			// minMoveCount가 초기값(-1)이거나 currentMoveCount이 더 작으면
			if(minMoveCount==-1 || minMoveCount>currentMoveCount) {
				minMoveCount = currentMoveCount;
			}
		}
		
		System.out.println(minMoveCount);
	}
	
	/**
	 * 이동 횟수 세는 메소드
	 * @param checkMap
	 * @param directionAry
	 * @return
	 */
	public int countMove(char[][] checkMap, int[] directionAry) {
		Position nowRedBall = new Position(P13460.RED_BALL);
		Position nowBlueBall = new Position(P13460.BLUE_BALL);
		int moveCount = 0;
		
		for(int direction : directionAry) {
			while(true) {
				boolean isRedBallMoved = moveBall(checkMap, nowRedBall, direction); 
				boolean isBlueBallMoved = moveBall(checkMap, nowBlueBall, direction);
				if(!isRedBallMoved && !isBlueBallMoved) {
					break;
				}
				
				// 파란 공이 빠졌으면
				if(checkMap[nowBlueBall.row][nowBlueBall.col] == '.') {
					return -1;
				}
			}
			moveCount++;
			
			if(checkMap[nowRedBall.row][nowRedBall.col] == '.') {
				return moveCount;
			}
		}
		
		if(checkMap[nowRedBall.row][nowRedBall.col] == '.') {
			return P13460.MOVE_LIMIT;
		}else {
			return -1;
		}
	}
	
	/**
	 * 공 이동 메소드
	 * @param checkMap
	 * @param initPosition
	 * @param direction
	 * @return
	 */
	public boolean moveBall(char[][] checkMap, Position nowPosition, int direction) { 
		boolean isMoved = false;
		
		// 공이 빠진 상태에서 또 움직이려고 하면
		if(checkMap[nowPosition.row][nowPosition.col] == '.') {
			return isMoved;
		}
		
		do {
			Position nextPosition = new Position(nowPosition.row+P13460.ROW_MOVE[direction], nowPosition.col+P13460.COL_MOVE[direction]);
			char nextCh = checkMap[nextPosition.row][nextPosition.col];
			
			if(nextCh == '.') {
				// 공과 빈 공간 swap
				char nowCh = checkMap[nowPosition.row][nowPosition.col];
				checkMap[nowPosition.row][nowPosition.col] = nextCh;
				checkMap[nextPosition.row][nextPosition.col] = nowCh;
				
				// 현 위치를 다음 위치로 변경
				nowPosition.setPosition(nextPosition);
				isMoved = true;
			}else if(nextCh=='#' || nextCh=='B' || nextCh=='R') {
				break;
			}else if(nextCh == 'O') {
				checkMap[nowPosition.row][nowPosition.col] = '.';
				isMoved = true;
				break;
			}
		}while(true);
		
		return isMoved;
	}
	
	/**
	 * 파라미터로 받은 숫자로 이동방향 배열 만드는 메소드
	 * @param directionNum
	 * @return
	 */
	public int[] make10Direction(int directionNum) {
		int[] direction = new int[P13460.MOVE_LIMIT];
		
		for(int index=0; index<P13460.MOVE_LIMIT; index++) {
			// 상하좌우 이동의 index값(0~3)을 만들기 위해 3(2진수로 11)과 &연산 후 2bit씩 뺀다.
			int move = directionNum & 3;
			direction[index] = move;
			directionNum = directionNum >> 2;
		}
		
		return direction;
	}
	
	/**
	 * 의미없는 이동이 포함되어 있는지를 확인하는 메소드
	 * @param directionAry
	 * @return
	 */
	public boolean isValidDirection(int[] directionAry) {
		for(int index=0; index<P13460.MOVE_LIMIT-1; index++) {
			// 두 번 연속 같은 방향으로 이동하면 의미없음
			if(directionAry[index] == directionAry[index+1]) {
				return false;
			}
			
			// 연속된 이동이 서로 반대방향이면 의미없음
			if(directionAry[index]==0 && directionAry[index+1]==1) {
				return false;
			}else if(directionAry[index]==1 && directionAry[index+1]==0) {
				return false;
			}else if(directionAry[index]==2 && directionAry[index+1]==3) {
				return false;
			}else if(directionAry[index]==3 && directionAry[index+1]==2) {
				return false;
			}
		}
		return true;
	}
}

class Position{
	int row;
	int col;
	
	public Position(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	public Position(Position position) {
		this.row = position.row;
		this.col = position.col;
	}
	
	public void setPosition(Position position) {
		this.row = position.row;
		this.col = position.col;
	}
}



//정답 코드
/*import java.util.*;
class Result {
    boolean isMoved, hole;
    int x, y;
    Result(boolean isMoved, boolean hole, int x, int y) {
        this.isMoved = isMoved;
        this.hole = hole;
        this.x = x;
        this.y = y;
    }
}
public class Main {
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static final int LIMIT = 10;
    
    static int[] generateDirection(int directionNum) {
        int[] direction = new int[LIMIT];
        for (int i=0; i<LIMIT; i++) {
        	// 상하좌우 이동의 index값(0~3)을 만들기 위해 3(2진수로 11)과 &연산 후 2bit씩 뺀다.
            direction[i] = (directionNum&3);
            directionNum >>= 2;
        }
        return direction;
    }
    
    static Result simulate(char[][] board, int moveDirection, int x, int y) {
        int n = board.length;
        int m = board[0].length;
        
        // 구멍에 빠져서 . 이면 
        if (board[x][y] == '.') return new Result(false, false, x, y);
        
        boolean isMoved = false;
        while (true) {
            int nx = x+dx[moveDirection];
            int ny = y+dy[moveDirection];
            
            // 이동 불가능한 경우
            if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                return new Result(isMoved, false, x, y);
            }
            
            char ch = board[nx][ny];
            if (ch == '#') {
                return new Result(isMoved, false, x, y);
            } else if (ch == 'R' || ch == 'B') {
            	// 다른 공을 만난 경우
                return new Result(isMoved, false, x, y);
            } else if (ch == '.') {
            	// 한 칸 이동했는데 빈 공간이면 계속 이동
                char temp = board[nx][ny];
                board[nx][ny] = board[x][y];
                board[x][y] = temp;
                x = nx;
                y = ny;
                isMoved = true;
            } else if (ch == 'O') {
            	// 구멍에 빠지면 현재 공이 있는 위치를 빈공간을 바꿈
                board[x][y] = '.';
                isMoved = true;
                return new Result(isMoved, true, x, y);
            }
        }
    }
    static int check(char[][] board, int[] direction) {
        int n = board.length;
        int m = board[0].length;
        int hx = 0, hy = 0;
        int rx = 0, ry = 0;
        int bx = 0, by = 0;
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                if (board[i][j] == 'O') {
                    hx = i; hy = j;
                } else if (board[i][j] == 'R') {
                    rx = i; ry = j;
                } else if (board[i][j] == 'B') {
                    bx = i; by = j;
                }
            }
        }
        
        int cnt = 0;
        for (int moveDirection : direction) {
            cnt += 1;
            boolean isRedBallInHole = false, isBlueBallInHole = false;
            
            while (true) {
                Result redBall = simulate(board, moveDirection, rx, ry);
                // 다음 이동을 위해 위치 저장
                rx = redBall.x; ry = redBall.y;
                
                Result blueBall = simulate(board, moveDirection, bx, by);
                bx = blueBall.x; by = blueBall.y;
                
                // 이동 방향은 이미 direction배열에 저장되어 있다.
                // 언젠가는 반드시 움직이지 않는 순간이 옴
                if (redBall.isMoved == false && blueBall.isMoved == false) {
                    break;
                }
                if (redBall.hole) isRedBallInHole = true;
                if (blueBall.hole) isBlueBallInHole = true;
            }
            // 이동 중에 파란공이 빠짐
            if (isBlueBallInHole) return -1;
            
            // 이동 중에 빨간공이 빠짐 
            if (isRedBallInHole) return cnt;
        }
        
        // 이동을 끝마쳤으나 아무 공도 빠지지 않음
        return -1;
    }
    
    *//**
     * 올바른 이동인지를 체크하는 메소드
     * @param dir
     * @return
     *//*
    static boolean valid(int[] dir) {
        int l = dir.length;
        for (int i=0; i+1<l; i++) {
        	// 서로 반대 방향으로 이동하는 경우는 의미가 없음
            if (dir[i] == 0 && dir[i+1] == 1) return false;
            if (dir[i] == 1 && dir[i+1] == 0) return false;
            if (dir[i] == 2 && dir[i+1] == 3) return false;
            if (dir[i] == 3 && dir[i+1] == 2) return false;
            
            // 같은 방향으로 2번 연속 움직여도 의미 없음
            if (dir[i] == dir[i+1]) return false;
        }
        return true;
    }
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        String[] map = new String[n];
        char[][] board = new char[n][m];
        for (int i=0; i<n; i++) {
            map[i] = sc.next();
        }
        
        int ans = -1;
        
        // 최대 10회만 이동하면 되므로 10번의 이동방향을 모두 만든다.
        // 상하좌우 4개 방향으로 이동 가능하므로 2bit가 필요
        // 10번의 방향을 만들기 위해선 총 20bit가 필요
        for (int directionNum=0; directionNum<(1<<(LIMIT*2)); directionNum++) {
            int[] direction = generateDirection(directionNum);
            if (!valid(direction)) continue;
          
            // 한 번 이동을 검사할 때마다 원래 map의 정보가 바뀌므로 매 번 원래 map으로 세팅
            for (int i=0; i<n; i++) {
                board[i] = map[i].toCharArray();
            }
            
            // 현재 이동방향으로 이동했을 때 움직인 횟수
            int cur = check(board, direction);
            
            // -1은 파란공이 빠지거나 빨간공이 빠지지 못한 경우이므로 아래 if문에서 검사하면 안됨
            if (cur == -1) continue;
            if (ans == -1 || ans > cur) ans = cur;
        }
        System.out.println(ans);
    }
}*/