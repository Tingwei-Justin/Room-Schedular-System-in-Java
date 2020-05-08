
import javax.swing.table.AbstractTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author luckyjustin
 */
public class ResultSetTableModel extends AbstractTableModel{
    private final Connection connection;
    private final Statement statement;
    private ResultSet resultSet;
    private int numberOfRows;
    private ResultSetMetaData metaData;
    private boolean connectedToDataBase = false;
    
    public ResultSetTableModel(String query) throws SQLException {
        connection = DBConnection.getConnection();
        statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        connectedToDataBase = true;
        setQuery(query);
    }
    @Override
    public int getRowCount() {
        if (!connectedToDataBase) {
            throw new IllegalStateException("Not connected to database");
        }
        return numberOfRows;
    }

    @Override
    public int getColumnCount() {
        if (!connectedToDataBase) {
            throw new IllegalStateException("Not connected to database");
        }
        try {
            return metaData.getColumnCount();
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return 0;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (!connectedToDataBase) {
            throw new IllegalStateException("Not connected to database");
        }
        try {
            resultSet.absolute(rowIndex + 1);
            return resultSet.getObject(columnIndex + 1);
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return ""; //return emtpy if problems
    }
    
    
    private void setQuery(String query) throws SQLException, IllegalStateException {
        if (!connectedToDataBase) {
            throw new IllegalStateException("Not connected to database");
        }
        resultSet = statement.executeQuery(query);
        metaData = resultSet.getMetaData();
        resultSet.last();
        numberOfRows = resultSet.getRow();
        fireTableStructureChanged();  // notify JTable 
    }
    
    public String getColumnName(int column) throws IllegalStateException {
        if (!connectedToDataBase) {
            throw new IllegalStateException("Not connected to database");
        }
        try {
            return metaData.getColumnName(column + 1);
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return "";
    }
    
    public void disconnectFromDatabase() {
        if (connectedToDataBase) {
            try {
                resultSet.close();
                statement.close();
                connection.close();
            }
            catch(SQLException sqlException) {
                sqlException.printStackTrace();
            }
            finally {
                connectedToDataBase = false;
            }
        }
    }
    
}
