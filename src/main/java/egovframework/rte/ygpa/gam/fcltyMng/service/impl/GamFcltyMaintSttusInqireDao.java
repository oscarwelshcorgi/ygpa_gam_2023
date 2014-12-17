/**
 * 
 */
package egovframework.rte.ygpa.gam.fcltyMng.service.impl;

import java.util.List;
import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyMaintSttusInqireVO;

/**
 * 
 * @author HNJ
 * @since 2014. 12. 8.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 8.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Repository("gamFcltyMaintSttusInqireDao")
public class GamFcltyMaintSttusInqireDao extends YGPAAbstractDAO {
	
	
	/**
	 * 유지보수 내역 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List selectFcltyMaintSttusInqireList(GamFcltyMaintSttusInqireVO vo) throws Exception {
		return list("gamFcltyMaintSttusInqireDao.selectFcltyMaintSttusInqireList_D", vo);
	}
	
	
	/**
	 * 유지보수 내역 총갯수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int selectFcltyMaintSttusInqireListTotCnt(GamFcltyMaintSttusInqireVO vo) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamFcltyMaintSttusInqireDao.selectFcltyMaintSttusInqireListTotCnt_S", vo);
	}
	
	
	/**
	 * 유지보수상세내역 조회
	 * @param searchVO
	 * @return map
	 * @throws Exception
	 */
	public EgovMap selectFcltyMaintSttusInqireDetail(GamFcltyMaintSttusInqireVO vo) throws Exception{
		return (EgovMap) selectByPk("gamFcltyMaintSttusInqireDao.selectFcltyMaintSttusInqireDetail_S", vo);
	}
	
	
	/**
	 * 유지보수 대상시설물 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List selectMntnSttusRprObjFcltsFList(GamFcltyMaintSttusInqireVO vo) throws Exception {
		return list("gamFcltyMaintSttusInqireDao.selectMntnSttusRprObjFcltsFList_D", vo);
	}
	
	
	/**
	 * 유지보수 대상시설물 총갯수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int selectMntnSttusRprObjFcltsFListTotCnt(GamFcltyMaintSttusInqireVO vo) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamFcltyMaintSttusInqireDao.selectMntnSttusRprObjFcltsFListTotCnt_S", vo);
	}
	
	
	/**
	 * 유지보수 첨부파일 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List selectFcltyMaintSttusFileList(GamFcltyMaintSttusInqireVO vo) throws Exception {
		return list("gamFcltyMaintSttusInqireDao.selectFcltyMaintSttusFileList_D", vo);
	}
	
	
	/**
	 * 유지보수 첨부파일 총갯수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int selectFcltyMaintSttusFileListTotCnt(GamFcltyMaintSttusInqireVO vo) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamFcltyMaintSttusInqireDao.selectFcltyMaintSttusFileListTotCnt_S", vo);
	}
	
	

}
