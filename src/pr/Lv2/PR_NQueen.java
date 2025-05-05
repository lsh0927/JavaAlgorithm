package pr.Lv2;

public class PR_NQueen {
	// class Solution {
	// 	static int[][] map;
	// 	static int ans;
	//
	// 	public int solution(int n) {
	// 		map = new int[n][n];
	// 		ans = 0;
	//
	// 		dfs(0, n);
	// 		return ans;
	// 	}
	//
	// 	static void dfs(int row, int n) {
	// 		if (row == n) {
	// 			ans++;
	// 			return;
	// 		}
	//
	// 		for (int col = 0; col < n; col++) {
	// 			if (isValid(row, col, n)) {
	// 				map[row][col] = 1;
	// 				dfs(row + 1, n);
	// 				map[row][col] = 0;
	// 			}
	// 		}
	// 	}
	//
	// 	static boolean isValid(int row, int col, int n) {
	// 		// 열비교
	// 		for (int i = 0; i < row; i++) {
	// 			if (map[i][col] == 1) {
	// 				return false;
	// 			}
	// 		}
	//
	// 		// 좌상단
	// 		for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
	// 			if (map[i][j] == 1) {
	// 				return false;
	// 			}
	// 		}
	//
	// 		// 우상단
	// 		for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
	// 			if (map[i][j] == 1) {
	// 				return false;
	// 			}
	// 		}
	// 		return true;
	// 	}
	// }
	class Solution {
		static int[][] map;
		static int ans;
		public int solution(int n) {
			map= new int[n][n];
			dfs(0,n);
			return ans;
		}
		static void dfs(int depth, int n){
			if(depth==n){
				ans++;
				return;
			}

			for(int col=0;col<n;col++){
				if(isValid(depth,col,n)){
					map[depth][col]=1;
					dfs(depth+1,n);
					map[depth][col]=0;
				}
			}


		}

		static boolean isValid(int depth, int col, int n){
			for(int i=0;i<depth;i++){
				if(map[i][col]==1){
					//System.out.println(1);
					return false;
				}
			}

			for(int i=0;i<depth;i++){
				for(int j=0;j<col;j++){
					if(depth-i==col-j && map[i][j]==1){
						//System.out.println(2);
						return false;
					}
				}
			}

			for(int i=0;i<depth;i++){
				for(int j=col+1;j<n;j++){
					if(Math.abs(depth-i)==Math.abs(col-j)&& map[i][j]==1){
						//System.out.println(3);
						return false;
					}
				}
			}

			return true;
		}
	}
}
