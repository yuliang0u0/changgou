package com.changgou.util;

import com.changgou.entity.FastDFSFile;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 实现fastdfs文件管理
 * @author yuliang0u0
 * @create 2021-04-30 19:02
 */
public class FastDFSUtils {

    // 加载tracker连接信息
    static {
        try {
//            String path = "D:\\code\\IdeaProjects\\changgou\\changgou-parent\\changgou-service\\changgou-service-file\\src\\main\\resources\\fdfs_client.conf";
            String path = new ClassPathResource("fdfs_client.conf").getPath();
            ClientGlobal.init(path);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件上传
     * @param file 封装了要上传的文件信息的实体
     * @return String[] 1:文件上传后所存储的组名 2:文件存储路径
     */
    public static String[] upload(FastDFSFile file){
        // 获取文件作者
        NameValuePair[] meta_list = new NameValuePair[1];
        meta_list[0] =new NameValuePair("author", file.getAuthor());

        /***
         * 文件上传后的返回值
         * uploadResults[0]:文件上传所存储的组名，例如:group1
         * uploadResults[1]:文件存储路径,例如：M00/00/00/wKjThF0DBzaAP23MAAXz2mMp9oM26.jpeg
         */
        String[] uploadResults = null;
        try {
            // 创建TrackerClient客户端对象
            TrackerClient trackerClient = new TrackerClient();
            // 通过TrackerClient对象获取TrackerServer信息
            TrackerServer trackerServer = trackerClient.getConnection();
            // 通过TrackerServer的连接信息可以获取Storage的连接信息，获取StorageClient对象（存储了Storage的连接信息）
            StorageClient storageClient = new StorageClient(trackerServer, null);
            // 执行文件上传
            /**
             * 参数：
             *  1.上传文件的字节数组
             *  2.文件的扩展名
             *  3.附加参数
             */
            uploadResults = storageClient.upload_file(file.getContent(), file.getExt(), meta_list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return uploadResults;
    }

    /**
     * 获取文件信息
     * @param groupName:组名 group1
     * @param remoteFileName：文件存储完整名 M00/00/00/wKjThF1E95SAZkDVAAnAAJuzIB4821.jpg
     */
    public static FileInfo getFile(String groupName, String remoteFileName){
        try {
            //创建TrackerClient对象
            TrackerClient trackerClient = new TrackerClient();
            //通过TrackerClient获得TrackerServer信息
            TrackerServer trackerServer =trackerClient.getConnection();
            //通过TrackerServer获取StorageClient对象
            StorageClient storageClient = new StorageClient(trackerServer,null);
            //获取文件信息
            return storageClient.get_file_info(groupName,remoteFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 文件下载
     * @param groupName:组名
     * @param remoteFileName：文件存储完整名
     * @return
     */
    public static InputStream downFile(String groupName, String remoteFileName){
        try {
            //创建TrackerClient对象
            TrackerClient trackerClient = new TrackerClient();
            //通过TrackerClient对象创建TrackerServer
            TrackerServer trackerServer = trackerClient.getConnection();
            //通过TrackerServer创建StorageClient
            StorageClient storageClient = new StorageClient(trackerServer,null);
            //通过StorageClient下载文件
            byte[] fileByte = storageClient.download_file(groupName, remoteFileName);
            //将字节数组转换成字节输入流
            return new ByteArrayInputStream(fileByte);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 文件删除实现
     * @param groupName:组名
     * @param remoteFileName：文件存储完整名
     */
    public static void deleteFile(String groupName,String remoteFileName){
        try {
            //创建TrackerClient对象
            TrackerClient trackerClient = new TrackerClient();
            //通过TrackerClient获取TrackerServer对象
            TrackerServer trackerServer = trackerClient.getConnection();
            //通过TrackerServer创建StorageClient
            StorageClient storageClient = new StorageClient(trackerServer,null);
            //通过StorageClient删除文件
            storageClient.delete_file(groupName,remoteFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取组信息
     * @param groupName :组名
     */
    public static StorageServer getStorage(String groupName){
        try {
            //创建TrackerClient对象
            TrackerClient trackerClient = new TrackerClient();
            //通过TrackerClient获取TrackerServer对象
            TrackerServer trackerServer = trackerClient.getConnection();
            //通过trackerClient获取Storage组信息
            return trackerClient.getStoreStorage(trackerServer,groupName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据文件组名和文件存储路径获取Storage服务的IP、端口信息
     * @param groupName :组名
     * @param remoteFileName ：文件存储完整名
     */
    public static ServerInfo[] getServerInfo(String groupName, String remoteFileName){
        try {
            //创建TrackerClient对象
            TrackerClient trackerClient = new TrackerClient();
            //通过TrackerClient获取TrackerServer对象
            TrackerServer trackerServer = trackerClient.getConnection();
            //获取服务信息
            return trackerClient.getFetchStorages(trackerServer,groupName,remoteFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取Tracker服务地址
     */
    public static String getTrackerUrl(){
        try {
            //创建TrackerClient对象
            TrackerClient trackerClient = new TrackerClient();
            //通过TrackerClient获取TrackerServer对象
            TrackerServer trackerServer = trackerClient.getConnection();
            //获取Tracker地址
            return "http://"+trackerServer.getInetSocketAddress().getHostString()+":"+ClientGlobal.getG_tracker_http_port();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
//        FileInfo fileInfo = getFile("group1","M00/00/00/wKjThF1E95SAZkDVAAnAAJuzIB4821.jpg");
//        System.out.println(fileInfo);

        // 文件下载
//        InputStream is = downFile("group1", "M00/00/00/wKjThF1E95SAZkDVAAnAAJuzIB4821.jpg");
//        FileOutputStream os = new FileOutputStream("D:/1.jpg");
//
//        byte[] bytes = new byte[1024];
//        while (is.read(bytes) != -1) {
//            os.write(bytes);
//        }
//
//        os.flush();
//        os.close();
//        is.close();
//        deleteFile("group1", "M00/00/00/wKjThGCL7FqAFuM6AAEYE5h6E94310.jpg");

//        StorageServer server = getStorage("group1");
//        System.out.println(server.getStorePathIndex());
//        System.out.println(server.getInetSocketAddress().getHostString());

//        ServerInfo[] servers = getServerInfo("group1", "M00/00/00/wKjThF1FBz-ARyy5AAoAAFSKsG8238.jpg");
//        for (ServerInfo serverInfo : servers) {
//            System.out.println(serverInfo.getPort());
//            System.out.println(serverInfo.getIpAddr());
//        }

        System.out.println(getTrackerUrl());
    }

}
