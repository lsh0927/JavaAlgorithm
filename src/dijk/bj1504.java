package dijk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class bj1504 {
	static int N, E;
	static List<List<int[]>> graph;
	static final int INF = 200000000; // 충분히 큰 값 설정

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		graph = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph.get(u).add(new int[]{v, w});
			graph.get(v).add(new int[]{u, w});
		}

		st = new StringTokenizer(br.readLine());
		int v1 = Integer.parseInt(st.nextToken());
		int v2 = Integer.parseInt(st.nextToken());

		// 1 -> v1 -> v2 -> N 경로
		long path1 = 0;
		int dist1to1 = dijkstra(1, v1);
		int dist1to2 = dijkstra(v1, v2);
		int dist1to3 = dijkstra(v2, N);

		// 1 -> v2 -> v1 -> N 경로
		long path2 = 0;
		int dist2to1 = dijkstra(1, v2);
		int dist2to2 = dijkstra(v2, v1);
		int dist2to3 = dijkstra(v1, N);

		if (dist1to1 == INF || dist1to2 == INF || dist1to3 == INF) {
			path1 = INF;
		} else {
			path1 = (long)dist1to1 + dist1to2 + dist1to3;
		}

		if (dist2to1 == INF || dist2to2 == INF || dist2to3 == INF) {
			path2 = INF;
		} else {
			path2 = (long)dist2to1 + dist2to2 + dist2to3;
		}

		long result = Math.min(path1, path2);

		if (result >= INF) {
			System.out.println(-1);
		} else {
			System.out.println(result);
		}
	}

	// 다익스트라 알고리즘 - start부터 end까지의 최단거리를 반환
	private static int dijkstra(int start, int end) {
		int[] dist = new int[N + 1];
		Arrays.fill(dist, INF);
		dist[start] = 0;

		// int[]의 첫 번째 요소는 노드 번호, 두 번째 요소는 시작점으로부터의 거리
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
		pq.add(new int[]{start, 0});

		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			int node = cur[0];
			int cost = cur[1];

			// 현재 노드가 이미 더 짧은 경로로 처리되었다면 건너뜀
			if (cost > dist[node]) continue;

			// 현재 노드에 인접한 모든 노드 탐색
			for (int[] next : graph.get(node)) {
				int nextNode = next[0];
				int nextCost = next[1] + cost;

				// 더 짧은 경로를 발견한 경우 업데이트
				if (nextCost < dist[nextNode]) {
					dist[nextNode] = nextCost;
					pq.add(new int[]{nextNode, nextCost});
				}
			}
		}

		return dist[end];
	}
}