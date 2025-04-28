package pr.Lv2;
import java.util.*;

public class PR_조이스틱 {
	class Solution {
		public int solution(String name) {
			int move=name.length()-1;
			int answer=0;
			for(int i=0;i<name.length();i++){
				//상하이동
				answer+= Math.min(name.charAt(i)-'A', 'Z'-name.charAt(i)+1);

				//죄우 이동
				int next= i+1;
				while(next<name.length() && name.charAt(next)=='A'){
					next++;
				}

				//오른쪽으로 가기 + 왼쪽으로 가기 (2번의 i번 이동 + len-next만큼 이동)
				move= Math.min(move, (2*i)+(name.length()-next));
			}

			answer+=move;
			return answer;
		}
	}
}
