package dijk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class bj2307 {

	static class Node {
		int to;
		int cost;

		public Node(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
	}

	static int N, M;
	static List<List<Node>> graph;
	static int[] time;
	static int[] parent; // 경로 추적을 위한 배열

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		graph = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			graph.get(from).add(new Node(to, cost));
			graph.get(to).add(new Node(from, cost));
		}

		// 최초 최단 경로 찾기
		parent = new int[N + 1];
		time = new int[N + 1];
		for (int i = 0; i <= N; i++) {
			parent[i] = -1;
			time[i] = Integer.MAX_VALUE;
		}

		int originalShortestPath = findShortestPath();

		// 최단 경로를 따라가며 각 간선 차단해보기
		int maxDelay = 0;
		List<int[]> pathEdges = new ArrayList<>();
		int node = N;

		// 최단 경로 상의 모든 간선 수집
		while (parent[node] != -1) {
			pathEdges.add(new int[]{parent[node], node});
			node = parent[node];
		}

		// 각 간선 차단 후 최단 경로 계산
		for (int[] edge : pathEdges) {
			int newPath = findShortestPathWithBlockedEdge(edge[0], edge[1]);

			if (newPath == Integer.MAX_VALUE) {
				System.out.println(-1);
				return;
			}

			maxDelay = Math.max(maxDelay, newPath - originalShortestPath);
		}

		System.out.println(maxDelay);
	}

	// 최초 최단 경로 찾기 (간선 차단 없음)
	static int findShortestPath() {
		Arrays.fill(time, Integer.MAX_VALUE);
		Arrays.fill(parent, -1);
		time[1] = 0;

		PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.cost));
		pq.add(new Node(1, 0));

		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			int curNode = cur.to;
			int curCost = cur.cost;

			if (time[curNode] < curCost) continue;

			for (Node next : graph.get(curNode)) {
				int nextNode = next.to;
				int nextCost = curCost + next.cost;

				if (time[nextNode] > nextCost) {
					time[nextNode] = nextCost;
					parent[nextNode] = curNode;
					pq.add(new Node(nextNode, nextCost));
				}
			}
		}

		return time[N];
	}

	// 간선을 차단한 상태에서 최단 경로 찾기
	static int findShortestPathWithBlockedEdge(int blockFrom, int blockTo) {
		Arrays.fill(time, Integer.MAX_VALUE);
		time[1] = 0;

		PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.cost));
		pq.add(new Node(1, 0));

		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			int curNode = cur.to;
			int curCost = cur.cost;

			if (time[curNode] < curCost) continue;

			for (Node next : graph.get(curNode)) {
				int nextNode = next.to;

				// 차단된 간선 검사
				if ((curNode == blockFrom && nextNode == blockTo) ||
					(curNode == blockTo && nextNode == blockFrom)) {
					continue;
				}

				int nextCost = curCost + next.cost;

				if (time[nextNode] > nextCost) {
					time[nextNode] = nextCost;
					pq.add(new Node(nextNode, nextCost));
				}
			}
		}

		return time[N];
	}
}