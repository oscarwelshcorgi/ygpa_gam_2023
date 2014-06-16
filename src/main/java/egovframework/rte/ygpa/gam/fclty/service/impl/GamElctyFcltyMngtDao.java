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
@Repository("gamElctyFcltyMngtDao")
public class GamElctyFcltyMngtDao extends YGPAAbstractDAO{

	/**
	 * 시설관리 저장
	 * @param fcltyMngtList
	 */
	public String insertElctyFclty(Map form){
		insert("gamElctyFcltyMngtDao.insertElctyFclty", form);

		return (String)form.get("maxFcltySeq");
	}


	/**
	 * 시설관리 파일 저장
	 * @param fcltyMngtList
	 */
	public void insertElctyFcltyFile(Map fileList){
		insert("gamElctyFcltyMngtDao.insertElctyFcltyFile", fileList);
	}


    /**
     * 시설관리 수정화면
     * @param vo
     */
    public void updateElctyFclty(Map form){
        update("gamElctyFcltyMngtDao.updateElctyFclty",form);
    }


    /**
     * 시설관리 파일 저장
     * @param vo
     */
    public void updateElctyFcltyFile(Map fileList){
    	update("gamElctyFcltyMngtDao.updateElctyFcltyFile",fileList);
    }


    /**
     * 시설관리 파일 저장
     * @param vo
     */
    public void deleteElctyFcltyFile(Map fileList){
    	update("gamElctyFcltyMngtDao.deleteElctyFcltyFile",fileList);
    }


	/**
	 * 시설관리 목록 조회
	 * @param vo ComDefaultVO
	 * @return List
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComDefaultVO> selectElctyFcltyMngtList(GamFcltyManageVO vo) throws Exception{
		return list("gamElctyFcltyMngtDao.selectElctyFcltyMngtList", vo);
	}


	/**
	 * 시설관리 총 수
	 * @param ComDefaultVO vo
	 * @return int
	 * @exception Exception
	 */
    public int selectElctyFcltyMngtListTotCnt(GamFcltyManageVO vo) throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamElctyFcltyMngtDao.selectElctyFcltyMngtListTotCnt", vo);
    }


    /**
     * 시설관리 파일 목록 조회
     * @param vo ComDefaultVO
     * @return List
     * @exception Exception
     */
    @SuppressWarnings("unchecked")
    public List<ComDefaultVO> selectElctyFcltyMngtPhotoList(GamFcltyManageVO vo) throws Exception{
    	return list("gamElctyFcltyMngtDao.selectElctyFcltyMngtPhotoList", vo);
    }


    /**
     * 시설관리 파일 총 수
     * @param ComDefaultVO vo
     * @return int
     * @exception Exception
     */
    public int selectElctyFcltyMngtPhotoListTotCnt(GamFcltyManageVO vo) throws Exception {
    	return (Integer)getSqlMapClientTemplate().queryForObject("gamElctyFcltyMngtDao.selectElctyFcltyMngtPhotoListTotCnt", vo);
    }


    /**
     * 시설관리 시퀀스
     * @return int
     * @exception Exception
     */
    public String insertElctyFcltyGetSeq() throws Exception {
    	return (String)getSqlMapClientTemplate().queryForObject("gamElctyFcltyMngtDao.insertElctyFcltyGetSeq");
    }


    /**
     * 시설관리 상세화면
     * @param vo
     * @return GamFcltyManageVO
     */
    public EgovMap ElctyfcltyMngSelectView(Map vo) throws Exception{
        return (EgovMap) selectByPk("gamElctyFcltyMngtDao.ElctyfcltyMngSelectView", vo);
    }


    /**
     * 시설관리 삭제화면
     * @param vo
     */
    public void deleteFclty(Map vo) throws Exception{
    	delete("gamElctyFcltyMngtDao.deleteElctyFclty", vo);
    }

    public List mergeFcltyPhoto(Map vo) throws Exception{
		return this.merge(vo, "gamElctyFcltyMngtDao.insertElctyFcltyFile", "gamElctyFcltyMngtDao.updateElctyFcltyFile", "gamElctyFcltyMngtDao.deleteElctyFcltyFile");
    }

    public Integer selectElctyFcltyPhotoMaxSeq(Map vo) throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamElctyFcltyMngtDao.selectElctyFcltyPhotoMaxSeq", vo);
    }

}