/**
 * 
 */
package egovframework.rte.ygpa.gam.fcltyMng.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyRepairMngService;
import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyRepairMngVO;

/**
 * 
 * @author HNJ
 * @since 2014. 12. 01.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 01.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Service("gamFcltyRepairMngService")
public class GamFcltyRepairMngServiceImpl extends AbstractServiceImpl implements GamFcltyRepairMngService {
	
	@Resource(name="gamFcltyRepairMngDao")
	private GamFcltyRepairMngDao gamFcltyRepairMngDao;

	/**
	 * 하자보수 내역 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List selectFcltyRepairMngList(GamFcltyRepairMngVO vo) throws Exception {
		return (List)gamFcltyRepairMngDao.selectFcltyRepairMngList(vo);
	}

	
	/**
	 * 하자보수 내역 총갯수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int selectFcltyRepairMngListTotCnt(GamFcltyRepairMngVO vo) throws Exception {
		return gamFcltyRepairMngDao.selectFcltyRepairMngListTotCnt(vo);
	}
	
	
	/**
	 * 하자보수 대상시설물 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List selectFlawRprObjFcltsFList(GamFcltyRepairMngVO vo) throws Exception {
		return (List)gamFcltyRepairMngDao.selectFlawRprObjFcltsFList(vo);
	}
	
	
	/**
	 * 하자보수 대상시설물 총갯수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int selectFlawRprObjFcltsFListTotCnt(GamFcltyRepairMngVO vo) throws Exception {
		return gamFcltyRepairMngDao.selectFlawRprObjFcltsFListTotCnt(vo);
	}
	
	
	
	/**
	 * 하자보수 검사자 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List selectFlawExamUsrFList(GamFcltyRepairMngVO vo) throws Exception {
		return (List)gamFcltyRepairMngDao.selectFlawExamUsrFList(vo);
	}
	
	
	/**
	 * 하자보수 검사자 총갯수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int selectFlawExamUsrFListTotCnt(GamFcltyRepairMngVO vo) throws Exception {
		return gamFcltyRepairMngDao.selectFlawExamUsrFListTotCnt(vo);
	}
	
	
	/**
	 * 하자보수 첨부파일 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List selectFcltyRepairFileList(GamFcltyRepairMngVO vo) throws Exception {
		return (List)gamFcltyRepairMngDao.selectFcltyRepairFileList(vo);
	}
	
	
	/**
	 * 하자보수 첨부파일 총갯수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int selectFcltyRepairFileListTotCnt(GamFcltyRepairMngVO vo) throws Exception {
		return gamFcltyRepairMngDao.selectFcltyRepairFileListTotCnt(vo);
	}
	
	
	/**
	 * 하자보수 순번
	 * @param map
	 * @return String
	 * @throws Exception
	 */
	public int selectNextMntnRprSeq(Map<String, Object> vo) throws Exception{
		return gamFcltyRepairMngDao.selectNextMntnRprSeq(vo);
	}
	
	
	/**
	 * 하자보수내역 입력
	 * @param map
	 * @return 
	 * @throws Exception
	 */
	public void insertFcltyRepairMng(Map<String, Object> vo) throws Exception{
		gamFcltyRepairMngDao.insertFcltyRepairMng(vo);
	}
	
	
	/**
	 * 하자보수내역 수정
	 * @param map
	 * @return 
	 * @throws Exception
	 */
	public void updateFcltyRepairMng(Map<String, Object> vo) throws Exception{
		gamFcltyRepairMngDao.updateFcltyRepairMng(vo);
	}
	
	
	/**
	 * 하자보수내역 삭제
	 * @param map
	 * @return 
	 * @throws Exception
	 */
	public void deleteFcltyRepairMng(Map<String, Object> vo) throws Exception{
		gamFcltyRepairMngDao.deleteFcltyRepairMng(vo);
	}
	
	
	/**
	 * 하자보수내역 하위 대상시설물 전체 삭제
	 * @param map
	 * @return 
	 * @throws Exception
	 */
	public void deleteFlawRprObjFcltsF(Map<String, Object> vo) throws Exception{
		gamFcltyRepairMngDao.deleteFlawRprObjFcltsF(vo);
	}
	
	
	/**
	 * 하자보수내역 하위 검사자 전체 삭제
	 * @param map
	 * @return 
	 * @throws Exception
	 */
	public void deleteFlawExamUsrF(Map<String, Object> vo) throws Exception{
		gamFcltyRepairMngDao.deleteFlawExamUsrF(vo);
	}
	
	
	/**
	 * 하자보수내역 하위 첨부파일 전체 삭제
	 * @param map
	 * @return 
	 * @throws Exception
	 */
	public void deleteFcltyRepairFile(Map<String, Object> vo) throws Exception{
		gamFcltyRepairMngDao.deleteFcltyRepairFile(vo);
	}
	
	
	/**
	 * 하자보수 대상시설물 데이타 적용
	 * @param map
	 * @return 
	 * @throws Exception
	 */
	public List mergeFlawRprObjFcltsF(Map<String, Object> mergeList) throws Exception{
		return (List)gamFcltyRepairMngDao.mergeFlawRprObjFcltsF(mergeList);
	}
	
	
	/**
	 * 하자보수 검사자 데이타 적용
	 * @param map
	 * @return 
	 * @throws Exception
	 */
	public List mergeFlawExamUsrF(Map<String, Object> mergeList) throws Exception{
		return (List)gamFcltyRepairMngDao.mergeFlawExamUsrF(mergeList);
	}
	
	
	
	/**
	 * 하자보수 첨부파일 적용
	 * @param map
	 * @return 
	 * @throws Exception
	 */
	public List mergeFcltyRepairFile(Map<String, Object> mergeList) throws Exception{
		return (List)gamFcltyRepairMngDao.mergeFcltyRepairFile(mergeList);
	}
	


}
