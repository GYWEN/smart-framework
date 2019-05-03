package resource;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.web.context.support.ServletContextResource;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 实现的资源的学习和加载，要求读取一个地方的信息并发送到另一个地方
 */
public class ResourceUtil extends AbstractResourceUtil{

    //非web项目
    //1.文件路径资源

    public void file() throws IOException {
        FileSystemResource fileSystemResource = new FileSystemResource("/usr/local/apple/svn/mycode/README.txt");
        InputStream inputStream = fileSystemResource.getInputStream();
        String result=read(inputStream);
        inputStream.close();
        System.out.println(result);
        write(result,"/Users/gyw/Desktop/test.txt");
    }

    // 2.类路径

    public void classes() throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("test.txt");
        EncodedResource encodedResource =new EncodedResource(classPathResource,"utf-8");
        InputStream inputStream = classPathResource.getInputStream();
        String result=read(inputStream);
        inputStream.close();
        System.out.println(result);
        write(result,"/Users/gyw/Desktop/test.txt");
    }

    //web项目
    //web根路径
    public void parttern() throws IOException {
        ResourcePatternResolver resolver =new PathMatchingResourcePatternResolver();
        Resource resource = resolver.getResource("classpath:test.txt");
        String result = read(resource.getInputStream());
        System.out.println(result);
    }

    public static void main(String[] args) throws IOException {
        ResourceUtil resourceUtil =new ResourceUtil();
        resourceUtil.parttern();
    }
}
