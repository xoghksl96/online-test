package goodee.gdj58.online.restController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class chartRestController {
	@GetMapping("/scoreData")
	public List<Map<String, Integer>> scoreData() {
		List<Map<String, Integer>> list = new ArrayList<>();
		
		for(int i=1; i<=3; i++) {
			Map<String, Integer> map = new TreeMap<String, Integer>();
			for(int j=1; j<=12; j++) {
				if(j<10) {
					map.put("0"+j+"월", (int) (Math.random()*100)+1);
				} else {
					map.put(j+"월", (int) (Math.random()*100)+1);
				}
			}
			list.add(map);
		}
		return list;
	}
	
	@GetMapping("/monthData") 
	public Map<Integer, Integer> monthData(){
		
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		
		for(int i = 0; i<12; i++) {
			map.put((i+1), (int) (Math.random()*100)+1);	
		}
		return map;	// --> Json 객체 -> {} -> [], []
	}
}
