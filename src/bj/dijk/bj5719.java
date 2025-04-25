package bj.dijk;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.io.IOException;

public class bj5719 {
	static class Node {
		int to;
		int cost;

		public Node(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
	}

	static final int INF = Integer.MAX_VALUE;
	static int N, M, S, D;
	static int[] dist;
	static List<List<Node>> graph;
	static List<List<Integer>> prev;  // 이전 노드를 저장하는 리스트
	static boolean[][] removed;       // 제거된 간선 정보

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		while (true) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			if (N == 0 && M == 0) break;

			st = new StringTokenizer(br.readLine());
			S = Integer.parseInt(st.nextToken());
			D = Integer.parseInt(st.nextToken());

			// 초기화
			graph = new ArrayList<>();
			prev = new ArrayList<>();
			removed = new boolean[N][N];

			for (int i = 0; i < N; i++) {
				graph.add(new ArrayList<>());
				prev.add(new ArrayList<>());
			}

			// 간선 정보 입력
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());

				graph.get(from).add(new Node(to, cost));
			}

			// 1. 최단 경로 찾기
			dijkstra(S);

			// 2. 최단 경로 간선 제거
			removeShortestPaths(D);

			// 3. 거의 최단 경로 찾기
			dijkstra(S);

			// 결과 출력
			sb.append(dist[D] == INF ? -1 : dist[D]).append("\n");
		}

		System.out.print(sb);
	}

	// 최단 경로를 DFS로 제거
	static void removeShortestPaths(int node) {
		if (prev.get(node).isEmpty()) return;

		for (int prevNode : prev.get(node)) {
			if (!removed[prevNode][node]) {
				removed[prevNode][node] = true;
				removeShortestPaths(prevNode);
			}
		}
	}

	static void dijkstra(int start) {
		// 거리 배열 초기화
		dist = new int[N];
		Arrays.fill(dist, INF);
		dist[start] = 0;

		// 이전 노드 정보 초기화
		for (int i = 0; i < N; i++) {
			prev.get(i).clear();
		}

		PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.cost));
		pq.add(new Node(start, 0));

		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			int curNode = cur.to;
			int curCost = cur.cost;

			if (dist[curNode] < curCost) continue;

			for (Node next : graph.get(curNode)) {
				int nextNode = next.to;
				int nextCost = next.cost + curCost;

				// 제거된 간선은 건너뛰기
				if (removed[curNode][nextNode]) continue;

				if (dist[nextNode] > nextCost) {
					// 더 짧은 경로 발견
					dist[nextNode] = nextCost;

					// 이전 경로 정보 초기화 후 현재 노드 추가
					prev.get(nextNode).clear();
					prev.get(nextNode).add(curNode);

					pq.add(new Node(nextNode, nextCost));
				} else if (dist[nextNode] == nextCost) {
					// 같은 비용의 경로 발견 - 이전 경로에 추가
					prev.get(nextNode).add(curNode);
				}
			}
		}
	}
}
