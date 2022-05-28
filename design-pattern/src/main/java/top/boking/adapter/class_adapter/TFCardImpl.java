package top.boking.adapter.class_adapter;

public class TFCardImpl implements TFCard{


    @Override
    public String redaTF() {
        String msg = "TFCard read msg: hello world TFCard";

        return msg;
    }

    @Override
    public void writeTF(String msg) {
        System.out.println("TFCard write msg:"+msg);
    }
}
