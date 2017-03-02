package ua.form.filter;

import java.util.regex.Pattern;

public class DigitValueFormFilter {

private String min = "";
	
	private String max = "";
	
	private int minInt = 0;
	
	private int maxInt = 0;
	
	private static final Pattern p = Pattern.compile("^[0-9]{1,9}$");

	public String getMin() {
		return min;
	}

	public void setMin(String min) {
		if(p.matcher(min).matches()) minInt = Integer.valueOf(min);
		this.min = min;
	}

	public String getMax() {
		return max;
	}

	public void setMax(String max) {
		if(p.matcher(max).matches()) maxInt = Integer.valueOf(max);
		this.max = max;
	}

	public int getMinInt() {
		return minInt;
	}

	public void setMinInt(int minInt) {
		this.minInt = minInt;
	}

	public int getMaxInt() {
		return maxInt;
	}

	public void setMaxInt(int maxInt) {
		this.maxInt = maxInt;
	}


	
}
