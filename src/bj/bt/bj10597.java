package bj.bt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class bj10597 {

	static char[] arr;
	static int L,C;
	static List<String> list= new ArrayList<>();
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());

		L=Integer.parseInt(st.nextToken());
		C=Integer.parseInt(st.nextToken());

		st= new StringTokenizer(br.readLine());

		arr= new char[C];
		for(int i=0;i<C;i++){
			arr[i]=st.nextToken().charAt(0);
		}

		Arrays.sort(arr);
		visited = new boolean[C];

		dfs(0,0,"");
		for(String s: list){
			System.out.println(s);
		}

	}
	static void dfs(int idx, int len, String str){
		if(len==L){
			if(checkMo(str) && checkJa(str)){
				list.add(str);
			}
			return;
		}

		for(int i=idx;i<C;i++){
			dfs(i + 1, len + 1, str + arr[i]);
		}
	}

	static boolean checkMo(String str){
		int cnt=0;
		for(int i=0;i<str.length();i++){
			if(str.charAt(i)=='a' ||
				str.charAt(i)=='e' ||
				str.charAt(i)=='i' ||
				str.charAt(i)=='o' ||
				str.charAt(i)=='u'){
				cnt++;
			}
		}
		if(cnt>=1)
			return true;
		return false;

	}
	static boolean checkJa(String str){
		int cnt=0;
		for(int i=0;i<str.length();i++){
			if(str.charAt(i)!='a' &&
				str.charAt(i)!='e' &&
				str.charAt(i)!='i' &&
				str.charAt(i)!='o' &&
				str.charAt(i)!='u'){
				cnt++;
			}
		}
		if(cnt>=2)
			return true;
		return false;
	}

}