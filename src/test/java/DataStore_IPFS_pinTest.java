import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.io.IOException;

public class DataStore_IPFS_pinTest {

    /**
     * 是否将传入的文件在各节点pin住
     *  对于多个节点需要 pin 住的，采取循环的方式即可
     */
    @Test
    public void test() throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ip", "192.168.40.187");
        jsonObject.put("hashID", "Qma6ngsvtSwd8jTbTfJpC8N1EWm4HTjaEuRAzMVAyWZnfe");
        DataStore_IPFS_pin pin = new DataStore_IPFS_pin(jsonObject.getString("ip"));
       pin.rmPin(jsonObject.getString("hashID"));
//        pin.addPin(jsonObject.getString("hashID"));
    }
}