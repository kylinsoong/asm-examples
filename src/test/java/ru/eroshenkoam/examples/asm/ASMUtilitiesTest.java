package ru.eroshenkoam.examples.asm;

import org.junit.Test;
import org.objectweb.asm.Type;

/**
 * @author Artem Eroshenko eroshenkoam
 *         7/8/13, 10:56 PM
 */
public class ASMUtilitiesTest {

    @Test
    public void testOutput() {
        System.out.println(Type.getDescriptor(int.class));
        System.out.println(Type.getDescriptor(Object.class));
        System.out.println(Type.getInternalName(Object.class));

    }


}
