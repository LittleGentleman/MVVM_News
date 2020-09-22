package com.gmm.www.news.headlinenews;

import com.gmm.www.base.BaseApplication;
import com.gmm.www.base.activity.IBaseView;
import com.gmm.www.base.model.BaseModel;
import com.gmm.www.base.utils.Logger;
import com.gmm.www.base.utils.ToastUtil;
import com.gmm.www.base.viewmodel.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:gmm
 * @date:2020/4/27
 * @类说明:
 */
public class HeadLineNewsViewModel extends BaseViewModel<HeadLineNewsViewModel.IMainView,ChannelsModel> implements BaseModel.IModelListener<ArrayList<ChannelsModel.Channel>> {
    public ArrayList<ChannelsModel.Channel> channels = new ArrayList<>();

    public HeadLineNewsViewModel() {
        model = new ChannelsModel();
        model.register(this);
    }


    public void refresh() {
        model.getCachedDataAndLoad();
    }

    @Override
    public void onLoadFinish(BaseModel model, ArrayList<ChannelsModel.Channel> data) {
        if (model instanceof ChannelsModel) {
            if (null != getPageView() && data instanceof List) {
                channels.clear();
                channels.addAll(data);
                getPageView().onChannelsLoaded(channels);
            }
        }
    }

    @Override
    public void onLoadFail(BaseModel model, String prompt) {
        ToastUtil.show(BaseApplication.sApplication,prompt);
    }

    public interface IMainView extends IBaseView {
        void onChannelsLoaded(ArrayList<ChannelsModel.Channel> channels);
    }
}
