package asm.examples;

import static org.objectweb.asm.Opcodes.ACC_PUBLIC;
import static org.objectweb.asm.Opcodes.ACC_SUPER;
import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.ATHROW;
import static org.objectweb.asm.Opcodes.BIPUSH;
import static org.objectweb.asm.Opcodes.DUP;
import static org.objectweb.asm.Opcodes.INVOKEINTERFACE;
import static org.objectweb.asm.Opcodes.INVOKESPECIAL;
import static org.objectweb.asm.Opcodes.INVOKEVIRTUAL;
import static org.objectweb.asm.Opcodes.NEW;
import static org.objectweb.asm.Opcodes.V1_5;

import java.lang.reflect.Method;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

public class BootstrapServletClassGenerate {
    
    static byte[] getBootstrapServletClass(String vdbName, String desc, String version, String[] schamas, String baseUrl, String packages, Boolean scan) {
        ClassWriter cw = new ClassWriter(0);
        MethodVisitor mv;
        cw.visit(V1_5, ACC_PUBLIC + ACC_SUPER, "org/teiid/jboss/rest/BootstrapServlet", null, "javax/servlet/http/HttpServlet", null);
        
        mv = cw.visitMethod(ACC_PUBLIC, "init", "(Ljavax/servlet/ServletConfig;)V", null, new String[] {"javax/servlet/ServletException"});
        mv.visitCode();
        mv.visitTypeInsn(NEW, "io/swagger/jaxrs/config/BeanConfig");
        mv.visitInsn(DUP);
        mv.visitMethodInsn(INVOKESPECIAL, "io/swagger/jaxrs/config/BeanConfig", "<init>", "()V");
        mv.visitLdcInsn(vdbName);
        mv.visitMethodInsn(INVOKEINTERFACE, "io/swagger/jaxrs/config/BeanConfig", "setTitle", "(Ljava/lang/String;)V");
        mv.visitLdcInsn(desc);
        mv.visitMethodInsn(INVOKEINTERFACE, "io/swagger/jaxrs/config/BeanConfig", "setDescription", "(Ljava/lang/String;)V");
        mv.visitLdcInsn(version);
        mv.visitMethodInsn(INVOKEINTERFACE, "io/swagger/jaxrs/config/BeanConfig", "setVersion", "(Ljava/lang/String;)V");
//        mv.visitLdcInsn(schamas);
//        mv.visitMethodInsn(INVOKEINTERFACE, "io/swagger/jaxrs/config/BeanConfig", "setSchemes", "([Ljava/lang/String;)V");
        mv.visitLdcInsn(baseUrl);
        mv.visitMethodInsn(INVOKEINTERFACE, "io/swagger/jaxrs/config/BeanConfig", "setBasePath", "(Ljava/lang/String;)V");
        mv.visitLdcInsn(packages);
        mv.visitMethodInsn(INVOKEINTERFACE, "io/swagger/jaxrs/config/BeanConfig", "setResourcePackage", "(Ljava/lang/String;)V");
        mv.visitLdcInsn(scan);
        mv.visitMethodInsn(INVOKEINTERFACE, "io/swagger/jaxrs/config/BeanConfig", "setScan", "(Z)V");
        mv.visitMaxs(6, 3);
        mv.visitEnd();
        
        mv = cw.visitMethod(ACC_PUBLIC, "doGet", "(Ljavax/servlet/http/HttpServletRequest;Ljavax/servlet/http/HttpServletResponse;)V", null, new String[] {"javax/servlet/ServletException", "java/io/IOException"});
        mv.visitCode();
        //TODO
        mv.visitInsn(ATHROW);
        mv.visitMaxs(6, 3);
        mv.visitEnd();
        
        mv = cw.visitMethod(ACC_PUBLIC, "doPost", "(Ljavax/servlet/http/HttpServletRequest;Ljavax/servlet/http/HttpServletResponse;)V", null, new String[] {"javax/servlet/ServletException", "java/io/IOException"});
        mv.visitCode();
        mv.visitMethodInsn(INVOKEVIRTUAL, "org/teiid/jboss/rest/BootstrapServlet", "doGet", "(Ljavax/servlet/http/HttpServletRequest;Ljavax/servlet/http/HttpServletResponse;)V");
        mv.visitMaxs(6, 3);
        mv.visitEnd();
        
        cw.visitEnd();
        
        return cw.toByteArray();
    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {

        byte[] bytes = getBootstrapServletClass("vdbName", "desc", "version", new String[]{"http"}, "baseUrl", "packages", true);
        Class cls = ASMUtilities.defineClass("org.teiid.jboss.rest.BootstrapServlet", bytes);
            
        System.out.println(cls.getSuperclass());
        
//        cls.newInstance();
        
//        for(Method m : cls.getMethods()) {
//            System.out.println(m);
//        }

    }

}
