package sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj11399 {

	static int N;
	static int[] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N=Integer.parseInt(br.readLine());
		arr= new int[N];

		StringTokenizer st= new StringTokenizer(br.readLine());

		for(int i=0;i<N;i++){
			arr[i]=Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);

		int sum=0;
		int cnt=N;
		for(int i=0;i<N;i++){
			sum+=arr[i]*(cnt--);
		}
		System.out.println(sum);
	}
}
