package top.boking.sharding.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.boking.sharding.entity.User;
import top.boking.sharding.mapper.UserMapper;
import top.boking.sharding.service.UserService;

import java.util.*;


/**
 * @author xub
 * @Description: 用户实现类
 * @date 2019/8/8 上午9:13
 *
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public  List<User> list() {
        List<User> users = userMapper.selectAll();
        return users;
    }

    @Override
    public String insertForeach() {
        Random random = new Random();
        List<User> users= new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            int i1 = random.nextInt(10);
            int i2 = random.nextInt(10);
            int i3 = random.nextInt(100);
            User user = new User((long)i1, "randon" + i, i2 / 2 == 0 ? "男": "女", i3);
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());
            user.setStatus(0);
            users.add(user);

        }

        int i = userMapper.insertForeach(users);
        return i>=0?"保存成功":"保存失败";
    }

    @Override
    public Map<String, Object> listpage(Integer index, Integer size) {
        PageHelper.startPage(index,size);
        List<User> list = userMapper.selectAll();
        PageInfo<User> userPageInfo = new PageInfo<>(list);
        long total = userPageInfo.getTotal();
        Map<String, Object> map = new HashMap<>();
        map.put("total",total);
        map.put("data",userPageInfo.getList());
        return map;
    }

    public List<User> selectOrder(Map<String,Object> map) {

        int index =(int) map.get("index");
        int size = (int)map.get("size");
        PageHelper.startPage(index,size);
        List<User> list = userMapper.selectOrder(map);
        PageInfo<User> userPageInfo = new PageInfo<>(list);
        long total = userPageInfo.getTotal();

        return userPageInfo.getList();


    }
}
