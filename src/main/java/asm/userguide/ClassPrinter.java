package asm.userguide;

import java.io.IOException;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;

public class ClassPrinter implements ClassVisitor{
    
    public static void main(String[] args) throws IOException {
        ClassPrinter cp = new ClassPrinter();
        ClassReader cr = new ClassReader("java.lang.Runnable");
        cr.accept(cp, 0);
    }


    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        System.out.println(name + " extends " + superName + " {");
    }

    public AnnotationVisitor visitAnnotation(String arg0, boolean arg1) {
        return null;
    }

    public void visitAttribute(Attribute arg0) {
        
    }

    public void visitEnd() {
        System.out.println("}");
    }

    public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
        System.out.println(" " + desc + " " + name);
        return null;
    }

    public void visitInnerClass(String arg0, String arg1, String arg2, int arg3) {
        // TODO Auto-generated method stub
        
    }

    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        System.out.println(" " + name + desc);
        return null;
    }

    public void visitOuterClass(String arg0, String arg1, String arg2) {
        // TODO Auto-generated method stub
        
    }

    public void visitSource(String arg0, String arg1) {
        // TODO Auto-generated method stub
        
    }

}
