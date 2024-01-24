package org.klozevitz.classwork;

import org.klozevitz.classwork.db.DbDao;
import org.klozevitz.classwork.model.Notepad;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println(Arrays.stream(Notepad.class.getDeclaredFields()).map(Field::getName).toList());
//        DbDao dao = new DbDao();
//        List<Notepad> all = dao.all();
//        System.out.printf(all.toString());
    }
}
