package kakao;

import java.util.ArrayList;
import java.util.List;

public class PR_카드게임 {
	class Solution {

		private boolean[] used;
		private int coin;
		private boolean canNext=true;

		public int solution(int coin, int[] cards) {
			used = new boolean[cards.length];

			List<Integer> list= new ArrayList<>();
			this.coin=coin;

			for(int i=0;i<cards.length/3;i++){
				list.add(cards[i]);
			}
			int round=1;

			for(int i=cards.length/3;i<cards.length;i+=2){
				list.add(cards[i]);
				list.add(cards[i+1]);
				submit(list,cards.length+1,cards);

				if(this.coin<0 || !canNext){
					return round;
				}
				round++;
			}
			return round;
		}

		private void submit(List<Integer> list, int n, int[] cards){
			for(int i=0;i<cards.length/3;i++){
				for(int j=i+1;j<cards.length/3;j++){
					if(list.get(i)+list.get(j)==n && !used[i] && !used[j]){
						used[i]=true;
						used[j]=true;
						return;
					}
				}
			}
			for (int i = 0; i < list.size() - 1; i++) {
				for (int j = i + 1; j < list.size(); j++) {
					if (list.get(i) + list.get(j) == n && !used[i] && !used[j]) {
						used[i] = true;
						used[j] = true;
						if (i >= cards.length / 3) {
							coin--;
						}
						if (j >= cards.length / 3) {
							coin--;
						}
						return;
					}
				}
			}

			//다음 라운드로 진출 못함(n+1을 만들 짝을 찾지 못함)
			canNext=false;
		}
	}
}
