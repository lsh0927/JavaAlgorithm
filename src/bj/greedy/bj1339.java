package bj.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class bj1339 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		Map<Character, Integer> weight = new HashMap<>();

		// 각 알파벳의 가중치 계산
		for(int i = 0; i < n; i++) {
			String s = br.readLine();
			int len = s.length();

			for(int j = 0; j < len; j++) {
				char c = s.charAt(j);
				int pow = (int)Math.pow(10, len - j - 1);
				weight.put(c, weight.getOrDefault(c, 0) + pow);
			}
		}

		// 알파벳 가중치 기준 내림차순 정렬
		List<Character> alphabets = new ArrayList<>(weight.keySet());
		alphabets.sort((a, b) -> weight.get(b) - weight.get(a));

		// 가중치가 높은 알파벳부터 9, 8, 7... 숫자 할당
		int number = 9;
		int answer = 0;

		for(char c : alphabets) {
			answer += weight.get(c) * number--;
		}

		System.out.println(answer);
	}
}
