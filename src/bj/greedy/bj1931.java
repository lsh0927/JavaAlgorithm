package bj.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class bj1931 {

	static int N;
	static int cnt;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N=Integer.parseInt(br.readLine());
		PriorityQueue<int[]> pq = new PriorityQueue<>(
			Comparator.comparingInt((int[] a) -> a[1])
				/*
				반례
				3
				2 2
				1 2
				2 3
				cnt=3
				않이 상식적으로 t에서 시작해서 t에 끝나는 회의가 왜있는데!!!
		 		*/
				.thenComparingInt(a -> a[0])
		);

		int max=0;
		for(int i=0;i<N;i++){
			st= new StringTokenizer(br.readLine());
			int start= Integer.parseInt(st.nextToken());
			int end= Integer.parseInt(st.nextToken());
			max=Math.max(max,end);
			pq.add(new int []{start,end});
		}
		int tmp=0;

		while(!pq.isEmpty()){
			int[] arr=pq.poll();
			int start=arr[0];
			int end=arr[1];

			if(tmp<=start){
				cnt++;
				tmp=end;
			}
		}

		System.out.println(cnt);


	}
}
