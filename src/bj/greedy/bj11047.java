package bj.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class bj11047 {

	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N=Integer.parseInt(st.nextToken());
		int K=Integer.parseInt(st.nextToken());

		int ans=0;
		int sum=0;

		int[] arr=new int[N];

		for (int i = 0; i < N; i++) {
			arr[i]=Integer.parseInt(br.readLine());
		}

		int idx=N-1;
		while (sum<K && idx>=0){
			if (sum+arr[idx]<=K){
				sum+=arr[idx];
				ans++;
				if (sum==K){
					break;
				}
			}
			else{
				if (idx>0)
					idx--;
			}
		}

		System.out.println(ans);
	}
}