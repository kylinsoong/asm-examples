package asm.examples;

import static org.objectweb.asm.Opcodes.AASTORE;
import static org.objectweb.asm.Opcodes.ACC_PUBLIC;
import static org.objectweb.asm.Opcodes.ACC_SUPER;
import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.ANEWARRAY;
import static org.objectweb.asm.Opcodes.ASTORE;
import static org.objectweb.asm.Opcodes.DUP;
import static org.objectweb.asm.Opcodes.ICONST_0;
import static org.objectweb.asm.Opcodes.ICONST_1;
import static org.objectweb.asm.Opcodes.ICONST_2;
import static org.objectweb.asm.Opcodes.ICONST_3;
import static org.objectweb.asm.Opcodes.ICONST_4;
import static org.objectweb.asm.Opcodes.ICONST_5;
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

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

public class BootstrapServletClassGenerate {
    
    static byte[] getBootstrapServletClass(String vdbName, String desc, String version, String[] schamas, String baseUrl, String packages, Boolean scan) {
        
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        MethodVisitor mv;
        AnnotationVisitor av0;
   
        cw.visit(V1_5, ACC_PUBLIC + ACC_SUPER, "org/teiid/jboss/rest/BootstrapServlet", null, "javax/servlet/http/HttpServlet", null);
        
        {
            mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESPECIAL, "javax/servlet/http/HttpServlet", "<init>", "()V");
            mv.visitInsn(RETURN);
            mv.visitMaxs(1, 1);
            mv.visitEnd();
        }
        
        //init method
        {
            mv = cw.visitMethod(ACC_PUBLIC, "init", "(Ljavax/servlet/ServletConfig;)V", null, new String[] {"javax/servlet/ServletException"});
            av0 = mv.visitAnnotation("Ljava/lang/Override;", true);
            av0.visitEnd();
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKESPECIAL, "javax/servlet/http/HttpServlet", "init", "(Ljavax/servlet/ServletConfig;)V");
            mv.visitTypeInsn(NEW, "io/swagger/jaxrs/config/BeanConfig");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "io/swagger/jaxrs/config/BeanConfig", "<init>", "()V");
            mv.visitVarInsn(ASTORE, 2);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitLdcInsn(vdbName);
            mv.visitMethodInsn(INVOKEVIRTUAL, "io/swagger/jaxrs/config/BeanConfig", "setTitle", "(Ljava/lang/String;)V");
            mv.visitVarInsn(ALOAD, 2);
            mv.visitLdcInsn(desc);
            mv.visitMethodInsn(INVOKEVIRTUAL, "io/swagger/jaxrs/config/BeanConfig", "setDescription", "(Ljava/lang/String;)V");
            mv.visitVarInsn(ALOAD, 2);
            mv.visitLdcInsn(version);
            mv.visitMethodInsn(INVOKEVIRTUAL, "io/swagger/jaxrs/config/BeanConfig", "setVersion", "(Ljava/lang/String;)V");
            mv.visitVarInsn(ALOAD, 2);
            mv.visitInsn(ICONST_1);
            mv.visitTypeInsn(ANEWARRAY, "java/lang/String");
            Integer[] array = new Integer[]{ICONST_0, ICONST_1, ICONST_2, ICONST_3, ICONST_4, ICONST_5};
            for(int i = 0 ; i < schamas.length ; i ++) {
                mv.visitInsn(DUP);
                mv.visitInsn(array[i]);
                mv.visitLdcInsn(schamas[i]);
                mv.visitInsn(AASTORE);
            }
            mv.visitMethodInsn(INVOKEVIRTUAL, "io/swagger/jaxrs/config/BeanConfig", "setSchemes", "([Ljava/lang/String;)V");
            mv.visitVarInsn(ALOAD, 2);
            mv.visitLdcInsn(baseUrl);
            mv.visitMethodInsn(INVOKEVIRTUAL, "io/swagger/jaxrs/config/BeanConfig", "setBasePath", "(Ljava/lang/String;)V");
            mv.visitVarInsn(ALOAD, 2);
            mv.visitLdcInsn(packages);
            mv.visitMethodInsn(INVOKEVIRTUAL, "io/swagger/jaxrs/config/BeanConfig", "setResourcePackage", "(Ljava/lang/String;)V");
            mv.visitVarInsn(ALOAD, 2);
            mv.visitInsn(scan?ICONST_1:ICONST_0);
            mv.visitMethodInsn(INVOKEVIRTUAL, "io/swagger/jaxrs/config/BeanConfig", "setScan", "(Z)V");
            mv.visitInsn(RETURN);
            mv.visitMaxs(3, 1);
            mv.visitEnd();
        }
        
        // doGet method
        {
            mv = cw.visitMethod(ACC_PUBLIC, "doGet", "(Ljavax/servlet/http/HttpServletRequest;Ljavax/servlet/http/HttpServletResponse;)V", null, new String[] {"javax/servlet/ServletException", "java/io/IOException"});
            av0 = mv.visitAnnotation("Ljava/lang/Override;", true);
            av0.visitEnd();
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/teiid/jboss/rest/BootstrapServlet", "doPost", "(Ljavax/servlet/http/HttpServletRequest;Ljavax/servlet/http/HttpServletResponse;)V");
            mv.visitInsn(RETURN);
            mv.visitMaxs(3, 1);
            mv.visitEnd();
        }
        
        // doPost method
        {
            mv = cw.visitMethod(ACC_PUBLIC, "doPost", "(Ljavax/servlet/http/HttpServletRequest;Ljavax/servlet/http/HttpServletResponse;)V", null, new String[] {"javax/servlet/ServletException", "java/io/IOException"});
            AnnotationVisitor av2 = mv.visitAnnotation("Ljava/lang/Override;", true);
            av2.visitEnd();
            mv.visitCode();
            
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEINTERFACE, "javax/servlet/http/HttpServletRequest", "getContextPath", "()Ljava/lang/String;");
            mv.visitVarInsn(ASTORE, 3);
            mv.visitTypeInsn(NEW, "java/lang/StringBuilder");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V");
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEINTERFACE, "javax/servlet/http/HttpServletRequest", "getScheme", "()Ljava/lang/String;");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;");
            mv.visitLdcInsn("://");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;");
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEINTERFACE, "javax/servlet/http/HttpServletRequest", "getServerName", "()Ljava/lang/String;");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;");
            mv.visitLdcInsn(":");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;");
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEINTERFACE, "javax/servlet/http/HttpServletRequest", "getServerPort", "()I");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(I)Ljava/lang/StringBuilder;");
            mv.visitVarInsn(ALOAD, 3);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;");
            mv.visitLdcInsn("/");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;");
            mv.visitVarInsn(ASTORE, 4);
            mv.visitTypeInsn(NEW, "java/lang/StringBuilder");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V");
            mv.visitVarInsn(ALOAD, 3);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;");
            mv.visitLdcInsn("/api.html");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;");
            mv.visitVarInsn(ASTORE, 5);
            mv.visitLdcInsn("/swagger.json");
            mv.visitVarInsn(ASTORE, 6);
            mv.visitTypeInsn(NEW, "java/lang/StringBuilder");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V");
            mv.visitLdcInsn("/url=");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;");
            mv.visitVarInsn(ALOAD, 4);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;");
            mv.visitVarInsn(ALOAD, 6);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;");
            mv.visitVarInsn(ASTORE, 7);
            mv.visitTypeInsn(NEW, "java/lang/StringBuilder");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V");
            mv.visitVarInsn(ALOAD, 5);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;");
            mv.visitLdcInsn("?");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;");
            mv.visitVarInsn(ALOAD, 7);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;");
            mv.visitVarInsn(ASTORE, 8);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitVarInsn(ALOAD, 8);
            mv.visitMethodInsn(INVOKEINTERFACE, "javax/servlet/http/HttpServletResponse", "sendRedirect", "(Ljava/lang/String;)V");
            
            mv.visitInsn(RETURN);
            mv.visitMaxs(8, 8);
            mv.visitEnd();
        }
        
        cw.visitEnd();
        
        return cw.toByteArray();
    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, IOException {

        byte[] bytes = getBootstrapServletClass("vdbName", "", "version", new String[]{"http", "https"}, "baseUrl", "packages", true);
        
        byte[] archive = ASMUtilities.createRestArchive("org/teiid/jboss/rest/BootstrapServlet.class", bytes);
                
        OutputStream outputStream = new FileOutputStream(new File("/home/kylin/tmp/jars/19.jar"));
        outputStream.write(archive);
        outputStream.close();

        
        System.out.println("DONE");
        
        Class cls = ASMUtilities.defineClass("org.teiid.jboss.rest.BootstrapServlet", bytes);
        cls.newInstance();
            
//        System.out.println(cls.getSuperclass());
        
//        cls.newInstance();
        
//        for(Method m : cls.getMethods()) {
//            System.out.println(m);
//        }

    }

}
