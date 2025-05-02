import java.io.*;
import java.util.*;
//
// public class Main {
// 	public static void main(String[] args) throws IOException {
// 		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
// 		StringTokenizer st= new StringTokenizer(br.readLine());
//
// 		int N=Integer.parseInt(st.nextToken());
// 		int K=Integer.parseInt(st.nextToken());
//
// 		String str= Integer.toBinaryString(N);
//
// 		// 1101 -> 10000으로 만들기 위해서는 우선 모든 비트를 1로 만들고 거기에 1을 더해야 함
//
// 		//str에서 0이 아닌 비트마다, 2^ 그 비트의 자릿수를 더함
// 		//근데 2로 나누어떨어지지 않을때까지 연산하고 해야하는듯?
// 		int sum=0;
// 		for(int i=str.length()-1;i>=0;i--){
// 			if(str.charAt(i)=='0'){
// 				int j=str.length()-1-i;
// 				int num=1;
// 				for(int k=0;k<j;k++){
// 					num*=2;
// 				}
// 				sum+=num;
// 			}
// 		}
//
// 		System.out.println(sum+1);
// 	}
// }
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class Main {
	static int N;
	static int K;
	static int ans=0;
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st= new StringTokenizer(br.readLine());

		N=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());

		//1개만 옮기려면 ->N= 2^n
		//2개 ::      ->N= 2^n1 +2^n2

		//ex) 13,2 => 8+4+1 => 1을 없애고 4쪽에 붙어야하니 3개의 물병을 사야 함.
		ans=count(N,K);

		System.out.println(ans);
	}
	public static int count(int num,int d){
		int count=0;
		int sum=0;
		int result;
		List<Integer> list= new ArrayList<>();
		int i=1;
		while(num>0) {
			while (i*2<= num) {
				i = i * 2;
			}
			list.add(i);
			num-=i;
			count++;
			i=1;
		}
		int index= count-d;
		if(index>0){
			for(int j=d;j<count;j++)
			{
				sum+=list.get(j);
			}
			result=list.get(d-1)-sum;
			return result;
		}
		else
			return 0;
	}
}