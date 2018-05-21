package baekjoon.algorithmCourse.bruteForce;

import java.util.Scanner;

/**
 * 부분합
 * 
 * 문제
 * 10,000 이하의 자연수로 이루어진 길이 N짜리 수열이 주어진다. 이 수열에서 연속된 수들의 부분합 중에 그 합이 S 이상이 되는 것 중, 가장 짧은 것의 길이를 구하는 프로그램을 작성하시오.
 * 
 * 
 * 입력
 * 첫째 줄에 N (10 ≤ N < 100,000)과 S (0 < S ≤ 100,000,000)가 주어진다. 둘째 줄에는 수열이 주어진다. 수열의 각 원소는 공백으로 구분되어져 있으며, 10,000이하의 자연수이다.
 * 
 * 출력
 * 첫째 줄에 구하고자 하는 최소의 길이를 출력한다. 만일 그러한 합을 만드는 것이 불가능하다면 0을 출력하면 된다.
 * 
 * 
 * 예제 입력 1
 * 10 15
 * 5 1 3 5 10 7 4 9 2 8
 * 
 * 예제 출력 1
 * 2
 */
public class P1806 {

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
		int minLength = n;
		int count = 0;
		int sum = sequence[0];
		
		while(rightIndex<n && leftIndex<n) {
			if(sum < m) {
				rightIndex++;
				if(rightIndex < n) {
					sum += sequence[rightIndex];
				}
			}else if(sum >= m) {
				count++;
				int currentLength = rightIndex - leftIndex + 1;
				if(currentLength < minLength) {
					minLength = currentLength;
				}
				sum -= sequence[leftIndex];
				leftIndex++;
			}
		}
		
		if(count != 0) {
			System.out.println(minLength);
		}else {
			System.out.println(0);
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
        int[] a = new int[n+1];
        for (int i=0; i<n; i++) {
            a[i] = sc.nextInt();
        }
        int i=0;
        int j=0;
        int sum=a[0];
        int ans=n+1;
        while (i <= j && j < n) {
            if (sum < s) {
                j += 1;
                sum += a[j];
            } else if (sum == s) {
                ans = Math.min(j-i+1,ans);
                j += 1;
                sum += a[j];
            } else if (sum > s) {
                ans = Math.min(j-i+1,ans);
                sum -= a[i];
                i++;
            }
        }
        if (ans > n) {
            ans = 0;
        }
        System.out.println(ans);
    }
}*/