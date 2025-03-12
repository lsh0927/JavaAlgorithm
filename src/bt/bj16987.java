package bt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj16987 {
	static int N;
	static int[] dura;
	static int[] weight;
	static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		dura = new int[N];
		weight = new int[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			dura[i] = Integer.parseInt(st.nextToken()); // 계란의 내구도
			weight[i] = Integer.parseInt(st.nextToken()); // 계란의 무게
		}

		bt(0, 0); // 0번째 계란부터 시작, 깨진 계란 0개

		System.out.println(max);
	}

	static void bt(int idx, int cnt) {
		if (idx == N || cnt == N-1) {
			max = Math.max(cnt, max);
			return;
		}

		if (dura[idx] <= 0 ) {
			bt(idx+1, cnt);
		}
		else {
			for (int i = 0; i < N; i++) {
				if (idx == i || dura[i] <= 0)
					continue;
				dura[idx]-=weight[i];
				dura[i]-=weight[idx];

				bt(idx+1, cnt+ (dura[idx]<=0 ? 1: 0) + (dura[i]<=0 ? 1:0));

				dura[idx]+=weight[i];
				dura[i]+=weight[idx];
			}
		}
	}
}
