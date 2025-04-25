package bj.dijk;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class bj1719 {

	static class Node {
		int to;
		int cost;

		public Node(int to, int cost) {
			this.cost = cost;
			this.to = to;
		}
	}

	static int n, m;
	static List<List<Node>> graph;
	static int[][] dist;
	static int[][] firstNode; // 최단 경로에서 첫 번째로 거쳐가는 정점

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		graph = new ArrayList<>();
		for (int i = 0; i <= n; i++) {
			graph.add(new ArrayList<>());
		}

		dist = new int[n+1][n+1];
		firstNode = new int[n+1][n+1];

		for (int i = 1; i <= n; i++) {
			Arrays.fill(dist[i], Integer.MAX_VALUE);
			dist[i][i] = 0;
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			graph.get(from).add(new Node(to, cost));
			graph.get(to).add(new Node(from, cost));
		}

		// 각 정점에서 다익스트라 수행
		for (int i = 1; i <= n; i++) {
			dijkstra(i);
		}

		// 결과 출력
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (i == j) {
					sb.append("- ");
				} else {
					sb.append(firstNode[i][j]).append(" ");
				}
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}

	static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.cost));
		pq.add(new Node(start, 0));

		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			int curTo = cur.to;
			int curCost = cur.cost;

			// 현재 거리가 이미 더 짧다면 건너뜀
			if (dist[start][curTo] < curCost) continue;

			for (Node next : graph.get(curTo)) {
				int nextTo = next.to;
				int nextCost = next.cost + curCost;

				if (dist[start][nextTo] > nextCost) {
					dist[start][nextTo] = nextCost;

					// 첫 번째 경유지 갱신
					if (curTo == start) {
						// 시작점에서 바로 가는 경우
						firstNode[start][nextTo] = nextTo;
					} else {
						// 다른 정점을 거쳐가는 경우, 첫 정점 유지
						firstNode[start][nextTo] = firstNode[start][curTo];
					}

					pq.add(new Node(nextTo, nextCost));
				}
			}
		}
	}
}