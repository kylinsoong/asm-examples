package asm.userguide;

import static org.objectweb.asm.Opcodes.ACC_PUBLIC;
import static org.objectweb.asm.Opcodes.ACC_SUPER;
import static org.objectweb.asm.Opcodes.V1_5;
import static org.objectweb.asm.Opcodes.ACC_PRIVATE;
import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.GETFIELD;
import static org.objectweb.asm.Opcodes.IRETURN;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;

import asm.examples.ASMUtilities;

public class BeanGenerate {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {

        ClassWriter cw = new ClassWriter(0);
        MethodVisitor mv;

        cw.visit(V1_5, ACC_PUBLIC + ACC_SUPER, "asm/userguides/Bean", null, "java/lang/Object", null);
        
        FieldVisitor fv = cw.visitField(ACC_PRIVATE, "f", "I", "I", null);
        fv.visitEnd();
        
        mv = cw.visitMethod(ACC_PUBLIC, "getF", "(I)I", null, null);
        mv.visitCode();
        mv.visitVarInsn(ALOAD, 0);
        mv.visitFieldInsn(GETFIELD, "asm/userguides/Bean", "f", "I");
        mv.visitInsn(IRETURN);
        mv.visitMaxs(1, 1);
        mv.visitEnd();
        
        cw.visitEnd();
        
        byte[] bytes = cw.toByteArray();
        Class cls = ASMUtilities.defineClass("asm.userguides.Bean", bytes);
        
        Object obj = cls.newInstance();
        
        System.out.println(obj);
    }

}
