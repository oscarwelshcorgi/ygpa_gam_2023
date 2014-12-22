/**
 * 
 */
package egovframework.rte.ygpa.gam.fcltyMng.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyRepairMngVO;

/**
 * 
 * @author HNJ
 * @since 2014. 11. 25.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 11. 25.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Repository("gamFcltyRepairMngDao")
public class GamFcltyRepairMngDao extends YGPAAbstractDAO {
	
	
	/**
	 * 하자보수 내역 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List<?> selectFcltyRepairMngList(GamFcltyRepairMngVO vo) throws Exception {
		return list("gamFcltyRepairMngDao.selectFcltyRepairMngList_D", vo);
	}
	
	
	/**
	 * 하자보수 내역 총갯수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int selectFcltyRepairMngListTotCnt(GamFcltyRepairMngVO vo) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamFcltyRepairMngDao.selectFcltyRepairMngListTotCnt_S", vo);
	}
	
	
	/**
	 * 하자보수상세내역 조회
	 * @param searchVO
	 * @return map
	 * @throws Exception
	 */
	public EgovMap selectFcltyRepairMngDetail(GamFcltyRepairMngVO vo) throws Exception{
		return (EgovMap) selectByPk("gamFcltyRepairMngDao.selectFcltyRepairMngDetail_S", vo);
	}
	
	
	/**
	 * 하자보수 대상시설물 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List<?> selectFlawRprObjFcltsFList(GamFcltyRepairMngVO vo) throws Exception {
		return list("gamFcltyRepairMngDao.selectFlawRprObjFcltsFList_D", vo);
	}
	
	
	/**
	 * 하자보수 대상시설물 총갯수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int selectFlawRprObjFcltsFListTotCnt(GamFcltyRepairMngVO vo) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamFcltyRepairMngDao.selectFlawRprObjFcltsFListTotCnt_S", vo);
	}
	
	
	
	/**
	 * 하자보수 검사자 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List<?> selectFlawExamUsrFList(GamFcltyRepairMngVO vo) throws Exception {
		return list("gamFcltyRepairMngDao.selectFlawExamUsrFList_D", vo);
	}
	
	
	/**
	 * 하자보수 검사자 총갯수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int selectFlawExamUsrFListTotCnt(GamFcltyRepairMngVO vo) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamFcltyRepairMngDao.selectFlawExamUsrFListTotCnt_S", vo);
	}
	
	
	/**
	 * 하자보수 첨부파일 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List<?> selectFcltyRepairFileList(GamFcltyRepairMngVO vo) throws Exception {
		return list("gamFcltyRepairMngDao.selectFcltyRepairFileList_D", vo);
	}
	
	
	/**
	 * 하자보수 첨부파일 총갯수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int selectFcltyRepairFileListTotCnt(GamFcltyRepairMngVO vo) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamFcltyRepairMngDao.selectFcltyRepairFileListTotCnt_S", vo);
	}
	
	
	/**
	 * 하자보수 순번
	 * @param map
	 * @return String
	 * @throws Exception
	 */
	public int selectNextMntnRprSeq(Map<?,?> vo) throws Exception{
		return (Integer) getSqlMapClient().queryForObject("gamFcltyRepairMngDao.selectNextMntnRprSeq_S", vo); 
	}
	
	
	/**
	 * 하자보수내역 입력
	 * @param map
	 * @return 
	 * @throws Exception
	 */
	public void insertFcltyRepairMng(Map<?,?> vo) throws Exception{
		insert("gamFcltyRepairMngDao.insertFcltyRepairMng", vo);
	}
	
	
	/**
	 * 하자보수내역 수정
	 * @param map
	 * @return 
	 * @throws Exception
	 */
	public void updateFcltyRepairMng(Map<?,?> vo) throws Exception{
		update("gamFcltyRepairMngDao.updateFcltyRepairMng", vo);
	}
	
	
	/**
	 * 하자보수내역 삭제
	 * @param map
	 * @return 
	 * @throws Exception
	 */
	public void deleteFcltyRepairMng(Map<?,?> vo) throws Exception{
		delete("gamFcltyRepairMngDao.deleteFcltyRepairMng", vo);
	}
	
	/**
	 * 하자보수내역 하위 대상시설물 전체 삭제
	 * @param map
	 * @return 
	 * @throws Exception
	 */
	public void deleteFlawRprObjFcltsF(Map<?,?> vo) throws Exception{
		delete("gamFcltyRepairMngDao.deleteFlawRprObjFcltsF", vo);
	}
	
	/**
	 * 하자보수내역 하위 검사자 전체 삭제
	 * @param map
	 * @return 
	 * @throws Exception
	 */
	public void deleteFlawExamUsrF(Map<?,?> vo) throws Exception{
		delete("gamFcltyRepairMngDao.deleteFlawExamUsrF", vo);
	}
	
	
	/**
	 * 하자보수내역 하위 첨부파일 전체 삭제
	 * @param map
	 * @return 
	 * @throws Exception
	 */
	public void deleteFcltyRepairFile(Map<?,?> vo) throws Exception{
		delete("gamFcltyRepairMngDao.deleteFcltyRepairFile", vo);
	}
	
	
	/**
	 * 하자보수 대상시설물 데이타 적용
	 * @param map
	 * @return 
	 * @throws Exception
	 */
	public List<?> mergeFlawRprObjFcltsF(Map<String, Object> mergeList) throws Exception{
		return this.merge(mergeList, "gamFcltyRepairMngDao.insertFlawRprObjFcltsF", "gamFcltyRepairMngDao.updateFlawRprObjFcltsF", "gamFcltyRepairMngDao.deleteFlawRprObjFcltsF");
	}
	
	
	
	
	/**
	 * 하자보수 검사자 데이타 적용
	 * @param map
	 * @return 
	 * @throws Exception
	 */
	public List<?> mergeFlawExamUsrF(Map<String, Object> mergeList) throws Exception{
		return this.merge(mergeList, "gamFcltyRepairMngDao.insertFlawExamUsrF", "gamFcltyRepairMngDao.updateFlawExamUsrF", "gamFcltyRepairMngDao.deleteFlawExamUsrF");
	}
	
	
	
	/**
	 * 하자보수 첨부파일 적용
	 * @param map
	 * @return 
	 * @throws Exception
	 */
	public List<?> mergeFcltyRepairFile(Map<String, Object> mergeList) throws Exception{
		return this.merge(mergeList, "gamFcltyRepairMngDao.insertFcltyRepairFile", "gamFcltyRepairMngDao.updateFcltyRepairFile", "gamFcltyRepairMngDao.deleteFcltyRepairFile");
	}

}
