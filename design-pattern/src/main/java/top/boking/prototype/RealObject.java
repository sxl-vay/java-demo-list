package top.boking.prototype;

import lombok.Data;
import top.boking.POJO.Person;

public class RealObject implements Cloneable {
    private Person person;

    public RealObject(Person person) {
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public RealObject clone() throws CloneNotSupportedException {

        return (RealObject) super.clone();
    }
}
