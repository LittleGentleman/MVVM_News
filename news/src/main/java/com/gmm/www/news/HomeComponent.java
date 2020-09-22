package com.gmm.www.news;

import com.billy.cc.core.component.CC;
import com.billy.cc.core.component.CCResult;
import com.billy.cc.core.component.IComponent;
import com.gmm.www.news.headlinenews.HeadLineNewsFragment;

/**
 * @author:gmm
 * @date:2020/4/26
 * @类说明:
 */
public class HomeComponent implements IComponent {
    @Override
    public String getName() {
        return "Home";
    }

    @Override
    public boolean onCall(CC cc) {
        String actionName = cc.getActionName();
        switch (actionName) {
            case "showHomeFragment":
                CCResult result = new CCResult();
                result.addData("home_fragment", new HeadLineNewsFragment());
                CC.sendCCResult(cc.getCallId(), result);
                return true;

            default:
                //其它actionName当前组件暂时不能响应，可以通过如下方式返回状态码为-12的CCResult给调用方
                CC.sendCCResult(cc.getCallId(), CCResult.errorUnsupportedActionName());
                return false;

        }

    }
}
