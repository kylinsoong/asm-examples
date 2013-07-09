package ru.eroshenkoam.examples.asm.fields;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author Artem Eroshenko eroshenkoam
 *         7/9/13, 1:01 PM
 */
public class PrimitiveStaticFieldValueTest {

    @Test
    public void generatedClassFieldValueShouldBeZero() throws Exception {
        Class<?> clazz = ClassWithPrimitiveFieldGenerator.generateClassWithStaticIntegerField();
        assertThat((Integer) clazz.getField("index").get(null), equalTo(0));
    }

    @Test
    public void generatedClassFieldValueShouldBeSameAsDeclared() throws Exception {
        Integer value = -1;
        Class<?> clazz = ClassWithPrimitiveFieldGenerator.generateClassWithStaticIntegerField(value);
        assertThat((Integer) clazz.getField("index").get(null), equalTo(value));
    }
}
