package baekjoon.algorithmCourse.bruteForce;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * 문제
 * 최근에 ICPC 탐사대는 남아메리카의 잉카 제국이 놀라운 문명을 지닌 카잉 제국을 토대로 하여 세워졌다는 사실을 발견했다. 카잉 제국의 백성들은 특이한 달력을 사용한 것으로 알려져 있다.
 * 그들은 M 과 N 보다 작거나 같은 두 개의 자연수 x, y를 가지고 각 년도를 <x:y>와 같은 형식으로 표현하였다. 그들은 이 세상의 시초에 해당하는 첫 번째 해를 <1:1>로 표현하고,
 * 두 번째 해를 <2:2>로 표현하였다. <x:y>의 다음 해를 표현한 것을 <x':y'>이라고 하자. 만일 x < M 이면 x' = x + 1이고, 그렇지 않으면 x' = 1이다.
 * 같은 방식으로 만일 y < N이면 y' = y + 1이고, 그렇지 않으면 y' = 1이다. <M:N>은 그들 달력의 마지막 해로서, 이 해에 세상의 종말이 도래한다는 예언이 전해 온다. 
 * 예를 들어, M = 10 이고 N = 12라고 하자. 첫 번째 해는 <1:1>로 표현되고, 11 번째 해는 <1:11>로 표현된다. <3:1>은 13 번째 해를 나타내고, <10:12>는 마지막인 60 번째 해를 나타낸다. 
 * 네 개의 정수 M, N, x 와 y가 주어질 때, <M:N>이 카잉 달력의 마지막 해라고 하면 <x:y>는 몇 번째 해를 나타내는 지를 구하는 프로그램을 작성하라. 
 * 
 * 입력
 * 입력 데이터는 표준 입력을 사용한다. 입력은 T개의 테스트 데이터로 구성된다. 입력의 첫 번째 줄에는 입력 데이터의 수를 나타내는 정수 T가 주어진다. 각 테스트 데이터는 한 줄로 구성된다.
 * 각 줄에는 네 개의 정수 M, N, x와 y가 주어진다. (1 ≤ M, N ≤ 40,000, 1 ≤ x ≤ M, 1 ≤ y ≤ N) 여기서 <M:N>은 카잉 달력의 마지막 해를 나타낸다.
 * 
 * 출력
 * 출력은 표준 출력을 사용한다. 각 테스트 데이터에 대해, 정수 k를 한 줄에 출력한다. 여기서 k는 <x:y>가 k번째 해를 나타내는 것을 의미한다.
 * 만일 <x:y>에 의해 표현되는 해가 없다면, 즉, <x:y>가 유효하지 않은 표현이면, -1을 출력한다.
 * 
 * 
 * 예제 입력 1
 * 3
 * 10 12 3 9
 * 10 12 7 2
 * 13 11 5 6
 * 
 * 예제 출력 1
 * 33
 * -1
 * 83
 */
public class P6064 {
	public static void main(String[] args) {
		//16억은 너무 큰 수
//		System.out.println(40000*40000);
		
		
		//input
		Scanner scan = new Scanner(System.in);
//		int T = scan.nextInt();
		int T = Integer.parseInt(scan.nextLine());
		LinkedList<String> testCaseList = new LinkedList<>();
		
		for(int testCaseCount=0; testCaseCount<T; testCaseCount++) {
//			int M = scan.nextInt();
//			int N = scan.nextInt();
//			int x = scan.nextInt();
//			int y = scan.nextInt();
			
			testCaseList.add(scan.nextLine());
		}
		scan.close();
		
		
		//print
		P6064 p6064 = new P6064();
		for(String testCaseStr : testCaseList) {
			String[] testCaseAry = testCaseStr.split(" ");
			int M = Integer.parseInt(testCaseAry[0]);
			int N = Integer.parseInt(testCaseAry[1]);
			int x = Integer.parseInt(testCaseAry[2]);
			int y = Integer.parseInt(testCaseAry[3]);
			
			System.out.println(p6064.calcYear(M, N, x, y));
		}
	}

	public int calcYear(int M, int N, int x, int y) {
		/*
		 * x와 M만 생각해보면 x,y의 위치의 년도 값은 i*M+x
		 * i는 M을 반복한 횟수
		 * 이 값과 y 기준에서 계산한 값(j*N+y)이 같은 값이 존재하면 해당 값이 구하고자하는 년도
		 * i값에 대해 j가 존재하는지 확인
		 * i가 가장 클 때는 M=1, N=40000, x=0, y=40000 일 때 for문 40000 돌게 됨
		 */
		for(int i=0; i<40000; i++) {
			if((i*M+x-y)%N == 0) {
				return (i*M + x);
			}
		}
		return -1;
	}
}


//정답 소스
/*import java.util.*;
import java.io.*;
public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.valueOf(bf.readLine());
        while (t-- > 0) {
            String[] line = bf.readLine().split(" ");
            int m = Integer.valueOf(line[0]);
            int n = Integer.valueOf(line[1]);
            int x = Integer.valueOf(line[2])-1;
            int y = Integer.valueOf(line[3])-1;
            boolean ok = false;
            for (int k=x; k<(n*m); k+=m) {
                if (k%n == y) {
                    System.out.println(k+1);
                    ok = true;
                    break;
                }
            }
            if (!ok) {
                System.out.println(-1);
            }
        }
    }
}*/
