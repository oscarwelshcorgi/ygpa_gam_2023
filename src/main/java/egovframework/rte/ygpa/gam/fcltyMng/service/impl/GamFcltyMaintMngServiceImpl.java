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
import egovframework.rte.ygpa.gam.fclty.service.GamAtchFileDirMngVO;
import egovframework.rte.ygpa.gam.fclty.service.GamFcltsAtchFileMngVO;
import egovframework.rte.ygpa.gam.fclty.service.GamMntnRprDtlsVO;
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
	public List<?> selectFcltyMaintMngList(GamFcltyMaintMngVO vo) throws Exception {
		return (List<?>)gamFcltyMaintMngDao.selectFcltyMaintMngList(vo);
	}


	/**
	 * 유지보수 내역 총갯수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public GamFcltyMaintMngVO selectFcltyMaintMngListTotCnt(GamFcltyMaintMngVO vo) throws Exception {
		return gamFcltyMaintMngDao.selectFcltyMaintMngListTotCnt(vo);
	}


	/**
	 * 유지보수상세내역 조회
	 * @param searchVO
	 * @return map
	 * @throws Exception
	 */
	public EgovMap selectFcltyMaintMngDetail(GamFcltyMaintMngVO vo) throws Exception{
		return gamFcltyMaintMngDao.selectFcltyMaintMngDetail(vo);
	}


	/**
	 * 유지보수 대상시설물 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List<?> selectMntnRprObjFcltsFList(GamFcltyMaintMngVO vo) throws Exception {
		return (List<?>)gamFcltyMaintMngDao.selectMntnRprObjFcltsFList(vo);
	}


	/**
	 * 유지보수 첨부파일 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List<?> selectFcltyMaintFileList(GamFcltyMaintMngVO vo) throws Exception {
		return (List<?>)gamFcltyMaintMngDao.selectFcltyMaintFileList(vo);
	}


	/**
	 * 유지보수내역 입력
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void insertFcltyMaintMng(Map insertMntnData, List insertObjList, List insertFileList) throws Exception{

		String fcltsMngGroupNo = (String) insertMntnData.get("fcltsMngGroupNo");
		String fcltsJobSe = (String) insertMntnData.get("fcltsJobSe");
		int mntnRprSeq = gamFcltyMaintMngDao.selectNextMntnRprSeq(insertMntnData);
		Map insertObj = null;
		Map insertFile = null;

		insertMntnData.put("mntnRprSeq",mntnRprSeq);
		gamFcltyMaintMngDao.insertFcltyMaintMng(insertMntnData);

		// 유지보수 대상시설물 입력처리
		for(int i=0;i<insertObjList.size();i++){
			insertObj = (Map) insertObjList.get(i);
			insertObj.put("fcltsMngGroupNo",fcltsMngGroupNo);
			insertObj.put("fcltsJobSe",fcltsJobSe);
			insertObj.put("mntnRprSeq",mntnRprSeq);
			insertObj.put("regUsr", insertMntnData.get("regUsr"));
			gamFcltyMaintMngDao.insertMntnRprObjFcltsF(insertObj);
		}

		// 유지보수 첨부파일 입력처리
		for(int i=0;i<insertFileList.size();i++){
			insertFile = (Map) insertFileList.get(i);
			insertFile.put("fcltsMngGroupNo",fcltsMngGroupNo);
			insertFile.put("fcltsJobSe",fcltsJobSe);
			insertFile.put("mntnRprSeq",mntnRprSeq);
			insertFile.put("regUsr", insertMntnData.get("regUsr"));
			gamFcltyMaintMngDao.insertFcltyMaintFile(insertFile);
		}
	}


	/**
	 * 유지보수내역 수정
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void updateFcltyMaintMng(Map updateMntnData, List insertObjList, List insertFileList) throws Exception{
		String fcltsMngGroupNo = (String) updateMntnData.get("fcltsMngGroupNo");
		String fcltsJobSe = (String) updateMntnData.get("fcltsJobSe");
		String mntnRprSeq = (String) updateMntnData.get("mntnRprSeq");
		Map insertObj = null;
		Map insertFile = null;

		gamFcltyMaintMngDao.updateFcltyMaintMng(updateMntnData);

		// 유지보수 대상시설물 수정처리
		gamFcltyMaintMngDao.deleteMntnRprObjFcltsF(updateMntnData);

		for(int i=0;i<insertObjList.size();i++){
			insertObj = (Map) insertObjList.get(i);
			insertObj.put("fcltsMngGroupNo",fcltsMngGroupNo);
			insertObj.put("fcltsJobSe",fcltsJobSe);
			insertObj.put("mntnRprSeq",mntnRprSeq);
			insertObj.put("regUsr", updateMntnData.get("regUsr"));
			gamFcltyMaintMngDao.insertMntnRprObjFcltsF(insertObj);
		}

		// 유지보수 첨부파일 입력처리
		gamFcltyMaintMngDao.deleteFcltyMaintFile(updateMntnData);

		for(int i=0;i<insertFileList.size();i++){
			insertFile = (Map) insertFileList.get(i);
			insertFile.put("fcltsMngGroupNo",fcltsMngGroupNo);
			insertFile.put("fcltsJobSe",fcltsJobSe);
			insertFile.put("mntnRprSeq",mntnRprSeq);
			insertFile.put("regUsr", updateMntnData.get("regUsr"));
			gamFcltyMaintMngDao.insertFcltyMaintFile(insertFile);
		}
	}


	/**
	 * 유지보수내역 삭제
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public void deleteFcltyMaintMng(Map<?,?> vo) throws Exception{
		gamFcltyMaintMngDao.deleteFcltyMaintMng(vo);
		gamFcltyMaintMngDao.deleteMntnRprObjFcltsF(vo);
		gamFcltyMaintMngDao.deleteFcltyMaintFile(vo);
	}




	@Override
	public List selectMaintMngAtchFileDirList(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {
		return gamFcltyMaintMngDao.selectMaintMngAtchFileDirList(gamAtchFileDirMngVO);
	}

	@Override
	public EgovMap selectMaintMngAtchFileDirPk(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {
		return gamFcltyMaintMngDao.selectMaintMngAtchFileDirPk(gamAtchFileDirMngVO);
	}

	@Override
	public void insertMaintMngAtchFileDir(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {
		String strDepthSort = (String)gamAtchFileDirMngVO.getDepthSort();
		gamFcltyMaintMngDao.insertMaintMngAtchFileDir(gamAtchFileDirMngVO);
		if (!"1".equals(strDepthSort)) {
			gamFcltyMaintMngDao.updateMaintMngInsertAtchFileDirLeafYn(gamAtchFileDirMngVO);
		}
	}

	@Override
	public void updateMaintMngAtchFileDir(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {
		gamFcltyMaintMngDao.updateMaintMngAtchFileDir(gamAtchFileDirMngVO);
	}

	@Override
	public void deleteMaintMngAtchFileDir(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {
		String strDepthSort = (String)gamAtchFileDirMngVO.getDepthSort();
		gamFcltyMaintMngDao.deleteMaintMngAtchFileDirLowerData(gamAtchFileDirMngVO);
		gamFcltyMaintMngDao.deleteMaintMngAtchFileDir(gamAtchFileDirMngVO);
		if (!"1".equals(strDepthSort)) {
			gamFcltyMaintMngDao.updateMaintMngDeleteAtchFileDirLeafYn(gamAtchFileDirMngVO);
		}
		GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO = new GamFcltsAtchFileMngVO();
		gamFcltsAtchFileMngVO.setAtchFileDirNo(gamAtchFileDirMngVO.getDirNo());
		gamFcltyMaintMngDao.deleteMaintMngAllFcltsAtchFile(gamFcltsAtchFileMngVO);
	}

	@Override
	public String selectMaintMngAtchFileDirNewNo(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {
		return gamFcltyMaintMngDao.selectMaintMngAtchFileDirNewNo(gamAtchFileDirMngVO);
	}

	@Override
	public List selectMaintMngAtchFileDirLowerDataCnt(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {
		return gamFcltyMaintMngDao.selectMaintMngAtchFileDirLowerDataCnt(gamAtchFileDirMngVO);
	}


	@Override
	public List selectMaintMngFcltsAtchFileList(GamFcltsAtchFileMngVO searchVO) throws Exception {
		return gamFcltyMaintMngDao.selectMaintMngFcltsAtchFileList(searchVO);
	}

	@Override
	public void insertMaintMngFcltsAtchFile(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception {
		gamFcltyMaintMngDao.insertMaintMngFcltsAtchFile(gamFcltsAtchFileMngVO);
	}

	@Override
	public void updateMaintMngFcltsAtchFile(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception {
		gamFcltyMaintMngDao.updateMaintMngFcltsAtchFile(gamFcltsAtchFileMngVO);
	}

	@Override
	public void deleteMaintMngFcltsAtchFile(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception {
		gamFcltyMaintMngDao.deleteMaintMngFcltsAtchFile(gamFcltsAtchFileMngVO);
	}

	@Override
	public void deleteMaintMngFcltsAtchFileMulti(Map<?, ?> deleteVO) throws Exception {
		gamFcltyMaintMngDao.deleteMaintMngFcltsAtchFileMulti(deleteVO);
	}

	@Override
	public EgovMap selectMaintMngFcltsAtchFilePk(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception {
		return gamFcltyMaintMngDao.selectMaintMngFcltsAtchFilePk(gamFcltsAtchFileMngVO);
	}

	@Override
	public String selectMaintMngFcltsAtchFileNewNo(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception {
		return gamFcltyMaintMngDao.selectMaintMngFcltsAtchFileNewNo(gamFcltsAtchFileMngVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyMaintMngService#selectMaintMngMntnRprDtlsList(egovframework.rte.ygpa.gam.fclty.service.GamMntnRprDtlsVO)
	 */
	@Override
	public List selectMaintMngMntnRprDtlsList(GamMntnRprDtlsVO searchVO) throws Exception {
		return gamFcltyMaintMngDao.selectMaintMngMntnRprDtlsList(searchVO);
	}

}
