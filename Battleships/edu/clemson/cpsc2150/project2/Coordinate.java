package edu.clemson.cpsc2150.project2;

/**
 * Created by Owner on 9/20/2016.
 */
public class Coordinate {
    public int row, column;
    public Coordinate(int r, int c)
    {
        row = r;
        column = c;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Coordinate)
        {
            Coordinate other = (Coordinate) obj;
            if((this.row == other.row) && (this.column == other.column))
                return true;
            else
                return false;
        }
        else
        {
            return super.equals(obj);
        }
    }

    @Override
    public String toString() {
        return "(" + row + ", " + column + ")";
    }
}
