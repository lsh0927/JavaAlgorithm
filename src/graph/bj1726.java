package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj1726 {
	static class Node {
		int x;
		int y;
		int dir;
		int cnt;

		public Node(int x, int y, int dir, int cnt) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.cnt = cnt;
		}
	}

	static int M, N;
	static int[][] map;
	// 동(0) 서(1) 남(2) 북(3)
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	static boolean[][][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		map = new int[M + 1][N + 1];
		visited = new boolean[M + 1][N + 1][4]; // 3차원 방문 배열 (x, y, direction)

		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		int sx = Integer.parseInt(st.nextToken());
		int sy = Integer.parseInt(st.nextToken());
		int sDir = Integer.parseInt(st.nextToken()) - 1;

		st = new StringTokenizer(br.readLine());
		int ex = Integer.parseInt(st.nextToken());
		int ey = Integer.parseInt(st.nextToken());
		int eDir = Integer.parseInt(st.nextToken()) - 1;

		System.out.println(bfs(sx, sy, sDir, ex, ey, eDir));
	}

	static int bfs(int sx, int sy, int sDir, int ex, int ey, int eDir) {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(sx, sy, sDir, 0));
		visited[sx][sy][sDir] = true;

		while (!q.isEmpty()) {
			Node cur = q.poll();

			// 목적지에 도달하고 방향도 맞으면 종료
			if (cur.x == ex && cur.y == ey && cur.dir == eDir) {
				return cur.cnt;
			}

			// 동작 1: 현재 방향으로 1, 2, 3칸 이동
			for (int i = 1; i <= 3; i++) {
				int nx = cur.x + dx[cur.dir] * i;
				int ny = cur.y + dy[cur.dir] * i;

				// 벽에 부딪히면 더 이상 진행 불가
				if (nx <= 0 || nx > M || ny <= 0 || ny > N || map[nx][ny] == 1) break;

				// 이미 방문했으면 큐에 추가하지 않음
				if (!visited[nx][ny][cur.dir]) {
					visited[nx][ny][cur.dir] = true;
					q.add(new Node(nx, ny, cur.dir, cur.cnt + 1));
				}
			}

			// 동작 2: 방향 전환 (동-서, 남-북 쌍으로 반대 방향 전환 시 명령 두번 필요)
			for (int nDir = 0; nDir < 4; nDir++) {
				// 같은 방향이면 무시
				if (nDir == cur.dir) continue;

				// 방문하지 않은 방향이면 큐에 추가
				if (!visited[cur.x][cur.y][nDir]) {
					int turnCost = 1;
					// 반대 방향으로 회전은 명령 2번 필요
					if ((cur.dir == 0 && nDir == 1) || (cur.dir == 1 && nDir == 0) ||
						(cur.dir == 2 && nDir == 3) || (cur.dir == 3 && nDir == 2)) {
						turnCost = 2;
					}
					visited[cur.x][cur.y][nDir] = true;
					q.add(new Node(cur.x, cur.y, nDir, cur.cnt + turnCost));
				}
			}
		}
		return -1; // 도달할 수 없는 경우
	}
}