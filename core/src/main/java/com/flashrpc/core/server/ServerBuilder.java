package com.flashrpc.core.server;

import com.flashrpc.core.Protocol;
import com.flashrpc.core.Serializer;
import com.flashrpc.core.util.ServiceLoadUtil;

/**
 * Created by yeyc on 2016/12/28.
 */
public final class ServerBuilder {

    private ServerChannel serverChannel;
    private Serializer serializer;//序列化
    private Protocol protocol;//传输协议
    private int port;//服务的d端口

    private Object serviceBean;//需要发布rpc的服务

    private ServerBuilder(int port) {
        this.port = port;
    }

    public static ServerBuilder forPort(int port) {
        return new ServerBuilder(port);
    }

    public ServerBuilder addService(Object serviceBean) {
        this.serviceBean = serviceBean;
        return this;
    }

    public Server build() {
        serverChannel = ServiceLoadUtil.getProvider(ServerChannel.class);
        serializer = ServiceLoadUtil.getProvider(Serializer.class);
        //protocol = ServiceLoadUtil.getProvider(Protocol.class);
        return new Server(serverChannel, serializer, protocol, port, serviceBean);
    }
}
