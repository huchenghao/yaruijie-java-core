package com.yrj.number;

import java.math.BigDecimal;

public class Arith {

	/**
	 * 提供精确的加法运算。
	 * 
	 * @param b1
	 *            被加数
	 * @param b2
	 *            加数
	 * @return 两个参数的和
	 */
	public static BigDecimal add(BigDecimal b1, BigDecimal b2) {
		return b1.add(b2).setScale(2,BigDecimal.ROUND_DOWN);
	}

	/**
	 * 提供精确的减法运算。
	 * 
	 * @param v1
	 *            被减数
	 * @param v2
	 *            减数
	 * @return 两个参数的差
	 */
	public static BigDecimal sub(BigDecimal b1, BigDecimal b2) {
		return b1.subtract(b2).setScale(2,BigDecimal.ROUND_DOWN);
	}

	/**
	 * 提供精确的乘法运算。
	 * 
	 * @param v1
	 *            被乘数
	 * @param v2
	 *            乘数
	 * @return 两个参数的积
	 */
	public static BigDecimal mul(BigDecimal b1, BigDecimal b2) {
		return b1.multiply(b2).setScale(2,BigDecimal.ROUND_DOWN);
	}

	/**
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
	 * 
	 * @param v1
	 *            被除数
	 * @param v2
	 *            除数
	 * @param scale
	 *            表示表示需要精确到小数点以后几位。
	 * @return 两个参数的商
	 */
	public static BigDecimal div(BigDecimal b1, BigDecimal b2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 提供精确的小数位四舍五入处理。
	 * 
	 * @param v
	 *            需要四舍五入的数字
	 * @param scale
	 *            小数点后保留几位
	 * @return 四舍五入后的结果
	 */
	public static BigDecimal round(BigDecimal b, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		// BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * 如果传入数字的两位小数,大于.01 那么加1,否则舍去
	 * 例如:  13.0123862 --> 进1  ---> 14
	 *        13.0023862 --> 舍  ---> 13
	 * @Title: enterOrabandon
	 * @Description: TODO
	 * @param b
	 * @return
	 * @author gs
	 * @date 2018年11月22日 下午12:11:00
	 */
	public static BigDecimal enterOrabandon(BigDecimal b) {
		String bStr = b.toString();
		String[] split = bStr.split("\\.");
		if (split.length <= 1) {
			return b;
		}
		if (split[1].length() >= 2) {
			String substring0 = split[1].substring(0, 1);
//			char charAtO = split[1].charAt(0);
			System.out.println(substring0);
//			char charAt1 = split[1].charAt(1);
			String substring1 = split[1].substring(1, 2);
			System.out.println(substring1);
			if (Integer.valueOf(substring0) > 0 || Integer.valueOf(substring1) > 0) {
				return new BigDecimal(Math.ceil(b.doubleValue()));
			}else{
				return new BigDecimal(b.longValue());
			}
		}else{
			if (Integer.valueOf(split[1].substring(0, 1)) > 0) {
				return new BigDecimal(Math.ceil(b.doubleValue()));
			}else{
				return new BigDecimal(b.longValue());
			}
		}
	}
	
	

}
