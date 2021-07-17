package com.project.common.utils;

import java.math.BigDecimal;

public class CoputerUtils {

	public static double getNumberTwoPontis(double bb) {
		BigDecimal b = new BigDecimal(bb);
		double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return f1;
	}

}
