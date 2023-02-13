package ru.job4j.generics;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RoleStoreTest {

    @Test
    void whenAddAndFindRolenameIsActor() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Actor"));
        Role result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("Actor");
    }

    @Test
    void whenTwoEqualAddRolename() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Model"));
        store.add(new Role("1", "Manager"));
        Role result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("Model");
    }

    @Test
    void whenAddAndDeleteRolenameDriver() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Driver"));
        store.delete("1");
        Role result = store.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenAddAndReplaceRolenameActorTeacher() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Actor"));
        store.replace("1", new Role("1", "Teacher"));
        Role result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("Teacher");
    }

    @Test
    void whenFindRolenameIsEmptyStore() {
        RoleStore store = new RoleStore();
        Role result = store.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenAddAndReplaceRolenameNoOk() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Dancer"));
        store.replace("2", new Role("1", "Doctor"));
        Role result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("Dancer");
    }

    @Test
    void whenAddAndDeleteRolenameNoOk() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Doctor"));
        store.delete("2");
        Role result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("Doctor");
    }
}




