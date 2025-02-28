package graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class bj2667 {

	static char[][] map;
	static boolean[][] visited;
	static List<Integer> list;
	static int[] dx={0,1,0,-1};
	static int[] dy={1,0,-1,0};
	static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));

		N= Integer.parseInt(br.readLine());

		map= new char[N][N];
		visited= new boolean[N][N];
		list= new ArrayList<>();

		for(int i=0;i<N;i++){
			String s= br.readLine();
			for(int j=0;j<N;j++){
				map[i][j]=s.charAt(j);
			}
		}

		for(int i=0;i<N;i++){
			for(int j=0;j<N;j++){
				if(map[i][j]=='1' && !visited[i][j]){
					list.add(dfs(i,j));
				}
			}
		}
		Collections.sort(list);

		System.out.println(list.size());
		for(int n: list){
			System.out.println(n);
		}
	}
	// static int dfs(int x, int y,int cnt){
	// 	visited[x][y]=true;
	//
	// 	for(int i=0;i<4;i++){
	// 		int nx= x+dx[i];
	// 		int ny= y+dy[i];
	//
	// 		if(nx>=0 && nx<N && ny>=0 && ny<N && !visited[nx][ny] && map[nx][ny]=='1'){
	// 			visited[nx][ny]=true;
	// 			dfs(nx,ny,cnt+1);
	//			이렇게 계산하면 cnt가 호출반환될때 제대로 반영되지 않음
	// 		}
	// 	}
	//
	// 	return cnt;
	// }
	static int dfs(int x, int y) {
		visited[x][y] = true;
		int count = 1;  // 현재 위치 포함
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny] && map[nx][ny]=='1') {
				count += dfs(nx, ny);
			}
		}
		return count;
	}

}