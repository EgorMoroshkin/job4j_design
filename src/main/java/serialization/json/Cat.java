package serialization.json;

import jakarta.xml.bind.annotation.*;

import java.util.Arrays;

@XmlRootElement(name = "cat")
@XmlAccessorType(XmlAccessType.FIELD)
public class Cat {
    @XmlAttribute
    private int age;
    private String name;
    private boolean colorIsBlack;

    @XmlElementWrapper(name = "children")
    @XmlElement(name = "child")
    private String[] child;

    public Cat() {

    }

    public Cat(int age, String name, boolean colorIsBlack, String[] child) {
        this.age = age;
        this.name = name;
        this.colorIsBlack = colorIsBlack;
        this.child = child;
    }

    @Override
    public String toString() {
        return "Cat{"
                + "age=" + age
                + ", name='" + name + '\''
                + ", colorIsBlack=" + colorIsBlack
                + ", child=" + Arrays.toString(child)
                + '}';
    }
}
