package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj7576{

	static int M, N;
	static int[][] map;
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[M][N];
		Queue<int[]> queue = new LinkedList<>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) {
					queue.add(new int[]{i, j});
				}
			}
		}

		System.out.println(bfs(queue));
	}

	static int bfs(Queue<int[]> queue) {
		int max = 1;

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int x = cur[0];
			int y = cur[1];

			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if (nx >= 0 && nx < M && ny >= 0 && ny < N && map[nx][ny] == 0) {
					map[nx][ny] = map[x][y] + 1;
					queue.add(new int[]{nx, ny});
					max = Math.max(max, map[nx][ny]);
				}
			}
		}

		if (checkZero()) return -1;
		return max - 1;
	}

	static boolean checkZero() {
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 0) return true;
			}
		}
		return false;
	}
}
