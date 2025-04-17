package kakao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PR_도넛과막대그래프 {

	/*
	실패 코드
	더 어떻게 해야할지 몰라서 못 품
	 */


	// import java.util.*;

	// class Solution {
	//     static boolean[] visit;
	//     static int[] parent;
	//     static List<List<Integer>> graph;
	//     public int[] solution(int[][] edges) {
	//         int[] answer= new int[4];

	//         graph= new ArrayList<>();

	//         int max=-1;
	//         for(int i=0;i<edges.length;i++){
	//             int[] tmp= edges[i];
	//             max=Math.max(Math.max(max,tmp[0]),tmp[1]);
	//         }

	//         visit= new boolean[max+1];
	//         parent= new int[max+1];
	//         for(int i=0;i<=max;i++){
	//             graph.add(new ArrayList<>());
	//         }


	//         for(int[] edge: edges){
	//             int from= edge[0];
	//             int to= edge[1];

	//             graph.get(from).add(to);
	//             visit[to]=true;

	//         }

	//         int center=0;
	//         //중심 찾기
	//         //중심으로는 이어진 선이 없음
	//         //막대 그래프의 처음 정점으로도 도달하는 선이 없음 -> 추가근거 필요
	//         //중심은 뻗어나가는게 1개 이상?
	//         //(도넛 모양 그래프, 막대 모양 그래프, 8자 모양 그래프의 수의 합은 2이상)
	//         for(int i=1;i<=max;i++){
	//             if(!visit[i]){
	//                 if(graph.get(i).size()>1){
	//                     center=i;
	//                 }
	//             }
	//         }

	//         //각 모양 찾기
	//         //중점으로부터 이어진 각 노드들의 목록
	//         List<Integer> list= graph.get(center);

	//         //도넛, 막대, 8자인걸 각각 어떻게 구분하지?
	//         //정점과 간선의 개수?
	//         //유니온 파인드?
	//         for(int start: list){
	//             //{2.8.11}
	//             List<Integer> li= graph.get(start);



	//         }

	//         return answer;
	//     }
	//     static int find(int a){
	//         if(a==parent[a]) return a;
	//         return parent[a]=find(parent[a]);
	//     }
	//     static void union(int a, int b){
	//         int aRoot= find(a);
	//         int bRoot= find(b);

	//         if(aRoot>bRoot) parent[aRoot]= bRoot;
	//         else parent[bRoot]=aRoot;
	//     }
	// }










	// import java.util.*;
	//
	// class Solution {
	// 	static List<List<Integer>> graph;
	// 	static int[] parent;
	// 	static boolean[] visited;
	// 	static int[] inDegree;
	// 	static int[] outDegree;
	// 	public int[] solution(int[][] edges) {
	// 		int[] answer= new int[4];
	//
	// 		int maxNode=0;
	//
	// 		for(int[] edge: edges){
	// 			maxNode= Math.max(maxNode,Math.max(edge[0],edge[1]));
	// 		}
	//
	// 		graph = new ArrayList<>();
	// 		parent = new int[maxNode + 1];
	// 		visited = new boolean[maxNode + 1];
	// 		inDegree = new int[maxNode + 1];
	// 		outDegree = new int[maxNode + 1];
	//
	// 		for(int i=0;i<=maxNode;i++){
	// 			graph.add(new ArrayList<>());
	// 			parent[i]=i;
	// 		}
	//
	// 		//그래프 구성 및 차수 계산
	// 		for(int[] edge: edges){
	// 			int from=edge[0];
	// 			int to=edge[1];
	//
	// 			graph.get(from).add(to);
	//
	// 			outDegree[from]++;
	// 			inDegree[to]++;
	// 		}
	//
	//
	// 		//중심 정점 찾기(outDegree>=2 && inDegree=0)
	// 		for (int i = 1; i <= maxNode; i++) {
	// 			if (outDegree[i] >= 2 && inDegree[i] == 0) {
	// 				answer[0] = i;
	// 				break;
	// 			}
	// 		}
	//
	// 		//중심 정점에서 출발하는 모든 그래프 확인
	// 		List<Integer> centralConnections= graph.get(answer[0]);
	//
	// 		//시작점 처리
	// 		for(int start: centralConnections){
	// 			//이미 다른 그래프의 일부인 노드는 건너뛰기
	// 			if(find(start)!=start) continue;
	//
	// 			//해당 시작점에서 출발하는 그래프의 특성 파악
	// 			int graphType= identifyGraphType(start);
	//
	// 			//결과 기록
	// 			answer[graphType]++;
	//
	// 		}
	// 		return answer;
	// 	}
	//
	// 	static int identifyGraphType(int start){
	// 		//방문 배열 초기화
	// 		Arrays.fill(visited,false);
	//
	// 		//DFS로 그래프 탐색하며 특성 파악
	// 		//해당 범위(사이클 내의 정점들의 outDegree 모음을 만들어  Degree가 0이면 막대 그래프, 2이면 8자 그래프
	// 		Map<Integer, Integer> nodeOutDegrees= new HashMap<>();
	//
	// 		dfs(start, nodeOutDegrees);
	//
	// 		//dfs가 끝나면 각 노드별
	// 		//해당 요소에 outDegree가 0인 노드가 있으면 막대 그래프
	//
	// 		boolean hasEndNode= false;
	//
	// 		//outDegree가 2인 노드가 있으면 8자 그래프
	// 		boolean hasTwoOutDegree=false;
	//
	// 		//Set<Map.Entry<K, V>> 형태에서 Map.Entry<K, V>만 뽑아 value확인
	// 		for(Map.Entry<Integer,Integer> entry: nodeOutDegrees.entrySet()){
	// 			int node= entry.getKey();
	// 			int degree= entry.getValue();
	//
	// 			if(degree==0) hasEndNode=true;
	// 			if(degree==2) hasTwoOutDegree=true;
	// 		}
	//
	// 		if (hasEndNode) return 2; // 막대 그래프
	// 		if (hasTwoOutDegree) return 3; // 8자 그래프
	// 		return 1; // 도넛 그래프
	//
	// 	}
	// 	static void dfs(int node, Map<Integer, Integer> nodeOutDegrees){
	// 		visited[node]=true;
	//
	// 		//현재 노드 기록
	// 		nodeOutDegrees.put(node,outDegree[node]);
	//
	// 		//인접 노드 탐색
	// 		for(int next: graph.get(node)){
	// 			if(!visited[next]){
	// 				//연결 정보 기록
	// 				union(node,next);
	// 				dfs(next,nodeOutDegrees);
	// 			}
	// 		}
	// 	}
	// 	// 유니온 파인드 - Find 연산
	// 	static int find(int x) {
	// 		if (parent[x] == x) return x;
	// 		return parent[x] = find(parent[x]);
	// 	}
	//
	// 	// 유니온 파인드 - Union 연산
	// 	static void union(int a, int b) {
	// 		int aRoot = find(a);
	// 		int bRoot = find(b);
	//
	// 		if (aRoot != bRoot) {
	// 			if (aRoot < bRoot) parent[bRoot] = aRoot;
	// 			else parent[aRoot] = bRoot;
	// 		}
	// 	}
	//
	// }



	class Solution {
		static int N = 1_000_000;

		public int[] solution(int[][] edges) {
			int[] ingoing = new int[N];
			int[] outgoing = new int[N];
			for(int[] edge : edges) {
				outgoing[edge[0]-1]++;
				ingoing[edge[1]-1]++;
			}
			int created = 0;
			int eight = 0;
			int stick = 0;
			for(int i=0; i<N; i++) {
				if(outgoing[i] >= 2) {
					if(ingoing[i] == 0) {
						created = i;
					}else {
						eight++;
					}
				}else if(outgoing[i] == 0 && ingoing[i] > 0){
					stick++;
				}
			}
			return new int[] {created+1,outgoing[created]-eight-stick,stick,eight};
		}
	}
}
