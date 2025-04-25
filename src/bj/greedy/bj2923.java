package bj.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj2923 {
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		// A와 B 카드의 숫자별 개수를 저장할 배열
		int[] cardA = new int[101]; // 카드 A의 숫자별 개수 (인덱스 = 카드 숫자)
		int[] cardB = new int[101]; // 카드 B의 숫자별 개수
		StringBuilder sb = new StringBuilder();

		for(int i=1; i<=N; i++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			int valueA = Integer.parseInt(st.nextToken());
			int valueB = Integer.parseInt(st.nextToken());

			cardA[valueA] += 1;
			cardB[valueB] += 1;

			// 현재까지의 카드로 최대값 계산
			int result = findMinMaxSum(cardA, cardB, i);
			sb.append(result).append("\n");
		}

		System.out.println(sb.toString().trim());
	}

	static int findMinMaxSum(int[] cardA, int[] cardB, int totalCards) {
		int[] copyA = copyArray(cardA);
		int[] copyB = copyArray(cardB);

		int minA = 1;       // A의 가장 작은 값부터 시작
		int maxB = 100;     // B의 가장 큰 값부터 시작
		int maxSum = -1;    // 발견된 최대 합 (아직 발견 안됨)
		int matchedPairs = 0; // 매치된 카드 쌍의 수

		// 필요한 카드 쌍이 모두 매치될 때까지 반복
		while(matchedPairs < totalCards){
			// 현재 위치에 카드가 없으면 커서 이동
			if(copyA[minA] == 0 || copyB[maxB] == 0) {
				// 카드가 없는 위치 건너뛰기
				if(copyA[minA] == 0) {
					minA++; // A의 다음 숫자로 이동
				}
				if(copyB[maxB] == 0) {
					maxB--; // B의 이전 숫자로 이동
				}
				continue;
			}

			// 이제 minA와 maxB 위치에는 카드가 있음
			// 현재 조합의 합을 확인하고 최대값 갱신
			maxSum = Math.max(maxSum, minA + maxB);

			// 카드 매칭하기 (가능한 많은 쌍 만들기)
			if(copyA[minA] <= copyB[maxB]) {
				// A 카드를 모두 사용할 수 있는 경우
				int pairs = copyA[minA];
				copyB[maxB] -= pairs;  // B 카드에서 사용한 만큼 감소
				matchedPairs += pairs; // 매치된 쌍 증가
				copyA[minA] = 0;       // A 카드 모두 사용
				minA++;                // 다음 A 숫자로 이동
			} else {
				// B 카드를 모두 사용하는 경우
				int pairs = copyB[maxB];
				copyA[minA] -= pairs;  // A 카드에서 사용한 만큼 감소
				matchedPairs += pairs; // 매치된 쌍 증가
				copyB[maxB] = 0;       // B 카드 모두 사용
				maxB--;                // 이전 B 숫자로 이동
			}
		}

		return maxSum;
	}

	// 배열 복사 함수
	static int[] copyArray(int[] original) {
		int[] copy = new int[original.length];
		for(int i=0; i<original.length; i++) {
			copy[i] = original[i];
		}
		return copy;
	}
}
