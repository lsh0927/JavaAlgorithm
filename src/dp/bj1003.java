package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj1003 {

	static  Integer[][] dp;
	public static void main(String[] args) throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st = new StringTokenizer(br.readLine());

			dp= new Integer[41][2];

			dp[0][0]=1; //N이 0일때의 0호출 횟수
			dp[0][1]=0;
			dp[1][0]=0;
			dp[1][1]=1;

			int T= Integer.parseInt(st.nextToken());


			for(int i=0;i<T;i++){
				int n= Integer.parseInt(br.readLine());
				fibonacci(n);
				System.out.println(dp[n][0]+" "+dp[n][1]);
			}
		}
		static Integer[] fibonacci(int n) {
			if(dp[n][0]==null || dp[n][1]==null){
				dp[n][0]=fibonacci(n-1)[0]+fibonacci(n-2)[0];
				dp[n][1]=fibonacci(n-1)[1]+fibonacci(n-2)[1];
			}

			return dp[n];
		}
	/*
	 public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int i = 0; i < T; i++){
            int N = Integer.parseInt(br.readLine());
            int[] fibonacci0 = new int[41];
            int[] fibonacci1 = new int[41];
            fibonacci0[0] = 1;
            fibonacci0[1] = 0;
            fibonacci1[0] = 0;
            fibonacci1[1] = 1;
            for(int k = 2; k <= N; k++){
                fibonacci0[k] = fibonacci0[k-1] + fibonacci0[k-2];
                fibonacci1[k] = fibonacci1[k-1] + fibonacci1[k-2];
            }
            System.out.println(fibonacci0[N] + " " + fibonacci1[N]);
        }
    }
	 */
}
