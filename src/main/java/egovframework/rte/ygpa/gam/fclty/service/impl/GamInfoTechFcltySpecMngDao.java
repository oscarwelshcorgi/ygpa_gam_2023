/**
 * 
 */
package egovframework.rte.ygpa.gam.fclty.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.fclty.service.GamInfoTechFcltySpecMngVO;

/**
 * 
 * @author 김종민
 * @since 2014. 11. 17.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 11. 17.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Repository("gamInfoTechFcltySpecMngDao")
public class GamInfoTechFcltySpecMngDao extends YGPAAbstractDAO {
	/**
	 * 정보통신시설재원관리 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */	
	public List<?> selectInfoTechFcltySpecMngList(GamInfoTechFcltySpecMngVO searchVO) throws Exception {
		return list("gamInfoTechFcltySpecMngDao.selectInfoTechFcltySpecMngList_D", searchVO);
	}
	
	/**
	 * 정보통신시설재원관리 목록 총수 조회
	 * @param vo
	 * @return int
	 * @throws Exception
	 */		
	public int selectInfoTechFcltySpecMngListTotCnt(GamInfoTechFcltySpecMngVO searchVO) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamInfoTechFcltySpecMngDao.selectInfoTechFcltySpecMngListTotCnt_S", searchVO);
	}

	
	/**
	 * 정보통신시설재원관리 데이터 조회
	 * @param vo
	 * @return egovMap
	 * @throws Exception
	 */		
	public EgovMap selectInfoTechFcltySpecMngDetail(Map<?, ?> searchVO) throws Exception {
		return (EgovMap)selectByPk("gamInfoTechFcltySpecMngDao.selectInfoTechFcltySpecMngDetail_S", searchVO);
	}
	
	/**
	 * 정보통신시설재원관리 데이터를 삽입한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */		
	public void insertInfoTechFcltySpecMngDetail(Map<?, ?> vo) throws Exception {
		insert("gamInfoTechFcltySpecMngDao.insertInfoTechFcltySpecMngDetail_S", vo);
	}
	
	/**
	 * 정보통신시설재원관리 데이터를 수정한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */		
	public void updateInfoTechFcltySpecMngDetail(Map<?, ?> vo) throws Exception {
		update("gamInfoTechFcltySpecMngDao.updateInfoTechFcltySpecMngDetail_S", vo);
	}
	
	/**
	 * 정보통신시설재원관리 데이터를 삭제한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */		
	public void deleteInfoTechFcltySpecMngDetail(Map<?, ?> vo) throws Exception {
		delete("gamInfoTechFcltySpecMngDao.deleteInfoTechFcltySpecMngDetail_S", vo);
	}
	

	/**
	 * 정보통신시설재원관리 첨부파일 목록을 가져온다.
	 * @param vo
	 * @return list 
	 * @throws Exception
	 */		
	public List<?> selectInfoTechFcltySpecFileList(GamInfoTechFcltySpecMngVO searchVO) throws Exception {
		return list("gamInfoTechFcltySpecMngDao.selectInfoTechFcltySpecFileList_D", searchVO);
	}

	/**
	 * 정보통신시설재원관리 첨부파일 목록 총수를 가져온다.
	 * @param vo
	 * @return list 
	 * @throws Exception
	 */		
	public int selectInfoTechFcltySpecFileListTotCnt(GamInfoTechFcltySpecMngVO searchVO) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamInfoTechFcltySpecMngDao.selectInfoTechFcltySpecFileListTotCnt_S", searchVO);
	}

	/**
	 * 정보통신시설재원관리 첨부파일 목록을 삭제한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */			
	public void deleteInfoTechFcltySpecFileList(Map<?, ?> vo) throws Exception {
		delete("gamInfoTechFcltySpecMngDao.deleteInfoTechFcltySpecFileList_S", vo);
	}

	/**
	 * 정보통신시설재원관리 첨부파일정보를 저장한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */			
    public void insertFcltyFile(Map<String, String> vo) throws Exception{
    	insert("gamInfoTechFcltySpecMngDao.insertInfoTechFcltySpecFileDetail_S", vo);
    }
	
	/**
	 * 정보통신시설재원관리 첨부파일 목록을 병합하여 저장한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */			
    public void mergeFcltyFile(Map<String, Object> vo) throws Exception{
    	this.merge(vo, "gamInfoTechFcltySpecMngDao.insertInfoTechFcltySpecFileDetail_S", "gamInfoTechFcltySpecMngDao.updateInfoTechFcltySpecFileDetail_S", "gamInfoTechFcltySpecMngDao.deleteInfoTechFcltySpecFileDetail_S");
    }
	
}
