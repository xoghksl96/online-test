package goodee.gdj58.online.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import goodee.gdj58.online.vo.Question;

@Mapper
public interface QuestionMapper {
	int insertQuestion(int testNo, int )
	List<Question> selectQuestionList(int testNo);
}
