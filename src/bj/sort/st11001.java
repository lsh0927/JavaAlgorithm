package bj.sort;

import java.io.*;
import java.util.*;

public class st11001 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N= Integer.parseInt(br.readLine());
		List<Pair> list= new ArrayList<>();

		for(int i=1;i<=N;i++){
			String input= br.readLine();
			String[] pairs= input.split("\\.");
			int intPart= Integer.parseInt(pairs[0]);
			int floatPart;
			if(pairs.length>1){
				floatPart= Integer.parseInt(pairs[1]);
			}else{
				floatPart= -1;
			}
			list.add(new Pair(intPart, floatPart));
		}
		Collections.sort(list);

		for(Pair p: list){
			if(p.floatPart==-1){
				System.out.println(p.intPart);
			}else{
				System.out.println(p.intPart+"."+p.floatPart);
			}
		}

	}
	static class Pair implements Comparable<Pair>{
		int intPart;
		int floatPart;

		public Pair(int intPart, int floatPart){
			this.intPart=intPart;
			this.floatPart=floatPart;
		}

		@Override
		public int compareTo(Pair o){
			if(this.intPart==o.intPart){
				return Integer.compare(this.floatPart, o.floatPart);
			}
			return Integer.compare(this.intPart, o.intPart);
		}
	}

}