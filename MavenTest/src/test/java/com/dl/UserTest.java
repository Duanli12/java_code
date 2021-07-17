package com.dl;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void getId() {
        User user = new User();
        user.setId(1);
        int id = user.getId();
        assertEquals(id,0);
    }

    @Test
    public void setId() {
    }

    @Test
    public void getName() {
    }

    @Test
    public void setName() {
    }

    @Test
    public void testToString() {
    }
}