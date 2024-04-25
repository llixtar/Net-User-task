package org.example.server.network;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserServerHandler extends SimpleChannelInboundHandler<String> {

    private final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm:ss");

    static final List<Channel> channels = new ArrayList<>();

    @Override
    public void channelActive(final ChannelHandlerContext ctx) {
        System.out.println("Client joined - " + ctx);
        channels.add(ctx.channel());
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, String msg) {

        int indx = msg.indexOf("eMail");
        String userName = msg.substring(0, indx - 1);

        String time = DATE_FORMAT.format(new Date());
        System.out.println("CLIENT - ( " + time + " )\n" + msg);
        for (Channel ch : channels) {
            ch.writeAndFlush("( " + time + " )  " + "Welcome!!! " + userName);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        System.out.println("Closing connection for client - " + ctx);
        ctx.close();
    }
}
