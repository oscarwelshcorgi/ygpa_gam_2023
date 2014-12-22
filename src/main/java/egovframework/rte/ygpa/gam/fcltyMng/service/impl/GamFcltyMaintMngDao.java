/**
 * 
 */
package egovframework.rte.ygpa.gam.fcltyMng.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyMaintMngVO;

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

@Repository("gamFcltyMaintMngDao")
public class GamFcltyMaintMngDao extends YGPAAbstractDAO {
	
	
	/**
	 * 유지보수 내역 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List<?> selectFcltyMaintMngList(GamFcltyMaintMngVO vo) throws Exception {
		return list("gamFcltyMaintMngDao.selectFcltyMaintMngList_D", vo);
	}
	
	
	/**
	 * 유지보수 내역 총갯수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int selectFcltyMaintMngListTotCnt(GamFcltyMaintMngVO vo) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamFcltyMaintMngDao.selectFcltyMaintMngListTotCnt_S", vo);
	}
	
	
	/**
	 * 유지보수상세내역 조회
	 * @param searchVO
	 * @return map
	 * @throws Exception
	 */
	public EgovMap selectFcltyMaintMngDetail(GamFcltyMaintMngVO vo) throws Exception{
		return (EgovMap) selectByPk("gamFcltyMaintMngDao.selectFcltyMaintMngDetail_S", vo);
	}
	
	
	/**
	 * 유지보수 대상시설물 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List<?> selectMntnRprObjFcltsFList(GamFcltyMaintMngVO vo) throws Exception {
		return list("gamFcltyMaintMngDao.selectMntnRprObjFcltsFList_D", vo);
	}
	
	
	/**
	 * 유지보수 대상시설물 총갯수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int selectMntnRprObjFcltsFListTotCnt(GamFcltyMaintMngVO vo) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamFcltyMaintMngDao.selectMntnRprObjFcltsFListTotCnt_S", vo);
	}
	
	
	/**
	 * 유지보수 첨부파일 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List<?> selectFcltyMaintFileList(GamFcltyMaintMngVO vo) throws Exception {
		return list("gamFcltyMaintMngDao.selectFcltyMaintFileList_D", vo);
	}
	
	
	/**
	 * 유지보수 첨부파일 총갯수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int selectFcltyMaintFileListTotCnt(GamFcltyMaintMngVO vo) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamFcltyMaintMngDao.selectFcltyMaintFileListTotCnt_S", vo);
	}
	
	
	/**
	 * 유지보수 순번
	 * @param map
	 * @return String
	 * @throws Exception
	 */
	public int selectNextMntnRprSeq(Map<?,?> vo) throws Exception{
		return (Integer) getSqlMapClient().queryForObject("gamFcltyMaintMngDao.selectNextMntnRprSeq_S", vo); 
	}
	
	
	/**
	 * 유지보수내역 입력
	 * @param map
	 * @return 
	 * @throws Exception
	 */
	public void insertFcltyMaintMng(Map<?,?> vo) throws Exception{
		insert("gamFcltyMaintMngDao.insertFcltyMaintMng", vo);
	}
	
	
	/**
	 * 유지보수내역 수정
	 * @param map
	 * @return 
	 * @throws Exception
	 */
	public void updateFcltyMaintMng(Map<?,?> vo) throws Exception{
		insert("gamFcltyMaintMngDao.updateFcltyMaintMng", vo);
	}
	
	
	/**
	 * 유지보수내역 삭제
	 * @param map
	 * @return 
	 * @throws Exception
	 */
	public void deleteFcltyMaintMng(Map<?,?> vo) throws Exception{
		insert("gamFcltyMaintMngDao.deleteFcltyMaintMng", vo);
	}
	
	/**
	 * 유지보수내역 하위 대상시설물 전체 삭제
	 * @param map
	 * @return 
	 * @throws Exception
	 */
	public void deleteMntnRprObjFcltsF(Map<?,?> vo) throws Exception{
		delete("gamFcltyMaintMngDao.deleteMntnRprObjFcltsF", vo);
	}
	
	
	/**
	 * 유지보수내역 하위 첨부파일 전체 삭제
	 * @param map
	 * @return 
	 * @throws Exception
	 */
	public void deleteFcltyMaintFile(Map<?,?> vo) throws Exception{
		delete("gamFcltyMaintMngDao.deleteFcltyMaintFile", vo);
	}
	
	
	
	
	/**
	 * 유지보수 대상시설물 데이타 적용
	 * @param map
	 * @return 
	 * @throws Exception
	 */
	public List<?> mergeMntnRprObjFcltsF(Map<String, Object> mergeList) throws Exception{
		return this.merge(mergeList, "gamFcltyMaintMngDao.insertMntnRprObjFcltsF", "gamFcltyMaintMngDao.updateMntnRprObjFcltsF", "gamFcltyMaintMngDao.deleteMntnRprObjFcltsF");
	}
	
	
	
	/**
	 * 유지보수 첨부파일 적용
	 * @param map
	 * @return 
	 * @throws Exception
	 */
	public List<?> mergeFcltyMaintFile(Map<String, Object> mergeList) throws Exception{
		return this.merge(mergeList, "gamFcltyMaintMngDao.insertFcltyMaintFile", "gamFcltyMaintMngDao.updateFcltyMaintFile", "gamFcltyMaintMngDao.deleteFcltyMaintFile");
	}

}
