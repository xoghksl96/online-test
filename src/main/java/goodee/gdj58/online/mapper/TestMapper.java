package goodee.gdj58.online.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestMapper {
	
	// 학생이 응시한 시험만 조회
	List<Map<String, Object>> selectCompleteTest(int studentNo);
	// 한 개의 테스트 조회
	List<Map<String, Object>> selectTestOne(int testNo);
	// 학생이 응시한 시험 개수 조회
	int selectCompleteTestCount(int studentNo);
	// 모든 시험 개수 조회
	int selectTestCount(Map<String, Object> paramMap);
	// 문제 리스트 조회 (문제정보 + 선생이름)
	List<Map<String, Object>> selectTestList(Map<String, Object> paramMap);
}
