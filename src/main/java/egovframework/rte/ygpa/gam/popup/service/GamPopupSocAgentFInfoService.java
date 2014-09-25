package egovframework.rte.ygpa.gam.popup.service;

import java.util.List;

/**
 * @Class Name : GamPopupAgentFInfoService.java
 * @Description : 허가원부정보 Business class
 * @Modification Information
 * 
 * @author HNJ
 * @since 2014. 9. 25.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 9. 25.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamPopupSocAgentFInfoService {

	/**
	 * 허가원부 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
	List selectSocAgentFInfoList(GamPopupSocAgentFInfoVO searchVO) throws Exception;

	/**
	 * 허가원부 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
	int selectSocAgentFInfoListTotCnt(GamPopupSocAgentFInfoVO searchVO) throws Exception;

}
