# shadowsocks-server
shadowsocks server base on netty

how to use
1. git clone https://github.com/Jacky-Cai/shadowsocks-server.git
1. mvn install 
1. cd target 
1. java -cp shasowsocks-server-1.0-SNAPSHOT.jar  shadowsocks.ServerMain -h ${server_ip} -p ${server_port} -k {passwd}
1. open shadowsocks client and connect to the specified server ip and port 
