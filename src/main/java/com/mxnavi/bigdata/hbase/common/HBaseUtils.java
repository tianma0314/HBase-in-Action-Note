package com.mxnavi.bigdata.hbase.common;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;


/**
 * @Description:
 * @Auther: liujun
 * @Date: 2018/8/17 10:47
 */
public class HBaseUtils {

    private static Connection hbaseConnection;

    static {
        Configuration conf = HBaseConfiguration.create();
        conf.addResource("hbase-site.xml");
        conf.addResource("hbase-policy.xml");
        try {
            hbaseConnection = ConnectionFactory.createConnection(conf);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static byte[] getMxUserRowKey(String deviceNo, Integer productId) {
        byte[] key = new byte[20];

        byte[] deviceNoBytes = MD5Utils.md5(deviceNo);
        byte[] productIdBytes = Bytes.toBytes(productId);

        System.arraycopy(deviceNoBytes, 0, key, 0, deviceNoBytes.length);
        System.arraycopy(productIdBytes, 0, key, deviceNoBytes.length, productIdBytes.length);

        return key;
    }

    public static byte[] getCellValue(byte[] rowKey, String tableName, byte[] columnFamily, byte[] qualifier) throws IOException {
        HTable table = (HTable) hbaseConnection.getTable(TableName.valueOf(tableName));
        Get get = new Get(rowKey);
        Result result = table.get(get);
        byte[] valueBytes = result.getValue(columnFamily, qualifier);

        return valueBytes;
    }

    public static boolean getRow(byte[] rowKey, String tableName) throws IOException {
        HTable table = (HTable) hbaseConnection.getTable(TableName.valueOf(tableName));
        Get get = new Get(rowKey);
        return table.exists(get);
    }

    public static Result getRows(byte[] rowKey, String tableName) throws IOException {
        HTable table = (HTable) hbaseConnection.getTable(TableName.valueOf(tableName));
        Get get = new Get(rowKey);
        Result result = table.get(get);
        return result;
    }
}
