package pr.고득점kit;
import java.util.*;

public class 가장큰수 {

	class Solution {
		public String solution(int[] numbers) {

			List<String> list= new ArrayList<>();

			for(int number:numbers){
				list.add(String.valueOf(number));
			}

			// Collections.sort(list, (o1, o2) -> {
			// 	String st1= o1+o2;
			// 	String st2= o2+o1;
			//
			// 	return st2.compareTo(st1);
			// });
			Collections.sort(list, (o1,o2)-> (o2+o1).compareTo(o1+o2));

			String answer="";
			for(String str: list){
				answer+=str;
			}

			if(answer.startsWith("0")) answer="0";
			return answer;
		}
	}
}
