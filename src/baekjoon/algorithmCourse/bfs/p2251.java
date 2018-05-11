package baekjoon.algorithmCourse.bfs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 물통
 * 
 * 문제
 * 각각 부피가 A, B, C(1≤A, B, C≤200) 리터인 세 개의 물통이 있다. 처음에는 앞의 두 물통은 비어 있고, 세 번째 물통은 가득(C 리터) 차 있다.
 * 이제 어떤 물통에 들어있는 물을 다른 물통으로 쏟아 부을 수 있는데, 이 때에는 한 물통이 비거나, 다른 한 물통이 가득 찰 때까지 물을 부을 수 있다. 이 과정에서 손실되는 물은 없다고 가정한다.
 * 이와 같은 과정을 거치다보면 세 번째 물통(용량이 C인)에 담겨있는 물의 양이 변할 수도 있다. 첫 번째 물통(용량이 A인)이 비어 있을 때, 세 번째 물통(용량이 C인)에 담겨있을 수 있는 물의 양을 모두 구해내는 프로그램을 작성하시오.
 * 
 * 
 * 입력
 * 첫째 줄에 세 정수 A, B, C가 주어진다.
 * 
 * 출력
 * 첫째 줄에 공백으로 구분하여 답을 출력한다. 각 용량은 오름차순으로 정렬한다.
 * 
 * 
 * 예제 입력 1
 * 8 9 10
 * 
 * 예제 출력 1
 * 1 2 8 9 10
 */
public class p2251 {

	public static void main(String[] args) {
		int size = 3;
		
		Scanner scan = new Scanner(System.in);
		int[] bottle = new int[size];
		for(int index=0; index<size; index++) {
			bottle[index] = scan.nextInt();
		}
		scan.close();
		
		String initialWater = "00" + bottle[2];
		
		Queue<String> queue = new LinkedList<>();
		HashMap<String, Integer> madeWater = new HashMap<>();
		
		queue.add(initialWater);
		madeWater.put(initialWater, 0);
		
		while(!queue.isEmpty()) {
//			String nowWater = queue.remove();
			
			String nowWaterStr = queue.remove();
			char[] nowWater = nowWaterStr.toCharArray();
//			int nowA = nowWater.charAt(0) - '0';
//			int nowB = nowWater.charAt(1) - '0';
//			int nowC = nowWater.charAt(2) - '0';
			
			// A -> B
			/*int nextA = -1;
			int nextB = -1;;
			if(nowA+nowB < bottle[1]) {
				nextA = 0; 
				nextB = nowA + nowB;
			}else {
				nextA = nowA + nowB - bottle[1];
				nextB = bottle[1];
			}
			
			String nextWater = "" + nextA + nextB + nowC;
			if(!madeWater.containsKey(nextWater)) {
				queue.add(nextWater);
				madeWater.put(nextWater, madeWater.get(nowWater)+1);
			}*/
			
			
			for(int fromIndex=0; fromIndex<size; fromIndex++) {
				int nowFrom = nowWater[fromIndex] - '0';
				
				for(int toIndex=0; toIndex<size; toIndex++) {
					if(toIndex == fromIndex) {
						continue;
					}
					
					int nowTo = nowWater[toIndex] - '0'; 
					int nextFrom = -1;
					int nextTo = -1;
					
					if(nowFrom+nowTo < bottle[toIndex]) {
						nextFrom = 0;
						nextTo = nowFrom + nowTo;
					}else {
						nextFrom = nowFrom + nowTo - bottle[toIndex];
						nextTo = bottle[toIndex];
					}
					
					String nextWater = "";
					int nextC = -1;
					if(fromIndex==0 && toIndex==1) {
						nextWater += nextFrom;
						nextWater += nextTo;
						nextWater += nowWater[2];
						nextC = nowWater[2];
					}else if(fromIndex==0 && toIndex==2) {
						nextWater += nextFrom;
						nextWater += nowWater[1];
						nextWater += nextTo;
						nextC = nextTo;
					}else if(fromIndex==1 && toIndex==0) {
						nextWater += nextTo;
						nextWater += nextFrom;
						nextWater += nowWater[2];
						nextC = nowWater[2];
					}else if(fromIndex==1 && toIndex==2) {
						nextWater += nowWater[0];
						nextWater += nextFrom;
						nextWater += nextTo;
						nextC = nextTo;
					}else if(fromIndex==2 && toIndex==0) {
						nextWater += nextTo;
						nextWater += nowWater[1];
						nextWater += nextFrom;
						nextC = nextFrom;
					}else {
						nextWater += nowWater[0];
						nextWater += nextTo;
						nextWater += nextFrom;
						nextC = nextFrom;
					}
					
					if(!madeWater.containsKey(nextWater)) {
						queue.add(nextWater);
						madeWater.put(nextWater, nextC);
					}
				}
			}
		}
		
		ArrayList<Integer> result = new ArrayList<>();
		Iterator<String> iter = madeWater.keySet().iterator();
		while(iter.hasNext()) {
			String water = iter.next();
			if(water.charAt(0) == '0') {
				result.add(madeWater.get(water));
			}
		}
		
		Collections.sort(result);
		for(int cWater : result) {
			System.out.print(cWater + " ");
		}
	}

}
