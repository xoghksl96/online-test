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

import goodee.gdj58.online.filter.EmployeeLoginFilter;
import goodee.gdj58.online.service.EmployeeService;
import goodee.gdj58.online.service.IdService;
import goodee.gdj58.online.service.StudentService;
import goodee.gdj58.online.service.TeacherService;
import goodee.gdj58.online.vo.Employee;
import goodee.gdj58.online.vo.Student;
import goodee.gdj58.online.vo.Teacher;
import lombok.extern.slf4j.Slf4j;

@Slf4j // static Log log = new Log() 로그 객체를 선언하여 사용할 수 있게 함
@Controller
public class EmployeeController {
	@Autowired EmployeeService employeeService;
	@Autowired StudentService studentService;
	@Autowired TeacherService teacherService;
	@Autowired IdService idService;
	
	// 비밀번호 수정
	@GetMapping("employee/modifyEmpPw")
	public String modifyEmpPw() {		
		return "employee/modifyEmpPw";	
	}
	
	@PostMapping("employee/modifyEmpPw")
	public String modifyEmpPw(HttpSession session
			, @RequestParam(value="oldPw") String oldPw
			, @RequestParam(value="newPw") String newPw) {
		
		
		Employee loginEmp = (Employee) session.getAttribute("loginEmp");	

		employeeService.updateEmployeePw(loginEmp.getEmpNo(), oldPw, newPw);
		
		return "redirect:/employee/empList";
		
	}
	// 로그아웃
	@GetMapping("/employee/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/employee/loginEmp";
	}
		
	// 로그인
	@GetMapping("/loginEmp")
	public String loginEmp() {
		return "employee/loginEmp";
	}
	
	@PostMapping("/loginEmp")
	public String loginEmp(HttpSession session, Employee employee) {
		Employee resultEmp = employeeService.login(employee);	// row == 1이면 입력성공
		session.setAttribute("loginEmp", resultEmp);
		return "redirect:/employee/empList";
	}
		
	// employee 삭제
	@GetMapping("/employee/removeEmp")
	public String removeEmp(@RequestParam("empNo") int empNo) {		
		employeeService.removeEmp(empNo);	// row == 1이면 삭제성공
		return "redirect:/employee/empList";
	}
	
	
	// employee 입력
	@GetMapping("/employee/addEmp")
	public String addEmp() {
		return "employee/addEmp";
	}
	
	@PostMapping("/employee/addEmp")
	public String addEmp(Model model, Employee employee) {
		
		String id = idService.getIdCheck(employee.getEmpId());
		if(id != null) { // 입력한 ID가 기존 DB에 존재 시, 다시 회원가입 페이지로 이동
			model.addAttribute("errorMsg", "중복된 ID");
			return "employee/addEmp";
		}
		
		int row = employeeService.addEmp(employee);
		if(row != 1) { // row != 1이면 입력실패
			model.addAttribute("errorMsg", "시스템 에러로 인한 실패");
			return "employee/addEmp";
		}
		return "redirect:/employee/empList"; // sendRedict , cm
	}
	
	
	// EmpList 출력
	@GetMapping("/employee/empList")
	public String empList(Model model
			, @RequestParam(value="currentPage", defaultValue="1") int currentPage
			, @RequestParam(value="rowPerPage", defaultValue="10") int rowPerPage
			, @RequestParam(value="searchWord", defaultValue="") String searchWord) {
			// int currentPage = request.getParameter("currentPage");
		
		log.debug("\u001B[31m" + currentPage + "<-- currentPage");
		log.debug("\u001B[31m" + rowPerPage + "<-- rowPerPage");
		log.debug("\u001B[31m" + searchWord + "<-- searchWord");
		
		List<Employee> list = employeeService.getEmployeeList(currentPage,rowPerPage,searchWord);
		
		model.addAttribute("list", list); // request.setAttribute("list", list) 기능 (매개변수 model 필요)
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("searchWord", searchWord);
		
		Map<String, Object> map = employeeService.getEmployeecount(currentPage, rowPerPage, searchWord);
		log.debug("\u001B[31m" + map.get("startPage") + "<-- startPage");
		log.debug("\u001B[31m" + map.get("endPage") + "<-- endPage");
		log.debug("\u001B[31m" + map.get("lastPage") + "<-- lastPage");
		model.addAttribute("map", map);
		
		return "employee/empList";
	}
	
	
}
