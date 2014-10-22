/**
 * 
 */
package egovframework.rte.ygpa.gam.popup.service;

import java.util.List;


/**
 * @Class Name : GamPopupSocApplyEntrpsInfoService.java
 * @Description : 투자비보전신청업체정보 Business class
 * @Modification Information
 * 
 * @author HNJ
 * @since 2014. 10. 21.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 21.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamPopupSocApplyEntrpsInfoService {
	
	/**
	 * 투자비보전신청업체정보 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectSocApplyEntrpsInfoList(GamPopupSocApplyEntrpsInfoVO searchVO) throws Exception;

    /**
	 * 투자비보전신청업체정보 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectSocApplyEntrpsInfoListTotCnt(GamPopupSocApplyEntrpsInfoVO searchVO) throws Exception;

}
