/**
 * 
 */
package egovframework.rte.ygpa.gam.fclty.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.fclty.service.GamElctyFcltySpecMngVO;

/**
 * 
 * @author HNJ
 * @since 2014. 11. 19.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 11. 19.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Repository("gamElctyFcltySpecMngDao")
public class GamElctyFcltySpecMngDao extends YGPAAbstractDAO {
	/**
	 * 전기시설재원관리 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */	
	public List selectElctyFcltySpecMngList(GamElctyFcltySpecMngVO searchVO) throws Exception {
		return list("gamElctyFcltySpecMngDao.selectElctyFcltySpecMngList", searchVO);
	}
	
	/**
	 * 전기시설재원관리 목록 총수 조회
	 * @param vo
	 * @return int
	 * @throws Exception
	 */		
	public int selectElctyFcltySpecMngListTotCnt(GamElctyFcltySpecMngVO searchVO) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamElctyFcltySpecMngDao.selectElctyFcltySpecMngListTotCnt", searchVO);
	}

	
	/**
	 * 전기시설재원관리 데이터 조회
	 * @param vo
	 * @return egovMap
	 * @throws Exception
	 */		
	public EgovMap selectElctyFcltySpecMngDetail(Map searchVO) throws Exception {
		return (EgovMap)selectByPk("gamElctyFcltySpecMngDao.selectElctyFcltySpecMngDetail", searchVO);
	}
	
	/**
	 * 전기시설재원관리 데이터를 삽입한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */		
	public void insertElctyFcltySpecMngDetail(Map<String, Object> vo) throws Exception {
		insert("gamElctyFcltySpecMngDao.insertElctyFcltySpecMngDetail", vo);
	}
	
	/**
	 * 전기시설재원관리 데이터를 수정한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */		
	public void updateElctyFcltySpecMngDetail(Map<String, Object> vo) throws Exception {
		update("gamElctyFcltySpecMngDao.updateElctyFcltySpecMngDetail", vo);
	}
	
	/**
	 * 전기시설재원관리 데이터를 삭제한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */		
	public void deleteElctyFcltySpecMngDetail(Map<String, Object> vo) throws Exception {
		delete("gamElctyFcltySpecMngDao.deleteElctyFcltySpecMngDetail", vo);
	}
	

	/**
	 * 전기시설재원관리 첨부파일 목록을 가져온다.
	 * @param vo
	 * @return list 
	 * @throws Exception
	 */		
	public List selectElctyFcltySpecFileList(GamElctyFcltySpecMngVO searchVO) throws Exception {
		return list("gamElctyFcltySpecMngDao.selectElctyFcltySpecFileList", searchVO);
	}

	/**
	 * 전기시설재원관리 첨부파일 목록 총수를 가져온다.
	 * @param vo
	 * @return list 
	 * @throws Exception
	 */		
	public int selectElctyFcltySpecFileListTotCnt(GamElctyFcltySpecMngVO searchVO) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamElctyFcltySpecMngDao.selectElctyFcltySpecFileListTotCnt", searchVO);
	}


	/**
	 * 전기시설재원관리 첨부파일 데이터를 삽입한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */		
	public void insertElctyFcltySpecFileDetail(Map<String, Object> vo) throws Exception {
		insert("gamElctyFcltySpecMngDao.insertElctyFcltySpecFileDetail", vo);
	}
	
	/**
	 * 전기시설재원관리 첨부파일 데이터를 수정한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */		
	public void updateElctyFcltySpecFileDetail(Map<String, Object> vo) throws Exception {
		update("gamElctyFcltySpecMngDao.updateElctyFcltySpecFileDetail", vo);
	}

	/**
	 * 전기시설재원관리 첨부파일 데이터를 삭제한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */			
	public void deleteElctyFcltySpecFileDetail(Map<String, Object> vo) throws Exception {
		delete("gamElctyFcltySpecMngDao.deleteElctyFcltySpecFileDetail", vo);
	}

	/**
	 * 전기시설재원관리 첨부파일 목록을 삭제한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */			
	public void deleteElctyFcltySpecFileList(Map<String, Object> vo) throws Exception {
		delete("gamElctyFcltySpecMngDao.deleteElctyFcltySpecFileList", vo);
	}
	
	/**
	 * 전기시설재원관리 첨부파일 목록을 병합하여 저장한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */			
    public List mergeFcltyFile(Map vo) throws Exception{
		return this.merge(vo, "gamElctyFcltySpecMngDao.insertElctyFcltySpecFileDetail", "gamElctyFcltySpecMngDao.updateElctyFcltySpecFileDetail", "gamElctyFcltySpecMngDao.deleteElctyFcltySpecFileDetail");
    }
	
}
