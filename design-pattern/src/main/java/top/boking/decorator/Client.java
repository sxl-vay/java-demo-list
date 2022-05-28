package top.boking.decorator;

public class Client {
    public static void main(String[] args) {
        FastFood food = new FriedRice();
        String s = food.getDesc() + "  " + food.cost() + "元";
        System.out.println("s = " + s);
        System.out.println("_______________");
        food = new Egg(food);
        String segg = food.getDesc() + "  " + food.cost() + "元";
        System.out.println("segg = " + segg);

    }
}
