package com.mohsen.game.database.helper;

import java.util.ArrayList;
import java.util.List;

public class FilterGroup extends Filter {
	
	private List<Filter> mFilterList;
	private FilterType mFilterType;
	
	public FilterGroup() {
		mFilterList = new ArrayList<>();
		mFilterType = FilterType.And;
	}

	public FilterGroup(FilterType filterType) {
		mFilterList = new ArrayList<>();
		mFilterType = filterType;
	}
	
	public FilterGroup(List<Filter> filterList) {
		mFilterList = new ArrayList<>();
		if (filterList.size() > 0)
			mFilterList.addAll(filterList);
		mFilterType = FilterType.And;
	}
	
	public FilterGroup(List<Filter> filterList, FilterType filterType) {
		mFilterList = new ArrayList<>();
		if (filterList.size() > 0)
			mFilterList.addAll(filterList);
		mFilterType = filterType;
	}
	
	public FilterGroup(Filter filter) {
		mFilterList = new ArrayList<>();
		if (filter.getFilterString().trim().length() > 0)
			mFilterList.add(filter);
		mFilterType = FilterType.And;
	}
	
	public FilterGroup(Filter filter, FilterType filterType) {
		mFilterList = new ArrayList<>();
		if (filter.getFilterString().trim().length() > 0)
			mFilterList.add(filter);
		mFilterType = filterType;
	}
	
	public enum FilterType {
		Or,
		And;
	}
	
	public void setFilterType(FilterType filterType) {
		mFilterType = filterType;
	}
	
	public FilterType getFilterType() {
		return mFilterType;
	}
	
	public void setFilterList(List<Filter> filterList) {
		mFilterList = filterList;
	}
	
	public List<Filter> getFilterList() {
		return mFilterList;
	}
	
	
	@Override
	public String getFilterString() {
		
		return buildString();
	}
	
	private String buildString() {
		String result = "";
		for (int i = 0; i < mFilterList.size(); i++) {
			
			Filter filter = mFilterList.get(i);
			
			if (!result.equals(""))
				result += " " + mFilterType.toString() + " ";
			
			if (filter instanceof FilterSingle) {
				FilterSingle filterSingle = (FilterSingle) filter;
				result += filterSingle.getFilterString();
			} else if (filter instanceof FilterGroup) {
				FilterGroup filterGroup = (FilterGroup) filter;
				result += "(" + filterGroup.getFilterString() + ")";
			}
		}
		return result;
	}
	
	public void add(String param, String operand, Object value) {
		FilterSingle filterSingle = new FilterSingle(param, operand, value);
		mFilterList.add(filterSingle);
	}
	
	public void add(Filter filter) {
		if (filter.getFilterString().length() > 0)
			mFilterList.add(filter);
	}
	
	public void addAll(ArrayList<Filter> filterList) {
		mFilterList = new ArrayList<>();
		mFilterList = filterList;
	}
	
	public void remove(int position) {
		mFilterList.remove(position);
	}
	
	public void clear() {
		mFilterList.clear();
	}
	
}
