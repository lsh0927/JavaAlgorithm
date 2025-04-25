import java.io.*;
import java.util.*;

public class Main {

	static int N,K,W;
	static int[] D;
	static List<List<Integer>> list;
	static int[] degree;
	static int max;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T= Integer.parseInt(br.readLine());
		for(int i=0;i<T;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N=Integer.parseInt(st.nextToken());
			K=Integer.parseInt(st.nextToken());

			D= new int[N+1];
			degree= new int[N+1];
			st = new StringTokenizer(br.readLine());
			for (int j=1;j<=N;j++){
				D[j]=Integer.parseInt(st.nextToken());
			}

			list= new ArrayList<>();
			for (int j=0;j<=N;j++){
				list.add(new ArrayList<>());
			}

			for (int j=0;j<K;j++) {
				st = new StringTokenizer(br.readLine());
				int before= Integer.parseInt(st.nextToken());
				int after= Integer.parseInt(st.nextToken());
				degree[after]++;
				list.get(before).add(after);
			}

			W= Integer.parseInt(br.readLine());
			max=-1;
			cal();
			System.out.println(max);
		}
	}
	static void cal() {
		Queue<int[]> q = new LinkedList<>();
		for (int i = 1; i <= N; i++) {
			if (degree[i] == 0) {
				q.add(new int[]{i, D[i]});
			}
		}

		//초기화
		int[] result= new int[N+1];
		for (int i=1;i<=N;i++){
			result[i]=D[i];
		}


		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int n = cur[0];
			int time = cur[1];

			if (n == W) {
				max = Math.max(max, time);
			}

			for (Integer next : list.get(n)) {
				degree[next]--;

				int newTime=time+D[next];
				result[next]= Math.max(newTime,result[next]);

				// 진입 차수가 0이 된 경우에만 큐에 추가
				if (degree[next] == 0) {
					q.add(new int[]{next, result[next]});
				}
			}
		}
	}
}