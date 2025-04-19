package kakao;
import java.util.*;

public class PR_석유시추 {
	// class Solution {
	//
	// 	static class Node{
	// 		int x;
	// 		int y;
	//
	// 		public Node(int x, int y){
	// 			this.x=x;
	// 			this.y=y;
	// 		}
	// 	}
	//
	// 	static int res;
	// 	static int[] dx={1,-1,0,0};
	// 	static int[] dy={0,0,1,-1};
	// 	static int n,m;
	// 	static boolean[][] visited;
	// 	static int max;
	// 	public int solution(int[][] land) {
	// 		n=land.length;
	// 		m=land[0].length;
	//
	// 		for(int j=0;j<m;j++){
	// 			visited= new boolean[n][m];
	// 			res= bfs(land, j,0);
	// 			max=Math.max(max,res);
	// 		}
	//
	// 		return max;
	// 	}
	// 	static int bfs(int[][] land, int j, int sum){
	// 		Queue<Node> q= new LinkedList<>();
	//
	// 		for(int i=0;i<n;i++){
	// 			if(!visited[i][j]&& land[i][j]==1){
	// 				q.add(new Node(i,j));
	// 				visited[i][j]=true;
	// 				sum++;
	// 			}
	// 		}
	//
	// 		while(!q.isEmpty()){
	// 			Node cur= q.poll();
	//
	// 			int x=cur.x;
	// 			int y=cur.y;
	//
	// 			for(int i=0;i<4;i++){
	// 				int nx= x+dx[i];
	// 				int ny= y+dy[i];
	//
	//
	// 				if(nx>=0 && nx<n && ny>=0 && ny<m && !visited[nx][ny] && land[nx][ny]==1){
	// 					sum++;
	// 					visited[nx][ny]=true;
	// 					q.add(new Node(nx,ny));
	// 				}
	// 			}
	// 		}
	// 		return sum;
	// 	}
	// }

	class Solution {
		// 가로, 세로 길이
		static int n, m;
		// 시추관 위치별 석유량
		static int[] oil;

		public int solution(int[][] land) {
			int answer = 0;

			n = land.length;
			m = land[0].length;
			oil = new int[m];

			boolean[][] visited = new boolean[n][m];

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (land[i][j] == 1 && visited[i][j] == false) {
						bfs(land, visited, i, j);
					}
				}
			}

			answer = Arrays.stream(oil).max().getAsInt();
			System.out.println(answer);
			return answer;
		}

		public void bfs(int[][] land, boolean[][] visited, int x, int y) {
			Queue<int[]> q = new LinkedList<>();
			q.add(new int[]{x, y});
			visited[x][y] = true;

			// 상, 하, 좌, 우 이동
			int[] dx = {-1, 1, 0, 0};
			int[] dy = {0, 0, -1, 1};

			// 석유 덩어리 개수
			int count = 1;
			// 석유 덩어리의 열 위치 저장
			Set<Integer> set = new HashSet<>();

			while (!q.isEmpty()) {
				int[] now = q.poll();
				set.add(now[1]);

				for (int i = 0; i < 4; i++) {
					int nx = now[0] + dx[i];
					int ny = now[1] + dy[i];

					// 땅 범위를 넘는 경우 생략
					if (!checkRange(nx, ny)) {
						continue;
					}

					// 빈 땅이거나, 방문한 적이 있는 경우
					if (land[nx][ny] == 1 && visited[nx][ny] == false) {
						q.add(new int[]{nx, ny});
						visited[nx][ny] = true;
						count += 1;
					}
				}
			}

			for (int index : set) {
				oil[index] += count;
			}
		}

		public boolean checkRange(int x, int y) {
			if (x < 0 || x >= n || y < 0 || y >= m) {
				return false;
			}
			return true;
		}
	}
}
