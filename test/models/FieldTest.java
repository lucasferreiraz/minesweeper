package models;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import exceptions.ExplosionException;

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

    void defaultValueOfMarkedAttributeTeste(){
        assertFalse(field.isMarked());
    }

    @Test
    void switchMarkingTest(){
        field.switchMarking();
        assertTrue(field.isMarked());
    }
    
    @Test
    void switchMarkingTwiceTest(){
        field.switchMarking();
        field.switchMarking();
        assertFalse(field.isMarked());
    }

    @Test
    void openNotMinedAndNotMarkedTest(){
        assertTrue(field.open());
    }

    @Test
    void openNotMinedMarkedTest(){
        field.switchMarking();
        assertFalse(field.open());
    }

    @Test
    void openMinedMarkedTest(){
        field.mine();
        field.switchMarking();
        assertFalse(field.open());
    }

    @Test
    void openMinedNotMarkedTest(){

        field.mine();

        assertThrows(ExplosionException.class, () -> {
            field.open();
        });
    }

    @Test
    void openingWithNeighborsTest1(){
        Field field11 = new Field(1, 1);
        Field field22 = new Field(2, 2);
        field22.addNeighbor(field11);

        field.addNeighbor(field22);
        field.open();

        assertTrue(field22.isOpen() && field11.isOpen());
    }

    @Test
    void openingWithNeighborsTest2(){
        Field field11 = new Field(1, 1);
        Field field12 = new Field(1, 2);
        field12.mine(); 

        Field field22 = new Field(2, 2);
        field22.addNeighbor(field11);
        field22.addNeighbor(field12);

        field.addNeighbor(field22);
        field.open();

        assertTrue(field22.isOpen() && field12.isClosed());
    }
}