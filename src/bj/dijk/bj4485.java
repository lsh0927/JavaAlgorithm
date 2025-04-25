package bj.dijk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj4485 {

	static class Node{
		int x;
		int y;
		int cost;

		public Node(int x, int y, int cost) {
			this.x = x;
			this.y = y;
			this.cost = cost;
		}
	}
	static int N;
	static int[][] map;
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cnt=1;
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			if (N==0) break;

			map = new int[N][N];
			visited = new boolean[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			Queue<Node> queue =  new PriorityQueue<>((a,b)->a.cost-b.cost);
			queue.add(new Node(0, 0, map[0][0]));

			while (!queue.isEmpty()) {
				Node node = queue.poll();
				int x = node.x;
				int y = node.y;
				int cost = node.cost;

				if (x==N-1 && y==N-1) {
					System.out.println("Problem" + " "+ cnt++ +":" +" "+node.cost);
					break;
				}

				for (int i=0; i<4; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];

					if(nx>=0 && nx<N && ny>=0 && ny<N && !visited[nx][ny]) {
						visited[nx][ny] = true;
						queue.add(new Node(nx, ny, cost+map[nx][ny]));
					}
				}
			}
		}
	}
}