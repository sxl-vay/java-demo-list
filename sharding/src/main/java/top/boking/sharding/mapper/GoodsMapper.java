package top.boking.sharding.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.boking.sharding.entity.Goods;

import java.util.List;

/**
 * @author shxl
 * @data 2022/8/17 15:03
 **/
@Mapper
public interface GoodsMapper {

    List<Goods> listAll();

    List<Goods> listByCondition(Goods goods);

    Boolean reduceCountByCondition(Goods goods);



}
