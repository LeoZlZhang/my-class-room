package com.leo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

/**
 * Created by sh00514 on 2017/1/22.
 * entry
 */
public class Run {

    public static void main(String[] args) throws IOException {
        ApplicationContext context = new FileSystemXmlApplicationContext(
                 Files.find(Paths.get(System.getProperty("user.dir")), Integer.MAX_VALUE, (path, attri) -> path.getFileName().toString().endsWith("applicationContext.xml"))
                        .map(path -> path.toAbsolutePath().toString())
                        .collect(Collectors.toList()).toArray(new String[]{})[0]
        );
        context.getBean("demoClass", MyClassLoadBySpring.class).print();
    }
}
