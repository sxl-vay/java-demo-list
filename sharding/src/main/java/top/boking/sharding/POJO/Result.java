package top.boking.sharding.POJO;

public class Result{
    private Boolean success;
    private String msg;

    @Override
    public String toString() {
        return "Result{" +
                "success=" + success +
                ", msg='" + msg + '\'' +
                '}';
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Result(Boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }
}