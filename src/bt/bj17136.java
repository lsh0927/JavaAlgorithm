package bt;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj17136 {
	static int[] paperCnt = new int[6]; // 1x1 ~ 5x5 색종이 개수
	static int[][] map = new int[10][10];
	static int minAnswer = Integer.MAX_VALUE; // 최소 사용 색종이 개수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 맵 입력
		for(int i = 0; i < 10; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 10; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 색종이 개수 초기화
		for(int i = 1; i <= 5; i++) {
			paperCnt[i] = 5;
		}

		dfs(0, 0, 0);

		// 결과 출력
		System.out.println(minAnswer == Integer.MAX_VALUE ? -1 : minAnswer);
	}

	static void dfs(int row, int col, int usedPapers) {
		// 이미 찾은 최소값보다 많은 색종이를 사용했다면 가지치기
		if(usedPapers >= minAnswer) {
			return;
		}

		// 맨 끝에 도달하면 최소값 갱신
		if(row >= 10) {
			minAnswer = Math.min(minAnswer, usedPapers);
			return;
		}

		// 현재 행을 다 확인했으면 다음 행으로
		if(col >= 10) {
			dfs(row + 1, 0, usedPapers);
			return;
		}

		// 현재 위치가 0이면 다음 위치로 이동
		if(map[row][col] == 0) {
			dfs(row, col + 1, usedPapers);
			return;
		}

		// 현재 위치에 붙일 수 있는 색종이 크기 확인
		int maxSize = check(row, col);

		// 가능한 모든 크기의 색종이 시도
		for(int size = 1; size <= maxSize; size++) {
			// 해당 크기의 색종이가 남아있는지 확인
			if(paperCnt[size] > 0) {
				// 색종이 붙이기
				paperCnt[size]--;

				// 맵에 표시
				for(int i = 0; i < size; i++) {
					for(int j = 0; j < size; j++) {
						map[row + i][col + j] = 0;
					}
				}

				// 다음 위치 탐색
				dfs(row, col + 1, usedPapers + 1);

				// 색종이 떼기 (백트래킹)
				paperCnt[size]++;

				// 맵 원상복구
				for(int i = 0; i < size; i++) {
					for(int j = 0; j < size; j++) {
						map[row + i][col + j] = 1;
					}
				}
			}
		}
	}

	static int check(int row, int col) {
		int maxSize = 5; // 최대 색종이 크기

		// 배열 범위 초과하지 않도록 조정
		maxSize = Math.min(maxSize, 10 - row);
		maxSize = Math.min(maxSize, 10 - col);

		// 가능한 최대 크기 확인
		for(int size = 1; size <= maxSize; size++) {
			boolean possible = true;

			for(int i = 0; i < size; i++) {
				for(int j = 0; j < size; j++) {
					if(map[row + i][col + j] != 1) {
						possible = false;
						break;
					}
				}
				if(!possible) break;
			}

			if(!possible) {
				return size - 1;
			}
		}

		return maxSize;
	}
}

//
// public class bj17136 {
//
// 	static int[] cnt;
// 	static int[][] map;
// 	public static void main(String[] args) throws IOException {
// 		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
// 		StringTokenizer st;
//
// 		map= new int[10][10];
// 		for(int i=0;i<10;i++){
// 			st= new StringTokenizer(br.readLine());
// 			for(int j=0;j<10;j++){
// 				map[i][j]=Integer.parseInt(st.nextToken());
// 			}
// 		}
//
// 		//1x1 ~ 5x5 cnt
// 		cnt= new int[6];
// 		for(int i=1;i<6;i++){
// 			cnt[i]=5;
// 		}
//
// 		dfs(0,0,0);
//
// 	}
// 	static void dfs(int row, int col, int ans){
// 		if(row==10){
// 			System.out.println(ans);
// 			return;
// 		}
//
// 		if(col==10){
// 			dfs(row+1,0,ans);
// 			return;
// 		}
// 		if(map[row][col] == 0) {
// 			dfs(row, col + 1, ans);
// 			return;
// 		}
//
// 		int len;
// 		if(map[row][col]==1){
// 			//정사각형을 만들수 있는 최대 길이
// 			len= check(row,col);
//
// 			//큰 것부터 개수가 부족하면 아래로
// 			while(cnt[len]==0){
// 				len--;
// 				//근데 더 없으면 -1리턴
// 				if(len==0) {
// 					System.out.print(-1);
// 					System.exit(0);
// 				}
// 			}
//
//
// 			for(int i=0;i<len;i++){
// 				for(int j=0;j<len;j++){
// 					map[row+i][col+j]=0;
// 				}
// 			}
// 			cnt[len]--;
//
// 			dfs(row,col+1,ans+1);
// 			for(int i=0;i<len;i++){
// 				for(int j=0;j<len;j++){
// 					map[row+i][col+j]=1;
// 				}
// 			}
// 		}
// 	}
// 	static int check(int row, int col) {
// 		int n =10;
// 		int m =10;
// 		int maxSize = 0;
// 		// 배열 범위 내에서 가능한 최대 크기 계산
// 		int possibleMaxSize = Math.min(n - row, m - col);
//
// 		for (int size = 1; size <= possibleMaxSize; size++) {
// 			boolean isSquare = true;
//
// 			// 정사각형 내부의 모든 칸 확인
// 			for (int i = 0; i < size; i++) {
// 				for (int j = 0; j < size; j++) {
// 					if (map[row + i][col + j] != 1) {
// 						isSquare = false;
// 						break;
// 					}
// 				}
// 				if (!isSquare) break;
// 			}
// 			if (isSquare) {
// 				maxSize = size;
// 			} else {
// 				break;
// 			}
// 		}
// 		return maxSize;
// 	}
// }