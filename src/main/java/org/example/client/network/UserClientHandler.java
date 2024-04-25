package org.example.client.network;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class UserClientHandler extends SimpleChannelInboundHandler<String> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) {
		System.out.println(msg);
		System.exit(0);//отримав повідомлення від сервера. Наразі, робити більше нічого:)
	}
}
