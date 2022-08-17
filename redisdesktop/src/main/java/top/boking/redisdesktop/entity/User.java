package top.boking.redisdesktop.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author shxl
 * @data 2022/6/3 10:00
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data

public class User implements Serializable {
    private static final long serialVersionUID = 1L;


    private Integer id;
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
