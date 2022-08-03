package models;

import java.util.ArrayList;
import java.util.List;

public class Board {
    
    private int lines;
    private int columns;
    private int mines;

    private final List<Field> fields = new ArrayList<>();

    public Board(int lines, int columns, int mines) {
        this.lines = lines;
        this.columns = columns;
        this.mines = mines;

        generateFields();
        associateNeighbors();
        drawMines();
    }

    private void generateFields() {
        for(int line = 0; line < lines; line++){
            for(int column = 0; column < columns; column++){
                fields.add(new Field(line, column));
            }
        }
    }

    private void associateNeighbors() {
        for(Field field1: fields){
            for(Field field2: fields){
                field1.addNeighbor(field2);
            }
        }
    }
    
    private void drawMines() {
        
    }

}
