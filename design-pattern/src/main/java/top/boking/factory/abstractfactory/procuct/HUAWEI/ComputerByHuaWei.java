package top.boking.factory.abstractfactory.procuct.HUAWEI;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.boking.factory.abstractfactory.procuct.IComputer;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComputerByHuaWei implements IComputer {
    private String name;
    private String desc;
    private BigDecimal price;
}
