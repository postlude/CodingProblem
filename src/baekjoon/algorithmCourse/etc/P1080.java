package baekjoon.algorithmCourse.etc;

import java.util.Scanner;

/**
 * 행렬
 * 
 * 문제
 * 0과 1로만 이루어진 행렬 A와 행렬 B가 있다. 이 때, 행렬 A를 행렬 B로 바꾸는데 필요한 연산의 횟수의 최소값을 구하는 프로그램을 작성하시오.
 * 행렬을 변환하는 연산은 어떤 3*3크기의 부분 행렬에 있는 모든 원소를 뒤집는 것이다. (0 -> 1, 1 -> 0)
 * 
 * 
 * 입력
 * 첫째 줄에 행렬의 크기 N M이 주어진다. N과 M은 50보다 작거나 같은 자연수이다. 둘째 줄부터 N개의 줄에는 행렬 A가 주어지고, 그 다음줄부터 N개의 줄에는 행렬 B가 주어진다.
 * 
 * 출력
 * 첫째 줄에 문제의 정답을 출력한다. 만약 A를 B로 바꿀 수 없다면 -1을 출력한다.
 * 
 * 
 * 예제 입력 1
 * 3 4
 * 0000
 * 0010
 * 0000
 * 1001
 * 1011
 * 1001
 * 
 * 예제 출력 1
 * 2
 */
/*
 * 예제 입력에서 (0, 0)을 변경하는 방법은 (0, 0)~(2, 2) 를 변경하는 방법밖에 없다.
 * (0, 0)을 확인 후 (0, 1)을 변경하는 방법은 마찬가지로 (0, 1)~(2, 3)을 변경하는 방법밖에 없다.
 * 동일한 방법으로 계속적으로 확인하면 됨 
 */
public class P1080 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int row = scan.nextInt();
		int col = scan.nextInt();
		
		String[] initMatrix = new String[row];
		for(int index=0; index<row; index++) {
			initMatrix[index] = scan.next();
		}
		
		String[] targetMatrix = new String[row];
		for(int index=0; index<row; index++) {
			targetMatrix[index] = scan.next();
		}
		scan.close();
		// input end

		
		P1080 p1080 = new P1080();
		int reverseCount = 0;
		
		for(int rowIndex=0; rowIndex<row-2; rowIndex++) {
			for(int colIndex=0; colIndex<col-2; colIndex++) {
				if(targetMatrix[rowIndex].charAt(colIndex) != initMatrix[rowIndex].charAt(colIndex)) {
					p1080.reverseMatrix(initMatrix, rowIndex, colIndex);
					reverseCount++;
				}
			}
		}
		
		for(int rowIndex=0; rowIndex<row; rowIndex++) {
			for(int colIndex=0; colIndex<col; colIndex++) {
				if(targetMatrix[rowIndex].charAt(colIndex) != initMatrix[rowIndex].charAt(colIndex)) {
					System.out.println(-1);
					return;
				}
			}
		}
		
		System.out.println(reverseCount);
	}

	public void reverseMatrix(String[] matrix, int startRow, int startCol) {
		for(int rowIndex=startRow; rowIndex<startRow+3; rowIndex++) {
			StringBuilder sb = new StringBuilder(matrix[rowIndex]);
			
			for(int colIndex=startCol; colIndex<startCol+3; colIndex++) {
				if(sb.charAt(colIndex) == '1') {
					sb.setCharAt(colIndex, '0');
				}else {
					sb.setCharAt(colIndex, '1');
				}
			}
			
			matrix[rowIndex] = sb.toString();
		}
	}
}
