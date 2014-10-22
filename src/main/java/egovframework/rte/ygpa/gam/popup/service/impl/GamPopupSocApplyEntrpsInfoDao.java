/**
 * 
 */
package egovframework.rte.ygpa.gam.popup.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.popup.service.GamPopupSocApplyEntrpsInfoVO;


/**
 * @Class Name : GamPopupSocApplyEntrpsInfoDao.java
 * @Description : 투자비보전신청업체정보 DAO Class
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

@Repository("gamPopupSocApplyEntrpsInfoDao")
public class GamPopupSocApplyEntrpsInfoDao extends YGPAAbstractDAO {
	
	/**
	 * 투자비보전신청업체정보 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 투자비보전신청업체정보 목록
	 * @exception Exception
	 */
    public List selectSocApplyEntrpsInfoList(GamPopupSocApplyEntrpsInfoVO searchVO) throws Exception {
        return list("gamPopupSocApplyEntrpsInfoDao.selectSocApplyEntrpsInfoList_S", searchVO);
    }

    /**
	 * 투자비보전신청업체정보 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 투자비보전신청업체정보 목록 총 갯수
	 * @exception
	 */
    public int selectSocApplyEntrpsInfoListTotCnt(GamPopupSocApplyEntrpsInfoVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamPopupSocApplyEntrpsInfoDao.selectSocApplyEntrpsInfoListTotCnt_S", searchVO);
    }

}
