package bj.dijk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class bj1504 {

	static class Node{
		int to;
		int cost;

		public Node(int to, int cost) {
			this.cost = cost;
			this.to = to;
		}
	}
	static int INF= Integer.MAX_VALUE;
	static int N,E;
	static List<List<int[]>> graph;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N=Integer.parseInt(st.nextToken());
		E=Integer.parseInt(st.nextToken());

		graph = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}

		for(int i=0;i<E;i++){
			st = new StringTokenizer(br.readLine());
			int from=Integer.parseInt(st.nextToken());
			int to=Integer.parseInt(st.nextToken());
			int c=Integer.parseInt(st.nextToken());

			graph.get(from).add(new int[]{to,c});
		}

		st = new StringTokenizer(br.readLine());
		int v1 = Integer.parseInt(st.nextToken());
		int v2 = Integer.parseInt(st.nextToken());

		long path1=0;
		int dist1to1= dijk(1,v1);
		int dist1to2= dijk(v1,v2);
		int dist1to3= dijk(v2,N);

		long path2=0;
		int dist2to1= dijk(1,v1);
		int dist2to2= dijk(v1,v2);
		int dist2to3= dijk(v2,N);

		if(dist1to1==INF || dist1to2==INF || dist1to3==INF){
			path1=INF;
		}
		else{
			path1=(long)dist1to1+dist1to2+dist1to3;
		}

		if(dist2to1==INF || dist2to2==INF || dist2to3==INF){
			path2=INF;
		}
		else{
			path2=(long)dist2to1+dist2to2+dist2to3;
		}

		long result= Math.min(path1,path2);
		System.out.println(result == INF?-1:result);


	}
	private static int dijk(int from, int to){
		int[] dist= new int[N+1];
		Arrays.fill(dist,INF);
		dist[from]=0;

		PriorityQueue<Node> pq= new PriorityQueue<>();
		pq.add(new Node(from,0));

		while(!pq.isEmpty()){
			Node node= pq.poll();
			int curTo= node.to;
			int curCost= node.cost;

			if(curCost>dist[curTo]){
				continue;
			}

			for (int[] next: graph.get(from)){
				int nextTo= next[0];
				int nextCost= next[1];

				if (nextCost<dist[nextTo]){
					dist[nextTo]=nextCost;
					pq.add(new Node(nextTo,dist[nextTo]));
				}
			}
		}

		return dist[to];


	}
}