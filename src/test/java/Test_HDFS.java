import org.apache.commons.io.IOUtils;
import org.apache.hadoop.fs.*;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Test_HDFS {

    FileSystem fs = new DataBase_Hdfs().init();

    /**
     * 上传文件（流形式和路径下形式）
     */
    @Test
    public void upload() throws Exception {

        // 本地路径和上传路径----------（需要将数据变为流的形式进行上传）
        String localpath = "E:\\DemoRecording\\testFileStorage\\JerseyTest\\test1.jpg";
        String destpath = "hdfs://192.168.40.198:9000/user/bcgis/test2.jpg";
        Path dst = new Path(destpath);
        FSDataOutputStream os = fs.create(dst);
        FileInputStream is = new FileInputStream(localpath);
        IOUtils.copy(is, os);
        /**
         * 分析原因
         */
    }

    // 从hdfs中复制文件到本地文件系统
    @Test
    public void testDownloadFileToLocal() throws IllegalArgumentException, IOException {
        String localpath = "E:\\DemoRecording\\testFileStorage\\JerseyTest\\HDFSdownloadtest.jpg";
        fs.copyToLocalFile(new Path("/user/bcgis/test.jpg"), new Path(localpath));
        fs.close();
    }

    @Test
    public void testMkdirAndDeleteAndRename() throws IllegalArgumentException, IOException {
        // 创建目录
        fs.mkdirs(new Path("/user/bcgis/testbcgis"));
        fs.mkdirs(new Path("/user/bcgis/testbcgis1"));
        // 删除文件夹 ，如果是非空文件夹，参数2必须给值true
        fs.delete(new Path("/user/bcgis/testbcgis"), true);
        fs.deleteOnExit(new Path("/user/bcgis/test1.jpg"));
        // 重命名文件或文件夹
        fs.rename(new Path("/user/bcgis/testbcgis1"), new Path("/user/bcgis/testbcgis_rename"));
    }

    // 查看目录信息，只显示文件
    @Test
    public void testListFiles() throws FileNotFoundException, IllegalArgumentException, IOException {

        // 思考：为什么返回迭代器，而不是List之类的容器
        RemoteIterator<LocatedFileStatus> listFiles = fs.listFiles(new Path("/"), true);

        while (listFiles.hasNext()) {
            LocatedFileStatus fileStatus = listFiles.next();
            System.out.println(fileStatus.getPath().getName());
            System.out.println(fileStatus.getBlockSize());
            System.out.println(fileStatus.getPermission());
            System.out.println(fileStatus.getLen());
            BlockLocation[] blockLocations = fileStatus.getBlockLocations();
            for (BlockLocation bl : blockLocations) {
                System.out.println("block-length:" + bl.getLength() + "--" + "block-offset:" + bl.getOffset());
                String[] hosts = bl.getHosts();
                for (String host : hosts) {
                    System.out.println(host);
                }
            }
            System.out.println("--------------为angelababy打印的分割线--------------");
        }
    }

    //查看文件及文件夹信息
    @Test
    public void testListAll() throws FileNotFoundException, IllegalArgumentException, IOException {

        FileStatus[] listStatus = fs.listStatus(new Path("/"));

        String flag = "d--             ";
        for (FileStatus fstatus : listStatus) {
            if (fstatus.isFile())  flag = "f--         ";
            System.out.println(flag + fstatus.getPath().getName());
        }
    }
}

