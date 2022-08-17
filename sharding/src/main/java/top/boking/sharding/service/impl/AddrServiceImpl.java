package top.boking.sharding.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.boking.sharding.entity.Addr;
import top.boking.sharding.mapper.AddrMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author shxl
 * @data 2022/7/22 22:18
 **/
@Service
public class AddrServiceImpl {
    @Autowired
    private AddrMapper mapper;

    public Map<String,Object> getListPage(int page, int pageSize) {
        PageHelper.startPage(page,pageSize);
        List<Addr> all = mapper.getAll();
        PageInfo<Addr> addrPageInfo = new PageInfo<>(all);
        Map<String,Object> result = new HashMap<>();
        result.put("data",addrPageInfo.getList());
        result.put("total",addrPageInfo.getTotal());
        return result;
    }

    public boolean insertBatch(List<Addr> addrs) {
        return mapper.insert(addrs);
    }

    public boolean remove(String addrname) {
        return mapper.remove(addrname);
    }

}
