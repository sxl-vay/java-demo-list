package top.boking.factory.abstractfactory.factory;

import top.boking.factory.abstractfactory.procuct.IBuletooth;
import top.boking.factory.abstractfactory.procuct.IComputer;
import top.boking.factory.abstractfactory.procuct.IKeyboard;

public interface IFactory {
     IComputer createComputer();
     IKeyboard createKeyboard();
     IBuletooth createBuletooth();
}
