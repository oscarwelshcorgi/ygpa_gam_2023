/**
 * 
 */
package egovframework.rte.ygpa.gam.fcltyMng.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyRepairSttusInqireVO;

/**
 * 
 * @author HNJ
 * @since 2014. 12. 9.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 9.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Repository("gamFcltyRepairSttusInqireDao")
public class GamFcltyRepairSttusInqireDao extends YGPAAbstractDAO {
	
	
	/**
	 * 하자보수 내역 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List selectFcltyRepairSttusInqireList(GamFcltyRepairSttusInqireVO vo) throws Exception {
		return list("gamFcltyRepairSttusInqireDao.selectFcltyRepairSttusInqireList_D", vo);
	}
	
	
	/**
	 * 하자보수 내역 총갯수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int selectFcltyRepairSttusInqireListTotCnt(GamFcltyRepairSttusInqireVO vo) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamFcltyRepairSttusInqireDao.selectFcltyRepairSttusInqireListTotCnt_S", vo);
	}
	
	
	/**
	 * 하자보수 대상시설물 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List selectFlawRprSttusObjFcltsFList(GamFcltyRepairSttusInqireVO vo) throws Exception {
		return list("gamFcltyRepairSttusInqireDao.selectFlawRprSttusObjFcltsFList_D", vo);
	}
	
	
	/**
	 * 하자보수 대상시설물 총갯수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int selectFlawRprSttusObjFcltsFListTotCnt(GamFcltyRepairSttusInqireVO vo) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamFcltyRepairSttusInqireDao.selectFlawRprSttusObjFcltsFListTotCnt_S", vo);
	}
	
	
	
	/**
	 * 하자보수 검사자 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List selectFlawExamUsrSttusFList(GamFcltyRepairSttusInqireVO vo) throws Exception {
		return list("gamFcltyRepairSttusInqireDao.selectFlawExamUsrSttusFList_D", vo);
	}
	
	
	/**
	 * 하자보수 검사자 총갯수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int selectFlawExamUsrSttusFListTotCnt(GamFcltyRepairSttusInqireVO vo) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamFcltyRepairSttusInqireDao.selectFlawExamUsrSttusFListTotCnt_S", vo);
	}
	
	
	/**
	 * 하자보수 첨부파일 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List selectFcltyRepairSttusFileList(GamFcltyRepairSttusInqireVO vo) throws Exception {
		return list("gamFcltyRepairSttusInqireDao.selectFcltyRepairSttusFileList_D", vo);
	}
	
	
	/**
	 * 하자보수 첨부파일 총갯수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int selectFcltyRepairSttusFileListTotCnt(GamFcltyRepairSttusInqireVO vo) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamFcltyRepairSttusInqireDao.selectFcltyRepairSttusFileListTotCnt_S", vo);
	}
	

}
