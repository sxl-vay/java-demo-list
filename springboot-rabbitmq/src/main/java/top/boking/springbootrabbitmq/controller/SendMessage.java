package top.boking.springbootrabbitmq.controller;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 发送延迟消息
 */
@Slf4j
@RestController
@RequestMapping("/ttl")
public class SendMessage {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/sendMsg/{message}")
    public void sendMsg(@PathVariable String message) {
        log.info("当前时间:{}发送一条消息给ttl队列{}", new Date().toString(), message);
        rabbitTemplate.convertAndSend("X", "XA", "消息来自ttl为10秒" + message);
        rabbitTemplate.convertAndSend("X", "XB", "消息来自ttl为40秒" + message);
    }

    @GetMapping("/test")
    public String test() {
        return "ok";
    }

    @GetMapping("/sendMsg/{message}/{ttl}")
    public void sendMsgTtl(@PathVariable String message, @PathVariable String ttl) {

        log.info("当前时间:{}发送一条消息给ttl队列{}ttl为{}", new Date().toString(), message, ttl);

        rabbitTemplate.convertAndSend("X", "XC", message, msg ->
                {
                    msg.getMessageProperties().setExpiration(ttl);
                    return msg;
                }
        );
    }
}
