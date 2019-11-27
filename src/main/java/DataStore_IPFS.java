import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import io.ipfs.multihash.Multihash;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class DataStore_IPFS {

    private Logger logger = Logger.getLogger(DataStore_IPFS.class.toString());
    private IPFS ipfs;

    public DataStore_IPFS(String ip){
        ipfs = new IPFS("/ip4/" + ip + "/tcp/5001");
        try {
            ipfs.refs.local();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 上传绝对路径的文件
     */
    public JSONObject upLoadFile(String localFilePath) throws IOException {
        NamedStreamable.FileWrapper fileAdd = new NamedStreamable.FileWrapper(new File(localFilePath));
        MerkleNode merkleNode = null;
        try {
            merkleNode = ipfs.add(fileAdd).get(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // TODO 显示出分块的结构 可以把该块的信息全部显示出来
//        System.out.println(ipfs.ls(merkleNode.hash).get(0).links.size());
//        String s = ipfs.ls(merkleNode.hash).get(0).toJSONString();
//        System.out.println(s);
        JSONObject jsonObject = JSONObject.parseObject(merkleNode.toJSONString());
        return jsonObject;
    }

    /**
     * 下载文件 并保存到本地
     */
    public void downloadFile(String hashID, String localPath){
        Multihash multihash = Multihash.fromBase58(hashID);
        try {
            byte[] fileContents = ipfs.cat(multihash);
            String download_path = localPath;
            OutputStream out = new FileOutputStream(download_path);
            out.write(fileContents);
            out.close();
        }catch (IOException e){
        }
    }

    /**
     * 在分节点查看属于的主节点
     */
    public void testFunction(){
//        try {
//            List list = ipfs.bootstrap.list();
//            System.out.println(list);
//            Map<Multihash, Object> s = ipfs.pin.ls();
//            System.out.println(s);
//        }catch (IOException e){
//        }
    }
}
