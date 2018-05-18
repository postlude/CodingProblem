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
	static int RED_BALL_ROW;
	static int RED_BALL_COL;
	static int BLUE_BALL_ROW;
	static int BLUE_BALL_COL;
	static int HOLE_ROW;
	static int HOLE_COL;
		
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int m = scan.nextInt();
		
//		int redBallRow = -1;
//		int redBallCol = -1;
//		int blueBallRow = -1;
//		int blueBallCol = -1;
//		int holeRow = -1;
//		int holeCol = -1;
		
		char[][] originMap = new char[n][m];
		
		for(int row=0; row<n; row++) {
			String input = scan.next();
			for(int col=0; col<m; col++) {
				originMap[row][col] = input.charAt(col);
				if(originMap[row][col] == 'R') {
//					redBallRow = row;
//					redBallCol = col;
					P13460.RED_BALL_ROW = row;
					P13460.RED_BALL_COL = col;
				}else if(originMap[row][col] == 'B') {
//					blueBallRow = row;
//					blueBallCol = col;
					P13460.BLUE_BALL_ROW = row;
					P13460.BLUE_BALL_COL = col;
				}else if(originMap[row][col] == 'O') {
//					holeRow = row;
//					holeCol = col;
					P13460.HOLE_ROW = row;
					P13460.HOLE_COL = col;
				}
			}
		}
		scan.close();
		// input end
		
		
		P13460 p13460 = new P13460();
		int minMoveCount = P13460.MOVE_LIMIT;
		
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
			char[][] checkMap = originMap.clone();
			int currentMoveCount = p13460.countMove(checkMap, directionAry);
			
			// currentMoveCount가 -1이 아닐 경우에만 비교
			if(currentMoveCount!=-1 && minMoveCount>currentMoveCount) {
				minMoveCount = currentMoveCount;
			}
		}
		
		System.out.println(minMoveCount);
	}
	
	
	
	
	public int countMove(char[][] checkMap, int[] directionAry) {
		// 파란 구슬이 빠진 경우에는 움직인 횟수가 의미가 없으므로 -1로 초기화
		int moveCount = -1;
		
		int redBallRow = P13460.RED_BALL_ROW;
		int redBallCol = P13460.RED_BALL_COL;
		int blueBallRow = P13460.BLUE_BALL_ROW;
		int blueBallCol = P13460.BLUE_BALL_COL;
		int holeRow = P13460.HOLE_ROW;
		int holeCol = P13460.HOLE_COL;
		
		for(int direction : directionAry) {
			int nextRedBallRow = redBallRow + P13460.ROW_MOVE[direction];
			int nextRedBallCol = redBallCol + P13460.COL_MOVE[direction];
			int nextBlueBallRow = blueBallRow + P13460.ROW_MOVE[direction];
			int nextBlueBallCol = blueBallCol + P13460.COL_MOVE[direction];
			
			
		}
		
		return moveCount;
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
