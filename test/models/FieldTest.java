package models;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class FieldTest {

    private Field field = new Field(3, 3);

    @Test
    void leftNeighborTest(){
        Field neighbor = new Field(3, 2);
        boolean result = field.addNeighbor(neighbor);

        assertTrue(result);
    }

    @Test
    void rightNeighborTest(){
        Field neighbor = new Field(3, 4);
        boolean result = field.addNeighbor(neighbor);

        assertTrue(result);
    }

    @Test
    void aboveNeighborTest(){
        Field neighbor = new Field(2, 3);
        boolean result = field.addNeighbor(neighbor);

        assertTrue(result);
    }

    @Test
    void belowNeighborTest(){
        Field neighbor = new Field(4, 3);
        boolean result = field.addNeighbor(neighbor);

        assertTrue(result);
    }

    @Test
    void diagonalNeighborTest(){
        Field neighbor = new Field(2, 2);
        boolean result = field.addNeighbor(neighbor);

        assertTrue(result);
    }

    @Test
    void notNeighborTest(){
        Field neighbor = new Field(1, 1);
        boolean result = field.addNeighbor(neighbor);

        assertFalse(result);
    }
    
}