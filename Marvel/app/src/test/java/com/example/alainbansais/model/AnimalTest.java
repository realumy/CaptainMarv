package com.example.alainbansais.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class AnimalTest {

    @Test
    public void testBuilder() {
        final Animal lion = Animal.builder()
                                  .setName("Lion")
                                  .setNumberOfLegs(4)
                                  .setIsFish(false)
                                  .build();
        assertEquals(lion.getName(), "Lion");
        assertEquals(lion.getNumberOfLegs(), 4);
        assertEquals(lion.getIsFish(), false);
    }

}