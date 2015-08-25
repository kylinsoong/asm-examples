package asm.examples;

import static org.objectweb.asm.Opcodes.ACC_PUBLIC;
import static org.objectweb.asm.Opcodes.ACC_STATIC;
import static org.objectweb.asm.Opcodes.ACC_SUPER;
import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.INVOKESPECIAL;
import static org.objectweb.asm.Opcodes.INVOKEVIRTUAL;
import static org.objectweb.asm.Opcodes.GETSTATIC;
import static org.objectweb.asm.Opcodes.RETURN;
import static org.objectweb.asm.Opcodes.V1_5;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

public class JavaHelloWorldGenerate {
    
    public static byte[] dump() throws Exception {
        
        ClassWriter cw = new ClassWriter(0);
        MethodVisitor mv;
        
        cw.visit(V1_5, ACC_PUBLIC + ACC_SUPER, "org/teiid/jboss/HelloWorld", null, "java/lang/Object", null);
        
        {
            mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESPECIAL,"java/lang/Object", "<init>", "()V");
            mv.visitInsn(RETURN);
            mv.visitMaxs(1, 1);
            mv.visitEnd();
        }
        
        {
            mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
            mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitLdcInsn("Hello World");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V");
            mv.visitInsn(RETURN);
            mv.visitMaxs(2, 1);
            mv.visitEnd();
        }
        
        cw.visitEnd();

        return cw.toByteArray();
    }

    public static void main(String[] args) throws IOException, Exception {

        byte[] archive = ASMUtilities.createRestArchive("org/teiid/jboss/HelloWorld.class", dump());
        
        OutputStream outputStream = new FileOutputStream(new File("/home/kylin/tmp/jars/h1.jar"));
        outputStream.write(archive);
        outputStream.close();

        
        System.out.println("DONE");
    }

}


