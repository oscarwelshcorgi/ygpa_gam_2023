package egovframework.rte.ygpa.gam.asset.rent.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentMngtVO;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentSttusInqireVO;

/**
 * @Class Name : GamAssetRentSttusInqireDao.java
 * @Description : 자산임대현황조회 DAO Class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-22
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamAssetRentSttusInqireDao")
public class GamAssetRentSttusInqireDao extends YGPAAbstractDAO {
	
	/**
	 * 자산임대관리 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록
	 * @exception Exception
	 */
    public List selectAssetRentList(GamAssetRentSttusInqireVO searchVO) throws Exception {
        return list("gamAssetRentSttusInqireDao.selectAssetRentList_D", searchVO);
    }

    /**
	 * 자산임대관리 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectAssetRentListTotCnt(GamAssetRentSttusInqireVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamAssetRentSttusInqireDao.selectAssetRentListTotCnt_S", searchVO);
    }
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록
	 * @exception Exception
	 */
	public GamAssetRentSttusInqireVO selectAssetRentSum(GamAssetRentSttusInqireVO searchVO) throws Exception {
		return (GamAssetRentSttusInqireVO) selectByPk("gamAssetRentSttusInqireDao.selectAssetRentSum_S", searchVO);
	}
    
	/**
	 * 자산임대관리 상세목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록
	 * @exception Exception
	 */
    public List selectAssetRentDetailList(GamAssetRentSttusInqireVO vo) throws Exception {
        return list("gamAssetRentSttusInqireDao.selectAssetRentDetailList_D", vo);
    }

    /**
	 * 자산임대관리 상세목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectAssetRentDetailListTotCnt(GamAssetRentSttusInqireVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamAssetRentSttusInqireDao.selectAssetRentDetailListTotCnt_S", vo);
    }
    
    /**
	 * 파일 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록
	 * @exception Exception
	 */
    public List selectAssetRentFileList(GamAssetRentSttusInqireVO searchVO) throws Exception {
        return list("gamAssetRentSttusInqireDao.selectAssetRentFileList_D", searchVO);
    }

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectAssetRentFileListTotCnt(GamAssetRentSttusInqireVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamAssetRentSttusInqireDao.selectAssetRentFileListTotCnt_S", searchVO);
    }
    
    /**
	 * 파일을 등록한다.
	 * @param vo GamAssetRentVO
	 * @exception Exception
	 */
	public void insertAssetRentFile(GamAssetRentSttusInqireVO vo){
		insert("gamAssetRentSttusInqireDao.insertAssetRentFile_S", vo);
	}
    
	/**
	 * 파일을 삭제한다.(1row)
	 * @param vo GamAssetRentVO
	 * @exception Exception
	 */
	public void deleteAssetRentPhotoSingle(GamAssetRentSttusInqireVO vo){
		delete("gamAssetRentSttusInqireDao.deleteAssetRentPhotoSingle_S", vo);
	}
	
}
