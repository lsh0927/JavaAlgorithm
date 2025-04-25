package pr;

import java.util.*;
public class PR_비밀코드해독 {
	static int answer=0;
	public int solution(int n, int[][] q, int[] ans) {
		dfs(1,0,n,q,ans,new ArrayList<>());

		return answer;
	}
	static void dfs(int idx,int cnt,int n, int[][]q, int[] ans, List<Integer> list){
		//종료조건
		if(cnt==5){
			//답이 일치하는지 확인
			for(int i=0;i<q.length;i++){
				int a= ans[i];
				int[] arr= q[i];

				int same=0;
				for(int j=0;j<5;j++){
					if(list.contains(arr[j])){
						same++;
					}
				}
				if(same!=a){
					return;
				}
			}
			answer++;
		}

		for(int i=idx;i<=n;i++){
			list.add(i);
			dfs(i+1,cnt+1, n, q, ans, list);
			list.remove(list.size()-1);
		}
	}
}