/**
 * 
 */
package egovframework.rte.ygpa.gam.fcltyMng.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyMaintMngService;
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

@Service("gamFcltyMaintMngService")
public class GamFcltyMaintMngServiceImpl extends AbstractServiceImpl implements GamFcltyMaintMngService {
	
	@Resource(name="gamFcltyMaintMngDao")
	private GamFcltyMaintMngDao gamFcltyMaintMngDao;

	/**
	 * 유지보수 내역 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List selectFcltyMaintMngList(GamFcltyMaintMngVO vo) throws Exception {
		return (List)gamFcltyMaintMngDao.selectFcltyMaintMngList(vo);
	}

	
	/**
	 * 유지보수 내역 총갯수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int selectFcltyMaintMngListTotCnt(GamFcltyMaintMngVO vo) throws Exception {
		return gamFcltyMaintMngDao.selectFcltyMaintMngListTotCnt(vo);
	}
	
	
	/**
	 * 유지보수 대상시설물 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List selectMntnRprObjFcltsFList(GamFcltyMaintMngVO vo) throws Exception {
		return (List)gamFcltyMaintMngDao.selectMntnRprObjFcltsFList(vo);
	}
	
	
	/**
	 * 유지보수 대상시설물 총갯수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int selectMntnRprObjFcltsFListTotCnt(GamFcltyMaintMngVO vo) throws Exception {
		return gamFcltyMaintMngDao.selectMntnRprObjFcltsFListTotCnt(vo);
	}
	
	
	/**
	 * 유지보수 첨부파일 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List selectFcltyMaintFileList(GamFcltyMaintMngVO vo) throws Exception {
		return (List)gamFcltyMaintMngDao.selectFcltyMaintFileList(vo);
	}
	
	
	/**
	 * 유지보수 첨부파일 총갯수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int selectFcltyMaintFileListTotCnt(GamFcltyMaintMngVO vo) throws Exception {
		return gamFcltyMaintMngDao.selectFcltyMaintFileListTotCnt(vo);
	}
	
	
	/**
	 * 유지보수 순번
	 * @param map
	 * @return String
	 * @throws Exception
	 */
	public int selectNextMntnRprSeq(Map<String, Object> vo) throws Exception{
		return gamFcltyMaintMngDao.selectNextMntnRprSeq(vo);
	}
	
	
	/**
	 * 유지보수내역 입력
	 * @param map
	 * @return 
	 * @throws Exception
	 */
	public void insertFcltyMaintMng(Map<String, Object> vo) throws Exception{
		gamFcltyMaintMngDao.insertFcltyMaintMng(vo);
	}
	
	
	/**
	 * 유지보수내역 수정
	 * @param map
	 * @return 
	 * @throws Exception
	 */
	public void updateFcltyMaintMng(Map<String, Object> vo) throws Exception{
		gamFcltyMaintMngDao.updateFcltyMaintMng(vo);
	}
	
	
	/**
	 * 유지보수내역 삭제
	 * @param map
	 * @return 
	 * @throws Exception
	 */
	public void deleteFcltyMaintMng(Map<String, Object> vo) throws Exception{
		gamFcltyMaintMngDao.deleteFcltyMaintMng(vo);
	}
	
	
	/**
	 * 유지보수내역 하위 대상시설물 전체 삭제
	 * @param map
	 * @return 
	 * @throws Exception
	 */
	public void deleteMntnRprObjFcltsF(Map<String, Object> vo) throws Exception{
		gamFcltyMaintMngDao.deleteMntnRprObjFcltsF(vo);
	}
	
	
	/**
	 * 유지보수내역 하위 첨부파일 전체 삭제
	 * @param map
	 * @return 
	 * @throws Exception
	 */
	public void deleteFcltyMaintFile(Map<String, Object> vo) throws Exception{
		gamFcltyMaintMngDao.deleteFcltyMaintFile(vo);
	}
	
	
	/**
	 * 유지보수 대상시설물 데이타 적용
	 * @param map
	 * @return 
	 * @throws Exception
	 */
	public List mergeMntnRprObjFcltsF(Map<String, Object> mergeList) throws Exception{
		return (List)gamFcltyMaintMngDao.mergeMntnRprObjFcltsF(mergeList);
	}
	
	
	
	/**
	 * 유지보수 첨부파일 적용
	 * @param map
	 * @return 
	 * @throws Exception
	 */
	public List mergeFcltyMaintFile(Map<String, Object> mergeList) throws Exception{
		return (List)gamFcltyMaintMngDao.mergeFcltyMaintFile(mergeList);
	}
	


}
