package bj.graph;

import java.io.*;
import java.util.*;

public class bj1012 {

	static int[][] map;
	static int M,N,K;
	static boolean[][] visited;
	static int[] dx={1,-1,0,0};
	static int[] dy={0,0,1,-1};
	static int warm;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int tc= Integer.parseInt(br.readLine());

		for (int i=1;i<=tc;i++){
			warm=0;
			StringTokenizer st= new StringTokenizer(br.readLine());
			M=Integer.parseInt(st.nextToken());
			N=Integer.parseInt(st.nextToken());
			K=Integer.parseInt(st.nextToken());

			map= new int[N][M];
			visited= new boolean[N][M];

			for (int j=0;j<K;j++){
				st= new StringTokenizer(br.readLine());
				int c=Integer.parseInt(st.nextToken());
				int r=Integer.parseInt(st.nextToken());
				map[r][c]=1;
			}

			cal();

			System.out.println(warm);
		}
	}
	static void cal(){

		for (int i=0;i<N;i++){
			for (int j=0;j<M;j++){
				if(map[i][j]==1 && !visited[i][j]){
					visited[i][j]=true;
					bfs(i,j);
					warm++;
				}
			}
		}
	}
	static void bfs(int r, int c){
		Queue<int[]> q= new LinkedList<>();
		q.add(new int[]{r,c});

		while (!q.isEmpty()){
			int[] cur = q.poll();
			int x= cur[0];
			int y= cur[1];

			for (int i=0;i<4;i++){
				int nx= x+dx[i];
				int ny= y+dy[i];

				if(nx>=0 && ny>=0 && nx<N && ny<M && !visited[nx][ny] && map[nx][ny]==1){
					visited[nx][ny]=true;
					q.add(new int[]{nx,ny});
				}
			}
		}
	}
}
