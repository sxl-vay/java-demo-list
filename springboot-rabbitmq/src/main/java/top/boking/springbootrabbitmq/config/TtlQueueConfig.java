package top.boking.springbootrabbitmq.config;


import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.HashMap;
@EnableAspectJAutoProxy
@Configuration
public class TtlQueueConfig {
    //普通交换机Container
    public static final String X_EXCHANGE = "X";
    //死信交换机
    public static final String Y_DEAD_LETTER_EXCHANGE = "Y";
    //普通队列名称
    public static final String A_QUEUE = "QA";
    public static final String B_QUEUE = "QB";
    public static final String C_QUEUE = "QC";
    //死信队列名称
    public static final String DEAE_LETTER_QUEUE = "QD";

    @Bean("xExchange")
    public DirectExchange xExchange() {

        return new DirectExchange(X_EXCHANGE);
    }

    @Bean("yExchange")
    public DirectExchange yExchange() {

        return new DirectExchange(Y_DEAD_LETTER_EXCHANGE);
    }

    @Bean("queueA")
    public Queue queueA() {
        HashMap<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", Y_DEAD_LETTER_EXCHANGE);
        args.put("x-dead-letter-routing-key", "YD");
        args.put("x-message-ttl", 10000);

        return QueueBuilder.durable(A_QUEUE).withArguments(args).build();
    }

    @Bean("queueB")
    public Queue queueB() {
        HashMap<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", Y_DEAD_LETTER_EXCHANGE);
        args.put("x-dead-letter-routing-key", "YD");
        args.put("x-message-ttl", 20000);

        return QueueBuilder.durable(B_QUEUE).withArguments(args).build();
    }

    @Bean("queueC")
    public Queue queueC() {
        HashMap<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", Y_DEAD_LETTER_EXCHANGE);
        args.put("x-dead-letter-routing-key", "YD");

        return QueueBuilder.durable(C_QUEUE).withArguments(args).build();
    }

    @Bean("queueD")
    public Queue queueD() {
        return QueueBuilder.durable(DEAE_LETTER_QUEUE).build();
    }

    @Bean
    public Binding queueABingdingX(@Qualifier("queueA") Queue queueA,
                                   @Qualifier("xExchange") DirectExchange xExchange) {
        return BindingBuilder.bind(queueA).to(xExchange).with("XA");

    }

    @Bean
    public Binding queueBBingdingX(@Qualifier("queueB") Queue queueB,
                                   @Qualifier("xExchange") DirectExchange xExchange) {
        return BindingBuilder.bind(queueB).to(xExchange).with("XB");

    }

    @Bean
    public Binding queueDBingdingY(@Qualifier("queueD") Queue queueD,
                                   @Qualifier("yExchange") DirectExchange yExchange) {
        return BindingBuilder.bind(queueD).to(yExchange).with("YD");

    }
    @Bean
    public Binding queueCBingdingX(@Qualifier("queueC") Queue queueD,
                                   @Qualifier("xExchange") DirectExchange yExchange) {
        return BindingBuilder.bind(queueD).to(yExchange).with("XC");

    }


}
