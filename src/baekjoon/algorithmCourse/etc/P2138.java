package baekjoon.algorithmCourse.etc;

import java.util.Scanner;

/**
 * 전구와 스위치
 * 
 * 문제
 * N개의 스위치와 N개의 전구가 있다. 각각의 전구는 켜져 있는(1) 상태와 꺼져 있는 (0) 상태 중 하나의 상태를 가진다. i(1<i<N)번 스위치를 누르면 i-1, i, i+1의 세 개의 전구의 상태가 바뀐다.
 * 즉, 꺼져 있는 전구는 켜지고, 켜져 있는 전구는 꺼지게 된다. 1번 스위치를 눌렀을 경우에는 1, 2번 전구의 상태가 바뀌고, N번 스위치를 눌렀을 경우에는 N-1, N번 전구의 상태가 바뀐다.
 * N개의 전구들의 현재 상태와 우리가 만들고자 하는 상태가 주어졌을 때, 그 상태를 만들기 위해 스위치를 최소 몇 번 누르면 되는지 알아내는 프로그램을 작성하시오.
 * 
 * 
 * 입력
 * 첫째 줄에 자연수 N(2≤N≤100,000)이 주어진다. 다음 줄에는 전구들의 현재 상태를 나타내는 숫자 N개가 공백 없이 주어진다. 그 다음 줄에는 우리가 만들고자 하는 전구들의 상태를 나타내는 숫자 N개가 공백 없이 주어진다.
 * 
 * 출력
 * 첫째 줄에 답을 출력한다. 불가능한 경우에는 -1을 출력한다.
 * 
 * 
 * 예제 입력 1
 * 3
 * 000
 * 010
 * 
 * 예제 출력 1
 * 3
 */
public class P2138 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int n = scan.nextInt();
		String initBulb = scan.next();
		String targetBulb = scan.next();
		scan.close();
		
		P2138 p2138 = new P2138();
		
		String checkBulb = new String(initBulb);
		int minButtonPress1 = 0;
		// 첫 번째를 누르지 않은 경우
		for(int index=1; index<n; index++) {
			if(checkBulb.charAt(index-1) != targetBulb.charAt(index-1)) {
				int endIndex = -1;
				if(index == n-1) {
					endIndex = n-1;
				}else {
					endIndex = index + 1;
				}
				checkBulb = p2138.pressButton(checkBulb, index-1, endIndex);
				minButtonPress1++;
			}
		}
		if(!checkBulb.equals(targetBulb)) {
			minButtonPress1 = -1;
		}
		
		// 첫 번째를 누른 경우
		checkBulb = new String(initBulb);
		checkBulb = p2138.pressButton(checkBulb, 0, 1);
		int minButtonPress2 = 1;
		for(int index=1; index<n; index++) {
			if(checkBulb.charAt(index-1) != targetBulb.charAt(index-1)) {
				int endIndex = -1;
				if(index == n-1) {
					endIndex = n-1;
				}else {
					endIndex = index + 1;
				}
				checkBulb = p2138.pressButton(checkBulb, index-1, endIndex);
				minButtonPress2++;
			}
		}
		if(!checkBulb.equals(targetBulb)) {
			minButtonPress2 = -1;
		}
		
		if(minButtonPress1!=-1 && minButtonPress2!=-1) {
			System.out.println((minButtonPress1>minButtonPress2)?minButtonPress2:minButtonPress1);
		}else if(minButtonPress1 != -1) {
			System.out.println(minButtonPress1);
		}else if(minButtonPress2 != -1) {
			System.out.println(minButtonPress2);
		}else {
			System.out.println(-1);
		}
	}
	
	public String pressButton(String checkBulb, int startIndex, int endIndex) {
		StringBuilder sb = new StringBuilder(checkBulb);
		
		for(int index=startIndex; index<=endIndex; index++) {
			if(sb.charAt(index) == '0') {
				sb.setCharAt(index, '1');
			}else {
				sb.setCharAt(index, '0');
			}
		}
		
		return sb.toString();
	}
}




// 정답 코드
/*import java.util.*;
class Pair {
    boolean first;
    int second;
    Pair(boolean first, int second) {
        this.first = first;
        this.second = second;
    }
}
public class Main {
    static void change(int[] a, int index) {
        for (int i=index-1; i<=index+1; i++) {
            if (0 <= i && i < a.length) {
                a[i] = 1-a[i];
            }
        }
    }
    static Pair go(int[] a, int[] goal) {
        int n = a.length;
        int ans = 0;
        for (int i=0; i<n-1; i++) {
            if (a[i] != goal[i]) {
                change(a, i+1);
                ans += 1;
            }
        }
        boolean ok = true;
        for (int i=0; i<n; i++) {
            if (a[i] != goal[i]) {
                ok = false;
            }
        }
        if (ok) {
            return new Pair(true, ans);
        } else {
            return new Pair(false, ans);
        }
    }
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        int[] goal = new int[n];
        String s = sc.next();
        for (int i=0; i<n; i++) {
            a[i] = s.charAt(i)-'0';
        }
        s = sc.next();
        for (int i=0; i<n; i++) {
            goal[i] = s.charAt(i)-'0';
        }
        int[] b = new int[a.length];
        System.arraycopy(a, 0, b, 0, a.length);
        Pair p1 = go(b, goal);
        change(a, 0);
        Pair p2 = go(a, goal);
        if (p2.first) {
            p2.second += 1;
        }
        if (p1.first && p2.first) {
            System.out.printf("%d\n",Math.min(p1.second, p2.second));
        } else if (p1.first) {
            System.out.printf("%d\n",p1.second);
        } else if (p2.first) {
            System.out.printf("%d\n",p2.second);
        } else {
            System.out.printf("-1\n");
        }
    }
}*/