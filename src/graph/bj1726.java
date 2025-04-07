package graph;

import java.io.*;
import java.util.*;

public class bj1726 {

	static int N,M;
	static int[][] map;
	static boolean[][][] visited;
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());

		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());

		map= new int[N][M];
		visited= new boolean[N][M][4];

		for(int i=0;i<N;i++){
			st= new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++){
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		st= new StringTokenizer(br.readLine());
		int sX= Integer.parseInt(st.nextToken())-1;
		int sY= Integer.parseInt(st.nextToken())-1;
		int sDir= Integer.parseInt(st.nextToken())-1;

		st= new StringTokenizer(br.readLine());
		int eX= Integer.parseInt(st.nextToken())-1;
		int eY= Integer.parseInt(st.nextToken())-1;
		int eDir= Integer.parseInt(st.nextToken())-1;

		Queue<int[]> q= new LinkedList<>();
		q.add(new int[]{sX,sY,sDir,0});
		visited[sX][sY][sDir]=true;

		while(!q.isEmpty()){
			int[] cur= q.poll();
			int x= cur[0];
			int y= cur[1];
			int dir= cur[2];
			int cnt= cur[3];

			if(x==eX && y==eY && dir == eDir){
				System.out.println(cnt);
				return;
			}

			//동작 1: 현재 이동하는 방향으로 1~3칸 움직이기
			for(int i=1;i<=3;i++){
				int nx=  x +dx[dir] * i;
				int ny=  y +dy[dir] * i;



				// if(nx>=0 && nx<N & ny>=0 && ny<M  && map[nx][ny]==0 && !visited[nx][ny][dir]){
				// 	visited[nx][ny][dir]=true;
				// 	q.add(new int[]{nx,ny,dir,cnt+1});
				// }

				//이 루프는 각 거리(1칸, 2칸, 3칸)에 대해 독립적으로 확인하지만,
				// 로봇이 i=3으로 3칸 움직일 때 중간에 있는 칸(i=1, i=2)에 벽이 있는지는 확인하지 않습니다.

				if(nx < 0 || nx >= N || ny < 0 || ny >= M || map[nx][ny] == 1)
					break; // 경로를 벗어나거나 벽을 만나면 더 이상 진행 불가

				if(!visited[nx][ny][dir]){
					visited[nx][ny][dir] = true;
					q.add(new int[]{nx, ny, dir, cnt+1});
				}
			}


			//동작 2: 왼쪽 혹은 오른쪽으로 방향 바꾸기
			if(dir==0){
				if(!visited[x][y][2]){
					visited[x][y][2]=true;
					q.add(new int[]{x,y,2,cnt+1});
				}
				if(!visited[x][y][3]){
					visited[x][y][3]=true;
					q.add(new int[]{x,y,3,cnt+1});
				}
			}else if(dir==1){
				if(!visited[x][y][3]){
					visited[x][y][3]=true;
					q.add(new int[]{x,y,3,cnt+1});
				}
				if(!visited[x][y][2]){
					visited[x][y][2]=true;
					q.add(new int[]{x,y,2,cnt+1});
				}
			}else if(dir==2){
				if(!visited[x][y][1]){
					visited[x][y][1]=true;
					q.add(new int[]{x,y,1,cnt+1});
				}
				if(!visited[x][y][0]){
					visited[x][y][0]=true;
					q.add(new int[]{x,y,0,cnt+1});
				}
			}
			else{
				if(!visited[x][y][1]){
					visited[x][y][1]=true;
					q.add(new int[]{x,y,1,cnt+1});
				}
				if(!visited[x][y][0]){
					visited[x][y][0]=true;
					q.add(new int[]{x,y,0,cnt+1});
				}
			}

		}
	}
}