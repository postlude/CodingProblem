package baekjoon.algorithmcourse;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 로또
 * 
 * 문제
 * 독일 로또는 {1, 2, ..., 49}에서 숫자 6개를 고른다.
 * 로또 번호를 선택하는데 사용되는 가장 유명한 전략은 49가지 숫자 중 k(k>6)개의 숫자를 골라 집합 S를 만든 다음 그 숫자만 가지고 번호를 선택하는 것이다.
 * 예를 들어, k=8, S={1,2,3,5,8,13,21,34}인 경우 이 집합 S에서 숫자를 고를 수 있는 경우의 수는 총 28가지이다.
 * ([1,2,3,5,8,13], [1,2,3,5,8,21], [1,2,3,5,8,34], [1,2,3,5,13,21], ..., [3,5,8,13,21,34])
 * 집합 S와 k가 주어졌을 때, 숫자를 고르는 모든 방법을 구하는 프로그램을 작성하시오.
 * 
 * 입력
 * 입력은 여러 개의 테스트 케이스로 이루어져 있다. 각 테스트 케이스는 한 줄로 이루어져 있다. 첫 번째 숫자는 k (6 < k < 13)이고, 다음 k개 숫자는 집합 S에 포함되는 수이다.
 * S의 원소는 오름차순으로 주어진다.
 * 입력의 마지막 줄에는 0이 하나 주어진다. 
 * 
 * 출력
 * 각 테스트 케이스 마다 숫자를 고르는 모든 방법을 출력한다. 이 때, 사전 순으로 출력한다.
 * 각 테스트 케이스 사이에는 빈 줄을 하나 출력한다.
 * 
 * 
 * 예제 입력 1
 * 7 1 2 3 4 5 6 7
 * 8 1 2 3 5 8 13 21 34
 * 0
 * 
 * 예제 출력 1
 * 1 2 3 4 5 6
 * 1 2 3 4 5 7
 * 1 2 3 4 6 7
 * 1 2 3 5 6 7
 * 1 2 4 5 6 7
 * 1 3 4 5 6 7
 * 2 3 4 5 6 7
 * 
 * 1 2 3 5 8 13
 * 1 2 3 5 8 21
 * 1 2 3 5 8 34
 * 1 2 3 5 13 21
 * 1 2 3 5 13 34
 * 1 2 3 5 21 34
 * 1 2 3 8 13 21
 * 1 2 3 8 13 34
 * 1 2 3 8 21 34
 * 1 2 3 13 21 34
 * 1 2 5 8 13 21
 * 1 2 5 8 13 34
 * 1 2 5 8 21 34
 * 1 2 5 13 21 34
 * 1 2 8 13 21 34
 * 1 3 5 8 13 21
 * 1 3 5 8 13 34
 * 1 3 5 8 21 34
 * 1 3 5 13 21 34
 * 1 3 8 13 21 34
 * 1 5 8 13 21 34
 * 2 3 5 8 13 21
 * 2 3 5 8 13 34
 * 2 3 5 8 21 34
 * 2 3 5 13 21 34
 * 2 3 8 13 21 34
 * 2 5 8 13 21 34
 * 3 5 8 13 21 34
 */
//조합 문제
//k개 중에서 6개를 뽑는다 = k개를 각각 뽑는다/안뽑는다
//뽑는다=1, 안뽑는다=2 으로 두고 k-1개의 2과 6개의 1의 순열
//(안뽑는 걸 2로 둔 이유는 출력시 사전 순으로 출력하므로)
public class P6603 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		ArrayList<String[]> testCase = new ArrayList<>();
		while(true){
			String input = scan.nextLine();
			if(input.equals("0")) {
				break;
			}
			String[] inputAry = input.split(" ");
			testCase.add(inputAry);
		}
		scan.close();
		//input end
		
		
		P6603 p6603 = new P6603();
		for(String[] testCaseAry : testCase) {
			int[] intAry = p6603.makeIntAry(testCaseAry);
			do {
				p6603.printArray(intAry, testCaseAry);
			}while(p6603.nextPermutation(intAry));
			System.out.println();
		}
	}
	
	public void printArray(int[] array, String[] testCaseAry) {
		for(int index=0; index<array.length; index++) {
			if(array[index] == 1) {// 해당 수를 뽑는 경우
				// testCaseAry의 맨 처음 값은 K이므로 index+1을 해야 해당 수
				System.out.print(testCaseAry[index+1] + " ");
			}
		}
		System.out.println();
	}
	
	public int[] makeIntAry(String[] testCaseAry) { 
		// 첫 번째 값은 K
		int[] intAry = new int[Integer.parseInt(testCaseAry[0])];
		
		for(int index=0; index<intAry.length; index++) {
			if(index < 6) {// 6개의 1
				intAry[index] = 1;
			}else {// K-1 개의 2
				intAry[index] = 2;
			}
		}
		
		return intAry;
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




// 정답 코드
/*import java.util.*;
public class Main {
    static boolean next_permutgetion(int[] a) {
        int i = a.length-1;
        while (i > 0 && a[i-1] >= a[i]) {
            i -= 1;
        }

        if (i <= 0) {
            return false;
        }

        int j = a.length-1;
        while (a[j] <= a[i-1]) {
            j -= 1;
        }

        int temp = a[i-1];
        a[i-1] = a[j];
        a[j] = temp;

        j = a.length-1;
        while (i < j) {
            temp = a[i];
            a[i] = a[j];
            a[j] = temp;
            i += 1;
            j -= 1;
        }
        return true;
    }
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            int n = sc.nextInt();
            if (n == 0) break;
            int[] a = new int[n];
            for (int i=0; i<n; i++) {
                a[i] = sc.nextInt();
            }
            int[] d = new int[n];
            for (int i=0; i<n; i++) {
                if (i < n-6) d[i] = 0;
                else d[i] = 1;
            }
            ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
            do {
                ArrayList<Integer> cur = new ArrayList<>();
                for (int i=0; i<n; i++) {
                    if (d[i] == 1) {
                        cur.add(a[i]);
                    }
                }
                ans.add(cur);
            } while (next_permutgetion(d));
            Collections.sort(ans, new Comparator<ArrayList<Integer>>() {
                public int compare(ArrayList<Integer> l1, ArrayList<Integer> l2) {
                    int n = l1.size();
                    int m = l2.size();
                    int i = 0;
                    while (i < n && i < m) {
                        int t1 = l1.get(i);
                        int t2 = l2.get(i);
                        if (t1 < t2) return -1;
                        else if (t1 > t2) return 1;
                        i += 1;
                    }
                    if (i == n && i != m) return -1;
                    else if (i != n && i == m) return 1;
                    return 0;
                }
            });
            for (ArrayList<Integer> v : ans) {
                for (int x : v) {
                    System.out.print(x + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }
}*/
