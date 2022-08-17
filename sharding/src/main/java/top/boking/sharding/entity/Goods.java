package top.boking.sharding.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author shxl
 * @data 2022/8/17 15:33
 **/
@Data
@AllArgsConstructor

public class Goods {
    private Long orderId;
    private String name;
    private Integer count;
    private Long id;

    public Goods() {
    }

    public Goods(Long orderId) {
        this.orderId = orderId;
    }

    public Goods(Long orderId, Integer count) {
        this.orderId = orderId;
        this.count = count;
    }
}
