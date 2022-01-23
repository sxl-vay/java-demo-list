package POJO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @Classname OptionsEntity
 * @Description TODO
 * @Date 2022/1/22 9:39
 * @Created by shxl
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OptionsEntity {
    private Integer start;
    private Integer end;
    private String cookie;
    private Map<String, Object> options;

}
