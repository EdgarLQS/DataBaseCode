import io.ipfs.api.IPFS;
import io.ipfs.multihash.Multihash;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

/**
 * 以单个节点的 pin 的方式延伸到对整个集群进行 pin 管理
 */
public class DataStore_IPFS_pin {

    private Logger logger = Logger.getLogger(DataStore_IPFS_pin.class.toString());
    private IPFS ipfs;

    public DataStore_IPFS_pin(String ip){
        ipfs = new IPFS("/ip4/" + ip + "/tcp/5001");
        try {
            ipfs.refs.local();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addPin(String hashID) throws IOException {
        Multihash multihash = Multihash.fromBase58(hashID);
        ipfs.pin.add(multihash);
        logger.info(hashID + " is success pin ");
    }

    public void rmPin(String hashID) throws IOException {
        Multihash multihash = Multihash.fromBase58(hashID);
        ipfs.pin.rm(multihash);
        logger.info(hashID + " is success rm pin ");
    }

    public void testFunction(String hashID) throws IOException {
        Map<Multihash, Object> s = ipfs.pin.ls();
    }
}
