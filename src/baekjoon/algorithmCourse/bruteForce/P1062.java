package baekjoon.algorithmCourse.bruteForce;

import java.util.Scanner;

/**
 * 가르침
 * 
 * 문제
 * 남극에 사는 김지민 선생님은 학생들이 되도록이면 많은 단어를 읽을 수 있도록 하려고 한다. 그러나 지구온난화로 인해 얼음이 녹아서 곧 학교가 무너지기 때문에, 김지민은 K개의 글자를 가르칠 시간 밖에 없다.
 * 김지민이 가르치고 난 후에는, 학생들은 그 K개의 글자로만 이루어진 단어만을 읽을 수 있다. 김지민은 어떤 K개의 글자를 가르쳐야 학생들이 읽을 수 있는 단어의 개수가 최대가 되는지 고민에 빠졌다.
 * 남극언어의 모든 단어는 "anta"로 시작되고, "tica"로 끝난다. 남극언어에 단어는 N개 밖에 없다고 가정한다. 학생들이 읽을 수 있는 단어의 최댓값을 구하는 프로그램을 작성하시오.
 * 
 * 
 * 입력
 * 첫째 줄에 단어의 개수 N과 K가 주어진다. N은 50보다 작거나 같은 자연수이고, K는 26보다 작거나 같은 자연수 또는 0이다. 둘째 줄부터 N개의 줄에 남극 언어의 단어가 주어진다.
 * 단어는 영어 소문자로만 이루어져 있고, 길이가 8보다 크거나 같고, 15보다 작거나 같다. 모든 단어는 중복되지 않는다.
 * 
 * 출력
 * 첫째 줄에 김지민이 K개의 글자를 가르칠 때, 학생들이 읽을 수 있는 단어 개수의 최댓값을 출력한다.
 * 
 * 
 * 예제 입력 1
 * 3 6
 * antarctica
 * antahellotica
 * antacartica
 * 
 * 예제 출력 1
 * 2
 */
public class P1062 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int k = scan.nextInt();
		
		String[] word = new String[n];
		for(int wordIndex=0; wordIndex<n; wordIndex++) {
			String input = scan.next();
			word[wordIndex] = input.substring(4, input.length()-4);
		}
		scan.close();
		// input end
		
		
		// 반복 횟수를 줄이기 위해 무조건 배워야하는 단어부터 시작하도록 learnInit값을 만듦
		char[] mustLearn = {'a', 'n', 't', 'i', 'c'};
		int learnInit = 0;
		for(int learnIndex=0; learnIndex<5; learnIndex++) {
			int learnCh = 1<<(mustLearn[learnIndex]-'a');
			learnInit |= learnCh;
		}
		
		// 최대로 배울 수 있는 단어 수
		int maxLearnWordCount = 0;
		
		ALPHA_LEARN_LOOP :
		for(int useAlphabet=learnInit; useAlphabet<(1<<26); useAlphabet++) {
			
			// antic 배웠는지 확인
			for(int index=0; index<5; index++) {
				int learnCh = 1<<(mustLearn[index]-'a');
				if((useAlphabet&learnCh) == 0) {
					continue ALPHA_LEARN_LOOP;
				}
			}
			
			// k개를 배웠는지 확인
			int alphaCount = 0;
			for(int checkNum=1; checkNum<(1<<26); checkNum=checkNum<<1) {
				if((useAlphabet&checkNum) == checkNum) {
					alphaCount++;
				}
			}
			if(alphaCount != k) {
				continue ALPHA_LEARN_LOOP;
			}
			
			int wordCount = 0;
			
			// 배울 수 있는 단어인지 확인
			WORD_CHECK_LOOP :
			for(int wordIndex=0; wordIndex<n; wordIndex++) {
				String checkWord = word[wordIndex];
				// 입력 값이 antatica 인 경우 word에 ""이 들어가게 된다.
				// 이 경우에는 무조건 가능
				if("".equals(checkWord)) {
					wordCount++;
					continue;
				}
				
				for(int chIndex=0; chIndex<checkWord.length(); chIndex++) {
					char alpha = checkWord.charAt(chIndex);
					int checkAlpha = 1 << (alpha-'a');
					
					// 해당 알파벳을 배우지 않았으면 다음 단어로 넘어감
					if((useAlphabet&checkAlpha) == 0) {
						continue WORD_CHECK_LOOP;
					}
				}
				
				// 모든 알파벳을 다 배운 상태이면 카운트 증가
				wordCount++;
			}
			
			if(wordCount > maxLearnWordCount) {
				maxLearnWordCount = wordCount;
			}
		}
		
		System.out.println(maxLearnWordCount);
	}
}



// 정답 코드
/*import java.util.*;
public class Main {
    static boolean[] learn = new boolean[26];
    static int count(String[] words) {
        int cnt = 0;
        for (String word : words) {
            boolean ok = true;
            for (char x : word.toCharArray()) {
                if (!learn[x-'a']) {
                    ok = false;
                    break;
                }
            }
            if (ok) cnt += 1;
        }
        return cnt;
    }
    static int go(int index, int k, String[] words) {
        if (k < 0) return 0;
        if (index == 26) {
            return count(words);
        }
        int ans = 0;
        learn[index] = true;
        int t1 = go(index+1, k-1, words);
        learn[index] = false;
        if (ans < t1) ans = t1;
        if (index != 'a'-'a' && index != 'n'-'a' && index != 't'-'a' && index != 'i'-'a' && index != 'c'-'a') {
            t1 = go(index+1, k, words);
            if (ans < t1) ans = t1;
        }
        return ans;
    }
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        String[] words = new String[n];
        for (int i=0; i<n; i++) {
            words[i] = sc.next();
        }
        System.out.println(go(0,m,words));
    }
}*/


/*import java.util.*;
public class Main {
    static int count(int mask, int[] words) {
        int cnt = 0;
        for (int word : words) {
        	// (1<<26)-1-mask) : 배우지 않은 알파벳들이 들어 있는 값 
            if ((word & ((1<<26)-1-mask)) == 0) {
                cnt += 1;
            }
        }
        return cnt;
    }
    static int go(int index, int k, int mask, int[] words) {
        if (k < 0) return 0;
        if (index == 26) {
            return count(mask, words);
        }
        int ans = 0;
        int t1 = go(index+1, k-1, mask | (1 << index), words);
        if (ans < t1) ans = t1;
        if (index != 'a'-'a' && index != 'n'-'a' && index != 't'-'a' && index != 'i'-'a' && index != 'c'-'a') {
            t1 = go(index+1, k, mask, words);
            if (ans < t1) ans = t1;
        }
        return ans;
    }
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] words = new int[n];
        for (int i=0; i<n; i++) {
            String s = sc.next();
            for (char x : s.toCharArray()) {
                words[i] |= (1 << (x-'a'));
            }
        }
        System.out.println(go(0,m,0,words));
    }
}*/