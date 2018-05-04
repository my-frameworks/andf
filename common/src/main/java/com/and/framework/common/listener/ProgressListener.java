package com.and.framework.common.listener;

public interface ProgressListener {

    void onProgress(long progress,long total,boolean done);
}
