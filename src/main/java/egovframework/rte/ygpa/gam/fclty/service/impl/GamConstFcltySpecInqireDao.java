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
import egovframework.rte.ygpa.gam.fclty.service.GamConstFcltySpecInqireVO;

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

@Repository("GamConstFcltySpecInqireDao")
public class GamConstFcltySpecInqireDao extends YGPAAbstractDAO{

	/**
	 * 시설관리 목록 조회
	 * @param vo ComDefaultVO
	 * @return List
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComDefaultVO> selectFcltySpecInqireList(GamConstFcltySpecInqireVO searchVO) throws Exception{
		return list("GamConstFcltySpecInqireDao.selectFcltySpecInqireList", searchVO);
	}

	/**
	 * 시설관리 총 수
	 * @param ComDefaultVO vo
	 * @return int
	 * @exception Exception
	 */
    public int selectFcltySpecInqireListTotCnt(GamConstFcltySpecInqireVO searchVO) throws Exception {
    	return (Integer)getSqlMapClientTemplate().queryForObject("GamConstFcltySpecInqireDao.selectFcltySpecInqireListTotCnt", searchVO);
    }

    /**
     * 시설관리 상세화면
     * @param vo
     * @return GamFcltyManageVO
     */
	public EgovMap selectFcltySpecInqireDetail(Map searchVO) throws Exception {
		return (EgovMap) selectByPk("GamConstFcltySpecInqireDao.selectFcltySpecInqireDetail", searchVO);
	}

	/**
     * 시설관리 파일 목록 조회
     * @param vo ComDefaultVO
     * @return List
     * @exception Exception
     */
	public List<ComDefaultVO> selectFcltySpecInqireFileList(GamConstFcltySpecInqireVO searchVO) throws Exception {
		return list("GamConstFcltySpecInqireDao.selectFcltySpecInqireFileList", searchVO);
	}

	/**
     * 시설관리 파일 총 수
     * @param ComDefaultVO vo
     * @return int
     * @exception Exception
     */
	public int selectFcltySpecInqireFileListTotCnt(GamConstFcltySpecInqireVO searchVO) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("GamConstFcltySpecInqireDao.selectFcltySpecInqireFileListTotCnt", searchVO);
	}
}