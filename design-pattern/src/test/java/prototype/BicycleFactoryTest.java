package prototype;
import java.awt.*;
import javax.swing.*;
public class BicycleFactoryTest {
    public static void main(String[] args) {
        try {
            Bicycle a;
            BicycleFactory bf;
            bf = (BicycleFactory) ReadXML.getObject();
            a = bf.produce();
            a.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
//抽象产品：自行车
interface Bicycle {
    public void show();
}
//具体产品：爱玛自行车
class AimaBicycle implements Bicycle {
    JScrollPane sp;
    JFrame jf = new JFrame("工厂方法模式测试");
    public AimaBicycle() {
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(1, 1));
        p1.setBorder(BorderFactory.createTitledBorder("爱玛自行车"));
        JLabel l1 = new JLabel(new ImageIcon("src/FactoryMethod/AIMABicycle.jpg"));
        p1.add(l1);
        sp = new JScrollPane(p1);
        Container contentPane = jf.getContentPane();
        contentPane.add(sp, BorderLayout.CENTER);
        jf.pack();
        jf.setVisible(false);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //用户点击窗口关闭
    }
    public void show() {
        jf.setVisible(true);
    }
}
//具体产品：雅迪自行车
class YadeaBicycle implements Bicycle {
    JScrollPane sp;
    JFrame jf = new JFrame("工厂方法模式测试");
    public YadeaBicycle() {
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(1, 1));
        p1.setBorder(BorderFactory.createTitledBorder("雅迪自行车"));
        JLabel l1 = new JLabel(new ImageIcon("src/FactoryMethod/YadeaBicycle.jpg"));
        p1.add(l1);
        Container contentPane = jf.getContentPane();
        sp = new JScrollPane(p1);
        contentPane.add(sp, BorderLayout.CENTER);
        jf.pack();
        jf.setVisible(false);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //用户点击窗口关闭
    }
    public void show() {
        jf.setVisible(true);
    }
}
//抽象工厂：自行车工厂
interface BicycleFactory {
    public Bicycle produce();
}
//具体工厂：爱玛工厂
class AimaFactory implements BicycleFactory {
    public Bicycle produce() {
        System.out.println("爱玛自行车生产了！");
        return new AimaBicycle();
    }
}
//具体工厂：雅迪工厂
class YadeaFactory implements BicycleFactory {
    public Bicycle produce() {
        System.out.println("雅迪自行车生产了！");
        return new YadeaBicycle();
    }
}