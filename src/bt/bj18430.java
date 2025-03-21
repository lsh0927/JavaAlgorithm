package bt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj18430 {

	static int N,M;
	static int[][] map;
	static boolean[][] visited;
	static int max=0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());

		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());

		map= new int[N][M];
		visited= new boolean[N][M];

		for(int i=0;i<N;i++){
			st= new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++){
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}

		dfs(0,0,0);
		System.out.print(max);

	}
	static void dfs(int r, int c,int sum){
		if(r==N){
			max=Math.max(max,sum);
			return;
		}

		if(c==M){
			dfs(r+1,0,sum);
			return;
		}

		if(visited[r][c]){
			dfs(r,c+1,sum);
			return;
		}

		//북서
		if(c-1>=0 && r-1>=0 && !visited[r][c-1] && !visited[r-1][c]){
			visited[r-1][c]=true;
			visited[r][c-1]=true;
			visited[r][c]=true;

			dfs(r,c+1,sum+map[r][c]*2+map[r-1][c]+map[r][c-1]);

			visited[r-1][c]=false;
			visited[r][c-1]=false;
			visited[r][c]=false;

		}
		//북동
		if(c+1<M && r-1>=0 && !visited[r][c+1] && !visited[r-1][c]){
			visited[r-1][c]=true;
			visited[r][c+1]=true;
			visited[r][c]=true;

			dfs(r,c+1,sum+map[r][c]*2+map[r-1][c]+map[r][c+1]);

			visited[r-1][c]=false;
			visited[r][c+1]=false;
			visited[r][c]=false;

		}

		//남서
		if(c-1>=0 && r+1<N && !visited[r+1][c] && !visited[r][c-1]){
			visited[r+1][c]=true;
			visited[r][c-1]=true;
			visited[r][c]=true;


			dfs(r,c+1,sum+map[r][c]*2+map[r+1][c]+map[r][c-1]);

			visited[r+1][c]=false;
			visited[r][c-1]=false;
			visited[r][c]=false;

		}


		//남동
		if(c+1<M && r+1<N && !visited[r+1][c] && !visited[r][c+1]){
			visited[r+1][c]=true;
			visited[r][c+1]=true;
			visited[r][c]=true;


			dfs(r,c+1,sum+map[r][c]*2+map[r+1][c]+map[r][c+1]);

			visited[r+1][c]=false;
			visited[r][c+1]=false;
			visited[r][c]=false;
		}

		dfs(r,c+1,sum);

	}
}
