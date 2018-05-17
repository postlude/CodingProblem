package baekjoon.algorithmCourse.bfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 열쇠
 * 
 * 문제
 * 상근이는 1층 빌딩에 침입해 매우 중요한 문서를 훔쳐오려고 한다. 상근이가 가지고 있는 평면도에는 문서의 위치가 모두 나타나 있다. 빌딩의 문은 모두 잠겨있기 때문에, 문을 열려면 열쇠가 필요하다.
 * 상근이는 일부 열쇠를 이미 가지고 있고, 일부 열쇠는 빌딩의 바닥에 놓여져 있다.
 * 상근이가 훔칠 수 있는 문서의 최대 개수를 구하는 프로그램을 작성하시오.
 * 
 * 
 * 입력
 * 첫째 줄에 테스트 케이스의 개수가 주어진다. 테스트 케이스의 수는 100개를 넘지 않는다.
 * 각 테스트 케이스의 첫째 줄에는 지도의 높이와 너비 h와 w (2 ≤ h, w ≤ 100)가 주어진다. 다음 h개 줄에는 빌딩을 나타내는 w개의 문자가 주어지며, 각 문자는 다음 중 하나이다.
 * 
 * '.'는 빈 공간을 나타낸다.
 * '*'는 벽을 나타내며, 상근이는 벽을 통과할 수 없다.
 * '$'는 상근이가 훔쳐야하는 문서이다.
 * 알파벳 대문자는 문을 나타낸다.
 * 알파벳 소문자는 열쇠를 나타내며, 그 문자의 대문자인 모든 문을 열 수 있다.
 * 마지막 줄에는 상근이가 이미 가지고 있는 열쇠가 공백없이 주어진다. 만약, 열쇠를 하나도 가지고 있지 않는 경우에는 "0"이 주어진다.
 * 
 * 상근이는 빌딩 밖으로 나갈 수 있다. 각각의 문에 대해서, 그 문을 열 수 잇는 열쇠의 개수는 0개, 1개, 또는 그 이상이고, 각각의 열쇠에 대해서, 그 열쇠로 열 수 있는 문의 개수도 0개, 1개, 또는 그 이상이다.
 * 열쇠는 여러 번 사용할 수 있다.
 * 
 * 
 * 출력
 * 각 테스트 케이스 마다, 상근이가 훔칠 수 있는 문서의 최대 개수를 출력한다.
 * 
 * 
 * 예제 입력 1
 * 3
 * 5 17
 * *****************
 * .............**$*
 * *B*A*P*C**X*Y*.X.
 * *y*x*a*p**$*$**$*
 * *****************
 * cz
 * 5 11
 * *.*********
 * *...*...*x*
 * *X*.*.*.*.*
 * *$*...*...*
 * ***********
 * 0
 * 7 7
 * *ABCDE*
 * X.....F
 * W.$$$.G
 * V.$$$.H
 * U.$$$.J
 * T.....K
 * *SQPML*
 * irony
 * 
 * 예제 출력 1
 * 3
 * 1
 * 0
 */
/*
 * 문과 키는 알파벳으로 주어지므로 알파벳 개수만큼의 큐를 사용해서
 * 문을 만났을 때 키가 있으면 진행하고
 * 키가 없으면 해당 알파벳의 문의 위치를 저장했다가
 * 키를 획득했을 때 해당 문의 위치를 전부 이동하는 큐에 넣는 식으로 진행
 */
public class P9328 {
	// up, down, right, left
	static final int[] ROW_MOVE = {-1, 1, 0, 0};
	static final int[] COL_MOVE = {0, 0, 1, -1};
		
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int testNum = scan.nextInt();
		
		for(int test=0; test<testNum; test++) {
			int h = scan.nextInt();
			int w = scan.nextInt();
			
			String[] building = new String[h+2];
			for(int row=1; row<=h; row++) {
				String input = scan.next();
				// 양 끝쪽에 빈 공간 추가
				building[row] = ".".concat(input).concat(".");
			}
			
			// 맨 위와 아래에 추가할 String
			String topBottomRow = "";
			for(int col=0; col<w+2; col++) {
				topBottomRow = topBottomRow.concat(".");
			}
			building[0] = topBottomRow;
			building[h+1] = topBottomRow;
			
			char[] key = scan.next().toCharArray();
			// input end
			
			
			h += 2;
			w += 2;
			
			Queue<Position3> queue = new LinkedList<>();
			
			// 알파벳마다의 문의 위치를 저장할 큐 배열
			Queue<Position3>[] doorPosition = new Queue[26];
			for(int index=0; index<26; index++) {
				doorPosition[index] = new LinkedList<>();
			}
			
			boolean[] hasKey = new boolean[26];
			// 초기에 키를 가진 경우에만 setting
			if(key[0] != '0') {
				for(int index=0; index<key.length; index++) {
					hasKey[key[index]-'a'] = true;
				}
			}
			
			boolean[][] isVisited = new boolean[h][w];
			
			int maxDoc = 0;
			
			queue.add(new Position3(0, 0));
			isVisited[0][0] = true;
			
			while(!queue.isEmpty()) {
				Position3 nowPosition = queue.remove();
				int nowRow = nowPosition.row;
				int nowCol = nowPosition.col;
				
				for(int index=0; index<4; index++) {
					int nextRow = nowRow + P9328.ROW_MOVE[index];
					int nextCol = nowCol + P9328.COL_MOVE[index];
					
					if(nextRow<0 || nextRow>=h || nextCol<0 || nextCol>=w) {
						continue;
					}
					
					// 이미 방문한 경우
					if(isVisited[nextRow][nextCol]) {
						continue;
					}
					
					char nextCh = building[nextRow].charAt(nextCol);
					
					// 이동불가능한 경우
					if(nextCh == '*') {
						continue;
					}
					
					Position3 nextPosition = new Position3(nextRow, nextCol);
					isVisited[nextRow][nextCol] = true;
					
					// 빈 공간
					if(nextCh == '.') {
						queue.add(new Position3(nextRow, nextCol));
					}else if(nextCh == '$') {// 문서 획득
						maxDoc++;
						queue.add(new Position3(nextRow, nextCol));
					}else if(nextCh>='A' && nextCh<='Z') {// 문을 만났을 때
						// 키가 있으면 진행
						if(hasKey[nextCh-'A']) {
							queue.add(new Position3(nextRow, nextCol));
						}else {
							// 키가 없으면 문의 위치를 저장
							doorPosition[nextCh-'A'].add(nextPosition);
						}
					}else if(nextCh>='a' && nextCh<='z') {// 키 획득
						queue.add(new Position3(nextRow, nextCol));
						hasKey[nextCh-'a'] = true;
						
						// 획득한 키에 해당하는 문의 위치에 갈 수 있으므로 전부 queue에 넣음
						while(!doorPosition[nextCh-'a'].isEmpty()) {
							queue.add(doorPosition[nextCh-'a'].remove());
						}
					}
				}
			}
			System.out.println(maxDoc);
		}
		
		scan.close();
	}
}

class Position3{
	int row;
	int col;
	
	public Position3(int row, int col) {
		this.row = row;
		this.col = col;
	}
}