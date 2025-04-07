package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj9944 {

		/*
		1칸(혹은 여러칸)씩 움직여 목표지점까지 가는 최소 비용(경로)의 경우 BFS가 가능함
		그런데 이 문제의 경우 "모든 빈 칸 방문 문제의 특성" 때문에 백트래킹을 택해야함
		또한 방향 전환은 장애물을 만났을때에만 가능함
	 */

	static int N, M;
	static char[][] map;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static int ans;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String line;
		int idx = 0;
		while ((line = br.readLine()) != null && !line.isEmpty()) {
			idx++;
			ans=-1;

			StringTokenizer st = new StringTokenizer(line);
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			map = new char[N][M];
			visited= new boolean[N][M];

			//이동횟수, 방문횟수
			int result=1;
			int cnt=1;

			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				map[i] = str.toCharArray();
				for (int j = 0; j < M; j++) {
					if (map[i][j] == '*') {
						visited[i][j]=true;
						cnt++;
					}
				}
			}

			if(cnt==N*M) ans=0;


			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					//방문했거나 장애물이면 넘김
					if(!visited[i][j]){
						for(int d=0;d<4;d++) {
							if(isValid(i+ dx[d],j+dy[d])) {
								visited[i][j] = true;
								dfs(i, j, result, d, cnt);
								visited[i][j] = false;
							}
						}
					}
				}
			}

			System.out.println("Case " + idx + ": " + ans);
		}
	}
	static void dfs(int i, int j, int result, int dir, int cnt){
		//종료: 모든 빈칸을 방문한 경우
		if(cnt==N*M){
			if(ans==-1 || ans>result){
				ans=result;
				return;
			}
		}

		int x= i+dx[dir];
		int y= j+dy[dir];

		if(isValid(x,y)){
			visited[x][y]=true;
			dfs(x,y,result,dir,cnt+1);
			visited[x][y]=false;
		}
		else{
			//꺾였으니 방향 변경
			for(int d=0;d<4;d++){
				if(d==dir) continue;

				int nx= i+dx[d];
				int ny= j+dy[d];

				if(isValid(nx,ny)){
					visited[nx][ny]=true;
					dfs(nx,ny,result+1,d,cnt+1);
					visited[nx][ny]=false;
				}
			}
		}

	}
	static boolean isValid(int x, int y){
		if(x<0 || x>=N || y<0 || y>=M || visited[x][y]) return false;
		return true;
	}





	// 	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	//
	// 	String line;
	// 	int idx=1;
	// 	while ((line = br.readLine()) != null) {
	//
	// 		ans=Integer.MAX_VALUE;
	// 		StringTokenizer st = new StringTokenizer(line);
	// 		N = Integer.parseInt(st.nextToken());
	// 		M = Integer.parseInt(st.nextToken());
	//
	// 		visited= new boolean[N][M];
	// 		map= new char[N][M];
	// 		for (int i = 0; i < N; i++) {
	// 			String str = br.readLine();
	// 			map[i] = str.toCharArray();
	// 		}
	//
	// 		// System.out.println(N);
	// 		// System.out.println(M);
	// 		//
	// 		// for(int i=0;i<N;i++){
	// 		// 	for(int j=0;j<M;j++){
	// 		// 		System.out.print(map[i][j]);
	// 		// 	}
	// 		// 	System.out.println();
	// 		// }
	//
	// 		for(int i=0;i<N;i++){
	// 			for(int j=0;j<M;j++){
	// 				if(map[i][j]=='.'){
	// 					bfs(i,j,0);
	// 				}
	// 			}
	// 		}
	// 		System.out.print("Case "+ idx+": "+ans);
	// 		System.out.println();
	// 		idx++;
	// 	}
	// }
	// static void bfs(int r, int c, int cnt){
	//
	// 	Queue<int[]> q= new LinkedList<>();
	// 	q.add(new int[]{r,c,0});
	//
	// 	while(!q.isEmpty()){
	// 		int[] cur= q.poll();
	// 		int curR=cur[0];
	// 		int curC=cur[1];
	// 		int curCnt=cur[2];
	//
	// 		if(check()){
	// 			ans=cur[2];
	// 			return;
	// 		}
	//
	// 		for(int i=0;i<4;i++){
	// 			for(int j=1;j<Math.max(N,M);j++) {
	// 				int nx = curR + dx[i]*j;
	// 				int ny = curC + dy[i]*j;
	//
	// 				if(nx<0 || nx>=N || ny<0 || ny>=M || visited[nx][ny] || map[nx][ny]=='*')
	// 					continue;
	//
	// 				visited[nx][ny]=true;
	// 				q.add(new int[]{nx,ny,curCnt+1});
	// 			}
	// 		}
	// 	}
	// }
	// static boolean check(){
	// 	for(int i=0;i<N;i++){
	// 		for(int j=0;j<M;j++){
	// 			if(map[i][j]=='.' && !visited[i][j])
	// 				return false;
	// 		}
	// 	}
	// 	return true;
	// }
}
