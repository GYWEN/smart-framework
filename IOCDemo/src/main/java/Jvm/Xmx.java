package Jvm;

import java.util.Vector;

/**
 * <Description> <br>;
 *
 * @author gyw<br>
 * @version 1.0<br>
 * @taskId
 * @CreateDate 2019/5/14
 * @see Jvm<br>
 * @since V7.3<br>
 */
public class Xmx {

    public static void main(String[] args) {

//        System.out.println(Runtime.getRuntime().maxMemory());

        Vector vector = new Vector();
        for(int i=0;i<10;i++){
            byte[] bytes = new byte[1024*1024];
            vector.add(bytes);
        }
        System.out.println(Runtime.getRuntime().maxMemory());

//        [GC (Allocation Failure)  509K->488K(3584K), 0.0006699 secs]
//        [GC (Allocation Failure)  1000K->600K(3584K), 0.0005434 secs]
//        [GC (Allocation Failure)  8229K->7824K(9216K), 0.0006648 secs]
//        [GC (Allocation Failure)  7824K->7832K(9728K), 0.0005094 secs]
//        [Full GC (Allocation Failure)  7832K->7765K(9728K), 0.0047816 secs]
//        [GC (Allocation Failure)  7765K->7765K(9728K), 0.0002658 secs]
//        [Full GC (Allocation Failure)  7765K->7748K(9728K), 0.0040858 secs]


        //首先学习一下GC日志的基本阅读  508k->488k(3584k) 508k是指的GC前的内存占用，488k指的是GC后的内存占用 （3584）指的是总内存的大小，需要搞清楚在什么情况下进行GC

        //[GC (Allocation Failure) [PSYoungGen: 3671K->488K(4608K)] 3671K->2784K(15872K), 0.0069671 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
        //首先确定的是对于新生代，GC的处理是在不足的情况下才会进行处理  这里GC前新生代为3671K,Eden区域不足以容乃1m的容量，所以进行GC.

        //到运行到第5次的时候，eden区已经装满，同时from区也已经接近饱和
//        PSYoungGen      total 4608K, used 3722K [0x00000000ffb00000, 0x0000000100000000, 0x0000000100000000)
////        eden space 4096K, 78% used [0x00000000ffb00000,0x00000000ffe28960,0x00000000fff00000)
////        from space 512K, 95% used [0x00000000fff00000,0x00000000fff7a020,0x00000000fff80000)
////        to   space 512K, 0% used [0x00000000fff80000,0x00000000fff80000,0x0000000100000000)
////        ParOldGen       total 11264K, used 2224K [0x00000000ff000000, 0x00000000ffb00000, 0x00000000ffb00000)
////        object space 11264K, 19% used [0x00000000ff000000,0x00000000ff22c020,0x00000000ffb00000)
////        Metaspace       used 3134K, capacity 4496K, committed 4864K, reserved 1056768K
////        class space    used 343K, capacity 388K, committed 512K, reserved 1048576K

        //运行到第6次，空间分配担保，此时s0区域已经无法承载任何数据，所以，直接进入老年代
//        Heap
//        PSYoungGen      total 4608K, used 1651K [0x00000000ffb00000, 0x0000000100000000, 0x0000000100000000)
//        eden space 4096K, 28% used [0x00000000ffb00000,0x00000000ffc22ca0,0x00000000fff00000)
//        from space 512K, 95% used [0x00000000fff80000,0x00000000ffffa020,0x0000000100000000)
//        to   space 512K, 0% used [0x00000000fff00000,0x00000000fff00000,0x00000000fff80000)
//        ParOldGen       total 11264K, used 5344K [0x00000000ff000000, 0x00000000ffb00000, 0x00000000ffb00000)
//        object space 11264K, 47% used [0x00000000ff000000,0x00000000ff538050,0x00000000ffb00000)
//        Metaspace       used 3197K, capacity 4496K, committed 4864K, reserved 1056768K
//        class space    used 346K, capacity 388K, committed 512K, reserved 1048576K

        //第7次，会放置到新生代中 Eden区域到达53%
//        Heap
//        PSYoungGen      total 4608K, used 2682K [0x00000000ffb00000, 0x0000000100000000, 0x0000000100000000)
//        eden space 4096K, 53% used [0x00000000ffb00000,0x00000000ffd22b68,0x00000000fff00000)
//        from space 512K, 96% used [0x00000000fff80000,0x00000000ffffc020,0x0000000100000000)
//        to   space 512K, 0% used [0x00000000fff00000,0x00000000fff00000,0x00000000fff80000)
//        ParOldGen       total 11264K, used 5384K [0x00000000ff000000, 0x00000000ffb00000, 0x00000000ffb00000)
//        object space 11264K, 47% used [0x00000000ff000000,0x00000000ff542060,0x00000000ffb00000)
//        Metaspace       used 3100K, capacity 4496K, committed 4864K, reserved 1056768K
//        class space    used 337K, capacity 388K, committed 512K, reserved 1048576K

        //第8次，会放置到新生代中 Eden区域到达78%
//        Heap
//        PSYoungGen      total 4608K, used 3714K [0x00000000ffb00000, 0x0000000100000000, 0x0000000100000000)
//        eden space 4096K, 78% used [0x00000000ffb00000,0x00000000ffe22b50,0x00000000fff00000)
//        from space 512K, 98% used [0x00000000fff80000,0x00000000ffffe010,0x0000000100000000)
//        to   space 512K, 0% used [0x00000000fff00000,0x00000000fff00000,0x00000000fff80000)
//        ParOldGen       total 11264K, used 5324K [0x00000000ff000000, 0x00000000ffb00000, 0x00000000ffb00000)
//        object space 11264K, 47% used [0x00000000ff000000,0x00000000ff533060,0x00000000ffb00000)
//        Metaspace       used 3111K, capacity 4496K, committed 4864K, reserved 1056768K
//        class space    used 338K, capacity 388K, committed 512K, reserved 1048576K

        //第9次，新生代饱和，回去计算每次晋升到老年代的平均大小是否超过老年代的剩余大小，如果超过，则直接触发Full GC
//        Heap
//        PSYoungGen      total 4608K, used 1238K [0x00000000ffb00000, 0x0000000100000000, 0x0000000100000000)
//        eden space 4096K, 30% used [0x00000000ffb00000,0x00000000ffc35a48,0x00000000fff00000)
//        from space 512K, 0% used [0x00000000fff00000,0x00000000fff00000,0x00000000fff80000)
//        to   space 512K, 0% used [0x00000000fff80000,0x00000000fff80000,0x0000000100000000)
//        ParOldGen       total 11264K, used 8792K [0x00000000ff000000, 0x00000000ffb00000, 0x00000000ffb00000)
//        object space 11264K, 78% used [0x00000000ff000000,0x00000000ff896150,0x00000000ffb00000)
//        Metaspace       used 3131K, capacity 4496K, committed 4864K, reserved 1056768K
//        class space    used 343K, capacity 388K, committed 512K, reserved 1048576K


        //新生代大小的设置 Xmn

        //设置持久代 -XX:PermSize 持久代的大小关系着环境可以建立多少个类，多少个常量的大小。

        //设置栈大小 -Xss 栈的大小与堆得大小具有一定的关系，可建立的栈的数量是通过 系统可用内存-堆内存 可以通过减少堆内存的大小来实现线程的提高。



        //相关的策略 Full GC的效率要远低于Minor GC的效率，所以，可以通过适当的新生代的大小，来实现避免老年代的Full GC.
    }
}
