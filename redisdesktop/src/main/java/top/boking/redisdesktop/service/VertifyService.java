package top.boking.redisdesktop.service;


import top.boking.redisdesktop.entity.Token;
import top.boking.redisdesktop.entity.User;

public interface VertifyService {
     User find(User s);
     Token vetify(User user);


}
