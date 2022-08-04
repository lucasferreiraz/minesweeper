package models;

import java.util.ArrayList;
import java.util.List;

import exceptions.ExplosionException;

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

    public void open(int line, int column){
        try {
            fields.parallelStream()
                .filter(f -> f.getLine() == line && f.getColumn() == column)
                .findFirst()
                .ifPresent(f -> f.open());
        } catch (ExplosionException e) {
            fields.forEach(f -> f.setOpen(true));
            throw e;
        }
        
    }

    public void switchMarking(int line, int column){
        fields.parallelStream()
                .filter(f -> f.getLine() == line && f.getColumn() == column)
                .findFirst()
                .ifPresent(f -> f.switchMarking());
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
        long armedMines = 0;

        do {
            
            int random = (int) (Math.random() * fields.size());
            fields.get(random).mine();
            armedMines = fields.stream()
                                .filter(f -> f.isMined())
                                .count();

        } while(armedMines < mines);
    }

    public boolean goalAchieved(){
        return fields.stream().allMatch(f -> f.goalAchieved());
    }

    public void reboot(){
        fields.stream().forEach(f -> f.reboot());
        drawMines();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        int i = 0;

        for(int line = 0; line < lines; line++){
            for(int column = 0; column < columns; column++){
                sb.append(" ");
                sb.append(fields.get(i));
                sb.append(" ");
                i++;
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    

}
