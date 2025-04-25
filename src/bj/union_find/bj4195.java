package bj.union_find;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class bj4195 {
	static int tc, F;
	static int[] parents;
	static int[] networkSize; // 각 네트워크의 크기를 저장

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		tc = Integer.parseInt(br.readLine());

		for (int i = 0; i < tc; i++) {
			F = Integer.parseInt(br.readLine());

			// 사용자 ID를 관리하는 맵
			Map<String, Integer> userIdMap = new HashMap<>();

			parents = new int[2*F+1]; // 최대 2*F명의 사용자
			networkSize = new int[2*F+1]; // 각 네트워크 크기

			// 초기화
			for (int a = 0; a < 2*F; a++) {
				parents[a] = a;
				networkSize[a] = 1; // 초기 네트워크 크기는 1
			}

			int userCount = 0; // 사용자 ID 할당을 위한 카운터

			for (int j = 0; j < F; j++) {
				st = new StringTokenizer(br.readLine());

				String from = st.nextToken();
				String to = st.nextToken();

				// 사용자 ID 할당
				if (!userIdMap.containsKey(from)) {
					userIdMap.put(from, userCount++);
				}
				if (!userIdMap.containsKey(to)) {
					userIdMap.put(to, userCount++);
				}

				int fromId = userIdMap.get(from);
				int toId = userIdMap.get(to);

				// 합집합 연산
				union(fromId, toId);

				// fromId가 속한 네트워크의 크기 출력
				System.out.println(networkSize[find(fromId)]);
			}
		}
	}

	static int find(int a) {
		if (a == parents[a])
			return a;
		return parents[a] = find(parents[a]);
	}

	static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);

		if (aRoot == bRoot) return; // 이미 같은 네트워크에 있는 경우

		// 두 네트워크 합치기
		parents[bRoot] = aRoot;
		networkSize[aRoot] += networkSize[bRoot]; // 네트워크 크기 업데이트
	}
}
/*
import static java.util.Comparator.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	static int tc,F;
	static int[] parents;
	static Map<String, List<String>> bj.graph;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		tc= Integer.parseInt(br.readLine());

		for(int i=0;i<tc;i++){
			F=Integer.parseInt(br.readLine());
			parents= new int[2*F+1];

			for(int a=1;a<=2*F;a++){
				parents[a]=a;
			}

			bj.graph= new HashMap<>();


			for(int j=1;j<=F;j++){
				st = new StringTokenizer(br.readLine());

				String from= st.nextToken();
				String to= st.nextToken();

				bj.graph.putIfAbsent(from,new ArrayList<>());
				bj.graph.putIfAbsent(to,new ArrayList<>());

				bj.graph.get(from).add(to);
				bj.graph.get(to).add(from);

				int fromIdx=0;
				int toIdx=0;
				for(String a: bj.graph.keySet()){
					if(a.equals(from)){
						break;
					}
					fromIdx++;
				}

				for(String b: bj.graph.keySet()){
					if(b.equals(to)){
						break;
					}
					toIdx++;
				}
				System.out.println("fromIdx: "+ fromIdx);
				System.out.println("toIdx: "+ toIdx);
				union(fromIdx,toIdx);

				int cnt=1;
				for(int k=0;k<=2*F;k++){
					if(fromIdx==find(k)){
						cnt++;
					}
				}

				System.out.println("cnt: "+cnt);
			}

			for(int n: parents){
				System.out.print(find(n)+" ");
			}
			System.out.println();
		}

	}
	static int find(int a){
		if(a==parents[a])
			return a;
		return parents[a]=find(parents[a]);
	}
	static void union(int a, int b){
		int aRoot= find(a);
		int bRoot= find(b);

		if(aRoot>bRoot) parents[bRoot]=aRoot;
		else{
			parents[aRoot]=bRoot;
		}
	}
}
 */