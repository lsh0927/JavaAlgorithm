import java.io.*;
import java.util.*;

public class Main {

	static class Node{
		int from;
		int to;
		int cost;

		public Node(int from,int to, int cost) {
			this.from=from;
			this.to = to;
			this.cost = cost;
		}
	}
	static int N,M;
	static int[] parent;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		M=Integer.parseInt(st.nextToken());

		parent= new int[N+1];
		for (int i=1;i<=N;i++){
			parent[i]=i;
		}
		PriorityQueue<Node> pq= new PriorityQueue<>(Comparator.comparingInt(a->a.cost));

		for (int i=0;i<M;i++){
			st= new StringTokenizer(br.readLine());
			int from= Integer.parseInt(st.nextToken());
			int to= Integer.parseInt(st.nextToken());
			int cost= Integer.parseInt(st.nextToken());

			pq.add(new Node(from,to,cost));

		}

		int sum=0;
		while (!pq.isEmpty()){
			Node cur= pq.poll();
			int from= cur.from;
			int to= cur.to;
			int cost= cur.cost;

			if(find(from)!=find(to)){
				union(from,to);
				sum+=cost;
			}
		}

		System.out.println(sum);
	}
	static int find(int a){
		if(a==parent[a]) return a;
		return parent[a]=find(parent[a]);
	}
	static void union(int a, int b){
		a=find(a);
		b=find(b);
		if(a!=b) parent[a]=b;
	}
}