package asm.examples;

import static org.objectweb.asm.Opcodes.ACC_PUBLIC;
import static org.objectweb.asm.Opcodes.ACC_INTERFACE;
import static org.objectweb.asm.Opcodes.ACC_SUPER;
import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.ARETURN;
import static org.objectweb.asm.Opcodes.ASTORE;
import static org.objectweb.asm.Opcodes.ATHROW;
import static org.objectweb.asm.Opcodes.BIPUSH;
import static org.objectweb.asm.Opcodes.CHECKCAST;
import static org.objectweb.asm.Opcodes.DUP;
import static org.objectweb.asm.Opcodes.F_SAME1;
import static org.objectweb.asm.Opcodes.GETSTATIC;
import static org.objectweb.asm.Opcodes.ICONST_0;
import static org.objectweb.asm.Opcodes.ICONST_1;
import static org.objectweb.asm.Opcodes.INVOKEINTERFACE;
import static org.objectweb.asm.Opcodes.INVOKESPECIAL;
import static org.objectweb.asm.Opcodes.INVOKEVIRTUAL;
import static org.objectweb.asm.Opcodes.NEW;
import static org.objectweb.asm.Opcodes.RETURN;
import static org.objectweb.asm.Opcodes.V1_5;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

public class ApiOriginFilterClassGenerate {
    
    static byte[] getApiOriginFilterClass(String k1, String v1, String k2, String v2, String k3, String v3) {
        
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        MethodVisitor mv;
        AnnotationVisitor av0;
        
        cw.visit(V1_5, ACC_PUBLIC + ACC_SUPER, "org/teiid/jboss/rest/ApiOriginFilter", null, "java/lang/Object", new String[]{"javax/servlet/Filter"});
        
        {
            mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESPECIAL,"java/lang/Object", "<init>", "()V");
            mv.visitInsn(RETURN);
            mv.visitMaxs(1, 1);
            mv.visitEnd();
        }
        
        {
            mv = cw.visitMethod(ACC_PUBLIC, "init", "(Ljavax/servlet/FilterConfig;)V", null, new String[] {"javax/servlet/ServletException"});
            av0 = mv.visitAnnotation("Ljava/lang/Override;", true);
            av0.visitEnd();
            mv.visitCode();
            mv.visitInsn(RETURN);
            mv.visitMaxs(1, 1);
            mv.visitEnd();
        }
        
        {
            mv = cw.visitMethod(ACC_PUBLIC, "doFilter", "(Ljavax/servlet/ServletRequest;Ljavax/servlet/ServletResponse;Ljavax/servlet/FilterChain;)V", null, new String[] {"javax/servlet/ServletException", "java/io/IOException"});
            av0 = mv.visitAnnotation("Ljava/lang/Override;", true);
            av0.visitEnd();
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 2);
            mv.visitTypeInsn(CHECKCAST, "javax/servlet/http/HttpServletResponse");
            mv.visitVarInsn(ASTORE, 4);
            mv.visitVarInsn(ALOAD, 4);
            mv.visitLdcInsn(k1);
            mv.visitLdcInsn(v1);
            mv.visitMethodInsn(INVOKEINTERFACE, "javax/servlet/http/HttpServletResponse", "addHeader", "(Ljava/lang/String;Ljava/lang/String;)V");
            mv.visitVarInsn(ALOAD, 4);
            mv.visitLdcInsn(k2);
            mv.visitLdcInsn(v2);
            mv.visitMethodInsn(INVOKEINTERFACE, "javax/servlet/http/HttpServletResponse", "addHeader", "(Ljava/lang/String;Ljava/lang/String;)V");
            mv.visitVarInsn(ALOAD, 4);
            mv.visitLdcInsn(k3);
            mv.visitLdcInsn(v3);
            mv.visitMethodInsn(INVOKEINTERFACE, "javax/servlet/http/HttpServletResponse", "addHeader", "(Ljava/lang/String;Ljava/lang/String;)V");
            mv.visitVarInsn(ALOAD, 3);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitMethodInsn(INVOKEINTERFACE, "javax/servlet/FilterChain", "doFilter", "(Ljavax/servlet/ServletRequest;Ljavax/servlet/ServletResponse;)V");
            mv.visitInsn(RETURN);
            mv.visitMaxs(6, 3);
            mv.visitEnd();
        }
        
        {
            mv = cw.visitMethod(ACC_PUBLIC, "destroy", "()V", null, null);
            av0 = mv.visitAnnotation("Ljava/lang/Override;", true);
            av0.visitEnd();
            mv.visitCode();
            mv.visitInsn(RETURN);
            mv.visitMaxs(1, 1);
            mv.visitEnd();
        }
        
        cw.visitEnd();
        
        return cw.toByteArray();
           
    }

    public static void main(String[] args) throws IOException, InstantiationException, IllegalAccessException {

        byte[] bytes = getApiOriginFilterClass("Access-Control-Allow-Origin", "*", "Access-Control-Allow-Methods", "GET, POST, DELETE, PUT", "Access-Control-Allow-Headers", "Content-Type");
       
        byte[] archive = ASMUtilities.createRestArchive("org/teiid/jboss/rest/ApiOriginFilter.class", bytes);
        
        System.out.println(archive.length);
        
        OutputStream outputStream = new FileOutputStream(new File("/home/kylin/tmp/jars/api7.jar"));
        outputStream.write(archive);
        outputStream.close();
        
        System.out.println(new File("/home/kylin/tmp/jars/api7.jar").length());

        
        System.out.println("DONE");
        
        Class cls = ASMUtilities.defineClass("org.teiid.jboss.rest.ApiOriginFilter", bytes);
        
        System.out.println(cls);
        
        cls.newInstance();
        
    }

}
