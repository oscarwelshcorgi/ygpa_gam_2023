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
 *  2014. 6. 17.		sj		수정 
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
@Repository("gamInfoTechFcltyMngtDao")
public class GamInfoTechFcltyMngtDao extends YGPAAbstractDAO{

	/**
	 * 정보통신 시설관리 저장
	 * @param fcltyMngtList
	 */
	public String insertInfoTechFclty(Map form){
		insert("gamInfoTechFcltyMngtDao.insertInfoTechFclty", form);

		return (String)form.get("maxFcltySeq");
	}


	/**
	 * 정보통신 시설관리 파일 저장
	 * @param fcltyMngtList
	 */
	public void insertInfoTechFcltyFile(Map fileList){
		insert("gamInfoTechFcltyMngtDao.insertInfoTechFcltyFile", fileList);
	}


    /**
     * 정보통신 시설관리 수정화면
     * @param vo
     */
    public void updateInfoTechFclty(Map form){
        update("gamInfoTechFcltyMngtDao.updateInfoTechFclty",form);
    }


    /**
     * 정보통신 시설관리 파일 저장
     * @param vo
     */
    public void updateInfoTechFcltyFile(Map fileList){
    	update("gamInfoTechFcltyMngtDao.updateInfoTechFcltyFile",fileList);
    }


    /**
     * 정보통신 시설관리 파일 저장
     * @param vo
     */
    public void deleteInfoTechFcltyFile(Map fileList){
    	update("gamInfoTechFcltyMngtDao.deleteInfoTechFcltyFile",fileList);
    }


	/**
	 * 정보통신 시설관리 목록 조회
	 * @param vo ComDefaultVO
	 * @return List
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComDefaultVO> selectInfoTechFcltyMngtList(GamFcltyManageVO vo) throws Exception{
		return list("gamInfoTechFcltyMngtDao.selectInfoTechFcltyMngtList", vo);
	}


	/**
	 * 정보통신 시설관리 총 수
	 * @param ComDefaultVO vo
	 * @return int
	 * @exception Exception
	 */
    public int selectInfoTechFcltyMngtListTotCnt(GamFcltyManageVO vo) throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamInfoTechFcltyMngtDao.selectInfoTechFcltyMngtListTotCnt", vo);
    }


    /**
     * 정보통신 시설관리 파일 목록 조회
     * @param vo ComDefaultVO
     * @return List
     * @exception Exception
     */
    @SuppressWarnings("unchecked")
    public List<ComDefaultVO> selectInfoTechFcltyMngtPhotoList(GamFcltyManageVO vo) throws Exception{
    	return list("gamInfoTechFcltyMngtDao.selectInfoTechFcltyMngtPhotoList", vo);
    }


    /**
     * 정보통신 시설관리 파일 총 수
     * @param ComDefaultVO vo
     * @return int
     * @exception Exception
     */
    public int selectInfoTechFcltyMngtPhotoListTotCnt(GamFcltyManageVO vo) throws Exception {
    	return (Integer)getSqlMapClientTemplate().queryForObject("gamInfoTechFcltyMngtDao.selectInfoTechFcltyMngtPhotoListTotCnt", vo);
    }


    /**
     * 정보통신 시설관리 시퀀스
     * @return int
     * @exception Exception
     */
    public String insertInfoTechFcltyGetSeq() throws Exception {
    	return (String)getSqlMapClientTemplate().queryForObject("gamInfoTechFcltyMngtDao.insertInfoTechFcltyGetSeq");
    }


    /**
     * 정보통신 시설관리 상세화면
     * @param vo
     * @return GamFcltyManageVO
     */
    public EgovMap InfoTechfcltyMngSelectView(Map vo) throws Exception{
        return (EgovMap) selectByPk("gamInfoTechFcltyMngtDao.InfoTechfcltyMngSelectView", vo);
    }


    /**
     * 정보통신 시설관리 삭제화면
     * @param vo
     */
    public void deleteInfoTechFclty(Map vo) throws Exception{
    	delete("gamInfoTechFcltyMngtDao.deleteInfoTechFclty", vo);
    }

    public List mergeInfoTechFcltyPhoto(Map vo) throws Exception{
		return this.merge(vo, "gamInfoTechFcltyMngtDao.insertInfoTechFcltyFile", "gamInfoTechFcltyMngtDao.updateInfoTechFcltyFile", "gamInfoTechFcltyMngtDao.deleteInfoTechFcltyFile");
    }

    public Integer selectInfoTechFcltyPhotoMaxSeq(Map vo) throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamInfoTechFcltyMngtDao.selectInfoTechFcltyPhotoMaxSeq", vo);
    }

}