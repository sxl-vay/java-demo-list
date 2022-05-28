package top.boking.decorator;

/**
 * 培根类（具体的装饰着）
 */
public class Bacon extends Garnish{

    public Bacon(FastFood fastFood) {
        super(3, "培根", fastFood);
    }

    public float cost() {
        return getPrice()+getFastFood().getPrice();
    }
    public String getDesc(){
        return super.getDesc()+getFastFood().getDesc();
    }
}
