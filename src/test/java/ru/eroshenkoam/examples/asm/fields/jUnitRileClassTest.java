package ru.eroshenkoam.examples.asm.fields;

import org.junit.Rule;
import org.junit.Test;

import java.lang.reflect.Field;

/**
 * @author Artem Eroshenko eroshenkoam
 *         7/9/13, 4:00 PM
 */
public class jUnitRileClassTest {

    @Test
    public void testOutput () throws Exception {
        Class<?> itemsClass = JUnitRuleClassGenerator.generateClassWithJUnitRule();
        Object items = itemsClass.newInstance();
        Field field = itemsClass.getDeclaredField("testName");
        System.out.println(field.isAnnotationPresent(Rule.class));
        System.out.println(field.get(items));
    }

}
