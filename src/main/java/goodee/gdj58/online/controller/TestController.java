package goodee.gdj58.online.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import goodee.gdj58.online.service.TestService;
import goodee.gdj58.online.vo.Student;
import goodee.gdj58.online.vo.Teacher;
import lombok.extern.slf4j.Slf4j;

@Slf4j // static Log log = new Log() 로그 객체를 선언하여 사용할 수 있게 함
@Controller
public class TestController {
	@Autowired private TestService testService;
	
	// 시험 문제 추가
	@PostMapping
	public String addTest(Model model, HttpSession session
			,@RequestParam(value="testNo", defaultValue="0") int testNo
			,@RequestParam(value="questionIdx", defaultValue="0") int questionIdx
			,@RequestParam(value="questionTitle", defaultValue="") String questionTitle
			,@RequestParam(value="exampleIdx", defaultValue="0") int exampleIdx[]
			,@RequestParam(value="exampleTitle", defaultValue="") String exampleTitle[]
			,@RequestParam(value="exampleTitle", defaultValue="오답") String exampleOx[]) {
		
		int result = testService.addQuesetionAndExample(testNo, questionIdx, questionTitle, exampleIdx, exampleIdx, exampleOx);
		
		if(result != 1) {
			return "test/testOne";
		}
		return "/teacher/test/addTest";
		
		
	}
	
	// 시험 출력(하나의 시험)
	@GetMapping("/teacher/test/testOne")
	public String testOneForTeacher(Model model, HttpSession session
			,@RequestParam(value="testNo", defaultValue="0") int testNo) {
		
		List<Map<String, Object>> list = testService.getTestOne(testNo);
		model.addAttribute("list", list);
		
		return "test/testOne";
	}
	
	// 시험 리스트 출력 (선생)
	@GetMapping("/teacher/testList")
	public String testListForTeacher(Model model, HttpSession session
			, @RequestParam(value="teacherNo", defaultValue="0") int teacherNo
			, @RequestParam(value="currentPage", defaultValue="1") int currentPage
			, @RequestParam(value="rowPerPage", defaultValue="10") int rowPerPage
			, @RequestParam(value="searchWord", defaultValue="") String searchWord) {
		
		if(session.getAttribute("loginTeacher") != null) { // teacher 로그인 시, 자기가 등록한 문제만 출력하기 위함
			Teacher loginTeacher = (Teacher) session.getAttribute("loginTeacher");
			teacherNo = loginTeacher.getTeacherNo();
		}	
		
		List<Map<String, Object>> list = testService.getTestList(teacherNo, currentPage, rowPerPage, searchWord);
		
		model.addAttribute("list", list); // request.setAttribute("list", list) 기능 (매개변수 model 필요)
		model.addAttribute("teacherNo", teacherNo);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("rowPerPage", rowPerPage);
		model.addAttribute("searchWord", searchWord);
		
		Map<String, Object> map = testService.getTestCount(teacherNo, currentPage, rowPerPage, searchWord);
		model.addAttribute("map", map);
		
		return "test/testList";		
	}
	
	// 시험 리스트 출력(학생)
	@GetMapping("student/testList")
	public String testListForStudent(Model model, HttpSession session
			, @RequestParam(value="teacherNo", defaultValue="0") int teacherNo
			, @RequestParam(value="studentNo", defaultValue="0") int studentNo
			, @RequestParam(value="currentPage", defaultValue="1") int currentPage
			, @RequestParam(value="rowPerPage", defaultValue="10") int rowPerPage
			, @RequestParam(value="searchWord", defaultValue="") String searchWord) {	
		
		List<Map<String, Object>> list = testService.getTestList(teacherNo, currentPage, rowPerPage, searchWord);
		
		model.addAttribute("list", list); // request.setAttribute("list", list) 기능 (매개변수 model 필요)
		model.addAttribute("teacherNo", teacherNo);
		model.addAttribute("studentNo", studentNo);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("rowPerPage", rowPerPage);
		model.addAttribute("searchWord", searchWord);
		
		Map<String, Object> map = testService.getTestCount(teacherNo, currentPage, rowPerPage, searchWord);
		model.addAttribute("map", map);
		
		return "test/testList";		
	}
}
