/**
 * 
 */
package egovframework.rte.ygpa.gam.fclty.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.fclty.service.GamMechFcltySpecInqireVO;

/**
 * 
 * @author LFIT
 * @since 2014. 12. 9.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 9.		김영길		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Repository("gamMechFcltySpecInqireDao")
public class GamMechFcltySpecInqireDao extends YGPAAbstractDAO {

	/**
	 * 기계시설제원관리 목록 조회
	 * @param searchVO
	 * @return
	 */
	public List selectMechFcltySpecInqireList(GamMechFcltySpecInqireVO searchVO) throws Exception {
		return list("gamMechFcltySpecInqireDao.selectMechFcltySpecInqireList_D", searchVO);
	}

	/**
	 * 기계시설제원관리 목록 총수 조회
	 * @param searchVO
	 * @return
	 */
	public int selectMechFcltySpecInqireListTotCnt(GamMechFcltySpecInqireVO searchVO) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamMechFcltySpecInqireDao.selectMechFcltySpecInqireListTotCnt_S", searchVO);
	}

	/**
	 * 기계시설제원관리 데이터 조회
	 * @param searchVO
	 * @return
	 */
	public EgovMap selectMechFcltySpecInqireDetail(Map searchVO) throws Exception {
		return (EgovMap)selectByPk("gamMechFcltySpecInqireDao.selectMechFcltySpecInqireDetail_S", searchVO);
	}

	/**
	 * 기계시설제원관리 첨부파일 목록
	 * @param searchVO
	 * @return
	 */
	public List selectMechFcltySpecInqireFileList(GamMechFcltySpecInqireVO searchVO) throws Exception {
		return list("gamMechFcltySpecInqireDao.selectMechFcltySpecInqireFileList_D", searchVO);
	}

	/**
	 * 기계시설제원관리 첨부파일 목록 총수
	 * @param searchVO
	 * @return
	 */
	public int selectMechFcltySpecInqireFileListTotCnt(GamMechFcltySpecInqireVO searchVO) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamMechFcltySpecInqireDao.selectMechFcltySpecInqireFileListTotCnt_S", searchVO);
	}

	
	
}
