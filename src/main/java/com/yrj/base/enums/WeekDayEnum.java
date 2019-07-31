package com.yrj.base.enums;

public enum WeekDayEnum {
	Mon("星期一",1), 
	Tue("星期二",2), 
	Wed("星期三",3), 
	Thu("星期四",4), 
	Fri("星期五",5), 
	Sat("星期六",6), 
	Sun("星期日",7),
	UNKNOW("未知",99);
	
	private final String day;
	private final int index;
	  
	private WeekDayEnum(String day,int index) {
	     this.day = day;
	     this.index = index;
	}
	
	@Override
	public String toString() {
		return this.index+"_"+this.day;
	}
	/**
	 * 
	 * @Title: getWeekName
	 * @Description: 周N就传N 如想要获取周一,则传1
	 * @param index
	 * @return
	 * @author huchenghao
	 */
	public static String getWeekName(int index){
		for(WeekDayEnum day:WeekDayEnum.values()){
			if(day.getIndex() == index){
				return day.day;
			}
		}
		return "未知";
	}
	
	

	public String getDay() {
		return day;
	}

	public int getIndex() {
		return index;
	}
	
	
	
}
