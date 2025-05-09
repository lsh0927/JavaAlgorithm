package pr.고득점kit;
import java.util.*;

public class 이중우선순위큐 {


	class Solution {
		public int[] solution(String[] operations) {
			TreeMap<Integer, Integer> map = new TreeMap<>();

			for (String operation : operations) {
				String[] parts = operation.split(" ");
				String cmd = parts[0];
				int num = Integer.parseInt(parts[1]);

				if (cmd.equals("I")) {
					// 삽입 연산
					map.put(num, map.getOrDefault(num, 0) + 1);
				} else if (!map.isEmpty()) {
					// 삭제 연산
					int key = num == 1 ? map.lastKey() : map.firstKey();
					int count = map.get(key);

					if (count == 1) {
						map.remove(key);
					} else {
						map.put(key, count - 1);
					}
				}
			}

			if (map.isEmpty()) {
				return new int[]{0, 0};
			} else {
				return new int[]{map.lastKey(), map.firstKey()};
			}
		}
	}

	// class Solution {
	// 	public int[] solution(String[] operations) {
	// 		PriorityQueue<Integer> minpq = new PriorityQueue<>();
	// 		PriorityQueue<Integer> maxpq = new PriorityQueue<>(Collections.reverseOrder());
	//
	// 		for (String operation : operations) {
	// 			if (operation.startsWith("I ")) {
	// 				int n = Integer.parseInt(operation.substring(2));
	// 				minpq.offer(n);
	// 				maxpq.offer(n);
	// 			} else if (!minpq.isEmpty() && operation.equals("D -1")) {
	// 				maxpq.remove(minpq.poll());
	// 			} else if (!maxpq.isEmpty() && operation.equals("D 1")) {
	// 				minpq.remove(maxpq.poll());
	// 			}
	// 		}
	//
	// 		if (minpq.isEmpty() && maxpq.isEmpty()) {
	// 			return new int[]{0, 0};
	// 		}
	//
	// 		return new int[]{maxpq.poll(), minpq.poll()};
	// 	}
	// }
}
