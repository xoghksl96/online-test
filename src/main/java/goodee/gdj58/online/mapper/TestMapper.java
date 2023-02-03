package goodee.gdj58.online.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestMapper {
	
	// 총 문제회차 조회
	int selectTestCount(Map<String, Object> paramMap);
	// 문제 리스트 조회 (문제정보 + 선생이름)
	List<Map<String, Object>> selectTestList(Map<String, Object> paramMap);
}
