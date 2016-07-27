/**
 * Copyright 2015 Pengyuan-Jiang
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * <p>
 * Author：Ybao on 2015/11/5 ‏‎17:53
 * <p>
 * QQ: 392579823
 * <p>
 * Email：392579823@qq.com
 */
package com.ybao.pullrefreshview.layout;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.ybao.pullrefreshview.support.impl.Loadable;
import com.ybao.pullrefreshview.support.impl.Refreshable;
import com.ybao.pullrefreshview.support.type.LayoutType;


/**
 * 经典下拉刷新，上拉加载的通用控件（可用于任意控件 如 ListView GridView WebView ScrollView）
 * <p>
 * 弹性下（上）拉，滑倒顶（低）部无需松开即可继续拉动
 *
 * @author Ybao
 */
public class PullRefreshLayout extends FlingLayout {

    protected Loadable mFooter;
    protected Refreshable mHeader;
    protected boolean hasHeader = true;
    protected boolean hasFooter = true;

    public PullRefreshLayout(Context context) {
        this(context, null);
    }

    public PullRefreshLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PullRefreshLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    @Override
    protected void fling(float nowY) {
        if (mHeader != null && nowY >= 0 && hasHeader) {
            mHeader.fling(nowY);
        } else if (mFooter != null && nowY <= 0 && hasFooter) {
            mFooter.fling(nowY);
        } else {
            startMoveTo(nowY, 0);
        }
    }

    @Override
    public void moveTo(float y) {
        onScroll(y);
        setMoveY(y);
        View terget = getPullView();
        if (terget == null) {
            return;
        }
        if (y > 0 && mHeader != null && hasHeader) {
            mHeader.moveTo(terget, y);
            return;
        } else if (y < 0 && mFooter != null && hasFooter) {
            mFooter.moveTo(terget, y);
            return;
        }

        setViewTranslationY(terget, y);
        if (mHeader != null) {
            setViewTranslationY((View) mHeader, y);
        }
        if (mFooter != null) {
            setViewTranslationY((View) mFooter, y);
        }
    }

    public void openHeader() {
        if (mHeader != null && hasHeader) {
            mHeader.open();
        }
    }

    public void openFooter() {
        if (mFooter != null && hasFooter) {
            mFooter.open();
        }
    }

    public void closeHeader() {
        if (mHeader != null && hasHeader) {
            mHeader.close();
        }
    }

    public void closeFooter() {
        if (mFooter != null && hasFooter) {
            mFooter.close();
        }
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        if (child instanceof Refreshable && mHeader == null) {
            mHeader = (Refreshable) child;
            mHeader.setPullRefreshLayout(this);
        } else if (child instanceof Loadable && mFooter == null) {
            mFooter = (Loadable) child;
            mFooter.setPullRefreshLayout(this);
        }
        super.addView(child, index, params);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        int height = getHeight();
        if (mHeader != null) {
            View mHeaderView = (View) mHeader;
            mHeaderView.layout(mHeaderView.getLeft(), -mHeaderView.getMeasuredHeight(), mHeaderView.getRight(), 0);
        }
        if (mFooter != null) {
            View mFooterView = (View) mFooter;
            mFooterView.layout(mFooterView.getLeft(), height, mFooterView.getRight(), height + mFooterView.getMeasuredHeight());
        }
    }

    public void setHasFooter(boolean hasFooter) {
        this.hasFooter = hasFooter;
    }

    public void setHasHeader(boolean hasHeader) {
        this.hasHeader = hasHeader;
    }
}