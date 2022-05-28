package top.boking.adapter.class_adapter;

public class SDAdapterTF extends TFCardImpl implements SDCard {


    @Override
    public String redaSD() {
        System.out.println("adapter read tf card");
        return redaTF();
    }

    @Override
    public void writeSD(String msg) {
        System.out.println("adapter write tf card");
        writeTF(msg);
    }
}
