package baekjoon.algorithmCourse.bruteForce;

import java.util.Scanner;

/**
 * 수들의 합 2
 * 
 * 문제
 * N개의 수로 된 수열 A[1], A[2], …, A[N] 이 있다. 이 수열의 i번째 수부터 j번째 수까지의 합 A[i]+A[i+1]+…+A[j-1]+A[j]가 M이 되는 경우의 수를 구하는 프로그램을 작성하시오.
 * 
 * 
 * 입력
 * 첫째 줄에 N(1≤N≤10,000), M(1≤M≤300,000,000)이 주어진다. 다음 줄에는 A[1], A[2], …, A[N]이 공백으로 분리되어 주어진다. 각각의 A[x]는 30,000을 넘지 않는 자연수이다.
 * 
 * 출력
 * 첫째 줄에 경우의 수를 출력한다.
 * 
 * 
 * 예제 입력 1
 * 4 2
 * 1 1 1 1
 * 
 * 예제 출력 1
 * 3
 * 
 * 
 * 예제 입력 2
 * 10 5
 * 1 2 3 4 2 5 3 1 1 2
 * 
 * 예제 출력 2
 * 3
 */
public class P2003 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int m = scan.nextInt();
		
		int[] sequence = new int[n];
		for(int index=0; index<n; index++) {
			sequence[index] = scan.nextInt();
		}
		scan.close();
		// input end
		
		
		int rightIndex = 0;
		int leftIndex = 0;
		int count = 0;
		int sum = sequence[0];
		
		// leftIndex에서 rightIndex까지의 합을 기준으로 m보다 크면 leftIndex 증가, 작으면 rightIndex를 증가시키는 방법으로 진행
		while(rightIndex<n && leftIndex<n) {
			if(sum == m) {
				count++;
				rightIndex++;
				if(rightIndex < n) {
					sum += sequence[rightIndex];
				}
			}else if(sum < m) {
				rightIndex++;
				if(rightIndex < n) {
					sum += sequence[rightIndex];
				}
			}else if(sum > m) {
				sum -= sequence[leftIndex];
				leftIndex++;
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
        int n = sc.nextInt();
        int s = sc.nextInt();
        int[] a = new int[n+1];
        for (int i=0; i<n; i++) {
            a[i] = sc.nextInt();
        }
        int left=0;
        int right=0;
        int sum=a[0];
        int ans=0;
        while (left <= right && right < n) {
            if (sum < s) {
                right += 1;
                sum += a[right];
            } else if (sum == s) {
                ans += 1;
                right += 1;
                sum += a[right];
            } else if (sum > s) {
                sum -= a[left];
                left++;
                if (left > right && left < n) {
                    right = left;
                    sum = a[left];
                }
            }
        }
        System.out.println(ans);
    }
}*/