package bt;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj6987 {
	static int[][] play = new int[6][3];	//경기 결과 저장 배열
	static int answer = 0;

	public static void main(String[] args) throws IOException {
		//입력값 처리하는 BufferedReader
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		//4개의 일정 탐색 진행
		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			answer = 0;
			//경기 정보 저장
			for (int j = 0; j < 18; j++)
				play[j / 3][j % 3] = Integer.parseInt(st.nextToken());
			boolean flag = false;
			//경기 수가 5가 아닌 국가가 있을 경우 올바른 결과 불가능
			for(int j=0;j<6;j++) {
				if(play[j][0] + play[j][1] + play[j][2] != 5) {
					flag = true;
					break;
				}
			}
			if(!flag)	//모든 국가의 경기수가 5일 때 백트래킹 탐색 진행
				search(0, 1);
			sb.append(answer).append(" ");
		}
		System.out.print(sb);	//결과 출력
	}

	//국가별 경기를 경우를 탐색하는 백트래킹 함수
	private static void search(int idx, int nxt) {
		if (answer == 1)	//해당 결과가 올바른 것일 때 재귀 종료!
			return;
		//F국가까지 올바르게 경기가 진행되었을 때
		if (idx == 5) {
			answer = 1;
			return;
		}
		//현재 국가가 상대 국가에게 이겼을 때
		if (play[idx][0] > 0 && play[nxt][2] > 0) {
			play[idx][0]--;
			play[nxt][2]--;
			if (nxt == 5) {	//현재 국가 탐색 완료
				search(idx + 1, idx + 2);
			}else
				search(idx, nxt + 1);
			play[idx][0]++;
			play[nxt][2]++;
		}
		//현재 국가와 상대 국가가 무승부 했을 때
		if (play[idx][1] > 0 && play[nxt][1] > 0) {
			play[idx][1]--;
			play[nxt][1]--;
			if (nxt == 5) {	//현재 국가 탐색 완료
				search(idx + 1, idx + 2);
			}else
				search(idx, nxt + 1);
			play[idx][1]++;
			play[nxt][1]++;
		}
		//현재 국가가 상대 국가에게 패배하였을 때
		if (play[idx][2] > 0 && play[nxt][0] > 0) {
			play[idx][2]--;
			play[nxt][0]--;
			if (nxt == 5) {	//현재 국가 탐색 완료
				search(idx + 1, idx + 2);
			}else
				search(idx, nxt + 1);
			play[idx][2]++;
			play[nxt][0]++;
		}

	}
}