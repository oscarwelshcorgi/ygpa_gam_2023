/**
 * 
 */
package egovframework.rte.ygpa.gam.fclty.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.cmmn.fclty.service.impl.GamGisPrtFcltyCdMngtDao;
import egovframework.rte.ygpa.gam.fclty.service.GamCivilFcltySpecMngService;
import egovframework.rte.ygpa.gam.fclty.service.GamCivilFcltySpecMngVO;

/**
 * 
 * @author 김종민
 * @since 2014. 11. 10.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 11. 10.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Service("gamCivilFcltySpecMngService")
public class GamCivilFcltySpecMngServiceImpl extends AbstractServiceImpl implements GamCivilFcltySpecMngService{
	@Resource(name="gamCivilFcltySpecMngDao")
	private GamCivilFcltySpecMngDao gamCivilFcltySpecMngDao;
	@Resource(name="gamGisPrtFcltyCdMngtDao")
	GamGisPrtFcltyCdMngtDao gamGisPrtFcltyCdMngtDao;

    private final static String prtFcltySe = "C";    

	/**
	 * 토목시설재원관리 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */	
	public List<?> selectCivilFcltySpecMngList(GamCivilFcltySpecMngVO searchVO) throws Exception {
		return gamCivilFcltySpecMngDao.selectCivilFcltySpecMngList(searchVO);
	}
	
	/**
	 * 토목시설재원관리 목록 총수 조회
	 * @param vo
	 * @return int
	 * @throws Exception
	 */		
	public int selectCivilFcltySpecMngListTotCnt(GamCivilFcltySpecMngVO searchVO) throws Exception {
		return gamCivilFcltySpecMngDao.selectCivilFcltySpecMngListTotCnt(searchVO);
	}

	/**
	 * 토목시설재원관리 데이터 조회
	 * @param vo
	 * @return egovMap
	 * @throws Exception
	 */		
	public EgovMap selectCivilFcltySpecMngDetail(Map<?, ?> searchVO) throws Exception {
		return gamCivilFcltySpecMngDao.selectCivilFcltySpecMngDetail(searchVO);
	}
	
	/**
	 * 토목시설재원관리 데이터를 삽입한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */		
	public void insertCivilFcltySpecMngDetail(Map<String, String> detailForm, List<HashMap<String, String>> atchFileList) throws Exception {
		detailForm.put("prtFcltySe", prtFcltySe);
		detailForm.put("gisPrtFcltySeq", gamGisPrtFcltyCdMngtDao.selectNextFcltySeq(detailForm));
		gamGisPrtFcltyCdMngtDao.insertGisPrtFclty(detailForm);
		gamCivilFcltySpecMngDao.insertCivilFcltySpecMngDetail(detailForm);
		
		detailForm.put("fcltsMngNo", detailForm.get("gisAssetsPrtAtCode") 
				+ detailForm.get("gisAssetsCd") 
				+ detailForm.get("gisAssetsSubCd") 
				+ detailForm.get("gisPrtFcltyCd") 
				+ detailForm.get("gisPrtFcltySeq")
				+ prtFcltySe);

		for(HashMap<String, String> atchFileItem : atchFileList) {
			atchFileItem.put("fcltsMngNo", detailForm.get("fcltsMngNo"));
			gamCivilFcltySpecMngDao.insertFcltyFile(atchFileItem);
		}		
	}
	
	/**
	 * 토목시설재원관리 데이터를 수정한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */		
	public void updateCivilFcltySpecMngDetail(Map<String, String> detailForm, Map<String, Object> atchFileMap) throws Exception {
		detailForm.put("prtFcltySe", prtFcltySe);
		gamGisPrtFcltyCdMngtDao.updateGisPrtFclty(detailForm);
		gamCivilFcltySpecMngDao.updateCivilFcltySpecMngDetail(detailForm);
		gamCivilFcltySpecMngDao.mergeFcltyFile(atchFileMap);
	}
	
	/**
	 * 토목시설재원관리 데이터를 삭제한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */		
	public void deleteCivilFcltySpecMngDetail(Map<?, ?> vo) throws Exception {
		gamCivilFcltySpecMngDao.deleteCivilFcltySpecFileList(vo);
		gamCivilFcltySpecMngDao.deleteCivilFcltySpecMngDetail(vo);
		gamGisPrtFcltyCdMngtDao.deleteGisPrtFclty(vo);
	}

	/**
	 * 토목시설재원관리 첨부파일 목록을 가져온다.
	 * @param vo
	 * @return list 
	 * @throws Exception
	 */		
	public List<?> selectCivilFcltySpecFileList(GamCivilFcltySpecMngVO searchVO) throws Exception {
		return gamCivilFcltySpecMngDao.selectCivilFcltySpecFileList(searchVO);
	}

	/**
	 * 토목시설재원관리 첨부파일 목록 총수를 가져온다.
	 * @param vo
	 * @return list 
	 * @throws Exception
	 */		
	public int selectCivilFcltySpecFileListTotCnt(GamCivilFcltySpecMngVO searchVO) throws Exception {
		return gamCivilFcltySpecMngDao.selectCivilFcltySpecFileListTotCnt(searchVO);
	}
}
