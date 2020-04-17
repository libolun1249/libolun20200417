package com.bawei.libolun20200417;

import java.lang.ref.WeakReference;

public abstract class BasePresenter<M extends IBaseModel,V extends IBaseView> {
    public M model;
    //使用弱引用包装v层，解决内存泄露
    public WeakReference<V> weakReference;

    public BasePresenter() {
        //初始化p层
        model=initModel();
    }
    public void attach(V v) {
        weakReference=new WeakReference<>(v);
        //初始化v层
    }
    public void detach(){
        if (weakReference != null) {
            weakReference.clear();
            weakReference=null;
        }
    }
    public V getView(){
        return weakReference.get();
    }
    protected abstract M initModel();
}
