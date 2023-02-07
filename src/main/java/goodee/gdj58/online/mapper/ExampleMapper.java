package goodee.gdj58.online.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import goodee.gdj58.online.vo.Example;

@Mapper
public interface ExampleMapper {
	int updateExample(Example exmple);
	int deleteExample(int questionNo);
	int insertExample(Map<String, Object> paramap);
	List<Example> selectExampleList(int questionNo);
}
