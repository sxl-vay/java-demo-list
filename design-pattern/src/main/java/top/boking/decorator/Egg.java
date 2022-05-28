package top.boking.decorator;

/**
 * 鸡蛋类（具体的装饰着）
 */
public class Egg extends Garnish{

    public Egg(FastFood fastFood) {
        super(1, "鸡蛋", fastFood);
    }

    public float cost() {
        return getPrice()+getFastFood().getPrice();
    }
    public String getDesc(){
        return super.getDesc()+getFastFood().getDesc();
    }
}
