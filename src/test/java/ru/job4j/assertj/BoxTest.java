package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {

    @Test
    void whatsThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere").endsWith("ere");
    }

    @Test
    void whatsThisCube() {
        Box box = new Box(8, 6);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Cube").isNotEmpty().startsWith("Cu");
    }

    @Test
    void getNumberOfVerticesFour() {
        Box box = new Box(4, 4);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex).isNotNull().isGreaterThan(3);
    }

    @Test
    void getNumberOfVerticesEight() {
        Box box = new Box(8, 6);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex).isPositive().isEqualTo(8).isLessThan(10);
    }

    @Test
    void isExistFalse() {
        Box box = new Box(-1, 6);
        boolean exist = box.isExist();
        assertThat(exist).isFalse().isEqualTo(false).isNotNull();
    }

    @Test
    void isExistTrue() {
        Box box = new Box(8, 6);
        boolean exist = box.isExist();
        assertThat(exist).isTrue().isEqualTo(true).isNotNull();
    }

    @Test
    void getAreaSphere() {
        Box box = new Box(0, 2);
        double areaSphere = box.getArea();
        assertThat(areaSphere).isPositive().isEqualTo(50.2d, withPrecision(0.09d));
    }

    @Test
    void getAreaTetrahedron() {
        Box box = new Box(4, 5);
        double areaTetrahedron = box.getArea();
        assertThat(areaTetrahedron).isGreaterThan(40d).isEqualTo(43.3d, withPrecision(0.02d));
    }

    @Test
    void getAreaCube() {
        Box box = new Box(8, 8);
        double areaTetrahedron = box.getArea();
        assertThat(areaTetrahedron).isLessThan(400d).isCloseTo(383d, withPrecision(1d));
    }
}