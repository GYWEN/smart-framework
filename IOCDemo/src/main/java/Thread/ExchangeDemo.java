package Thread;

/**
 * <Description> <br>;
 *
 * @author gyw<br>
 * @version 1.0<br>
 * @taskId
 * @CreateDate 2019/5/14
 * @see Thread < br>
 * @since V7.3<br>
 */
public class ExchangeDemo {

    //Exchanger类的作用就是在两个线程之间传递数据，这是什么应用

    // 最新的知识的学习：Exchange的基本原理在于伙伴线程，是不是两个线程在没执行之前，就已经确定了伙伴线程的关系，然后用于数据的交换，数据就是exchange(var)中的
    // 变量值，如果当前伙伴线程已经到达，那么会直接进行交换，但是如果匹配的伙伴线程还没有到达，那么就会等待

    //而所谓的伙伴线程也很简单，就是共同绑定的数据，联系一下Demo


//    三、实战场景
//    1.问题描述
//    最近接到外部项目组向我组提出的接口需求，需要查询我们业务办理量的统计情况。我们系统目前的情况是，有一个日增长十多万、总数据量为千万级别的业务办理明细表（xxx_info），
//    每人次的业务办理结果会实时写入其中。以往对外提供的业务统计接口是在每次被调用时候在明细表中执行SQL查询（select、count、where、group by等），响应时间很长，
//    对原生产业务的使用也有很大的影响。于是我决定趁着这次新增接口的上线机会对系统进行优化
//    2.优化思路
//    首先是在明细表之外再建立一个数据统计（xxx_statistics）表，考虑到目前数据库的压力以及公司内部质管流控等因素，暂没有分库存放，仍旧与原明细表放在同一个库。
//    再设置一个定时任务于每日凌晨对明细表进行查询、过滤、统计、排序等操作，把统计结果插入到统计表中。然后对外暴露统计接口查询统计报表。
//    现在的设计与原来的实现相比，虽然牺牲了统计表所占用的少量额外的存储空间（每日新增的十来万条业务办理明细记录经过处理最终会变成几百条统计表的记录），
//    但是却能把select、count这样耗时的数据统计操作放到凌晨时段执行以避开白天的业务办理高峰，分表处理能够大幅降低对生产业务明细表的性能影响，
//    而对外提供的统计接口的查询速度也将得到几个数量级的提升。当然，还有一个缺点是，不能实时提供当天的统计数据，不过这也是双方可以接受的
//    3.设计实现
//    设计一个定时任务，每日凌晨执行。在定时任务中启动两个线程，一个线程负责对业务明细表（xxx_info）进行查询统计，把统计的结果放置在内存缓冲区，
//    另一个线程负责读取缓冲区中的统计结果并插入到业务统计表（xxx_statistics）中。
//    亲，这样的场景是不是听起来很有感觉？没错!两个线程在内存中批量交换数据，这个事情我们可以使用Exchanger去做！我们马上来看看代码如何实现。


//      https://www.cnblogs.com/davidwang456/p/4179488.html




}
