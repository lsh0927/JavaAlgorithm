package bj.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj1027 {


	public class Main {

		static int[] arr;
		static int N;
		public static void main(String[] args) throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			N = Integer.parseInt(br.readLine());


			StringTokenizer st = new StringTokenizer(br.readLine());
			arr = new int[N];

			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			int ans=0;
			for(int i=0;i<N;i++){
				ans= Math.max(ans,Count(i));
			}
			System.out.println(ans);
		}
		static int Count(int idx){
			int cnt=0;
			double tmp=0;

			for(int i=idx-1;i>=0;i--){
				double slope = (double) (arr[idx]-arr[i]) /(idx-i);

				if(i==idx-1 || slope<tmp){
					cnt++;
					tmp=slope;
				}
			}

			// 오른쪽
			for (int i=idx+1; i<N; i++) {
				double slope = (double) (arr[idx] - arr[i]) / (idx - i);

				if (i == idx+1 || tmp < slope) {
					cnt++;
					tmp = slope;
				}
			}

			return cnt;
		}
	}
}
