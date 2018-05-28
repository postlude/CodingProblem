package baekjoon.algorithmCourse.bruteForce;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * 집합
 * 
 * 문제
 * 비어있는 공집합 S가 주어졌을 때, 아래 연산을 수행하는 프로그램을 작성하시오.
 * 
 * add x: S에 x를 추가한다. (1 ≤ x ≤ 20) S에 x가 이미 있는 경우에는 연산을 무시한다.
 * remove x: S에서 x를 제거한다. (1 ≤ x ≤ 20) S에 x가 없는 경우에는 연산을 무시한다.
 * check x: S에 x가 있으면 1을, 없으면 0을 출력한다.
 * toggle x: S에 x가 있으면 x를 제거하고, 없으면 x를 추가한다. (1 ≤ x ≤ 20)
 * all: S를 {1, 2, ..., 20} 으로 바꾼다.
 * empty: S를 공집합으로 바꾼다. 
 * 
 * 입력
 * 첫째 줄에 수행해야 하는 연산의 수 M (1 ≤ M ≤ 3,000,000)이 주어진다.
 * 
 * 출력
 * check 연산이 주어질때마다, 결과를 출력한다.
 * 
 * 
 * 예제 입력 1
 * 26
 * add 1
 * add 2
 * check 1
 * check 2
 * check 3
 * remove 2
 * check 1
 * check 2
 * toggle 3
 * check 1
 * check 2
 * check 3
 * check 4
 * all
 * check 10
 * check 20
 * toggle 10
 * remove 20
 * check 10
 * check 20
 * empty
 * check 1
 * toggle 1
 * check 1
 * toggle 1
 * check 1
 * 
 * 예제 출력 1
 * 1
 * 1
 * 0
 * 1
 * 0
 * 1
 * 0
 * 1
 * 0
 * 1
 * 1
 * 0
 * 0
 * 0
 * 1
 * 0
 * 
 */
public class P11723 {

	public static void main(String[] args) {
		// scanner 사용시 시간 초과
//		Scanner scan = new Scanner(System.in);
//		int M = scan.nextInt();
		
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));	
			){
			int M = Integer.parseInt(br.readLine());
			int S = 0;
			
			for(int count=0; count<M; count++) {
//				String oper = scan.next();
				String[] input = br.readLine().split(" ");
				String oper = input[0];
				
				switch(oper) {
					case "add" : {
	//					int num = scan.nextInt();
						int num = Integer.parseInt(input[1]);
						S = S | (1<<num);
						break;
					}
					case "remove" : {
	//					int num = scan.nextInt();
						int num = Integer.parseInt(input[1]);
						S = S & ~(1<<num);
						break;
					}
					case "check" : {
	//					int num = scan.nextInt();
						int num = Integer.parseInt(input[1]);
	//					if((S&(1<<num)) == 1<<num) {
	//						System.out.println(1);
	//					}else {
	//						System.out.println(0);
	//					}
//						System.out.println((S&(1<<num))>>num);
						bw.write(((S&(1<<num))>>num) + "\n");
						break;
					}
					case "toggle" : {
	//					int num = scan.nextInt();
						int num = Integer.parseInt(input[1]);
						S = S ^ (1<<num);
						break;
					}
					case "all" : {
						// 아래 코드도 가능
	//					S = S | ~(1);
						S = S | (1<<21) - 1;
						break;
					}
					case "empty" : {
						S = 0;
						break;
					}
				}
			}
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
		
		
		// 시간 초과 남
//		HashSet<Integer> S = new HashSet<>();
		
		/*for(int count=0; count<M; count++) {
			String[] input = scan.nextLine().split(" ");
			String oper = input[0];
			int num = -1;
			if(input.length == 2) {
				num = Integer.parseInt(input[1]);
			}
			
			switch(oper) {
				case "add" : {
					S.add(num);
					break;
				}
				case "remove" : {
					S.remove(num);
					break;
				}
				case "check" : {
					if(S.contains(num)) {
						System.out.println(1);
					}else {
						System.out.println(0);
					}
					break;
				}
				case "toggle" : {
					if(S.contains(num)) {
						S.remove(num);
					}else {
						S.add(num);
					}
					break;
				}
				case "all" : {
					S.clear();
					for(int element=1; element<=20; element++) {
						S.add(element);
					}
					break;
				}
				case "empty" : {
					S.clear();
					break;
				}
			}
		}*/
		
//		scan.close();
	}
}
