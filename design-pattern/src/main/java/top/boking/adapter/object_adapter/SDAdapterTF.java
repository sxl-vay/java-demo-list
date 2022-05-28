package top.boking.adapter.object_adapter;

public class SDAdapterTF implements SDCard {

    //声明一个适配者类
    private TFCard tfCard;

    public SDAdapterTF(TFCard tfCard) {
        this.tfCard = tfCard;
    }

    @Override
    public String redaSD() {
        System.out.println("adapter read tf card");
        return tfCard.redaTF();
    }

    @Override
    public void writeSD(String msg) {
        System.out.println("adapter write tf card");
        tfCard.writeTF(msg);
    }
}
