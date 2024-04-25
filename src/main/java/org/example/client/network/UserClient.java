package org.example.client.network;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.example.client.utils.Constants;

public class UserClient {

    static Channel channel;

    public void start(String eMail, String userName) throws Exception {

        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bst = new Bootstrap();
            bst.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) {
                            ChannelPipeline pln = ch.pipeline();

                            pln.addLast(new StringDecoder());
                            pln.addLast(new StringEncoder());


                            pln.addLast(new UserClientHandler());
                        }
                    });

            ChannelFuture ft = bst.connect(Constants.HOST,
                    Constants.PORT).sync();


            channel = ft.sync().channel();
            channel.writeAndFlush("User name: " + userName + "\neMail: " + eMail);
            channel.flush();


            ft.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }
}
