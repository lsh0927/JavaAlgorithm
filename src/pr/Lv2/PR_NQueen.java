package pr.Lv2;

public class PR_NQueen {
	class Solution {
		static int[][] map;
		static int ans;

		public int solution(int n) {
			map = new int[n][n];
			ans = 0;

			dfs(0, n);
			return ans;
		}

		static void dfs(int row, int n) {
			if (row == n) {
				ans++;
				return;
			}

			for (int col = 0; col < n; col++) {
				if (isValid(row, col, n)) {
					map[row][col] = 1;
					dfs(row + 1, n);
					map[row][col] = 0;
				}
			}
		}

		static boolean isValid(int row, int col, int n) {
			// 열비교
			for (int i = 0; i < row; i++) {
				if (map[i][col] == 1) {
					return false;
				}
			}

			// 좌상단
			for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
				if (map[i][j] == 1) {
					return false;
				}
			}

			// 우상단
			for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
				if (map[i][j] == 1) {
					return false;
				}
			}
			return true;
		}
	}
}
