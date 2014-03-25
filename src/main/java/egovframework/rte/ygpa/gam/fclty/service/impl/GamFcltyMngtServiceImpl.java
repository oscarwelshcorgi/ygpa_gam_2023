/**
 * 
 */
package egovframework.rte.ygpa.gam.fclty.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.fclty.service.GamFcltyManageVO;
import egovframework.rte.ygpa.gam.fclty.service.GamFcltyMngtService;

/**
 * 
 * @author kok
 * @since 2014. 2. 3.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 2. 3.		kok		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Service("gamFcltyMngtService")
public class GamFcltyMngtServiceImpl extends AbstractServiceImpl implements GamFcltyMngtService{

	@Resource(name="gamFcltyMngtDao")
    private GamFcltyMngtDao gamFcltyMngtDao;
	
	/**
	 * 시설관리 목록
	 */
	public List<ComDefaultVO> selectFcltyMngtList(ComDefaultVO vo) throws Exception {
   		return (List<ComDefaultVO>)gamFcltyMngtDao.selectFcltyMngtList(vo);
	}
	
	
	/**
	 * 시설관리 총 수
	 */
	public int selectFcltyMngtListTotCnt(ComDefaultVO vo) throws Exception {
		return gamFcltyMngtDao.selectFcltyMngtListTotCnt(vo);
    }
	
	
	/**
	 * 시설관리 파일 목록
	 */
	public List<ComDefaultVO> selectFcltyMngtPhotoList(ComDefaultVO vo) throws Exception {
		return (List<ComDefaultVO>)gamFcltyMngtDao.selectFcltyMngtPhotoList(vo);
	}
	
	
	/**
	 * 시설관리 파일 총 수
	 */
	public int selectFcltyMngtPhotoListTotCnt(ComDefaultVO vo) throws Exception {
		return gamFcltyMngtDao.selectFcltyMngtPhotoListTotCnt(vo);
	}

	
	/**
	 * 시설관리 시퀀스
	 */
	public String insertFcltyGetSeq() throws Exception {
		return gamFcltyMngtDao.insertFcltyGetSeq();
	}
	
	
	/**
	 * 시설관리 상세화면
	 * @param vo
	 * @return GamFcltyManageVO
	 */
	public GamFcltyManageVO fcltyMngSelectView(GamFcltyManageVO vo) {
		GamFcltyManageVO fcltyManageVO = gamFcltyMngtDao.fcltyMngSelectView(vo);		
		return fcltyManageVO;
	}
	

	/**
	 * 시설관리 저장
	 * @param vo GamFcltyManageVO
	 * @exception Exception
	 */
	public void insertFcltyManage(Map<String, Object> fcltyMngtList) throws Exception {
		
		List<HashMap<String,String>> insertFileList = null;
    	HashMap<String,String> form = null;

    	ObjectMapper mapper = new ObjectMapper();

		//convert JSON string to Map
		form = mapper.readValue((String)fcltyMngtList.get("form"),new TypeReference<HashMap<String,String>>(){});
		insertFileList = mapper.readValue((String)fcltyMngtList.get("insertFileList"),new TypeReference<List<HashMap<String,String>>>(){});

		// 시설관리 등록
		form.put("prtFcltySe", (String)fcltyMngtList.get("prtFcltySe"));
		form.put("regUsr", (String)fcltyMngtList.get("USERID"));

		String insertkey = insertFclty(form);
		// 시설관리 파일을 저장한다.
		if(insertFileList.size() > 0){
			for(int i = 0 ; i < insertFileList.size() ; i++) {

				// form에서 저장한 값을 가져온다. 
				insertFileList.get(i).put("gisPrtFcltySeq", insertkey);									// GIS 항만 시설 순번
				insertFileList.get(i).put("regUsr", (String)fcltyMngtList.get("USERID"));				// Login ID
				insertFileList.get(i).put("prtFcltySe", (String)fcltyMngtList.get("prtFcltySe"));		// 항만시설 구분
				insertFileList.get(i).put("gisAssetsCd", form.get("gisAssetsCd"));						// GIS 자산 코드
				insertFileList.get(i).put("gisAssetsSubCd", form.get("gisAssetsSubCd"));				// GIS 자산 SUB 코드
				insertFileList.get(i).put("gisAssetsPrtAtCode", form.get("gisAssetsPrtAtCode"));		// GIS 자산 항코드
				insertFileList.get(i).put("gisPrtFcltyCd", form.get("gisPrtFcltyCd"));					// GIS 항만 시설 코드
				insertFcltyFile(insertFileList.get(i));
			}
		}
	}
	
	
	/**
	 * 시설관리 수정화면
	 */
	public void updateFclty(Map<String, Object> fcltyMngtList) throws Exception {
		
		List<HashMap<String,String>> insertFileList = null;
		List<HashMap<String,String>> updateFileList = null;
		List<HashMap<String,String>> deleteFileList = null;
		HashMap<String,String> form = null;
		
		ObjectMapper mapper = new ObjectMapper();
		
		//convert JSON string to Map
		form = mapper.readValue((String)fcltyMngtList.get("form"),new TypeReference<HashMap<String,String>>(){});
		insertFileList = mapper.readValue((String)fcltyMngtList.get("insertFileList"),new TypeReference<List<HashMap<String,String>>>(){});
		updateFileList = mapper.readValue((String)fcltyMngtList.get("updateFileList"),new TypeReference<List<HashMap<String,String>>>(){});
		deleteFileList = mapper.readValue((String)fcltyMngtList.get("deleteFileList"),new TypeReference<List<HashMap<String,String>>>(){});

		String prtFcltySe = (String)fcltyMngtList.get("prtFcltySe");
		
		// 시설관리 수정
		form.put("updUsr", (String)fcltyMngtList.get("USERID"));
		form.put("prtFcltySe", prtFcltySe);
		updateFclty(form);
		
		String gisPrtFcltySeq = form.get("gisPrtFcltySeq");
		String gisAssetsCd = form.get("gisAssetsCd");
		String gisAssetsSubCd = form.get("gisAssetsSubCd");
		String gisAssetsPrtAtCode = form.get("gisAssetsPrtAtCode");
		String gisPrtFcltyCd = form.get("gisPrtFcltyCd");
		
		// 파일 목록을 수정한다.
		if(insertFileList.size() > 0){
			for(int i = 0 ; i < insertFileList.size() ; i++) {

				// form에서 저장한 값을 가져온다. 
				insertFileList.get(i).put("gisPrtFcltySeq", gisPrtFcltySeq);				// GIS 항만 시설 순번
				insertFileList.get(i).put("regUsr", (String)fcltyMngtList.get("USERID"));	// Login ID
				insertFileList.get(i).put("prtFcltySe", prtFcltySe);						// 항만시설 구분
				insertFileList.get(i).put("gisAssetsCd", gisAssetsCd);						// GIS 자산 코드
				insertFileList.get(i).put("gisAssetsSubCd", gisAssetsSubCd);				// GIS 자산 SUB 코드
				insertFileList.get(i).put("gisAssetsPrtAtCode", gisAssetsPrtAtCode);		// GIS 자산 항코드
				insertFileList.get(i).put("gisPrtFcltyCd", gisPrtFcltyCd);					// GIS 항만 시설 코드
				insertFcltyFile(insertFileList.get(i));
			}
		}
		if(updateFileList.size() > 0){
			for(int i = 0 ; i < updateFileList.size() ; i++) {
				updateFileList.get(i).put("gisPrtFcltySeq", gisPrtFcltySeq);				// GIS 항만 시설 순번
				updateFileList.get(i).put("prtFcltySe", prtFcltySe);						// 항만시설 구분
				updateFileList.get(i).put("gisAssetsCd", gisAssetsCd);						// GIS 자산 코드
				updateFileList.get(i).put("gisAssetsSubCd", gisAssetsSubCd);				// GIS 자산 SUB 코드
				updateFileList.get(i).put("gisAssetsPrtAtCode", gisAssetsPrtAtCode);		// GIS 자산 항코드
				updateFileList.get(i).put("gisPrtFcltyCd", gisPrtFcltyCd);					// GIS 항만 시설 코드
				updateFcltyFile(updateFileList.get(i));
			}
		}
		if(deleteFileList.size() > 0){
			for(int i = 0 ; i < deleteFileList.size() ; i++) {
				deleteFileList.get(i).put("gisPrtFcltySeq", gisPrtFcltySeq);				// GIS 항만 시설 순번
				deleteFileList.get(i).put("prtFcltySe", prtFcltySe);						// 항만시설 구분
				deleteFileList.get(i).put("gisAssetsCd", gisAssetsCd);						// GIS 자산 코드
				deleteFileList.get(i).put("gisAssetsSubCd", gisAssetsSubCd);				// GIS 자산 SUB 코드
				deleteFileList.get(i).put("gisAssetsPrtAtCode", gisAssetsPrtAtCode);		// GIS 자산 항코드
				deleteFileList.get(i).put("gisPrtFcltyCd", gisPrtFcltyCd);					// GIS 항만 시설 코드
				deleteFcltyFile(deleteFileList.get(i));
			}
		}
	}
	
	
	/**
	 * 시설관리 삭제
	 */
	public void deleteFcltyMngt(GamFcltyManageVO vo) throws Exception {
		
		Map<String,String> deleteList = new HashMap<String, String>();
		
		deleteList.put("gisAssetsCd", vo.getGisAssetsCd());
		deleteList.put("gisAssetsPrtAtCode", vo.getGisAssetsPrtAtCode());
		deleteList.put("gisAssetsSubCd", vo.getGisAssetsSubCd());
		deleteList.put("gisPrtFcltyCd", vo.getGisPrtFcltyCd());
		deleteList.put("gisPrtFcltySeq", vo.getGisPrtFcltySeq());
		deleteList.put("prtFcltySe", vo.getPrtFcltySe());
		deleteFcltyFile(deleteList);
		deleteFclty(vo);	
	}
	
	
	// 시설관리 저장
	private String insertFclty(HashMap<String,String> form) throws Exception{
		return gamFcltyMngtDao.insertFclty(form);
	}
	
	// 시설 파일 목록 저장
	private void insertFcltyFile(Map<String,String> insertList) throws Exception{
		gamFcltyMngtDao.insertFcltyFile(insertList);
	}
	
	// 시설관리 수정
	private void updateFclty(HashMap<String,String> form) throws Exception{
		gamFcltyMngtDao.updateFclty(form);
	}
	
	// 시설 파일 목록 수정
	private void updateFcltyFile(Map<String,String> updateList) throws Exception{
		gamFcltyMngtDao.updateFcltyFile(updateList);
	}
	
	// 시설 정보 삭제
	private void deleteFclty(GamFcltyManageVO vo) throws Exception{
		gamFcltyMngtDao.deleteFclty(vo);
	}
	
	// 시설 파일 삭제
	private void deleteFcltyFile(Map<String,String> deleteList) throws Exception{
		gamFcltyMngtDao.deleteFcltyFile(deleteList);
	}
}