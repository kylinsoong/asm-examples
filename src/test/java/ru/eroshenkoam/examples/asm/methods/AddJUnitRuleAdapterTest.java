package ru.eroshenkoam.examples.asm.methods;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;
import ru.eroshenkoam.examples.asm.ASMUtilities;

import java.lang.reflect.Field;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.objectweb.asm.Opcodes.ASM4;
import static org.objectweb.asm.Type.getInternalName;

/**
 * @author Artem Eroshenko eroshenkoam
 *         7/9/13, 8:00 PM
 */
public class AddJUnitRuleAdapterTest {

    @Test
    public void testOutput() throws Exception {

        ClassReader classReader = new ClassReader(getInternalName(JUnitTest.class));
        ClassWriter classWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_MAXS);

        ClassNode classNode = new ClassNode(ASM4);
        classReader.accept(classNode, 0);

        AddJUnitRuleAdapter addTimeAdapter = new AddJUnitRuleAdapter(classWriter);
        classReader.accept(addTimeAdapter, 0);

        Class<?> jUnitTestClass = ASMUtilities.defineClass(JUnitTest.class.getName(), classWriter);
        Object jUnitTest = jUnitTestClass.newInstance();
        Field field = jUnitTestClass.getField(AddJUnitRuleAdapter.JUNIT_RULE_FIELD_NAME);
        assertThat(field.getType(), equalTo(AddJUnitRuleAdapter.JUNIT_RULE_CLASS));
        assertThat(field.get(jUnitTest), notNullValue());
        assertThat(field.getAnnotation(Rule.class), instanceOf(Rule.class));

    }

    public static class JUnitTest {

        @Rule
        public TemporaryFolder folder;

        public JUnitTest() {
            folder = new TemporaryFolder();
        }

        @Test
        public void testOutput() {
            assertThat(true, equalTo(true));
        }

    }

}
