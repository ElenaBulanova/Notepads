package org.klozevitz.classwork.db;

import org.klozevitz.classwork.model.Notepad;

import java.sql.SQLException;
import java.util.List;

public interface task5 {
    Notepad add(Notepad notepad);
    List<Notepad> all();
    List<String> covers() throws SQLException;
    List<String> pageTypes() throws SQLException;
    void deleteRow(int rowNumber) throws SQLException;
    void closeConnection() throws SQLException;
    Notepad findById(int id) throws SQLException;
    Notepad update(Notepad notepad);
}
