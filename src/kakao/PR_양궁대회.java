package kakao;
import java.util.*;

public class PR_양궁대회 {

	class Solution {
		static int max;
		static boolean canWin;
		static List<int[]> ans;
		public int[] solution(int n, int[] info) {
			max = 0;
			canWin = false;
			ans= new ArrayList<>();
			//info는 10점부터 0점까지 맞힌 횟수
			//어피치와 라이언이 각 점수에 맞힌 개수가 같다면 어피치가 그 과녁 점수만큼 가져감(10점짜리면 갯수상관없이 10)
			//라이언이 가장크게 이기는 경우의 수

			//n<=10
			//info 길이 =11

			int[] lionInfo= new int[11];

			dfs(n,0,0,lionInfo,info);

			if(!canWin){
				return new int[] {-1};
			}
			else{

				Collections.sort(ans, (o1, o2) -> {
					// 맨 뒤(낮은 점수)부터 비교
					for (int i = 10; i >= 0; i--) {
						if (o1[i] != o2[i]) {
							return o2[i] - o1[i]; // 낮은 점수를 더 많이 맞힌 경우가 앞으로
						}
					}
					return 0;
				});


				return ans.get(0);
			}
		}
		static void dfs(int n,int idx, int cnt, int[] lionInfo,int[] info){

			if(cnt==n){
				int aScore=0;
				int bScore=0;
				//화살을 전부 쐈으므로 점수 비교
				for(int i=0;i<=10;i++){
					if(info[i]<lionInfo[i]){
						bScore+= 10-i;
					}else if(info[i]!=0){
						aScore+= 10-i;
					}
				}

				if(bScore>aScore){
					canWin=true;
					int diff = bScore-aScore;

					if(max < diff){
						ans.clear();
						max = diff;
						int[] arr= new int[11];
						for(int i=0;i<11;i++){
							arr[i]=lionInfo[i];
						}
						ans.add(arr);
					} else if(max == diff) {
						// 최대 점수 차이가 같은 경우에만 추가
						int[] arr= new int[11];
						for(int i=0;i<11;i++){
							arr[i]=lionInfo[i];
						}
						ans.add(arr);
					}
				}
				return;
			}


			for(int i=idx;i<=10;i++){
				//중복이 허용되므로
				lionInfo[i]++;
				dfs(n,i,cnt+1,lionInfo,info);
				lionInfo[i]--;
			}
		}
	}
}

//훨씬 빠른 풀이

/*
import java.util.*;

class Solution {
    int maxScore=0;
    int[] info;
    int[] answer= new int[11];
    public int calScore(int[] lion){
        int diff=0;
        for(int i=0;i<=10;i++){
            if(lion[i]==0 && info[i]==0){
                continue;
            }
            diff=(lion[i]>info[i])?diff + (10-i) : diff -(10-i);
        }
        return diff;
    }
    public int[] solution(int n, int[] info) {
        this.info=info;
        dfs(0,new int[11],n);

        //정답이 없으면 -1리턴
        if(maxScore==0){
            return new int[]{-1};
        }
        return answer;
    }
    public void dfs(int index, int[] lion, int arrow){
        //최종 인덱스 도달 시 결과 처리
        if(index==11){
            lion[10]=arrow; //화살이 남아있다면 모두 10번 인덱스에 부여
            //점수차이 계산
            int score= calScore(lion);
            if(score>maxScore){
                maxScore=score;
                answer= Arrays.copyOf(lion,lion.length);

            }else if(score==maxScore){
                for(int i=10;i>=0;i--){
                    if(lion[i]==answer[i]){
                        continue;
                        //뒤에서부터 더 많이 맞힌 경우 정답 교체
                    }
                    if(lion[i]>answer[i]){
                        answer= Arrays.copyOf(lion,lion.length);
                    }
                    break;
                }
            }
            return;
        }
        if(info[index]<arrow){
            lion[index]=info[index]+1;
            dfs(index+1,lion,arrow-lion[index]);
            lion[index]=0;
        }dfs(index+1,lion,arrow);
    }
}


 */