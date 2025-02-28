package graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class bj10451 {

	static List<List<Integer>> graph;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc= Integer.parseInt(br.readLine());

		for(int t=0;t<tc;t++){
			int N=Integer.parseInt(br.readLine());
			StringTokenizer st= new StringTokenizer(br.readLine());
			int[] arr= new int[N+1];

			graph= new ArrayList<>();
			visited= new boolean[N+1];

			for(int i=0;i<=N;i++){
				graph.add(new ArrayList<>());
			}

			for(int i=1;i<=N;i++){
				arr[i]=Integer.parseInt(st.nextToken());
			}

			for(int i=1;i<=N;i++){
				graph.get(i).add(arr[i]);
			}

			/* 여기서 막힘 -> 당연히 dfs지...하긴했는데 이게 실3이야?
			for(int from=1;from<=N;from++){
				for(int to: graph.get(from)){

				}
			}
			 */
			int cnt=0;
			for(int i=1;i<=N;i++){
				if(!visited[i]){
					cnt++;
					dfs(i);
				}
			}
			System.out.println(cnt);
		}
	}
	static void dfs(int idx){
		visited[idx]=true;

		for(int to: graph.get(idx)){
			if(!visited[to]){
				visited[to]=true;
				dfs(to);
			}
		}
	}
}
