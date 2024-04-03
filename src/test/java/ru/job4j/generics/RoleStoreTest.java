package ru.job4j.generics;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RoleStoreTest {

    @Test
    void whenAddAndFindThenRoleNameIsManager() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Manager"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Manager");
    }

    @Test
    void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Manager"));
        Role result = store.findById("20");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindThenRoleNameIsManager() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Manager"));
        store.add(new Role("1", "CEO"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Manager");
    }

    @Test
    void whenReplaceThenRoleNameIsCEO() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Manager"));
        store.replace("1", new Role("1", "CEO"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("CEO");
    }

    @Test
    void whenNoReplaceRoleThenNoChangeRoleName() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Manager"));
        store.replace("20", new Role("20", "CEO"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Manager");
    }

    @Test
    void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Manager"));
        store.delete("1");
        Role result = store.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteRoleThenRoleNameIsManager() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Manager"));
        store.delete("2");
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Manager");
    }

    @Test
    void whenReplaceOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Manager"));
        boolean result = store.replace("1", new Role("1", "CEO"));
        assertThat(result).isTrue();
    }

    @Test
    void whenReplaceIsNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Manager"));
        boolean result = store.replace("2", new Role("2", "CEO"));
        assertThat(result).isFalse();
    }

}