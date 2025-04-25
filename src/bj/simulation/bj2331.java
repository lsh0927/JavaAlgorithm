package bj.simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class bj2331 {

	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());

		int A= Integer.parseInt(st.nextToken());
		int P= Integer.parseInt(st.nextToken());

		List<Integer> list= new ArrayList<>();
		list.add(A);

		while (true) {
			int temp = list.get(list.size() - 1);

			int result = 0;
			// 어떤 수의 각 자리에 대해 P제곱만큼하여 각 자리를 더한 값을 구함.
			while (temp != 0) {
				result += (int) Math.pow(temp % 10, P);
				temp /= 10;
			}

			// result가 이미 list에 포함되어있다면,
			// 그 result가 가리키는 인덱스를 반환.
			if (list.contains(result)) {
				int ans = list.indexOf(result);
				System.out.println(ans);
				break;
			}
			list.add(result);
		}
	}
}