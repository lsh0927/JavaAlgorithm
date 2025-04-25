package bj.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class BJ2251{
	static Set<Integer> list= new TreeSet<>();
	static boolean[][] visited;
	static int A,B,C;
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());

		A= Integer.parseInt(st.nextToken());
		B= Integer.parseInt(st.nextToken());
		C= Integer.parseInt(st.nextToken());

		visited= new boolean[201][201];
		dfs(0,0,C);

		for(Integer v: list){
			System.out.print(v+" ");
		}
	}
	static void dfs(int a, int b, int c){
		if(visited[a][b]){
			return;
		}

		visited[a][b] = true;


		if(a==0){
			list.add(c);
		}


		//0->1
		if(a+b>B){
			dfs((a+b)-B,B,c);
		}
		else{
			dfs(0,a+b,c);
		}

		//0->2
		dfs(0,b,a+c);

		//1->2
		dfs(a,0,b+c);

		//1->0
		if(a+b>A){
			dfs(A,(a+b)-A,c);
		}else{
			dfs(a+b,0,c);
		}

		//2->0
		if(a+c>A){
			dfs(A,b,(a+c)-A);
		}else{
			dfs(a+c,b,0);
		}

		//2->1
		if(b+c>B){
			dfs(a,B,(b+c)-B);
		}else{
			dfs(a,b+c,0);
		}
	}
}