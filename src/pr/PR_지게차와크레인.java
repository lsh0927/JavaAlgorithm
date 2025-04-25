package pr;

public class PR_지게차와크레인 {
	static int r;
	static int c;
	static boolean[][][] visited;
	static int ans=0;
	//[][][0]은 외부로 뚫려 있음
	//[][][1]은 방문은 했으나 외부와 연결되어있지 않음
	static char[][] map;
	static int[] dx= {1,-1,0,0};
	static int[] dy= {0,0,1,-1};

/*	public int solution(String[] storage, String[] requests) {
		r= storage.length;
		c= storage[0].length();


		map= new char[r+2][c+2];
		visited= new boolean[r+2][c+2][2];

		for(int i=0; i<r+2; i++){
			for(int j=0; j<c+2; j++){
				if(i==0 || i==r+1 || j==0 || j==c+1){
					map[i][j] = 'a';
					visited[i][j][0] = true;
				}
			}
		}

		for(int i=0; i<r; i++){
			for(int j=0; j<c; j++){
				map[i+1][j+1] = storage[i].charAt(j);
			}
		}

		for(int i=0;i<requests.length;i++){
			String str= requests[i];
			int len= str.length();

			char target;
			if(len==1){
				//조건 체크해야함
				target=str.charAt(0);

				//지게차 조건
				for(int a=1;a<=r;a++){
					for(int b=1;b<=c;b++){
						if(map[a][b]==target && check(a,b)){
							//이제 이 컨테이너를 빼고 빈공간으로 만들어 다음 지게차로 접근 가능하게
							visited[a][b][0]=true;
							//대문자가 아닌 문자로 변환
							map[a][b]='a';
						}
					}
				}
			}
			else if(len==2){
				//크레인은 조건없음
				target=str.charAt(0);
				for(int a=1;a<=r;a++){
					for(int b=1;b<=c;b++){
						if(map[a][b]==target && check(a,b)){
							visited[a][b][0]=true;
							//대문자가 아닌 문자로 변환
							map[a][b]='a';

							//주변의 새로운 연결공간이 있나 확인
							bfs(a,b);
						}
						else if(map[a][b]==target){
							visited[a][b][1]=true;
							map[a][b]='a';
						}
					}
				}
			}
		}

		for(int i=0;i<=r+1;i++){
			for(int j=0;j<=c+1;j++){
				if(map[i][j]!='a'){
					ans++;
				}
			}
		}
		return ans;
	}
	static void bfs(int x, int y){
		Queue<int[]> q= new LinkedList<>();
		q.add(new int[]{x,y});

		while(!q.isEmpty()){
			int[] cur= q.poll();
			int curX= cur[0];
			int curY= cur[1];

			for(int i=0;i<4;i++){
				int nx=curX+dx[i];
				int ny=curY+dy[i];

				if(map[nx][ny]=='a' && visited[nx][ny][1] && !visited[nx][ny][0]){
					visited[nx][ny][0]=true;
					q.add(new int[]{nx,ny});
				}
			}
		}
	}

	static boolean check(int x,int y){
		for(int i=0;i<4;i++){
			int nx= x+dx[i];
			int ny= y+dy[i];

			if(visited[nx][ny][0]){
				return true;
			}
		}
		return false;
	}*/

	class Solution {
		private static final int[] dx = {0, 0, 1, -1};
		private static final int[] dy = {1, -1, 0, 0};

		public int solution(String[] storage, String[] requests) {
			char[][] containers = new char[storage.length][];

			for (int i = 0; i < storage.length; i++) {
				containers[i] = storage[i].toCharArray();
			}

			for (String request : requests) {
				char c = request.charAt(0);

				if (request.length() == 1) {
					removeWithForklift(containers, c);
				} else {
					removeWithCrane(containers, c);
				}

			}

			int count = 0;

			for (char[] container : containers) {
				for (char c : container) {
					if (c != 0) {
						count++;
					}
				}
			}
			return count;
		}

		private void removeWithCrane(char[][] containers, char c) {
			for (int i = 0; i < containers.length; i++) {
				for (int j = 0; j < containers[i].length; j++) {
					if (containers[i][j] == c) {
						containers[i][j] = 0;
					}
				}
			}
		}

		private void removeWithForklift(char[][] containers, char c) {
			boolean[][] isVisited = new boolean[containers.length][containers[0].length];

			for (int i = 0; i < containers.length; i++) {
				for (int j = 0; j < containers[i].length; j++) {
					if ((i == 0 || j == 0 || i == containers.length - 1 || j == containers[i].length - 1)
						&& !isVisited[i][j]) {
						dfs(containers, i, j, c, isVisited);
					}
				}
			}

			for (int i = 0; i < containers.length; i++) {
				for (int j = 0; j < containers[i].length; j++) {
					if (containers[i][j] == 1) {
						containers[i][j] = 0;
					}
				}
			}
		}

		private void dfs(char[][] containers, int x, int y, char c, boolean[][] isVisited) {
			if (x < 0 || y < 0 || x >= containers.length || y >= containers[x].length
				|| isVisited[x][y]) {
				return;
			}

			isVisited[x][y] = true;

			if (containers[x][y] == c) {
				containers[x][y] = 1;
				return;
			}

			if(containers[x][y] != 0) return;

			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				dfs(containers, nx, ny, c, isVisited);
			}
		}
	}
}