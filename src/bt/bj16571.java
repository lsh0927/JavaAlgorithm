package bt;

//미니맥스 알고리즘

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj16571 {

	static int[][] board = new int[3][3];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for(int i = 0; i < 3; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 3; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int xCount = 0;
		int oCount = 0;

		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(board[i][j] == 1) xCount++;
				else if(board[i][j] == 2) oCount++;
			}
		}

		// 게임이 이미 종료되었는지 체크
		if(isWinner(1) || isWinner(2) || xCount + oCount == 9) {
			System.out.println("D");
			return;
		}

		// 다음 플레이어 결정 (X가 먼저 시작)
		int currentPlayer = (xCount > oCount) ? 2 : 1;

		int result = getOptimalPlay(currentPlayer);

		if(result == 1) {
			System.out.println("W"); // 이길 수 있음
		} else if(result == 0) {
			System.out.println("D"); // 비길 수 있음
		} else {
			System.out.println("L"); // 질 수밖에 없음
		}
	}

	// 최적의 결과 계산: 1(승리), 0(무승부), -1(패배)
	static int getOptimalPlay(int player) {
		// 상대방이 이미 이겼는지 체크
		int opponent = (player == 1) ? 2 : 1;
		if(isWinner(opponent)) {
			return -1;
		}

		// 빈칸이 없는지 체크 (무승부)
		boolean isFull = true;
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(board[i][j] == 0) {
					isFull = false;
					break;
				}
			}
			if(!isFull) break;
		}

		if(isFull) {
			return 0;
		}

		// 최적의 결과 찾기
		int bestResult = -1; // 디폴트는 '질 것'으로 설정

		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(board[i][j] == 0) {
					// 수를 놓아봄
					board[i][j] = player;

					// 이 수로 이기는지 체크
					if(isWinner(player)) {
						board[i][j] = 0; // 되돌리기
						return 1; // 이기는 수를 찾았으므로 바로 리턴
					}

					// 상대방의 최적 플레이 결과
					int nextResult = -getOptimalPlay(opponent);

					// 결과를 업데이트 (최선의 결과 선택)
					bestResult = Math.max(bestResult, nextResult);

					// 되돌리기
					board[i][j] = 0;

					// 이기는 수를 찾았다면 더 탐색할 필요 없음
					if(bestResult == 1) {
						return 1;
					}
				}
			}
		}

		return bestResult;
	}

	// 주어진 플레이어가 이겼는지 확인
	static boolean isWinner(int player) {
		// 가로 체크
		for(int i = 0; i < 3; i++) {
			if(board[i][0] == player && board[i][1] == player && board[i][2] == player) {
				return true;
			}
		}

		// 세로 체크
		for(int i = 0; i < 3; i++) {
			if(board[0][i] == player && board[1][i] == player && board[2][i] == player) {
				return true;
			}
		}

		// 대각선 체크
		if(board[0][0] == player && board[1][1] == player && board[2][2] == player) {
			return true;
		}

		if(board[0][2] == player && board[1][1] == player && board[2][0] == player) {
			return true;
		}

		return false;
	}
}