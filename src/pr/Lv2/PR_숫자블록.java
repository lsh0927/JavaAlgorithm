package pr.Lv2;
import java.util.*;

public class PR_숫자블록 {
	class Solution {
		public int[] solution(long begin, long end) {
			int st= (int) begin;
			int en= (int) end;

			int[] answer= new int[en-st+1];
			Arrays.fill(answer,1);

			for(int i=st;i<=en;i++){
				answer[i-st]=cal(i);
			}
			if(st==1)
				answer[0]=0;

			return answer;
		}
		static int cal(int input){
			int max=1;
			for(int i=2;i*i<=input;i++){
				if(input%i==0){
					max=i;
					if(input / i <= 10000000)
						return input/i;
				}
			}
			return max;
		}
	}
}
