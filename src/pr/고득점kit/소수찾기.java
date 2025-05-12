package pr.고득점kit;
import java.util.*;

public class 소수찾기 {

	class Solution {
		static Set<Integer> set= new HashSet<>();
		static boolean[] visited;
		static int ans=0;

		public int solution(String numbers) {
			char[] arr= numbers.toCharArray();
			visited= new boolean[7];
			dfs(arr,new StringBuilder());
			return ans;
		}

		static void dfs(char[] arr, StringBuilder sb){
			if(sb.length()>0){
				int num= Integer.parseInt(sb.toString());
				if(!set.contains(num)&&isPrime(num)){
					set.add(num);
					ans++;
				}
			}

			for(int i=0;i<arr.length;i++){
				if(!visited[i]){
					visited[i]=true;
					sb.append(arr[i]);
					dfs(arr,sb);
					sb.deleteCharAt(sb.length()-1);
					visited[i]=false;
				}
			}
		}
		static boolean isPrime(int n) {
			if (n <= 1) return false;
			for (int i = 2; i <= Math.sqrt(n); i++) {
				if (n % i == 0) return false;
			}
			return true;
		}
	}
}
