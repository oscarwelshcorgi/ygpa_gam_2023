/**
 *
 */
package egovframework.rte.ygpa.gam.fclty.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.fclty.service.GamConsFcltySpecMngVO;


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

@Repository("gamConsFcltySpecMngDao")
public class GamConsFcltySpecMngDao extends YGPAAbstractDAO{

	/**
	 * 시설관리 저장
	 * @param fcltyMngtList
	 */
	public String insertFclty(Map form){
		insert("gamConsFcltySpecMngDao.insertFclty", form);

		return (String)form.get("maxFcltySeq");
	}


	/**
	 * 시설관리 파일 저장
	 * @param fcltyMngtList
	 */
	public void insertFcltyFile(Map fileList){
		insert("gamConsFcltySpecMngDao.insertFcltyFile", fileList);
	}


    /**
     * 시설관리 수정화면
     * @param vo
     */
    public void updateFclty(Map form){
        update("gamConsFcltySpecMngDao.updateFclty",form);
    }


    /**
     * 시설관리 파일 저장
     * @param vo
     */
    public void updateFcltyFile(Map fileList){
    	update("gamConsFcltySpecMngDao.updateFcltyFile",fileList);
    }


    /**
     * 시설관리 파일 저장
     * @param vo
     */
    public void deleteFcltyFile(Map fileList){
    	update("gamConsFcltySpecMngDao.deleteFcltyFile",fileList);
    }


	/**
	 * 시설관리 목록 조회
	 * @param vo ComDefaultVO
	 * @return List
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComDefaultVO> selectFcltySpecMngList(GamConsFcltySpecMngVO vo) throws Exception{
		return list("gamConsFcltySpecMngDao.selectFcltySpecMngList", vo);
	}


	/**
	 * 시설관리 총 수
	 * @param ComDefaultVO vo
	 * @return int
	 * @exception Exception
	 */
    public int selectFcltySpecMngListTotCnt(GamConsFcltySpecMngVO vo) throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamConsFcltySpecMngDao.selectFcltySpecMngListTotCnt", vo);
    }


    /**
     * 시설관리 파일 목록 조회
     * @param vo ComDefaultVO
     * @return List
     * @exception Exception
     */
    @SuppressWarnings("unchecked")
    public List<ComDefaultVO> selectFcltySpecMngPhotoList(GamConsFcltySpecMngVO vo) throws Exception{
    	return list("gamConsFcltySpecMngDao.selectFcltySpecMngPhotoList", vo);
    }


    /**
     * 시설관리 파일 총 수
     * @param ComDefaultVO vo
     * @return int
     * @exception Exception
     */
    public int selectFcltySpecMngPhotoListTotCnt(GamConsFcltySpecMngVO vo) throws Exception {
    	return (Integer)getSqlMapClientTemplate().queryForObject("gamConsFcltySpecMngDao.selectFcltySpecMngPhotoListTotCnt", vo);
    }


    /**
     * 시설관리 시퀀스
     * @return int
     * @exception Exception
     */
    public String insertFcltyGetSeq() throws Exception {
    	return (String)getSqlMapClientTemplate().queryForObject("gamConsFcltySpecMngDao.insertFcltyGetSeq");
    }


    /**
     * 시설관리 상세화면
     * @param vo
     * @return GamFcltyManageVO
     */
    public EgovMap fcltyMngSelectView(Map vo) throws Exception{
        return (EgovMap) selectByPk("gamConsFcltySpecMngDao.fcltyMngSelectView", vo);
    }
    
    /**
     * 시설관리 상세화면(제원)
     * @param vo
     * @return GamFcltyManageVO
     */
    public EgovMap fcltySpecMngSelectView(Map vo) throws Exception{
        return (EgovMap) selectByPk("gamConsFcltySpecMngDao.fcltySpecMngSelectView", vo);
    }


    /**
     * 시설관리 삭제화면
     * @param vo
     */
    public void deleteFclty(Map vo) throws Exception{
    	delete("gamConsFcltySpecMngDao.deleteFclty", vo);
    }

    public List mergeFcltyPhoto(Map vo) throws Exception{
		return this.merge(vo, "gamConsFcltySpecMngDao.insertFcltyFile", "gamConsFcltySpecMngDao.updateFcltyFile", "gamConsFcltySpecMngDao.deleteFcltyFile");
    }

    public Integer selectFcltyPhotoMaxSeq(Map vo) throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamConsFcltySpecMngDao.selectFcltyPhotoMaxSeq", vo);
    }
    
    
    
    /**
	 * 제원관리 층수 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List selectFcltyinfo9List(GamConsFcltySpecMngVO vo) throws Exception{
		return list("gamConsFcltySpecMngDao.selectFcltyinfo9List", vo);
	}


	/**
	 * 제원관리 층수 목록 총 수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int selectFcltyinfo9ListTotCnt(GamConsFcltySpecMngVO vo) throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamConsFcltySpecMngDao.selectFcltyinfo9ListTotCnt", vo);
    }

}