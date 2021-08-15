package com.bw.mvp_lib.presenter;

import com.bw.mvp_lib.model.IModel;
import com.bw.mvp_lib.view.IView;

public
/**
 *MyVideo
 *file name is : BasePresenter
 *created by Ender on 2021/8/14 8:07.
 *author : 王益德
 *Describe:
 */
abstract
class BasePresenter<V extends IView,M extends IModel> implements IPresenter {

    protected V view;
    protected M model;

    public BasePresenter(V view, M model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void destroy() {
        if (model !=null){
            model.destroy();
            model = null;
        }
    }
}
