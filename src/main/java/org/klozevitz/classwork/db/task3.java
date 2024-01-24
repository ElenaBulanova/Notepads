package org.klozevitz.classwork.db;

import org.klozevitz.classwork.model.MinMax;

import java.sql.SQLException;
import java.util.Map;

public interface task3 {
    Map<String, Integer> countriesSummary() throws SQLException;
    Map<String, Integer> brandSummary() throws SQLException;
    void closeConnection() throws SQLException;
    MinMax findMinMax(Map<String, Integer> resMinMax) throws SQLException;
}
