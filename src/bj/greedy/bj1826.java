package bj.greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class bj1826 {

	static class Node{
		int dist;
		int oil;

		public Node(int dist, int oil) {
			this.dist = dist;
			this.oil = oil;
		}
	}
	static int N,L,P;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N=Integer.parseInt(st.nextToken());
		PriorityQueue<Node> pq= new PriorityQueue<>(Comparator.comparingInt(a->a.dist));
		for(int i=0;i<N;i++){
			st= new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			pq.add(new Node(a,b));
		}


		st= new StringTokenizer(br.readLine());
		L=Integer.parseInt(st.nextToken());
		P=Integer.parseInt(st.nextToken());

		//먼저 현재 갈수 있는 범위 중 가장 연료가 많은 주유소 선택
		PriorityQueue<Integer> fuels = new PriorityQueue<>(Collections.reverseOrder());
		int answer=0;

		while(P<L){
			while(!pq.isEmpty()&& pq.peek().dist<=P){
				fuels.add(pq.poll().oil);
			}
			if(fuels.isEmpty()){
				System.out.println(-1);
				return;
			}
			answer++;
			P+=fuels.poll();
		}
		System.out.print(answer);
	}
}
