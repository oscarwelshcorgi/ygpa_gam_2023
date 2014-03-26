/**
 * 
 */
package egovframework.rte.ygpa.gam.fclty.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.fclty.service.GamFcltyDrwDtaFVO;
import egovframework.rte.ygpa.gam.fclty.service.GamFcltyDrwInfoFVO;

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
@Repository("gamFcltyDrwMngtDao")
public class GamFcltyDrwMngtDao extends YGPAAbstractDAO{

	/**
	 * 도면목록관리 목록
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<GamFcltyDrwInfoFVO> selectDrwListMngtList(GamFcltyDrwInfoFVO vo) throws Exception{
		return list("gamFcltyDrwMngtDao.selectDrwListMngtList", vo);
	}
	
	/**
	 * 도면목록관리 목록 총 수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int selectDrwListMngtListTotCnt(GamFcltyDrwInfoFVO vo) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamFcltyDrwMngtDao.selectDrwListMngtListTotCnt", vo);
	}
	
	/**
	 * 도면목록관리 파일 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
    @SuppressWarnings("unchecked")
    public List<ComDefaultVO> selectDrwListPhotoList(GamFcltyDrwDtaFVO vo) throws Exception{
    	return list("gamFcltyDrwMngtDao.selectDrwListPhotoList", vo);
    }
    
    /**
     * 도면목록관리 파일 총 수
     * @param vo
     * @return int
     * @throws Exception
     */
    public int selectDrwListPhotoListTotCnt(GamFcltyDrwDtaFVO vo) throws Exception {
    	return (Integer)getSqlMapClientTemplate().queryForObject("gamFcltyDrwMngtDao.selectDrwListPhotoListTotCnt", vo);
    }
	
	/**
	 * 도면목록관리 저장
	 * @param form
	 * @return string
	 */
	public String insertDrwListMng(HashMap<String,String> form){
		insert("gamFcltyDrwMngtDao.insertDrwListMng", form);
		
		return form.get("maxDrwSeq");
	}
	
	/**
	 * 도면목록관리 파일 저장
	 * @param fileList
	 */
	public void insertDrwListMngFile(Map<String,String> fileList){
		insert("gamFcltyDrwMngtDao.insertDrwListMngFile", fileList);
	}
	
	/**
	 * 도면목록관리 수정
	 * @param form
	 */
    public void updateDrwListMng(HashMap<String,String> form){
        update("gamFcltyDrwMngtDao.updateDrwListMng",form);
    }
    
    /**
     * 도면목록관리 파일 수정
     * @param vo
     */
    public void updateDrwListMngFile(Map<String,String> fileList){
    	update("gamFcltyDrwMngtDao.updateDrwListMngFile",fileList);
    }
	
    /**
     * 도면목록관리 삭제
     * @param vo
     */
    public void deleteDrwListMng(GamFcltyDrwDtaFVO vo){
    	delete("gamFcltyDrwMngtDao.deleteDrwListMng", vo);
    }
    
    /**
     * 도면목록관리 파일 삭제
     * @param fileList
     */
    public void deleteDrwListMngFile(Map<String,String> fileList){
    	update("gamFcltyDrwMngtDao.deleteDrwListMngFile",fileList);
    }
}