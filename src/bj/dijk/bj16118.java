package bj.dijk;

import java.util.*;
import java.io.*;

public class bj16118 {

	static class Node {
		int to;
		double cost;

		public Node(int to,double cost) {
			this.cost = cost;
			this.to = to;
		}
	}

	static class Wolf{
		int to;
		double cost;
		int idx; // 0: 빠른 상태, 1: 느린 상태

		public Wolf(int to, double cost, int idx) {
			this.cost = cost;
			this.idx = idx;
			this.to = to;
		}
	}

	static int N,M;
	static List<List<Node>> graph;
	static List<List<Node>> graph2;
	static double[] dist;
	static double[][] dist2; // [노드번호][상태(0: 빠른 상태, 1: 느린 상태)]

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());

		graph= new ArrayList<>();
		graph2= new ArrayList<>();

		for(int i=0;i<=N;i++){
			graph.add(new ArrayList<>());
			graph2.add(new ArrayList<>());
		}

		dist= new double[N+1];
		dist2= new double[N+1][2]; // [노드번호][상태(0: 빠른 상태, 1: 느린 상태)]

		Arrays.fill(dist,Double.MAX_VALUE);
		for(int i=0;i<=N;i++) {
			Arrays.fill(dist2[i],Double.MAX_VALUE);
		}

		dist[1]=0;
		dist2[1][0]=0; // 늑대는 1번 노드에서 빠른 상태로 시작

		for(int i=0;i<M;i++){
			st= new StringTokenizer(br.readLine());
			int from= Integer.parseInt(st.nextToken());
			int to= Integer.parseInt(st.nextToken());
			int cost= Integer.parseInt(st.nextToken());

			graph.get(from).add(new Node(to,cost));
			graph.get(to).add(new Node(from,cost));
		}

		// 여우의 다익스트라
		PriorityQueue<Node> pq= new PriorityQueue<>(Comparator.comparingDouble(a->a.cost));
		pq.add(new Node(1,0));

		while(!pq.isEmpty()){
			Node cur= pq.poll();
			int curTo=cur.to;
			double curCost=cur.cost;

			if(dist[curTo]<curCost) continue;

			for(Node next: graph.get(curTo)){
				int nextTo=next.to;
				double nextCost=next.cost+cur.cost;

				if(dist[nextTo]>nextCost){
					dist[nextTo]=nextCost;
					pq.add(new Node(nextTo,nextCost));
				}
			}
		}

		// 늑대의 다익스트라
		PriorityQueue<Wolf> pqw= new PriorityQueue<>(Comparator.comparingDouble(a->a.cost));
		pqw.add(new Wolf(1,0,0)); // 늑대는 빠른 상태(idx=0)로 시작

		while(!pqw.isEmpty()){
			Wolf cur= pqw.poll();
			int curTo= cur.to;
			double curCost= cur.cost;
			int curIdx= cur.idx % 2; // 현재 상태 (0: 빠른 상태, 1: 느린 상태)

			if(dist2[curTo][curIdx]<curCost) continue;

			for(Node next: graph.get(curTo)){
				int nextTo= next.to;
				double nextCost;
				int nextIdx= (cur.idx + 1) % 2; // 상태 전환

				if(curIdx==0) { // 현재 빠른 상태면
					nextCost = (double)next.cost/2 + curCost; // 빠르게 이동
				}
				else{ // 현재 느린 상태면
					nextCost = (double)next.cost*2 + curCost; // 느리게 이동
				}

				if(dist2[nextTo][nextIdx]>nextCost){
					dist2[nextTo][nextIdx]=nextCost;
					pqw.add(new Wolf(nextTo,nextCost,cur.idx+1));
				}
			}
		}

		int cnt=0;
		for(int i=2;i<=N;i++){
			double wolfMinDist = Math.min(dist2[i][0], dist2[i][1]); // 늑대의 최단 거리는 두 상태 중 최소값
			if(dist[i]<wolfMinDist){
				cnt++;
			}
		}

		System.out.println(cnt);
	}
	/*
	 static class Node {
       int to;
       double cost;

       public Node(int to,double cost) {
          this.cost = cost;
          this.to = to;
       }
    }

    static class Wolf{
       int to;
       double cost;
       int idx;

       public Wolf(int to, double cost, int idx) {
          this.cost = cost;
          this.idx = idx;
          this.to = to;
       }
    }

    static int N,M;
    static List<List<Node>> bj.graph;
    static List<List<Wolf>> graph2;
    static double[] dist;
    static double[] dist2;
    public static void main(String[] args) throws Exception {

       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       StringTokenizer st = new StringTokenizer(br.readLine());

       N=Integer.parseInt(st.nextToken());
       M=Integer.parseInt(st.nextToken());

       bj.graph= new ArrayList<>();
       graph2= new ArrayList<>();

       for(int i=0;i<=N;i++){
          bj.graph.add(new ArrayList<>());
          graph2.add(new ArrayList<>());
       }

       dist= new double[N+1];
       dist2= new double[N+1];
       Arrays.fill(dist,Integer.MAX_VALUE);
       Arrays.fill(dist2,Integer.MAX_VALUE);

       dist[1]=0;
       dist2[1]=0;

       for(int i=0;i<M;i++){
          st= new StringTokenizer(br.readLine());
          int from= Integer.parseInt(st.nextToken());
          int to= Integer.parseInt(st.nextToken());
          int cost= Integer.parseInt(st.nextToken());

          bj.graph.get(from).add(new Node(to,cost));
          bj.graph.get(to).add(new Node(from,cost));

       }

       PriorityQueue<Node> pq= new PriorityQueue<>(Comparator.comparingDouble(a->a.cost));
       pq.add(new Node(1,0));

       while(!pq.isEmpty()){
          Node cur= pq.poll();
          int curTo=cur.to;
          double curCost=cur.cost;

          if(dist[curTo]<curCost) continue;

          for(Node next: bj.graph.get(curTo)){
             int nextTo=next.to;
             double nextCost=next.cost+cur.cost;

             if(dist[nextTo]>nextCost){
                dist[nextTo]=nextCost;
                pq.add(new Node(nextTo,nextCost));
             }
          }


       }

       PriorityQueue<Wolf> pqw= new PriorityQueue<>(Comparator.comparingDouble(a->a.cost));
       pqw.add(new Wolf(1,0,0));

       while(!pqw.isEmpty()){
          Wolf cur= pqw.poll();
          int curTo= cur.to;
          double curCost= cur.cost;

          if(dist2[curTo]<curCost) continue;

          for(Node next: bj.graph.get(curTo)){
             int nextTo= next.to;
             double nextCost;
             if(cur.idx%2==0) {
                 nextCost = (double)next.cost/2 + curCost;
             }
             else{
                nextCost = (double)next.cost*2 + curCost;
             }

             if(dist2[nextTo]>nextCost){
                dist2[nextTo]=nextCost;
                pqw.add(new Wolf(nextTo,nextCost,cur.idx+1));
             }
          }
       }
       int cnt=0;
       for(int i=1;i<=N;i++){
          if(dist[i]<dist2[i]){
             cnt++;
          }
          System.out.println(dist2[i]);
       }
       System.out.println(cnt);
    }
	 */
}