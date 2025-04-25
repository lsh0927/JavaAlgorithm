package bj.dijk;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class bj13911 {


	static class Node implements Comparable<Node>{
		int to;
		int cost;

		public Node(int to, int cost) {
			this.cost = cost;
			this.to = to;
		}

		@Override
		public int compareTo(Node o){
			return Integer.compare(this.cost, o.cost); // 안전한 비교 방식
		}
	}

	static int V, E;
	static List<List<Node>> graph;
	static int[] distM, distS; // 맥도날드와 스타벅스로부터의 거리 배열
	static int M, S, x, y;
	static Set<Integer> mPoint;
	static Set<Integer> sPoint;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		graph = new ArrayList<>();
		for(int i = 0; i <= V; i++){
			graph.add(new ArrayList<>());
		}

		for(int i = 0; i < E; i++){
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			graph.get(u).add(new Node(v, w));
			graph.get(v).add(new Node(u, w));
		}

		// 맥도날드 수와 범위
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());

		// 맥도날드 지점
		st = new StringTokenizer(br.readLine());
		mPoint = new HashSet<>();
		for(int i = 0; i < M; i++){
			mPoint.add(Integer.parseInt(st.nextToken()));
		}

		// 스타벅스 수와 범위
		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());

		// 스타벅스 지점
		st = new StringTokenizer(br.readLine());
		sPoint = new HashSet<>();
		for(int i = 0; i < S; i++){
			sPoint.add(Integer.parseInt(st.nextToken()));
		}

		// 맥도날드와 스타벅스로부터의 최단 거리 계산
		distM = new int[V + 1];
		distS = new int[V + 1];
		Arrays.fill(distM, Integer.MAX_VALUE);
		Arrays.fill(distS, Integer.MAX_VALUE);

		// 모든 맥도날드에서 다익스트라
		dijkstraFromMultiSource(mPoint, distM, x);

		// 모든 스타벅스에서 다익스트라
		dijkstraFromMultiSource(sPoint, distS, y);

		// 최소 거리 합 찾기
		int minSum = Integer.MAX_VALUE;
		for(int i = 1; i <= V; i++){
			// 집일 경우만 고려 (맥도날드나 스타벅스가 아닌 경우)
			if(!mPoint.contains(i) && !sPoint.contains(i)) {
				// 맥도날드와 스타벅스 모두 범위 내에 있는지 확인
				if(distM[i] <= x && distS[i] <= y) {
					minSum = Math.min(minSum, distM[i] + distS[i]);
				}
			}
		}

		// 결과 출력
		if(minSum == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(minSum);
		}
	}

	// 다수의 시작점에서 다익스트라 알고리즘 실행
	static void dijkstraFromMultiSource(Set<Integer> sources, int[] dist, int limit) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[V + 1];

		// 모든 소스 노드를 시작점으로 설정
		for(int source : sources) {
			dist[source] = 0;
			pq.add(new Node(source, 0));
		}

		while(!pq.isEmpty()){
			Node cur = pq.poll();
			int curTo = cur.to;

			if(visited[curTo]) continue;
			visited[curTo] = true;

			for(Node next : graph.get(curTo)){
				int nextTo = next.to;
				int nextCost = next.cost + cur.cost;

				// 범위 체크
				if(nextCost > limit) continue;

				if(!visited[nextTo] && dist[nextTo] > nextCost){
					dist[nextTo] = nextCost;
					pq.add(new Node(nextTo, nextCost));
				}
			}
		}
	}
	//	메모리 초과 코드
	/*
	static class Node implements Comparable<Node>{
		int to;
		int cost;

		public Node(int to,int cost) {
			this.cost = cost;
			this.to = to;
		}

		@Override
		public int compareTo(Node o){
			return this.cost-o.cost;
		}
	}

	static int min=Integer.MAX_VALUE;
	static int V,E;
	static List<List<Node>> bj.graph;
	static int[][] dist;
	static int M,S,x,y;
	static Set<Integer> mPoint;
	static Set<Integer> sPoint;
	static int ans;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		V=Integer.parseInt(st.nextToken());
		E=Integer.parseInt(st.nextToken());

		bj.graph= new ArrayList<>();
		for(int i=0;i<=V;i++){
			bj.graph.add(new ArrayList<>());
		}


		for(int i=0;i<E;i++){
			st= new StringTokenizer(br.readLine());
			int u=Integer.parseInt(st.nextToken());
			int v=Integer.parseInt(st.nextToken());
			int w=Integer.parseInt(st.nextToken());

			bj.graph.get(u).add(new Node(v,w));
			bj.graph.get(v).add(new Node(u,w));
		}
		//맥날 수와 범위
		st= new StringTokenizer(br.readLine());
		M=Integer.parseInt(st.nextToken());
		x=Integer.parseInt(st.nextToken());

		//맥날 지점
		st= new StringTokenizer(br.readLine());
		mPoint= new HashSet<>();
		for(int i=0;i<M;i++){
			mPoint.add(Integer.parseInt(st.nextToken()));
		}

		//스벅 수와 범위
		st= new StringTokenizer(br.readLine());
		S=Integer.parseInt(st.nextToken());
		y=Integer.parseInt(st.nextToken());

		//스벅 지점
		st= new StringTokenizer(br.readLine());
		sPoint= new HashSet<>();
		for(int i=0;i<S;i++){
			sPoint.add(Integer.parseInt(st.nextToken()));
		}

		dist= new int[V+1][V+1];
		for(int i=0;i<=V;i++){
			Arrays.fill(dist[i],Integer.MAX_VALUE);
			dist[i][i]=0;
		}


		for(int i=1;i<=V;i++){
			//정점이 집일떄
			if(!mPoint.contains(i) && !sPoint.contains(i)) {
				//다익스트라 시작
				int tmp= bj.dijk(i);

				if(min>tmp){
					min=tmp;
					ans=i;
				}
			}
		}
		System.out.println(ans);
	}
	static int bj.dijk(int idx){
		PriorityQueue<Node> pq= new PriorityQueue<>();
		pq.add(new Node(idx,0));

		while(!pq.isEmpty()){
			Node cur= pq.poll();
			int curTo=cur.to;
			int curCost=cur.cost;

			if(dist[idx][curTo]<curCost) continue;

			for(Node next:bj.graph.get(curTo)){
				int nextTo= next.to;
				int nextCost= next.cost+ curCost;

				if(dist[idx][nextTo]>nextCost){
					dist[idx][nextTo]=nextCost;
					pq.add(new Node(nextTo,nextCost));
				}
			}
		}

		int mMin=Integer.MAX_VALUE;
		int sMin=Integer.MAX_VALUE;
		//한 정점에서 mPoint, sPoint의 최소값 추출
		for(int mIdx: mPoint){
			mMin = Math.min(mMin, dist[idx][mIdx]);
		}
		//System.out.print(idx+"에서 맥날 최소는 "+ mMin);

		for(int sIdx: sPoint){
			sMin=Math.min(sMin,dist[idx][sIdx]);
		}
		//System.out.println("스벅 최소는 "+ sMin);


		if(mMin<=x && sMin<=y)
			return mMin+sMin;
		else return Integer.MAX_VALUE;


	}
	 */
}
