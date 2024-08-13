package base.question;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author shxl
 * @Date 2024/7/25 23:24
 * @Version 1.0
 */
public class EightQueen {
    private List<List<Integer>> result = new ArrayList<>();

    private static final int queenNumber = 8;

    public List<List<Integer>> getResult() {
        return getResult(queenNumber);
    }


    public List<List<Integer>> getResult(int queenNumber) {
        result = new ArrayList<>();
        //对应列是否有棋子
        Set<Integer> rowQueen = new HashSet<>();
        //对应斜线1是否有棋子
        Set<Integer> line1Queen = new HashSet<>();
        //对应斜线2是否有棋子
        Set<Integer> line2Queen = new HashSet<>();
        ArrayList<Integer> oneResult = new ArrayList<>();
        findResult(0, oneResult, rowQueen, line1Queen, line2Queen, queenNumber);
        return result;
    }

    private void findResult(int lineNumber, ArrayList<Integer> oneResult, Set<Integer> rowQueen, Set<Integer> line1Queen, Set<Integer> line2Queen, int queenNumber) {
        if (lineNumber == queenNumber) {
            //重新new一个结果对象出来
            result.add(new ArrayList<>(oneResult));
            return;
        }
        for (int rowNumber = 0; rowNumber < queenNumber; rowNumber++) {
            if (rowQueen.contains(rowNumber)) continue;
            int number1 = lineNumber + rowNumber;
            if (line1Queen.contains(number1)) {
                continue;
            }
            int number2 = lineNumber - rowNumber;
            if (line2Queen.contains(number2)) {
                continue;
            }
            rowQueen.add(rowNumber);
            line1Queen.add(number1);
            line2Queen.add(number2);
            oneResult.add(rowNumber);
            findResult(lineNumber + 1, oneResult, rowQueen, line1Queen, line2Queen, queenNumber);
            oneResult.remove(lineNumber);
            rowQueen.remove(rowNumber);
            line1Queen.remove(number1);
            line2Queen.remove(number2);
        }


    }

    public static void main(String[] args) {
        EightQueen eightQueen = new EightQueen();
        List<List<Integer>> result1 = eightQueen.getResult(9);
        System.out.println(result1.size());
        result1.forEach(System.out::println);
    }
}
