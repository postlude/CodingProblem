package baekjoon.algorithmCourse.etc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/**
 * 버블 소트
 * 
 * 문제
 * 영식이는 다음과 같은 버블 소트 프로그램을 C++로 작성했다.
 * 
 * bool change = false;
 * for (int i=1; i<=n+1; i++) {
 *     change = false;
 *     for (int j=1; j<=n-i; j++) {
 *         if (a[j] > a[j+1]) {
 *             change = true;
 *             swap(a[j], a[j+1]);
 *         }
 *     }
 *     if (change == false) {
 *         break;
 *     }
 * }
 * cout << i << '\n';
 * 
 * 위 소스에서 n은 배열의 크기이고, a는 수가 들어있는 배열이다. 수는 배열의 1번방부터 채운다.
 * 위와 같은 소스를 실행시켰을 때, 어떤 값이 출력되는지 구하는 프로그램을 작성하시오.
 * 
 * 
 * 입력
 * 첫째 줄에 N이 주어진다. N은 500,000보다 작거나 같은 자연수이다. 둘째 줄부터 N개의 줄에 A[1]부터 A[N]까지 하나씩 주어진다.
 * 
 * 출력
 * 정답을 출력한다.
 * 
 * 
 * 예제 입력 1
 * 5
 * 10
 * 1
 * 5
 * 2
 * 3
 * 
 * 예제 출력 1
 * 3
 */
/*
 * 버블 소트를 그대로 돌리면 시간 초과
 * 
 * 한 번의 버블 소트 진행시 비교하는 두 수 중에서
 * 앞에 있는 수가 뒤로 이동 : 제일 끝까지 무제한으로 이동 가능
 * 뒤에 있는 수가 앞으로 이동 : 한 번의 비교에서 한 칸만 이동 가능
 * 
 * 전체 정렬 후 뒤에서 앞으로 이동한 횟수 중 가장 큰 값을 찾으면 된다.
 */
public class P1377 {

	public static void main(String[] args) {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			){
			int n = Integer.parseInt(br.readLine());
			
			Pair[] numAry = new Pair[n];
			for(int index=0; index<n; index++) {
				int num = Integer.parseInt(br.readLine());
				numAry[index] = new Pair(num, index);
			}
			
			Arrays.sort(numAry);
			
			int maxCount = 0;
			for(int index=0; index<n; index++) {
				// 정렬 전 index
				int beforeIndex = numAry[index].initIndex;
				if(index < beforeIndex) {
					int count = beforeIndex - index;
					if(count > maxCount) {
						maxCount = count;
					}
				}
			}
			bw.write((maxCount+1) + "\n");
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/*public int bubbleSort(int[] numAry, int length) {
		boolean change = false;
		int index = 1;
		for (; index<=length+1; index++) {
			change = false;
			for(int changeIndex=1; changeIndex<=length-index; changeIndex++) {
				if(numAry[changeIndex] > numAry[changeIndex+1]) {
					change = true;
					// swap
					int temp = numAry[changeIndex];
					numAry[changeIndex] = numAry[changeIndex+1];
					numAry[changeIndex+1] = temp;
				}
			}
			if(change == false) {
				break;
			}
		}
		return index;
	}*/
}

class Pair implements Comparable<Pair>{
	int num;
	// 초기 index 값
	int initIndex;
	
	public Pair(int num, int index) {
		this.num = num;
		this.initIndex = index;
	}

	@Override
	public int compareTo(Pair pair) {
		if(this.num > pair.num) {
			return 1;
		}else if(this.num == pair.num) {
			return 0;
		}else {
			return -1;
		}
	}
}



// 정답 코드
/*import java.util.*;
import java.io.*;
class Pair implements Comparable<Pair> {
    int first;
    int second;
    Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }
    public int compareTo(Pair that) {
        if (this.first < that.first) {
            return -1;
        } else if (this.first == that.first) {
            if (this.second < that.second) return -1;
            else if (this.second == that.second) return 0;
            else return 1;
        } else {
            return 1;
        }
    }
}

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        Pair[] a = new Pair[n];
        for (int i=0; i<n; i++) {
            a[i] = new Pair(Integer.parseInt(bf.readLine()), i);
        }
        Arrays.sort(a);
        int ans = 0;
        for (int i=0; i<n; i++) {
            if (a[i].second - i > ans) {
                ans = a[i].second - i;
            }
        }
        System.out.println(ans+1);
    }
}*/