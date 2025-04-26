package pr;
import java.util.*;

public class PR_전화번호목록 {
	// import java.util.*;

	// class Solution {
	//     public boolean solution(String[] phone_book) {
	//         // 모든 전화번호를 HashSet에 저장
	//         Set<String> set = new HashSet<>();
	//         for (String phone : phone_book) {
	//             set.add(phone);
	//         }

	//         // 각 전화번호의 모든 접두어가 다른 전화번호와 일치하는지 확인
	//         for (String phone : phone_book) {
	//             for (int i = 1; i < phone.length(); i++) {
	//                 String prefix = phone.substring(0, i);
	//                 if (set.contains(prefix)) {
	//                     return false;
	//                 }
	//             }
	//         }

	//         return true;
	//     }
	// }
	class Solution {
		public boolean solution(String[] phone_book) {

			Set<String> set = new HashSet<>();
			for (String phone : phone_book) {
				set.add(phone);
			}
			for(int i=0;i<phone_book.length;i++){
				String num=phone_book[i];
				for(int j=0;j<num.length();j++){
					if(set.contains(num.substring(0,j))) {
						// System.out.println(num.substring(0,j));
						return false;
					}
				}
			}
			return true;
		}
	}
}
