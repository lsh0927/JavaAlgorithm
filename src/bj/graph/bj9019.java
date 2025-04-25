package bj.graph;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj9019 {

	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		for (int i = 0; i < n; i++) {
			visited = new boolean[10000];
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			bfs(start, end);
		}
	}

	static void bfs(int start, int end) {
		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(start, ""));

		while (!queue.isEmpty()) {
			Node cur = queue.poll();

			if (cur.num == end) {
				System.out.println(cur.str);
				return;
			}

			int num = cur.num;

			if (visited[num]) continue;
			visited[num] = true;

			// D 연산
			int nextNum = (num * 2) % 10000;
			queue.add(new Node(nextNum, cur.str + "D"));

			// S 연산
			if (num == 0) {
				nextNum = 9999;
			} else {
				nextNum = num - 1;
			}
			queue.add(new Node(nextNum, cur.str + "S"));

			// L 연산
			//(num % 1000)*10 + (num / 1000)
			nextNum = (num % 1000) * 10 + (num / 1000);
			queue.add(new Node(nextNum, cur.str + "L"));

			// R 연산
			//(num % 10)*1000 + (num / 10)
			nextNum = (num % 10) * 1000 + (num / 10);
			queue.add(new Node(nextNum, cur.str + "R"));
		}
	}

	static class Node {
		int num;
		String str;

		public Node(int num, String str) {
			this.num = num;
			this.str = str;
		}
	}
}
