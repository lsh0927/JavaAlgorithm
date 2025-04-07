import java.io.*;
import java.util.*;

public class Main {

	static class Ball{
		int idx;
		int color;
		int size;

		public Ball(int idx, int color, int size){
			this.idx=idx;
			this.color=color;
			this.size=size;
		}
	}

	static int N;
	static Ball[] balls;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		balls= new Ball[N];

		for(int i=0;i<N;i++){
			st= new StringTokenizer(br.readLine());
			balls[i]=new Ball(i,Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
		}

		Arrays.sort(balls, Comparator.comparingInt(o -> o.size));

		int[] result= new int[N];
		int[] color= new int[N];
		int sum=0;
		int idx=0;

		for(int i=0;i<N;i++){
			Ball cur= balls[i];
			while(idx<i && balls[idx].size<cur.size){
				sum+=cur.size;
				//현재 cur의 size는 더할 필요없이, 그 이전의 같은 color만 거르면 됨
				color[balls[idx].color]+= balls[idx].size;
				idx++;
			}
			result[i]=sum-color[cur.color];
		}


		// 결과 출력
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			sb.append(result[i]).append('\n');
		}
		System.out.print(sb);

	}
}