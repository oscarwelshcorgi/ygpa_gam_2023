package egovframework.rte.ygpa.gam.oper.gnrl.web;

import java.text.SimpleDateFormat;

import egovframework.com.utl.fcc.service.EgovDateUtil;
import egovframework.com.utl.fcc.service.EgovStringUtil;

/**
 * @Class Name : GamModuleTestController.java
 * @Description : 테스트 - 자체 삭제 예정...
 * @Modification Information
 *
 * @author 도명호
 * @since 2014-01-24
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class Test_com {

	public static void Con() {
		
		// param value
		String startDt = "2014-01-15";	
		String endDt = "2015-01-14";
		
		/*
		 1	일괄	
		 2	반기납
		 3	3분납	
		 4	분기납
		 5	월납	
		*/
		String feeGbn = "5";	// 월납
		
		// ------------------------------------
		// impl 에 넣을 부분.
		// ------------------------------------
		
		String cStartDt = EgovStringUtil.remove(startDt, '-');
		String cEndDt = EgovStringUtil.remove(endDt, '-');
		
		String[] startRetVal = new String[12];
		String[] endRetVal = new String[12];
		
		if( feeGbn.equals("2") ) {	// 반기납
		
			startRetVal[0] = cStartDt;
			endRetVal[0] = EgovDateUtil.addYearMonthDay(cStartDt, 0, 6, -1);
			
			startRetVal[1] = EgovDateUtil.addYearMonthDay(cStartDt, 0, 6, 0);
			endRetVal[1] = cEndDt;
			
		} else if( feeGbn.equals("3") ) {
		
		} else if( feeGbn.equals("4") ) {
			
		} else if( feeGbn.equals("5") ) {	// 월납
			
			for( int i = 0; i < 12; i++ ) {
				if( i == 0 ) {
					startRetVal[i] = cStartDt;
				} else {
					startRetVal[i] = EgovDateUtil.addYearMonthDay(cStartDt, 0, i, 0);
				}
				if( i == 11 ) {
					endRetVal[i] = cEndDt;
				} else {
					endRetVal[i] = EgovDateUtil.addYearMonthDay(startRetVal[i], 0, 1, -1);
				}
			}
			
		}
		
		// ------------------------------------
		
		System.out.println("###########################################");
		System.out.println("startDt -> " + startDt);
		System.out.println("endDt -> " + endDt);
		System.out.println("------------------------------------");
		//System.out.println("일수테스트(간격) -> " + getDiffDayCount("20140115", "20140120"));
		System.out.println("일수테스트 ('2014-01-15' ~ '2014-01-20') -> " + getDiffDayCount("20140115", EgovDateUtil.addYearMonthDay("20140120", 0, 0, 1)));
		System.out.println("일수테스트 ('2014-01-15' ~ '2015-01-14') -> " + getDiffDayCount(cStartDt, EgovDateUtil.addYearMonthDay(cEndDt, 0, 0, 1)));
		/*
		System.out.println("################# 2 반기납 #################");
		System.out.println("상반기 시작 -> " + startRetVal[0]);
		System.out.println("상반기 종료 -> " + endRetVal[0]);
		System.out.println("상반기 일수 -> " + getDiffDayCount(startRetVal[0], endRetVal[0]));
		System.out.println("------------------------------------");
		System.out.println("하반기 시작 -> " + startRetVal[1]);
		System.out.println("하반기 종료 -> " + endRetVal[1]);
		System.out.println("하반기 일수 -> " + getDiffDayCount(startRetVal[1], endRetVal[1]));
		*/
		System.out.println("################# 5 월납 #################");
		/*System.out.println("첫달 시작 -> " + startRetVal[0]);
		System.out.println("첫달 종료 -> " + endRetVal[0]);
		System.out.println("첫달 일수 -> " + getDiffDayCount(startRetVal[0], EgovDateUtil.addYearMonthDay(endRetVal[0], 0, 0, 1)));
		System.out.println("------------------------------------");
		System.out.println("둘째달 시작 -> " + startRetVal[1]);
		System.out.println("둘째달 종료 -> " + endRetVal[1]);
		System.out.println("둘째달 일수 -> " + getDiffDayCount(startRetVal[1], EgovDateUtil.addYearMonthDay(endRetVal[1], 0, 0, 1)));
		System.out.println("------------------------------------");
		System.out.println("셋째달 시작 -> " + startRetVal[2]);
		System.out.println("셋째달 종료 -> " + endRetVal[2]);
		System.out.println("셋째달 일수 -> " + getDiffDayCount(startRetVal[2], EgovDateUtil.addYearMonthDay(endRetVal[2], 0, 0, 1)));
		System.out.println("------------------------------------");
		System.out.println("막달 시작 -> " + startRetVal[11]);
		System.out.println("막달 종료 -> " + endRetVal[11]);
		System.out.println("막달 일수 -> " + getDiffDayCount(startRetVal[11], EgovDateUtil.addYearMonthDay(endRetVal[11], 0, 0, 1)));
		*/
		
		for( int i = 0; i < 12; i++ ) {
			if( i != 0 ) {
				System.out.println("------------------------------------");
			}
			System.out.println((i+1)+ " 달 시작 -> " + startRetVal[i]);
			System.out.println((i+1)+ " 달 종료 -> " + endRetVal[i]);
			System.out.println((i+1)+ " 달 일수 -> " + getDiffDayCount(startRetVal[i], EgovDateUtil.addYearMonthDay(endRetVal[i], 0, 0, 1)));
		}
		
		System.out.println("###########################################");
		
		
	}
	
	/**
	 * 
	 * 시작일과 종료일의 일수를 계산한다.
	 * 
	 * @param fromDate
	 * @param toDate
	 * @return 두날짜의 일수차
	 */
	public static int getDiffDayCount(String fromDate, String toDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		try {
			return (int) ((sdf.parse(toDate).getTime() - sdf.parse(fromDate).getTime()) / 1000 / 60 / 60 / 24);
		} catch (Exception e) {
			return 0;
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Con();
	}

}
