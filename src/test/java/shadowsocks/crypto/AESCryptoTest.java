package shadowsocks.crypto;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static shadowsocks.crypto.AESCrypto.CIPHER_AES_256_CFB;

public class AESCryptoTest {

    @Test
    public void encryptAndDecrypt() throws Exception {
        AESCrypto crypto = new AESCrypto(CIPHER_AES_256_CFB, "abc123");
        byte[] bytes = "Hello World, my little baby, 2017".getBytes();
        byte[] encrypt = crypto.encrypt(bytes, bytes.length);
        byte[] decrypt = crypto.decrypt(encrypt, encrypt.length);
        Assert.assertArrayEquals(bytes, decrypt);
    }

    @Test
    public void encryptWithSlice() throws Exception {
        AESCrypto crypto = new AESCrypto(CIPHER_AES_256_CFB, "abc123");
        System.out.println(encryptStr("Hello World, my little baby, 2017", crypto));
        System.out.println(encryptStr("Hello World, my little baby", crypto));
        System.out.println(encryptStr(", 2017", crypto));
    }

    private String encryptStr(String str, AESCrypto aesCrypto) throws CryptoException {
        byte[] bytes = str.getBytes();
        byte[] encrypt = aesCrypto.encrypt(bytes, bytes.length);
        StringBuilder sb = new StringBuilder();
        for (byte b : encrypt) {
            sb.append((int)b);
        }
        return sb.toString();
    }

    private List<byte[]> slice(byte[] bytes) {
        int half = bytes.length / 2;
        byte[] first = new byte[half];
        byte[] second = new byte[bytes.length - half];
        System.arraycopy(bytes, 0, first, 0, half);
        System.arraycopy(bytes, half, second, 0, bytes.length - half);
        List<byte[]> result = new ArrayList<>();
        result.add(first);
        result.add(second);
        return result;
    }
}