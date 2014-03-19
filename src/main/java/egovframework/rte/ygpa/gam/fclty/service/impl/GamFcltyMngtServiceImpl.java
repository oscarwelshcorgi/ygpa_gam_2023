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
	 * 시설관리 카운트
	 */
	public int selectFcltyMngtListTotCnt(ComDefaultVO vo) throws Exception {
		return gamFcltyMngtDao.selectFcltyMngtListTotCnt(vo);
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

    	try {

    		//convert JSON string to Map
    		form = mapper.readValue((String)fcltyMngtList.get("form"),new TypeReference<HashMap<String,String>>(){});
    		insertFileList = mapper.readValue((String)fcltyMngtList.get("insertFileList"),new TypeReference<List<HashMap<String,String>>>(){});

    		// 시설관리 등록
    		form.put("regUsr", (String)fcltyMngtList.get("USERID"));
    		form.put("prtFcltySe", (String)fcltyMngtList.get("prtFcltySe"));
    		insertFclty(form);

    		// 시설관리 파일을 저장한다.
			if(insertFileList.size() > 0){
				for(int i = 0 ; i < insertFileList.size() ; i++) {
					insertFileList.get(i).put("entrpscd", form.get("entrpscd"));
					insertFileList.get(i).put("regUsr", (String)fcltyMngtList.get("USERID"));
					insertFcltyFile(insertFileList.get(i));
				}
			}
    	}catch (Exception e){
    		e.printStackTrace();
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
		
		try {
			
			//convert JSON string to Map
			form = mapper.readValue((String)fcltyMngtList.get("form"),new TypeReference<HashMap<String,String>>(){});
			insertFileList = mapper.readValue((String)fcltyMngtList.get("insertFileList"),new TypeReference<List<HashMap<String,String>>>(){});
			updateFileList = mapper.readValue((String)fcltyMngtList.get("updateFileList"),new TypeReference<List<HashMap<String,String>>>(){});
			deleteFileList = mapper.readValue((String)fcltyMngtList.get("deleteFileList"),new TypeReference<List<HashMap<String,String>>>(){});

			// 시설관리 수정
			form.put("updUsr", (String)fcltyMngtList.get("USERID"));
			form.put("prtFcltySe", (String)fcltyMngtList.get("prtFcltySe"));
			updateFclty(form);
			
			// 업체 담당자 목록을 수정한다.
			if(insertFileList.size() > 0){
				for(int i = 0 ; i < insertFileList.size() ; i++) {
					insertFileList.get(i).put("entrpscd", form.get("entrpscd"));
					insertFileList.get(i).put("regUsr", (String)fcltyMngtList.get("USERID"));
					insertFcltyFile(insertFileList.get(i));
				}
			}
			if(updateFileList.size() > 0){
				for(int i = 0 ; i < updateFileList.size() ; i++) {
					updateFileList.get(i).put("entrpscd", updateFileList.get(i).get("entrpscd"));
					updateFileList.get(i).put("chargerNo", updateFileList.get(i).get("chargerNo"));
					updateFileList.get(i).put("updUsr", (String)fcltyMngtList.get("USERID"));
					updateFcltyFile(updateFileList.get(i));
				}
			}
			if(deleteFileList.size() > 0){
				for(int i = 0 ; i < deleteFileList.size() ; i++) {
					deleteFileList.get(i).put("entrpscd", deleteFileList.get(i).get("entrpscd"));
					deleteFileList.get(i).put("chargerNo", deleteFileList.get(i).get("chargerNo"));
					deleteFcltyFile(deleteFileList.get(i));
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 시설관리 삭제
	 */
	public void deleteFcltyMngt(GamFcltyManageVO vo) {
		
		try{
		
			deleteFclty(vo);	
		}catch(Exception e){
			
		}
	}
	
	
	// 시설관리 저장
	private void insertFclty(HashMap<String,String> form) throws Exception{
		gamFcltyMngtDao.insertFclty(form);
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