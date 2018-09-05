package algo.performance;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class TestIO {
    //io

    //nio
    /**
     * channel IO的管道
     *  FileChannel
     *  DatagramChannel
     *  SocketChannel
     *  ServerSocketChannel
     * buffer IO的暂存区
     *  ByteBuffer
     *  IntBuffer..
     * selector 查询IO状态
     */

    public static void basicFilChannel() throws IOException {
        RandomAccessFile aFile = new RandomAccessFile("LICENSE", "rw");
        FileChannel inChannel = aFile.getChannel();

        ByteBuffer buf = ByteBuffer.allocate(1024);

        int bytesRead = inChannel.read(buf);
        while (bytesRead != -1) {

            System.out.println("Read " + bytesRead);
            buf.flip();

            while(buf.hasRemaining()){
                System.out.print((char) buf.get());
            }

            buf.clear();
            bytesRead = inChannel.read(buf);
        }
        aFile.close();
    }

    public static void basicSocketCHannel() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        serverSocketChannel.socket().bind(new InetSocketAddress(9999));
        serverSocketChannel.configureBlocking(false);


        while(true){
            SocketChannel socketChannel =
                    serverSocketChannel.accept();

            //do something with socketChannel...
            if(socketChannel != null){
                //do something with socketChannel...
            }
        }
    }

    public static void main(String[] args){
        try {
            basicFilChannel();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
