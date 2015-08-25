package asm.examples;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class SubClassExample {

    public static void main(String[] args) throws IOException, InstantiationException, IllegalAccessException {

        ClassWriter sw = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
        sw.visit(Opcodes.V1_6, Opcodes.ACC_PUBLIC | Opcodes.ACC_SUPER, "test/SubCls", null, "test/SuperCls", null);
        sw.visitField(Opcodes.ACC_PUBLIC, "i", "I", null, null);

        MethodVisitor mv = sw.visitMethod(0, "<init>", "()V", null, null);
        // mv.visitMaxs(2, 1);
        mv.visitCode();
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "test/SuperCls", "<init>", "()V");
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitInsn(Opcodes.ICONST_2);
        mv.visitFieldInsn(Opcodes.PUTFIELD, "test/SubCls", "i", "I");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(1, 1);
        mv.visitEnd();

        sw.visitEnd();
        byte[] bytes = sw.toByteArray();
        
        byte[] archive = ASMUtilities.createRestArchive("test/SubCls.class", bytes);
        
        OutputStream outputStream = new FileOutputStream(new File("/home/kylin/tmp/jars/sub1.jar"));
        outputStream.write(archive);
        outputStream.close();
        
        Class cls = ASMUtilities.defineClass("test.SubCls", bytes);
        cls.newInstance();

    }

}
