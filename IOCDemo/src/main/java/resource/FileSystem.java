package resource;

import org.springframework.context.support.FileSystemXmlApplicationContext;

public class FileSystem {


    public static void main(String[] args) {
        FileSystemXmlApplicationContext fileSystemXmlApplicationContext = new FileSystemXmlApplicationContext("classpath:spring-context.xml");
        fileSystemXmlApplicationContext.getBean("car22");
    }

}
