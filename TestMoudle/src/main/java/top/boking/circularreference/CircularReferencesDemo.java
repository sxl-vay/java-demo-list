package top.boking.circularreference;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Collection;

public class CircularReferencesDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(CircularReferencesDemo.class);
        context.setAllowCircularReferences(true);//是否开启循环依赖注入
        context.refresh();
        Student bean = context.getBean(Student.class);
        System.out.println("bean = " + bean);
        ClassRoom bean1 = context.getBean(ClassRoom.class);
        System.out.println("bean1 = " + bean1);
    }
    @Bean
    public Student getStudent() {
        Student student = new Student();
        student.setName("sxl");
        return  student;
    }

    @Bean
    public ClassRoom getClassRoom() {
        ClassRoom classRoom = new ClassRoom();
        classRoom.setRoomNumber("101");
        return classRoom;
    }
}
