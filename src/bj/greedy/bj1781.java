package bj.greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class bj1781 {

	static class Node implements Comparable<Node>{
		int deadLine;
		int ramen;

		public Node(int deadLine, int ramen) {
			this.deadLine = deadLine;
			this.ramen = ramen;
		}

		@Override
		public int compareTo(Node o){
			// 데드라인 오름차순 정렬
			return this.deadLine - o.deadLine;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		Node[] tasks = new Node[N];

		for(int i = 0; i < N; i++){
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			tasks[i] = new Node(d, r);
		}

		// 데드라인 기준 오름차순 정렬
		Arrays.sort(tasks);

		// 라면 개수 기준 최소 힙
		PriorityQueue<Integer> pq = new PriorityQueue<>();

		for(Node task : tasks) {
			// 현재 작업 추가
			pq.add(task.ramen);

			// 현재 데드라인보다 많은 작업을 처리하고 있다면 라면 수가 가장 적은 작업 제거
			if(pq.size() > task.deadLine) {
				pq.poll();
			}
		}

		// 선택된 모든 작업의 라면 수 합산
		long sum = 0;
		while(!pq.isEmpty()) {
			sum += pq.poll();
		}

		System.out.print(sum);
	}
}
