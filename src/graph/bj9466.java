package graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class bj9466 {

	static int T,N,res;
	static int[] arr;
	static boolean[] visited, done;
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));

		T= Integer.parseInt(br.readLine());

		for(int t=1;t<=T;t++){
			N=Integer.parseInt(br.readLine());
			res=0;

			arr= new int[N+1];
			done=new boolean[N+1];
			visited= new boolean[N+1];

			StringTokenizer st= new StringTokenizer(br.readLine());
			for(int i=1;i<=N;i++){
				arr[i]=Integer.parseInt(st.nextToken());
			}

			for(int i=1; i<=N;i++){
				if(done[i]) continue;
				dfs(i);
			}
			System.out.println(N-res);

		}
	}
	static void dfs(int idx){
		if(done[idx]) //이전에 검사를 했으므로 리턴
			return;

		if(visited[idx]){ //방문 했었다==사이클 구성원이다.
			done[idx]=true; //이제 다시 볼일 없다
			res++; //구성원 체크
		}

		visited[idx]=true; //체크
		dfs(arr[idx]);
		done[idx]=true; //사이클 아닌 애들도 검사 끝남
		visited[idx]=false; //방문 체크 초기화
	}
}