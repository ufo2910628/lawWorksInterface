package com.dtxx.util;

public class Code {

	public static int getRandom() {
		int x = (int) (Math.random() * 100000);
		return x;
	}
	
	public static String getCode() {
		String num = getRandom() + "";
		if(num.length() == 6){
			return num;
		}else if(num.length() > 6){
			return num.substring(0, 6);
		}else{
			if(num.length() == 5){
				num = num + "0";
			}else if(num.length() == 4){
				num = num + "00";
			}else if(num.length() == 3){
				num = num + "000";
			}else if(num.length() == 2){
				num = num + "0000";
			}else if(num.length() == 1){
				num = num + "00000";
			}
			return num;
		}
	}

	public static void main(String[] args) {
		for(;;){
			System.out.println(Code.getCode());
		}
	}

}
