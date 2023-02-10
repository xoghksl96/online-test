package goodee.gdj58.online.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ScoreMapper {
	int selectScore(Map<String,Object> paramMap);
	int insertScore(Map<String,Object> paramMap);
}
