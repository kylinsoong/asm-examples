package asm.examples;

import org.objectweb.asm.ClassWriter;


public class ASMUtilities {
    
    public static Class<?> defineClass(String name, byte[] bytes) {
        return new MyClassLoader(MyClassLoader.class.getClassLoader()).defineClassForName(name, bytes);
    }

    public static Class<?> defineClass(String name, ClassWriter writer) {
        return defineClass(name, writer.toByteArray());
    }
    
    private static  class MyClassLoader extends ClassLoader {

        public MyClassLoader(ClassLoader classLoader) {
            super(classLoader);
        }

        public Class<?> defineClassForName(String name, byte[] data) {
            return this.defineClass(name, data, 0, data.length);
        }

    }


}
