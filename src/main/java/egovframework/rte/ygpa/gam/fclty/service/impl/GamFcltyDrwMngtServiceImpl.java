/**
 *
 */
package egovframework.rte.ygpa.gam.fclty.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.fclty.service.GamFcltyDrwInfoFVO;
import egovframework.rte.ygpa.gam.fclty.service.GamFcltyDrwMngtService;
import egovframework.rte.ygpa.gam.fclty.service.GamFcltyDrwDtaFVO;

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

@Service("gamFcltyDrwMngtService")
public class GamFcltyDrwMngtServiceImpl extends AbstractServiceImpl implements GamFcltyDrwMngtService{

	@Resource(name="gamFcltyDrwMngtDao")
    private GamFcltyDrwMngtDao gamFcltyDrwMngtDao;

	/**
	 * 도면목록관리 목록
	 */
	public List<GamFcltyDrwInfoFVO> selectDrwListMngtList(GamFcltyDrwInfoFVO vo) throws Exception {
		return (List<GamFcltyDrwInfoFVO>)gamFcltyDrwMngtDao.selectDrwListMngtList(vo);
	}

	/**
	 * 도면목록관리 목록 총 수
	 */
	public int selectDrwListMngtListTotCnt(GamFcltyDrwInfoFVO vo) throws Exception {
		return gamFcltyDrwMngtDao.selectDrwListMngtListTotCnt(vo);
	}

	/**
	 * 도면목록관리 파일 목록
	 */
	public List<ComDefaultVO> selectDrwListPhotoList(GamFcltyDrwDtaFVO vo) throws Exception {
		return (List<ComDefaultVO>)gamFcltyDrwMngtDao.selectDrwListPhotoList(vo);
	}

	/**
	 * 도면목록관리 파일 총 수
	 */
	public int selectDrwListPhotoListTotCnt(GamFcltyDrwDtaFVO vo) throws Exception {
		return gamFcltyDrwMngtDao.selectDrwListPhotoListTotCnt(vo);
	}

	/**
	 * 도면목록관리 저장
	 * @param vo GamFcltyDrwInfoFVO
	 * @exception Exception
	 */
	public void insertDrwListMng(Map<String, Object> drwListMngtList) throws Exception {

		List<HashMap<String,String>> insertFileList = null;
    	HashMap<String,String> form = null;

    	ObjectMapper mapper = new ObjectMapper();

		//convert JSON string to Map
		form = mapper.readValue((String)drwListMngtList.get("form"),new TypeReference<HashMap<String,String>>(){});
		insertFileList = mapper.readValue((String)drwListMngtList.get("insertFileList"),new TypeReference<List<HashMap<String,String>>>(){});

		// 도면목록관리 등록
		form.put("regUsr", (String)drwListMngtList.get("USERID"));

		String insertkey = insertDrwListMng(form);


		// 도면목록관리 파일을 저장한다.
		if(insertFileList.size() > 0){
			for(int i = 0 ; i < insertFileList.size() ; i++) {

				// form에서 저장한 값을 가져온다.
				insertFileList.get(i).put("drwLstRegistYear", form.get("drwLstRegistYear"));		// 도면 목록 등록 년도
				insertFileList.get(i).put("drwLstSeq", insertkey);									// 도면 목록 순번
				insertFileList.get(i).put("regUsr", (String)drwListMngtList.get("USERID"));			// 등록자
				insertDrwListMngFile(insertFileList.get(i));
			}
		}
	}

	/**
	 * 도면목록관리 수정
	 * @param fcltyMngtList
	 * @throws Exception
	 */
	public void updateDrwListMng(Map<String, Object> drwListMngtList) throws Exception {

		List<HashMap<String,String>> insertFileList = null;
		List<HashMap<String,String>> updateFileList = null;
		List<HashMap<String,String>> deleteFileList = null;
		HashMap<String,String> form = null;

		ObjectMapper mapper = new ObjectMapper();

		//convert JSON string to Map
		form = mapper.readValue((String)drwListMngtList.get("form"),new TypeReference<HashMap<String,String>>(){});
		insertFileList = mapper.readValue((String)drwListMngtList.get("insertFileList"),new TypeReference<List<HashMap<String,String>>>(){});
		updateFileList = mapper.readValue((String)drwListMngtList.get("updateFileList"),new TypeReference<List<HashMap<String,String>>>(){});
		deleteFileList = mapper.readValue((String)drwListMngtList.get("deleteFileList"),new TypeReference<List<HashMap<String,String>>>(){});


		// 도면목록관리 수정
		form.put("updUsr", (String)drwListMngtList.get("USERID"));
		updateDrwListMng(form);

		// 도면목록관리 파일 목록을 수정한다.
		if(insertFileList.size() > 0){
			for(int i = 0 ; i < insertFileList.size() ; i++) {

				// form에서 저장한 값을 가져온다.
				insertFileList.get(i).put("drwLstRegistYear", form.get("drwLstRegistYear"));		// 도면 목록 등록 년도
				insertFileList.get(i).put("drwLstSeq", form.get("drwLstSeq"));						// 도면 목록 순번
				insertFileList.get(i).put("regUsr", (String)drwListMngtList.get("USERID"));			// Login ID
				insertDrwListMngFile(insertFileList.get(i));
			}
		}
		if(updateFileList.size() > 0){
			for(int i = 0 ; i < updateFileList.size() ; i++) {
				//updateFileList.get(i).put("drwLstRegistYear", form.get("drwLstRegistYear"));		// 도면 목록 등록 년도
				//updateFileList.get(i).put("drwLstSeq", form.get("drwLstSeq"));						// 도면 목록 순번
				updateDrwListMngFile(updateFileList.get(i));
			}
		}
		if(deleteFileList.size() > 0){
			for(int i = 0 ; i < deleteFileList.size() ; i++) {
				//deleteFileList.get(i).put("drwLstSeq", form.get("drwLstRegistYear"));			// 도면 목록 순번
				//deleteFileList.get(i).put("drwLstSeq", form.get("drwLstSeq"));					// 도면 목록 순번
				System.out.println(">> : "+deleteFileList.get(i));
				deleteDrwListMngFileImpl(deleteFileList.get(i));
			}
		}
	}

	// 도면목록관리 삭제
	public void deleteDrwListMng(Map vo) throws Exception{
		gamFcltyDrwMngtDao.deleteDrwListMngFile(vo);
		gamFcltyDrwMngtDao.deleteDrwListMng(vo);
	}

	// 도면목록관리 저장
	private String insertDrwListMng(HashMap<String,String> form) throws Exception{
		return gamFcltyDrwMngtDao.insertDrwListMng(form);
	}

	// 도면목록관리 파일 목록 저장
	private void insertDrwListMngFile(Map<String,String> insertList) throws Exception{
		gamFcltyDrwMngtDao.insertDrwListMngFile(insertList);
	}

	// 도면목록관리 수정
	private void updateDrwListMng(HashMap<String,String> form) throws Exception{
		gamFcltyDrwMngtDao.updateDrwListMng(form);
	}

	// 도면목록관리 파일 목록 수정
	private void updateDrwListMngFile(Map<String,String> updateList) throws Exception{
		gamFcltyDrwMngtDao.updateDrwListMngFile(updateList);
	}

	// 도면목록관리 파일 삭제
	private void deleteDrwListMngFileImpl(Map<String,String> deleteList) throws Exception{
		gamFcltyDrwMngtDao.deleteDrwListMngFile(deleteList);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.fclty.service.GamFcltyDrwMngtService#mergeFcltyPhotoMngt(java.util.Map)
	 */
	@Override
	public List mergeDrwInfoMngt(Map mergeList) throws Exception {

		ArrayList arraylistCU = (ArrayList)mergeList.get("CU");
        HashMap[] hmCU = (HashMap[])arraylistCU.toArray(new HashMap[arraylistCU.size()]);
        Map result;
        Integer photoSeq=0;
        Map master = (Map)mergeList.get("MST");

        if("insert".equals(master.get("cmd"))) {
        	gamFcltyDrwMngtDao.insertDrwListMng(master);
        }
        else {
        	gamFcltyDrwMngtDao.updateDrwListMng(master);
        }

//		if(hmCU.length>0) photoSeq=gamFcltyDrwMngtDao.selectFcltyPhotoMaxSeq(hmCU[0]);
        //수정처리 & 입력처리
        for (int i=0; i<hmCU.length; i++) {
        	if ("I".equals(hmCU[i].get("_updtId"))) {
            	log.debug("#photoeq : "+photoSeq.toString());
            	hmCU[i].put("photoSeq", photoSeq++);
//            	hmCU[i].put("prtFcltySe", prtFcltySe);
            }
        	else if("U".equals(hmCU[i].get("_updtId"))){
        	}
            else {
            	log.debug("unknown RowStatus ["+i+"] : "+hmCU[i].get("_updtId"));
            }
        }

		return gamFcltyDrwMngtDao.mergeFcltyPhoto(mergeList);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.fclty.service.GamFcltyDrwMngtService#selectDrwListDetailMaster(java.util.Map)
	 */
	@Override
	public Map selectDrwListDetailMaster(Map vo) throws Exception {
		// TODO Auto-generated method stub
		return gamFcltyDrwMngtDao.selectDrwListDetailMaster(vo);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.fclty.service.GamFcltyDrwMngtService#selectDrwListDetailFileList(java.util.Map)
	 */
	@Override
	public List selectDrwListDetailFileList(Map vo) throws Exception {
		// TODO Auto-generated method stub
		return gamFcltyDrwMngtDao.selectDrwListDetailFileList(vo);
	}


}