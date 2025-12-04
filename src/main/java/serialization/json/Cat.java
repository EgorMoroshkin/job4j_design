package serialization.json;

import lombok.Getter;

import java.util.Arrays;

@Getter
public class Cat {
    private final int age;
    private final String name;
    private final boolean colorIsBlack;
    private final String[] child;

    public Cat(int age, String name, boolean colorIsBlack, String[] child) {
        this.age = age;
        this.name = name;
        this.colorIsBlack = colorIsBlack;
        this.child = child;
    }

    public boolean getColorIsBlack() {
        return colorIsBlack;
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