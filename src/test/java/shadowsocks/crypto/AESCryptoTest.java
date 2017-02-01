package shadowsocks.crypto;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.System.arraycopy;
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
    public void encyptedBytesCouldBeSliced() throws Exception {
        AESCrypto aesCrypto = new AESCrypto(AESCrypto.CIPHER_AES_256_CFB, "abc123");
        byte[] fullBytes = "12121211331313131,who am i,12345555".getBytes();
        byte[] encrypt = aesCrypto.encrypt(fullBytes, fullBytes.length);
        int someLength = 18;
        byte[] halfEncryptOne = new byte[someLength];
        byte[] halfEncryptTwo = new byte[encrypt.length - someLength];
        arraycopy(encrypt, 0, halfEncryptOne, 0, halfEncryptOne.length);
        arraycopy(encrypt, someLength, halfEncryptTwo, 0, halfEncryptTwo.length);

        byte[] decryptOne = aesCrypto.decrypt(halfEncryptOne, halfEncryptOne.length);
        byte[] decryptTwo = aesCrypto.decrypt(halfEncryptTwo, halfEncryptTwo.length);
        byte[] joint = new byte[decryptOne.length + decryptTwo.length];
        arraycopy(decryptOne, 0, joint, 0, decryptOne.length);
        arraycopy(decryptTwo, 0, joint, decryptOne.length, decryptTwo.length);
        Assert.assertArrayEquals(fullBytes, joint);
    }


}