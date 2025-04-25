package bj.graph;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

//https://www.youtube.com/watch?v=mDSQfb5Rqc4
public class bj1707 {


	static List<List<Integer>> list;
	static int[] colors;
	static final int RED=1;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int tc= Integer.parseInt(br.readLine());

		for (int i=0;i<tc;i++){

			StringTokenizer st= new StringTokenizer(br.readLine());

			int V=Integer.parseInt(st.nextToken());
			int E=Integer.parseInt(st.nextToken());
			colors = new int[V+1];

			list= new ArrayList<>();

			for (int a=0;a<=V;a++){
				list.add(new ArrayList<>());
			}

			for (int b=0;b<E;b++){
				st= new StringTokenizer(br.readLine());
				int s= Integer.parseInt(st.nextToken());
				int e= Integer.parseInt(st.nextToken());

				list.get(s).add(e);
				list.get(e).add(s);
			}

			boolean rst= false;

			for (int vertex=1; vertex<=V;vertex++){
				if (colors[vertex]==0){
					rst= bfs(vertex,RED);
				}
				if (!rst) break;
			}

			if (rst) System.out.println("YES");
			else System.out.println("NO");

		}
	}
	private static boolean bfs(int start, int color){
		Queue<Integer> queue= new LinkedList<>();
		queue.add(start);

		//2. 시작 정점 임의의 색상으로 색칠
		colors[start]=color;

		while (!queue.isEmpty()){
			int cur= queue.poll();

			for (int next : list.get(cur)) {
				//4. 인접 정점 색이 동일하면 이분 그래프가 아님
				if (colors[cur]==colors[next])
					return false;
				//3. 인접 정점 색칠이 안된 경우 현재 정점 반대 색깔로 색칠
				// 색상 배열을 통해 방문 여부 확인이 가능

				if (colors[next]==0){
					colors[next]= colors[cur] * -1;
					queue.add(next);
				}

			}

		}
		return true;
	}
}