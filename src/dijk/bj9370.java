package dijk;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class bj9370 {

	static class Node {
		int to;
		int cost;

		public Node(int to, int cost){
			this.to=to;
			this.cost=cost;
		}
	}

	static List<List<Node>> graph;
	static int n,m,t;  //교차로, 도로, 목적지 후보의 개수
	static int s,g,h;  //출발지, g,h는 교차로 사이의 도로를 지나갔다는 것을 의미
	static int[] dest;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());

		for(int tc=0; tc<TC; tc++){
			StringTokenizer st= new StringTokenizer(br.readLine());
			n= Integer.parseInt(st.nextToken());
			m= Integer.parseInt(st.nextToken());
			t= Integer.parseInt(st.nextToken());

			st= new StringTokenizer(br.readLine());
			s= Integer.parseInt(st.nextToken());
			g= Integer.parseInt(st.nextToken());
			h= Integer.parseInt(st.nextToken());

			graph= new ArrayList<>();
			for(int i=0;i<=n;i++){
				graph.add(new ArrayList<>());
			}

			int gh_dist = 0;  // g-h 사이의 도로 길이

			for(int i=0;i<m;i++){
				st= new StringTokenizer(br.readLine());
				int from= Integer.parseInt(st.nextToken());
				int to= Integer.parseInt(st.nextToken());
				int cost= Integer.parseInt(st.nextToken());

				graph.get(from).add(new Node(to,cost));
				graph.get(to).add(new Node(from,cost));

				// g-h 도로 길이 저장
				if((from==g && to==h) || (from==h && to==g)){
					gh_dist = cost;
				}
			}

			dest= new int[t];
			for(int i=0;i<t;i++){
				dest[i]=Integer.parseInt(br.readLine());
			}

			// 시작점에서 각 노드까지의 최단 거리
			int[] distFromS = dijkstra(s);
			// g에서 각 노드까지의 최단 거리
			int[] distFromG = dijkstra(g);
			// h에서 각 노드까지의 최단 거리
			int[] distFromH = dijkstra(h);

			List<Integer> result = new ArrayList<>();

			// 각 목적지 후보에 대해 검사
			for(int candidate : dest){
				// s에서 candidate까지의 최단 거리
				int shortestPath = distFromS[candidate];

				if(shortestPath == Integer.MAX_VALUE) continue;

				// s->g->h->candidate 경로
				int pathThroughGH = Integer.MAX_VALUE;
				if(distFromS[g] != Integer.MAX_VALUE && distFromH[candidate] != Integer.MAX_VALUE){
					pathThroughGH = distFromS[g] + gh_dist + distFromH[candidate];
				}

				// s->h->g->candidate 경로
				int pathThroughHG = Integer.MAX_VALUE;
				if(distFromS[h] != Integer.MAX_VALUE && distFromG[candidate] != Integer.MAX_VALUE){
					pathThroughHG = distFromS[h] + gh_dist + distFromG[candidate];
				}

				// 두 경로 중 하나라도 최단 경로와 같다면 후보에 추가
				if(shortestPath == pathThroughGH || shortestPath == pathThroughHG){
					result.add(candidate);
				}
			}

			// 결과 정렬
			Collections.sort(result);

			// 결과 출력
			for(int res : result){
				sb.append(res).append(" ");
			}
			sb.append("\n");
		}

		System.out.print(sb.toString());
	}

	// 다익스트라 알고리즘
	static int[] dijkstra(int start){
		int[] dist = new int[n+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start] = 0;

		PriorityQueue<Node> pq= new PriorityQueue<>(Comparator.comparingInt(a->a.cost));
		pq.add(new Node(start, 0));

		while(!pq.isEmpty()){
			Node cur= pq.poll();
			int curTo=cur.to;
			int curCost=cur.cost;

			// 이미 처리된 노드면 스킵
			if(dist[curTo] < curCost) continue;

			// 인접 노드 탐색
			for(Node next : graph.get(curTo)){
				int nextCost = curCost + next.cost;

				// 더 짧은 경로 발견
				if(nextCost < dist[next.to]){
					dist[next.to] = nextCost;
					pq.add(new Node(next.to, nextCost));
				}
			}
		}

		return dist;
	}
}
