package baekjoon.algorithmCourse.dp;

import java.util.Scanner;

/**
 * 타일 채우기
 * 
 * 문제
 * 3×N 크기의 벽을 2×1, 1×2 크기의 타일로 채우는 경우의 수를 구해보자.
 * 
 * 
 * 입력
 * 첫째 줄에 N(1 ≤ N ≤ 30)이 주어진다.
 * 
 * 출력
 * 첫째 줄에 경우의 수를 출력한다.
 * 
 * 
 * 예제 입력 1
 * 2
 * 
 * 예제 출력 1
 * 3
 */
/*
 * n이 짝수일 때만 채우는 것이 가능
 * D[2] = 3;
 * D[4] = 3*3 = 9가 아님. 다른 경우가 존재
 * n이 짝수일 때 각각의 타일을 채우는 경우가 2개씩 존재
 * n=4 : 2개를 채우는 경우로 끝나는 경우(9) + 2(4개를 채우는 방법)
 * n=6 : 6개 채우는 타일(2) + 4개 채우는 타일(2)*2개 채우는 타일(3) + 2개 채우는 타일(3)*2개 채우는 타일(3)
 * 
 * D[n] = 2 + 3*D[n-2] + 2*D[n-4] + 2*D[n-6] ... + 2*D[2]
 */
public class P2133 {
	static int[] TILE_FILL_WAY;
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		scan.close();
		
		P2133.TILE_FILL_WAY = new int[n+1];
		P2133 p2133 = new P2133();
		
		// for 문
//		System.out.println(p2133.calcTileFill(n));
		
		// 재귀
		if(n > 1) {
			P2133.TILE_FILL_WAY[2] = 3;
		}
		System.out.println(p2133.calcTileFill2(n));
	}

	public int calcTileFill(int n) {
		if(n%2 != 0) {
			return 0;
		}
		
		if(n > 1) {
			P2133.TILE_FILL_WAY[2] = 3;
		}
		
		for(int num=4; num<=n; num+=2) {
			P2133.TILE_FILL_WAY[num] = 2 + 3*P2133.TILE_FILL_WAY[num-2];
			for(int index=4; index<num; index+=2) {
				P2133.TILE_FILL_WAY[num] += 2 * P2133.TILE_FILL_WAY[num-index]; 
			}
		}
		
		return P2133.TILE_FILL_WAY[n];
	}
	
	public int calcTileFill2(int n) {
		if(n%2 != 0) {
			return 0;
		}
		
		if(P2133.TILE_FILL_WAY[n] > 0) {
			return P2133.TILE_FILL_WAY[n];
		}
		
		if(P2133.TILE_FILL_WAY[n-2] > 0) {
			P2133.TILE_FILL_WAY[n] = 2 + 3*P2133.TILE_FILL_WAY[n-2];
		}else {
			P2133.TILE_FILL_WAY[n] = 2 + 3*calcTileFill2(n-2);
		}
		
		for(int index=4; index<n; index+=2) {
			P2133.TILE_FILL_WAY[n] += 2 * P2133.TILE_FILL_WAY[n-index]; 
		}
		
		return P2133.TILE_FILL_WAY[n];
	}
}



// 정답 코드
/*
 * 2를 따로 더하지 않고 d[0]을 1로 둬서 *2한 값이 더해지도록
 */
/*import java.util.*;
public class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[] d = new long[n+1];
        d[0] = 1;
        for (int i=2; i<=n; i+=2) {
            d[i] = d[i-2]*3;
            for (int j=i-4; j>=0; j-=2) {
                d[i] += d[j]*2;
            }
        }
        System.out.println(d[n]);
    }
}*/