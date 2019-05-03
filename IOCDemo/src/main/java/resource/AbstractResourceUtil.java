package resource;

import org.springframework.core.io.FileSystemResource;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class AbstractResourceUtil {

    /**
     * 用来写入路径
     * @param result
     * @param path
     * @throws IOException
     */
    public void write(String result,String path) throws IOException {
        FileSystemResource fileSystemResource1 = new FileSystemResource(path);
        OutputStream outputStream =fileSystemResource1.getOutputStream();
        outputStream.write(result.getBytes());
        outputStream.close();
    }


    public String read(InputStream inputStream) throws IOException {
        byte[] bytes = new byte[16];
        int length=0;
        String result="";
        while(-1!=(length=inputStream.read(bytes))){
            result+=new String(bytes,0,length);
        }
        return result;
    }




}
