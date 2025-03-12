package bt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj2661 {

	static int n;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n= Integer.parseInt(br.readLine());

		dfs("");
	}
	static void dfs(String str){
		if(str.length()==n){
			System.out.println(str);
			System.exit(0);
			return;
		}

		for(int i=1;i<=3;i++){
			if(check(str+i)) dfs(str+i);
		}

	}
	static boolean check(String str){
		for(int i=1;i<=str.length()/2;i++){
			String front= str.substring(str.length()-i*2,str.length()-i);
			String back= str.substring(str.length()-i);
			if(front.equals(back)) return false;
		}
		return true;
	}
}