package baekjoon.algorithmCourse.bfs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 퍼즐
 * 
 * 문제
 * 3*3 표에 다음과 같이 수가 채워져 있다. 오른쪽 아래 가장 끝 칸은 비어 있는 칸이다.
 * 
 * 1	2	3
 * 4	5	6
 * 7	8	 
 * 
 * 어떤 수와 인접해 있는 네 개의 칸 중에 하나가 비어 있으면, 수를 그 칸으로 이동시킬 수가 있다. 물론 표 바깥으로 나가는 경우는 불가능하다. 우리의 목표는 초기 상태가 주어졌을 때, 최소의 이동으로 위와 같은 정리된 상태를 만드는 것이다. 다음의 예를 보자.
 * 
 * 1	 	3
 * 4	2	5
 * 7	8	6
 * 
 * 1	2	3
 * 4	 	5
 * 7	8	6
 * 
 * 1	2	3
 * 4	5	 
 * 7	8	6
 * 
 * 1	2	3
 * 4	5	6
 * 7	8	 
 * 
 * 가장 윗 상태에서 세 번의 이동을 통해 정리된 상태를 만들 수 있다. 이와 같이 최소 이동 횟수를 구하는 프로그램을 작성하시오.
 * 
 * 
 * 입력
 * 세 줄에 걸쳐서 표에 채워져 있는 아홉 개의 수가 주어진다. 한 줄에 세 개의 수가 주어지며, 빈 칸은 0으로 나타낸다.
 * 
 * 출력
 * 첫째 줄에 최소의 이동 횟수를 출력한다. 이동이 불가능한 경우 -1을 출력한다.
 * 
 * 
 * 예제 입력 1
 * 1 0 3
 * 4 2 5
 * 7 8 6
 * 
 * 예제 출력 1
 * 3
 */
/*
 * 겹치는 숫자가 없으므로 하나의 문자열 or 하나의 숫자로 표현 가능
 * 상태를 표시할 때 9개의 숫자가 모두 영향을 미치므로 배열로는 표시 불가
 * map으로 표시
 */
public class P1525 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		String startNum = "";
		for(int count=0; count<9; count++) {
			int inputNum = scan.nextInt();
			startNum += inputNum;
		}
		scan.close();
		// input end
		
		
		Queue<String> queue = new LinkedList<>();
		HashMap<String, Integer> madeNum = new HashMap<>();
		
		queue.add(startNum);
		madeNum.put(startNum, 0);
		
		while(!queue.isEmpty()) {
			String nowNum = queue.remove();
			
			// 현재 0의 index와 배열로 생각했을 때의 row, col 값
			int index0 = nowNum.indexOf('0');
			int row = index0 / 3;
			int col = index0 % 3;
		
			for(int move=0; move<4; move++) {
				int swapIndex = -1;
				// 위치를 바꿀 숫자를 찾기 위해 index 계산
				switch(move) {
					case 0 : {
						int upRow = row - 1;
						if(upRow >= 0) {
							swapIndex = upRow*3 + col;
						}
						break;
					}
					case 1 : {
						int downRow = row + 1;
						if(downRow < 3) {
							swapIndex = downRow*3 + col;
						}
						break;
					}
					case 2 : {
						int rightCol = col + 1;
						if(rightCol < 3) {
							swapIndex = row*3 + rightCol;
						}
						break;
					}
					case 3 : {
						int leftCol = col - 1;
						if(leftCol >= 0) {
							swapIndex = row*3 + leftCol;
						}
						break;
					}
				}
				
				// 이동 불가능한 경우
				if(swapIndex == -1) {
					continue;
				}
				
				char swapChar = nowNum.charAt(swapIndex);
				String nextNum = nowNum;
				
				// swapIndex가 더 앞쪽에 있으면 뒤에 있는 0을 먼저 바꾼다.
				if(swapIndex < index0) {
					nextNum = nextNum.replace('0', swapChar);
					nextNum = nextNum.replaceFirst(String.valueOf(swapChar), "0");
				}else {
					nextNum = nextNum.replace(swapChar, '0');
					nextNum = nextNum.replaceFirst("0", String.valueOf(swapChar));
				}
				
				// 만든적이 없는 숫자일 경우 추가
				if(!madeNum.containsKey(nextNum)) {
					queue.add(nextNum);
					madeNum.put(nextNum, madeNum.get(nowNum)+1);
				}
			}
		}
		
		if(madeNum.containsKey("123456780")) {
			System.out.println(madeNum.get("123456780"));
		}else {
			System.out.println(-1);
		}
	}
}




// 정답 코드
/*
 * 하나의 숫자로 표시
 * 0은 9로 표현하면 항상 9자리 숫자로 됨
 */
/*import java.util.*;

public class Main {
    public static final int[] dx = {0, 0, 1, -1};
    public static final int[] dy = {1, -1, 0, 0};
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = 3;
        int start = 0;
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                int temp = sc.nextInt();
                if (temp == 0) {
                    temp = 9;
                }
                start = start * 10 + temp;
            }
        }
        Queue<Integer> q = new LinkedList<Integer>();
        HashMap<Integer,Integer> d = new HashMap<Integer,Integer>();
        d.put(start, 0);
        q.add(start);
        while (!q.isEmpty()) {
            int now_num = q.remove();
            String now = Integer.toString(now_num);
            int z = now.indexOf('9');
            int x = z/3;
            int y = z%3;
            for (int k=0; k<4; k++) {
                int nx = x+dx[k];
                int ny = y+dy[k];
                if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                    StringBuilder next = new StringBuilder(now);
                    char temp = next.charAt(x*3+y);
                    next.setCharAt(x*3+y, next.charAt(nx*3+ny));
                    next.setCharAt(nx*3+ny, temp);
                    int num = Integer.parseInt(next.toString());
                    if (!d.containsKey(num)) {
                        d.put(num, d.get(now_num)+1);
                        q.add(num);
                    }
                }
            }
        }
        if (d.containsKey(123456789)) {
            System.out.println(d.get(123456789));
        } else {
            System.out.println("-1");
        }
    }
}*/