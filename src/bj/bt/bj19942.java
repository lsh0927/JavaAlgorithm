package bj.bt;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class bj19942 {

	static int N;
	static int mp,mf,ms,mv;
	static int[][] table;
	static int[] tmp;
	static int min=Integer.MAX_VALUE;
	static List<List<Integer>> list;
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N=Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		mp=Integer.parseInt(st.nextToken());
		mf=Integer.parseInt(st.nextToken());
		ms=Integer.parseInt(st.nextToken());
		mv=Integer.parseInt(st.nextToken());

		table= new int[N][5];
		tmp=new int[5];
		list= new ArrayList<>();

		for(int i=0;i<N;i++){
			st = new StringTokenizer(br.readLine());
			//단백질,지방,탄수화물,비타민,가격순으로 저장
			table[i][0]=Integer.parseInt(st.nextToken());
			table[i][1]=Integer.parseInt(st.nextToken());
			table[i][2]=Integer.parseInt(st.nextToken());
			table[i][3]=Integer.parseInt(st.nextToken());
			table[i][4]=Integer.parseInt(st.nextToken());
		}
		//idx,cost
		dfs(0,0, new ArrayList<>());


		//같은 비용일때 list 정렬 기준 -> 사전순
		//List<List<Integer>> 사전 순 정렬을 어떻게 해야하지...

		// Collections.bj.sort(list, Comparator.comparing(a -> a, (a, b) -> {
		// 	for (int i = 0; i < Math.min(a.size(), b.size()); i++) {
		// 		if (!a.get(i).equals(b.get(i))) {
		// 			return a.get(i) - b.get(i);
		// 		}
		// 	}
		// 	return a.size() - b.size();
		// }));
		//

		if(list.isEmpty()){
			System.out.print(-1);
		}
		else{
			System.out.println(min);
			for(int n: list.get(0)){
				System.out.print(n+1+" ");
			}
		}
	}
	static void dfs(int idx, int cost,  List<Integer> idxList){

		if(cost>min){
			return;
		}

		//min 갱신과 함께, list도 정리가 필요
		//min이 새로 갱신되었다면 list.clear로 제일 작은 값일때의 인덱스 리스트 추가
		if(check(tmp)){
			if(cost<min){
				min=cost;
				list.clear();
				list.add(new ArrayList<>(idxList));
			}
			if(cost==min){
				list.add(new ArrayList<>(idxList));
			}
			return;
		}

		for(int i= idx;i<N;i++){

			tmp[0]+=table[i][0];
			tmp[1]+=table[i][1];
			tmp[2]+=table[i][2];
			tmp[3]+=table[i][3];

			idxList.add(i);

			dfs(i+1,cost+table[i][4],idxList);

			idxList.remove(idxList.size()-1);

			tmp[0]-=table[i][0];
			tmp[1]-=table[i][1];
			tmp[2]-=table[i][2];
			tmp[3]-=table[i][3];
		}
	}
	static boolean check(int[] arr){
		if(arr[0]<mp || arr[1]<mf || arr[2]<ms || arr[3]<mv){
			return false;
		}
		return true;
	}
}