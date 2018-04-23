package org.yxyqcy.family.home.test.reflect;

import org.yxyqcy.family.home.fbook.entity.FBook;

import java.lang.reflect.Field;

/**
 * Created by lcy on 17/11/16.
 */
public class TestReflect {

    public static void main(String[] args) {
        Class<?> classK = FBook.class;

        Field[] listA = classK.getDeclaredFields();
        System.out.println(listA.toString() + "  " + listA.length);

        FBook fBook = new FBook();
        Field[] listF = fBook.getClass().getDeclaredFields();
        System.out.println(listF.toString() + "  " + listF.length);

        Class classB = FBook.class;
        Field[] listB = classB.getDeclaredFields();

        System.out.println(listB.toString() + "  " + listB.length);

        Field[] listC = fBook.getClass().getSuperclass().getDeclaredFields();
        System.out.println(listC.toString() + "  " + listC.length);

        Field[] listD = fBook.getClass().getSuperclass().getSuperclass().getDeclaredFields();
        System.out.println(listD.toString() + "  " + listD.length);
    }
}
