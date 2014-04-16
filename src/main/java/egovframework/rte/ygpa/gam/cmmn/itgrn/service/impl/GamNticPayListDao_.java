/**
 * 
 */
package egovframework.rte.ygpa.gam.cmmn.itgrn.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.cmmn.itgrn.service.GamNticPayListVO_;

/**
 * 
 * @author kok
 * @since 2014. 2. 7.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 2. 7.		kok		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Repository("gamNticPayListDao_")
public class GamNticPayListDao_ extends YGPAAbstractDAO{
	
	
	/**
	 * 납부현황목록조회
	 * @param vo ComDefaultVO
	 * @return List
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComDefaultVO> selectNticPayList(ComDefaultVO vo) throws Exception{
		return list("gamNticPayListDao_.selectFcltyMngtList", vo);
	}
	
	
	/**
	 * 납부현황목록조회 총 수
	 * @param ComDefaultVO vo
	 * @return int
	 * @exception Exception
	 */
    public int selectNticPayListTotCnt(ComDefaultVO vo) throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamNticPayListDao_.selectFcltyMngtListTotCnt", vo);
    }
    
    
    /**
     * 납부현황목록조회
     * @param vo
     * @return GamFcltyManageVO_
     */
    public GamNticPayListVO_ gamNticPayListSelectView(GamNticPayListVO_ vo){
        return (GamNticPayListVO_) selectByPk("gamNticPayListDao_.gamNticPayListSelectView", vo);
    }
}