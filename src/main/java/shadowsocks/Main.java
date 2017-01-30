package shadowsocks;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        new ShadowSocksServer().start("localhost", 8000, "aes-256-cfb", "123456");
    }
}
