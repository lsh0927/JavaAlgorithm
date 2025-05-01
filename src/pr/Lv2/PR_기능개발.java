package pr.Lv2;
import java.util.*;
public class PR_기능개발 {

	class Solution {
		public int[] solution(int[] progresses, int[] speeds) {
			Queue<Integer> queue= new LinkedList<>();

			for(int i=0;i<speeds.length;i++){
				int cnt=0;
				int sum=progresses[i];
				while(sum<100){
					sum+=speeds[i];
					cnt++;
				}
				queue.add(cnt);
			}

			int max=0;
			Map<Integer, Integer> map= new HashMap<>();
			while(!queue.isEmpty()){
				int num= queue.poll();
				if(max>num){
					num=max;
				}
				else{
					max=num;
				}

				map.put(num, map.getOrDefault(num,0)+1);
			}

			List<Integer> list= new ArrayList<>();
			List<Integer> keyList= new ArrayList<>(map.keySet());
			Collections.sort(keyList);
			for(int v: keyList){
				// System.out.println("key= "+v+" Value= "+map.get(v));
				list.add(map.get(v));
			}

			return list.stream().mapToInt(i -> i.intValue()).toArray();
		}
	}
}
