package org.sec.config;

import com.beust.jcommander.Parameter;

public class Command {
    @Parameter(names = {"-h", "--help"}, description = "Help Info", help = true)
    public boolean help;

    @Parameter(names = {"-u"},description = "Target Url")
    public String url;

    @Parameter(names = {"-k"},description = "Target Key")
    public String targetKey;
}
