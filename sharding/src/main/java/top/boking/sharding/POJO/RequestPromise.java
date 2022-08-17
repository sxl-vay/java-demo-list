package top.boking.sharding.POJO;

public class RequestPromise {
    @Override
    public String toString() {
        return "RequestPromise{" +
                "UserRequestEntity=" + UserRequestEntity +
                ", result=" + result +
                '}';
    }

    private top.boking.sharding.POJO.UserRequestEntity UserRequestEntity;
    private Result result;

    public UserRequestEntity getUserRequestEntity() {
        return UserRequestEntity;
    }

    public void setUserRequestEntity(UserRequestEntity UserRequestEntity) {
        this.UserRequestEntity = UserRequestEntity;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public RequestPromise(UserRequestEntity UserRequestEntity) {
        this.UserRequestEntity = UserRequestEntity;
    }

    public RequestPromise(UserRequestEntity UserRequestEntity, Result result) {
        this.UserRequestEntity = UserRequestEntity;
        this.result = result;
    }
}
