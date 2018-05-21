package baekjoon.algorithmCourse.bruteForce;

import java.util.Scanner;

/**
 * 소수의 연속합
 * 
 * 문제
 * 하나 이상의 연속된 소수의 합으로 나타낼 수 있는 자연수들이 있다. 몇 가지 자연수의 예를 들어 보면 다음과 같다.
 * 
 * 3 : 3 (한 가지)
 * 41 : 2+3+5+7+11+13 = 11+13+17 = 41 (세 가지)
 * 53 : 5+7+11+13+17 = 53 (두 가지)
 * 
 * 하지만 연속된 소수의 합으로 나타낼 수 없는 자연수들도 있는데, 20이 그 예이다. 7+13을 계산하면 20이 되기는 하나 7과 13이 연속이 아니기에 적합한 표현이 아니다.
 * 또한 한 소수는 반드시 한 번만 덧셈에 사용될 수 있기 때문에, 3+5+5+7과 같은 표현도 적합하지 않다.
 * 2 이상의 자연수가 주어졌을 때, 이 자연수를 연속된 소수의 합으로 나타낼 수 있는 경우의 수를 구하는 프로그램을 작성하시오.
 * 
 * 
 * 입력
 * 첫째 줄에 자연수 N이 주어진다. (1 ≤ N ≤ 4,000,000)
 * 
 * 출력
 * 첫째 줄에 자연수 N을 연속된 소수의 합으로 나타낼 수 있는 경우의 수를 출력한다.
 * 
 * 
 * 예제 입력 1
 * 20
 * 
 * 예제 출력 1
 * 0
 * 
 * 
 * 예제 입력 2
 * 3
 * 
 * 예제 출력 2
 * 1
 * 
 * 
 * 예제 입력 3
 * 41
 * 
 * 예제 출력 3
 * 3
 * 
 * 
 * 예제 입력 4
 * 53
 * 
 * 예제 출력 4
 * 2
 */
public class P1644 {
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		scan.close();
		
		P1644 p1644 = new P1644();
		
		int[] primeNum = new int[n];

		int primeNumIndex = 0;
		for(int num=2; num<=n; num++) {
			if(p1644.isPrime(num)) {
				primeNum[primeNumIndex] = num;
				primeNumIndex++;
			}
		}
		
		
		int rightIndex = 0;
		int leftIndex = 0;
		int count = 0;
		int sum = primeNum[0];
		
		while(rightIndex<primeNumIndex && leftIndex<primeNumIndex) {
			if(sum == n) {
				count++;
				rightIndex++;
				if(rightIndex < n) {
					sum += primeNum[rightIndex];
				}
			}else if(sum < n) {
				rightIndex++;
				if(rightIndex < n) {
					sum += primeNum[rightIndex];
				}
			}else if(sum > n) {
				sum -= primeNum[leftIndex];
				leftIndex++;
			}
		}
		
		System.out.println(count);
	}

	
	public boolean isPrime(int num)	{
	    if( num <= 1 )
	        return false; //1은 소수가 아니다.
	         
	    if( (num&1) == 0 ) //짝수는
	        return (num == 2); //2이면 true 아니면 소수아니다
	         
	    for(int i=3; i*i<=num; i++) { // i = 3 ~ sqrt(n) 까지의 홀수
	        if( num % i == 0) {//i가 n의 약수라면
	            return false; //약수존재. 소수아니다.
	        }
	    }
	    
	    return true; //소수이다   
	}
}



// 정답 코드
/*import java.util.*;

public class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        boolean[] check = new boolean[n+1];
        List<Integer> primeNum = new ArrayList<Integer>();
        
        for (int i=2; i<=n; i++) {
            if (!check[i]) {
            	// 소수에 추가하고
                primeNum.add(i);
                // 추가한 소수의 배수들은 전부 소수에서 제외
                for (int j=i*2; j<=n; j+=i) {
                    check[j] = true;
                }
            }
        }
        int l=0;
        int r=0;
        int sum = primeNum.size() == 0 ? 0 : primeNum.get(0);
        int ans = 0;

        while (l <= r && r < primeNum.size()) {
            if (sum <= n) {
                if (sum == n) {
                    ans += 1;
                }
                r++;
                if (r < primeNum.size()) {
                    sum += primeNum.get(r);
                }
            } else {
                sum -= primeNum.get(l++);
            }
        }
        System.out.println(ans);
    }
}*/