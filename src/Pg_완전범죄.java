import java.util.*;

public class Pg_완전범죄 {
		// public int solution(int[][] info, int n, int m) {
		//
		// 	PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> {
		// 		if (a[0] != b[0]) {
		// 			return b[0] - a[0];
		// 		} else {
		// 			return a[1] - b[1];
		// 		}
		// 	});
		//
		// 	for (int[] inf : info) {
		// 		pq.add(inf);
		// 	}
		//
		// 	int curA = 0;
		// 	int curB = 0;
		// 	while (!pq.isEmpty()) {
		// 		int[] cur = pq.poll();
		// 		if (curB + cur[1] < m)
		// 			curB += cur[1];
		// 		else if (curA + cur[0] < n)
		// 			curA += cur[0];
		// 		else {
		// 			return -1;
		// 		}
		// 	}
		//
		// 	return curA;
		// }
		static final int INF = 100000;
		public int solution(int[][] info, int n, int m) {
			int size = info.length;
			int [][] dp = new int [size+1][m];
			for(int i = 0; i <= size; i++){
				Arrays.fill(dp[i], INF);
			}

			//dp[x][y]=z;
			// x번째 물건을 훔칠때, b의 흔적을 y개로 봤을 때 a의 최소흔적 개수 z
			dp[0][0] = 0;
			for(int i = 1; i <= size; i++){
				int a = info[i-1][0];
				int b = info[i-1][1];
				for(int j = 0; j < m; j++){
					// a 선택
					dp[i][j] = Math.min(dp[i][j], dp[i-1][j] + a);
					// b 선택
					if(j + b < m){
						dp[i][j + b] = Math.min(dp[i][j + b], dp[i-1][j]);
					}
				}
			}
			int min = INF;
			for(int j = 0; j < m; j++){
				min = Math.min(dp[size][j], min);
			}
			return min >= n ? -1 : min;
		}
}
