/**
 * 
 */
package egovframework.rte.ygpa.gam.popup.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.popup.service.GamPopupSocApplyInfoVO;

/**
 * 
 * @author 김종민
 * @since 2014. 9. 29.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 9. 29.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Repository("gamPopupSocApplyInfoDao")
public class GamPopupSocApplyInfoDao extends YGPAAbstractDAO {
	/**
	 * 투자비보전신청업체를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
	public List selectSocApplyInfoList(GamPopupSocApplyInfoVO searchVO) throws Exception {
		return list("gamPopupSocApplyInfoDao.selectSocApplyInfoList_D", searchVO);
	}
	
	/**
	 * 투자비보전신청업체 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
	public int selectSocApplyInfoListTotCnt(GamPopupSocApplyInfoVO searchVO) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamPopupSocApplyInfoDao.selectSocApplyInfoListTotCnt_S", searchVO);
	}
}
