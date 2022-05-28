package top.boking.factory.abstractfactory.procuct.Lenovo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.boking.factory.abstractfactory.procuct.IKeyboard;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KeyboardByLenovo implements IKeyboard {
    private String name;
    private String desc;
    private BigDecimal price;
}
