package ru.eroshenkoam.examples.asm.fields;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.GeneratorAdapter;
import org.objectweb.asm.commons.Method;
import ru.eroshenkoam.examples.asm.ASMUtilities;

import java.util.ArrayList;
import java.util.List;

import static org.objectweb.asm.Opcodes.*;
import static org.objectweb.asm.Type.getDescriptor;
import static org.objectweb.asm.Type.getInternalName;

/**
 * @author Artem Eroshenko eroshenkoam
 *         7/9/13, 1:47 PM
 */
public class ClassWithListFieldGenerator {

    public static final String CLASS_NAME = "Items";

    public static Class<?> generateClassWithListField() {

        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        classWriter.visit(V1_6, ACC_PUBLIC, CLASS_NAME, null, getInternalName(Object.class), null);

        classWriter.visitField(ACC_PUBLIC, "ids", getDescriptor(List.class), "Ljava/util/List<Ljava/lang/Integer;>;", null).visitEnd();

        MethodVisitor constructor = classWriter.visitMethod(
                ACC_PUBLIC,
                "<init>",
                "()V",
                null,
                null);
        constructor.visitVarInsn(ALOAD, 0);
        constructor.visitMethodInsn(INVOKESPECIAL, getInternalName(Object.class), "<init>", "()V");
        constructor.visitVarInsn(ALOAD, 0);
        constructor.visitTypeInsn(NEW, getInternalName(ArrayList.class));
        constructor.visitInsn(DUP);
        constructor.visitMethodInsn(INVOKESPECIAL, getInternalName(ArrayList.class), "<init>", "()V");
        constructor.visitFieldInsn(PUTFIELD, "Items", "ids", getDescriptor(List.class));
        constructor.visitInsn(RETURN);
        constructor.visitMaxs(1, 1);
        constructor.visitEnd();

//        Method constructorMethod = Method.getMethod("void <init> ()");
//        GeneratorAdapter constructor = new GeneratorAdapter(ACC_PUBLIC, constructorMethod, null, null, classWriter);
//        constructor.loadThis();
//        constructor.invokeConstructor(Type.getType(Object.class), constructorMethod);
//        constructor.newInstance(Type.getType(ArrayList.class));
//        constructor.dup();
//        constructor.invokeConstructor(Type.getType(ArrayList.class), constructorMethod);
//        constructor.putField(Type.getType("LItems;"), "ids", Type.getType(List.class));
//        constructor.returnValue();
//        constructor.endMethod();


        return ASMUtilities.defineClass(CLASS_NAME, classWriter);
    }
}
