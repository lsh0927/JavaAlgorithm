package pr.고득점kit;
import java.util.*;

public class 카펫 {

	/*class Solution {
		static int total;
		static int r,c;
		public int[] solution(int brown, int yellow) {

			total=brown+yellow;
			//가로는 세로보다 같거나 김

			dfs(brown,yellow,1,1);
			return new int[]{r,c};

		}
		static void dfs(int brown, int yellow, int row, int col){

			if(col>row){
				return;
			}
			if(row*col>total){
				return;
			}

			if(check(brown,row,col)){
				r=row;
				c=col;
				return;
			}


			dfs(brown,yellow,row+1,col);
			dfs(brown,yellow,row,col+1);

		}
		static boolean check(int brown,int row, int col){
			if(row*col==total && 2*(row+col)-4==brown){
				return true;
			}
			return false;
		}
	}*/
	class Solution {
		public int[] solution(int brown, int yellow) {
			int total = brown + yellow;

			// 가능한 높이 값 탐색
			for (int height = 3; height <= Math.sqrt(total); height++) {
				if (total % height == 0) {
					int width = total / height;

					// 갈색 테두리 조건 확인
					if (2 * (width + height) - 4 == brown) {
						return new int[]{width, height};
					}
				}
			}

			return new int[]{0, 0}; // 유효한 입력에서는 여기에 도달하지 않음
		}
	}
}
