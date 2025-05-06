package pr.Lv2;
class Solution {
	public int solution(String name) {
		int move=name.length()-1;
		int answer=0;
		for(int i=0;i<name.length();i++){
			//상하이동
			answer+= Math.min(name.charAt(i)-'A', 'Z'-name.charAt(i)+1);

			//좌우 이동

			int next= i+1;
			while(next<name.length() && name.charAt(next)=='A'){
				next++;
			}

			move=Math.min(move, (2*i)+(name.length()-next));
			move=Math.min(move, (2*(name.length()-next)+ i));


		}

		answer+=move;
		return answer;
	}
}