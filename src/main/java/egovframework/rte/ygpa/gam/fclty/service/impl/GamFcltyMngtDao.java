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
/**
 *
 * @author EUNSUNGJ
 * @since 2014. 4. 23.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 4. 23.		EUNSUNGJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Repository("gamFcltyMngtDao")
public class GamFcltyMngtDao extends YGPAAbstractDAO{

	/**
	 * 시설관리 저장
	 * @param fcltyMngtList
	 */
	public String insertFclty(Map form){
		insert("gamFcltyMngtDao.insertFclty", form);

		return (String)form.get("maxFcltySeq");
	}


	/**
	 * 시설관리 파일 저장
	 * @param fcltyMngtList
	 */
	public void insertFcltyFile(Map fileList){
		insert("gamFcltyMngtDao.insertFcltyFile", fileList);
	}


    /**
     * 시설관리 수정화면
     * @param vo
     */
    public void updateFclty(Map form){
        update("gamFcltyMngtDao.updateFclty",form);
    }


    /**
     * 시설관리 파일 저장
     * @param vo
     */
    public void updateFcltyFile(Map fileList){
    	update("gamFcltyMngtDao.updateFcltyFile",fileList);
    }


    /**
     * 시설관리 파일 저장
     * @param vo
     */
    public void deleteFcltyFile(Map fileList){
    	update("gamFcltyMngtDao.deleteFcltyFile",fileList);
    }


	/**
	 * 시설관리 목록 조회
	 * @param vo ComDefaultVO
	 * @return List
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComDefaultVO> selectFcltyMngtList(GamFcltyManageVO vo) throws Exception{
		return list("gamFcltyMngtDao.selectFcltyMngtList", vo);
	}


	/**
	 * 시설관리 총 수
	 * @param ComDefaultVO vo
	 * @return int
	 * @exception Exception
	 */
    public int selectFcltyMngtListTotCnt(GamFcltyManageVO vo) throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamFcltyMngtDao.selectFcltyMngtListTotCnt", vo);
    }


    /**
     * 시설관리 파일 목록 조회
     * @param vo ComDefaultVO
     * @return List
     * @exception Exception
     */
    @SuppressWarnings("unchecked")
    public List<ComDefaultVO> selectFcltyMngtPhotoList(GamFcltyManageVO vo) throws Exception{
    	return list("gamFcltyMngtDao.selectFcltyMngtPhotoList", vo);
    }


    /**
     * 시설관리 파일 총 수
     * @param ComDefaultVO vo
     * @return int
     * @exception Exception
     */
    public int selectFcltyMngtPhotoListTotCnt(GamFcltyManageVO vo) throws Exception {
    	return (Integer)getSqlMapClientTemplate().queryForObject("gamFcltyMngtDao.selectFcltyMngtPhotoListTotCnt", vo);
    }


    /**
     * 시설관리 시퀀스
     * @return int
     * @exception Exception
     */
    public String insertFcltyGetSeq() throws Exception {
    	return (String)getSqlMapClientTemplate().queryForObject("gamFcltyMngtDao.insertFcltyGetSeq");
    }


    /**
     * 시설관리 상세화면
     * @param vo
     * @return GamFcltyManageVO
     */
    public EgovMap fcltyMngSelectView(Map vo) throws Exception{
        return (EgovMap) selectByPk("gamFcltyMngtDao.fcltyMngSelectView", vo);
    }


    /**
     * 시설관리 삭제화면
     * @param vo
     */
    public void deleteFclty(Map vo) throws Exception{
    	delete("gamFcltyMngtDao.deleteFclty", vo);
    }

    public List mergeFcltyPhoto(Map vo) throws Exception{
		return this.merge(vo, "gamFcltyMngtDao.insertFcltyFile", "gamFcltyMngtDao.updateFcltyFile", "gamFcltyMngtDao.deleteFcltyFile");
    }

    public Integer selectFcltyPhotoMaxSeq(Map vo) throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamFcltyMngtDao.selectFcltyPhotoMaxSeq", vo);
    }

}