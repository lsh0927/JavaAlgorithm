package bj.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj1041 {

	static int[] side= new int[6];
	static long[] totalNum= new long[3];
	static int[] min= new int[3];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());

		int[] arr= new int[6];

		StringTokenizer st= new StringTokenizer(br.readLine());
		for (int i=0;i<6;i++){
			arr[i]=Integer.parseInt(st.nextToken());
		}

		setMinIdx();

		totalNum[0] = 4L;
		totalNum[1] = 8L * (N-2) + 4;
		totalNum[2] = 5L * (N-2) * (N-2) + 4L * (N-2);

		if (N==1){
			Arrays.sort(side);
			System.out.println(side[0]+side[1]+side[2]+side[3]+side[4]);
		}
		else {


			System.out.println(
				totalNum[0]*(min[0]+min[1]+min[2])
					+totalNum[1]*(min[0]+min[1])
					+ totalNum[2]* min[0]
			);
		}

	}

	private static void setMinIdx() {

		min[0]= Math.min(side[0], side[5]);
		min[1]= Math.min(side[1], side[4]);
		min[2]= Math.min(side[2], side[3]);

		Arrays.sort(min);
	}
}
