package com.lyp.neulife.bean;

import java.util.List;

/**
 * Created by lyp on 2016/3/21.
 */
public class BeanBanner {

    private int[] localImgs;
    private List<String> networkImgs;

    public BeanBanner(int[] localImgs) {
        this.localImgs = localImgs;
    }

    public BeanBanner(List<String> networkImgs) {
        this.networkImgs = networkImgs;
    }

    public int[] getLocalImgs() {
        return localImgs;
    }

    public void setLocalImgs(int[] localImgs) {
        this.localImgs = localImgs;
    }

    public List<String> getNetworkImgs() {
        return networkImgs;
    }

    public void setNetworkImgs(List<String> networkImgs) {
        this.networkImgs = networkImgs;
    }
}