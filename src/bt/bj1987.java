package bt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj1987 {

	static int R,C;
	static char[][] map;
	static int max=Integer.MIN_VALUE;
	static boolean[] visited;
	static int[] dx={1,0,-1,0};
	static int[] dy={0,1,0,-1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());

		R=Integer.parseInt(st.nextToken());
		C=Integer.parseInt(st.nextToken());

		map= new char[R+1][C+1];
		visited= new boolean[26];

		for(int i=1;i<=R;i++){
			String str= br.readLine();
			for(int j=1;j<=C;j++){
				map[i][j]=str.charAt(j-1);
			}
		}

		dfs(1,1,1);
		System.out.println(max);
	}
	static void dfs(int x, int y, int cnt){
		max=Math.max(max,cnt);

		visited[map[x][y]-'A']=true;

		for(int i=0;i<4;i++){
			int nx=x+dx[i];
			int ny=y+dy[i];

			if(nx>0 && nx<=R && ny>0 && ny<=C && !visited[map[nx][ny]-'A']){
				visited[map[nx][ny]-'A']=true;
				dfs(nx,ny,cnt+1);
				visited[map[nx][ny]-'A']=false;
			}
		}

	}
}