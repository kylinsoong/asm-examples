package asm.examples;

import static org.objectweb.asm.Opcodes.ACC_ENUM;
import static org.objectweb.asm.Opcodes.ACC_FINAL;
import static org.objectweb.asm.Opcodes.ACC_PUBLIC;
import static org.objectweb.asm.Opcodes.ACC_STATIC;
import static org.objectweb.asm.Opcodes.ACC_SUPER;
import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.INVOKESPECIAL;
import static org.objectweb.asm.Opcodes.RETURN;
import static org.objectweb.asm.Opcodes.V1_6;

import java.util.Collection;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;


public class ViewClassGenerate {
    
    static byte[] getViewClass(String modelName) {
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        MethodVisitor mv;
        AnnotationVisitor av0;
        boolean hasValidProcedures = false;
        
        cw.visit(V1_6, ACC_PUBLIC + ACC_SUPER, "org/teiid/jboss/rest/"+modelName, null, "org/teiid/jboss/rest/TeiidRSProvider", null);

        {
        av0 = cw.visitAnnotation("Ljavax/ws/rs/Path;", true);
        av0.visit("value", "/"+modelName.toLowerCase());
        av0.visitEnd();
        }
        cw.visitInnerClass("javax/ws/rs/core/Response$Status", "javax/ws/rs/core/Response", "Status", ACC_PUBLIC + ACC_FINAL + ACC_STATIC + ACC_ENUM);

        {
        mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
        mv.visitCode();
        mv.visitVarInsn(ALOAD, 0);
        mv.visitMethodInsn(INVOKESPECIAL, "org/teiid/jboss/rest/TeiidRSProvider", "<init>", "()V");
        mv.visitInsn(RETURN);
        mv.visitMaxs(1, 1);
        mv.visitEnd();
        }
        
        
        cw.visitEnd();

        if (!hasValidProcedures) {
            return null;
        }
        return cw.toByteArray();
    }

    public static void main(String[] args) {

        byte[] bytes = getViewClass("View");
        
        System.out.println(bytes);
    }

}
