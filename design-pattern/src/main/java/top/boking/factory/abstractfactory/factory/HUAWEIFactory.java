package top.boking.factory.abstractfactory.factory;

import top.boking.factory.abstractfactory.procuct.HUAWEI.BuletoothByHuaWei;
import top.boking.factory.abstractfactory.procuct.HUAWEI.ComputerByHuaWei;
import top.boking.factory.abstractfactory.procuct.HUAWEI.KeyboardByHuaWei;
import top.boking.factory.abstractfactory.procuct.IBuletooth;
import top.boking.factory.abstractfactory.procuct.IComputer;
import top.boking.factory.abstractfactory.procuct.IKeyboard;

import java.math.BigDecimal;

public class HUAWEIFactory implements IFactory {

    @Override
    public IComputer createComputer() {
        ComputerByHuaWei computerByHuaWei = new ComputerByHuaWei("华为电脑", "华为电脑值得信赖", new BigDecimal(9998));
        return computerByHuaWei;
    }

    @Override
    public IKeyboard createKeyboard() {
        KeyboardByHuaWei keyboardByHuaWei= new KeyboardByHuaWei("华为键盘", "华为键盘值得信赖", new BigDecimal(99));

        return keyboardByHuaWei;
    }

    @Override
    public IBuletooth createBuletooth() {
        return new BuletoothByHuaWei("华为Buletooth", "华为Buletooth值得信赖", new BigDecimal(998));
    }
}
