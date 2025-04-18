package kakao;

public class PR_퍼즐게임챌린지 {
	//최소인 lev을 출력하기 위해선 최솟값부터 시작을...
	//그럼 lev을 1부터?
	//그럼 4번 입출력보면 300000 길이를 39354만큼 도는 데 그럼 타임 아웃..
	//그리디?
	//이분 탐색
	public int solution(int[] diffs, int[] times, long limit) {

		//min=100000일때 모든 퍼즐을 한번에 통과하므로 시간은 최소
		int maxLev=100000;
		int minLev=1;

		while(minLev<=maxLev){
			int midLev=(minLev+maxLev)/2;
			long time = cal(diffs,times,limit,midLev);

			if(time>limit){
				minLev=midLev+1;
			}else{
				maxLev=midLev-1;
			}
		}
		return minLev;
	}
	static long cal(int[] diffs, int[] times, long limit, int lev){
		long time=times[0];
		for(int i=1;i<diffs.length;i++){
			if(lev>=diffs[i]){
				time+=times[i];
			}
			else{
				time+=(long)(diffs[i]-lev)*(times[i]+times[i-1]) + times[i];
			}
		}
		return time;
	}
}