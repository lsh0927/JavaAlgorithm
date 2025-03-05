package union_find;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class bj1976 {

	static List<List<Integer>> graph;
	static List<Integer> list;
	static int N,M;
	static int[] parents;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N=Integer.parseInt(br.readLine());
		M=Integer.parseInt(br.readLine());

		graph= new ArrayList<>();
		list= new ArrayList<>();
		for(int i=0;i<=N;i++){
			graph.add(new ArrayList<>());
		}
		parents= new int[N+1];

		for(int i=0;i<=N;i++){
			parents[i]=i;
		}

		for(int i=1;i<=N;i++){
			st= new StringTokenizer(br.readLine());
			for(int j=1;j<=N;j++){
				if(Integer.parseInt(st.nextToken())==1){
					graph.get(i).add(j);
					graph.get(j).add(i);

					if(find(i)!=find(j)){
						union(i,j);
					}
				}
			}

		}
		st= new StringTokenizer(br.readLine());
		while(st.hasMoreTokens()){
			list.add(Integer.parseInt(st.nextToken()));
		}


		int tmp= find(list.get(0));
		for(int n: list){
			if(tmp!=find(n)){
				System.out.println("NO");
				return;
			}
		}
		System.out.println("YES");


	}
	static int find(int a){
		if(parents[a]==a){
			return a;
		}
		return parents[a]=find(parents[a]);
	}

	static void union(int a, int b){
		int aRoot= find(a);
		int bRoot= find(b);

		if(aRoot>bRoot){
			parents[bRoot]=aRoot;
		}
		else{
			parents[aRoot]=bRoot;
		}
	}
}
