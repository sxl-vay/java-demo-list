package top.boking.sharding.POJO;

/**
 * @author shxl
 * @data 2022/8/17 15:05
 **/
public class UserRequestEntity {
    private Long orderId;
    private Long userId;
    private Integer count;

    public UserRequestEntity() {
    }

    public UserRequestEntity(Long orderId, Long userId, Integer count) {
        this.orderId = orderId;
        this.userId = userId;
        this.count = count;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
