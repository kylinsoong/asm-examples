package ru.eroshenkoam.examples.asm.fields;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.TestName;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import ru.eroshenkoam.examples.asm.ASMUtilities;

import static org.objectweb.asm.Opcodes.*;
import static org.objectweb.asm.Type.getDescriptor;
import static org.objectweb.asm.Type.getInternalName;

/**
 * @author Artem Eroshenko eroshenkoam
 *         7/9/13, 3:58 PM
 */
public class JUnitRuleClassGenerator {

    public static String CLASS_NAME = "Test";

    public static Class<?> generateClassWithJUnitRule() {

        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        classWriter.visit(V1_6, ACC_PUBLIC, CLASS_NAME, null, getInternalName(Object.class), null);

        classWriter.visitField(ACC_PUBLIC + ACC_STATIC, "folder", getDescriptor(TemporaryFolder.class), null, null).
                visitAnnotation(getDescriptor(ClassRule.class), true).
                visitEnd();

        classWriter.visitField(ACC_PUBLIC, "testName", getDescriptor(TestName.class), null, null).
                visitAnnotation(getDescriptor(Rule.class), true).
                visitEnd();

        MethodVisitor staticConstructor = classWriter.visitMethod(
                ACC_STATIC,
                "<clinit>",
                "()V",
                null,
                null);

        staticConstructor.visitCode();
        staticConstructor.visitTypeInsn(NEW, getInternalName(TemporaryFolder.class));
        staticConstructor.visitInsn(DUP);
        staticConstructor.visitMethodInsn(INVOKESPECIAL, getInternalName(TemporaryFolder.class), "<init>", "()V");
        staticConstructor.visitFieldInsn(PUTSTATIC, CLASS_NAME, "folder", getDescriptor(TemporaryFolder.class));
        staticConstructor.visitInsn(RETURN);
        staticConstructor.visitMaxs(3, 1);
        staticConstructor.visitEnd();

        MethodVisitor constructor = classWriter.visitMethod(
                ACC_PUBLIC,
                "<init>",
                "()V",
                null,
                null);
        constructor.visitCode();
        constructor.visitVarInsn(ALOAD, 0);
        constructor.visitMethodInsn(INVOKESPECIAL, getInternalName(Object.class), "<init>", "()V");
        constructor.visitVarInsn(ALOAD, 0);
        constructor.visitTypeInsn(NEW, getInternalName(TestName.class));
        constructor.visitInsn(DUP);
        constructor.visitMethodInsn(INVOKESPECIAL, getInternalName(TestName.class), "<init>", "()V");
        constructor.visitFieldInsn(PUTFIELD, CLASS_NAME, "testName", getDescriptor(TestName.class));
        constructor.visitInsn(RETURN);
        constructor.visitMaxs(4, 2);
        constructor.visitEnd();

        return ASMUtilities.defineClass(CLASS_NAME, classWriter);

    }
}
