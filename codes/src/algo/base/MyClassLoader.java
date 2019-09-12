package algo.base;

import algo.string.Atoi;

import java.io.IOException;
import java.io.InputStream;

public class MyClassLoader {
    public static void main(String[] args) throws Exception {
        ClassLoader classLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                System.out.println(fileName);
                InputStream stream = getClass().getResourceAsStream(fileName);
                if (stream == null) {
                    System.out.println("load null");
                    return super.loadClass(name);
                }
                try {
                    byte[] b = new byte[stream.available()];
                    // 将流写入字节数组b中
                    stream.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return super.loadClass(name);
            }
        };
        Object obj = classLoader.loadClass("algo.base.Test").newInstance();
        System.out.println(obj.getClass());
        System.out.println(obj instanceof Test);

    }

}
