package algo.performance;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.ReferenceCountUtil;

public class TestNetty {
    static class TimeServerHandler extends ChannelInboundHandlerAdapter {

        @Override
        public void channelActive(final ChannelHandlerContext ctx) { // (1)
            final ByteBuf time = ctx.alloc().buffer(4); // (2)
            time.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));

            final ChannelFuture f = ctx.writeAndFlush(time); // (3)
            f.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) {
                    assert f == future;
                    ctx.close();
                }
            }); // (4)
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
            cause.printStackTrace();
            ctx.close();
        }
    }

    static class DiscardServerHandler extends ChannelInboundHandlerAdapter { // (1)

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)
            // Discard the received data silently.
            ByteBuf in = (ByteBuf) msg;
            try {
                while (in.isReadable()) { // (1)
                    System.out.print((char) in.readByte());
                    System.out.flush();
                }
            } finally {
                ReferenceCountUtil.release(msg); // (2)
            }
            final ByteBuf time = ctx.alloc().buffer(4); // (2)
            time.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));
            ctx.write(time); // (1)
            ctx.flush(); // (2)
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
            // Close the connection when an exception is raised.
            cause.printStackTrace();
            ctx.close();
        }
    }

    static class DiscardServer {
        private int port;
        public DiscardServer(int port) {
            this.port = port;
        }
        public void run() throws Exception {
            EventLoopGroup bossGroup = new NioEventLoopGroup(); // (1)
            EventLoopGroup workerGroup = new NioEventLoopGroup();
            try {
                ServerBootstrap b = new ServerBootstrap(); // (2)
                b.group(bossGroup, workerGroup)
                        .channel(NioServerSocketChannel.class) // (3)
                        .childHandler(new ChannelInitializer<SocketChannel>() { // (4)
                            @Override
                            public void initChannel(SocketChannel ch) throws Exception {
                                ch.pipeline().addLast(new DiscardServerHandler());
                            }
                        })
                        .option(ChannelOption.SO_BACKLOG, 128)          // (5)
                        .childOption(ChannelOption.SO_KEEPALIVE, true); // (6)

                // Bind and start to accept incoming connections.
                ChannelFuture f = b.bind(port).sync(); // (7)

                // Wait until the server socket is closed.
                // In this example, this does not happen, but you can do that to gracefully
                // shut down your server.
                f.channel().closeFuture().sync();
            } finally {
                workerGroup.shutdownGracefully();
                bossGroup.shutdownGracefully();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        int port;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        } else {
            port = 8080;
        }
        new DiscardServer(port).run();
    }
}
