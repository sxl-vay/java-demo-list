package top.boking.sharding.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.boking.sharding.entity.Addr;

import java.util.List;

@Mapper
public interface AddrMapper {
    boolean insert(List<Addr> addrs);
    List<Addr> getAll();
    boolean remove(String addrname);
}
