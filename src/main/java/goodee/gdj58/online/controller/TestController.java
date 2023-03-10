package goodee.gdj58.online.controller;

import java.lang.ProcessBuilder.Redirect;
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
	
	// 응시한 시험목록 보기(학생)
	
	// 시험문제 제출(학생)
	@PostMapping("/paper/addPaper")
	public String addPaper(Model model, HttpSession session
		,@RequestParam(value="testNo") int testNo
		,@RequestParam(value="questionNo") int[] questionNo
		,@RequestParam(value="answer") int[] answer){
		
		Student loginStudent = (Student) session.getAttribute("loginStudent");
		int studentNo = loginStudent.getStudentNo();
		
		testService.addPapper(testNo, studentNo, questionNo, answer);
		
		return "redirect:/student/testList?studentNo="+studentNo;	
	}
	
	// 시험문제 수정
	@GetMapping("/test/modifyTest")
	public String modifyTest(Model model
			,@RequestParam(value="testNo", defaultValue="0") int testNo
			,@RequestParam(value="questionNo", defaultValue="0") int questionNo) {
		
		// 기존 문제를 보여주는 서비스 호출
		Map<String,Object> map = testService.getQuestionAndExample(testNo, questionNo);
		model.addAttribute("map",map);
		model.addAttribute("testNo",testNo);
		
		return "/test/modifyTest";	
	}
	
	@PostMapping("/test/modifyTest")
	public String modifyTest(Model model
			,@RequestParam(value="questionNo", defaultValue="0") int testNo
			,@RequestParam(value="questionNo", defaultValue="0") int questionNo
			,@RequestParam(value="questionNo", defaultValue="0") String questionTitle
			,@RequestParam(value="questionNo") int[] exampleNo
			,@RequestParam(value="questionNo") String[] exampleTitle
			,@RequestParam(value="questionNo") String[] exampleOx){
		
		testService.modifyQuestionAndExample(testNo, questionNo, questionTitle, exampleNo, exampleTitle, exampleOx);
		
		return "redirect:/teacher/test/testOneForTeacher?testNo="+testNo;
		}
	
	// 시험문제 삭제
	@GetMapping("/test/removeTest")
	public String removeTest(Model model, HttpSession session
			,@RequestParam(value="testNo", defaultValue="0") int testNo
			,@RequestParam(value="questionNo", defaultValue="0") int questionNo){
		
		testService.removeQuestionAndExample(questionNo);
		
		model.addAttribute("testNo", testNo);
		return "redirect:/teacher/test/testOneForTeacher?testNo="+testNo;	
	}
	
	// 시험 문제 추가
	@PostMapping("/test/addQuestionAndExample")
	public String addTest(Model model, HttpSession session
			,@RequestParam(value="testNo", defaultValue="0") int testNo
			,@RequestParam(value="questionIdx", defaultValue="0") int questionIdx
			,@RequestParam(value="questionTitle", defaultValue="") String questionTitle
			,@RequestParam(value="exampleIdx") int[] exampleIdx
			,@RequestParam(value="exampleTitle") String[] exampleTitle
			,@RequestParam(value="exampleOx") String[] exampleOx) {
		
		System.out.println("testNo : " + testNo);
		System.out.println("questionIdx : " + questionIdx);
		System.out.println("questionTitle : " + questionTitle);
		
		for(int i=0; i<exampleIdx.length;i++) {
			System.out.println(i + "번째 exampleIdx" + " : " + exampleIdx[i]);
			System.out.println(i + "번째 exampleTitle" + " : " + exampleTitle[i]);
			System.out.println(i + "번째 exampleOx" + " : " + exampleOx[i]);
		}
		log.debug("위치확인");
		int result = testService.addQuesetionAndExample(testNo, questionIdx, questionTitle, exampleIdx, exampleTitle, exampleOx);
		
		if(result == 1) {
			log.debug("문제 추가 성공!");
		}
		
		return "/test/testOneForTeacher";		
	}
	
	// 시험 출력(선생, 하나의 시험)
	@GetMapping("/teacher/test/testOne")
	public String testOneForTeacher(Model model, HttpSession session
			,@RequestParam(value="testNo", defaultValue="0") int testNo) {
		
		List<Map<String, Object>> list = testService.getTestOne(testNo);
		System.out.println(list.size() +"<----listSieze");
		model.addAttribute("list", list);
		model.addAttribute("testNo", testNo);
		return "test/testOneForTeacher";
	}
	
	// 시험지 출력(학생, 결과보기)
	@GetMapping("/Student/test/testResult")
	public String testResultForStudent(Model model, HttpSession session
			,@RequestParam(value="testNo", defaultValue="0")int testNo) {
		
		// 해당 시험을 응시했는 지 확인
		
		//
		Student loginStudent = (Student) session.getAttribute("loginStudent");
		int studentNo = loginStudent.getStudentNo();
		
		List<Map<String, Object>> list = testService.getTestResult(testNo, studentNo);
		model.addAttribute("list", list);
		model.addAttribute("testNo",testNo);
		
		return "test/testResultForStudent";	
	}
	
	// 시험지 출력(학생, 시험응시)
	@GetMapping("/student/test/testOne")
	public String testOneForStudent(Model model, HttpSession session
			,@RequestParam(value="testNo", defaultValue="0") int testNo) {
		
		List<Map<String, Object>> list = testService.getTestOne(testNo);
		model.addAttribute("list", list);
		model.addAttribute("testNo", testNo);
		return "test/testOneForStudent";
	}
		
	
	// 시험 리스트 출력 (선생)
	@GetMapping("/teacher/testList")
	public String testListForTeacher(Model model, HttpSession session
			, @RequestParam(value="teacherNo", defaultValue="0") int teacherNo
			, @RequestParam(value="studentNo", defaultValue="0") int studentNo
			, @RequestParam(value="currentPage", defaultValue="1") int currentPage
			, @RequestParam(value="rowPerPage", defaultValue="10") int rowPerPage
			, @RequestParam(value="searchWord", defaultValue="") String searchWord) {
		
	
		Teacher loginTeacher = (Teacher) session.getAttribute("loginTeacher");
		teacherNo = loginTeacher.getTeacherNo();
		
		List<Map<String, Object>> list = testService.getTestList(teacherNo, studentNo, currentPage, rowPerPage, searchWord);
		
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
	@GetMapping("/student/testList")
	public String testListForStudent(Model model, HttpSession session
			, @RequestParam(value="teacherNo", defaultValue="0") int teacherNo
			, @RequestParam(value="studentNo", defaultValue="0") int studentNo
			, @RequestParam(value="currentPage", defaultValue="1") int currentPage
			, @RequestParam(value="rowPerPage", defaultValue="10") int rowPerPage
			, @RequestParam(value="searchWord", defaultValue="") String searchWord) {	
		
		Student loginStudent = (Student) session.getAttribute("loginStudent");
		studentNo = loginStudent.getStudentNo();
		
		List<Map<String, Object>> list = testService.getTestList(teacherNo, studentNo, currentPage, rowPerPage, searchWord);
		
		model.addAttribute("list", list); // request.setAttribute("list", list) 기능 (매개변수 model 필요)
		model.addAttribute("teacherNo", teacherNo);
		model.addAttribute("studentNo", studentNo);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("rowPerPage", rowPerPage);
		model.addAttribute("searchWord", searchWord);
		
		Map<String, Object> map = testService.getTestCount(teacherNo, currentPage, rowPerPage, searchWord);
		model.addAttribute("map", map);
		
		return "test/testListForStudent";
	}
}
