package top.boking.sharding.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author shxl
 * @data 2022/7/22 22:03
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Addr {
    private Long id;

    private String addr;
    private String tip;

}
