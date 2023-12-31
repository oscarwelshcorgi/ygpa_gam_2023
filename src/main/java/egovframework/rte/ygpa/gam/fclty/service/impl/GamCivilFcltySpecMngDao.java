/**
 * 
 */
package egovframework.rte.ygpa.gam.fclty.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.fclty.service.GamCivilFcltySpecMngVO;

/**
 * 
 * @author 김종민
 * @since 2014. 11. 10.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 11. 10.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Repository("gamCivilFcltySpecMngDao")
public class GamCivilFcltySpecMngDao extends YGPAAbstractDAO {
	/**
	 * 토목시설재원관리 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */	
	public List<?> selectCivilFcltySpecMngList(GamCivilFcltySpecMngVO searchVO) throws Exception {
		return list("gamCivilFcltySpecMngDao.selectCivilFcltySpecMngList_D", searchVO);
	}
	
	/**
	 * 토목시설재원관리 목록 총수 조회
	 * @param vo
	 * @return int
	 * @throws Exception
	 */		
	public int selectCivilFcltySpecMngListTotCnt(GamCivilFcltySpecMngVO searchVO) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamCivilFcltySpecMngDao.selectCivilFcltySpecMngListTotCnt_S", searchVO);
	}

	
	/**
	 * 토목시설재원관리 데이터 조회
	 * @param vo
	 * @return egovMap
	 * @throws Exception
	 */		
	public EgovMap selectCivilFcltySpecMngDetail(Map<?, ?> searchVO) throws Exception {
		return (EgovMap)selectByPk("gamCivilFcltySpecMngDao.selectCivilFcltySpecMngDetail_S", searchVO);
	}
	
	/**
	 * 토목시설재원관리 데이터를 삽입한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */		
	public void insertCivilFcltySpecMngDetail(Map<?, ?> vo) throws Exception {
		insert("gamCivilFcltySpecMngDao.insertCivilFcltySpecMngDetail_S", vo);
	}
	
	/**
	 * 토목시설재원관리 데이터를 수정한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */		
	public void updateCivilFcltySpecMngDetail(Map<?, ?> vo) throws Exception {
		update("gamCivilFcltySpecMngDao.updateCivilFcltySpecMngDetail_S", vo);
	}
	
	/**
	 * 토목시설재원관리 데이터를 삭제한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */		
	public void deleteCivilFcltySpecMngDetail(Map<?, ?> vo) throws Exception {
		delete("gamCivilFcltySpecMngDao.deleteCivilFcltySpecMngDetail_S", vo);
	}
	

	/**
	 * 토목시설재원관리 첨부파일 목록을 가져온다.
	 * @param vo
	 * @return list 
	 * @throws Exception
	 */		
	public List<?> selectCivilFcltySpecFileList(GamCivilFcltySpecMngVO searchVO) throws Exception {
		return list("gamCivilFcltySpecMngDao.selectCivilFcltySpecFileList_D", searchVO);
	}

	/**
	 * 토목시설재원관리 첨부파일 목록 총수를 가져온다.
	 * @param vo
	 * @return list 
	 * @throws Exception
	 */		
	public int selectCivilFcltySpecFileListTotCnt(GamCivilFcltySpecMngVO searchVO) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamCivilFcltySpecMngDao.selectCivilFcltySpecFileListTotCnt_S", searchVO);
	}

	/**
	 * 토목시설재원관리 첨부파일 목록을 삭제한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */			
	public void deleteCivilFcltySpecFileList(Map<?, ?> vo) throws Exception {
		delete("gamCivilFcltySpecMngDao.deleteCivilFcltySpecFileList_S", vo);
	}
	
	/**
	 * 토목시설재원관리 첨부파일정보를 저장한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */			
    public void insertFcltyFile(Map<String, String> vo) throws Exception{
		insert("gamCivilFcltySpecMngDao.insertCivilFcltySpecFileDetail_S", vo);
    }

	/**
	 * 토목시설재원관리 첨부파일 목록을 병합하여 저장한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */			
    public void mergeFcltyFile(Map<String, Object> vo) throws Exception{
		this.merge(vo, "gamCivilFcltySpecMngDao.insertCivilFcltySpecFileDetail_S", "gamCivilFcltySpecMngDao.updateCivilFcltySpecFileDetail_S", "gamCivilFcltySpecMngDao.deleteCivilFcltySpecFileDetail_S");
    }
	
}
