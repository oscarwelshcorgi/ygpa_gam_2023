/**
 *
 */
package egovframework.rte.ygpa.gam.fclty.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.cmmn.fclty.service.impl.GamGisPrtFcltyCdMngtDao;
import egovframework.rte.ygpa.gam.fclty.service.GamConsFcltySpecMngVO;
import egovframework.rte.ygpa.gam.fclty.service.GamConsFcltySpecMngService;

/**
 *
 * @author HNJ
 * @since 2014. 11. 4.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 11. 4.	HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Service("gamConsFcltySpecMngService")
public class GamConsFcltySpecMngServiceImpl extends AbstractServiceImpl implements GamConsFcltySpecMngService{

	@Resource(name="gamConsFcltySpecMngDao")
    private GamConsFcltySpecMngDao gamConsFcltySpecMngDao;
	@Resource(name="gamGisPrtFcltyCdMngtDao")
	GamGisPrtFcltyCdMngtDao gamGisPrtFcltyCdMngtDao;

	/**
	 * 시설관리 목록
	 */
	public List<?> selectFcltySpecMngList(GamConsFcltySpecMngVO vo) throws Exception {
   		return (List<?>)gamConsFcltySpecMngDao.selectFcltySpecMngList(vo);
	}


	/**
	 * 시설관리 총 수
	 */
	public int selectFcltySpecMngListTotCnt(GamConsFcltySpecMngVO vo) throws Exception {
		return gamConsFcltySpecMngDao.selectFcltySpecMngListTotCnt(vo);
    }


	/**
	 * 시설관리 파일 목록
	 */
	public List<ComDefaultVO> selectFcltySpecMngFileList(GamConsFcltySpecMngVO vo) throws Exception {
		return (List<ComDefaultVO>)gamConsFcltySpecMngDao.selectFcltySpecMngFileList(vo);
	}


	/**
	 * 시설관리 파일 총 수
	 */
	public int selectFcltySpecMngFileListTotCnt(GamConsFcltySpecMngVO vo) throws Exception {
		return gamConsFcltySpecMngDao.selectFcltySpecMngFileListTotCnt(vo);
	}



	/**
	 * 시설재원관리 입력
	 * @param Map, List
	 * @return 
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void insertFcltySpec(Map fcltyManageVO, List insertFileList) throws Exception{
		
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
		
		gamGisPrtFcltyCdMngtDao.insertGisPrtFclty(fcltyManageVO);
		gamConsFcltySpecMngDao.insertFcltySpec(fcltyManageVO);
		
		// 시설 첨부파일 입력처리
		for(int i=0;i<insertFileList.size();i++){
			insertFile = (Map) insertFileList.get(i);
			insertFile.put("fcltsMngNo",fcltsMngNo);
			insertFile.put("regUsr", fcltyManageVO.get("regUsr"));
			gamConsFcltySpecMngDao.insertFcltyFile(insertFile);
		}
	}

	/**
	 * 시설재원관리 수정
	 * @param Map, Map
	 * @return 
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void updateFcltySpec(Map fcltyManageVO, Map mergeMap) throws Exception{
		
		gamGisPrtFcltyCdMngtDao.updateGisPrtFclty(fcltyManageVO);
		gamConsFcltySpecMngDao.updateFcltySpec(fcltyManageVO);
		
		gamConsFcltySpecMngDao.mergeFcltyFile(mergeMap);
	}

	/**
	 * 시설재원관리 삭제
	 * @param Map
	 * @return 
	 * @throws Exception
	 */
	public void deleteFcltySpec(Map<?,?> vo) throws Exception{
		gamGisPrtFcltyCdMngtDao.deleteGisPrtFclty(vo);
		gamConsFcltySpecMngDao.deleteFcltySpec(vo);
		gamConsFcltySpecMngDao.deleteFcltyTotalFile(vo);
	}


	/**
	 * 시설관리 상세화면
	 * @param emplyrId
	 * @return GamConsFcltySpecMngVO
	 * @throws Exception
	 */
	public EgovMap fcltyMngSelectView(Map<?,?> vo)throws Exception {
		return gamConsFcltySpecMngDao.fcltyMngSelectView(vo);
	}
	
	
	/**
	 * 시설관리 상세화면(제원)
	 * @param emplyrId
	 * @return GamConsFcltySpecMngVO
	 * @throws Exception
	 */
	public EgovMap fcltySpecMngSelectView(Map<?,?> vo) throws Exception {
		return gamConsFcltySpecMngDao.fcltySpecMngSelectView(vo);
	}


}