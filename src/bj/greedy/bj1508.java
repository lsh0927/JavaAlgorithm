package bj.greedy;

import java.util.*;
import java.io.*;

public class bj1508 {

	//이분탐색....
	static int n, m, k;
	static int[] positions;
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		positions = new int[k];
		for (int i = 0; i < k; i++) {
			positions[i] = Integer.parseInt(st.nextToken());
		}

		System.out.println(binarySearch());

		br.close();
	}

	private static String binarySearch() {

		String res = "";
		int min = 1;
		int max = n;

		while (min <= max) {

			int mid = (min + max) / 2;
			String ans = possible(mid);

			if (ans.isEmpty()) {
				max = mid - 1;
			} else {
				min = mid + 1;
				res = ans;
			}
		}

		return res;
	}

	private static String possible(int target) {

		int cnt = 0;
		StringBuilder sb = new StringBuilder();
		sb.append(1);
		cnt += 1;
		int before = positions[0];

		for (int i = 1; i < positions.length; i++) {
			if (cnt == m) {
				sb.append(0);
			} else {
				if (positions[i] - before >= target) {
					sb.append(1);
					cnt += 1;
					before = positions[i];
				} else {
					sb.append(0);
				}
			}
		}

		if (cnt == m) {
			return sb.toString();
		}

		return "";
	}
}