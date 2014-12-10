/**
 *
 */
package egovframework.rte.ygpa.gam.fclty.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.fclty.service.GamInfoTechFcltySpecInqireVO;

/**
 *
 * @author 김종민
 * @since 2014. 11. 17.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 11. 17.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Repository("gamInfoTechFcltySpecInqireDao")
public class GamInfoTechFcltySpecInqireDao extends YGPAAbstractDAO {
	/**
	 * 정보통신시설재원관리 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List selectInfoTechFcltySpecInqireList(GamInfoTechFcltySpecInqireVO searchVO) throws Exception {
		return list("gamInfoTechFcltySpecInqireDao.selectInfoTechFcltySpecInqireList_D", searchVO);
	}

	/**
	 * 정보통신시설재원관리 목록 총수 조회
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int selectInfoTechFcltySpecInqireListTotCnt(GamInfoTechFcltySpecInqireVO searchVO) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamInfoTechFcltySpecInqireDao.selectInfoTechFcltySpecInqireListTotCnt_S", searchVO);
	}


	/**
	 * 정보통신시설재원관리 데이터 조회
	 * @param vo
	 * @return egovMap
	 * @throws Exception
	 */
	public EgovMap selectInfoTechFcltySpecInqireDetail(Map searchVO) throws Exception {
		return (EgovMap)selectByPk("gamInfoTechFcltySpecInqireDao.selectInfoTechFcltySpecInqireDetail_S", searchVO);
	}



	/**
	 * 정보통신시설재원관리 첨부파일 목록을 가져온다.
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List selectInfoTechFcltySpecInqireFileList(GamInfoTechFcltySpecInqireVO searchVO) throws Exception {
		return list("gamInfoTechFcltySpecInqireDao.selectInfoTechFcltySpecInqireFileList_D", searchVO);
	}

	/**
	 * 정보통신시설재원관리 첨부파일 목록 총수를 가져온다.
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public int selectInfoTechFcltySpecInqireFileListTotCnt(GamInfoTechFcltySpecInqireVO searchVO) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamInfoTechFcltySpecInqireDao.selectInfoTechFcltySpecInqireFileListTotCnt_S", searchVO);
	}

}
