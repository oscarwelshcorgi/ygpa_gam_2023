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
import egovframework.rte.ygpa.gam.fclty.service.GamInfoTechFcltySpecMngService;
import egovframework.rte.ygpa.gam.fclty.service.GamInfoTechFcltySpecMngVO;

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
@Service("gamInfoTechFcltySpecMngService")
public class GamInfoTechFcltySpecMngServiceImpl extends AbstractServiceImpl implements GamInfoTechFcltySpecMngService{
	@Resource(name="gamInfoTechFcltySpecMngDao")
	private GamInfoTechFcltySpecMngDao gamInfoTechFcltySpecMngDao;
	@Resource(name="gamGisPrtFcltyCdMngtDao")
	GamGisPrtFcltyCdMngtDao gamGisPrtFcltyCdMngtDao;

    private final static String prtFcltySe = "I";
	/**
	 * 정보통신시설재원관리 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List<?> selectInfoTechFcltySpecMngList(GamInfoTechFcltySpecMngVO searchVO) throws Exception {
		return gamInfoTechFcltySpecMngDao.selectInfoTechFcltySpecMngList(searchVO);
	}

	/**
	 * 정보통신시설재원관리 목록 총수 조회
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int selectInfoTechFcltySpecMngListTotCnt(GamInfoTechFcltySpecMngVO searchVO) throws Exception {
		return gamInfoTechFcltySpecMngDao.selectInfoTechFcltySpecMngListTotCnt(searchVO);
	}

	/**
	 * 정보통신시설재원관리 데이터 조회
	 * @param vo
	 * @return egovMap
	 * @throws Exception
	 */
	public EgovMap selectInfoTechFcltySpecMngDetail(Map<?, ?> searchVO) throws Exception {
		return gamInfoTechFcltySpecMngDao.selectInfoTechFcltySpecMngDetail(searchVO);
	}

	/**
	 * 정보통신시설재원관리 데이터를 삽입한다.
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public void insertInfoTechFcltySpecMngDetail(Map<String, String> detailForm, List<HashMap<String, String>> atchFileList) throws Exception {
		detailForm.put("prtFcltySe", prtFcltySe);
		detailForm.put("gisPrtFcltySeq", gamGisPrtFcltyCdMngtDao.selectNextFcltySeq(detailForm));
		gamGisPrtFcltyCdMngtDao.insertGisPrtFclty(detailForm);
		gamInfoTechFcltySpecMngDao.insertInfoTechFcltySpecMngDetail(detailForm);

		detailForm.put("fcltsMngNo", detailForm.get("gisAssetsPrtAtCode")
				+ detailForm.get("gisAssetsCd")
				+ detailForm.get("gisAssetsSubCd")
				+ detailForm.get("gisPrtFcltyCd")
				+ detailForm.get("gisPrtFcltySeq")
				+ prtFcltySe);

		for(HashMap<String, String> atchFileItem : atchFileList) {
			atchFileItem.put("fcltsMngNo", detailForm.get("fcltsMngNo"));
			gamInfoTechFcltySpecMngDao.insertFcltyFile(atchFileItem);
		}
	}

	/**
	 * 정보통신시설재원관리 데이터를 수정한다.
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public void updateInfoTechFcltySpecMngDetail(Map<String, String> detailForm, Map<String, Object> atchFileMap) throws Exception {
		detailForm.put("prtFcltySe", prtFcltySe);
		gamGisPrtFcltyCdMngtDao.updateGisPrtFclty(detailForm);
		gamInfoTechFcltySpecMngDao.updateInfoTechFcltySpecMngDetail(detailForm);
		gamInfoTechFcltySpecMngDao.mergeFcltyFile(atchFileMap);
	}

	/**
	 * 정보통신시설재원관리 데이터를 삭제한다.
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public void deleteInfoTechFcltySpecMngDetail(Map<?, ?> vo) throws Exception {
		gamInfoTechFcltySpecMngDao.deleteInfoTechFcltySpecFileList(vo);
		gamInfoTechFcltySpecMngDao.deleteInfoTechFcltySpecMngDetail(vo);
		gamGisPrtFcltyCdMngtDao.deleteGisPrtFclty(vo);
	}

	/**
	 * 정보통신시설재원관리 첨부파일 목록을 가져온다.
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List<?> selectInfoTechFcltySpecFileList(GamInfoTechFcltySpecMngVO searchVO) throws Exception {
		return gamInfoTechFcltySpecMngDao.selectInfoTechFcltySpecFileList(searchVO);
	}

	/**
	 * 정보통신시설재원관리 첨부파일 목록 총수를 가져온다.
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public int selectInfoTechFcltySpecFileListTotCnt(GamInfoTechFcltySpecMngVO searchVO) throws Exception {
		return gamInfoTechFcltySpecMngDao.selectInfoTechFcltySpecFileListTotCnt(searchVO);
	}
}
