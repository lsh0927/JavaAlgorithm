package graph;

import java.util.*;
import java.io.*;

public class bj2206 {

		static class Node {
			int x;
			int y;
			int wall; // 벽을 부순 횟수 (0 또는 1)
			int dist; // 이동 거리

			public Node(int x, int y, int wall, int dist) {
				this.x = x;
				this.y = y;
				this.wall = wall;
				this.dist = dist;
			}
		}

		static int[][] map;
		static int N, M;
		static int[] dx = {0, 1, 0, -1};
		static int[] dy = {1, 0, -1, 0};
		static boolean[][][] visited; // [x][y][벽을 부순 상태(0 또는 1)]

		public static void main(String[] args) throws Exception {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			map = new int[N][M];
			visited = new boolean[N][M][2]; // 0: 벽을 부수지 않은 상태, 1: 벽을 부순 상태

			for(int i = 0; i < N; i++) {
				String s = br.readLine();
				for(int j = 0; j < M; j++) {
					map[i][j] = s.charAt(j) - '0';
				}
			}

			Queue<Node> q = new LinkedList<>();
			q.add(new Node(0, 0, 0, 1)); // 시작 위치, 벽을 부수지 않은 상태, 거리 1
			visited[0][0][0] = true;

			while(!q.isEmpty()) {
				Node cur = q.poll();

				if(cur.x == N-1 && cur.y == M-1) {
					System.out.println(cur.dist);
					return;
				}

				for(int i = 0; i < 4; i++) {
					int nx = cur.x + dx[i];
					int ny = cur.y + dy[i];

					if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;

					// 다음 칸이 벽이 아니고 해당 상태로 방문하지 않았을 때
					if(map[nx][ny] == 0 && !visited[nx][ny][cur.wall]) {
						visited[nx][ny][cur.wall] = true;
						q.add(new Node(nx, ny, cur.wall, cur.dist + 1));
					}
					// 다음 칸이 벽이고, 아직 벽을 부수지 않았고, 해당 상태로 방문하지 않았을 때
					else if(map[nx][ny] == 1 && cur.wall == 0 && !visited[nx][ny][1]) {
						visited[nx][ny][1] = true;
						q.add(new Node(nx, ny, 1, cur.dist + 1));
					}
				}
			}

			System.out.println(-1); // 도착점에 도달할 수 없는 경우
		}
	}
/*
	static class Node{
		int x;
		int y;
		int cnt;
		int step;

		public Node( int x, int y,int cnt, int step) {
			this.cnt = cnt;
			this.step=step;
			this.x = x;
			this.y = y;
		}
	}
	static int[][] map;
	static int N,M;
	static int[] dx= {0,1,0,-1};
	static int[] dy= {1,0,-1,0};
	static boolean[][] visited;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());

		map= new int[N][M];
		visited= new boolean[N][M];

		for(int i=0;i<N;i++){
			String s= br.readLine();
			for(int j=0;j<M;j++){
				map[i][j]=s.charAt(j)-'0';
			}
		}

		Queue<Node> q= new LinkedList<>();
		q.add(new Node(0,0,0,1));
		visited[0][0]=true;

		while(!q.isEmpty()){
			Node cur= q.poll();

			if(cur.x==N-1 && cur.y==M-1){
				System.out.println(cur.step);
				return;
			}

			for(int i=0;i<4;i++){
				int nx= cur.x+dx[i];
				int ny= cur.y+dy[i];

				if(nx>=0 && nx<N && ny>=0 && ny<M && !visited[nx][ny]){
					if(map[nx][ny]==1){
						if(cur.cnt==0) {
							visited[nx][ny] = true;
							q.add(new Node(nx, ny, cur.cnt+1, cur.step + 1));
						}
					}
					else{
						visited[nx][ny]=true;
						q.add(new Node(nx,ny,cur.cnt,cur.step+1));
					}
				}
			}
		}

		System.out.print(-1);

	}

 */
