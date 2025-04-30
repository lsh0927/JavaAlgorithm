package pr.고득점kit;
import java.util.*;

public class 베스트앨범 {

	class Solution {
		static class Album implements Comparable<Album>{
			int idx;
			int play;

			public Album(int idx, int play){
				this.idx=idx;
				this.play=play;
			}

			@Override
			public int compareTo(Album o){
				if(this.play==o.play){
					return this.idx-o.idx;
				}
				return o.play-this.play;
			}
		}
		public int[] solution(String[] genres, int[] plays) {

			Map<String, Integer> genreTotalPlay = new HashMap<>();
			Map<String, List<Album>> map= new HashMap<>();

			int idx=0;
			for(String genre: genres){
				genreTotalPlay.put(genre, genreTotalPlay.getOrDefault(genre,0)+plays[idx]);
				map.putIfAbsent(genre, new ArrayList<>());
				map.get(genre).add(new Album(idx,plays[idx]));
				idx++;
			}

			List<String> list= new ArrayList<>(map.keySet());
			list.sort((a,b)-> genreTotalPlay.get(b)-genreTotalPlay.get(a));

			List<Integer> ans= new ArrayList<>();
			for(String genre: list){
				List<Album> albumList= map.get(genre);

				Collections.sort(albumList);

				for(int i=0;i<Math.min(2,albumList.size());i++){
					ans.add(albumList.get(i).idx);
				}
			}

			return ans.stream().mapToInt(Integer::intValue).toArray();

		}
	}
}
