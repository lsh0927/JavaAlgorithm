package simulation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class PR_신고결과받기 {

	//진심 뭐하냐 하
	// static Set<String> visited;
	//
	// public int[] solution(String[] id_list, String[] report, int k) {
	// 	int[] answer= new int[id_list.length];
	//
	// 	Map<String,int[]> map= new HashMap<>();
	// 	visited= new HashSet<>();
	//
	// 	for(int i=0; i<id_list.length;i++){
	// 		int[] value= new int[3];
	// 		value[0]=i;
	// 		value[1]=0;
	// 		value[2]=0;
	// 		map.put(id_list[i],value);
	// 	}
	//
	// 	for(int i=0;i<report.length;i++){
	// 		String[] arr= report[i].split(" ");
	//
	// 		String a= arr[0];
	// 		int aIdx=0;
	// 		String b= arr[1];
	//
	//
	// 		//신고자의 idx를 대입
	// 		for(int j=0;j<id_list.length;j++){
	// 			if(id_list[j].equals(a)){
	// 				aIdx=j;
	// 			}
	// 		}
	//
	//
	//
	//
	// 		String comb= a+" "+b;
	// 		//a가 b를 신고함
	// 		if(!visited.contains(comb)){
	// 			int[] value= map.get(b);
	// 			value[0]=map.get(b)[0];
	// 			value[1]=aIdx;
	// 			value[2]++;
	// 			map.put(b,value);
	// 			visited.add(comb);
	//
	// 			System.out.println("신고자의 이름은"+ a+" 이고 번호는" + aIdx+"이고 신고 당한 사람은"+ b+"이고 번호는"+value[0]);
	//
	// 		}
	// 	}
	//
	// 	for(String key: map.keySet()){
	// 		int[] v= map.get(key);
	//
	//
	//
	//
	// 		//신고당한사람
	// 		int idx= v[0];
	//
	// 		//신고한사람
	// 		int idx2= v[1];
	//
	// 		//신고당항사람의 신고 횟수
	// 		int cnt= v[2];
	//
	// 		if(cnt>=k){
	// 			answer[idx2]++;
	// 		}
	//
	// 	}
	// 	return answer;
	// }


	//리포트 맵은 따로 구현

	class Solution {

		static Set<String> visited;

		public int[] solution(String[] id_list, String[] report, int k) {
			int[] answer = new int[id_list.length];

			Map<String, int[]> map = new HashMap<>();
			Map<String, Set<Integer>> reportersMap = new HashMap<>();
			visited = new HashSet<>();

			for (int i = 0; i < id_list.length; i++) {
				int[] value = new int[2];
				value[0] = i; // 유저 인덱스
				value[1] = 0; // 신고당한 횟수
				map.put(id_list[i], value);
				reportersMap.put(id_list[i], new HashSet<>());
			}

			for (int i = 0; i < report.length; i++) {
				String[] arr = report[i].split(" ");
				String a = arr[0]; // 신고한 사람
				String b = arr[1]; // 신고당한 사람

				int aIdx = 0;
				for (int j = 0; j < id_list.length; j++) {
					if (id_list[j].equals(a)) {
						aIdx = j;
						break;
					}
				}

				String comb = a + " " + b;
				if (!visited.contains(comb)) {
					int[] value = map.get(b);
					value[1]++; // 신고당한 횟수 증가
					visited.add(comb);
					reportersMap.get(b).add(aIdx); // 신고자 인덱스 추가
				}
			}

			for (String key : map.keySet()) {
				int[] v = map.get(key);
				int cnt = v[1]; // 신고당한 횟수

				if (cnt >= k) {
					for (int reporterIdx : reportersMap.get(key)) {
						answer[reporterIdx]++;
					}
				}
			}

			return answer;
		}
	}

}