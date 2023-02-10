package goodee.gdj58.online.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import goodee.gdj58.online.vo.Paper;

@Mapper
public interface PaperMapper {
	List<Paper> selectAnswer(Map<String, Object> paramMap);
	int selectCorrectAnswer(Map<String, Object> paramMap);
	int insertPaper(Map<String, Object> paramMap);
}
