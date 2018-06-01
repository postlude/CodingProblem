package baekjoon.algorithmCourse.etc;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 가운데를 말해요
 * 
 * 문제
 * 수빈이는 동생에게 "가운데를 말해요" 게임을 가르쳐주고 있다. 수빈이가 정수를 하나씩 외칠때마다 동생은 지금까지 수빈이가 말한 수 중에서 중간값을 말해야 한다.
 * 만약, 그동안 수빈이가 외친 수의 개수가 짝수개라면 중간에 있는 두 수 중에서 작은 수를 말해야 한다.
 * 예를 들어 수빈이가 동생에게 1, 5, 2, 10, -99, 7, 5를 순서대로 외쳤다고 하면, 동생은 1, 1, 2, 2, 2, 2, 5를 차례대로 말해야 한다.
 * 수빈이가 외치는 수가 주어졌을 때, 동생이 말해야 하는 수를 구하는 프로그램을 작성하시오.
 * 
 * 
 * 입력
 * 첫째 줄에는 수빈이가 외치는 정수의 개수 N이 주어진다. N은 1보다 크거나 같고, 100,000보다 작거나 같은 자연수이다. 그 다음 N줄에 걸쳐서 수빈이가 외치는 정수가 차례대로 주어진다.
 * 정수는 -10,000보다 크거나 같고, 10,000보다 작거나 같다.
 * 
 * 출력
 * 한 줄에 하나씩 N줄에 걸쳐 수빈이의 동생이 말해야하는 수를 순서대로 출력한다.
 * 
 * 
 * 예제 입력 1
 * 7
 * 1
 * 5
 * 2
 * 10
 * -99
 * 7
 * 5
 * 
 * 예제 출력 1
 * 1
 * 1
 * 2
 * 2
 * 2
 * 2
 * 5
 */
/*
 * 두 개의 정렬된 큐를 사용
 * 전체 개수가 홀수일 경우 왼쪽에 넣음
 * 
 * 가운데 수는 왼쪽 큐의 가장 큰 값이므로 왼쪽 큐는 역순 정렬
 */
public class P1655 {
	// 역순 정렬을 위해 사용하는 클래스
	class QueueComparator implements Comparator<Integer>{
		@Override
		public int compare(Integer num1, Integer num2) {
			return num2.compareTo(num1);
		}
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		
		P1655 p1655 = new P1655();
		QueueComparator comparator = p1655.new QueueComparator();
		
		PriorityQueue<Integer> leftQueue = new PriorityQueue<>(1, comparator);
		PriorityQueue<Integer> rightQueue = new PriorityQueue<>();
		
		for(int count=0; count<n; count++) {
			int inputNum = scan.nextInt();
			
			if(leftQueue.isEmpty() || rightQueue.isEmpty()) {
				leftQueue.add(inputNum);
			}else {
				if(inputNum <= leftQueue.peek()) {
					leftQueue.add(inputNum);
				}else if(inputNum >= rightQueue.peek()) {
					rightQueue.add(inputNum);
				}else {
					leftQueue.add(inputNum);
				}
			}
			
			if(leftQueue.size() > rightQueue.size()+1) {
				rightQueue.add(leftQueue.remove());
			}else if(rightQueue.size() > leftQueue.size()) {
				leftQueue.add(rightQueue.remove());
			}
			System.out.println(leftQueue.peek());
		}
		scan.close();
	}
}



// 정답 코드
/*import java.util.*;
public class Main {
    static class Compare implements Comparator<Integer> {
        public int compare(Integer one, Integer two) {
            return two.compareTo(one);
        }
    }
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        Compare cmp = new Compare();
        PriorityQueue<Integer> l = new PriorityQueue<Integer>(1, cmp);
        PriorityQueue<Integer> r = new PriorityQueue<>();
        int n = sc.nextInt();
        while (n-- > 0) {
            int x = sc.nextInt();

            if (l.isEmpty() || r.isEmpty()) {
                l.add(x);
            } else {
                if (x <= l.peek()) {
                    l.add(x);
                } else if (x >= r.peek()) {
                    r.add(x);
                } else {
                    l.add(x);
                }
            }
            while (!(l.size() == r.size() || l.size() == r.size() + 1)) {
                if (l.size() > r.size()) {
                    r.add(l.peek());
                    l.poll();
                } else {
                    l.add(r.peek());
                    r.poll();
                }
            }

            System.out.println(l.peek());
        }

    }
}*/