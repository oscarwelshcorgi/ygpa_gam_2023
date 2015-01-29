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
	public void insertFcltySpec(Map<?,?> form){
		insert("gamConsFcltySpecMngDao.insertFcltySpec", form);
	}


	/**
	 * 시설관리 파일 저장
	 * @param fcltyMngtList
	 */
	public void insertFcltyFile(Map<?,?> fileList){
		insert("gamConsFcltySpecMngDao.insertFcltyFile", fileList);
	}


    /**
     * 시설관리 수정화면
     * @param vo
     */
    public void updateFcltySpec(Map<?,?> form){
        update("gamConsFcltySpecMngDao.updateFcltySpec",form);
    }


    /**
     * 시설관리 파일 수정
     * @param vo
     */
    public void updateFcltyFile(Map<?,?> fileList){
    	update("gamConsFcltySpecMngDao.updateFcltyFile",fileList);
    }


    /**
     * 시설관리 파일 삭제
     * @param vo
     */
    public void deleteFcltyFile(Map<?,?> fileList){
    	 delete("gamConsFcltySpecMngDao.deleteFcltyFile",fileList);
    }
    
    
    /**
     * 시설관리 전체파일 삭제
     * @param vo
     */
    public void deleteFcltyTotalFile(Map<?,?> fileList){
    	delete("gamConsFcltySpecMngDao.deleteFcltyTotalFile",fileList);
    }


	/**
	 * 시설관리 목록 조회
	 * @param vo ComDefaultVO
	 * @return List
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComDefaultVO> selectFcltySpecMngList(GamConsFcltySpecMngVO vo) throws Exception{
		return list("gamConsFcltySpecMngDao.selectFcltySpecMngList_D", vo);
	}


	/**
	 * 시설관리 총 수
	 * @param ComDefaultVO vo
	 * @return int
	 * @exception Exception
	 */
    public int selectFcltySpecMngListTotCnt(GamConsFcltySpecMngVO vo) throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamConsFcltySpecMngDao.selectFcltySpecMngListTotCnt_S", vo);
    }


    /**
     * 시설관리 파일 목록 조회
     * @param vo ComDefaultVO
     * @return List
     * @exception Exception
     */
    @SuppressWarnings("unchecked")
    public List<ComDefaultVO> selectFcltySpecMngFileList(GamConsFcltySpecMngVO vo) throws Exception{
    	return list("gamConsFcltySpecMngDao.selectFcltySpecMngFileList_D", vo);
    }


    /**
     * 시설관리 파일 총 수
     * @param ComDefaultVO vo
     * @return int
     * @exception Exception
     */
    public int selectFcltySpecMngFileListTotCnt(GamConsFcltySpecMngVO vo) throws Exception {
    	return (Integer)getSqlMapClientTemplate().queryForObject("gamConsFcltySpecMngDao.selectFcltySpecMngFileListTotCnt_S", vo);
    }



    /**
     * 시설관리 상세화면
     * @param vo
     * @return GamFcltyManageVO
     */
    public EgovMap fcltyMngSelectView(Map<?,?> vo) throws Exception{
        return (EgovMap) selectByPk("gamConsFcltySpecMngDao.fcltyMngSelectView_S", vo);
    }
    
    /**
     * 시설관리 상세화면(제원)
     * @param vo
     * @return GamFcltyManageVO
     */
    public EgovMap fcltySpecMngSelectView(Map<?,?> vo) throws Exception{
        return (EgovMap) selectByPk("gamConsFcltySpecMngDao.fcltySpecMngSelectView_S", vo);
    }


    /**
     * 시설관리 삭제화면
     * @param vo
     */
    public void deleteFcltySpec(Map<?,?> vo) throws Exception{
    	delete("gamConsFcltySpecMngDao.deleteFcltySpec", vo);
    }

    public List<?> mergeFcltyFile(Map<String,Object> vo) throws Exception{
		return this.merge(vo, "gamConsFcltySpecMngDao.insertFcltyFile", "gamConsFcltySpecMngDao.updateFcltyFile", "gamConsFcltySpecMngDao.deleteFcltyFile");
    }
    
    
}