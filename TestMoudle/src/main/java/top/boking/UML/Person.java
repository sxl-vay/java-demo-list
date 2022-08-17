package top.boking.UML;

import com.sun.xml.internal.bind.v2.model.core.ID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author shxl
 * @data 2022/6/16 22:12
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private String userName;
    private Integer age;
    private String ID;

}