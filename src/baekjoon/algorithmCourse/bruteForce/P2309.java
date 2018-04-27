package baekjoon.algorithmCourse.bruteForce;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 문제
 * 왕비를 피해 일곱 난쟁이들과 함께 평화롭게 생활하고 있던 백설공주에게 위기가 찾아왔다. 일과를 마치고 돌아온 난쟁이가 일곱 명이 아닌 아홉 명이었던 것이다.
 * 아홉 명의 난쟁이는 모두 자신이 "백설 공주와 일곱 난쟁이"의 주인공이라고 주장했다. 뛰어난 수학적 직관력을 가지고 있던 백설공주는, 다행스럽게도 일곱 난쟁이의 키의 합이 100이 됨을 기억해 냈다.
 * 아홉 난쟁이의 키가 주어졌을 때, 백설공주를 도와 일곱 난쟁이를 찾는 프로그램을 작성하시오.
 * 
 * 입력
 * 아홉 개의 줄에 걸쳐 일곱 난쟁이의 키가 주어진다. 주어지는 키는 100을 넘지 않는 자연수이며, 아홉 난쟁이의 키는 모두 다르며, 가능한 정답이 여러가지인 경우에는 아무거나 출력한다.
 * 
 * 출력
 * 일곱 난쟁이의 키를 오름차순으로 출력한다.
 * 
 * 예제 입력 1
 * 20
 * 7
 * 23
 * 19
 * 10
 * 15
 * 25
 * 8
 * 13
 * 
 * 예제 출력 1
 * 7
 * 8
 * 10
 * 13
 * 19
 * 20
 * 23
 */
public class P2309 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		P2309 p2309 = new P2309();
		
		int dwarfNum = 9;
		int[] tallAry = new int[dwarfNum];
		
		//input
		for(int count=0; count<dwarfNum; count++) {
			tallAry[count] = scan.nextInt();
		}
		scan.close();
		
		
		
		p2309.selectDwarf(tallAry, dwarfNum);
		p2309.printSortedAry(tallAry);
	}
	
	/**
	 * sort and print array method
	 * @param ary
	 */
	public void printSortedAry(int[] ary) {
		int aryLength = ary.length;
		
		Arrays.sort(ary);
		
		for(int index=2; index<aryLength; index++) {
//			if(ary[index] != 0) {
				System.out.println(ary[index]);
//			}
		}
	}

	/**
	 * calc array sum method
	 * @param ary
	 * @return
	 */
	public int sumOfAry(int[] ary) {
		int sum = 0;
		for(int aryNum : ary) {
			sum += aryNum;
		}
		
		return sum;
	}
	
	public void selectDwarf(int[] tallAry, int dwarfNum) {
//		int selectDwarfNum = 7;
//		int[] selectedDwarfAry = new int[selectDwarfNum];
		
		int tallSum = sumOfAry(tallAry);
		
		//선택하지 않을 난쟁이 2명
		for(int dwarf1=0; dwarf1<dwarfNum-1; dwarf1++) {
			for(int dwarf2=dwarf1+1; dwarf2<dwarfNum; dwarf2++) {
				if(tallSum-tallAry[dwarf1]-tallAry[dwarf2] == 100) {
					tallAry[dwarf1] = 0;
					tallAry[dwarf2] = 0;
					return;
				}
			}
		}
		
		
//		return selectedDwarfAry;
	}
}




//정답 코드
/*
import java.util.*;
public class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = 9;
        int[] a = new int[n];
        int sum = 0;
        for (int i=0; i<n; i++) {
            a[i] = sc.nextInt();
            sum += a[i];
        }
        Arrays.sort(a);
        for (int i=0; i<n; i++) {
            for (int j=i+1; j<n; j++) {
                if (sum - a[i] - a[j] == 100) {
                    for (int k=0; k<n; k++) {
                        if (i == k || j == k) continue;
                        System.out.println(a[k]);
                    }
                    System.exit(0);
                }
            }
        }
    }
}
*/