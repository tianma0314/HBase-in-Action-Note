/**
 * @Title: MD5Utility.java
 * @Package com.mx.hadoop.activity.common.utils
 * @Description: 用户活跃度及数据分析
 * @author 沈阳美行科技有限公司
 * @date 2016年5月24日 上午10:11:11
 * @version V1.0.0
 */
package com.mxnavi.bigdata.hbase.common;

import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;

import java.nio.charset.Charset;

/**
 * @Description: MD5 工具
 * @author 沈阳美行科技有限公司
 * @date 2016年5月24日 上午10:11:11
 */
public class MD5Utils {
    /**
     * @Title: md5
     * @Description: 使用MD5，计算Hash值
     * @param plainText 需要加密的数据
     * @return byte[] Hash值
     * @throws
     */
    public static byte[] md5(String plainText) {
        Hasher hasher = Hashing.md5().newHasher();
        hasher.putString(plainText, Charset.forName("UTF-8"));
        return hasher.hash().asBytes();
    }
}
