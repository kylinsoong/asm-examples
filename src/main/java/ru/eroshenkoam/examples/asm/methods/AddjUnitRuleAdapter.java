package ru.eroshenkoam.examples.asm.methods;

import org.junit.Rule;
import org.junit.rules.TestName;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.*;
import static org.objectweb.asm.Type.getDescriptor;
import static org.objectweb.asm.Type.getInternalName;

/**
 * @author Artem Eroshenko eroshenkoam
 *         7/9/13, 7:39 PM
 */
public class AddJUnitRuleAdapter extends ClassVisitor {

    public static final String JUNIT_RULE_FIELD_NAME = "testName";

    public static final Class JUNIT_RULE_CLASS = TestName.class;

    public String owner;

    public boolean isInterface;

    public AddJUnitRuleAdapter(ClassVisitor cv) {
        super(ASM4, cv);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        cv.visit(version, access, name, signature, superName, interfaces);
        isInterface = (access & ACC_INTERFACE) != 0;
        owner = name;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor methodVisitor = cv.visitMethod(access, name, desc, signature, exceptions);
        if (!isInterface && methodVisitor != null && name.equals("<init>")) {
            methodVisitor = new AddJUnitRuleConstructorInitialisationAdapter(owner, methodVisitor);
        }
        return methodVisitor;
    }

    @Override
    public void visitEnd() {
        if (!isInterface) {
            FieldVisitor testName = cv.visitField(ACC_PUBLIC, JUNIT_RULE_FIELD_NAME,
                    getDescriptor(JUNIT_RULE_CLASS), null, null);
            if (testName != null) {
                testName.visitAnnotation(getDescriptor(Rule.class), true);
                testName.visitEnd();
            }
        }
        cv.visitEnd();
    }

    public class AddJUnitRuleConstructorInitialisationAdapter extends MethodVisitor {

        private String owner;

        public AddJUnitRuleConstructorInitialisationAdapter(String owner, MethodVisitor mv) {
            super(ASM4, mv);
            this.owner = owner;
        }

        @Override
        public void visitInsn(int opcode) {
            if ((opcode >= IRETURN && opcode <= RETURN) || opcode == ATHROW) {
                mv.visitVarInsn(ALOAD, 0);
                mv.visitTypeInsn(NEW, getInternalName(JUNIT_RULE_CLASS));
                mv.visitInsn(DUP);
                mv.visitMethodInsn(INVOKESPECIAL, getInternalName(JUNIT_RULE_CLASS), "<init>", "()V");
                mv.visitFieldInsn(PUTFIELD, owner, JUNIT_RULE_FIELD_NAME, getDescriptor(JUNIT_RULE_CLASS));
            }
            mv.visitInsn(opcode);
        }

        @Override
        public void visitMaxs(int maxStack, int maxLocals) {
            mv.visitMaxs(maxStack + 3, maxLocals + 1);
        }

    }

}
