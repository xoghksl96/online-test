package goodee.gdj58.online.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import goodee.gdj58.online.mapper.ExampleMapper;
import goodee.gdj58.online.mapper.PaperMapper;
import goodee.gdj58.online.mapper.QuestionMapper;
import goodee.gdj58.online.mapper.ScoreMapper;
import goodee.gdj58.online.mapper.TestMapper;
import goodee.gdj58.online.vo.Example;
import goodee.gdj58.online.vo.Paper;
import goodee.gdj58.online.vo.Question;
import lombok.extern.slf4j.Slf4j;

@Slf4j // static Log log = new Log() 로그 객체를 선언하여 사용할 수 있게 함
@Service
@Transactional
public class TestService {
	
	@Autowired private TestMapper testMapper;
	@Autowired private QuestionMapper questionMapper;
	@Autowired private ExampleMapper exampleMapper;
	@Autowired private PaperMapper paperMapper;
	@Autowired private ScoreMapper scoreMapper;
	
	// 응시한 시험 답안과 비교하기
	public List<Map<String, Object>> getgetCompleteTestResult(int testNo, int studentNo){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("testNo", testNo);
		paramMap.put("studentNo", studentNo);
		
		List<Question> questionList = questionMapper.selectQuestionList(testNo);
		List<Paper> paperList = paperMapper.selectAnswer(paramMap);
		for(int i = 0; i<questionList.size(); i++) {
			// 문제 번호 가져오기
			int questionNo = questionList.get(i).getQuestionNo();
			
			// 문제에 해당하는 보기 가져오기
			List<Example> exampleList = exampleMapper.selectExampleList(questionNo);
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("question", questionList.get(i)); // 한 문제에 대한 정보
			map.put("paper", paperList.get(i)); // 한 문제의 제출한 답안에 대한 정보
			map.put("exampleList", exampleList); // 한 문제의 보기에 대한 정보
			
			list.add(map);
		}		
		return list;
	}
	
	// 학생이 응시한 시험목록 조회
	public List<Map<String, Object>> getCompleteTest (int studentNo){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		list = testMapper.selectCompleteTest(studentNo);
		
		return list;		
	}
	
	// 시험지 제출 / 채점
	public void addPapper(int testNo, int studentNo, int[] questionNo, int[] answer) {
		
		// 시험지 제출
		for(int i = 0; i<questionNo.length; i++) {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			
			paramMap.put("testNo", testNo);
			paramMap.put("studentNo", studentNo);
			paramMap.put("questionNo", questionNo[i]);
			paramMap.put("answer", answer[i]);
			
			int result = paperMapper.insertPaper(paramMap);
			
			if(result != 1) {
				log.debug("입력 실패 시스템을 중단합니다.");
			}
		}
		
		// 채점
		final int MAX_SCORE = 100; // 문제를 모두 맞혔을 시 점수
		int scoreSum = 0; // 최종 점수
		int scoreOne = MAX_SCORE / questionNo.length;
		
		// 점수계산
		Map<String, Object> selectParamMap = new HashMap<String, Object>();
		selectParamMap.put("testNo", testNo);
		selectParamMap.put("studentNo", studentNo);
		
		int correctAnswerCount = paperMapper.selectCorrectAnswer(selectParamMap);
		
		if(questionNo.length == correctAnswerCount) {
			scoreSum = 100;
		} else {
			scoreSum = (int) (scoreOne * correctAnswerCount);
		}
		
		// 계산된 점수 insert
		Map<String, Object> insertParamMap = new HashMap<String, Object>();
		insertParamMap.put("testNo", testNo);
		insertParamMap.put("studentNo", studentNo);
		insertParamMap.put("score", scoreSum);
		int resultAddScore = scoreMapper.insertScore(insertParamMap);
		
		if(resultAddScore == 1) {
			log.debug("점수 등록 성공");
		}
	}
	
	// 문제와 보기 가져오기
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
	
	// 문제와 보기 수정
	public void modifyQuestionAndExample (int testNo
			, int questionNo
			, String questionTitle
			, int[] exampleNo
			, String[] exampleTitle
			, String[] exampleOx) {
		
	}
	
	// 문제와 보기 삭제
	public void removeQuestionAndExample (int questionNo) {
		
		int removeExample = exampleMapper.deleteExample(questionNo);
		int removeQuestion = questionMapper.deleteQuestion(questionNo);
		
		if(removeExample == 1 && removeQuestion == 1) {
			log.debug("보기, 질문 삭제 성공");
		}
	}
	
	// 문제와 보기 등록
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
	
	// 시험 결과 가져오기
	public List<Map<String, Object>> getTestResult(int testNo, int studentNo) {
		List<Question> questionList = null;	// 문제 List
		List<Example> exampleList = null;	// 보기 List
		List<Paper> paperList = null;
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		// 문제 List 가져오기
		questionList = questionMapper.selectQuestionList(testNo);
		
		// 학생이 제출한 답 가져오기
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("testNo", testNo);
		paramMap.put("StudentNo", studentNo);
		
		paperList =  paperMapper.selectAnswer(paramMap);
		
		// 해당 문제의 보기 가져오기
		for(int i = 0; i<questionList.size(); i++) {
			exampleList = exampleMapper.selectExampleList(questionList.get(i).getQuestionNo());
			
			Map<String,Object> resultMap = new HashMap<String, Object>();
			resultMap.put("questionList", questionList.get(i));
			resultMap.put("paperList", paperList.get(i));
			resultMap.put("exampleList", exampleList);
			
			list.add(resultMap);
		}				
		
		return list;
	}
	
	// 한 종목 시험의 문제와 보기 가져오기
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
	
	// 시험리스트 가져오기
	public List<Map<String, Object>> getTestList (int teacherNo, int studentNo, int currentPage,int rowPerPage,String searchWord) {
		int beginRow = (currentPage-1) * rowPerPage;
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("teacherNo", teacherNo);
		paramMap.put("studentNo", studentNo);
		paramMap.put("beginRow", beginRow);
		paramMap.put("rowPerPage", rowPerPage);
		paramMap.put("searchWord", searchWord);
		
		List list= testMapper.selectTestList(paramMap);
		System.out.println(list.get(0));
		System.out.println(list.get(1));
		return testMapper.selectTestList(paramMap);
	}
	
	// 응시한 시험 개수 세기
	public int getCompleteTestCount(int studentNo) {
		int completeTestCount = testMapper.selectCompleteTestCount(studentNo);
		
		return completeTestCount;
	}
	
	// 시험 총 개수를 세어 첫,마지막 페이지 정보 가져오기
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
		resultMap.put("testCount", testCount);
		resultMap.put("startPage", startPage);
		resultMap.put("endPage", endPage);
		resultMap.put("lastPage", lastPage);
		
		return resultMap;	
	}
}
