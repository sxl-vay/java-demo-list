package top.boking.sharding.mapper;


import org.apache.ibatis.annotations.Mapper;
import top.boking.sharding.entity.User;

import java.util.List;
import java.util.Map;

/**
 * @Description: 用户mapper
 *
 * @author xub
 * @date 2019/10/8 下午9:23
 */
@Mapper
public interface UserMapper {

    /**
     * 批量插入
     *
     * @param list 插入集合
     * @return 插入数量
     */
    int insertForeach(List<User> list);

    /**
     * 获取所有用户
     */
    List<User> selectAll();


    List<User> selectOrder(Map<String,Object> map);

}