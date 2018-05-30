package baekjoon.algorithmCourse.etc;

import java.util.Scanner;

/**
 * 수 이어 쓰기 2
 * 
 * 문제
 * 1부터 N까지의 수를 이어서 쓰면 다음과 같이 새로운 하나의 수를 얻을 수 있다.
 * 1234567891011121314151617181920212223...
 * 이렇게 만들어진 새로운 수에서, 앞에서 k번째 자리 숫자가 어떤 숫자인지 구하는 프로그램을 작성하시오.
 * 
 * 
 * 입력
 * 첫째 줄에 N(1≤N≤100,000,000)과,  k(1≤k≤1,000,000,000)가 주어진다. N과 k 사이에는 공백이 하나 이상 있다.
 * 
 * 출력
 * 첫째 줄에 앞에서 k번째 자리 숫자를 출력한다. 수의 길이가 k보다 작아서 k번째 자리 숫자가 없는 경우는 -1을 출력한다.
 * 
 * 
 * 예제 입력 1
 * 20 23
 * 
 * 예제 출력 1
 * 6
 */
public class P1790 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		long k = scan.nextLong();
		scan.close();
		
		P1790 p1790 = new P1790();

		if (p1790.calcLength(n) < k) {
			System.out.println(-1);
//			System.exit(0);
			return;
		}
		
		int startNum = 1;
		int endNum = n;
		int resultNum = 0;
		while(startNum <= endNum) {
			int midNum = (startNum+endNum)/2; 
			long length = p1790.calcLength(midNum);
			if(length == k) {
				String str = String.valueOf(midNum);
				System.out.println(str.charAt(str.length()-1));
				return;
			}else if(length > k) {
				endNum = midNum - 1;
				resultNum = midNum;
			}else {
				startNum = midNum + 1;
			}
		}
		
		String str = String.valueOf(resultNum);
		long resultLength = p1790.calcLength(resultNum);
		System.out.println(str.charAt(str.length()-(int)(resultLength-k)-1));
	}

	public long calcLength(int endNum) {
		int nDigit = 0;
		int nNum = endNum;
		while(nNum > 0) {
			nNum = nNum/10;
			nDigit++;
		}
		
		int digit = 1;
		long length = 0;
		
		while(digit < nDigit) {
			length += 9 * (int)Math.pow(10.0, digit-1) * digit;
			digit++;
		}
		
		int startNum = (int)Math.pow(10.0, digit-1);
		length += digit;
		
		while(true) {
			if(startNum == endNum) {
				break;
			}
			length += digit;
			startNum++;
		}
		
		return length;
	}
}




// 정답 코드
/*import java.util.*;

public class Main {
    static long calc(int n) {
        long ans = 0;
        for (int start=1, len=1; start<=n; start*=10, len++) {
            int end = start*10-1;
            if (end > n) {
                end = n;
            }
            ans += (long)(end - start + 1) * len;
        }
        return ans;
    }
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long k = sc.nextLong();
        if (calc(n) < k) {
            System.out.println(-1);
            System.exit(0);
        }
        int left = 1;
        int right = n;
        int ans = 0;
        while (left <= right) {
            int mid = (left+right)/2;
            long len = calc(mid);
            if (len < k) {
                left = mid+1;
            } else {
                ans = mid;
                right = mid-1;
            }
        }
        String s = Integer.toString(ans);
        long l = calc(ans);
        System.out.println(s.charAt(s.length()-(int)(l-k)-1));
    }
}*/