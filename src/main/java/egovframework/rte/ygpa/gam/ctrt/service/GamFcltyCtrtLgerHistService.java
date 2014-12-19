package egovframework.rte.ygpa.gam.ctrt.service;

import java.util.List;


/**
 * 
 * @author HNJ
 * @since 2014. 10. 29.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 29.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamFcltyCtrtLgerHistService {
	
	/**
	 * 계약대장목록 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약대장목록
	 * @exception Exception
	 */
	List<?> selectFcltyCtrtLgerHistList(GamFcltyCtrtLgerHistVO searchVO) throws Exception;
	
	
	/**
	 * 계약대장목록 총갯수 및 금액합계 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약대장목록 총갯수 및 금액합계
	 * @exception Exception
	 */
	GamFcltyCtrtLgerHistVO selectFcltyCtrtLgerHistInfoSum(GamFcltyCtrtLgerHistVO searchVO) throws Exception;
	
	
	/**
	 * 계약대장 상세내역 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약대장 상세내역
	 * @exception Exception
	 */
	GamFcltyCtrtLgerHistVO selectFcltyCtrtLgerHistDetail(GamFcltyCtrtLgerHistVO searchVO) throws Exception;
	
	
	/**
	 * 계약공동도급목록 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약공동도급목록
	 * @exception Exception
	 */
	List<?> selectFcltyCtrtJoinContrFList(GamFcltyCtrtLgerHistVO searchVO) throws Exception;
	
	
	/**
	 * 계약공동도급목록 총갯수 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약공동도급목록 총갯수
	 * @exception Exception
	 */
	int selectFcltyCtrtJoinContrFTotalCnt(GamFcltyCtrtLgerHistVO searchVO) throws Exception;
	
	
	/**
	 * 계약공동도급 상세내역 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약공동도급 상세내역
	 * @exception Exception
	 */
	GamFcltyCtrtLgerHistVO selectFcltyCtrtJoinContrFDetail(GamFcltyCtrtLgerHistVO searchVO) throws Exception;
	
	
	/**
	 * 계약변경목록 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약대장목록
	 * @exception Exception
	 */
	List<?> selectFcltyCtrtChangeFList(GamFcltyCtrtLgerHistVO searchVO) throws Exception;
	
	
	/**
	 * 계약변경목록 총갯수 및 금액합계 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약변경목록 총갯수 및 금액합계
	 * @exception Exception
	 */
	GamFcltyCtrtLgerHistVO selectFcltyCtrtChangeFListSum(GamFcltyCtrtLgerHistVO searchVO) throws Exception;
	
	
	/**
	 * 계약대금지급목록 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약대금지급목록
	 * @exception Exception
	 */
	List<?> selectFcltyCtrtMoneyPymntFList(GamFcltyCtrtLgerHistVO searchVO) throws Exception;
	
	
	/**
	 * 계약대금지급목록 총갯수 및 금액합계 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약대금지급목록 총갯수 및 금액합계
	 * @exception Exception
	 */
	GamFcltyCtrtLgerHistVO selectFcltyCtrtMoneyPymntFListSum(GamFcltyCtrtLgerHistVO searchVO) throws Exception;
	
	
	/**
	 * 계약이행이월목록 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약이행이월목록
	 * @exception Exception
	 */
	List<?> selectFcltyCtrtFulfillCaryFwdFList(GamFcltyCtrtLgerHistVO searchVO) throws Exception;
	
	
	/**
	 * 계약이행이월목록 총갯수 및 금액합계 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약이행이월목록 총갯수 및 금액합계
	 * @exception Exception
	 */
	GamFcltyCtrtLgerHistVO selectFcltyCtrtFulfillCaryFwdFListSum(GamFcltyCtrtLgerHistVO searchVO) throws Exception;
	
	
	/**
	 * 계약낙찰정보 목록을 가져온다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약낙찰정보 목록
	 * @exception Exception
	 */
	List<?> selectFcltyCtrtScsbidInfoHistList(GamFcltyCtrtLgerHistVO searchVO) throws Exception;
	
	/**
	 * 계약낙찰정보 목록의 총개수를 가져온다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약낙찰정보 목록 총개수
	 * @exception Exception
	 */
	int selectFcltyCtrtScsbidInfoHistListTotCnt(GamFcltyCtrtLgerHistVO searchVO) throws Exception;

}
