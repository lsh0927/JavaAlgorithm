package simulation;

import java.io.*;
import java.util.*;

public class st9657 {

	public static void main(String[] args) throws Exception {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());

		int R=Integer.parseInt(st.nextToken());
		int C=Integer.parseInt(st.nextToken());
		int ans=0;

		int[][] map= new int[R+1][C+1];
		for(int i=1;i<=R;i++){
			st= new StringTokenizer(br.readLine());
			for(int j=1;j<=C;j++){
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]==1) ans++;

			}
		}

		st= new StringTokenizer(br.readLine());
		int FAR= Integer.parseInt(st.nextToken());
		int FAC= Integer.parseInt(st.nextToken());

		st= new StringTokenizer(br.readLine());
		int SAR= Integer.parseInt(st.nextToken());
		int SAC= Integer.parseInt(st.nextToken());


		for(int i=FAR; i<=FAC;i++){
			for(int j=1;j<=C;j++){
				//한번 만나면 바로 ans 감소하고 다음행으로 넘어가야 함.
				if(map[i][j]==1){
					map[i][j]=0;
					ans--;
					break;
				}
			}
		}

		for(int i=SAR; i<=SAC;i++){
			for(int j=1;j<=C;j++){
				//한번 만나면 바로 ans 감소하고 다음행으로 넘어가야 함.
				if(map[i][j]==1){
					map[i][j]=0;
					ans--;
					break;
				}
			}
		}
		System.out.println(ans);
	}

}
