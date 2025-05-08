package pr.고득점kit;
import java.util.*;

public class 디스크컨트롤러 {

	class Solution {
		public int solution(int[][] jobs) {

			int answer=0;
			int end=0;
			int idx=0;
			int count=0;

			Arrays.sort(jobs, Comparator.comparingInt(o -> o[0]));

			PriorityQueue<int[]> pq= new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));

			while(count<jobs.length){
				while(idx<jobs.length && jobs[idx][0]<=end){
					pq.add(jobs[idx++]);
				}

				if(pq.isEmpty()){
					end=jobs[idx][0];
				}else{
					int[] cur= pq.poll();
					answer+=cur[1]+end-cur[0];
					end+=cur[1];
					count++;
				}
			}

			return answer/jobs.length;

		}
	}
}
