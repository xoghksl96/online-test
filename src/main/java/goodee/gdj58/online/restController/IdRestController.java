package goodee.gdj58.online.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import goodee.gdj58.online.service.IdService;

@RestController
public class IdRestController {
	@Autowired IdService idService;
	
	@GetMapping("/idCheck")
	public String idCheck(@RequestParam(value="id")String id) {
		System.out.println(id);
		return idService.getIdCheck(id);
	}
	
}
