package Design.link;

/**
 * <Description> <br>;
 *
 * @author gyw<br>
 * @version 1.0<br>
 * @taskId
 * @CreateDate 2019/5/5
 * @see Design.link<br>
 * @since V7.3<br>
 */
public class Customer implements Manager{

    Manager next;

    public void postRequest() {
        System.out.println("当前无法处理");
        next.postRequest();
    }
}
