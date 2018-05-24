package baekjoon.algorithmCourse.bruteForce;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * 부분집합의 합 2
 * 
 * 문제
 * N개의 정수로 이루어진 집합이 있을 때, 이 집합의 공집합이 아닌 부분집합 중에서 그 집합의 원소를 다 더한 값이 S가 되는 경우의 수를 구하는 프로그램을 작성하시오.
 * 
 * 
 * 입력
 * 첫째 줄에 정수의 개수를 나타내는 N과 정수 S가 주어진다. (1≤N≤40, |S|≤1,000,000) 둘째 줄에 N개의 정수가 빈 칸을 사이에 두고 주어진다.
 * 주어지는 정수의 절대값은 100,000을 넘지 않는다. 같은 수가 여러번 주어질 수도 있다.
 * 
 * 출력
 * 첫째 줄에 합이 S가 되는 부분집합의 개수를 출력한다.
 * 
 * 
 * 예제 입력 1
 * 5 0
 * -7 -3 -2 5 8
 * 
 * 예제 출력 1
 * 1
 */
public class P1208 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int s = scan.nextInt();
		
		int[] numAry = new int[n];
		for(int index=0; index<n; index++) {
			numAry[index] = scan.nextInt();
		}
		scan.close();
		
		int numCount1 = n / 2;
		int numCount2 = n - numCount1;
//		int numCount2 = n / 2;
//		int numCount1 = n - numCount2;
		
		ArrayList<Integer> list1 = new ArrayList<>();
		for(int subSetNum=0; subSetNum<(1<<numCount1); subSetNum++) {
			int subSetSum = 0;
			for(int index=0; index<numCount1; index++) {
				if((subSetNum&(1<<index)) == (1<<index)) { 
					subSetSum += numAry[index];
				}
			}
			list1.add(subSetSum);
		}
		
		ArrayList<Integer> list2 = new ArrayList<>();
		for(int subSetNum=0; subSetNum<(1<<numCount2); subSetNum++) {
			int subSetSum = 0;
			for(int index=0; index<numCount2; index++) {
				if((subSetNum&(1<<index)) == (1<<index)) { 
					subSetSum += numAry[index+numCount1];
				}
			}
			list2.add(subSetSum);
		}
		
		Collections.sort(list1);
		Collections.sort(list2);
		
//		for(int num : list1) {
//			System.out.print(num + " ");
//		}
//		System.out.println();
//		for(int num : list2) {
//			System.out.print(num + " ");
//		}
//		System.out.println();
		
		int list1Size = list1.size();
		int list2Size = list2.size();
		int leftIndex = 0;
		int rightIndex = list2Size - 1;
		long count = 0;
		
		while(leftIndex<list1Size && rightIndex>=0) {
//			int leftSum = list1.get(leftIndex);
//			int rightSum = list2.get(rightIndex);
			int sum = list1.get(leftIndex) + list2.get(rightIndex);
			
			if(sum == s) {
				long leftCount = 1;
				long rightCount = 1;
				leftIndex++;
				rightIndex--;
				
//				long leftCount = 0;
//				long rightCount = 0;
				
				// wrapper class 자체로 비교하면 안됨
				while(leftIndex<list1Size && list1.get(leftIndex).intValue()==list1.get(leftIndex-1).intValue()) {
//				while(leftIndex<list1Size && leftSum==list1.get(leftIndex)) {
					leftCount++;
					leftIndex++;
				}
				
				// wrapper class 자체로 비교하면 안됨
				while(rightIndex>=0 && list2.get(rightIndex).intValue()==list2.get(rightIndex+1).intValue()) {
//				while(rightIndex>=0 && rightSum==list2.get(rightIndex)) {
					rightCount++;
					rightIndex--;
				}
				
				count += leftCount*rightCount;
			}else if(sum > s) {
				rightIndex--;
			}else {
				leftIndex++;
			}
		}
		
		
//		Collections.reverse(list2);
		
		/*int list1Size = list1.size();
		int list2Size = list2.size();
		int leftIndex = 0;
		int rightIndex = 0;
		long count = 0;
		
		while(leftIndex<list1Size && rightIndex<list2Size) {
			int leftSum = list1.get(leftIndex);
			int rightSum = list2.get(rightIndex);
			
			int sum = list1.get(leftIndex) + list2.get(rightIndex);
			
			if(sum == s) {
//				long leftCount = 1;
//				long rightCount = 1;
//				leftIndex++;
//				rightIndex++;
				
				long leftCount = 0;
				long rightCount = 0;
				
//				while(leftIndex<list1Size && list1.get(leftIndex).intValue()==list1.get(leftIndex-1).intValue()) {
				while(leftIndex<list1Size && leftSum==list1.get(leftIndex)) {
					leftCount++;
					leftIndex++;
				}
//				while(rightIndex<list2Size && list2.get(rightIndex).intValue()==list2.get(rightIndex-1).intValue()) {
				while(rightIndex<list2Size && rightSum==list2.get(rightIndex)) {
					rightCount++;
					rightIndex++;
				}
				
				System.out.println(leftCount);
				System.out.println(rightCount);
				
				count += leftCount*rightCount;
			}else if(sum < s) {
				leftIndex++;
			}else {
				rightIndex++;
			}
		}*/
		
		if(s == 0) {
			System.out.println(count-1);
		}else {
			System.out.println(count);
		}
	}
}



// 정답 코드
/*import java.util.*;

public class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int s = sc.nextInt();
        int[] a = new int[n];
        for (int i=0; i<n; i++) {
            a[i] = sc.nextInt();
        }
        int m = n/2;
        n = n-m;
        int[] first = new int[1<<n];
        for (int i=0; i<(1<<n); i++) {
            for (int k=0; k<n; k++) {
                if ((i&(1<<k)) == (1<<k)) {
                    first[i] += a[k];
                }
            }
        }
        int[] second = new int[1<<m];
        for (int i=0; i<(1<<m); i++) {
            for (int k=0; k<m; k++) {
                if ((i&(1<<k)) == (1<<k)) {
                    second[i] += a[k+n];
                }
            }
        }
        Arrays.sort(first);
        Arrays.sort(second);
        n = (1<<n);
        m = (1<<m);
        for (int i=0; i<m/2; i++) {
            int temp = second[i];
            second[i] = second[m-i-1];
            second[m-i-1] = temp;
        }
        int i = 0;
        int j = 0;
        long ans = 0;
        while (i < n && j < m) {
            if (first[i] + second[j] == s) {
                long c1 = 1;
                long c2 = 1;
                i += 1;
                j += 1;
                while (i < n && first[i] == first[i-1]) {
                    c1 += 1;
                    i += 1;
                }
                while (j < m && second[j] == second[j-1]) {
                    c2 += 1;
                    j += 1;
                }
                ans += c1*c2;
            } else if (first[i] + second[j] < s) {
                i += 1;
            } else {
                j += 1;
            }
        }
        if (s == 0) ans -= 1;

        System.out.println(ans);
    }
}*/