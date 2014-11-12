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
	public void insertFcltySpec(Map form){
		insert("gamConsFcltySpecMngDao.insertFcltySpec", form);
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
    public void updateFcltySpec(Map form){
        update("gamConsFcltySpecMngDao.updateFcltySpec",form);
    }


    /**
     * 시설관리 파일 저장
     * @param vo
     */
    public void updateFcltyFile(Map fileList){
    	update("gamConsFcltySpecMngDao.updateFcltyFile",fileList);
    }


    /**
     * 시설관리 파일 삭제
     * @param vo
     */
    public void deleteFcltyFile(Map fileList){
    	update("gamConsFcltySpecMngDao.deleteFcltyFile",fileList);
    }
    
    
    /**
     * 시설관리 전체파일 삭제
     * @param vo
     */
    public void deleteFcltyTotalFile(Map fileList){
    	update("gamConsFcltySpecMngDao.deleteFcltyTotalFile",fileList);
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
    public List<ComDefaultVO> selectFcltySpecMngFileList(GamConsFcltySpecMngVO vo) throws Exception{
    	return list("gamConsFcltySpecMngDao.selectFcltySpecMngFileList", vo);
    }


    /**
     * 시설관리 파일 총 수
     * @param ComDefaultVO vo
     * @return int
     * @exception Exception
     */
    public int selectFcltySpecMngFileListTotCnt(GamConsFcltySpecMngVO vo) throws Exception {
    	return (Integer)getSqlMapClientTemplate().queryForObject("gamConsFcltySpecMngDao.selectFcltySpecMngFileListTotCnt", vo);
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
    public void deleteFcltySpec(Map vo) throws Exception{
    	delete("gamConsFcltySpecMngDao.deleteFcltySpec", vo);
    }

    public List mergeFcltyFile(Map vo) throws Exception{
		return this.merge(vo, "gamConsFcltySpecMngDao.insertFcltyFile", "gamConsFcltySpecMngDao.updateFcltyFile", "gamConsFcltySpecMngDao.deleteFcltyFile");
    }

    public Integer selectFcltyFileMaxSeq(Map vo) throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamConsFcltySpecMngDao.selectFcltyFileMaxSeq", vo);
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
	
	
	/**
	 * 시설 층수 정보 삭제
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public void deleteFcltyFloorSpecData(Map vo) throws Exception{
		delete("gamConsFcltySpecMngDao.deleteFcltyFloorSpecData", vo);
	}
	
	
	/**
	 * 시설 층수 정보 입력
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public void insertFcltyFloorSpecList(Map vo) throws Exception{
		insert("gamConsFcltySpecMngDao.insertFcltyFloorSpecList", vo);
	}

}