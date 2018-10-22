package test.com.zyouke.concurrent;

import org.junit.Test;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * @Author: zhoujun
 * java 提供的Unsafe测试
 */
public class UnsafeTest {

    @Test
    public void test1(){
        System.out.println("-------------------" + getUnsafe().pageSize());
    }

    @Test
    public void  compareAndSwapIntTest(){
        boolean b = getUnsafe().compareAndSwapInt(new ArrayList<String>(), 1, 2, 3);
        System.out.println("-------------------->" + b);
    }

    /**
     * 如果并非 java.util.concurrent.atomic内的原子类、AbstractQueuedSynchronizer等类型，则会抛出SecurityException不安全异常 通过反射获取Unsafe对象
      * @return
     */
    private Unsafe getUnsafe(){
        try {
            Field singletonInstanceField = Unsafe.class.getDeclaredField("theUnsafe");
            singletonInstanceField.setAccessible(true);
            return (Unsafe) singletonInstanceField.get(null);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }


}
