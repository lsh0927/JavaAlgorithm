package graph;

import java.io.*;
import java.util.*;

public class bj11724 {

	static int[] parents;
	static HashSet<Integer> set= new HashSet<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		int N=Integer.parseInt(st.nextToken());
		int M=Integer.parseInt(st.nextToken());

		parents=new int[N+1];
		for(int i=1;i<=N;i++){
			parents[i]=i;
		}

		for(int i=1;i<=M;i++){
			st= new StringTokenizer(br.readLine());
			int u= Integer.parseInt(st.nextToken());
			int v= Integer.parseInt(st.nextToken());

			if(find(u)!=find(v)){
				union(u,v);
			}
		}

		for(int i=1;i<parents.length;i++){
			set.add(find(parents[i]));
		}
		System.out.print(set.size());

	}
	static void union(int a, int b){
		int aRoot= find(a);
		int bRoot= find(b);

		if(aRoot>bRoot) parents[bRoot]=aRoot;
		else parents[aRoot]=bRoot;
	}
	static int find(int a){
		if(parents[a]==a) return a;
		return parents[a]= find(parents[a]);
	}
}