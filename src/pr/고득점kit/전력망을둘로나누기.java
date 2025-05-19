package pr.고득점kit;
import java.util.*;

public class 전력망을둘로나누기 {

	class Solution {
		static List<List<Integer>> graph;
		static int[] parent;
		static int ans = Integer.MAX_VALUE;
		public int solution(int n, int[][] wires) {

			graph=new ArrayList<>();
			for(int i=0;i<=n;i++){
				graph.add(new ArrayList<>());
			}

			parent= new int[n+1];
			for(int i=0;i<=n;i++){
				parent[i]=i;
			}

			for(int[] wire:wires){

				graph.get(wire[0]).add(wire[1]);
				graph.get(wire[1]).add(wire[0]);

				if(find(wire[0])!=find(wire[1])){
					union(wire[0],wire[1]);
				}
			}

			for(int i=0;i<wires.length;i++){
				//끊어내기
				int a= wires[i][0];
				int b= wires[i][1];

				graph.get(a).remove(Integer.valueOf(b));
				graph.get(b).remove(Integer.valueOf(a));
				//유니온 파인드에는 어떻게 반영이 되지?

				boolean[] visited= new boolean[n+1];

				//임의의 시작점에서 dfs
				int count1= dfs(1,visited);
				int count2=n-count1;

				//끊긴 애의 개수 세기
				ans= Math.min(ans, Math.abs(count1-count2));

				//복구
				graph.get(a).add(b);
				graph.get(b).add(a);
			}
			return ans;
		}
		static int dfs(int node, boolean[] visited){
			visited[node]=true;
			int count=1;

			for(int next: graph.get(node)){
				if(!visited[next]){
					count+=dfs(next,visited);
				}
			}
			return count;
		}

		static int find(int a){
			if(parent[a]==a){
				return a;
			}
			return parent[a]=find(parent[a]);
		}
		static void union(int a, int b){
			int aRoot=find(a);
			int bRoot=find(b);

			if(aRoot>bRoot){
				parent[aRoot]=bRoot;
			}else{
				parent[bRoot]=aRoot;
			}
		}
	}
}
