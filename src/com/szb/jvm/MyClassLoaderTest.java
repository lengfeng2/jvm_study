package com.szb.jvm;

import java.io.FileInputStream;
import java.lang.reflect.Method;

public class MyClassLoaderTest {
    static class MyClassLoader extends ClassLoader {
        private String classpath;

        public MyClassLoader() {
        }

        public MyClassLoader(String classpath) {
            this.classpath = classpath;
        }

        private byte[] loadByte(String name) throws Exception {
            name = name.replaceAll("\\.", "/");
            FileInputStream fis = new FileInputStream(classpath + "/" + name + ".class");
            int len = fis.available();
            byte[] bytes = new byte[len];
            fis.read(bytes);
            fis.close();
            return bytes;
        }

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            try {
                byte[] bytes = loadByte(name);
                //defineClass将一个字节数组转为Class对象，这个字节数组是class文件读取后最终的字节数组。
                return defineClass(name, bytes, 0, bytes.length);
            } catch (Exception e) {
                e.printStackTrace();
                throw new ClassNotFoundException();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        MyClassLoader myClassLoader = new MyClassLoader("D:/test");
        Class<?> clazz = myClassLoader.loadClass("com.szb.jvm.User");
        Object o = clazz.newInstance();
        Method method = o.getClass().getDeclaredMethod("sout", null);
        method.invoke(o,null);
        System.out.println(clazz.getClassLoader().getClass().getName());
    }


}
