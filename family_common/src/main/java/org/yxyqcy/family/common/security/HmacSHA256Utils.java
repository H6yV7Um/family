package org.yxyqcy.family.common.security;

import org.apache.commons.codec.binary.Hex;
import org.yxyqcy.family.common.constant.SysConstants;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.List;
import java.util.Map;

/**
 * Created by lcy on 17/3/4.
 */
public class HmacSHA256Utils {

    //使用指定的密码对内容生成消息摘要（散列值）
    public static String digest(String key, String content) {
        try {
            Mac mac = Mac.getInstance("HMACSHA256");
            byte[] secretByte = key.getBytes(SysConstants.SYS_HASH_ENCODING);
            byte[] dataBytes = content.getBytes(SysConstants.SYS_HASH_ENCODING);

            SecretKey secret = new SecretKeySpec(secretByte, "HMACSHA256");
            mac.init(secret);

            byte[] doFinal = mac.doFinal(dataBytes);
            byte[] hexB = new Hex().encode(doFinal);
            return new String(hexB, SysConstants.SYS_HASH_ENCODING);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    //使用指定的密码对整个Map的内容生成消息摘要（散列值）
    public static String digest(String key, Map<String, ?> map) {
        StringBuilder s = new StringBuilder();
        for(Object values : map.values()) {
            if(values instanceof String[]) {
                for(String value : (String[])values) {
                    s.append(value);
                }
            } else if(values instanceof List) {
                for(String value : (List<String>)values) {
                    s.append(value);
                }
            } else {
                s.append(values);
            }
        }
        return digest(key, s.toString());
    }

}
