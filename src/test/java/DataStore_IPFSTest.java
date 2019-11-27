import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.io.IOException;

public class DataStore_IPFSTest {

    private String ip = "192.168.40.187";
    DataStore_IPFS dataStoreIpfs = new DataStore_IPFS(ip);

    @Test
    public void testUpLoadFile() throws IOException {
        long startTime =  System.currentTimeMillis();
        JSONObject jsonObject = null;
        String loaclFilePath = "E:\\DemoRecording\\testFileStorage\\JerseyTest\\test1.jpg";
        jsonObject = dataStoreIpfs.upLoadFile(loaclFilePath);
        String hash = jsonObject.getString("Hash");
        String fileName = jsonObject.getString("Name");
        System.out.println("file hash is : " + hash);
        System.out.println("file Name is : " + fileName);
        System.out.println(jsonObject);
        long endTime =  System.currentTimeMillis();
        long usedTime = (endTime-startTime);
        System.out.println("Total time：" + usedTime + "ms");
    }

    @Test
    public void testDownloadFile(){
        long startTime =  System.currentTimeMillis();
        String loaclSaveFilePath = "E:\\DemoRecording\\testFileStorage\\JerseyTest\\t";
        String hashID = "QmbsSj7aLSt28chEyJt6T8RzDE1JVNbcti4nrefr4irffn";
        dataStoreIpfs.downloadFile(hashID, loaclSaveFilePath);
        long endTime =  System.currentTimeMillis();
        long usedTime = (endTime-startTime);
        System.out.println("Total time：" + usedTime + "ms");
    }

    @Test
    public void testDownloadAndUploadfile() throws IOException {
        long startTime =  System.currentTimeMillis();
        JSONObject jsonObject = null;
        String loaclFilePath = "E:\\DemoRecording\\testFileStorage\\JerseyTest\\test1.mp4";
        jsonObject = dataStoreIpfs.upLoadFile(loaclFilePath);
        String hash = jsonObject.getString("Hash");
        String loaclSaveFilePath = "E:\\DemoRecording\\testFileStorage\\JerseyTest\\test";
        dataStoreIpfs.downloadFile(hash, loaclSaveFilePath);
        long endTime =  System.currentTimeMillis();
        long usedTime = (endTime-startTime);
        System.out.println("Total time：" + usedTime + "ms");
    }
}

