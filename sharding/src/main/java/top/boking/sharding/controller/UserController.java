package top.boking.sharding.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.boking.sharding.entity.User;
import top.boking.sharding.service.UserService;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author xub
 * @Description: 接口测试
 * @date 2019/8/24 下午6:31
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 模拟插入数据
     */
    List<User> userList = Lists.newArrayList();

    /**
     * 初始化插入数据
     */
    @PostConstruct
    private void getData() {
        userList.add(new User(1L, "小小", "女", 3));
        userList.add(new User(2L, "爸爸", "男", 30));
        userList.add(new User(3L, "妈妈", "女", 28));
        userList.add(new User(4L, "爷爷", "男", 64));
        userList.add(new User(5L, "奶奶", "女", 62));
    }

    /**
     * @Description: 批量保存用户
     */
    @GetMapping("/save-user")
    public Object saveUser() {
        return userService.insertForeach();
    }

    /**
     * @Description: 获取用户列表
     */
    @GetMapping("/listall")
    public Object listUser() {
        return userService.list();
    }

    @GetMapping("/list")
    public Map<String, Object> listpage(@RequestParam("index") Integer index, @RequestParam("size") Integer size) {
        Map<String, Object> map = userService.listpage(index, size);
        return map;
    }


    @GetMapping("/listorder")
    public List<User> listpageorder(@RequestParam("index") Integer index, @RequestParam("size") Integer size, @RequestParam(value = "order", defaultValue = "id") String order, @RequestParam(value = "type", defaultValue = "ase") String type) {

        Map<String, Object> map = new HashMap<>();
        map.put("index",index);
        map.put("size",size);
        map.put("order",order);
        map.put("type",type);

        List<User> users = userService.selectOrder(map);

        return users;
    }

}
