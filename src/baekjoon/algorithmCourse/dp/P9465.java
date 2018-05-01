package baekjoon.algorithmCourse.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * 스티커
 * 
 * 문제
 * 상근이의 여동생 상냥이는 문방구에서 스티커 2n개를 구매했다. 스티커는 그림 (a)와 같이 2행 n열로 배치되어 있다. 상냥이는 스티커를 이용해 책상을 꾸미려고 한다.
 * 상냥이가 구매한 스티커의 품질은 매우 좋지 않다. 스티커 한 장을 떼면, 그 스티커와 변을 공유하는 스티커는 모두 찢어져서 사용할 수 없게 된다. 즉, 뗀 스티커의 왼쪽, 오른쪽, 위, 아래에 있는 스티커는 사용할 수 없게 된다.
 * 
 * 모든 스티커를 붙일 수 없게된 상냥이는 각 스티커에 점수를 매기고, 점수의 합이 최대가 되게 스티커를 떼어내려고 한다. 먼저, 그림 (b)와 같이 각 스티커에 점수를 매겼다.
 * 상냥이가 뗄 수 있는 스티커의 점수의 최대값을 구하는 프로그램을 작성하시오. 즉, 2n개의 스티커 중에서 점수의 합이 최대가 되면서 서로 변을 공유 하지 않는 스티커 집합을 구해야 한다.
 * 
 * 위의 그림의 경우에 점수가 50, 50, 100, 60인 스티커를 고르면, 점수는 260이 되고 이 것이 최대 점수이다. 가장 높은 점수를 가지는 두 스티커 (100과 70)은 변을 공유하기 때문에, 동시에 뗄 수 없다.
 * 
 * 입력
 * 첫째 줄에 테스트 케이스의 개수 T가 주어진다. 각 테스트 케이스의 첫째 줄에는 n (1 ≤ n ≤ 100,000)이 주어진다. 다음 두 줄에는 n개의 정수가 주어지며, 각 정수는 그 위치에 해당하는 스티커의 점수이다.
 * 연속하는 두 정수 사이에는 빈 칸이 하나 있다. 점수는 0보다 크거나 같고, 100보다 작거나 같은 정수이다. 
 * 
 * 출력
 * 각 테스트 케이스 마다, 2n개의 스티커 중에서 두 변을 공유하지 않는 스티커 점수의 최대값을 출력한다.
 * 
 * 
 * 예제 입력 1
 * 2
 * 5
 * 50 10 100 20 40
 * 30 50 70 10 60
 * 7
 * 10 30 10 50 100 20 40
 * 20 40 30 50 60 20 80
 * 
 * 
 * 예제 출력 1
 * 260
 * 290
 * 
 */
/*
 * D[n][m] : 끝자리 뜯는 경우(m)에 따른 최대 점수값
 * m=0 : 마지막 2개를 둘 다 뜯지 않음
 * m=1 : 마지막 2개 중 위에 것만 뜯음
 * m=2 : 마지막 2개 중 아래것만 뜯음
 * D[n][0] = max(D[n-1][0], D[n-1][1], D[n-1][2])
 * D[n][1] = max(D[n-1][0], D[n-1][2]) + sticker[1][n]
 * D[n][2] = max(D[n-1][0], D[n-1][1]) + sticker[2][n]
 * D[n] = max(D[n][0], D[n][1], D[n][2])
 */
public class P9465 {
	public static void main(String[] args) {
		P9465 p9465 = new P9465();
		
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			){
			int T = Integer.parseInt(br.readLine());
			for(int count=0; count<T; count++) {
				int n = Integer.parseInt(br.readLine());
				
				int[][] sticker = new int[3][n+1];
				
				for(int row=1; row<3; row++) {
					String[] column = br.readLine().split(" ");
					for(int col=1; col<=n; col++) {
						sticker[row][col] = Integer.parseInt(column[col-1]);
					}
				}
				
				int[][] calcPoint = new int[n+1][3];
				
				/*int m1 = p9465.calcStickerPoint(n, 0, sticker, calcPoint);
				int m2 = p9465.calcStickerPoint(n, 1, sticker, calcPoint);
				int m3 = p9465.calcStickerPoint(n, 2, sticker, calcPoint);
//				System.out.println(Math.max(m1, Math.max(m2, m3)));
				int maxPoint = Math.max(m1, Math.max(m2, m3));*/
				
				int maxPoint = p9465.calcStickerPoint2(n, sticker, calcPoint);
				bw.write(maxPoint + "\n");
			}
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	/**
	 * 시간 초과
	 * @param n
	 * @param m
	 * @param sticker
	 * @param calcPoint
	 * @return n번째 뜯는 경우(m)에 따른 최대 점수 값
	 */
	public int calcStickerPoint(int n, int m, int[][] sticker, int[][] calcPoint) {
		if(n==1 && m==0) {
			return 0;
		}else if(n==1 && m==1) {
			return sticker[1][1];
		}else if(n==1 && m==2) {
			return sticker[2][1];
		}
		
		if(calcPoint[n][m] > 0) {
			return calcPoint[n][m];
		}
		
		
		int maxPoint = 0;
		if(calcPoint[n-1][0] > 0) {
			maxPoint = calcPoint[n-1][0];
		}else {
			maxPoint = calcStickerPoint(n-1, 0, sticker, calcPoint);
		}
		
		int m1 = 0;
		// m==0 || m==2
		if(m != 1) {
			if(calcPoint[n-1][1] > 0) {
				m1 = calcPoint[n-1][1];
			}else {
				m1 = calcStickerPoint(n-1, 1, sticker, calcPoint);
			}
			
			if(m1 > maxPoint) {
				maxPoint = m1;
			}
		}
		
		int m2 = 0;
		// m==0 || m==1
		if(m != 2) {
			if(calcPoint[n-1][2] > 0) {
				m2 = calcPoint[n-1][2];
			}else {
				m2 = calcStickerPoint(n-1, 2, sticker, calcPoint);
			}
			
			if(m2 > maxPoint) {
				maxPoint = m2;
			}
		}
		
		if(m == 0) {
			return maxPoint;
		}else if(m == 1) {
			return maxPoint + sticker[1][n];
		}else {
			return maxPoint + sticker[2][n];
		}
	}
	
	/**
	 * for문 이용(통과)
	 * @param n
	 * @param sticker
	 * @param calcPoint
	 * @return 최대 점수
	 */
	public int calcStickerPoint2(int n, int[][] sticker, int[][] calcPoint) {
		calcPoint[1][0] = 0;
		calcPoint[1][1] = sticker[1][1];
		calcPoint[1][2] = sticker[2][1];
		
		for(int index=2; index<=n; index++) {
			calcPoint[index][0] = Math.max(calcPoint[index-1][0], Math.max(calcPoint[index-1][1], calcPoint[index-1][2]));
			calcPoint[index][1] = Math.max(calcPoint[index-1][0], calcPoint[index-1][2]) + sticker[1][index];
			calcPoint[index][2] = Math.max(calcPoint[index-1][0], calcPoint[index-1][1]) + sticker[2][index];
		}
		
		return Math.max(calcPoint[n][0], Math.max(calcPoint[n][1], calcPoint[n][2]));
	}
}



// 정답 코드
/*import java.io.*;
import java.util.*;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.valueOf(br.readLine());
        while (t-- > 0) {
            int n = Integer.valueOf(br.readLine());
            long[][] a = new long[n+1][2];
            {
                String[] line = br.readLine().split(" ");
                for (int i=1; i<=n; i++) {
                    a[i][0] = Long.valueOf(line[i-1]);
                }
            }
            {
                String[] line = br.readLine().split(" ");
                for (int i=1; i<=n; i++) {
                    a[i][1] = Long.valueOf(line[i-1]);
                }
            }
            long[][] d = new long[n+1][3];
            for (int i=1; i<=n; i++) {
                d[i][0] = Math.max(d[i-1][0],Math.max(d[i-1][1],d[i-1][2]));
                d[i][1] = Math.max(d[i-1][0],d[i-1][2])+a[i][0];
                d[i][2] = Math.max(d[i-1][0],d[i-1][1])+a[i][1];
            }
            long ans = 0;
            ans = Math.max(d[n][0], Math.max(d[n][1], d[n][2]));
            System.out.println(ans);
        }
    }
}*/