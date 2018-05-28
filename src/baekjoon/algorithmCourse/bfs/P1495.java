package baekjoon.algorithmCourse.bfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 기타리스트
 * 
 * 문제
 * Day Of Mourning의 기타리스트 강토는 다가오는 공연에서 연주할 N개의 곡을 연주하고 있다. 지금까지 공연과는 다른 공연을 보여주기 위해서 이번 공연에서는 매번 곡이 시작하기 전에 볼륨을 바꾸고 연주하려고 한다.
 * 먼저, 공연이 시작하기 전에 각각의 곡이 시작하기 전에 바꿀 수 있는 볼륨의 리스트를 만들었다. 이 리스트를 V라고 했을 때, V[i]는 i번째 곡을 연주하기 전에 바꿀 수 있는 볼륨을 의미한다.
 * 항상 리스트에 적힌 차이로만 볼륨을 바꿀 수 있다. 즉, 현재 볼륨이 P이고 지금 i번째 곡을 연주하기 전이라면, i번 곡은 P+V[i]나 P-V[i] 로 연주해야 한다. 하지만, 0보다 작은 값으로 볼륨을 바꾸거나, M보다 큰 값으로 볼륨을 바꿀 수 없다.
 * 곡의 개수 N과 시작 볼륨 S, 그리고 M이 주어졌을 때, 마지막 곡을 연주할 수 있는 볼륨 중 최대값을 구하는 프로그램을 작성하시오. 모든 곡은 리스트에 적힌 순서대로 연주해야 한다.
 * 
 * 
 * 입력
 * 첫째 줄에 곡의 개수 N, 시작 볼륨 S, M이 주어진다. (1 ≤ N ≤ 100, 1 ≤ M ≤ 1000, 0 ≤ S ≤ M) 둘째 줄에는 각 곡이 시작하기 전에 줄 수 있는 볼륨의 차이가 주어진다. 이 값은 1보다 크거나 같고, M보다 작거나 같다.
 * 
 * 출력
 * 첫째 줄에 가능한 마지막 곡의 볼륨 중 최대값을 출력한다. 만약 마지막 곡을 연주할 수 없다면 (중간에 볼륨 조절을 할 수 없다면) -1을 출력한다.
 * 
 * 
 * 예제 입력 1
 * 3 5 10
 * 5 3 7
 * 
 * 예제 출력 1
 * 10
 */
public class P1495 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int s = scan.nextInt();
		int m = scan.nextInt();
		
		int[] volume = new int[n];
		for(int index=0; index<n; index++) {
			volume[index] = scan.nextInt();
		}
		scan.close();
		// input end
		
		Queue<Volume> queue = new LinkedList<>();
		// 만든 볼륨 크기, 볼륨을 만들 때 사용한 인덱스
		boolean[][] isMadeVolume = new boolean[1101][n];
		
		queue.add(new Volume(s, -1));
		// 만든 볼륨 크기를 어떤 인덱스로 만들었냐에 따라 다른 값이므로 초기화하면 안됨
//		Arrays.fill(isMadeVolume[s], true);
		
		LinkedList<Integer> lastMusicVolume = new LinkedList<>();
		
		while(!queue.isEmpty()) {
			Volume nowVolume = queue.remove();
			
			int nextVolumeIndex = nowVolume.volumeIndex + 1;
			if(nextVolumeIndex < n) {
				// +volume
				int nextVolume = nowVolume.currentVolume + volume[nextVolumeIndex];
				if(nextVolume<=m && !isMadeVolume[nextVolume][nextVolumeIndex]) {
					queue.add(new Volume(nextVolume, nextVolumeIndex));
					isMadeVolume[nextVolume][nextVolumeIndex] = true;
					if(nextVolumeIndex == n-1) {
						lastMusicVolume.add(nextVolume);
					}
					
				}
				
				// -volume
				nextVolume = nowVolume.currentVolume - volume[nextVolumeIndex];
				if(nextVolume>=0 && !isMadeVolume[nextVolume][nextVolumeIndex]) {
					queue.add(new Volume(nextVolume, nextVolumeIndex));
					isMadeVolume[nextVolume][nextVolumeIndex] = true;
					if(nextVolumeIndex == n-1) {
						lastMusicVolume.add(nextVolume);
					}
				}
			}
		}
		
		int maxVolume = -1;
		for(int lastVolume : lastMusicVolume) {
			if(lastVolume > maxVolume) {
				maxVolume = lastVolume;
			}
		}
		System.out.println(maxVolume);
	}

}

class Volume {
	// 만들어진 볼륨 크기
	int currentVolume;
	// 볼륨 만들 때 사용한 인덱스
	int volumeIndex;
	
	public Volume(int currentVolume, int volumeIndex) {
		this.currentVolume = currentVolume;
		this.volumeIndex = volumeIndex;
	}
}


// 정답 코드
/*import java.util.*;
public class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int s = sc.nextInt();
        int m = sc.nextInt();
        int[] a = new int[n+1];
        boolean[][] d = new boolean[n+1][m+1];
        for (int i=1; i<=n; i++) {
            a[i] = sc.nextInt();
        }
        d[0][s] = true;
        for (int i=0; i<=n-1; i++) {
            for (int j=0; j<=m; j++) {
                if (d[i][j] == false) {
                    continue;
                }
                if (j-a[i+1] >= 0) {
                    d[i+1][j-a[i+1]] = true;
                }
                if (j+a[i+1] <= m) {
                    d[i+1][j+a[i+1]] = true;
                }
            }
        }
        int ans = -1;
        for (int i=0; i<=m; i++) {
            if (d[n][i]) ans = i;
        }
        System.out.println(ans);
    }
}*/