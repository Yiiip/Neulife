package com.lyp.neulife.fragment;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.lyp.neulife.R;
import com.lyp.neulife.util.ViewUtils;

/**
 * Created by lyp on 2016/3/22.
 */
public abstract class BaseFragment extends Fragment {

    static final int STATE_UNKOWN = 0;
    static final int STATE_LOADING = 1;
    static final int STATE_ERROR = 2;
    static final int STATE_EMPTY = 3;
    static final int STATE_SUCCESS = 4;

    int state = STATE_UNKOWN;

    private View loadingView;
    private View errorView;
    private View emptyView;
    private View successView;

    private FrameLayout frameLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (frameLayout == null) {
            frameLayout = new FrameLayout(getActivity());
            initState();
        } else {
            ViewUtils.removeSelfFromParent(frameLayout);
        }

        return frameLayout;
    }

    //在FrameLayout中添加四种界面。加载成功;加载失败;加载成功,但是没数据;加载中。
    private void initState() {
        loadingView = createLoadingView();
        if (loadingView != null) {
            frameLayout.addView(loadingView, new FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        }

        errorView = createErrorView();
        if (errorView != null) {
            frameLayout.addView(errorView, new FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        }

        emptyView = createEmptyView();
        if (emptyView != null) {
            frameLayout.addView(emptyView, new FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        }
        showPage();// 根据不同的状态显示不同的界面
    }


    //根据不同状态,显示不同的界面
    //五种状态,未知;加载中;错误;空;加载成功
    private void showPage() {
        if (loadingView != null) {
            loadingView.setVisibility(
                    state == STATE_UNKOWN || state == STATE_LOADING ? View.VISIBLE : View.INVISIBLE);
        }
        if (errorView != null) {
            errorView.setVisibility(state == STATE_ERROR ? View.VISIBLE : View.INVISIBLE);
        }
        if (emptyView != null) {
            emptyView.setVisibility(state == STATE_EMPTY ? View.VISIBLE : View.INVISIBLE);
        }


        if (state == STATE_SUCCESS) {
            successView = createSuccessView();
            if (successView != null) {
                frameLayout.addView(successView, new FrameLayout.LayoutParams(
                        FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));

                successView.setVisibility(View.VISIBLE);
            }
        } else {
            if (successView != null) {
                successView.setVisibility(View.INVISIBLE);
            }
        }
    }


    //加载中的布局
    private View createLoadingView() {
        View view = View.inflate(getActivity(), R.layout.loading_page_loading, null);
        return view;
    }

    //加载失败的布局
    private View createErrorView() {
        View view = View.inflate(getActivity(), R.layout.loading_page_error, null);
        Button page_bt = (Button) view.findViewById(R.id.page_error_bt);
        page_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show();
            }
        });
        return view;
    }

    //为空的布局
    private View createEmptyView() {
        View view = View.inflate(getActivity(), R.layout.loading_page_empty, null);
        return view;
    }

    //请求服务器,服务器返回只有三种状态
    //错误,空,成功
    public enum LoadResult {
        error(2), empty(3), success(4);
        int value;

        //枚举类的构造器只能使用private
        LoadResult(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    //根据服务器的数据,切换状态
    public void show() {

        if (state == STATE_ERROR || state == STATE_EMPTY) {
            state = STATE_LOADING;
        }


        //请求服务器,获取服务器上的数据,进行判断
        //请求服务器,返回一个结果
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(1500);
                final LoadResult result = load();

                //这里报过空指针
                //当快速退出,快速进入的时候,偶尔出现
                //可能与Fragment工场的缓存有关

                if (getActivity() != null) {

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (result != null) {
                                state = result.getValue();
                                //state = 2 + new Random().nextInt(3);
                                //状态改变了,重新判断当前应该显示哪个界面
                                showPage();
                            }
                        }
                    });
                }
            }
        }).start();
        showPage();
    }

    /**
     * 请求服务器数据
     *
     * @return
     */
    public abstract LoadResult load();

    /**
     * 创建成功的界面
     *
     * @return
     */
    protected abstract View createSuccessView();
}
