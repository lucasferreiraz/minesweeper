package models;

import java.util.ArrayList;
import java.util.List;

public class Field {
    
    private boolean mined = false;
    private boolean open = false;
    private boolean marked = false;
    private final int line;
    private final int column;

    List<Field> neighborsFields = new ArrayList<>();

    public Field(int line, int column) {
        this.line = line;
        this.column = column;
    }

    

}
