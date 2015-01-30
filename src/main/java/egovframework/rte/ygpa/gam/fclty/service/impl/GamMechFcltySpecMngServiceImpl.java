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
import egovframework.rte.ygpa.gam.fclty.service.GamMechFcltySpecMngService;
import egovframework.rte.ygpa.gam.fclty.service.GamMechFcltySpecMngVO;

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
 *  2014. 11. 17.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Service("gamMechFcltySpecMngService")
public class GamMechFcltySpecMngServiceImpl extends AbstractServiceImpl implements GamMechFcltySpecMngService{
	@Resource(name="gamMechFcltySpecMngDao")
	private GamMechFcltySpecMngDao gamMechFcltySpecMngDao;
	@Resource(name="gamGisPrtFcltyCdMngtDao")
	GamGisPrtFcltyCdMngtDao gamGisPrtFcltyCdMngtDao;

    private final static String prtFcltySe = "M";    
	/**
	 * 기계시설재원관리 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */	
	public List<?> selectMechFcltySpecMngList(GamMechFcltySpecMngVO searchVO) throws Exception {
		return gamMechFcltySpecMngDao.selectMechFcltySpecMngList(searchVO);
	}
	
	/**
	 * 기계시설재원관리 목록 총수 조회
	 * @param vo
	 * @return int
	 * @throws Exception
	 */		
	public int selectMechFcltySpecMngListTotCnt(GamMechFcltySpecMngVO searchVO) throws Exception {
		return gamMechFcltySpecMngDao.selectMechFcltySpecMngListTotCnt(searchVO);
	}

	/**
	 * 기계시설재원관리 데이터 조회
	 * @param vo
	 * @return egovMap
	 * @throws Exception
	 */		
	public EgovMap selectMechFcltySpecMngDetail(Map<?, ?> searchVO) throws Exception {
		return gamMechFcltySpecMngDao.selectMechFcltySpecMngDetail(searchVO);
	}
	
	/**
	 * 기계시설재원관리 데이터를 삽입한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */		
	public void insertMechFcltySpecMngDetail(Map<String, String> detailForm, List<HashMap<String, String>> atchFileList) throws Exception {
		detailForm.put("prtFcltySe", prtFcltySe);
		detailForm.put("gisPrtFcltySeq", gamGisPrtFcltyCdMngtDao.selectNextFcltySeq(detailForm));
		gamGisPrtFcltyCdMngtDao.insertGisPrtFclty(detailForm);
		gamMechFcltySpecMngDao.insertMechFcltySpecMngDetail(detailForm);
		
		detailForm.put("fcltsMngNo", detailForm.get("gisAssetsPrtAtCode") 
						+ detailForm.get("gisAssetsCd") 
						+ detailForm.get("gisAssetsSubCd") 
						+ detailForm.get("gisPrtFcltyCd") 
						+ detailForm.get("gisPrtFcltySeq")
						+ prtFcltySe);
		
		for(HashMap<String, String> atchFileItem : atchFileList) {
			atchFileItem.put("fcltsMngNo", detailForm.get("fcltsMngNo"));
			gamMechFcltySpecMngDao.insertFcltyFile(atchFileItem);
		}
	}
	
	/**
	 * 기계시설재원관리 데이터를 수정한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */		
	public void updateMechFcltySpecMngDetail(Map<String, String> detailForm, Map<String, Object> atchFileMap) throws Exception {
		detailForm.put("prtFcltySe", prtFcltySe);
		gamGisPrtFcltyCdMngtDao.updateGisPrtFclty(detailForm);
		gamMechFcltySpecMngDao.updateMechFcltySpecMngDetail(detailForm);
		gamMechFcltySpecMngDao.mergeFcltyFile(atchFileMap);
	}
	
	/**
	 * 기계시설재원관리 데이터를 삭제한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */		
	public void deleteMechFcltySpecMngDetail(Map<?, ?> vo) throws Exception {
		gamMechFcltySpecMngDao.deleteMechFcltySpecFileList(vo);
		gamMechFcltySpecMngDao.deleteMechFcltySpecMngDetail(vo);
		gamGisPrtFcltyCdMngtDao.deleteGisPrtFclty(vo);
	}	
	
	/**
	 * 기계시설재원관리 첨부파일 목록을 가져온다.
	 * @param vo
	 * @return list 
	 * @throws Exception
	 */		
	public List<?> selectMechFcltySpecFileList(GamMechFcltySpecMngVO searchVO) throws Exception {
		return gamMechFcltySpecMngDao.selectMechFcltySpecFileList(searchVO);
	}

	/**
	 * 기계시설재원관리 첨부파일 목록 총수를 가져온다.
	 * @param vo
	 * @return list 
	 * @throws Exception
	 */		
	public int selectMechFcltySpecFileListTotCnt(GamMechFcltySpecMngVO searchVO) throws Exception {
		return gamMechFcltySpecMngDao.selectMechFcltySpecFileListTotCnt(searchVO);
	}
}
