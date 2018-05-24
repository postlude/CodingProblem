package baekjoon.algorithmCourse.bruteForce;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/**
 * 합이 0인 네 정수
 * 
 * 문제
 * 정수로 이루어진 크기가 같은 배열 A, B, C, D가 있다.
 * A[a], B[b], C[c], D[d]의 합이 0인 (a, b, c, d) 쌍의 개수를 구하는 프로그램을 작성하시오.
 * 
 * 
 * 입력
 * 첫째 줄에 배열의 크기 n (1 ≤ n ≤ 4000)이 주어진다. 다음 n개 줄에는 A, B, C, D에 포함되는 정수가 공백으로 구분되어져서 주어진다. 배열에 들어있는 정수의 절대값은 최대 228이다.
 * 
 * 출력
 * 합이 0이 되는 쌍의 개수를 출력한다.
 * 
 * 
 * 예제 입력 1
 * 6
 * -45 22 42 -16
 * -41 -27 56 30
 * -36 53 -37 77
 * -36 30 -75 -46
 * 26 -38 -10 62
 * -32 -54 -6 45
 * 
 * 예제 출력 1
 * 5
 */
public class P7453 {

	public static void main(String[] args) {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			){
			int n = Integer.parseInt(br.readLine());
			
			int[] a = new int[n];
			int[] b = new int[n];
			int[] c = new int[n];
			int[] d = new int[n];
			
			for(int count=0; count<n; count++) {
				String[] input = br.readLine().split(" ");
				a[count] = Integer.parseInt(input[0]);
				b[count] = Integer.parseInt(input[1]);
				c[count] = Integer.parseInt(input[2]);
				d[count] = Integer.parseInt(input[3]);
			}
			
			P7453 p7453 = new P7453();
			int result = 0;
			
			int[] abSum = new int[n*n];
			int[] cdSum = new int[n*n];
			int sumIndex = 0;
			for(int index1=0; index1<n; index1++) {
				for(int index2=0; index2<n; index2++) {
					abSum[sumIndex] = a[index1] + b[index2];
					cdSum[sumIndex] = c[index1] + d[index2];
					sumIndex++;
				}
			}
			
			Arrays.sort(cdSum);
			for(int sum : abSum) {
				result += p7453.calcUpperBound(cdSum, -sum) - p7453.calcLowerBound(cdSum, -sum);
			}
			bw.write(result + "\n");
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}

	// lower, upper bound index를 구할 때 아래 메소드처럼 1씩 증/감소 시키는 것보다
	// calcLowerBound(), calcUpperBound() 메소드에서처럼 중간 index로 이동하는게 이동 횟수가 적어서 더 빠르다.
	/*public int count(int[] ary, int target) {
		int lowerBound = -1;
		int upperBound = -1;
		int length = ary.length;
		
		for(int index=0; index<length; index++) {
			if(ary[index] == target) {
				lowerBound = index;
				break;
			}
		}
		
		for(int index=length-1; index>=0; index--) {
			if(ary[index] == target) {
				upperBound = index;
				break;
			}
		}
		
		if(lowerBound == -1) {
			return 0;
		}else {
			return upperBound - lowerBound + 1;
		}
	}*/
	
	public int calcLowerBound(int[] ary, int target) {
		int leftIndex = 0;
		int rightIndex = ary.length - 1;
		
		while(leftIndex < rightIndex) {
			int midIndex = (leftIndex+rightIndex) / 2;
			
			if(ary[midIndex] >= target) {
				rightIndex = midIndex;
			}else {
				leftIndex = midIndex + 1;
			}
		}
		return rightIndex;
	}
	
	public int calcUpperBound(int[] ary, int target) {
		int leftIndex = 0;
		int rightIndex = ary.length - 1;
		
		while(leftIndex < rightIndex) {
			int midIndex = (leftIndex+rightIndex) / 2;
			
			if(ary[midIndex] <= target) {
				leftIndex = midIndex + 1;
			}else {
				rightIndex = midIndex;
			}
		}
		return leftIndex;
	}
}



// 정답 코드
/*import java.util.*;
import java.io.*;
public class Main {
    static int upper_bound(int[] a, int val) {
        int left = 0;
        int right = a.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (a[mid] <= val) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
    static int lower_bound(int[] a, int val) {
        int left = 0;
        int right = a.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (a[mid] >= val) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
    public static void main(String args[]) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.valueOf(bf.readLine());
        int[] a = new int[n];
        int[] b = new int[n];
        int[] c = new int[n];
        int[] d = new int[n];
        for (int i=0; i<n; i++) {
            String[] line = bf.readLine().split(" ");
            a[i] = Integer.valueOf(line[0]);
            b[i] = Integer.valueOf(line[1]);
            c[i] = Integer.valueOf(line[2]);
            d[i] = Integer.valueOf(line[3]);
        }
        int[] first = new int[n*n];
        int[] second = new int[n*n];
        int p=0;
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                first[p] = a[i]+b[j];
                second[p] = c[i] + d[j];
                p += 1;
            }
        }
        Arrays.sort(second);
        long ans = 0;
        for (int num : first) {
            int lower = lower_bound(second, -num);
            int upper = upper_bound(second, -num);
            ans += upper - lower;
        }
        System.out.println(ans);
    }
}*/