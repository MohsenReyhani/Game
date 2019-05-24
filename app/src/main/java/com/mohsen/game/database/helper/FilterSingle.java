package com.mohsen.game.database.helper;

public class FilterSingle extends Filter {
	
	private String mParam, mOperand;
	Object mValue;
	
	@Override
	public String getFilterString() {
		String result = "";
		if (mValue instanceof String) {
			if(mOperand.contains("in"))
				result += mParam + " " + mOperand + " ( " + mValue + " ) ";
			else
				result += mParam + " " + mOperand + " '" + mValue + "'";
		} else
			result += mParam + " " + mOperand + " " + mValue ;
		
		result = " ( " + result + " ) ";
		return result;
	}
	
	public FilterSingle() {
		
	}
	
	public FilterSingle(String param, String operand, Object value) {
		mParam = param;
		mOperand = operand;
		mValue = value;
	}
	
	public void setParam(String param) {
		mParam = param;
	}
	
	public String getParam() {
		return mParam;
	}
	
	public void setOperand(String operand) {
		mOperand = operand;
	}
	
	public String getOperand() {
		return mOperand;
	}
	
	public void setValue(Object value) {
		mValue = value;
	}
	
	public Object getValue() {
		return mValue;
	}
	
}
