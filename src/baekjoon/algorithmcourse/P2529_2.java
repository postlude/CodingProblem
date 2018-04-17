package baekjoon.algorithmcourse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * 부등호
 * 
 * 문제
 * 두 종류의 부등호 기호 ‘<’와 ‘>’가 k개 나열된 순서열  A가 있다. 우리는 이 부등호 기호 앞뒤에 서로 다른 한 자릿수 숫자를 넣어서 모든 부등호 관계를 만족시키려고 한다.
 * 예를 들어, 제시된 부등호 순서열 A가 다음과 같다고 하자. 
 * 
 *   A =>  < < < > < < > < >
 * 
 * 부등호 기호 앞뒤에 넣을 수 있는 숫자는 0부터 9까지의 정수이며 선택된 숫자는 모두 달라야 한다. 아래는 부등호 순서열 A를 만족시키는 한 예이다. 
 * 
 *   3 < 4 < 5 < 6 > 1 < 2 < 8 > 7 < 9 > 0
 * 
 * 이 상황에서 부등호 기호를 제거한 뒤, 숫자를 모두 붙이면 하나의 수를 만들 수 있는데 이 수를 주어진 부등호 관계를 만족시키는 정수라고 한다.
 * 그런데 주어진 부등호 관계를 만족하는 정수는 하나 이상 존재한다. 예를 들어 3456128790 뿐만 아니라 5689023174도 아래와 같이 부등호 관계 A를 만족시킨다. 
 * 
 *    5 < 6 < 8 < 9 > 0 < 2 < 3 > 1 < 7 > 4
 * 
 * 여러분은 제시된 k개의 부등호 순서를 만족하는 (k+1)자리의 정수 중에서 최대값과 최소값을 찾아야 한다.
 * 앞서 설명한 대로 각 부등호의 앞뒤에 들어가는 숫자는 { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 }중에서 선택해야 하며 선택된 숫자는 모두 달라야 한다. 
 * 
 * 입력
 * 첫 줄에 부등호 문자의 개수를 나타내는 정수 k가 주어진다. 그 다음 줄에는 k개의 부등호 기호가 하나의 공백을 두고 한 줄에 모두 제시된다. k의 범위는 2<=k<=9 이다. 
 * 
 * 출력
 * 여러분은 제시된 부등호 관계를 만족하는 k+1 자리의 최대, 최소 정수를 첫째 줄과 둘째 줄에 각각 출력해야 한다.
 * 단 아래 예(1)과 같이 첫 자리가 0인 경우도 정수에 포함되어야 한다. 모든 입력에 답은 항상 존재하며 출력 정수는 하나의 문자열이 되도록 해야 한다. 
 * 
 * 
 * 예제 입력 1
 * 2
 * < > 
 * 
 * 예제 출력 1
 * 897
 * 021
 * 
 */
// 재귀, 백트래킹으로 풀기
public class P2529_2 {
	static ArrayList<String> NUM_LIST = new ArrayList<>();
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		//부등호 개수
		int k = Integer.parseInt(scan.nextLine());
		String input = scan.nextLine();
		scan.close();
		
		String[] inequalitySign = input.split(" ");
		boolean[] numUseCheck = new boolean[10];
		
		P2529_2 p2529_2 = new P2529_2();
		p2529_2.checkInequalitySign(inequalitySign, numUseCheck, 0, "");
		
		Collections.sort(P2529_2.NUM_LIST);
		System.out.println(P2529_2.NUM_LIST.get(P2529_2.NUM_LIST.size()-1));
		System.out.println(P2529_2.NUM_LIST.get(0));
	}

	public void checkInequalitySign(String[] inequalitySign, boolean[] numUseCheck, int index, String numStr) {
		if(index == inequalitySign.length+1) {
			if(isSignTrue(inequalitySign, numStr)) {
				P2529_2.NUM_LIST.add(numStr);
				return;
			}
			return;
		}
		
		for(int num=0; num<=9; num++) {
			if(numUseCheck[num]) {
				continue;
			}
			numUseCheck[num] = true;
			checkInequalitySign(inequalitySign, numUseCheck, index+1, numStr+num);
			numUseCheck[num] = false;
		}
		
	}
	
	public boolean isSignTrue(String[] inequalitySignAry, String num) {
		for(int index=0; index<inequalitySignAry.length; index++) {
			String inequalitySign = inequalitySignAry[index];
			switch(inequalitySign) {
				case "<" : {
					// char형은 대소비교가 가능하므로 굳이 int로 변환하지 않고 char로 비교
					if(num.charAt(index) > num.charAt(index+1)) {
						return false;
					}
					break;
				}
				case ">" : {
					if(num.charAt(index) < num.charAt(index+1)) {
						return false;
					}
					break;
				}
			}
		}
		return true;
	}
}



// 정답 코드
/*import java.util.*;

public class Main {
    static int n;
    static char[] a = new char[20];
    static ArrayList<String> ans = new ArrayList<>();
    static boolean[] check = new boolean[10];
    static boolean ok(String num) {
        for (int i=0; i<n; i++) {
            if (a[i] == '<') {
                if (num.charAt(i) > num.charAt(i+1)) return false;
            } else if (a[i] == '>') {
                if (num.charAt(i) < num.charAt(i+1)) return false;
            }
        }
        return true;
    }
    static void go(int index, String num) {
        if (index == n+1) {
            if (ok(num)) {
                ans.add(num);
            }
            return;
        }
        for (int i=0; i<=9; i++) {
            if (check[i]) continue;
            check[i] = true;
            go(index+1, num+Integer.toString(i));
            check[i] = false;
        }
    }
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i=0; i<n; i++) {
            a[i] = sc.next().toCharArray()[0];
        }
        go(0, "");
        Collections.sort(ans);
        int m = ans.size();
        System.out.println(ans.get(m-1));
        System.out.println(ans.get(0));
    }
}*/