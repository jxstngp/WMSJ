import com.samton.platform.framework.util.DateUtil;
import org.junit.Test;

import java.io.File;

public class TestMain {
    @Test
    public void testFunc(){
        System.out.println(File.separator);
        System.out.println(DateUtil.currentDatetimeNum());
    }
}
