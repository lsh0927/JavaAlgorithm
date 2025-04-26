package pr.Lv2;
import java.util.*;

public class PR_순위검색 {

	class Solution {
		static Map<String,ArrayList<Integer>> hashMap= new HashMap<>();
		static ArrayList<Integer> score= new ArrayList<>();

		public int[] solution(String[] info, String[] query) {
			int[] answer = {};

			for(String i: info){
				dfs(0,"",i.split(" "));
			}

			return answer;
		}
		static void dfs(int cnt, String query, String[] info){
			if(cnt==4){
				if(!hashMap.containsKey(query)){
					score= new ArrayList<>();
					score.add(Integer.parseInt(info[4]));
					hashMap.put(query,score);
				}else{
					hashMap.get(query).add(Integer.parseInt(info[4]));
				}
				return;
			}
		}
	}
}
