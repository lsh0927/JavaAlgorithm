package simulation;

import java.io.*;
import java.util.*;

public class bj10800 {

	static class Ball implements Comparable<Ball> {
		int idx, color, size;

		public Ball(int idx, int color, int size) {
			this.idx = idx;
			this.color = color;
			this.size = size;
		}

		@Override
		public int compareTo(Ball o) {
			return this.size - o.size;
		}
	}

	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		List<Ball> list = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			list.add(new Ball(i, c, s));
		}

		// 크기 순으로 정렬
		Collections.sort(list);

		int[] color = new int[N+1];
		int[] ans = new int[N];

		int sum=0;
		int j=0;

		for(int i=0;i<N;i++){
			Ball a= list.get(i);
			Ball b= list.get(j);

			while(b.size<a.size){
				sum+=b.size;
				color[b.color]+=b.size;
				b=list.get(++j);
			}

			ans[a.idx] = sum -color[a.color];
		}

		for(int num: ans){
			System.out.println(num);
		}
	}
}