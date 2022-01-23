import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StreamTest {
    public static void main(String[] args) {
        ArrayList<Person> list = new ArrayList<>();
        list.add(new Person(1,11,"s1"));
        list.add(new Person(2,12,"s2"));
        list.add(new Person(3,13,"s3"));
        list.add(new Person(4,14,"s4"));


        ArrayList<Integer> collect = list.stream().collect(() -> new ArrayList<Integer>(),
                (map, p) -> map.add(p.getAge()), (m, n) -> {
                    System.out.println(m.getClass().getName());
                });
        collect.forEach(System.out::println);

    }
}
