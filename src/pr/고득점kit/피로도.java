package pr.고득점kit;

public class 피로도 {
	class Solution {
		static int ans=0;
		static boolean[] visited;
		public int solution(int k, int[][] dungeons) {
			visited= new boolean[dungeons.length];
			dfs(0,k,dungeons);
			return ans;
		}
		static void dfs(int cnt, int k, int[][] dungeons){

			ans=Math.max(ans,cnt);
			if(cnt==dungeons.length){
				return;
			}

			for(int i=0;i<dungeons.length;i++){
				if(!visited[i]&& k>=dungeons[i][0]){
					visited[i]=true;
					k-=dungeons[i][1];
					dfs(cnt+1,k,dungeons);
					k+=dungeons[i][1];
					visited[i]=false;
				}
			}

		}
	}
}
