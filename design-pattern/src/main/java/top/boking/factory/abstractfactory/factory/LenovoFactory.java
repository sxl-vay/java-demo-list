package top.boking.factory.abstractfactory.factory;

import top.boking.factory.abstractfactory.procuct.Lenovo.BuletoothByLenovo;
import top.boking.factory.abstractfactory.procuct.Lenovo.ComputerByLenovo;
import top.boking.factory.abstractfactory.procuct.Lenovo.KeyboardByLenovo;
import top.boking.factory.abstractfactory.procuct.IBuletooth;
import top.boking.factory.abstractfactory.procuct.IComputer;
import top.boking.factory.abstractfactory.procuct.IKeyboard;

import java.math.BigDecimal;

public class LenovoFactory implements IFactory {
    @Override
    public IComputer createComputer() {
        ComputerByLenovo computerByLenovo = new ComputerByLenovo("联想电脑", "联想电脑值得信赖", new BigDecimal(19998));
        return computerByLenovo;
    }

    @Override
    public IKeyboard createKeyboard() {
        KeyboardByLenovo keyboardByLenovo= new KeyboardByLenovo("联想键盘", "联想键盘值得信赖", new BigDecimal(199));

        return keyboardByLenovo;
    }

    @Override
    public IBuletooth createBuletooth() {
        return new BuletoothByLenovo("联想Buletooth", "联想Buletooth值得信赖", new BigDecimal(1998));
    }
}
