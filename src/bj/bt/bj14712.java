package bj.bt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj14712 {

	static int R,C;
	static int[][] map;
	static int ans;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());

		R=Integer.parseInt(st.nextToken());
		C=Integer.parseInt(st.nextToken());

		map= new int[R][C];

		dfs(0,0);
		System.out.println(ans);

	}
	static void dfs(int r, int c){
		if(r==R){
			ans++;
			return;
		}

		if(c==C){
			dfs(r+1,0);
			return;
		}

		dfs(r,c+1);

		map[r][c]=1;
		if(!check(r,c))
			dfs(r,c+1);
		map[r][c]=0;


	}
	static boolean check(int r, int c){
		//2x2가 만들어지려면 (r-1,c-1) - (r,c)까지 검사
		if(r-1>=0 && c-1>=0){
			if(map[r-1][c-1]==1 &&
				map[r-1][c]==1 &&
				map[r][c-1]==1 &&
				map[r][c]==1)
				return true;
		}
		return false;
	}
}
