package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj2146 {

	static boolean[][] check;
	static int[][] map;
	static int n;
	static Queue<int[]> q;
	// dx: 행의 변화, dy: 열의 변화 (상, 우, 하, 좌)
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		check = new boolean[n][n];
		q = new LinkedList<>();

		StringTokenizer st;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		bfs();

		int min = Integer.MAX_VALUE;
		// getDistance 함수 호출 시 (행, 열) 순서로 전달합니다.
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] > 0) {
					check = new boolean[n][n];
					int dis = getDistance(i, j);
					if (dis != -1 && min > dis) {
						min = dis;
					}
				}
			}
		}
		System.out.println(min - 1);
	}

	public static void bfs() {
		// 섬을 구분하기 위해 각 섬에 2부터 시작하는 고유 번호를 부여합니다.
		int value = 2;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (!check[i][j] && map[i][j] != 0) {
					map[i][j] = value;
					check[i][j] = true;
					q.add(new int[]{i, j});

					while (!q.isEmpty()) {
						int[] cur = q.poll();
						int curRow = cur[0];
						int curCol = cur[1];

						for (int d = 0; d < 4; d++) {
							int nr = curRow + dx[d];
							int nc = curCol + dy[d];

							if (nr >= 0 && nr < n && nc >= 0 && nc < n &&
								!check[nr][nc] && map[nr][nc] == 1) {
								check[nr][nc] = true;
								map[nr][nc] = value;
								q.add(new int[]{nr, nc});
							}
						}
					}
					value++;
				}
			}
		}
	}

	static int getDistance(int i, int j) {
		// (i, j)를 시작점으로 하여 다른 섬까지의 최소 다리 길이를 구합니다.
		q = new LinkedList<>();
		int num = map[i][j];
		check[i][j] = true;
		q.add(new int[]{i, j, 0});

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int curRow = cur[0];
			int curCol = cur[1];
			int dis = cur[2];

			if (map[curRow][curCol] != 0 && map[curRow][curCol] != num) {
				return dis;
			}

			for (int d = 0; d < 4; d++) {
				int nr = curRow + dx[d];
				int nc = curCol + dy[d];

				if (nr >= 0 && nr < n && nc >= 0 && nc < n &&
					!check[nr][nc] && map[nr][nc] != num) {
					check[nr][nc] = true;
					q.add(new int[]{nr, nc, dis + 1});
				}
			}
		}
		return -1;
	}
}
