package com.serkan;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

public class App {

    public static void main(String[] args) throws LifecycleException, FileNotFoundException, IOException {

        Tomcat tomcat = new Tomcat();
        tomcat.setBaseDir(new File("/tmp/tomcat").getAbsolutePath());
        tomcat.setHostname(Config.INSTANCE.getProperty("SERVER_HOSTNAME"));
        tomcat.setPort(Config.INSTANCE.getProperty("SERVER_PORT", Integer.class));
        tomcat.getConnector();
        tomcat.addWebapp(Config.INSTANCE.getProperty("CONTEXT_PATH"), new File("deploy").getAbsolutePath());

        tomcat.start();
        tomcat.getServer().await();
    }
}