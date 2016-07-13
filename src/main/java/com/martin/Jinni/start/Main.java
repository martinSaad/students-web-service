package com.martin.Jinni.start;

import java.io.File;

import javax.servlet.ServletException;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;


public class Main {
	public static void main(String[] args) {
        String webappDirLocation = "src/main/webapp/";
        Tomcat tomcat = new Tomcat();

        int webPort = 8080;
        tomcat.setPort(webPort);
        
        
        try {
            StandardContext ctx = (StandardContext) tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());

            File additionWebInfClasses = new File("target/classes");
            WebResourceRoot resources = new StandardRoot(ctx);
            resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes",
                    additionWebInfClasses.getAbsolutePath(), "/"));
            ctx.setResources(resources);

            tomcat.start();
            tomcat.getServer().await();
            
		} catch (ServletException e) {
			e.printStackTrace();
		}catch(LifecycleException e){
			e.printStackTrace();
		}
	}
		
}
   
