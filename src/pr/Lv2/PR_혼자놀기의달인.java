package pr.Lv2;
import java.util.*;

public class PR_혼자놀기의달인 {

	// class Solution {
	// 	static boolean[] visited;
	// 	static int[] card;
	// 	static List<Integer> list;
	// 	static int count=0;
	// 	public int solution(int[] cards) {
	// 		visited= new boolean[cards.length+1];
	// 		card=new int[cards.length+1];
	// 		list= new ArrayList<>();
	//
	// 		for(int i=0;i<cards.length;i++){
	// 			card[i+1]=cards[i];
	// 		}
	//
	// 		for(int i=1;i<=cards.length;i++){
	// 			if(!visited[i]){
	// 				count++;
	// 				list.add(dfs(i,1));
	// 			}
	// 		}
	//
	// 		if(count==1){
	// 			return 0;
	// 		}
	// 		else{
	// 			int ans=1;
	// 			for(int n: list){
	// 				ans*=n;
	// 			}
	// 			return ans;
	// 		}
	// 	}
	// 	static int dfs(int idx,int cnt){
	// 		visited[idx]=true;
	//
	// 		if(!visited[card[idx]]){
	// 			return dfs(card[idx],cnt+1);
	// 		}
	//
	// 		return cnt;
	// 	}
	// }

	class Solution {
		static boolean[] visited;
		static int[] card;
		static List<Integer> list;
		static int count=0;
		public int solution(int[] cards) {
			visited= new boolean[cards.length+1];
			card=new int[cards.length+1];
			list= new ArrayList<>();

			for(int i=0;i<cards.length;i++){
				card[i+1]=cards[i];
			}

			for(int i=1;i<=cards.length;i++){
				if(!visited[i]){
					count++;
					list.add(dfs(i,1));
				}
			}



			if(count==1){
				return 0;
			}
			else{
				Collections.sort(list, Comparator.reverseOrder());
				int ans=1;
				for(int i=0;i<2;i++){
					ans*=list.get(i);
				}
				return ans;
			}
		}
		static int dfs(int idx,int cnt){
			visited[idx]=true;

			if(!visited[card[idx]]){
				return dfs(card[idx],cnt+1);
			}

			return cnt;
		}
	}
}
