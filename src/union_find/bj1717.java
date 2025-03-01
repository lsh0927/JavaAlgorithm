package union_find;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class bj1717 {

	static int[] parents;
	static int N,M;
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());

		N= Integer.parseInt(st.nextToken());
		M= Integer.parseInt(st.nextToken());
		parents= new int[N+1];

		for (int i = 1; i <= N; i++) {
			parents[i]= i;
		}

		for (int i = 1; i <= M; i++) {
			st= new StringTokenizer(br.readLine());
			int cmd= Integer.parseInt(st.nextToken());
			int a= Integer.parseInt(st.nextToken());
			int b= Integer.parseInt(st.nextToken());

			if (cmd==0){
				if (find(a)!=find(b)){
					union(a,b);
				}
			}
			else if (cmd==1){
				if(find(a)!=find(b)){
					System.out.println("NO");
				}
				else {
					System.out.println("YES");
				}
			}
		}
	}
	static void union(int a, int b) {
		int aRoot= find(a);
		int bRoot= find(b);

		if(aRoot>bRoot){
			parents[bRoot]= aRoot;
		}
		else parents[aRoot]= bRoot;
	}
	static int find(int a){
		if (parents[a]==a){
			return a;
		}
		return parents[a]=find(parents[a]);
	}
}