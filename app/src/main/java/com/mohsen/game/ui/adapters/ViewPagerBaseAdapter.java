package com.mohsen.game.ui.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.mohsen.game.application.persian.calendar.PersianCalendar;

public abstract class ViewPagerBaseAdapter extends FragmentStatePagerAdapter {
	
	
	int mViewBy;
	int mViewMode;
	DateViewPagerSlideGetItem mDateViewPagerSlideGetItem;
	PersianCalendar mFromDate, mToDate;
	Context mContext;
	
	
	int mItemsCount = 0;
	
	
	public ViewPagerBaseAdapter(FragmentManager fm,
	                            Context context,
	                            int viewBy,
	                            Long FromDateInMillis,
	                            Long ToDateInMillis,
	                            DateViewPagerSlideGetItem dateViewPagerSlideGetItem) {
		super(fm);
		
		mFromDate = new PersianCalendar();
		mFromDate.setTimeInMillis(FromDateInMillis);
		mToDate = new PersianCalendar();
		mToDate.setTimeInMillis(ToDateInMillis);
		mViewBy = viewBy;
		mContext = context;
		mDateViewPagerSlideGetItem = dateViewPagerSlideGetItem;
		
		this.mFromDate.resetCalendarTime();
		this.mToDate.resetCalendarTime();
		
		calcItemsCount();
		
	}
	
	@Override
	public abstract CharSequence getPageTitle(int position);
	
	protected abstract void calcItemsCount();
	
	public abstract int getItemPositionByDate(Long dateTimeInMilliSecond);
	
	public abstract PersianCalendar[] getFilterDateByPosition(int position);
	
	public void setViewBy(int viewBy) {
		mViewBy = viewBy;
		this.notifyDataSetChanged();
	}
	
	public void setInitialDates(PersianCalendar FromDate, PersianCalendar ToDate) {
		mFromDate = FromDate;
		mToDate = ToDate;
		
		this.notifyDataSetChanged();
	}
	
	@Override
	public void notifyDataSetChanged() {
		calcItemsCount();
		super.notifyDataSetChanged();
	}
	
	@Override
	public Fragment getItem(int position) {
		PersianCalendar[] dateFilter = getFilterDateByPosition(position);
		if (dateFilter.length == 2) {
			
			return mDateViewPagerSlideGetItem.onDatePageSlideGetItem(mViewBy, mViewMode, getPageTitle(position).toString());
			
		} else return null;
	}
	
	@Override
	public int getCount() {
		return mItemsCount;
		
	}
	
	
	public interface DateViewPagerSlideGetItem {
		public Fragment onDatePageSlideGetItem(int viewBy, int viewMode, String title);
	}
	
	
}
