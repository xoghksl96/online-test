package goodee.gdj58.online.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import goodee.gdj58.online.mapper.StudentMapper;
import goodee.gdj58.online.vo.Student;
import lombok.extern.slf4j.Slf4j;

@Slf4j // static Log log = new Log() 로그 객체를 선언하여 사용할 수 있게 함
@Service
@Transactional
public class StudentService {
	@Autowired
	private StudentMapper studentMapper;
		
	public int removeStudent(int studentNo) {
		return studentMapper.deleteStudent(studentNo);
	}
	
	public int addStudent(Student student) {
		return studentMapper.insertStudent(student);
	}
	
	public List<Student> getStudentList(int currentPage, int rowPerPage, String searchWord) {
		int beginRow = (currentPage-1) * rowPerPage;
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("beginRow", beginRow);
		paramMap.put("rowPerPage", rowPerPage);
		paramMap.put("searchWord", searchWord);
		
		return studentMapper.selectStudentList(paramMap);
	}
	
	public Map<String, Object> getStudentCount(int currentPage, int rowPerPage, String searchWord) {
		int studentCount = studentMapper.selectStudentCount(searchWord);
		log.debug("\u001B[31m" + studentCount + "<-- studentCount");
		
		int startPage = currentPage / 10 * 10 + 1;
		
		int endPage = currentPage / 10 * 10 + 10;

		int lastPage = studentCount / 10;
		if(lastPage % 10 != 0 || lastPage == 0) {
			lastPage++;
		}
		
		if(endPage > lastPage) {
			endPage = lastPage;
		}
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("startPage", startPage);
		resultMap.put("endPage", endPage);
		resultMap.put("lastPage", lastPage);
		
		return resultMap;
		
	}
}
