package dijk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class bj10282 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 테스트 케이스 개수 읽기
		int tc = Integer.parseInt(br.readLine());

		for (int i = 0; i < tc; i++) {
			// 각 테스트 케이스마다 n, d, c 읽기
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			// 그래프 초기화
			List<List<int[]>> graph = new ArrayList<>();
			for (int a = 0; a <= n; a++) {
				graph.add(new ArrayList<>());
			}

			// 의존성 정보 입력
			for (int j = 0; j < d; j++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int s = Integer.parseInt(st.nextToken());
				graph.get(b).add(new int[]{a, s});
			}

			// 다익스트라 알고리즘 적용
			int[] dist = new int[n + 1];
			Arrays.fill(dist, Integer.MAX_VALUE);
			dist[c] = 0;

			PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
			pq.add(new int[]{c, 0});

			while (!pq.isEmpty()) {
				int[] cur = pq.poll();
				int to = cur[0];
				int cost = cur[1];

				if (dist[to] < cost) {
					continue;
				}

				for (int[] next : graph.get(to)) {
					int nextTo = next[0];
					int nextCost = next[1] + cost;

					if (nextCost < dist[nextTo]) {
						dist[nextTo] = nextCost;
						pq.add(new int[]{nextTo, nextCost});
					}
				}
			}

			// 결과 계산
			int max = 0;
			int cnt = 0;
			for (int k = 1; k <= n; k++) {
				if (dist[k] != Integer.MAX_VALUE) {
					max = Math.max(max, dist[k]);
					cnt++;
				}
			}

			System.out.println(cnt + " " + max);
		}
	}
}