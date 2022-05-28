package top.boking.adapter.class_adapter;

public class SDCardImpl implements SDCard{
    @Override
    public String redaSD() {
        String msg = "SDCard read msg: hello world SDCard";

        return msg;
    }

    @Override
    public void writeSD(String msg) {
        System.out.println("SDCard write msg:"+msg);
    }
}
