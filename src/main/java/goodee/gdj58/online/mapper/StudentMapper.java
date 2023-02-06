package goodee.gdj58.online.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import goodee.gdj58.online.vo.Student;
import goodee.gdj58.online.vo.Teacher;

@Mapper
public interface StudentMapper {
	
	// ======== student 접근 ========	
	// 학생로그인
	Student login(Student student);
		
		
	// ======== employee 접근 ========	
	// 학생 탈퇴
	int deleteStudent(int studentNo);
	// 학생 가입
	int insertStudent(Student student);
	// 총 학생인원 조회
	int selectStudentCount(String searchWord);
	// 학생 리스트 조회
	List<Student> selectStudentList(Map<String, Object> paramMap);

}
