/**
 *
 */
package egovframework.rte.ygpa.gam.fcltyMng.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;

import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyUsageSttusInqireVO;




/**
*
* @author jckim
* @since 2014. 12. 8.
* @version 1.0
* @see
* <pre>
* << 개정이력(Modification Information) >>
*
*   수정일 		 수정자		 수정내용
*  -------		--------	---------------------------
*  2014. 12. 8.		jckim		최초 생성
*
* Copyright (C) 2013 by LFIT  All right reserved.
* </pre>
*/

@Repository("gamFcltyUsageSttusInqireDao")
public class GamFcltyUsageSttusInqireDao extends YGPAAbstractDAO{

    /**
     * 시설물 사용현황 목록 조회
     * @param vo ComDefaultVO
     * @return List
     * @exception Exception
     */
	@SuppressWarnings("unchecked")
	public List selectFcltyUsageSttusInqireList(GamFcltyUsageSttusInqireVO vo) throws Exception{
		return list("gamFcltyUsageSttusInqireDao.selectFcltyUsageSttusInqireList", vo);
	}

	public int selectFcltyUsageSttusInqireListTotCnt(GamFcltyUsageSttusInqireVO vo) {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamFcltyUsageSttusInqireDao.selectFcltyUsageSttusInqireListTotCnt", vo);
	}
}