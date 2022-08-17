package top.boking.redisdesktop.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author shxl
 * @data 2022/6/3 10:48
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
//@TableName("token")

public class Token {
    //@TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer userid;
    private String token;

    public Token(String token) {
        this.token = token;
    }

    public Token(Integer userid, String token) {
        this.userid = userid;
        this.token = token;
    }
}
