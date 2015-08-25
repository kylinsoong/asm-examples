package asm.examples;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.objectweb.asm.ClassWriter;


public class ASMUtilities {
    
    public static byte[] createRestArchive(String name, byte[] bytes) throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        ZipOutputStream out = new ZipOutputStream(byteStream); 
        writeEntry(name, out, bytes);
        out.close();
        return byteStream.toByteArray();
    }
    
    static void writeEntry(String name, ZipOutputStream out, byte[] contents) throws IOException {
        ZipEntry e = new ZipEntry(name); 
        out.putNextEntry(e);
        write(new ByteArrayInputStream(contents), out, 1024);
        out.closeEntry();
    }
    
    public static void write(InputStream fis, OutputStream outputStream, int bufferSize) throws IOException {             
        byte[] buff = new byte[bufferSize];
        int bytesRead;

        // Simple read/write loop.
        while(-1 != (bytesRead = fis.read(buff, 0, buff.length))) {
            outputStream.write(buff, 0, bytesRead);
        } 
        outputStream.flush();
    }         
    
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
