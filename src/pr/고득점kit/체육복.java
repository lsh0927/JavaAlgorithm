package pr.고득점kit;
import java.util.*;
import java.util.stream.Collectors;
public class 체육복 {


	class Solution {
		public int solution(int n, int[] lost, int[] reserve) {
			Arrays.sort(lost);
			Arrays.sort(reserve);

			Set<Integer> owns= Arrays.stream(lost)
				.boxed()
				.collect(Collectors.toSet());

			owns.retainAll(Arrays.stream(reserve)
				.boxed()
				.collect(Collectors.toSet()));

			//이 코드에서는 stream을 이용해서 배열 lost와 reserve를 Set으로 변환하고,
			// Set 인터페이스의 retainAll() 메서드로 두 Set의 교집합을 구함.

			Queue<Integer> q= new LinkedList<>();
			for (int l : lost) q.add(l);

			int get=0;
			for (int r: reserve){
				if (owns.contains(r))
					continue;

				while (!q.isEmpty() && (q.peek() < r-1 || owns.contains(q.peek()))){
					q.poll();
				}
				if (q.isEmpty()) break;

				if (q.peek() <=r+1){
					q.poll();
					get++;
				}
			}
			return n - lost.length + owns.size() + get;


		}
	}
}
