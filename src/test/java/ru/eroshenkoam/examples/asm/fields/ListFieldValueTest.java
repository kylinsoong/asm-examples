package ru.eroshenkoam.examples.asm.fields;

import org.junit.Test;

/**
 * @author Artem Eroshenko eroshenkoam
 *         7/9/13, 2:00 PM
 */
public class ListFieldValueTest {

    @Test
    public void testOutput () throws Exception {
        Class<?> itemsClass = ClassWithListFieldGenerator.generateClassWithListField();
        Object items = itemsClass.newInstance();
        System.out.println(itemsClass.getDeclaredField("ids").get(items));
    }
}
