package top.boking.circularreference;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Autowired
    private ClassRoom classRoom;

    private String name;

    @Override
    public String toString() {
        return "Student{" +
                "classRoom.getRoomNumber=" + classRoom.getRoomNumber() +
                ", name='" + name + '\'' +
                '}';
    }
}
