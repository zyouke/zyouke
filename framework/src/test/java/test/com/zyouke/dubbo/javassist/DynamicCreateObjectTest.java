package test.com.zyouke.dubbo.javassist;

import com.zyouke.dubbo.javassist.DynamicCreateObject;
import org.junit.Test;

/**
 * @Author: zhoujun
 */
public class DynamicCreateObjectTest {

    @Test
    public void createSimpleClassTest(){
        DynamicCreateObject createObject = new DynamicCreateObject();
        createObject.createSimpleClass("Student");
    }


}
