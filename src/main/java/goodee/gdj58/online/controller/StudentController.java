package goodee.gdj58.online.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import goodee.gdj58.online.service.IdService;
import goodee.gdj58.online.service.StudentService;
import goodee.gdj58.online.service.TestService;
import goodee.gdj58.online.vo.Employee;
import goodee.gdj58.online.vo.Student;
import goodee.gdj58.online.vo.Teacher;
import lombok.extern.slf4j.Slf4j;

@Slf4j // static Log log = new Log() 로그 객체를 선언하여 사용할 수 있게 함
@Controller
public class StudentController {
	@Autowired StudentService studentService;
	@Autowired TestService testService;
	@Autowired IdService idService;
	
	// 응시한 한 시험의 성적표 보기
	@GetMapping("/student/completeTestResult")
	public String getCompleteTestResult(Model model, HttpSession session
			, @RequestParam(value="testNo", defaultValue="1") int testNo) {
		Student loginStudent = (Student) session.getAttribute("loginStudent");
		int studentNo = loginStudent.getStudentNo();
		
		List<Map<String,Object>> list = testService.getgetCompleteTestResult(testNo, studentNo);
		model.addAttribute("list",list);
		
		return "/student/completeTestResult";
	}
	
	// 학생이 응시한 시험목록만 가져오기
	@GetMapping("/student/completeTestList")
	public String getCompleteTest(Model model, HttpSession session){
		Student loginStudent = (Student) session.getAttribute("loginStudent");
		int studentNo = loginStudent.getStudentNo();
		
		List<Map<String,Object>> list = testService.getCompleteTest(studentNo);
		model.addAttribute("list",list);
		
		return "/student/completeTestListForStudent";
	}
	
	// 전체시험 개수, 내가 응시한 시험개수 가져오기
	// 부트스트랩확인(studentHome)
	@GetMapping("/student/studentHome")
	public String login(Model model, HttpSession session
			, @RequestParam(value="teacherNo", defaultValue="0") int teacherNo
			, @RequestParam(value="studentNo", defaultValue="0") int studentNo
			, @RequestParam(value="currentPage", defaultValue="1") int currentPage
			, @RequestParam(value="rowPerPage", defaultValue="5") int rowPerPage
			, @RequestParam(value="searchWord", defaultValue="") String searchWord) {	
		
		Student loginStudent = (Student) session.getAttribute("loginStudent");
		studentNo = loginStudent.getStudentNo();
		
		// 최근 5개의 시험 목록 가져오기
		List<Map<String, Object>> list = testService.getTestList(teacherNo, studentNo, currentPage, rowPerPage, searchWord);
		
		model.addAttribute("list", list); // request.setAttribute("list", list) 기능 (매개변수 model 필요)
		model.addAttribute("teacherNo", teacherNo);
		model.addAttribute("studentNo", studentNo);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("rowPerPage", rowPerPage);
		model.addAttribute("searchWord", searchWord);
		
		// 총 시험 개수 및 페이징에 필요한 정보 map에 담기
		Map<String, Object> map = testService.getTestCount(teacherNo, currentPage, rowPerPage, searchWord);	
		
		// 내가 응시한 시험개수 map에 추가
		int completeTestCount = testService.getCompleteTestCount(studentNo);
		map.put("completeTestCount", completeTestCount);
		
		model.addAttribute("map", map);
		
		return "student/studentHome";	
	}
	
	// 로그아웃
	@GetMapping("/student/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/student/loginStudent";
	}
	
	// 로그인
	@GetMapping("/loginStudent")
	public String loginTeacehr() {
		return "student/loginStudent";
	}
	
	@PostMapping("/loginStudent")
	public String loginTeacehr(HttpSession session, Student student) {
		Student resultStudent = studentService.login(student);	// row == 1이면 입력성공
		session.setAttribute("loginStudent", resultStudent);
		return "redirect:/student/studentHome";
	}
	
	// student 삭제
	@GetMapping("/employee/student/removeStudent")
	public String removeStudent(@RequestParam("studentNo") int studentNo) {
		
		studentService.removeStudent(studentNo);	// row == 1이면 삭제성공
		return "redirect:/employee/student/studentList";
	}
	
	// student 입력
	@GetMapping("/employee/student/addStudent")
	public String addStudent() {		
		return "student/addStudent";
	}
	@PostMapping("/employee/student/addStudent")
	public String addStudent(Model model, Student student) {
		
		int row = studentService.addStudent(student);
		if(row != 1) { // row != 1이면 입력실패
			model.addAttribute("errorMsg", "시스템 에러로 인한 실패");
			return "student/addStudent";
		}
		return "redirect:/employee/student/studentList"; // sendRedict , cm
	}
	
	
	// StudentList 출력
	@GetMapping("/employee/student/studentList")
	public String studentList(Model model
			, @RequestParam(value="currentPage", defaultValue="1") int currentPage
			, @RequestParam(value="rowPerPage", defaultValue="5") int rowPerPage
			, @RequestParam(value="searchWord", defaultValue="") String searchWord) {
			// int currentPage = request.getParameter("currentPage");
		
		List<Student> list = studentService.getStudentList(currentPage,rowPerPage,searchWord);
		
		model.addAttribute("list", list); // request.setAttribute("list", list) 기능 (매개변수 model 필요)
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("rowPerPage", rowPerPage);
		model.addAttribute("searchWord", searchWord);
		
		Map<String, Object> map = studentService.getStudentCount(currentPage, rowPerPage, searchWord);
		log.debug("\u001B[31m" + map.get("startPage") + "<-- startPage");
		log.debug("\u001B[31m" + map.get("endPage") + "<-- endPage");
		log.debug("\u001B[31m" + map.get("lastPage") + "<-- lastPage");
		model.addAttribute("map", map);
		
		return "student/studentList";
	}
}
