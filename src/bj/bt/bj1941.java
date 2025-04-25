package bj.bt;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class bj1941 {

		static char[][] map = new char[5][5];
		static int answer = 0;

		public static void main(String[] args) throws Exception {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			// 입력 받기
			for (int i = 0; i < 5; i++) {
				String s = br.readLine();
				for (int j = 0; j < 5; j++) {
					map[i][j] = s.charAt(j);
				}
			}

			// 조합을 생성하여 해결
			int[] selected = new int[7];
			combination(0, 0, selected);

			System.out.println(answer);
		}

		// 7명의 학생을 선택하는 조합 생성
		static void combination(int index, int count, int[] selected) {
			if (count == 7) {
				// 이다솜파(S)가 4명 이상인지 확인
				int sCount = 0;
				for (int i = 0; i < 7; i++) {
					int x = selected[i] / 5;
					int y = selected[i] % 5;
					if (map[x][y] == 'S') {
						sCount++;
					}
				}

				// 이다솜파가 4명 이상이면, 인접 여부 확인
				if (sCount >= 4 && check(selected)) {
					answer++;
				}
				return;
			}

			// 25명 중 7명을 선택하는 조합
			for (int i = index; i < 25; i++) {
				selected[count] = i;
				combination(i + 1, count + 1, selected);
			}
		}

		// 선택된 7명이 모두 인접해 있는지 확인
		static boolean check(int[] selected) {
			boolean[] visited= new boolean[7];
			Queue<Integer> q= new LinkedList<>();
			q.add(0);
			visited[0]=true;
			int cnt=1;
			while(!q.isEmpty()){
				int cur= q.poll();
				int curX= selected[cur] / 5;
				int curY= selected[cur] % 5;

				for(int i=1; i<7;i++){
					if(!visited[i]){
						//왜 여기 visited가 들어가면 안되지?
						int tarX=selected[i]/5;
						int tarY=selected[i]%5;

						if(Math.abs(curX-tarX)+Math.abs(curY-tarY)==1){
							visited[i]=true;
							q.add(i);
							cnt++;
						}
					}
				}
			}
			return cnt==7;
		}
	}
	// static char[][] map;
	// static boolean[][] visited;
	// static int ans;
	// public static void main(String[] args) throws Exception {
	// 	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	//
	// 	map= new char[5][5];
	// 	visited= new boolean[5][5];
	//
	// 	for(int i=0;i<5;i++){
	// 		String s=br.readLine();
	// 		for(int j=0;j<5;j++){
	// 			map[i][j]=s.charAt(j);
	// 		}
	// 	}
	//
	// 	for(int i=0;i<5;i++){
	// 		for(int j=0;j<5;j++){
	// 			if(!visited[i][j]) {
	// 				visited[i][j]=true;
	// 				dfs(i, j, 0, 0, 0);
	// 			}
	// 		}
	// 	}
	//
	// 	System.out.println(ans);
	//
	// }
	// static void dfs(int r, int c, int len, int s, int y){
	// 	if(len==7) {
	// 		if (s > y) {
	// 			ans++;
	// 		}
	// 		return;
	// 	}
	//
	// 	if(r+1<5){
	// 		char t= map[r+1][c];
	// 		if(!visited[r+1][c]) {
	// 			visited[r + 1][c] = true;
	// 			if (t == 'S') {
	// 				dfs(r + 1, c, len + 1, s + 1, y);
	// 			} else {
	// 				dfs(r + 1, c, len + 1, s , y+1);
	// 			}
	// 			visited[r + 1][c] = false;
	// 		}
	// 	}
	// 	if(c+1<5){
	// 		if(!visited[r][c+1]) {
	// 			char t = map[r][c + 1];
	// 			visited[r][c + 1] = true;
	// 			if (t == 'S') {
	// 				dfs(r, c + 1, len + 1, s + 1, y);
	// 			} else {
	// 				dfs(r, c + 1, len + 1, s, y + 1);
	// 			}
	// 			visited[r][c + 1] = false;
	// 		}
	// 	}
	// 	if(r-1>=0){
	// 		if(!visited[r-1][c]) {
	// 			char t = map[r - 1][c];
	// 			visited[r - 1][c] = true;
	// 			if (t == 'S') {
	// 				dfs(r - 1, c, len + 1, s + 1, y);
	// 			} else {
	// 				dfs(r - 1, c, len + 1, s, y + 1);
	// 			}
	// 			visited[r - 1][c] = false;
	// 		}
	// 	}
	// 	if(c-1>=0){
	// 		if(!visited[r][c-1]) {
	// 			char t = map[r][c - 1];
	// 			visited[r][c - 1] = true;
	// 			if (t == 'S') {
	// 				dfs(r, c - 1, len + 1, s + 1, y);
	// 			} else {
	// 				dfs(r, c - 1, len + 1, s, y + 1);
	// 			}
	// 			visited[r][c - 1] = false;
	// 		}
	// 	}
	// }

