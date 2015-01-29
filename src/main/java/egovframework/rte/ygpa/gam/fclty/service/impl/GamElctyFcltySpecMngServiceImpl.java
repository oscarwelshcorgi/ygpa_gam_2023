/**
 * 
 */
package egovframework.rte.ygpa.gam.fclty.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.cmmn.fclty.service.impl.GamGisPrtFcltyCdMngtDao;
import egovframework.rte.ygpa.gam.fclty.service.GamElctyFcltySpecMngService;
import egovframework.rte.ygpa.gam.fclty.service.GamElctyFcltySpecMngVO;

/**
 * 
 * @author HNJ
 * @since 2014. 11. 19.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 11. 19.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Service("gamElctyFcltySpecMngService")
public class GamElctyFcltySpecMngServiceImpl extends AbstractServiceImpl implements GamElctyFcltySpecMngService{
	
	@Resource(name="gamElctyFcltySpecMngDao")
	private GamElctyFcltySpecMngDao gamElctyFcltySpecMngDao;
	
	@Resource(name="gamGisPrtFcltyCdMngtDao")
	GamGisPrtFcltyCdMngtDao gamGisPrtFcltyCdMngtDao;
	
	/**
	 * 전기시설재원관리 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */	
	public List<?> selectElctyFcltySpecMngList(GamElctyFcltySpecMngVO searchVO) throws Exception {
		return gamElctyFcltySpecMngDao.selectElctyFcltySpecMngList(searchVO);
	}
	
	/**
	 * 전기시설재원관리 목록 총수 조회
	 * @param vo
	 * @return int
	 * @throws Exception
	 */		
	public int selectElctyFcltySpecMngListTotCnt(GamElctyFcltySpecMngVO searchVO) throws Exception {
		return gamElctyFcltySpecMngDao.selectElctyFcltySpecMngListTotCnt(searchVO);
	}

	/**
	 * 전기시설재원관리 데이터 조회
	 * @param vo
	 * @return egovMap
	 * @throws Exception
	 */		
	public EgovMap selectElctyFcltySpecMngDetail(Map<?,?> searchVO) throws Exception {
		return gamElctyFcltySpecMngDao.selectElctyFcltySpecMngDetail(searchVO);
	}
	
	/**
	 * 시설재원관리 입력
	 * @param Map, List
	 * @return 
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void insertElctyFcltySpecMngDetail(Map fcltyManageVO, List insertFileList) throws Exception {
		
		Map insertFile = null;

		String gisAssetsPrtAtCode = (String) fcltyManageVO.get("gisAssetsPrtAtCode");
		String gisAssetsCd = (String) fcltyManageVO.get("gisAssetsCd");
		String gisAssetsSubCd = (String) fcltyManageVO.get("gisAssetsSubCd");
		String gisPrtFcltyCd = (String) fcltyManageVO.get("gisPrtFcltyCd");
		String gisPrtFcltySeq = gamGisPrtFcltyCdMngtDao.selectNextFcltySeq(fcltyManageVO);
		String prtFcltySe = (String) fcltyManageVO.get("prtFcltySe");
		String fcltsMngNo = gisAssetsPrtAtCode + gisAssetsCd + gisAssetsSubCd + gisPrtFcltyCd + gisPrtFcltySeq + prtFcltySe;
		
		fcltyManageVO.put("fcltsMngNo", fcltsMngNo);
		fcltyManageVO.put("gisPrtFcltySeq", gisPrtFcltySeq);
		
		gamElctyFcltySpecMngDao.insertElctyFcltySpecMngDetail(fcltyManageVO);
		gamGisPrtFcltyCdMngtDao.insertGisPrtFclty(fcltyManageVO);
		
		//  시설 첨부파일 입력처리
		for(int i=0;i<insertFileList.size();i++){
			insertFile = (Map) insertFileList.get(i);
			insertFile.put("fcltsMngNo",fcltsMngNo);
			insertFile.put("regUsr", fcltyManageVO.get("regUsr"));
			gamElctyFcltySpecMngDao.insertElctyFcltySpecFileDetail(insertFile);
		}
		
	}
	
	/**
	 * 시설재원관리 수정
	 * @param Map, Map
	 * @return 
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void updateElctyFcltySpecMngDetail(Map fcltyManageVO, Map mergeMap) throws Exception {
		
		gamElctyFcltySpecMngDao.updateElctyFcltySpecMngDetail(fcltyManageVO);
		gamGisPrtFcltyCdMngtDao.updateGisPrtFclty(fcltyManageVO);
		
		gamElctyFcltySpecMngDao.mergeFcltyFile(mergeMap);
	}
	
	/**
	 * 전기시설재원관리 데이터를 삭제한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */		
	public void deleteElctyFcltySpecMngDetail(Map<?, ?> vo) throws Exception {
		gamElctyFcltySpecMngDao.deleteElctyFcltySpecMngDetail(vo);
		gamElctyFcltySpecMngDao.deleteElctyFcltySpecFileList(vo);
		gamGisPrtFcltyCdMngtDao.deleteGisPrtFclty(vo);
	}	
	
	/**
	 * 전기시설재원관리 첨부파일 목록을 가져온다.
	 * @param vo
	 * @return list 
	 * @throws Exception
	 */		
	public List<?> selectElctyFcltySpecFileList(GamElctyFcltySpecMngVO searchVO) throws Exception {
		return gamElctyFcltySpecMngDao.selectElctyFcltySpecFileList(searchVO);
	}

	/**
	 * 전기시설재원관리 첨부파일 목록 총수를 가져온다.
	 * @param vo
	 * @return list 
	 * @throws Exception
	 */		
	public int selectElctyFcltySpecFileListTotCnt(GamElctyFcltySpecMngVO searchVO) throws Exception {
		return gamElctyFcltySpecMngDao.selectElctyFcltySpecFileListTotCnt(searchVO);
	}

}
