/**
 *
 */
package egovframework.rte.ygpa.gam.fclty.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.fclty.service.GamCivilFcltySpecInqireVO;
import egovframework.rte.ygpa.gam.fclty.service.GamCivilFcltySpecMngVO;

/**
 *
 * @author 김종민
 * @since 2014. 11. 10.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 11. 10.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Repository("gamCivilFcltySpecInqireDao")
public class GamCivilFcltySpecInqireDao extends YGPAAbstractDAO {
	/**
	 * 토목시설재원관리 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List selectCivilFcltySpecInqireList(GamCivilFcltySpecInqireVO searchVO) throws Exception {
		return list("gamCivilFcltySpecInqireDao.selectCivilFcltySpecInqireList_D", searchVO);
	}

	/**
	 * 토목시설재원관리 목록 총수 조회
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int selectCivilFcltySpecInqireListTotCnt(GamCivilFcltySpecInqireVO searchVO) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamCivilFcltySpecInqireDao.selectCivilFcltySpecInqireListTotCnt_S", searchVO);
	}


	/**
	 * 토목시설재원관리 데이터 조회
	 * @param vo
	 * @return egovMap
	 * @throws Exception
	 */
	public EgovMap selectCivilFcltySpecInqireDetail(Map searchVO) throws Exception {
		return (EgovMap)selectByPk("gamCivilFcltySpecInqireDao.selectCivilFcltySpecInqireDetail_S", searchVO);
	}


	/**
	 * 토목시설재원관리 첨부파일 목록을 가져온다.
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List selectCivilFcltySpecInqireFileList(GamCivilFcltySpecInqireVO searchVO) throws Exception {
		return list("gamCivilFcltySpecInqireDao.selectCivilFcltySpecInqireFileList_D", searchVO);
	}

	/**
	 * 토목시설재원관리 첨부파일 목록 총수를 가져온다.
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public int selectCivilFcltySpecInqireFileListTotCnt(GamCivilFcltySpecInqireVO searchVO) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamCivilFcltySpecInqireDao.selectCivilFcltySpecInqireFileListTotCnt_S", searchVO);
	}

	/**
	 * @param searchVO
	 * @return
	 */


}
