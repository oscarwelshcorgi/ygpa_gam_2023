/**
 *
 */
package egovframework.rte.ygpa.gam.fclty.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.fclty.service.GamElctyFcltySpecInqireVO;

/**
*
* @author 정성현
* @since 2014. 12. 9.
* @version 1.0
* @see
* <pre>
* << 개정이력(Modification Information) >>
*
*   수정일 		 수정자		 수정내용
*  -------		   --------	---------------------------
*  2014. 12. 9.	정성현		최초 생성
*
* Copyright (C) 2013 by LFIT  All right reserved.
* </pre>
*/

@Repository("gamElctyFcltySpecInqireDao")
public class GamElctyFcltySpecInqireDao extends YGPAAbstractDAO {
	/**
	 * 전기시설재원관리 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List selectElctyFcltySpecInqireList(GamElctyFcltySpecInqireVO searchVO) throws Exception {
		return list("gamElctyFcltySpecInqireDao.selectElctyFcltySpecInqireList", searchVO);
	}

	/**
	 * 전기시설재원관리 목록 총수 조회
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int selectElctyFcltySpecInqireListTotCnt(GamElctyFcltySpecInqireVO searchVO) throws Exception {
		return (Integer) getSqlMapClientTemplate().queryForObject("gamElctyFcltySpecInqireDao.selectElctyFcltySpecInqireListTotCnt", searchVO);
	}

	/**
	 * 전기시설재원관리 데이터 조회
	 * @param vo
	 * @return egovMap
	 * @throws Exception
	 */
	public EgovMap selectElctyFcltySpecInqireDetail(Map searchVO) throws Exception {
		return (EgovMap) selectByPk("gamElctyFcltySpecInqireDao.selectElctyFcltySpecInqireDetail", searchVO);
	}

	/**
	 * 전기시설재원관리 첨부파일 목록을 가져온다.
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List selectElctyFcltySpecInqireFileList(GamElctyFcltySpecInqireVO searchVO) throws Exception {
		return list("gamElctyFcltySpecInqireDao.selectElctyFcltySpecInqireFileList", searchVO);
	}

	/**
	 * 전기시설재원관리 첨부파일 목록 총수를 가져온다.
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public int selectElctyFcltySpecInqireFileListTotCnt(GamElctyFcltySpecInqireVO searchVO) throws Exception {
		return (Integer) getSqlMapClientTemplate().queryForObject("gamElctyFcltySpecInqireDao.selectElctyFcltySpecInqireFileListTotCnt", searchVO);
	}



}
