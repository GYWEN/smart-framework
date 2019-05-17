package Thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * <Description> <br>;
 *
 * @author gyw<br>
 * @version 1.0<br>
 * @taskId
 * @CreateDate 2019/5/15
 * @see Thread <br>
 * @since V7.3<br>
 */
public class ExecutorDemo {


    //Executor的实现类ThreadPollExecutor是基本的实例化的类

    public static void main(String[] args) {

        //最简单的做法就是通过工厂方法创建一个线程池，然后调用execute方法进行处理，但是线程池是绝对的重点，需要源码级别的掌握
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new Runnable() {
            public void run() {
                System.out.println("This is a Thread");
            }
        });
        //线程池就是一个生产者消费者案例的具体实现，通过Executor框架创建出一个生产者，产生的结果可以通过Future进行获得，从而实现了基本的功能
        //同时，实现了创建过程的监控和性能等先关的处理


        //线程池的基本执行策略：根据参数来进行处理
//        public ThreadPoolExecutor(int corePoolSize,
//        int maximumPoolSize,
//        long keepAliveTime,
//        TimeUnit unit,
//        BlockingQueue<Runnable> workQueue) {
//            this(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
//                    Executors.defaultThreadFactory(), defaultHandler);
//        }

        //首先关注两个重要的参数corePoolSize和maximumPollSize.线程池在创建的初始就会去创建corePollSize数量的线程池，那么当线程池执行executor
        //的时候，会首先去查看是否还有线程是空余的，如果存在，则会去调用空余线程进行调用，否则会将它加入到阻塞队列workQueue中，等待处理。若是workQueue已经满了
        //那么就会查看当前执行的线程数量是否达到了maximumPoolSize的大小，如果没达到，就会创建一个线程来执行当前的任务，若是也已经到达了maximumPoolSize的话
        //那么就会执行具体的拒绝策略。


        //线程池执行任务，有两个基本的方法submit()和execute()方法，其中execute()方法是无返回值的，也就是无法知晓是否真正的执行。submit()方法
        //会返回一个future对象，我们可以通过这个future对象来进行判断线程的执行状况。




    }



}
