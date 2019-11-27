import com.alibaba.fastjson.JSONArray;
import org.ipfsbox.battery.api.IPFSCluster;

public class DataStore_IPFS_cluster {


    public static void main(String[] args) {
        IPFSCluster ipfsCluster = new IPFSCluster("192.168.40.182", 5001);
        JSONArray s =  ipfsCluster.pins.ls();
        System.out.println(s.toString());
    }
}
