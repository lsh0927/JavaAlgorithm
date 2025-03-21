package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class bj1114 {
	static long L;
	static int K,C;
	static long FIRST_CUT = Long.MAX_VALUE;
	static long [] loc;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Long.parseLong(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		loc = new long [K + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= K; i++) {
			loc[i] = Integer.parseInt(st.nextToken());
		}
		loc[0] = 0;
		Arrays.sort(loc);
		System.out.println(binary_search() + " " + FIRST_CUT);
	}

	public static long binary_search () {
		long start = 0;
		long end = L;
		while (start <= end){
			long mid = start + (end - start)/2;
			if(f(mid, C)) end = mid - 1;
			else start = mid + 1;
		}
		return start;
	}

	public static boolean f(long w, int c){
		long prev = L;
		long first_cut = -1;
		for (int i = K; i >= 0; i--) {
			long now = loc[i];
			if(prev - now <= w){
				if(now != 0 && c > 0) first_cut = now;
			}
			else{
				if(i == K) return false;
				now = loc[++i];
				if(prev - now <= w) {
					if(c == 0) return false;
					c--;
					prev = now;
					if(now != 0) first_cut = now;
				}else return false;
			}
		}
		FIRST_CUT = first_cut;
		return true;
	}
}