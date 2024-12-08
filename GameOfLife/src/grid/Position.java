package grid;

import exceptions.InvalidCoordinateException;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Position {
    private int columnId;
    private int rowId;

    public Position(int pColumnId, int pRowId){
        columnId = pColumnId;
        rowId = pRowId;
    }

    public Position(String coordinate) throws InvalidCoordinateException {
        columnId = -1;
        rowId = -1;
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(coordinate);
        int coordinatesFound = 0;
        while (m.find()) {
            if (coordinatesFound == 0) {
                columnId = Integer.parseInt(m.group());
                coordinatesFound ++;
            }
            else if (coordinatesFound == 1){
                rowId = Integer.parseInt(m.group());
                coordinatesFound ++;
            }
            else {
                throw new InvalidCoordinateException();
            }
        }
    }

    public int getColumnId() {
        return columnId;
    }

    public int getRowId() {
        return rowId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return columnId == position.columnId && rowId == position.rowId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(columnId, rowId);
    }
}
