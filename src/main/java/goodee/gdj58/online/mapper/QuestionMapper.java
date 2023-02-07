package goodee.gdj58.online.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import goodee.gdj58.online.vo.Question;

@Mapper
public interface QuestionMapper {
	int updateQuestion(Question question);
	int deleteQuestion(int questionNo);
	int insertQuestion(@Param("paramap") Map<String, Object> paramap);
	Question selectQuestionOne(Question question);
	List<Question> selectQuestionList(int testNo);
}
