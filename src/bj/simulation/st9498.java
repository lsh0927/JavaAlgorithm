package bj.simulation;

import java.io.*;
import java.util.*;

public class st9498 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();

		System.out.println(Solution(input));
	}

	public static String Solution(String input) {
		Deque<Character> stack = new ArrayDeque<>();
		int inputlength = input.length();

		if (inputlength == 2) {
			return "(1+1)";
		}

		for (int i = 0; i < input.length(); i++) {
			stack.offerLast(input.charAt(i));
		}

		boolean finish = true;
		StringBuilder answer = new StringBuilder();
		while(finish) {
			if (stack.size() == 2) {
				finish = false;
			}


			char L = stack.pollFirst();
			char R = stack.peek();

			if (L == ')' && R == '(') {
				answer.append(L);
				answer.append('+');
			}
			else if (L == '(' && R == ')') {
				answer.append(L);
				answer.append('1');
			} else {
				answer.append(L);
			}
			if (finish == false)
				answer.append(R);
		}
		String res = String.valueOf(answer);
		return res;
	}
}

