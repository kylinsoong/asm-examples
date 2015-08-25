package asm.examples;

import static org.objectweb.asm.Opcodes.ACC_PRIVATE;
import static org.objectweb.asm.Opcodes.ACC_PUBLIC;
import static org.objectweb.asm.Opcodes.ACC_SUPER;
import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.ARETURN;
import static org.objectweb.asm.Opcodes.DUP;
import static org.objectweb.asm.Opcodes.GETFIELD;
import static org.objectweb.asm.Opcodes.INVOKEINTERFACE;
import static org.objectweb.asm.Opcodes.INVOKESPECIAL;
import static org.objectweb.asm.Opcodes.NEW;
import static org.objectweb.asm.Opcodes.POP;
import static org.objectweb.asm.Opcodes.PUTFIELD;
import static org.objectweb.asm.Opcodes.RETURN;
import static org.objectweb.asm.Opcodes.V1_5;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;

public class RestApplicationClassGenerate {
    
    public static byte[] getApplicationClass(ArrayList<String> models) {
        ClassWriter cw = new ClassWriter(0);
        FieldVisitor fv;
        MethodVisitor mv;

        cw.visit(V1_5, ACC_PUBLIC + ACC_SUPER, "org/teiid/jboss/rest/TeiidRestApplication", null, "javax/ws/rs/core/Application", null);

        {
        fv = cw.visitField(ACC_PRIVATE, "singletons", "Ljava/util/Set;", "Ljava/util/Set<Ljava/lang/Object;>;", null);
        fv.visitEnd();
        }
        
        {
        fv = cw.visitField(ACC_PRIVATE, "empty", "Ljava/util/Set;", "Ljava/util/Set<Ljava/lang/Class<*>;>;", null);
        fv.visitEnd();
        }
        
        {
        mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
        mv.visitCode();
        mv.visitVarInsn(ALOAD, 0);
        mv.visitMethodInsn(INVOKESPECIAL, "javax/ws/rs/core/Application", "<init>", "()V");
        mv.visitVarInsn(ALOAD, 0);
        mv.visitTypeInsn(NEW, "java/util/HashSet");
        mv.visitInsn(DUP);
        mv.visitMethodInsn(INVOKESPECIAL, "java/util/HashSet", "<init>", "()V");
        mv.visitFieldInsn(PUTFIELD, "org/teiid/jboss/rest/TeiidRestApplication", "singletons", "Ljava/util/Set;");
        mv.visitVarInsn(ALOAD, 0);
        mv.visitTypeInsn(NEW, "java/util/HashSet");
        mv.visitInsn(DUP);
        mv.visitMethodInsn(INVOKESPECIAL, "java/util/HashSet", "<init>", "()V");
        mv.visitFieldInsn(PUTFIELD, "org/teiid/jboss/rest/TeiidRestApplication", "empty", "Ljava/util/Set;");
        mv.visitVarInsn(ALOAD, 0);
        
        mv.visitFieldInsn(GETFIELD, "org/teiid/jboss/rest/TeiidRestApplication", "singletons", "Ljava/util/Set;");
        mv.visitTypeInsn(NEW, "io/swagger/jaxrs/listing/ApiListingResource");
        mv.visitInsn(DUP);
        mv.visitMethodInsn(INVOKESPECIAL, "io/swagger/jaxrs/listing/ApiListingResource", "<init>", "()V");
        mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Set", "add", "(Ljava/lang/Object;)Z");
        mv.visitInsn(POP);
        mv.visitVarInsn(ALOAD, 0);
        
        mv.visitFieldInsn(GETFIELD, "org/teiid/jboss/rest/TeiidRestApplication", "singletons", "Ljava/util/Set;");
        mv.visitTypeInsn(NEW, "io/swagger/jaxrs/listing/SwaggerSerializers");
        mv.visitInsn(DUP);
        mv.visitMethodInsn(INVOKESPECIAL, "io/swagger/jaxrs/listing/SwaggerSerializers", "<init>", "()V");
        mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Set", "add", "(Ljava/lang/Object;)Z");
        mv.visitInsn(POP);
        mv.visitVarInsn(ALOAD, 0);
        
        for (int i = 0; i < models.size(); i++) {
            mv.visitFieldInsn(GETFIELD, "org/teiid/jboss/rest/TeiidRestApplication", "singletons", "Ljava/util/Set;");
            mv.visitTypeInsn(NEW, "org/teiid/jboss/rest/"+models.get(i));
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "org/teiid/jboss/rest/"+models.get(i), "<init>", "()V");
            mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Set", "add", "(Ljava/lang/Object;)Z");
            mv.visitInsn(POP);
            if (i < models.size()-1) {
                mv.visitVarInsn(ALOAD, 0);
            }
        }
        
        mv.visitInsn(RETURN);
        mv.visitMaxs(3, 1);
        mv.visitEnd();
        }
        
        {
        mv = cw.visitMethod(ACC_PUBLIC, "getClasses", "()Ljava/util/Set;", "()Ljava/util/Set<Ljava/lang/Class<*>;>;", null);
        mv.visitCode();
        mv.visitVarInsn(ALOAD, 0);
        mv.visitFieldInsn(GETFIELD, "org/teiid/jboss/rest/TeiidRestApplication", "empty", "Ljava/util/Set;");
        mv.visitInsn(ARETURN);
        mv.visitMaxs(1, 1);
        mv.visitEnd();
        }
        {
        mv = cw.visitMethod(ACC_PUBLIC, "getSingletons", "()Ljava/util/Set;", "()Ljava/util/Set<Ljava/lang/Object;>;", null);
        mv.visitCode();
        mv.visitVarInsn(ALOAD, 0);
        mv.visitFieldInsn(GETFIELD, "org/teiid/jboss/rest/TeiidRestApplication", "singletons", "Ljava/util/Set;");
        mv.visitInsn(ARETURN);
        mv.visitMaxs(1, 1);
        mv.visitEnd();
        }
        cw.visitEnd();

        return cw.toByteArray();
    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        ArrayList<String> models = new ArrayList<String> ();
        models.add("View");
        byte[] bytes = getApplicationClass(models);
        
        Class cls = ASMUtilities.defineClass("org.teiid.jboss.rest.TeiidRestApplication", bytes);
        
        Object obj = cls.newInstance();
        
        System.out.println(obj);
        
        for(Constructor o : cls.getConstructors()) {
            System.out.println(o);
        }
        
        for(Method m : cls.getMethods()) {
            if(m.getName().equals("getSingletons")){
                System.out.println(m.invoke(obj, new Object[]{}));
            }
            
            
        }
        
        
//        System.out.println(obj);
    }
    

}
