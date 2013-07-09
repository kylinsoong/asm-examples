package ru.eroshenkoam.examples.asm.fields;

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * @author Artem Eroshenko eroshenkoam
 *         7/9/13, 4:00 PM
 */
public class jUnitRileClassTest {

    @Test
    public void testOutput () throws Exception {
        Class<?> itemsClass = JUnitRuleClassGenerator.generateClassWithJUnitRule();
        Object items = itemsClass.newInstance();
        for (Field field : itemsClass.getDeclaredFields()) {
            System.out.println(Arrays.asList(field.getDeclaredAnnotations()));
            System.out.println(field.get(items));
        }
    }

}
