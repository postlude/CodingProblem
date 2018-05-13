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
/*
 * 상태를 나타내는 값이 총 3개(A, B, C의 물의 양)
 * String으로 표현하기에는 물의 양이 자리수가 1~3자리까지 가능
 * 물통 하나를 클래스로 만들어 배열이나 리스트를 쓰게 되면 hashmap에서 비교할 때 오류 발생
 * (각각은 equals(), hashCode()를 오버라이딩한 셈이라 비교가 가능하지만 배열이나 리스트는 아니기 때문)
 * 즉, 물통 3개의 값을 가지는 클래스 자체가 필요하고 equals(), hashCode() 오버라이딩 필요
 */
public class P2251 {
	static int[] FROM_INDEX = {0, 0, 1, 1, 2, 2};
	static int[] TO_INDEX = {1, 2, 0, 2, 0, 1};
	
	public static void main(String[] args) {
		int size = 3;
		
		Scanner scan = new Scanner(System.in);
		int[] bottle = new int[size];
		for(int index=0; index<size; index++) {
			bottle[index] = scan.nextInt();
		}
		scan.close();
		// input end
		
		Water initialWater = new Water();
		initialWater.amount[0] = 0;
		initialWater.amount[1] = 0;
		initialWater.amount[2] = bottle[2];
		
		Queue<Water> queue = new LinkedList<>();
		// map의 value값은 그 때의 C물통의 양(몇 번만에 만들었는지는 필요없으므로)
		HashMap<Water, Integer> madeWater = new HashMap<>();
		
		queue.add(initialWater);
		
		madeWater.put(initialWater, bottle[2]);
		
		while(!queue.isEmpty()) {
			Water nowWater = queue.remove();
			
			for(int index=0; index<6; index++) {
				int fromIndex = P2251.FROM_INDEX[index];
				int toIndex = P2251.TO_INDEX[index];
				
				// 부으려는 물통이 비어있으면 다음으로 넘어감
				int nowFrom = nowWater.amount[fromIndex];
				if(nowFrom == 0) {
					continue;
				}
				
				// 물을 받으려는 물통이 가득 차 있으면 다음으로 넘어감
				int nowTo = nowWater.amount[toIndex];
				if(nowTo == bottle[toIndex]) {
					continue;
				}
				
				// 물을 부은 이후의 양 계산
				int nextFrom = -1;
				int nextTo = -1;
				if(nowFrom+nowTo < bottle[toIndex]) {
					nextFrom = 0;
					nextTo = nowFrom + nowTo;
				}else {
					nextFrom = nowFrom + nowTo - bottle[toIndex];
					nextTo = bottle[toIndex];
				}
				
				
				Water nextWater = new Water(nowWater);
				nextWater.amount[fromIndex] = nextFrom;
				nextWater.amount[toIndex] = nextTo;
				
				if(!madeWater.containsKey(nextWater)) {
					queue.add(nextWater);
					madeWater.put(nextWater, nextWater.amount[2]);
				}
			}
		}
		
		ArrayList<Integer> result = new ArrayList<>();
		Iterator<Water> iter = madeWater.keySet().iterator();
		while(iter.hasNext()) {
			Water water = iter.next();
			// A 물통이 0일 때의 값만 result에 add
			if(water.amount[0] == 0) {
				result.add(water.amount[2]);
			}
		}
		
		Collections.sort(result);
		for(int cWater : result) {
			System.out.print(cWater + " ");
		}
	}
}


class Water {
	int[] amount;
	
	public Water() {
		this.amount = new int[3];
	}
	
	public Water(Water water) {
		this.amount = new int[3];
		for(int index=0; index<3; index++) {
			this.amount[index] = water.amount[index];
		}
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Water) {
			Water water = (Water)obj;
			for(int index=0; index<3; index++) {
				if(this.amount[index] != water.amount[index]) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int result = 3;
		for(int index=0; index<3; index++) {
			result = 19 * result + amount[index];
		}
		return result;
	}
}


// 정답 코드
/*
 * 물의 양은 손실이 없으므로 2개의 물의 양을 알면 나머지 하나의 양을 알 수 있음
 */
/*import java.util.*;

class Pair implements Comparable<Pair> {
    final int first;
    final int second;

    Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }

    @Override
        public int compareTo(Pair pair) {
            if (this.first < pair.first) {
                return -1;
            }
            if (this.first > pair.first) {
                return 1;
            }
            if (this.second < pair.second) {
                return -1;
            }
            if (this.second > pair.second) {
                return 1;
            }
            return 0;
        }

    public boolean equals(Object object) {
        if (object instanceof Pair) {
            Pair pair = (Pair)object;
            return this.first == pair.first && this.second == pair.second;
        }
        return false;
    }

    public int hashCode() {
        int n = 3;
        n = 19 * n + this.first;
        n = 19 * n + this.second;
        return n;
    }
}
public class Main {
    public static final int[] from = {0, 0, 1, 1, 2, 2};
    public static final int[] to = {1, 2, 0, 2, 0, 1};
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int[] cap = new int[3];
        for (int i=0; i<3; i++) {
            cap[i] = sc.nextInt();
        }
        int sum = cap[2];
        boolean[][] check = new boolean[201][201];
        boolean[] ans = new boolean[201];
        Queue<Pair> q = new LinkedList<Pair>();
        q.add(new Pair(0, 0));
        check[0][0] = true;
        ans[cap[2]] = true;
        while (!q.isEmpty()) {
            int[] cur = new int[3];
            Pair p = q.peek();
            cur[0] = p.first;
            cur[1] = p.second;
            cur[2] = sum - cur[0] - cur[1];
            q.remove();
            for (int k=0; k<6; k++) {
                int[] next = {cur[0], cur[1], cur[2]};
                next[to[k]] += next[from[k]];
                next[from[k]] = 0;
                if (next[to[k]] >= cap[to[k]]) {
                    next[from[k]] = next[to[k]] - cap[to[k]];
                    next[to[k]] = cap[to[k]];
                }
                if (!check[next[0]][next[1]]) {
                    check[next[0]][next[1]] = true;
                    q.add(new Pair(next[0], next[1]));
                    if (next[0] == 0) {
                        ans[next[2]] = true;
                    }
                }
            }
        }
        for (int i=0; i<=cap[2]; i++) {
            if (ans[i]) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }
}*/