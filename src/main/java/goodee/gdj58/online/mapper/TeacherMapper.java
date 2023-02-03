package goodee.gdj58.online.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import goodee.gdj58.online.vo.Teacher;
import goodee.gdj58.online.vo.Test;

@Mapper
public interface TeacherMapper {
	
	// ======== teacher 접근 ========	
	// 선생로그인(teacher 접근)
	Teacher login(Teacher teacher);
	
	
	// ======== employee 접근 ========	
	// 선생 탈퇴
	int deleteTeacher(int teacherNo);
	// 선생 가입
	int insertTeacher(Teacher teacher);
	// 총 선생인원 조회
	int selectTeacherCount(String searchWord);
	// 선생 리스트 조회
	List<Teacher> selectTeacherList(Map<String, Object> paramMap);
}
