package pr.고득점kit;
import java.util.*;

public class 더맵게 {

	class Solution {
		public int solution(int[] scoville, int K) {

			PriorityQueue<Integer> pq= new PriorityQueue<>();
			for(int v: scoville){
				pq.add(v);
			}

			int cnt=0;
			while(!pq.isEmpty()){
				if(pq.peek()>=K){
					return cnt;
				}

				int cur= pq.poll();
				if(!pq.isEmpty()){
					int next= pq.poll();
					pq.add(cur+2*next);
					cnt++;
				}


			}


			return -1;
		}
	}
}
