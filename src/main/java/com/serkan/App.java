package com.serkan;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.scan.StandardJarScanner;

public class App {

    public static void main(String[] args) throws LifecycleException, FileNotFoundException, IOException {

        Tomcat tomcat = new Tomcat();
        tomcat.setBaseDir(new File("/tmp/tomcat").getAbsolutePath());
        tomcat.setHostname(Config.instance.getProperty("SERVER_HOSTNAME"));
        tomcat.setPort(Config.instance.getProperty("SERVER_PORT", Integer.class));
        tomcat.getConnector();

        var context = tomcat.addWebapp(Config.instance.getProperty("CONTEXT_PATH"), new File("deploy").getAbsolutePath());
        ((StandardJarScanner) context.getJarScanner()).setScanManifest(false);

        tomcat.start();
        tomcat.getServer().await();
    }
}