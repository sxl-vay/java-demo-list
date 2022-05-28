package top.boking.factory.abstractfactory.factory;

import top.boking.factory.abstractfactory.procuct.Dell.BuletoothByDell;
import top.boking.factory.abstractfactory.procuct.Dell.ComputerByDell;
import top.boking.factory.abstractfactory.procuct.Dell.KeyboardByDell;
import top.boking.factory.abstractfactory.procuct.IBuletooth;
import top.boking.factory.abstractfactory.procuct.IComputer;
import top.boking.factory.abstractfactory.procuct.IKeyboard;

import java.math.BigDecimal;

public class DellFactory implements IFactory {
    @Override
    public IComputer createComputer() {
        ComputerByDell computerByDell = new ComputerByDell("Dell电脑", "Dell电脑值得信赖", new BigDecimal(8998));
        return computerByDell;
    }

    @Override
    public IKeyboard createKeyboard() {
        KeyboardByDell keyboardByDell= new KeyboardByDell("Dell键盘", "Dell键盘值得信赖", new BigDecimal(89));

        return keyboardByDell;
    }

    @Override
    public IBuletooth createBuletooth() {
        return new BuletoothByDell("DellBuletooth", "DellBuletooth值得信赖", new BigDecimal(898));
    }
}
