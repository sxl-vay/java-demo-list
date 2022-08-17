package top.boking.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.boking.entity.IndexStudy;

import java.util.List;

/**
 * @author shxl
 * @data 2022/7/14 20:30
 **/
@Mapper
@Repository
public interface IndexStudyMapper {
    List<IndexStudy> selectAll();

    Boolean insertStudy(IndexStudy indexStudy);

    List<IndexStudy> queryByPage(@Param("start") int start, @Param("pageSize") int pageSize, @Param("name") String name);

    List<IndexStudy> queryByPageHelper();

    Boolean insertStudys(List<IndexStudy> studies);
    Boolean deleteCase(IndexStudy study);
}
