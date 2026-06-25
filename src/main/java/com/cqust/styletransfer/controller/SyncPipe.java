package com.cqust.styletransfer.controller;

import java.io.InputStream;
import java.io.OutputStream;

public class SyncPipe implements Runnable {
    private final OutputStream out;
    private final InputStream in;
    public SyncPipe(InputStream inputStream, OutputStream outputStream) {
         in=inputStream;
         out=outputStream;

    }
    public void run() {
        try {
            final byte[] buffer = new byte[1024];
            for (int length = 0; (length = in.read(buffer)) != -1;) {
                out.write(buffer, 0, length);
            }
        } catch (Exception e) {
            throw new RuntimeException("处理命令出现错误：" + e.getMessage());
        }
    }
}
