/**
 * 
 */
package egovframework.rte.ygpa.gam.fcltyMng.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
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
	public List<?> selectFcltyRepairMngList(GamFcltyRepairMngVO vo) throws Exception {
		return (List<?>)gamFcltyRepairMngDao.selectFcltyRepairMngList(vo);
	}

	
	/**
	 * 하자보수 내역 총갯수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public GamFcltyRepairMngVO selectFcltyRepairMngListTotCnt(GamFcltyRepairMngVO vo) throws Exception {
		return gamFcltyRepairMngDao.selectFcltyRepairMngListTotCnt(vo);
	}
	
	
	/**
	 * 하자보수상세내역 조회
	 * @param searchVO
	 * @return map
	 * @throws Exception
	 */
	public EgovMap selectFcltyRepairMngDetail(GamFcltyRepairMngVO vo) throws Exception{
		return gamFcltyRepairMngDao.selectFcltyRepairMngDetail(vo);
	}
	
	
	
	/**
	 * 하자보수 대상시설물 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List<?> selectFlawRprObjFcltsFList(GamFcltyRepairMngVO vo) throws Exception {
		return (List<?>)gamFcltyRepairMngDao.selectFlawRprObjFcltsFList(vo);
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
	 * 하자보수 첨부파일 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List<?> selectFcltyRepairFileList(GamFcltyRepairMngVO vo) throws Exception {
		return (List<?>)gamFcltyRepairMngDao.selectFcltyRepairFileList(vo);
	}
	
	
	/**
	 * 하자보수내역 입력
	 * @param map
	 * @return 
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void insertFcltyRepairMng(Map insertRprData, List insertObjList, List insertFileList) throws Exception{
		String fcltsMngGroupNo = (String) insertRprData.get("fcltsMngGroupNo");
		String fcltsJobSe = (String) insertRprData.get("fcltsJobSe");
		int flawRprSeq = gamFcltyRepairMngDao.selectNextMntnRprSeq(insertRprData);
		Map insertObj = null;
		Map insertFile = null;
		
		insertRprData.put("flawRprSeq",flawRprSeq);
		gamFcltyRepairMngDao.insertFcltyRepairMng(insertRprData);
		
		// 하자보수 대상시설물 입력처리
		for(int i=0;i<insertObjList.size();i++){
			insertObj = (Map) insertObjList.get(i);
			insertObj.put("fcltsMngGroupNo",fcltsMngGroupNo);
			insertObj.put("fcltsJobSe",fcltsJobSe);
			insertObj.put("flawRprSeq",flawRprSeq);
			insertObj.put("regUsr", insertRprData.get("regUsr"));
			gamFcltyRepairMngDao.insertFlawRprObjFcltsF(insertObj);
		}

		// 하자보수 첨부파일 입력처리
		for(int i=0;i<insertFileList.size();i++){
			insertFile = (Map) insertFileList.get(i);
			insertFile.put("fcltsMngGroupNo",fcltsMngGroupNo);
			insertFile.put("fcltsJobSe",fcltsJobSe);
			insertFile.put("flawRprSeq",flawRprSeq);
			insertFile.put("regUsr", insertRprData.get("regUsr"));
			gamFcltyRepairMngDao.insertFcltyRepairFile(insertFile);
		}
		
	}
	
	
	/**
	 * 하자보수내역 수정
	 * @param map
	 * @return 
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void updateFcltyRepairMng(Map updateRprData, Map updateObj, List updateFileList) throws Exception{
		String fcltsMngGroupNo = (String) updateRprData.get("fcltsMngGroupNo");
		String fcltsJobSe = (String) updateRprData.get("fcltsJobSe");
		String flawRprSeq = (String) updateRprData.get("flawRprSeq");

		Map insertFile = null;
		
		gamFcltyRepairMngDao.updateFcltyRepairMng(updateRprData);
		
		// 하자보수 대상시설물 입력처리
		gamFcltyRepairMngDao.mergeFlawRprObjFcltsF(updateObj);
		
		
		// 하자보수 첨부파일 입력처리
		gamFcltyRepairMngDao.deleteFcltyRepairFile(updateRprData);
		
		for(int i=0;i<updateFileList.size();i++){
			insertFile = (Map) updateFileList.get(i);
			insertFile.put("fcltsMngGroupNo",fcltsMngGroupNo);
			insertFile.put("fcltsJobSe",fcltsJobSe);
			insertFile.put("flawRprSeq",flawRprSeq);
			insertFile.put("regUsr", updateRprData.get("regUsr"));
			gamFcltyRepairMngDao.insertFcltyRepairFile(insertFile);
		}
	}
	
	
	/**
	 * 하자보수내역 삭제
	 * @param map
	 * @return 
	 * @throws Exception
	 */
	public void deleteFcltyRepairMng(Map<?,?> vo) throws Exception{
		gamFcltyRepairMngDao.deleteFcltyRepairMng(vo);
		gamFcltyRepairMngDao.deleteFlawRprObjFcltsF(vo);
		gamFcltyRepairMngDao.deleteFcltyRepairFile(vo);
	}

	
	/**
	 * 하자검사조서인쇄
	 * @param map
	 * @return 
	 * @throws Exception
	 */
	public EgovMap selectFcltyRepairCheckReport(GamFcltyRepairMngVO vo) throws Exception{
		return (EgovMap)gamFcltyRepairMngDao.selectFcltyRepairCheckReport(vo);
	}
	
	
	/**
	 * 하자검사관리대장인쇄
	 * @param map
	 * @return 
	 * @throws Exception
	 */
	public EgovMap selectFcltyRepairCheckMng(GamFcltyRepairMngVO vo) throws Exception{
		return (EgovMap)gamFcltyRepairMngDao.selectFcltyRepairCheckMng(vo);
	}
	
	
	/**
	 *  계약당 하자보증 내역 인쇄
	 * @param  String
	 * @return list
	 * @throws Exception
	 */
	public List<?> selectFcltyRepairMngListPerCtrt(String vo) throws Exception {
		return (List<?>)gamFcltyRepairMngDao.selectFcltyRepairMngListPerCtrt(vo);
	}
	
	
	/**
	 * 계약당 계약당 하자보증 내역 총갯수
	 * @param String
	 * @return int
	 * @throws Exception
	 */
	public int selectFcltyRepairMngListPerCtrtTotalCnt(String vo) throws Exception {
		return gamFcltyRepairMngDao.selectFcltyRepairMngListPerCtrtTotalCnt(vo);
	}


	
	public List selectFcltyRepairCheckReportImgList(GamFcltyRepairMngVO vo) throws Exception {
		// TODO Auto-generated method stub
		return gamFcltyRepairMngDao.selectFcltyRepairCheckReportImgList_D(vo);
	}
	public EgovMap selectFcltyRepairCheckReportCharger(GamFcltyRepairMngVO GamFcltyRepairMngVO) throws Exception{
		return (EgovMap)gamFcltyRepairMngDao.selectFcltyRepairCheckReportCharger(GamFcltyRepairMngVO);
	}

}
