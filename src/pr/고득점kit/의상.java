package pr.고득점kit;
import java.util.*;

public class 의상 {
	class Solution {
		public int solution(String[][] clothes) {
			int answer=1;
			HashMap<String,Integer> map= new HashMap<>();

			for(String[] cloth: clothes){
				map.put(cloth[1],map.getOrDefault(cloth[1],0)+1);
			}

			// 반복문을 활용하여 map의 KeySet 순회
			for (String s : map.keySet()) {
				// 착용하지 않을 경우 +1
				answer *= (map.get(s) + 1);
			}
			// 모두 착용하지 않는 것은 불가능
			return answer-1;
		}
	}
}
