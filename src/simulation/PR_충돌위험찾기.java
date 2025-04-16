package simulation;

public class PR_충돌위험찾기 {
	class Solution {
		public int solution(int[][] points, int[][] routes) {
			int answer = 0;
			int[][] p = new int[routes.length][3];
			for (int i=0; i<routes.length; i++) {
				p[i][0] = points[routes[i][0]-1][0] - 1;
				p[i][1] = points[routes[i][0]-1][1] - 1;
				p[i][2] = 1;
			}
			int finish = 0;
			int time = 0;
			while (finish < routes.length) {
				int[][] visited = new int[100][100];
				for (int i=0; i<routes.length; i++) {
					if (p[i][2] < routes[0].length) {
						visited[p[i][0]][p[i][1]]++;
						if (visited[p[i][0]][p[i][1]]==2) {
							answer++;
						}
					}
				}

				for (int i=0; i<routes.length; i++) {
					if (p[i][2] < routes[0].length) {
						int ngr = points[routes[i][p[i][2]] - 1][0]-1;
						int ngc = points[routes[i][p[i][2]] - 1][1]-1;
						if (p[i][0]==ngr && p[i][1]==ngc) {
							p[i][2]++;
							if (p[i][2] == routes[0].length) {
								finish++;
								continue;
							}
							ngr = points[routes[i][p[i][2]] - 1][0]-1;
							ngc = points[routes[i][p[i][2]] - 1][1]-1;
						}

						if (ngr != p[i][0]) {
							p[i][0] += ngr>p[i][0] ? 1 : -1;
						} else {
							p[i][1] += ngc>p[i][1] ? 1 : -1;
						}
					}
				}
			}
			return answer;
		}
	}

}
