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
 *  2014. 6. 25.		sj		최초 생성
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
@Repository("gamCivilFcltyMngtDao")
public class GamCivilFcltyMngtDao extends YGPAAbstractDAO{

	/**
	 * 시설관리 저장
	 * @param fcltyMngtList
	 */
	public String insertCivilFclty(Map form){
		insert("gamCivilFcltyMngtDao.insertCivilFclty", form);

		return (String)form.get("maxFcltySeq");
	}


	/**
	 * 시설관리 파일 저장
	 * @param fcltyMngtList
	 */
	public void insertCivilFcltyFile(Map fileList){
		insert("gamCivilFcltyMngtDao.insertCivilFcltyFile", fileList);
	}


    /**
     * 시설관리 수정화면
     * @param vo
     */
    public void updateCivilFclty(Map form){
        update("gamCivilFcltyMngtDao.updateCivilFclty",form);
    }


    /**
     * 시설관리 파일 저장
     * @param vo
     */
    public void updateCivilFcltyFile(Map fileList){
    	update("gamCivilFcltyMngtDao.updateCivilFcltyFile",fileList);
    }


    /**
     * 시설관리 파일 저장
     * @param vo
     */
    public void deleteCivilFcltyFile(Map fileList){
    	update("gamCivilFcltyMngtDao.deleteCivilFcltyFile",fileList);
    }


	/**
	 * 시설관리 목록 조회
	 * @param vo ComDefaultVO
	 * @return List
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComDefaultVO> selectCivilFcltyMngtList(GamFcltyManageVO vo) throws Exception{
		return list("gamCivilFcltyMngtDao.selectCivilFcltyMngtList", vo);
	}


	/**
	 * 시설관리 총 수
	 * @param ComDefaultVO vo
	 * @return int
	 * @exception Exception
	 */
    public int selectCivilFcltyMngtListTotCnt(GamFcltyManageVO vo) throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamCivilFcltyMngtDao.selectCivilFcltyMngtListTotCnt", vo);
    }


    /**
     * 시설관리 파일 목록 조회
     * @param vo ComDefaultVO
     * @return List
     * @exception Exception
     */
    @SuppressWarnings("unchecked")
    public List<ComDefaultVO> selectCivilFcltyMngtPhotoList(GamFcltyManageVO vo) throws Exception{
    	return list("gamCivilFcltyMngtDao.selectCivilFcltyMngtPhotoList", vo);
    }


    /**
     * 시설관리 파일 총 수
     * @param ComDefaultVO vo
     * @return int
     * @exception Exception
     */
    public int selectCivilFcltyMngtPhotoListTotCnt(GamFcltyManageVO vo) throws Exception {
    	return (Integer)getSqlMapClientTemplate().queryForObject("gamCivilFcltyMngtDao.selectCivilFcltyMngtPhotoListTotCnt", vo);
    }


    /**
     * 시설관리 시퀀스
     * @return int
     * @exception Exception
     */
    public String insertCivilFcltyGetSeq() throws Exception {
    	return (String)getSqlMapClientTemplate().queryForObject("gamCivilFcltyMngtDao.insertCivilFcltyGetSeq");
    }


    /**
     * 시설관리 상세화면
     * @param vo
     * @return GamFcltyManageVO
     */
    public EgovMap CivilfcltyMngSelectView(Map vo) throws Exception{
        return (EgovMap) selectByPk("gamCivilFcltyMngtDao.CivilfcltyMngSelectView", vo);
    }


    /**
     * 시설관리 삭제화면
     * @param vo
     */
    public void deleteCivilFclty(Map vo) throws Exception{
    	delete("gamCivilFcltyMngtDao.deleteCivilFclty", vo);
    }

    public List mergeCivilFcltyPhoto(Map vo) throws Exception{
		return this.merge(vo, "gamCivilFcltyMngtDao.insertCivilFcltyFile", "gamCivilFcltyMngtDao.updateCivilFcltyFile", "gamCivilFcltyMngtDao.deleteCivilFcltyFile");
    }

    public Integer selectCivilFcltyPhotoMaxSeq(Map vo) throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamCivilFcltyMngtDao.selectCivilFcltyPhotoMaxSeq", vo);
    }

}