/**
 * 
 */
package egovframework.rte.ygpa.gam.ctrt.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.ctrt.service.GamFcltyCtrtLgerHistVO;

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
@Repository("gamFcltyCtrtLgerHistDao")
public class GamFcltyCtrtLgerHistDao extends YGPAAbstractDAO {
	
	/**
	 * 계약대장목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 목록
	 * @exception Exception
	 */
    public List selectFcltyCtrtLgerHistList(GamFcltyCtrtLgerHistVO searchVO) throws Exception {
        return list("gamFcltyCtrtLgerHistDao.selectFcltyCtrtLgerHistList", searchVO);
    }


    /**
	 * 계약대장목록,  합계금액 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약대장목록
	 * @exception Exception
	 */
    public GamFcltyCtrtLgerHistVO selectFcltyCtrtLgerHistInfoSum(GamFcltyCtrtLgerHistVO searchVO){
    	return (GamFcltyCtrtLgerHistVO) selectByPk("gamFcltyCtrtLgerHistDao.selectFcltyCtrtLgerHistInfoSum", searchVO);
    }
    
    
    /**
	 * 계약대장 상세내역 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약대장 상세내역
	 * @exception Exception
	 */
    public GamFcltyCtrtLgerHistVO selectFcltyCtrtLgerHistDetail(GamFcltyCtrtLgerHistVO searchVO){
    	return (GamFcltyCtrtLgerHistVO) selectByPk("gamFcltyCtrtLgerHistDao.selectFcltyCtrtLgerHistDetail", searchVO);
    }

}
