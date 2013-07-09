package ru.eroshenkoam.examples.asm.methods;

import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;
import ru.eroshenkoam.examples.asm.ASMUtilities;

import java.lang.reflect.Method;

import static org.objectweb.asm.Opcodes.*;
import static org.objectweb.asm.Type.getInternalName;

/**
 * @author Artem Eroshenko eroshenkoam
 *         7/9/13, 6:08 PM
 */
public class AddTimeAdapterTest {

    @Test
    public void testOutput() throws Exception {

        ClassReader classReader = new ClassReader(getInternalName(Sleeper.class));
        ClassWriter classWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_MAXS);

        ClassNode classNode = new ClassNode(ASM4);
        classReader.accept(classNode, 0);

        AddTimerAdapter addTimeAdapter = new AddTimerAdapter(classWriter);
        classReader.accept(addTimeAdapter, 0);

        Class<?> sleeperClass = ASMUtilities.defineClass(Sleeper.class.getName(), classWriter);
        Object sleep = sleeperClass.newInstance();
        for (Method method : sleeperClass.getDeclaredMethods()) {
            method.invoke(sleep);
        }
        System.out.println(sleeperClass.getField("timer").get(null));
    }

    public static class Sleeper {

        public void sleep() throws Exception {
            Thread.sleep(1000);
        }
    }

}
