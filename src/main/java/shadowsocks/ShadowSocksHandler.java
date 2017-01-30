package shadowsocks;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shadowsocks.crypto.SSCrypto;

public class ShadowSocksHandler extends ChannelInboundHandlerAdapter {

    private static Logger logger = LoggerFactory.getLogger(ShadowSocksHandler.class);
    private final static int ADDR_TYPE_IPV4 = 1;
    private final static int ADDR_TYPE_HOST = 3;

    private final SSCrypto ssCrypto;

    private int addressByte = 0;

    public ShadowSocksHandler(SSCrypto ssCrypto) {
        this.ssCrypto = ssCrypto;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info("connected with {}", ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        logger.info("disconnected with {}", ctx.channel());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buff = (ByteBuf) msg;
        if(buff.readableBytes() < 2) {
            return;
        }
        addressByte = buff.getByte(0);
        int minimumSize = minimumSize();

        super.channelRead(ctx, msg);
    }

    private int minimumSize() {
        if(addressByte == ADDR_TYPE_IPV4) {
            return 7;
        } else if(addressByte == ADDR_TYPE_HOST) {
            return 0;
        }
        return 0;
    }
}
