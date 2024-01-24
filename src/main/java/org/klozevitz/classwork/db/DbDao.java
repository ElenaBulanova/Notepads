package org.klozevitz.classwork.db;

import org.klozevitz.classwork.model.MinMax;
import org.klozevitz.classwork.model.Notepad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DbDao implements task2, task3, task4, task5{

    private final String url = "jdbc:mysql://localhost:3306/notebooks";
    private final String user = "root";
    private final String pass = "Vologda1";
    private final Connection connection;

    public DbDao() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            System.out.println("подключение невозможно");
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            System.out.println("Драйвер не распакован");
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Notepad> all() {
        try {
            return getNotepadsListFromResultSet(
                    connection.createStatement().executeQuery("SELECT * FROM notepads;"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Notepad> pagesAmount(int amountStart, int amountEnd) throws SQLException {
        try {
            return getNotepadsListFromResultSet(
                    connection.createStatement().executeQuery(String.format
                            ("SELECT * FROM notepads where ((pagesAmount >= '%d') && (pagesAmount <= '%d'));", amountStart, amountEnd)));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Notepad> getNotepadsListFromResultSet(ResultSet result) throws SQLException {
        List<Notepad> all = new ArrayList<>();
        while (result.next()) {
            all.add(new Notepad(result));
        }
        return all;
    }

    @Override
    public List<String> countries() throws SQLException{
        String query = "SELECT DISTINCT country FROM notepads;";
        ResultSet result = connection.createStatement().executeQuery(query);
        return getStringListFromResultSet(result, "country");
    }

    @Override
    public Map<String, Integer> countriesSummary() throws SQLException{
        String query = "SELECT  country, COUNT(*) FROM notepads group by country order by COUNT(*);";
        ResultSet result = connection.createStatement().executeQuery(query);
        Map<String, Integer> countries = new HashMap<>();
        while(result.next()) {
            countries.put(result.getString("country"),Integer.parseInt(result.getString("COUNT(*)")));
        }
        return countries;
    }

    @Override
    public Map<String, Integer> brandSummary() throws SQLException {
        String query = "SELECT  brand, COUNT(*) FROM notepads group by brand ;";
        ResultSet result = connection.createStatement().executeQuery(query);
        Map<String, Integer> brands = new HashMap<>();
        while(result.next()) {
            brands.put(result.getString("brand"),Integer.parseInt(result.getString("COUNT(*)")));
        }
        return brands;
    }

    @Override
    public List<Notepad> pageTypeFilter(String type) throws SQLException {
        ResultSet resultSet = connection.createStatement()
                .executeQuery(String.format("SELECT * FROM notepads WHERE type = '%s'", type));
        return getNotepadsListFromResultSet(resultSet);
    }

    @Override
    public List<Notepad> countryFilter(String country) throws SQLException {
        ResultSet resultSet = connection.createStatement()
                .executeQuery(String.format("SELECT * FROM notepads WHERE country = '%s'", country));
        return getNotepadsListFromResultSet(resultSet);
    }

    @Override
    public List<String> pageTypes() throws SQLException {
        String query = "SELECT DISTINCT type FROM notepads;";
        ResultSet result = connection.createStatement().executeQuery(query);
        return getStringListFromResultSet(result, "type");
    }

    @Override
    public void deleteRow(int rowNumber) throws SQLException {
        String query = String.format("delete from notepads where id = '%d'", rowNumber);
        connection.createStatement().executeUpdate(query);
    }

    private List<String> getStringListFromResultSet(ResultSet result, String column) throws SQLException {
        List<String> resultAsString = new ArrayList<>();
        while (result.next()) {
            resultAsString.add(result.getString(column));
        }
        return resultAsString;
    }

    @Override
    public Notepad add(Notepad notepad) {
        try {
            connection.createStatement().executeUpdate(getQueryFromNotepad(notepad));
            return notepad;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Notepad update(Notepad notepad) {
        String query = String.format("UPDATE  notepads SET brand = '%s', model = '%s', pagesAmount = %d, cover = '%s', country = '%s', type = '%s'  " +
                        "WHERE id = %d",
                notepad.getBrand(), notepad.getModel(),
                notepad.getPagesAmount(), notepad.getCover(),
                notepad.getCountry(), notepad.getType(), notepad.getId());
        try {
            connection.createStatement().executeUpdate(query);
            return notepad;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Notepad findById(int id){
        try {
            ResultSet result =  connection.createStatement().executeQuery(String.format("SELECT * from notepads where id = %d;", id));
            result.next();
            return new Notepad(result);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


//    private String getQueryFromNotepad(Notepad notepad) throws SQLException {
//        return String.format("INSERT INTO notepads (brand, model, pagesamount, cover, country, type) VALUES ('%s', '%s', %d, '%s', '%s', '%s')",
//                notepad.getBrand(), notepad.getModel(), notepad.getPagesAmount(), notepad.getCover(), notepad.getCountry(), notepad.getType());
//    }

// это не hibernate- автоинкремент так ловко, как там, не работает... приходится
// надо либо заморачиваться с генерацией последовательности (что, вроде, как, правильно, но для текущей задачи
// не является необходимостью), либо делать запрос в таблицу и вставлять полную строку с id
    private String getQueryFromNotepad(Notepad notepad) throws SQLException {
        return String.format("INSERT INTO notepads (brand, model, pagesAmount, cover, country, type)  VALUES ('%s', '%s', %d, '%s', '%s', '%s')",
               notepad.getBrand(), notepad.getModel(),   //getCurrentId()
                notepad.getPagesAmount(), notepad.getCover(),
                notepad.getCountry(), notepad.getType());
    }

//    private int getCurrentId() throws SQLException {
//        // в резалтсете получаем текущий id последней строки
//        ResultSet result = connection.createStatement().executeQuery("SELECT max(id) FROM notepads;");
//        result.next();
//        //возвращаем "автоинкремент"
//        return result.getInt("id") + 1;
//    }

    @Override
    public List<String> covers() throws SQLException {
        String query = "SELECT DISTINCT cover FROM notepads;";
        ResultSet result = connection.createStatement().executeQuery(query);
        return getStringListFromResultSet(result, "cover");
    }

    @Override
    public void closeConnection() throws SQLException {
        connection.close();
    }

    @Override
    public MinMax findMinMax(Map<String, Integer> all) throws SQLException {

        Map<String, Integer> max = new HashMap<>();
        Map<String, Integer> min = new HashMap<>();
        MinMax result = new MinMax(max, min);
        Map.Entry<String, Integer> maxEntry = null;
        Map.Entry<String, Integer> minEntry = null;
        for (Map.Entry<String, Integer> entry : all.entrySet()) {
            if (maxEntry == null || entry.getValue() > maxEntry.getValue()) {
                maxEntry = entry;
            }
            if (minEntry == null || entry.getValue() < minEntry.getValue()) {
                minEntry = entry;
            }
        }
        max.put(maxEntry.getKey(), maxEntry.getValue());
        min.put(minEntry.getKey(), minEntry.getValue());
        for (Map.Entry<String, Integer> entry : all.entrySet()) {
            if (entry.getValue() == maxEntry.getValue()) {
                max.put(entry.getKey(), entry.getValue());
            }
            if (entry.getValue() == minEntry.getValue()) {
                min.put(entry.getKey(), entry.getValue());
            }
        }
        result.setMax(max);
        result.setMin(min);
        return result;
    }


}
