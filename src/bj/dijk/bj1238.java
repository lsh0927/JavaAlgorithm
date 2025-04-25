package bj.dijk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class bj1238 {

	static class Node implements Comparable<Node>{
		int vertex;
		int cost;

		public Node(int vertex, int cost) {
			this.vertex = vertex;
			this.cost = cost;
		}
		@Override
		public int compareTo(Node o){
			return cost-o.cost;
		}

	}

	static int N,M,X;
	static int[] dist;
	static int[] fromXDist;

	static List<List<Node>> graph;
	static List<List<Node>> fromXGraph;

	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());

		N= Integer.parseInt(st.nextToken());
		M= Integer.parseInt(st.nextToken());
		X= Integer.parseInt(st.nextToken());

		dist= new int[N+1];

		//모든 정점에서 X로의 최단 거리
		fromXDist=new int[N+1];

		graph= new ArrayList<>();
		fromXGraph= new ArrayList<>();

		Arrays.fill(dist,Integer.MAX_VALUE);

		for (int i=0;i<=N;i++){
			graph.add(new ArrayList<>());
			fromXGraph.add(new ArrayList<>());
		}

		for (int i=0;i<M;i++){
			st= new StringTokenizer(br.readLine());
			int start= Integer.parseInt(st.nextToken());
			int end= Integer.parseInt(st.nextToken());
			int cost= Integer.parseInt(st.nextToken());

			graph.get(start).add(new Node(end,cost));
			//각 노드에서 갈수 있는 단방향 간선이 추가 됨.
			fromXGraph.get(end).add(new Node(start,cost));
		}


		dist[X]=0;
		fromXDist[X]=0;

		dijkstra(X);
		reverse_dijkstra(X);

		int ans=0;
		for (int i=1;i<=N;i++){
			ans=Math.max(ans,dist[i]+fromXDist[i]);
		}

		System.out.println(ans);

	}
	public static void reverse_dijkstra(int end) {
		Arrays.fill(fromXDist, Integer.MAX_VALUE); // Reset distances

		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(end, 0));

		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			int ver = cur.vertex;
			int co = cur.cost;

			if (fromXDist[ver] < co) {
				continue;
			}

			for (Node node : fromXGraph.get(ver)) {
				int nextVer = node.vertex;
				int nextCost = node.cost + co;
				if (fromXDist[nextVer] > nextCost) {
					fromXDist[nextVer] = nextCost;
					pq.add(new Node(nextVer, nextCost));
				}
			}
		}
	}

	public static void dijkstra(int start){

		dist[start]=0;
		PriorityQueue<Node> pq= new PriorityQueue<>();

		pq.add(new Node(start,0));

		while (!pq.isEmpty()){
			Node cur= pq.poll();

			int ver= cur.vertex;
			int co= cur.cost;


			if (dist[ver]< co){
				continue;
			}

			for (Node node : graph.get(ver)){
				int nextVer= node.vertex;
				int nextCost= node.cost+co;
				if (dist[nextVer]>nextCost){
					dist[nextVer]=nextCost;
					pq.add(new Node(nextVer,nextCost));
				}
			}
		}

	}
}