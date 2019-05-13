package nio;

import java.io.IOException;
import java.nio.ByteBuffer;


/**
 * <Description>用于学习NIO的知识 <br>;
 *
 * @author gyw<br>
 * @version 1.0<br>
 * @taskId
 * @CreateDate 2019/5/13
 * @see nio < br>
 * @since V7.3<br>
 */
public class NioDemo {

    //可以先这么进行理解，NIO的不同之处在与，之前我们进行文件的读取的时候，首先进行的是获取输入流，然后使用String等进行处理，而现在不同的是

    //NIO的InputStream变换成了Channel，然后所有的String都被Buffer进行替换了，就先这么理解和记忆。

    //NIO中的输入输出的写入对象发生了变化，对于基本的输入流，我们的执行对象是程序，读到程序中来，是输入流，从程序中输入出去对应的是输出流

    //但是对于NIO而言，针对的对象变成了缓存区，输入流对应的是写入到缓存中，输出流对应的是从缓存中读取数据


    public static void main(String[] args) throws IOException {

//        Resource resource = new ClassPathResource("classpath:nio_read.txt");
//
//        resource.readableChannel();
//
//        Channel readChannel = (FileInputStream)(resource.getInputStream());
//
//        FileSystemResource resource1 = new FileSystemResource("classpath:nio_read.txt");
//
//        FileInputStream fileInputStream  = new FileInputStream("classpath:nio_read.txt");

        //关于Buffer的相关知识

        ByteBuffer byteBuffer = ByteBuffer.allocate(15);

        for(int i=0;i<10;i++) {
            byteBuffer.put((byte)i);
        }

        System.out.println("position:"+byteBuffer.position()+"  limit"+byteBuffer.limit());

        byteBuffer.flip();

        System.out.println("position:"+byteBuffer.position()+"  limit"+byteBuffer.limit());


        for(int i=0;i<5;i++) {
            byteBuffer.put((byte)i);
        }

        System.out.println("position:"+byteBuffer.position()+"  limit"+byteBuffer.limit());

        //换言之，整个的ByeBuffer采用的不是清空的处理，而是覆盖的处理,而Clear操作的作用就是将所有的参数初始化并清空

        //整个NIO的buffer读取可以读取全部的buffer数据，或者是一部分，而这是要看具体的API操作，但是首先理解，Buffer的flip函数的作用就是

        //将写入状态切换到读取状态，因为读取是要从头开始进行读取的,同样的道理，读取操作也会修改对应的position信息，下面看是否进行具体的操作

        byteBuffer.clear();

        System.out.println("position:"+byteBuffer.position()+"  limit"+byteBuffer.limit());


        //Buffer的创建 两种方式，第一种就是之前的创建的方式，通过Buffer的静态方法创建一个区域给Buffer

        ByteBuffer byteBuffer1 = ByteBuffer.allocate(1024);

        //第二种方式 就是从一个已经存在的数组中创建数据,但是即使是这种创建，无论之前的数组是否有值，对应的Buffer的初始position和limit都是0和初始值
        byte[] bytes = new byte[1024];

        ByteBuffer byteBuffer2 = ByteBuffer.wrap(bytes);

        bytes[5] = (byte)5;



        System.out.println("position:"+byteBuffer2.position()+"  limit"+byteBuffer2.limit());


        //NIO的三种清空方式 rewind(),clear(),flip()，但是他们对应的操作是不同的，对于rewind()对应的操作仅仅是将position变为0，通常用于提取Buffe的有效数据

        //但是注意的是，对于这三种方式，无论怎么处理，都仅仅是重置标志位，而不会将数据清空



        //Buffer的读取和写入
        //Buffer的读取和写入都分为三种情况，第一种就是顺延的单个读取，这种读取会改变position的位置，其中的put方法对应的是将数据读入并将position移到下一位
        byteBuffer2.put((byte)1);
        System.out.println("position:"+byteBuffer2.position()+"  limit"+byteBuffer2.limit());
        //但是对于get方法，原意是进行连续读取，所以回去读取当前position的数据，并移动position到下一位.
        byteBuffer2.get();
        System.out.println("position:"+byteBuffer2.position()+"  limit"+byteBuffer2.limit());

        //第二种读取，就是指定位置的读取，一定要注意一个基本的环境，无论是采用哪一种读取方式，我们所做的都是移动三个标志位而已,所以他不会在乎你对应的数组里面是否有数据
        //对于指定位置的读取，那么这就只是简单的数据替换，所以position,limiit都不会发生改变。

        //第三种读取，一次读取多个数据，即数组的读取，这种方式会进行position的变动，因为他也是相当于批量的单个读取。

        byteBuffer1.put(new byte[512]);

        System.out.println("position:"+byteBuffer1.position()+"  limit"+byteBuffer1.limit());


        //标志缓存区Mark,记得在是那种设置标志位的方法中，都将mark设置为了-1；

        ByteBuffer byteBuffer3 = ByteBuffer.allocate(1024);

        for(int i=0;i<10;i++){
            byteBuffer3.put((byte)i);
            if(i==4){
                byteBuffer3.mark();
            }
        }
        System.out.println("position:"+byteBuffer3.position()+"  limit"+byteBuffer3.limit());

        byteBuffer3.reset();

        System.out.println("position:"+byteBuffer3.position()+"  limit"+byteBuffer3.limit());

        byteBuffer3.get();

        System.out.println("position:"+byteBuffer3.position()+"  limit"+byteBuffer3.limit());

        //这个标记reset的作用就是，设置一个mark位，在reset的同时，会将position同时设置都mark的位置，相当于个position设置了一个回退的功能，其余的照旧

        

    }



}
