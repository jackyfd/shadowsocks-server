package shadowsocks.crypto;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import org.junit.Test;

public class ByteBufTest {

    @Test
    public void name() throws Exception {
        ByteBuf byteBuf = ByteBufUtil.threadLocalDirectBuffer();
        ByteBuf byteBufTwo = ByteBufUtil.threadLocalDirectBuffer();
        byte[] bytes = "who are you, baby".getBytes();
        byteBuf.writeBytes(bytes, 0, bytes.length);
        System.out.println(byteBuf.readableBytes());

        byteBuf.readBytes(byteBufTwo);
        System.out.println(byteBuf.readableBytes());
        System.out.println(byteBufTwo.readableBytes());


    }
}
