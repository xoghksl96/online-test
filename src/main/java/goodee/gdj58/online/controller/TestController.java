package goodee.gdj58.online.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import goodee.gdj58.online.service.TestService;
import goodee.gdj58.online.vo.Student;
import goodee.gdj58.online.vo.Teacher;
import lombok.extern.slf4j.Slf4j;

@Slf4j // static Log log = new Log() 로그 객체를 선언하여 사용할 수 있게 함
@Controller
public class TestController {
	@Autowired private TestService testService;
	
	// 문제리스트 출력
	@GetMapping("/test/testList")
	public String testList(Model model, HttpSession session
			, @RequestParam(value="teacherNo", defaultValue="0") int teacherNo
			, @RequestParam(value="studentNo", defaultValue="0") int studentNo
			, @RequestParam(value="currentPage", defaultValue="1") int currentPage
			, @RequestParam(value="rowPerPage", defaultValue="10") int rowPerPage
			, @RequestParam(value="searchWord", defaultValue="") String searchWord) {
		
		if(session.getAttribute("loginTeacher") != null) { // teacher 로그인 시, 자기가 등록한 문제만 출력하기 위함
			Teacher loginTeacher = (Teacher) session.getAttribute("loginTeacher");
			teacherNo = loginTeacher.getTeacherNo();
		}
		
		if(session.getAttribute("loginStudent") != null) { // teacher 로그인 시, 자기가 등록한 문제만 출력하기 위함
			Student loginStudent = (Student) session.getAttribute("loginStudent");
			studentNo = loginStudent.getStudentNo();
		}	
		
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
