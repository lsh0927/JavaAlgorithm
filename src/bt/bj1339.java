package bt;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class bj1339 {

	static int N;
	static int max = Integer.MIN_VALUE;
	static ArrayList<Character> list; // 알파벳 저장
	static boolean[] visited; // 숫자 사용 여부
	static int[] value; // 알파벳에 할당된 숫자
	static String[] words; // 입력된 모든 단어 저장

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		list = new ArrayList<>();
		words = new String[N];

		// 모든 단어 저장 및 사용된 알파벳 추출
		for (int i = 0; i < N; i++) {
			words[i] = br.readLine();
			for (int j = 0; j < words[i].length(); j++) {
				char c = words[i].charAt(j);
				if (!list.contains(c)) {
					list.add(c);
				}
			}
		}

		// 0~9 숫자를 사용하므로 크기는 10
		visited = new boolean[10];
		value = new int[list.size()];

		bt(0);
		System.out.println(max);
	}

	static void bt(int len) {
		if (len == list.size()) {
			int sum = 0;
			// 모든 단어에 대해 계산
			for (int i = 0; i < N; i++) {
				int num = 0;
				for (int j = 0; j < words[i].length(); j++) {
					num *= 10;
					num += value[list.indexOf(words[i].charAt(j))];
				}
				sum += num;
			}
			max = Math.max(max, sum);
			return;
		}

		for (int i = 0; i <= 9; i++) {
			if (visited[i]) continue;
			visited[i] = true;
			value[len] = i;
			bt(len + 1);
			visited[i] = false;
			value[len] = 0;
		}
	}
}

/* 효율적인 그리디 알고리즘

public class Main {
	static int N;
	static int [] arr = new int[26];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < str.length(); j++) {
				char c = str.charAt(j);
				arr[c-'A'] += (int)Math.pow(10, str.length() - 1 - j);
			}
		}

		Arrays.sort(arr);

		int num = 9;
		int turn = 25;
		int ans = 0;
		while(arr[turn] != 0) {
			ans += arr[turn]*num;
			turn--;
			num--;
		}

		System.out.print(ans);
	}
}
 */

//풀이 추가

/*
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;


public class Main {

	static String[] word;
	static Map<Character,Integer> map= new HashMap<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n= Integer.parseInt(br.readLine());
		word= new String[n];

		for(int i=0;i<n;i++){
			word[i]=br.readLine();
		}

		for(String str: word){
			for(int i=0;i<str.length();i++){
				int val=str.length()-i-1;
				int w= (int)Math.pow(10,val);
				map.put(str.charAt(i),map.getOrDefault(str.charAt(i),0)+w);
			}
		}

		List<Character> keySet= new ArrayList<>(map.keySet());
		keySet.sort((o1, o2) -> map.get(o2).compareTo(map.get(o1)));

		int sum=0;
		int idx=9;
		for(char c: keySet){
			sum+=map.get(c)*idx--;
		}

		System.out.println(sum);

	}
}
 */