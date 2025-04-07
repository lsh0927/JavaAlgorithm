package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj1507 {
	static int N,M;
	static int[][] dist;
	static int[][] arr;
	static boolean[][] check;
	static final int INF = 987654321;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N=Integer.parseInt(st.nextToken());
		dist= new int[N][N];
		arr= new int[N][N];
		check= new boolean[N][N];

		for(int i=0;i<N;i++){
			st= new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++){
				dist[i][j]=Integer.parseInt(st.nextToken());
				arr[i][j] = dist[i][j];
			}
		}

		//주어진 경로를 이용해, 도로개수가 최소일때 모든 도로의 시간의 합을 구하는 문제
		//플로이드 와샬
		//모든 정점에 대해 모든 간선을 이용해 갱신이 일어나는지 확인
		for(int k=0;k<N;k++){
			for(int i=0;i<N;i++){
				for(int j=0;j<N;j++){
					// i == k 와 k == j인 경우를 거르지 않으면
					// 거쳐가지 않는 간선도 모조리 없애게 될 수 있음.
					// 예를 들어 i = 1, k = 1, j = 2일 경우
					// 1에서 2로 가기 위해 거쳐가는 정점이 없는데
					// 1 -> 2 간선을 없애게 될 수 있음.
					if (i == j || i == k || k == j) {
						continue;
					}

					// dist는 플로이드 와샬 알고리즘을 이미 수행한 상태인데
					// 또 최단거리를 초기화할 부분이 생기면 모순.
					if (dist[i][j] > dist[i][k] + dist[k][j]) {
						System.out.println(-1);
						return;
					}

					// 거쳐가는 지점을 통해서 최단거리가 초기화된 부분이 있다면
					// i -> j 간선을 없앰.
					if (dist[i][j] == dist[i][k] + dist[k][j]) {
						arr[i][j] = INF;
					}
				}
			}
		}

		int ans=0;
		for(int i=0;i<N;i++){
			for(int j=0;j<N;j++){
				if(arr[i][j]!=INF && i!=j && !check[i][j]){
					ans+=arr[i][j];
					check[i][j]=true;
					check[j][i]=true;
				}
			}
		}

		System.out.println(ans);
	}
}
