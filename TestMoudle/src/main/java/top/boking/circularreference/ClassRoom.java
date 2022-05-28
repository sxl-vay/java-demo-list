package top.boking.circularreference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassRoom {
    @Autowired
    private Collection<Student> students;
    private String roomNumber;
}
