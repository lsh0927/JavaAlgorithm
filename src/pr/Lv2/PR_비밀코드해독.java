package pr.Lv2;
import java.util.*;

public class PR_비밀코드해독 {
	class Solution {

		static int[] arr;
		static int answer=0;
		public int solution(int n, int[][] q, int[] ans) {
			arr= new int[5];

			dfs(arr,n,q,ans,0,1);
			return answer;
		}
		static void dfs(int[] arr, int n, int[][]q, int[] ans, int idx,int start){
			if(idx==5){
				//m번의 시도가 전부 맞다면 조합 개수 추가
				if(check(arr,ans,q)){
					answer++;
				}
				return;
			}

			for(int i=start;i<=n;i++){
				arr[idx]=i;
				dfs(arr,n,q,ans,idx+1,i+1);
			}
			// for(int i=1;i<=n-5;i++){
			//     arr[idx]=i;
			// }

		}
		static boolean check(int[] arr,int[] ans,int[][]q){
			for(int i=0;i<q.length;i++){
				int cnt=0;
				int[] tmp= q[i];

				for(int a=0;a<5;a++){
					for(int b=0;b<5;b++){
						if(arr[a]==tmp[b]){
							cnt++;
						}
					}
				}


				if(cnt!=ans[i]){
					return false;
				}
			}
			return true;
		}
	}
}
