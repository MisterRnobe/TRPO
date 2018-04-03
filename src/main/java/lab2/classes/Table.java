package lab2.classes;

import java.util.LinkedList;
import java.util.List;

public class Table {
    private List<Object> columnNames;
    private List<Object> columnTypes;
    private List<Object> columnUnits;
    private List<List<Object>>  rows;

    public List<List<Object>> getRows() {
        return rows;
    }

    public void setRows(List<List<Object>> rows) {
        this.rows = rows;
    }

    public List<Object> getColumnNames() {
        return columnNames;
    }

    public void setColumnNames(List<Object> columnNames) {
        this.columnNames = columnNames;
    }

    public List<Object> getColumnTypes() {
        return columnTypes;
    }

    public void setColumnTypes(List<Object> columnTypes) {
        this.columnTypes = columnTypes;
    }
    public List<Object> getColumnUnits() {
        return columnUnits;
    }
    public void setColumnUnits(List<Object> columnUnits) {
        this.columnUnits = columnUnits;
    }
    public void addColumnName(Object columnName)
    {
        this.columnNames.add(columnName);
    }
    private void addcolumnType(Object columnType)
    {
        this.columnTypes.add(columnType);
    }
    private void addcolumnUnit(Object columnUnit)
    {
        this.columnUnits.add(columnUnit);
    }
    private void addRow(LinkedList<Object> row)
    {
        this.rows.add(row);
    }
}
