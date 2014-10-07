/**
 * 
 */
package egovframework.rte.ygpa.gam.soc.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 
 * @author 김종민
 * @since 2014. 10. 6.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 6.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public class GamSocApplyVO extends ComDefaultVO {
	private static final long serialVersionUID = 1L;
	/** 공사관리청코드(조회조건) **/
	private String sPrtAtCode;
	
	/** 공사준공년도(조회조건) **/
	private String sCmplYr;
	
	/** 공사일련번호(조회조건) **/
	private String sConstNo;
	
	/** 면제요청청코드(조회조건) **/
	private String sAppPrtAtCode;
	
	/** 면제요청업체코드(조회조건) **/
	private String sAppAgentCode;
	
	/** 요청횟수(조회조건) **/
	private String sUseNo;
	
	/** 허가원부관리청코드 **/
	private String prtAtCode;

	/** 허가원부관리청명 **/
	private String prtAtCodeNm;
		
	/** 허가원부공사준공년도 **/
	private String cmplYr;
	
	/** 허가원부일련번호 **/
	private String constNo;
	
	/** 면제요청청코드 **/
	private String appPrtAtCode;

	/** 면제요청청명 **/
	private String appPrtAtCodeNm;
	
	/** 면제요청업체코드 **/
	private String appAgentCode;
	
	/** 면제요청신청횟수 **/
	private String useNo;
	
	/** 공사업체코드  ( soc_agent_detail_f에 등록된 업체만이 가능..)**/
	private String agentCode;
	
	/** 공사업체명 **/
	private String agentName;
	
	/** 보전처리대상 금액 **/
	private String exmpAmnt;
	
	/** 보전처리 누계액 **/
	private String exmpAcc;
	
	/** 보전처리 보전기간 from **/
	private String periodFr;
	
	/** 보전처리 보전기간 to **/
	private String periodTo;
	
	/** 보전처리 조건 1.금액, 2.기간 **/
	private String exmpCond;
	
	/** 적용요율 구분 1.과거, 2.현재 **/
	private String rateGubun;
	
	/** 특이사항 **/
	private String remark;
	
	/** 공사(상계)명칭 **/
	private String item;
	
	/** 사용여부 **/
	private String useYn;
	
	/** 사업자등록번호(면제화주처리시) **/
	private String bzRgstId;
	
	/** 신청일자 **/
	private String applDate;
	
	/** 허가일자 **/
	private String perfDt;
	
	/** 접안 외항 과거 요율 **/
	private String r11Rate;
	
	/** 접안 내항 고거 요율 **/
	private String r12Rate;

	/** 정박 외항 과거 요율 **/
	private String r21Rate;
	
	/** 정박 내항 고거 요율 **/
	private String r22Rate;
	
	/** 수정인 **/
	private String updtUid;
	
	/** 수정날짜 **/
	private String updtDate;
}
