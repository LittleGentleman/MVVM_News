package com.gmm.www.news.headlinenews;

import com.gmm.www.news.newslist.NewsListFragment;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

/**
 * @author:gmm
 * @date:2020/4/29
 * @类说明:
 */
public class HeadLineNewsFragmentAdapter extends FragmentStatePagerAdapter {
    private ArrayList<ChannelsModel.Channel> mChannels;

    public HeadLineNewsFragmentAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public void setChannels(ArrayList<ChannelsModel.Channel> channels) {
        this.mChannels = channels;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return NewsListFragment.newInstance(mChannels.get(position).channelId,mChannels.get(position).channelName);
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return FragmentPagerAdapter.POSITION_NONE;
    }

    @Override
    public int getCount() {
        if (mChannels != null && mChannels.size() > 0) {
            return mChannels.size();
        }
        return 0;
    }
}
