package bj.greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// public class bj3165 {
//
// 	static long N,K;
// 	public static void main(String[] args) throws Exception {
// 		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
// 		StringTokenizer st = new StringTokenizer(br.readLine());
//
// 		N=Long.parseLong(st.nextToken());
// 		K=Long.parseLong(st.nextToken());
//
// 		//N보다 크면서, 5가 최소 1번은 포함되는 가장 작은 수
// 		dfs(N);
// 	}
// 	static void dfs(long num){
// 		String str= String.valueOf(num);
// 		int cnt=0;
// 		for(int i=0;i<str.length();i++){
// 			if(str.charAt(i)=='5'){
// 				cnt++;
// 			}
// 		}
//
// 		if(num>N && cnt>=K){
// 			System.out.print(num);
// 			System.exit(0);
// 			return;
// 		}
//
// 		dfs(num+1);
// 	}
// }

public class bj3165 {

	static long N, K;
	static StringBuilder num;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N=Long.parseLong(st.nextToken());
		K=Long.parseLong(st.nextToken());

		num= new StringBuilder(String.valueOf(N+1)).reverse();

		bt(0,false);

		addRemaining();

		System.out.println(num.reverse().toString());
	}
	static void bt(int idx, boolean flag){
		if(idx>=num.length() || countFives() >=K){
			return;
		}

		//이전 자리수가 올림된 경우 현재 자리수를 5로 설정
		if(flag){
			num.setCharAt(idx-1,'5');
		}

		if(countFives()>=K) return;

		if(num.charAt(idx)<'5'){
			num.setCharAt(idx,'5');
			bt(idx+1,false);
		}
		else if(num.charAt(idx)>'5'){
			num.setCharAt(idx,'0');
			roundUp(idx);
			bt(idx+1,true);
		}else{
			bt(idx+1,false);
		}
	}
	static int countFives(){
		int count=0;
		for(int i=0;i<num.length();i++){
			if(num.charAt(i)==5){
				count++;
			}
		}
		return count;
	}
	static void roundUp(int idx){
		long add=10;
		for(int i=0;i<idx;i++){
			add*=10;
		}
		num= new StringBuilder(String.valueOf(Long.parseLong(num.reverse().toString())+add)).reverse();
	}
	static void addRemaining(){
		//현재 '5'의 개수 확인
		int count= countFives();

		//필요한만큼 5추가
		for(int i=count;i<K;i++){
			num.append('5');
		}
	}
}