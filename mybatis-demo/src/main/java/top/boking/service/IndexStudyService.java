package top.boking.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.boking.entity.IndexStudy;
import top.boking.mapper.IndexStudyMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author shxl
 * @data 2022/7/16 0:45
 **/
@Service
public class IndexStudyService {
    @Autowired
    IndexStudyMapper mapper;
    //service

    public Map<String,Object> queryByPageHelper(int page, int pageSize){
        PageHelper.startPage(page, pageSize);
        List<IndexStudy> indexStudies = mapper.selectAll();
        PageInfo<IndexStudy> info = new PageInfo<>(indexStudies);
        long total = info.getTotal();
        System.out.println("total = " + total);
        int size = info.getSize();
        System.out.println("size = " + size);
        HashMap<String, Object> result = new HashMap<>();
        result.put("data",info.getList());
        result.put("total",info.getTotal());
        return result;
    }
}
