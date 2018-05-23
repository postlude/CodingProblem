package baekjoon.algorithmCourse.bruteForce;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * 두 배열의 합
 * 
 * 문제
 * 한 배열 A[1], A[2], …, A[n]에 대해서, 부 배열은 A[i], A[i+1], …, A[j-1], A[j] (단, 1≤i≤j≤n)을 말한다. 이러한 부 배열의 합은 A[i]+…+A[j]를 의미한다.
 * 각 원소가 정수인 두 배열 A[1], …, A[n]과 B[1], …, B[m]이 주어졌을 때, A의 부 배열의 합에 B의 부 배열의 합을 더해서 T가 되는 모든 부 배열 쌍의 개수를 구하는 프로그램을 작성하시오.
 * 예를 들어 A = {1, 3, 1, 2}, B = {1, 3, 2}, T=5인 경우, 부 배열 쌍의 개수는 다음의 7가지 경우가 있다.
 * 
 * T(=5) = A[1] + B[1] + B[2]
 *       = A[1] + A[2] + B[1]
 *       = A[2] + B[3]
 *       = A[2] + A[3] + B[1]
 *       = A[3] + B[1] + B[2]
 *       = A[3] + A[4] + B[3]
 *       = A[4] + B[2] 
 *       
 *       
 * 입력
 * 첫째 줄에 T(-1,000,000,000 ≤ T ≤ 1,000,000,000)가 주어진다. 다음 줄에는 n(1 ≤ n ≤ 1,000)이 주어지고, 그 다음 줄에 n개의 정수로 A[1], …, A[n]이 주어진다.
 * 다음 줄에는 m(1≤m≤1,000)이 주어지고, 그 다음 줄에 m개의 정수로 B[1], …, B[m]이 주어진다. 각각의 배열 원소는 절대값이 1,000,000을 넘지 않는 정수이다.
 * 
 * 출력
 * 첫째 줄에 답을 출력한다. 가능한 경우가 한 가지도 없을 경우에는 0을 출력한다.
 * 
 * 
 * 예제 입력 1
 * 5
 * 4
 * 1 3 1 2
 * 3
 * 1 3 2
 * 
 * 예제 출력 1
 * 7
 */
public class P2143 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int t = scan.nextInt();
		
		int n = scan.nextInt();
		int[] a = new int[n];
		for(int index=0; index<n; index++) {
			a[index] = scan.nextInt();
		}
		
		int m = scan.nextInt();
		int[] b = new int[m];
		for(int index=0; index<m; index++) {
			b[index] = scan.nextInt();
		}
		scan.close();
		// input end
		
		
		ArrayList<Integer> list1 = new ArrayList<>();
		for(int startIndex=0; startIndex<n; startIndex++) {
			int subArySum = 0;
			for(int endIndex=startIndex; endIndex<n; endIndex++) {
				subArySum += a[endIndex];
				list1.add(subArySum);
			}
		}
		
		ArrayList<Integer> list2 = new ArrayList<>();
		for(int startIndex=0; startIndex<m; startIndex++) {
			int subArySum = 0;
			for(int endIndex=startIndex; endIndex<m; endIndex++) {
				subArySum += b[endIndex];
				list2.add(subArySum);
			}
		}
		
		Collections.sort(list1);
		Collections.sort(list2);
		Collections.reverse(list2);
		
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
		int rightIndex = 0;
		long count = 0;
		
		while(leftIndex<list1Size && rightIndex<list2Size) {
			int leftSum = list1.get(leftIndex);
			int rightSum = list2.get(rightIndex);
					
			int sum = list1.get(leftIndex) + list2.get(rightIndex);
			
			if(sum == t) {
//				long leftCount = 1;
//				long rightCount = 1;
//				leftIndex++;
//				rightIndex++;
				
				long leftCount = 0;
				long rightCount = 0;
				
//				while(leftIndex<list1Size && list1.get(leftIndex)==list1.get(leftIndex-1)) {
				while(leftIndex<list1Size && leftSum==list1.get(leftIndex)) {
					leftCount++;
					leftIndex++;
				}
//				while(rightIndex<list2Size && list2.get(rightIndex)==list2.get(rightIndex-1)) {
				while(rightIndex<list2Size && rightSum==list2.get(rightIndex)) {
					rightCount++;
					rightIndex++;
				}
				count += leftCount*rightCount;
			}else if(sum < t) {
				leftIndex++;
			}else {
				rightIndex++;
			}
		}
		
		System.out.println(count);
	}

}



// 정답 코드
/*import java.util.*;
public class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i=0; i<n; i++) {
            a[i] = sc.nextInt();
        }
        int m = sc.nextInt();
        int[] b = new int[m];
        for (int i=0; i<m; i++) {
            b[i] = sc.nextInt();
        }
        ArrayList<Integer> first = new ArrayList<>();
        ArrayList<Integer> second = new ArrayList<>();
        for (int i=0; i<n; i++) {
            int sum = 0;
            for (int j=i; j<n; j++) {
                sum += a[j];
                first.add(sum);
            }
        }
        for (int i=0; i<m; i++) {
            int sum = 0;
            for (int j=i; j<m; j++) {
                sum += b[j];
                second.add(sum);
            }
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int x : second) {
            if (map.containsKey(x)) {
                int temp = map.get(x);
                map.put(x, temp+1);
            } else {
                map.put(x, 1);
            }
        }
        long ans = 0;
        for (int num : first) {
            if (map.containsKey(t-num)) {
                ans += map.get(t-num);
            }
        }
        System.out.println(ans);
    }
}
*/