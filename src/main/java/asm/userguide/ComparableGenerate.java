package asm.userguide;

import static org.objectweb.asm.Opcodes.V1_5;
import static org.objectweb.asm.Opcodes.ACC_PUBLIC;
import static org.objectweb.asm.Opcodes.ACC_ABSTRACT;
import static org.objectweb.asm.Opcodes.ACC_INTERFACE;
import static org.objectweb.asm.Opcodes.ACC_FINAL;
import static org.objectweb.asm.Opcodes.ACC_STATIC;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.objectweb.asm.ClassWriter;

import asm.examples.ASMUtilities;

public class ComparableGenerate {
    
    static byte[] generate(){
        ClassWriter cw = new ClassWriter(0);
        cw.visit(V1_5, ACC_PUBLIC + ACC_ABSTRACT + ACC_INTERFACE, "asm/userguide/Comparable", null, "java/lang/Object", new String[] { "asm/userguide/Mesurable" });
        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "less", "I", null, new Integer(-1)).visitEnd();
        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "equal", "I", null, new Integer(-1)).visitEnd();
        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "greater", "I", null, new Integer(-1)).visitEnd();
        cw.visitMethod(ACC_PUBLIC + ACC_ABSTRACT, "compareTo", "(Ljava/lang/Object;)I", null, null).visitEnd();
        cw.visitEnd();
        return cw.toByteArray();
    }

    public static void main(String[] args) {

        byte[] bytes = generate();
        Class cls = ASMUtilities.defineClass("asm.userguide.Comparable", bytes);
        
        System.out.println(cls);
        
        for(Field f : cls.getFields()) {
            System.out.println(f);
        }
        
        for(Method m : cls.getMethods()) {
            System.out.println(m);
        }
    }

}
