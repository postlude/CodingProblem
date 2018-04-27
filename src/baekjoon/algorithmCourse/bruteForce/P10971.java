package baekjoon.algorithmCourse.bruteForce;

import java.util.Scanner;

/**
 * 외판원 순회 2
 * 
 * 문제
 * 외판원 순회 문제는 영어로 Traveling Salesman problem (TSP) 라고 불리는 문제로 computer science 분야에서 가장 중요하게 취급되는 문제 중 하나이다. 여러 가지 변종 문제가 있으나, 여기서는 가장 일반적인 형태의 문제를 살펴보자.
 * 1번부터 N번까지 번호가 매겨져 있는 도시들이 있고, 도시들 사이에는 길이 있다. (길이 없을 수도 있다) 이제 한 외판원이 어느 한 도시에서 출발해 N개의 도시를 모두 거쳐 다시 원래의 도시로 돌아오는 순회 여행 경로를 계획하려고 한다.
 * 단, 한번 갔던 도시로는 다시 갈 수 없다. (맨 마지막에 여행을 출발했던 도시로 돌아오는 것은 예외) 이런 여행 경로는 여러 가지가 있을 수 있는데, 가장 적은 비용을 들이는 여행 계획을 세우고자 한다.
 * 각 도시간에 이동하는데 드는 비용은 행렬 W[i][j]형태로 주어진다. W[i][j]는 도시 i에서 도시 j로 가기 위한 비용을 나타낸다.
 * 비용은 대칭적이지 않다. 즉, W[i][j] 는 W[j][i]와 다를 수 있다. 모든 도시간의 비용은 양의 정수이다. W[i][i]는 항상 0이다. 경우에 따라서 도시 i에서 도시 j로 갈 수 없는 경우도 있으며 이럴 경우 W[i][j]=0이라고 하자.
 * N과 비용 행렬이 주어졌을 때, 가장 적은 비용을 들이는 외판원의 순회 여행 경로를 구하는 프로그램을 작성하시오.
 * 
 * 입력
 * 첫째 줄에 도시의 수 N이 주어진다. (2<=N<=10) 다음 N개의 줄에는 비용 행렬이 주어진다. 각 행렬의 성분은 1,000,000 이하의 양의 정수이며, 갈 수 없는 경우는 0이 주어진다. W[i][j]는 도시 i에서 j로 가기 위한 비용을 나타낸다.
 * 항상 순회할 수 있는 경우만 입력으로 주어진다.
 * 
 * 출력
 * 첫째 줄에 외판원의 순회에 필요한 최소 비용을 출력한다.
 * 
 * 
 * 예제 입력 1
 * 4
 * 0 10 15 20
 * 5  0  9 10
 * 6 13  0 12
 * 8  8  9  0
 * 
 * 예제 출력 1
 * 35
 */
//시간 복잡도 : O(N*N!)
//모든 경우의 수 : N!, 비용 계산 : N
public class P10971 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int N = Integer.parseInt(scan.nextLine());
		
		int[][] cost = new int[N][N];
		int[] route = new int[N];
		
		for(int index1=0; index1<N; index1++) {
			route[index1] = index1;
			for(int index2=0; index2<N; index2++) {
				cost[index1][index2] = scan.nextInt();
			}
		}
		
		scan.close();
		//input end
		
		
		P10971 p10971 = new P10971();
		
		//최대값은 N=10, 비용이 전부 1000000 일 때 
		int minCost = 10000000;
		do {
			// 시작 점을 고정시켜도 무방하므로 제일 처음 값인 0일 때만 계산하면 반복 횟수가 줄어들게 됨
			if(route[0] != 0) break;
			
			int calcCost = p10971.calcCost(cost, route);
			// -1인 경우는 이동 불가능한 경우이므로 다음 경우로 넘어감
			if(calcCost == -1) {
				continue;
			}else if(calcCost < minCost) {
				minCost = calcCost;
			}
		}while(p10971.nextPermutation(route));
		
		System.out.println(minCost);
	}

	public int calcCost(int[][] cost, int[] route) {
		int totalCost = 0;
		
		for(int index=0; index<route.length; index++) {
			//presentCost 초기값은 불가능한 음수로
			int presentCost = -1;
			if(index == route.length-1) {
				presentCost = cost[route[index]][route[0]];
			}else {
				presentCost = cost[route[index]][route[index+1]];
			}
			
			// 비용이 0인 경우는 이동이 불가능한 경우이므로 비용을 음수 리턴
			if(presentCost == 0) {
				return -1;
			}else {// 그 외의 경우에는 이동 가능하므로 누적
				totalCost += presentCost;
			}
		}
		
		return totalCost;
	}
	
	public boolean nextPermutation(int[] permutationArray) {
		int sizeOfAry = permutationArray.length;
		
		//부호 방향이 < 인 순간을 찾을 index
		int index = sizeOfAry - 1;
		//맨 뒤에서부터 이전 값과 비교하여 현재 값이 더 커지는 순간에 while 종료
		while(index>0 && permutationArray[index-1]>=permutationArray[index]) {
			index--;
		}
		//while이 끝까지 돌았다는 것은 제일 마지막 순열이라는 의미이므로 다음 순열이 존재하지 않는다.
		if(index == 0) {
			return false;
		}
		
		//permutationArray[index-1] 보다 크면서 가장 작은 수의 index
		int swapIndex = sizeOfAry - 1;
		while(permutationArray[index-1] >= permutationArray[swapIndex]) {
			swapIndex--;
		}
		
		//index-1의 값과 swapIndex의 값을 swap
		int temp = permutationArray[index-1];
		permutationArray[index-1] = permutationArray[swapIndex];
		permutationArray[swapIndex] = temp;
		
		//permutationArray의 index부터 마지막까지 값의 순서를 거꾸로
		int endIndex = sizeOfAry - 1;
		while(index < endIndex) {
			//swap
			temp = permutationArray[index];
			permutationArray[index] = permutationArray[endIndex];
			permutationArray[endIndex] = temp;
			
			//index 변경
			index++;
			endIndex--;
		}
		return true;
	}
}
