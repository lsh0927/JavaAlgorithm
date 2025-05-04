package pr.고득점kit;
import java.util.*;

public class 주식가격 {

	class Solution {
		public int[] solution(int[] prices) {

			int[] arr= new int[prices.length];
			Deque<Integer> stack= new ArrayDeque<>();

			for(int i=0;i<arr.length;i++){
				while(!stack.isEmpty() && prices[stack.peek()]>prices[i]){
					int idx= stack.pop();
					arr[idx]=i-idx;
				}
				stack.push(i);
			}
			while(!stack.isEmpty()){
				int index= stack.pop();
				arr[index]=prices.length-1-index;
			}

			return arr;
		}
	}
}
