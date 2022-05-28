package top.boking.decorator;
/**
 * 炒面（具体构件角色）
 *
 */
public class FriedNoodles extends FastFood {
    public FriedNoodles() {
        super(12,"炒饭");
    }
    public float cost() {
        return getPrice();
    }
}
