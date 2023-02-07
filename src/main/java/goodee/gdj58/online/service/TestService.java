package goodee.gdj58.online.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import goodee.gdj58.online.mapper.ExampleMapper;
import goodee.gdj58.online.mapper.QuestionMapper;
import goodee.gdj58.online.mapper.TestMapper;
import goodee.gdj58.online.vo.Example;
import goodee.gdj58.online.vo.Question;
import lombok.extern.slf4j.Slf4j;

@Slf4j // static Log log = new Log() 로그 객체를 선언하여 사용할 수 있게 함
@Service
@Transactional
public class TestService {
	
	@Autowired private TestMapper testMapper;
	@Autowired private QuestionMapper questionMapper;
	@Autowired private ExampleMapper exampleMapper;
	
	public Map<String,Object> getQuestionAndExample(int testNo, int questionNo){
		
		Question paramQuestion = new Question();
		paramQuestion.setTestNo(testNo);
		paramQuestion.setQuestionNo(questionNo);
		Question question = questionMapper.selectQuestionOne(paramQuestion);
		
		Example paramExample = new Example();
		paramExample.setQuestionNo(questionNo);
		List<Example> exampleList = exampleMapper.selectExampleList(questionNo);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("question", question);
		resultMap.put("exampleList", exampleList);
		
		return resultMap;
	}
	
	public void modifyQuestionAndExample (int testNo
			, int questionNo
			, String questionTitle
			, int[] exampleNo
			, String[] exampleTitle
			, String[] exampleOx) {
		
	}
	
	public void removeQuestionAndExample (int questionNo) {
		
		int removeExample = exampleMapper.deleteExample(questionNo);
		int removeQuestion = questionMapper.deleteQuestion(questionNo);
		
		if(removeExample == 1 && removeQuestion == 1) {
			log.debug("보기, 질문 삭제 성공");
		}
	}
	
	public int addQuesetionAndExample (int testNo, int questionIdx, String questionTitle, int[] exampleIdx, String[] exampleTitle, String[] exampleOx) {
		
		int addQuestion = 0;
		int addExampleCount = 0;
		
		Map<String, Object> questionMap = new HashMap<String, Object>();
		questionMap.put("testNo", testNo);
		questionMap.put("questionIdx", questionIdx);
		questionMap.put("questionTitle", questionTitle);
		
		int questionNo = 0;
		
		questionMapper.insertQuestion(questionMap);
		questionNo = (int) questionMap.get("questionNo");
		
		if(questionNo != 0) {
			addQuestion = 1;
		} else {
			return 0;
		}
		
		for(int i=0; i<exampleIdx.length; i++) {
			Map<String, Object> exampleMap = new HashMap<String, Object>();
			exampleMap.put("questionNo", questionNo);
			exampleMap.put("exampleIdx", exampleIdx[i]);
			exampleMap.put("exampleTitle", exampleTitle[i]);
			exampleMap.put("exampleOx", exampleOx[i]);
			
			addExampleCount += exampleMapper.insertExample(exampleMap);
		}
		
		if(addQuestion == 1 && addExampleCount == exampleIdx.length) {
			return 1;
		} else {
			return 0;
		}
	}
	public List<Map<String, Object>> getTestOne (int testNo) {
		List<Question> questionList = null;	// 문제 List
		List<Example> exampleList = null;	// 보기 List
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		questionList = questionMapper.selectQuestionList(testNo);
		
		for(int i = 0; i<questionList.size(); i++) {
			exampleList = exampleMapper.selectExampleList(questionList.get(i).getQuestionNo());
			
			Map<String,Object> resultMap = new HashMap<String, Object>();
			resultMap.put("questionList", questionList.get(i));
			resultMap.put("exampleList", exampleList);
			
			list.add(resultMap);
		}
		
		return list;
	}
	
	public List<Map<String, Object>> getTestList (int teacherNo, int currentPage,int rowPerPage,String searchWord) {
		int beginRow = (currentPage-1) * rowPerPage;
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("teacherNo", teacherNo);
		paramMap.put("beginRow", beginRow);
		paramMap.put("rowPerPage", rowPerPage);
		paramMap.put("searchWord", searchWord);
		
		return testMapper.selectTestList(paramMap);
	}
	
	public Map<String, Object> getTestCount (int teacherNo, int currentPage,int rowPerPage,String searchWord) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("teacherNo", teacherNo);
		paramMap.put("searchWord", searchWord);
		
		int testCount = testMapper.selectTestCount(paramMap);
		
		int startPage = currentPage / 10 * 10 + 1;
		int endPage = currentPage / 10 * 10 + 10;
		if(currentPage % 10 == 0) { // 페이지 일의 자리 숫자가 0일경우
			startPage = startPage - 10;			
			endPage = endPage - 10;
		}
		
		int lastPage = testCount / rowPerPage;
		if(lastPage % rowPerPage != 0  || lastPage == 0) {
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
