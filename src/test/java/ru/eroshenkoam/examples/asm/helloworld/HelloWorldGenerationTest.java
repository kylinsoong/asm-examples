package ru.eroshenkoam.examples.asm.helloworld;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author Artem Eroshenko eroshenkoam
 *         7/9/13, 12:27 PM
 */
@RunWith(Parameterized.class)
public class HelloWorldGenerationTest {

    private final static String MESSAGE = "Hello, World";

    private Class<?> helloWorldClass;

    public HelloWorldGenerationTest(Class<?> helloWorldClass) {
        this.helloWorldClass = helloWorldClass;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> generateHelloWorldClasses() {
        return Arrays.asList(
                new Object[]{HelloWorldClassGenerator.generateHelloWorldClassViaGeneratorAdapter(MESSAGE)},
                new Object[]{HelloWorldClassGenerator.generateHelloWorldClassViaMethodVisitor(MESSAGE)}
        );
    }

    @Test
    public void generatedHelloWorldClassShouldPrintPhraseInMainMethod() throws Exception {
        helloWorldClass.getMethods()[0].invoke(null, new Object[]{null});
    }
}
