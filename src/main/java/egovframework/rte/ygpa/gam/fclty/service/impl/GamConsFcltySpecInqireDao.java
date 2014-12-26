/**
 *
 */
package egovframework.rte.ygpa.gam.fclty.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.fclty.service.GamConsFcltySpecInqireVO;
import egovframework.rte.ygpa.gam.fclty.service.GamConsFcltySpecMngVO;

/**
 * 
 * @author LFIT
 * @since 2014. 12. 5.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 5.		LFIT		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Repository("gamConsFcltySpecInqireDao")
public class GamConsFcltySpecInqireDao extends YGPAAbstractDAO{

	/**
	 * 시설관리 목록 조회
	 * @param vo ComDefaultVO
	 * @return List
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComDefaultVO> selectFcltySpecInqireList(GamConsFcltySpecInqireVO searchVO) throws Exception{
		return list("gamConsFcltySpecInqireDao.selectFcltySpecInqireList", searchVO);
	}

	/**
	 * 시설관리 총 수
	 * @param ComDefaultVO vo
	 * @return int
	 * @exception Exception
	 */
    public int selectFcltySpecInqireListTotCnt(GamConsFcltySpecInqireVO searchVO) throws Exception {
    	return (Integer)getSqlMapClientTemplate().queryForObject("gamConsFcltySpecInqireDao.selectFcltySpecInqireListTotCnt", searchVO);
    }

    /**
     * 시설관리 상세화면
     * @param vo
     * @return GamFcltyManageVO
     */
	public EgovMap fcltInqireSelectView(Map fcltyManageVO) throws Exception {
		return (EgovMap) selectByPk("gamConsFcltySpecInqireDao.fcltyInqireSelectView", fcltyManageVO);
	}

	/**
     * 시설관리 상세화면(제원)
     * @param vo
     * @return GamFcltyManageVO
     */
	public EgovMap fcltySpecInqireSelectView(Map fcltyManageVO) throws Exception {
		return (EgovMap) selectByPk("gamConsFcltySpecInqireDao.fcltySpecInqireSelectView", fcltyManageVO);
	}

	/**
     * 시설관리 파일 목록 조회
     * @param vo ComDefaultVO
     * @return List
     * @exception Exception
     */
	public List<ComDefaultVO> selectFcltySpecInqireFileList(GamConsFcltySpecInqireVO searchVO) throws Exception {
		return list("gamConsFcltySpecInqireDao.selectFcltySpecInqireFileList", searchVO);
	}

	/**
     * 시설관리 파일 총 수
     * @param ComDefaultVO vo
     * @return int
     * @exception Exception
     */
	public int selectFcltySpecInqireFileListTotCnt(GamConsFcltySpecInqireVO searchVO) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamConsFcltySpecInqireDao.selectFcltySpecInqireFileListTotCnt", searchVO);
	}
}