package com.and.framework.common;

import java.io.IOException;

import io.reactivex.annotations.Nullable;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

public class ProgressResponseBody  extends ResponseBody{

    private ResponseBody responseBody;
    private ProgressListener progressListener;
    private BufferedSource bufferedSource;
    public ProgressResponseBody(ResponseBody responseBody,ProgressListener progressListener){
        this.responseBody = responseBody;
        this.progressListener = progressListener;

    }
    @Nullable
    @Override
    public MediaType contentType() {
        return responseBody.contentType();
    }

    @Override
    public long contentLength() {
        return responseBody.contentLength();
    }

    @Override
    public BufferedSource source() {
        if (bufferedSource == null){
            bufferedSource = Okio.buffer(source(responseBody.source()));
        }
        return null;
    }
    private Source source(Source source){
        return new ForwardingSource(source) {
            long totalByteRead = 0L;
            @Override
            public long read(Buffer sink, long byteCount) throws IOException {

                long byteRead = super.read(sink,byteCount);
                totalByteRead +=byteRead != -1? byteRead:0;
                if (progressListener != null){
                    progressListener.onProgress(totalByteRead,responseBody.contentLength(),byteRead == -1);
                }
                return byteRead;
            }
        };
    }
}
