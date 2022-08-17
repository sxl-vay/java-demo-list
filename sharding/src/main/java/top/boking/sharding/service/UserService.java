package top.boking.sharding.service;


import org.springframework.web.bind.annotation.RequestParam;
import top.boking.sharding.entity.User;

import java.util.List;
import java.util.Map;

/**
 * @Description: 用户相关接口
 *
 * @author xub
 * @date 2019/8/24 下午6:32
 */
public interface UserService {

    /**
     * 获取所有用户信息
     */
    List<User> list();

    /**
     *   批量 保存用户信息
     * @param userVOList
     */
    String  insertForeach();

    Map<String,Object> listpage(Integer index, Integer size);

    List<User> selectOrder(Map<String,Object> map);
}