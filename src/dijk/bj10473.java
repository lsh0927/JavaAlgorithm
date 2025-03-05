package dijk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class bj10473 {
	// 다익스트라 알고리즘에서 PQ에 넣을 노드 – 해당 정점의 인덱스와 현재까지 걸린 시간을 저장합니다.
	static class Node implements Comparable<Node> {
		int idx;        // points 배열에서의 인덱스 (0: 출발지, 1~n: 대포, n+1: 도착지)
		double cost;    // 시작점으로부터 해당 정점까지의 최소 시간

		public Node(int idx, double cost) {
			this.idx = idx;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node other) {
			return Double.compare(this.cost, other.cost);
		}
	}

	// 시작지점 좌표
	static double curX, curY;
	// 도착지점 좌표
	static double dstX, dstY;
	// 대포 개수
	static int n;
	// 대포 좌표 리스트 (각 원소: double[]{x, y})
	static List<double[]> cannon;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 입력: 출발지와 도착지 좌표
		curX = Double.parseDouble(st.nextToken());
		curY = Double.parseDouble(st.nextToken());

		st = new StringTokenizer(br.readLine());
		dstX = Double.parseDouble(st.nextToken());
		dstY = Double.parseDouble(st.nextToken());

		// 대포 개수 입력
		n = Integer.parseInt(br.readLine());
		cannon = new ArrayList<>();

		// 대포 좌표 입력
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			double x = Double.parseDouble(st.nextToken());
			double y = Double.parseDouble(st.nextToken());
			cannon.add(new double[]{x, y});
		}

		// 총 정점 수: 0 - 출발지, 1~n - 대포, n+1 - 도착지
		int total = n + 2;
		// 모든 좌표를 저장하는 배열 (index 0: 출발지, index 1~n: 대포, index n+1: 도착지)
		double[][] points = new double[total][2];
		points[0][0] = curX;
		points[0][1] = curY;
		for (int i = 1; i <= n; i++) {
			// cannon 리스트의 i-1번 원소
			points[i] = cannon.get(i - 1);
		}
		points[n+1][0] = dstX;
		points[n+1][1] = dstY;

		// 다익스트라 알고리즘을 위한 최소 시간 배열과 방문 배열 초기화
		double[] times = new double[total];
		Arrays.fill(times, Double.MAX_VALUE);
		times[0] = 0;
		boolean[] visited = new boolean[total];

		// PQ 초기화 (출발지부터 시작)
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(0, 0));

		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			int curIdx = cur.idx;

			if (visited[curIdx])
				continue;
			visited[curIdx] = true;

			// 모든 다른 정점에 대해 relax 진행
			for (int nextIdx = 0; nextIdx < total; nextIdx++) {

				// 두 점 사이의 유클리드 거리 계산
				double dx = points[curIdx][0] - points[nextIdx][0];
				double dy = points[curIdx][1] - points[nextIdx][1];
				double distance = Math.sqrt(dx * dx + dy * dy);

				double time;
				if (curIdx == 0) {
					// 출발지에서는 무조건 달려야 함.
					time = distance / 5.0;
				} else {
					// 대포에서는 달리기와 대포 이용 중 더 빠른 방법 선택
					// 대포 발사시: 2초 + (대포 사정거리 50m와 실제 거리 차이의 절대값을 5로 나눈 값)
					time = Math.min(distance / 5.0, Math.abs(distance - 50) / 5.0 + 2);
				}

				// 왜 각 대포 노드를 거쳐야만 이동할 수 있는가...
				if (times[nextIdx] > times[curIdx] + time) {
					times[nextIdx] = times[curIdx] + time;
					pq.add(new Node(nextIdx, times[nextIdx]));
				}
			}
		}

		// 도착지 (index total-1)까지의 최소 시간 출력
		System.out.println(times[total - 1]);
	}
}
