package sean.com.drag.drag;

import android.view.View;

/**
 * Author: zhuwt
 * Date: 2016/7/4 16:00
 * Description: 说明
 * PackageName: sean.com.drag.drag.DragAdapter
 * Copyright: 杭州存网络科技有限公司
 **/
public interface RecycleCallBack {
    //item的点击事件
    void itemOnClick(int position,View view);

    void onMove(int from,int to);
}
