package org.zhsaen.admin.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * AES加密工具类
 */
public class AESUtil {

    private static final String ALGORITHM = "AES";
    private static final String DEFAULT_KEY = "quantum_admin_secret_key";

    /**
     * 加密字符串
     * @param content 待加密内容
     * @return 加密后的Base64编码字符串
     */
    public static String encrypt(String content) {
        try {
            // 生成密钥
            SecretKeySpec keySpec = generateKey(DEFAULT_KEY);
            
            // 创建加密器
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            
            // 加密内容
            byte[] encryptedBytes = cipher.doFinal(content.getBytes("UTF-8"));
            
            // 使用Base64编码
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("AES加密失败", e);
        }
    }

    /**
     * 解密字符串
     * @param encryptedContent Base64编码的加密内容
     * @return 解密后的字符串
     */
    public static String decrypt(String encryptedContent) {
        try {
            // 生成密钥
            SecretKeySpec keySpec = generateKey(DEFAULT_KEY);
            
            // 创建解密器
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
            
            // 解码Base64
            byte[] encryptedBytes = Base64.getDecoder().decode(encryptedContent);
            
            // 解密内容
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            
            return new String(decryptedBytes, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("AES解密失败", e);
        }
    }

    /**
     * 根据密钥字符串生成SecretKeySpec
     * @param keyString 密钥字符串
     * @return SecretKeySpec
     * @throws Exception 异常
     */
    private static SecretKeySpec generateKey(String keyString) throws Exception {
        // 生成固定长度的密钥
        byte[] keyBytes = new byte[16]; // AES-128
        byte[] temp = keyString.getBytes("UTF-8");
        
        // 复制密钥内容到固定长度数组
        System.arraycopy(temp, 0, keyBytes, 0, Math.min(temp.length, keyBytes.length));
        
        return new SecretKeySpec(keyBytes, ALGORITHM);
    }
}