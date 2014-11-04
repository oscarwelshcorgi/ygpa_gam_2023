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
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.fclty.service.GamFcltyManageVO;

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

@Repository("gamFcltySpecMngDao")
public class GamFcltySpecMngDao extends YGPAAbstractDAO{

	/**
	 * 시설관리 저장
	 * @param fcltyMngtList
	 */
	public String insertFclty(Map form){
		insert("gamFcltySpecMngDao.insertFclty", form);

		return (String)form.get("maxFcltySeq");
	}


	/**
	 * 시설관리 파일 저장
	 * @param fcltyMngtList
	 */
	public void insertFcltyFile(Map fileList){
		insert("gamFcltySpecMngDao.insertFcltyFile", fileList);
	}


    /**
     * 시설관리 수정화면
     * @param vo
     */
    public void updateFclty(Map form){
        update("gamFcltySpecMngDao.updateFclty",form);
    }


    /**
     * 시설관리 파일 저장
     * @param vo
     */
    public void updateFcltyFile(Map fileList){
    	update("gamFcltySpecMngDao.updateFcltyFile",fileList);
    }


    /**
     * 시설관리 파일 저장
     * @param vo
     */
    public void deleteFcltyFile(Map fileList){
    	update("gamFcltySpecMngDao.deleteFcltyFile",fileList);
    }


	/**
	 * 시설관리 목록 조회
	 * @param vo ComDefaultVO
	 * @return List
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComDefaultVO> selectFcltySpecMngList(GamFcltyManageVO vo) throws Exception{
		return list("gamFcltySpecMngDao.selectFcltySpecMngList", vo);
	}


	/**
	 * 시설관리 총 수
	 * @param ComDefaultVO vo
	 * @return int
	 * @exception Exception
	 */
    public int selectFcltySpecMngListTotCnt(GamFcltyManageVO vo) throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamFcltySpecMngDao.selectFcltySpecMngListTotCnt", vo);
    }


    /**
     * 시설관리 파일 목록 조회
     * @param vo ComDefaultVO
     * @return List
     * @exception Exception
     */
    @SuppressWarnings("unchecked")
    public List<ComDefaultVO> selectFcltySpecMngPhotoList(GamFcltyManageVO vo) throws Exception{
    	return list("gamFcltySpecMngDao.selectFcltySpecMngPhotoList", vo);
    }


    /**
     * 시설관리 파일 총 수
     * @param ComDefaultVO vo
     * @return int
     * @exception Exception
     */
    public int selectFcltySpecMngPhotoListTotCnt(GamFcltyManageVO vo) throws Exception {
    	return (Integer)getSqlMapClientTemplate().queryForObject("gamFcltySpecMngDao.selectFcltySpecMngPhotoListTotCnt", vo);
    }


    /**
     * 시설관리 시퀀스
     * @return int
     * @exception Exception
     */
    public String insertFcltyGetSeq() throws Exception {
    	return (String)getSqlMapClientTemplate().queryForObject("gamFcltySpecMngDao.insertFcltyGetSeq");
    }


    /**
     * 시설관리 상세화면
     * @param vo
     * @return GamFcltyManageVO
     */
    public EgovMap fcltyMngSelectView(Map vo) throws Exception{
        return (EgovMap) selectByPk("gamFcltySpecMngDao.fcltyMngSelectView", vo);
    }


    /**
     * 시설관리 삭제화면
     * @param vo
     */
    public void deleteFclty(Map vo) throws Exception{
    	delete("gamFcltySpecMngDao.deleteFclty", vo);
    }

    public List mergeFcltyPhoto(Map vo) throws Exception{
		return this.merge(vo, "gamFcltySpecMngDao.insertFcltyFile", "gamFcltySpecMngDao.updateFcltyFile", "gamFcltySpecMngDao.deleteFcltyFile");
    }

    public Integer selectFcltyPhotoMaxSeq(Map vo) throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamFcltySpecMngDao.selectFcltyPhotoMaxSeq", vo);
    }

}