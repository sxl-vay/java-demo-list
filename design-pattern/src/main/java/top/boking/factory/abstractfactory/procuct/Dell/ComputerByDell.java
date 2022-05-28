package top.boking.factory.abstractfactory.procuct.Dell;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.boking.factory.abstractfactory.procuct.IComputer;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComputerByDell implements IComputer {
    private String name;
    private String desc;
    private BigDecimal price;
}
