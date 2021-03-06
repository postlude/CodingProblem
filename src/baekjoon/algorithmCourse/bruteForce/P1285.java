package baekjoon.algorithmCourse.bruteForce;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 동전 뒤집기
 * 
 * 문제
 * N2개의 동전이 N행 N열을 이루어 탁자 위에 놓여 있다. 그 중 일부는 앞면(H)이 위를 향하도록 놓여 있고, 나머지는 뒷면(T)이 위를 향하도록 놓여 있다. <그림 1>은 N이 3일 때의 예이다.
 * 
 * HHT
 * THH
 * THT
 * 
 * 이들 N2개의 동전에 대하여 임의의 한 행 또는 한 열에 놓인 N개의 동전을 모두 뒤집는 작업을 수행할 수 있다.
 * 예를 들어 <그림 1>의 상태에서 첫 번째 열에 놓인 동전을 모두 뒤집으면 <그림 2>와 같이 되고, <그림 2>의 상태에서 첫 번째 행에 놓인 동전을 모두 뒤집으면 <그림 3>과 같이 된다.
 * 
 * THT			HTH
 * HHH			HHH
 * HHT			HHT
 * 
 * <그림 3>의 상태에서 뒷면이 위를 향하여 놓인 동전의 개수는 두 개이다. <그림 1>의 상태에서 이와 같이 한 행 또는 한 열에 놓인 N개의 동전을 모두 뒤집는 작업을 계속 수행할 때 뒷면이 위를 향하도록 놓인 동전의 개수를 2개보다 작게 만들 수는 없다.
 * N2개의 동전들의 초기 상태가 주어질 때, 한 행 또는 한 열에 놓인 N개의 동전을 모두 뒤집는 작업들을 수행하여 뒷면이 위를 향하는 동전 개수를 최소로 하려 한다. 이때의 최소 개수를 구하는 프로그램을 작성하시오.
 * 
 * 
 * 입력
 * 첫째 줄에 20이하의 자연수 N이 주어진다. 둘째 줄부터 N줄에 걸쳐 N개씩 동전들의 초기 상태가 주어진다.
 * 각 줄에는 한 행에 놓인 N개의 동전의 상태가 왼쪽부터 차례대로 주어지는데, 앞면이 위를 향하도록 놓인 경우 H, 뒷면이 위를 향하도록 놓인 경우 T로 표시되며 이들 사이에 공백은 없다.
 * 
 * 출력
 * 첫째 줄에 한 행 또는 한 열에 놓인 N개의 동전을 모두 뒤집는 작업들을 수행하여 뒷면이 위를 향하여 놓일 수 있는 동전의 최소 개수를 출력한다.
 * 
 * 
 * 예제 입력 1
 * 3
 * HHT
 * THH
 * THT
 * 
 * 예제 출력 1
 * 2
 */
public class P1285 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		
		String[] coin = new String[n];
		for(int index=0; index<n; index++) {
			coin[index] = scan.next();
		}
		scan.close();
		// input end
		
		P1285 p1285 = new P1285();
		
		int minTailNum = 400;
		for(int rowChange=0; rowChange<(1<<n); rowChange++) {
			String[] checkCoin = Arrays.copyOf(coin, n);
			
			for(int index=0; index<n; index++) {
				int reverseIndex = 1 << index;
				if((rowChange&reverseIndex) == reverseIndex) {
					checkCoin[index] = p1285.changeRow(checkCoin[index]);
				}
			}
			
			int tailNum = p1285.countTail(checkCoin, n);
			if(tailNum < minTailNum) {
				minTailNum = tailNum;
			}
		}
		
		System.out.println(minTailNum);
	}
	
	public int countTail(String[] coin, int size) {
		int tailNum = 0;
		
		for(int col=0; col<size; col++) {
			int columnTailNum = 0;
			int columnHeadNum = 0;
			for(int row=0; row<size; row++) {
				if(coin[row].charAt(col) == 'T') {
					columnTailNum++;
				}else {
					columnHeadNum++;
				}
			}
			
			if(columnTailNum > columnHeadNum) {
				tailNum += columnHeadNum;
			}else {
				tailNum += columnTailNum;
			}
		}

		return tailNum; 
	}
	
	public String changeRow(String row) {
		StringBuilder sb = new StringBuilder(row);
		
		for(int index=0; index<row.length(); index++) {
			if(sb.charAt(index) == 'H') {
				sb.setCharAt(index, 'T');
			}else {
				sb.setCharAt(index, 'H');
			}
		}
		
		return sb.toString();
	}
}



// 정답 코드
/*import java.util.*;
public class Main {
    static char flip(char x) {
        if (x == 'H') {
            return 'T';
        } else {
            return 'H';
        }
    }
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[] a = new String[n];
        for (int i=0; i<n; i++) {
            a[i] = sc.next();
        }
        int ans = n*n;
        for (int state=0; state<(1<<n); state++) {
            int sum = 0;
            for (int j=0; j<n; j++) {
                int cnt = 0;
                for (int i=0; i<n; i++) {
                    char cur = a[i].charAt(j);
                    if ((state & (1 << i)) != 0) {
                        cur = flip(cur);
                    }
                    if (cur == 'T') {
                        cnt += 1;
                    }
                }
                sum += Math.min(cnt, n-cnt);
            }
            if (ans > sum) ans = sum;
        }
        System.out.println(ans);
    }
}*/