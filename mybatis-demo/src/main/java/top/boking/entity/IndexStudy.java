package top.boking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author shxl
 * @data 2022/7/14 20:22
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IndexStudy {
    private Long id;
    private String name;
    private String a;
    private String b;
    private String c;
    private Integer age;
    private Date createTime;
    private Character char1;

    public IndexStudy(String name, String a, String b, String c) {
        this.name = name;
        this.a = a;
        this.b = b;
        this.c = c;
    }
}
