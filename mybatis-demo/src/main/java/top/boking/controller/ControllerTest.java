package top.boking.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.boking.mapper.IndexStudyMapper;
import top.boking.entity.IndexStudy;
import top.boking.service.IndexStudyService;

import java.util.*;

/**
 * @author shxl
 * @data 2022/7/14 20:41
 **/
@RestController
public class ControllerTest {
    @Autowired
    private IndexStudyMapper mapper;
    @Autowired
    private IndexStudyService service;

    @GetMapping("/get")
    public List get() {
        List<IndexStudy> indexStudies = mapper.selectAll();

        return indexStudies;

    }


    @GetMapping("/getPagehelper/{page}/{pageSize}")
    public Map<String, Object> getPagehelper(@PathVariable("page") Integer page,@PathVariable("pageSize") Integer pageSize) {

        Map<String, Object> info = service.queryByPageHelper(page, pageSize);

        return info;

    }

    @GetMapping("/getPage/{pageindex}")
    public List getPage(@PathVariable("pageindex") Integer pageindex) {

        List indexStudies = mapper.queryByPage((pageindex-1)*10,10,"zsr%");

        return indexStudies;

    }

    @GetMapping("/insert")
    public Boolean insert(@RequestParam String name, @RequestParam String a, @RequestParam String b, @RequestParam String c) {
        IndexStudy indexStudy = new IndexStudy(name, a, b, c);
        Boolean aBoolean = mapper.insertStudy(indexStudy);

        return aBoolean;
    }

    @GetMapping("/insertBatch")
    public Boolean insertBatch(@RequestParam Integer count, @RequestParam String prefix) {
        Boolean aBoolean = false;
        for (Integer i = 0; i < count; i++) {
            IndexStudy indexStudy = new IndexStudy(prefix + i, prefix + "a" + i, prefix + "b" + i, prefix + "c" + i);
            indexStudy.setCreateTime(new Date());
            indexStudy.setChar1('z');
            aBoolean = mapper.insertStudy(indexStudy);
        }

        return aBoolean;
    }
    @GetMapping("/insertBatchByone")
    public Boolean insertBatchByone(@RequestParam Integer count, @RequestParam String prefix) {
        Boolean aBoolean = false;
        List<IndexStudy> list = new ArrayList<>();
        int number = count / 1000;

        for (int n = 0; n <= number; n++) {
            for (int i = 0; i < 1000; i++) {
                int index = n*1000+i;
                IndexStudy indexStudy = new IndexStudy(prefix + index, "a" + index, "b" + index, "c" + index);
                char c= (char) i;
                indexStudy.setChar1(c);
                indexStudy.setCreateTime(new Date());
                indexStudy.setAge(i+99);
                list.add(indexStudy);
            }
            aBoolean = mapper.insertStudys(list);
        }

        return aBoolean;
    }

}
