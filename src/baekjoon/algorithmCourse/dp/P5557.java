package baekjoon.algorithmCourse.dp;

import java.util.Scanner;

/**
 * 1학년
 * 
 * 문제
 * 상근이가 1학년 때, 덧셈, 뺄셈을 매우 좋아했다. 상근이는 숫자가 줄 지어있는 것을 보기만 하면, 마지막 두 숫자 사이에 '='을 넣고, 나머지 숫자 사이에는 '+' 또는 '-'를 넣어 등식을 만들며 놀고 있다.
 * 예를 들어, "8 3 2 4 8 7 2 4 0 8 8"에서 등식 "8+3-2-4+8-7-2-4-0+8=8"을 만들 수 있다.
 * 상근이는 올바른 등식을 만들 수 있는 경우의 수를 만드려고 한다. 상근이는 아직 학교에서 음수를 배우지 않았고, 20을 넘는 수는 모른다. 따라서, 왼쪽부터 계산할 때, 중간에 나오는 수가 모두 0 이상 20 이하이어야 한다.
 * 예를 들어, "8+3+2-4-8-7+2+4+0+8=8"은 올바른 등식이지만, 8+3+2-4-8-7이 음수이기 때문에, 상근이가 만들 수 없는 등식이다.
 * 숫자가 주어졌을 때, 상근이가 만들 수 있는 올바른 등식의 수를 구하는 프로그램을 작성하시오.
 * 
 * 
 * 입력
 * 첫째 줄에 숫자의 개수 N이 주어진다. (3 ≤ N ≤ 100) 둘째 줄에는 0 이상 9 이하의 정수 N개가 공백으로 구분해 주어진다.
 * 
 * 출력
 * 첫째 줄에 상근이가 만들 수 있는 올바른 등식의 개수를 출력한다. 이 값은 2^63 - 1 이하이다.
 * 
 * 
 * 예제 입력 1
 * 11
 * 8 3 2 4 8 7 2 4 0 8 8
 * 
 * 예제 출력 1
 * 10
 */
/*
 * D[i][j] : i개의 숫자로 j를 만드는 경우의 수
 * ..+..-...+i번째 = j
 * ..+..-...-i번째 = j
 * D[i][j] = D[i-1][j-A[i]] + D[i-1][j+A[i]]
 */
public class P5557 {
//	static final int[] OPER = {1, -1};
	static long[][] NUM_OF_CASE;
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		
		int[] num = new int[n];
		for(int index=1; index<n; index++) {
			num[index] = scan.nextInt();
		}
		int targetSum = scan.nextInt();
		scan.close();
		
		P5557.NUM_OF_CASE = new long[n][21];
		// input end
		
		// bfs는 시간 초과
		/*Queue<Num> queue = new LinkedList<>();
		queue.add(new Num(0, -1));
		int count = 0;
		
		while(!queue.isEmpty()) {
			Num nowNum = queue.remove();

			int nextIndex = nowNum.numIndex + 1;
			if(nextIndex > n-2) {
				continue;
			}
			
			for(int index=0; index<2; index++) {
				int nextSum = nowNum.sum + P5557.OPER[index]*num[nextIndex];
				
				if(nextSum<0 || nextSum>20) {
					continue;
				}
				if(nextIndex == n-2) {
					if(nextSum == num[n-1]) {
						count++;
					}
				}else {
					queue.add(new Num(nextSum, nextIndex));
				}
			}
		}
		
		System.out.println(count);*/
		
		
		P5557 p5557 = new P5557();
		System.out.println(p5557.calcNumOfCase(num, n, targetSum));
		
	}

	public long calcNumOfCase(int[] num, int n, int targetSum) {
		P5557.NUM_OF_CASE[1][num[1]] = 1;
		
		for(int numIndex=2; numIndex<n; numIndex++) {
			for(int sum=0; sum<=20; sum++) {
				if(sum-num[numIndex] >= 0) {
					P5557.NUM_OF_CASE[numIndex][sum] += P5557.NUM_OF_CASE[numIndex-1][sum-num[numIndex]];
				}
				if(sum+num[numIndex] <= 20) {
					P5557.NUM_OF_CASE[numIndex][sum] += P5557.NUM_OF_CASE[numIndex-1][sum+num[numIndex]];
				}
			}
		}
		
		return P5557.NUM_OF_CASE[n-1][targetSum];
	}
}

/*class Num{
	int sum;
	int numIndex;
	
	public Num(int sum, int numIndex) {
		this.sum = sum;
		this.numIndex = numIndex;
	}
}*/