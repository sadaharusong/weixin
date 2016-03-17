package com.example.weixin;

import java.util.ArrayList;
import java.util.List;

import com.example.weixin.R.id;

import android.R.integer;
import android.app.ActionBar;
import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.LayoutParams;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.TextureView;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {
	private ViewPager mViewPager;
	private FragmentPagerAdapter mAdapter;
	private List<Fragment> mDates;
	
	private TextView mChatTextView;
	private TextView mFriendTextView;
	private TextView mContactTextView;
	
//	private LinearLayout mChatLinearLayout;
//	private BadgeView mBadgeView;
	private ImageView mTabline;
	private int mScreen1_3;
	
	private int mCurrentPageIndex;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
		ActionBar actionBar = getActionBar();
		actionBar.hide();
		initTabLine();
		initView();
	}
	private void initTabLine() {
		mTabline = (ImageView) findViewById(R.id.id_iv_tabline);
		Display display = getWindow().getWindowManager().getDefaultDisplay();
		DisplayMetrics outMetrics = new DisplayMetrics();
		display.getMetrics(outMetrics);
		mScreen1_3 = outMetrics.widthPixels / 3;
		
		android.view.ViewGroup.LayoutParams lp = mTabline.getLayoutParams();
		lp.width = mScreen1_3;
		mTabline.setLayoutParams(lp);
	}
	private void initView() {
		// TODO Auto-generated method stub
		mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
		mChatTextView = (TextView) findViewById(R.id.id_tv_chat);
		mFriendTextView = (TextView) findViewById(R.id.id_tv_friend);
		mContactTextView = (TextView) findViewById(R.id.id_tv_contact);
//		mChatLinearLayout = (LinearLayout)findViewById(R.id.id_ll_chat_swapper);
		mDates = new ArrayList<Fragment>();
		CharMainTabFragment tab01 = new CharMainTabFragment();
		FriendTabFragment tab02 = new FriendTabFragment();
		ContactCharMainTabFragment tab03 = new ContactCharMainTabFragment();
		
		mDates.add(tab01);
		mDates.add(tab02);
		mDates.add(tab03);

		mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
			
			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return mDates.size();
			}
			
			@Override
			public Fragment getItem(int arg0) {
				// TODO Auto-generated method stub
				return mDates.get(arg0);
			}
		};
		
		mViewPager.setAdapter(mAdapter);
		
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				// TODO Auto-generated method stub
				resetTextView();
				switch (position) {
				case 0:
//					if (mBadegeView != null) {
//						mChatLinearLayout.removeView(mBadgeView);
//					}
//					mBadegeView = new BadgeView(MainActivity.this);
//					mBadegeView.setBadgeCount(7);
//					mChatLinearLayout.addView(mBadgeView);
					mChatTextView.setTextColor(Color.parseColor("#008000"));
					break;
				case 1:
					mFriendTextView.setTextColor(Color.parseColor("#008000"));

					break;
				case 2:
					mContactTextView.setTextColor(Color.parseColor("#008000"));

					break;
				
				}
				
				mCurrentPageIndex = position;
			}
			
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPx) {
				// TODO Auto-generated method stub
				LinearLayout.LayoutParams lp = 
						(android.widget.LinearLayout.LayoutParams) mTabline.getLayoutParams();
				
				if (mCurrentPageIndex == 0 && position == 0 ) // 0 -> 1
				{
					lp.leftMargin = (int) (positionOffset * mScreen1_3 
							+ mCurrentPageIndex * mScreen1_3);
				}else if (mCurrentPageIndex == 1 && position == 0) {  //1 -> 0
					lp.leftMargin = (int) (mCurrentPageIndex * mScreen1_3
							+(positionOffset - 1)*mScreen1_3);
				}else if (mCurrentPageIndex == 1 && position == 1) {  //1->2
					lp.leftMargin = (int) (positionOffset * mScreen1_3 
							+ mCurrentPageIndex * mScreen1_3);
				}else if(mCurrentPageIndex == 2 && position == 1)  //2 ->1
				{
					lp.leftMargin = (int) (mCurrentPageIndex * mScreen1_3
							+(positionOffset - 1)*mScreen1_3);
				}
				mTabline.setLayoutParams(lp);
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	protected void resetTextView() {
		// TODO Auto-generated method stub
		mChatTextView.setTextColor(Color.BLACK);
		mFriendTextView.setTextColor(Color.BLACK);
		mContactTextView.setTextColor(Color.BLACK);
	}

	
}
