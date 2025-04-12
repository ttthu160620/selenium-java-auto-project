package testcase;

import java.util.ArrayList;

public class Test {
	public static void main(String[] args) {
		isEvents(224488);
		System.out.println(countDigit(1122556666, 1));
		System.out.println(data(50));
		System.out.println(getStr(100));
		
	}
	
	public static int countDigit(int n, int digit) {
		String nStr = String.valueOf(n);
		String newNStr = nStr.replaceAll(String.valueOf(digit),"");
	    int count = nStr.length() - newNStr.length();
	    return count * (n/Math.abs(n));
	}
	
	public static int isEvents(int n) {
		int temp, digit;
        temp = n;
        ArrayList<Integer> digitList = new ArrayList<>();
        while(temp > 0)
        {
            digit = temp % 10;
            digitList.add(Integer.valueOf(digit));
            temp = temp / 10;
        }
        int result = 1; 
        for(int index : digitList) {
        	if(index % 2 == 0) {
        		result = 1;
        	} else {
        		result = 0;
        	}
        }
        if(result == 1) {
        	System.out.println("This number is even");
        }
        else {
        	System.out.println("This number is not even");
        }
		return result;
	}
	
	public static String data(int n) {
		//1-2+3-4+5-6+7-8
		String data1 = "";
		String data = "";
		for(int i = 1; i <= n; i++) {
			if(i % 2 == 1) {
				data1 = "+" + String.valueOf(+i);
			} else {
				data1 = String.valueOf(-i);
			}
			data += data1;
		}
		return data;
	}
	
	public static String getStr(int n){
	    String nStr = "";
	    int chan = 1;
	    int le = 2;
	    nStr += String.valueOf(chan) + String.valueOf(-le);
	    for (int i = 1;i<n/2;i++){
	        chan+=2;
	        le+=2;
	        nStr +="+"+ String.valueOf(chan) + String.valueOf(-le);
	    }
	    return nStr;
	}
}
