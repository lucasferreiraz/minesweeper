package models;

import java.util.ArrayList;
import java.util.List;

import exceptions.ExplosionException;

public class Field {
    
    private boolean mined = false;
    private boolean open = false;
    private boolean marked = false;
    private final int line;
    private final int column;

    List<Field> neighbors = new ArrayList<>();

    public Field(int line, int column) {
        this.line = line;
        this.column = column;
    }

    public boolean addNeighbor(Field neighbor){
        boolean differentLine = (this.line != neighbor.line);   
        boolean differentColumn = (this.column != neighbor.column);
        boolean diagonal = differentLine && differentColumn;

        int deltaLine = Math.abs(this.line - neighbor.line);
        int deltaColumn = Math.abs(this.column - neighbor.column);
        int deltaGeral = deltaLine + deltaColumn;

        if(deltaGeral == 1 & !diagonal){
            return true;
        } else if(deltaGeral == 2 & diagonal){
            return true;
        } else {
            return false;
        }

    }

    public void switchMarking(){
        if(!open){
            marked = !marked;
        }
    }

    public boolean open(){
        if(!open && !marked){
            open = true;

            if(mined){
                throw new ExplosionException();
            }

            if(safeNeighborhood()){
                neighbors.forEach(n -> n.open());
            }

            return true;

        } else {
            return false;
        }

    }

    public boolean safeNeighborhood(){
        return neighbors.stream().noneMatch(n -> n.mined);
    }

}
