package pr.고득점kit;
import java.util.*;

public class 전력망 {

	public class Solution {

		private final List<List<Integer>> adj = new ArrayList<>();
		private boolean[] check;

		public int solution(int n, int[][] wires) {
			int[][] tower = new int[n + 1][n + 1];
			check = new boolean[n + 1];
			for (int i = 0; i <= n; i++) {
				adj.add(new ArrayList<>());
			}
			for (int[] wire : wires) {
				adj.get(wire[0]).add(wire[1]);
				adj.get(wire[1]).add(wire[0]);
			}

			for (int i = 1; i <= n; i++) {
				List<Integer> list = adj.get(i);
				Arrays.fill(check, false);
				check[i] = true;
				for (Integer j : list) {
					tower[i][j] = count(j) + 1; // i번 타워에서 j번 타워방향 자식들의 수
				}
			}

			int min = Integer.MAX_VALUE;
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					if (tower[i][j] != 0) {
						// i번 타워에서 j번 타워방향 자식들 수와
						// j번 타워에서 i번 타워방향 자식들 수의 차이 최소값 구하기
						min = Math.min(Math.abs(tower[i][j] - tower[j][i]), min);
					}
				}
			}
			return min;
		}

		private int count(int num) {
			int sum = 0;
			check[num] = true;
			List<Integer> list = adj.get(num);
			for (Integer next : list) {
				if (!check[next]) {
					sum += count(next);
				}
			}
			return sum + (list.size() - 1); // 진행방향 타워 하나 빼주기
		}


	}

}
