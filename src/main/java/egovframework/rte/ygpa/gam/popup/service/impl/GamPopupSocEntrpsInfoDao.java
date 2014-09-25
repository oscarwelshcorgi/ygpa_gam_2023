/**
 * 
 */
package egovframework.rte.ygpa.gam.popup.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.popup.service.GamPopupSocEntrpsInfoVO;


/**
 * @Class Name : GamPopupSocEntrpsInfoDao.java
 * @Description : 업체정보 DAO Class
 * @Modification Information
 * 
 * @author HNJ
 * @since 2014. 9. 24.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 9. 24.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Repository("gamPopupSocEntrpsInfoDao")
public class GamPopupSocEntrpsInfoDao extends YGPAAbstractDAO {
	
	/**
	 * 업체정보 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 업체정보 목록
	 * @exception Exception
	 */
    public List selectSocEntrpsInfoList(GamPopupSocEntrpsInfoVO searchVO) throws Exception {
        return list("gamPopupSocEntrpsInfoDao.selectSocEntrpsInfoList_S", searchVO);
    }

    /**
	 * 업체정보 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 업체정보 목록 총 갯수
	 * @exception
	 */
    public int selectSocEntrpsInfoListTotCnt(GamPopupSocEntrpsInfoVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamPopupSocEntrpsInfoDao.selectSocEntrpsInfoListTotCnt_S", searchVO);
    }

}
