package org.klozevitz.classwork.db;

import org.klozevitz.classwork.model.Notepad;

import java.sql.SQLException;
import java.util.List;

public interface task4 {
    List<Notepad> pageTypeFilter(String type) throws SQLException;
    List<Notepad> countryFilter(String country) throws SQLException;
    List<String> pageTypes() throws SQLException;
    void closeConnection() throws SQLException;
    List<String> countries() throws SQLException;
    List<Notepad> all() throws SQLException;
    List<Notepad> pagesAmount(int amountStart, int amountEnd) throws SQLException;
}
