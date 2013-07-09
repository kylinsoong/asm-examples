package ru.eroshenkoam.examples.asm.fields;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Type;
import ru.eroshenkoam.examples.asm.ASMUtilities;

import static org.objectweb.asm.Opcodes.*;
import static org.objectweb.asm.Type.getInternalName;

/**
 * @author Artem Eroshenko eroshenkoam
 *         7/9/13, 12:56 PM
 */
public class ClassWithPrimitiveFieldGenerator {

    public static final String GENERATED_CLASS_NAME = "Counter";

    public static Class<?> generateClassWithStaticIntegerField(Object value) {
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        classWriter.visit(V1_6, ACC_PUBLIC, GENERATED_CLASS_NAME, null, getInternalName(Object.class), null);
        classWriter.visitField(ACC_PUBLIC + ACC_STATIC, "index", Type.getDescriptor(int.class), null, value).visitEnd();
        return ASMUtilities.defineClass(GENERATED_CLASS_NAME, classWriter);
    }

    public static Class<?> generateClassWithStaticIntegerField() {
        return generateClassWithStaticIntegerField(null);
    }
}
