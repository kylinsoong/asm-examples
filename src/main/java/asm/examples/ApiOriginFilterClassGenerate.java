package asm.examples;

import static org.objectweb.asm.Opcodes.ACC_PUBLIC;
import static org.objectweb.asm.Opcodes.ACC_INTERFACE;
import static org.objectweb.asm.Opcodes.ACC_SUPER;
import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.ARETURN;
import static org.objectweb.asm.Opcodes.ASTORE;
import static org.objectweb.asm.Opcodes.ATHROW;
import static org.objectweb.asm.Opcodes.BIPUSH;
import static org.objectweb.asm.Opcodes.DUP;
import static org.objectweb.asm.Opcodes.F_SAME1;
import static org.objectweb.asm.Opcodes.GETSTATIC;
import static org.objectweb.asm.Opcodes.ICONST_0;
import static org.objectweb.asm.Opcodes.ICONST_1;
import static org.objectweb.asm.Opcodes.INVOKESPECIAL;
import static org.objectweb.asm.Opcodes.INVOKEVIRTUAL;
import static org.objectweb.asm.Opcodes.NEW;
import static org.objectweb.asm.Opcodes.V1_5;

import java.lang.reflect.Method;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

public class ApiOriginFilterClassGenerate {
    
    static byte[] getApiOriginFilterClass() {
        
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        MethodVisitor mv;
        cw.visit(V1_5, ACC_PUBLIC + ACC_SUPER, "org/teiid/jboss/rest/ApiOriginFilter", null, "java/lang/Object", new String[]{"javax/servlet/Filter"});
        
        mv = cw.visitMethod(ACC_PUBLIC, "init", "(Ljavax/servlet/FilterConfig;)V", null, new String[] {"javax/servlet/ServletException"});
        mv.visitCode();
        mv.visitInsn(ATHROW);
        mv.visitMaxs(6, 3);
        mv.visitEnd();
        
        mv = cw.visitMethod(ACC_PUBLIC, "init", "(Ljavax/servlet/ServletRequest;Ljavax/servlet/ServletResponse;Ljavax/servlet/FilterChain;)V", null, new String[] {"javax/servlet/ServletException", "java/io/IOException"});
        mv.visitCode();
//        //TODO
        mv.visitInsn(ATHROW);
        mv.visitMaxs(6, 3);
        mv.visitEnd();
        
        mv = cw.visitMethod(ACC_PUBLIC, "destroy", "()V", null, null);
        mv.visitCode();
        mv.visitInsn(ATHROW);
        mv.visitMaxs(6, 3);
        mv.visitEnd();
        
        cw.visitEnd();
        
        return cw.toByteArray();
        
    }

    public static void main(String[] args) {

        byte[] bytes = getApiOriginFilterClass();
        Class cls = ASMUtilities.defineClass("org.teiid.jboss.rest.ApiOriginFilter", bytes);
        
        for(Class<?> in : cls.getInterfaces()) {
            System.out.println(in);
        }
        
        for(Method m : cls.getMethods()) {
            System.out.println(m);
        }
    }

}
