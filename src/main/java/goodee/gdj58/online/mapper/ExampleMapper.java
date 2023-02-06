package goodee.gdj58.online.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import goodee.gdj58.online.vo.Example;

@Mapper
public interface ExampleMapper {
	List<Example> selectExampleList(int questionNo);
}
