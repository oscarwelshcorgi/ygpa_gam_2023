/**
 *
 */
package egovframework.rte.ygpa.gam.code.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.code.service.GamCmpyInfoMngtService;
import egovframework.rte.ygpa.gam.code.service.GamEntrpsChargerFVO;
import egovframework.rte.ygpa.gam.code.service.GamEntrpsInfoFVO;

/**
 *
 * @author kok
 * @since 2014. 3. 5.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 3. 5.		kok		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Service("gamCmpyInfoMngtService")
public class GamCmpyInfoMngtServiceImpl extends AbstractServiceImpl implements GamCmpyInfoMngtService{

	@Resource(name="gamCmpyInfoMngtDao")
    private GamCmpyInfoMngtDao gamCmpyInfoMngtDao;



	/**
	 * 업체정보관리 목록
	 */
	public List<GamEntrpsInfoFVO> selectCmpyInfoMngtList(GamEntrpsInfoFVO vo) throws Exception {
   		return (List<GamEntrpsInfoFVO>)gamCmpyInfoMngtDao.selectCmpyInfoMngtList(vo);
	}

	/**
	 * 업체정보관리 목록 총 수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int selectCmpyInfoMngtListTotCnt(GamEntrpsInfoFVO vo) throws Exception {
		return gamCmpyInfoMngtDao.selectCmpyInfoMngtListTotCnt(vo);
    }

	/**
	 * 업체정보관리 목록
	 */
	public List<GamEntrpsChargerFVO> selectCmpyMngtList(GamEntrpsChargerFVO vo) throws Exception {
		return (List<GamEntrpsChargerFVO>)gamCmpyInfoMngtDao.selectCmpyMngtList(vo);
	}


	/**
	 * 업체정보관리 목록 총 수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int selectCmpyMngtListTotCnt(GamEntrpsChargerFVO vo) throws Exception {
		return gamCmpyInfoMngtDao.selectCmpyMngtListTotCnt(vo);
	}


	/**
	 * 업체정보관리 상세
	 * @param vo
	 * @return vo
	 * @throws Exception
	 */
	public GamEntrpsInfoFVO selectCmpyInfoMngtDetail(GamEntrpsInfoFVO vo) throws Exception {
		return gamCmpyInfoMngtDao.selectCmpyInfoMngtDetail(vo);
	}


	/**
	 * 업체담당자 정보 상세
	 * @param vo
	 * @return vo
	 * @throws Exception
	 */
	public GamEntrpsChargerFVO selectCmpyMngtDetail(GamEntrpsChargerFVO vo) throws Exception {
		return gamCmpyInfoMngtDao.selectCmpyMngtDetail(vo);
	}


	/**
	 * 업체정보 관리 저장
	 * @param vo
	 * @return vo
	 * @throws Exception
	 */
	public void insertCmpyInfoMngt(Map<String, Object> cmpyMngtList) throws Exception {

		List<HashMap<String,String>> insertList = null;
    	HashMap<String,String> form = null;

    	ObjectMapper mapper = new ObjectMapper();

    	try {

    		//convert JSON string to Map
    		form = mapper.readValue((String)cmpyMngtList.get("form"),new TypeReference<HashMap<String,String>>(){});
    		insertList = mapper.readValue((String)cmpyMngtList.get("insertList"),new TypeReference<List<HashMap<String,String>>>(){});

    		// 업체정보 등록
    		form.put("regUsr", (String)cmpyMngtList.get("USERID"));
    		//insertCmpyInfo(form);

    		// 업체 담당자 목록을 저장한다.
    		if(insertList.size() > 0){
    			for(int i = 0 ; i < insertList.size() ; i++) {
    				insertList.get(i).put("entrpscd", form.get("entrpscd"));
    				insertList.get(i).put("regUsr", (String)cmpyMngtList.get("USERID"));
    				insertCmpyCharger(insertList.get(i));
    			}
    		}
    	}catch(Exception e) { log.warn("an error found"); }
	}


	/**
	 * 업체정보 관리 수정
	 * @param vo
	 * @return vo
	 * @throws Exception
	 */
	public void updateCmpyInfoMngt(Map<String, Object> cmpyMngtList) throws Exception {

		List<HashMap<String,String>> insertList = null;
		List<HashMap<String,String>> updateList = null;
		List<HashMap<String,String>> deleteList = null;
		HashMap<String,String> form = null;

		ObjectMapper mapper = new ObjectMapper();

		try {

			//convert JSON string to Map
			form = mapper.readValue((String)cmpyMngtList.get("form"),new TypeReference<HashMap<String,String>>(){});
			insertList = mapper.readValue((String)cmpyMngtList.get("insertList"),new TypeReference<List<HashMap<String,String>>>(){});
			updateList = mapper.readValue((String)cmpyMngtList.get("updateList"),new TypeReference<List<HashMap<String,String>>>(){});
			deleteList = mapper.readValue((String)cmpyMngtList.get("deleteList"),new TypeReference<List<HashMap<String,String>>>(){});

			// 업체정보 등록
			form.put("updUsr", (String)cmpyMngtList.get("USERID"));
			//updateCmpyInfo(form);

			// 업체 담당자 목록을 수정한다.
			if(insertList.size() > 0){
				for(int i = 0 ; i < insertList.size() ; i++) {
					insertList.get(i).put("entrpscd", form.get("entrpscd"));
					insertList.get(i).put("regUsr", (String)cmpyMngtList.get("USERID"));
					insertCmpyCharger(insertList.get(i));
				}
			}
			if(updateList.size() > 0){
				for(int i = 0 ; i < updateList.size() ; i++) {
					updateList.get(i).put("entrpscd", updateList.get(i).get("entrpscd"));
					updateList.get(i).put("chargerNo", updateList.get(i).get("chargerNo"));
					updateList.get(i).put("updUsr", (String)cmpyMngtList.get("USERID"));
					updateCmpyCharger(updateList.get(i));
				}
			}
			if(deleteList.size() > 0){
				for(int i = 0 ; i < deleteList.size() ; i++) {
					deleteList.get(i).put("entrpscd", deleteList.get(i).get("entrpscd"));
					deleteList.get(i).put("chargerNo", deleteList.get(i).get("chargerNo"));
					deleteCmpyCharger(deleteList.get(i));
				}
			}
		}catch(Exception e) { log.warn("an error found"); }
	}


	/**
	 * 업체정보 관리 삭제
	 * @param vo
	 * @return vo
	 * @throws Exception
	 */
	public void deleteCmpyInfoMngt(String entrpscd) throws Exception {

		Map<String, String> deleteList = new HashMap<String, String>();

		deleteList.put("entrpscd", entrpscd);

		// 업체담당자 삭제
		deleteCmpyCharger(deleteList);

		// 업체정보 삭제
		//deleteCmpyInfo(entrpscd);
	}


	// 업체 정보 저장
	private void insertCmpyInfo(HashMap<String,String> form) throws Exception{
		gamCmpyInfoMngtDao.insertCmpyInfo(form);
	}

	// 업체 담당자 저장
	private void insertCmpyCharger(Map<String,String> insertList) throws Exception{
		gamCmpyInfoMngtDao.insertCmpyCharger(insertList);
	}

	// 업체 정보 수정
	private void updateCmpyInfo(HashMap<String,String> form) throws Exception{
		gamCmpyInfoMngtDao.updateCmpyInfo(form);
	}

	// 업체 담당자 수정
	private void updateCmpyCharger(Map<String,String> updateList) throws Exception{
		gamCmpyInfoMngtDao.updateCmpyCharger(updateList);
	}

	// 업체 정보 삭제
	private void deleteCmpyInfo(String entrpscd) throws Exception{
		gamCmpyInfoMngtDao.deleteCmpyInfo(entrpscd);
	}

	// 업체 담당자 삭제
	private void deleteCmpyCharger(Map<String,String> deleteList) throws Exception{
		gamCmpyInfoMngtDao.deleteCmpyCharger(deleteList);
	}


	/**
	 * 업체코드 체크
	 */
	public int checkEntrpscd(String entrpscd) throws Exception {
		return gamCmpyInfoMngtDao.checkEntrpscd(entrpscd);
	}
}