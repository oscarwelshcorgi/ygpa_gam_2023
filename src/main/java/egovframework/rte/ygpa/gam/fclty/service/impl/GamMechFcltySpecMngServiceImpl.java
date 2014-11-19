/**
 * 
 */
package egovframework.rte.ygpa.gam.fclty.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.fclty.service.GamMechFcltySpecMngService;
import egovframework.rte.ygpa.gam.fclty.service.GamMechFcltySpecMngVO;

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
 *  2014. 11. 17.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Service("gamMechFcltySpecMngService")
public class GamMechFcltySpecMngServiceImpl extends AbstractServiceImpl implements GamMechFcltySpecMngService{
	@Resource(name="gamMechFcltySpecMngDao")
	private GamMechFcltySpecMngDao gamMechFcltySpecMngDao;
	/**
	 * 기계시설재원관리 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */	
	public List selectMechFcltySpecMngList(GamMechFcltySpecMngVO searchVO) throws Exception {
		return gamMechFcltySpecMngDao.selectMechFcltySpecMngList(searchVO);
	}
	
	/**
	 * 기계시설재원관리 목록 총수 조회
	 * @param vo
	 * @return int
	 * @throws Exception
	 */		
	public int selectMechFcltySpecMngListTotCnt(GamMechFcltySpecMngVO searchVO) throws Exception {
		return gamMechFcltySpecMngDao.selectMechFcltySpecMngListTotCnt(searchVO);
	}

	/**
	 * 기계시설재원관리 데이터 조회
	 * @param vo
	 * @return egovMap
	 * @throws Exception
	 */		
	public EgovMap selectMechFcltySpecMngDetail(Map searchVO) throws Exception {
		return gamMechFcltySpecMngDao.selectMechFcltySpecMngDetail(searchVO);
	}
	
	/**
	 * 기계시설재원관리 데이터를 삽입한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */		
	public void insertMechFcltySpecMngDetail(Map<String, Object> vo) throws Exception {
		gamMechFcltySpecMngDao.insertMechFcltySpecMngDetail(vo);
	}
	
	/**
	 * 기계시설재원관리 데이터를 수정한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */		
	public void updateMechFcltySpecMngDetail(Map<String, Object> vo) throws Exception {
		gamMechFcltySpecMngDao.updateMechFcltySpecMngDetail(vo);
	}
	
	/**
	 * 기계시설재원관리 데이터를 삭제한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */		
	public void deleteMechFcltySpecMngDetail(Map<String, Object> vo) throws Exception {
		gamMechFcltySpecMngDao.deleteMechFcltySpecMngDetail(vo);
	}	
	
	/**
	 * 기계시설재원관리 첨부파일 목록을 가져온다.
	 * @param vo
	 * @return list 
	 * @throws Exception
	 */		
	public List selectMechFcltySpecFileList(GamMechFcltySpecMngVO searchVO) throws Exception {
		return gamMechFcltySpecMngDao.selectMechFcltySpecFileList(searchVO);
	}

	/**
	 * 기계시설재원관리 첨부파일 목록 총수를 가져온다.
	 * @param vo
	 * @return list 
	 * @throws Exception
	 */		
	public int selectMechFcltySpecFileListTotCnt(GamMechFcltySpecMngVO searchVO) throws Exception {
		return gamMechFcltySpecMngDao.selectMechFcltySpecFileListTotCnt(searchVO);
	}


	/**
	 * 기계시설재원관리 첨부파일 데이터를 삽입한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */		
	public void insertMechFcltySpecFileDetail(Map<String, Object> vo) throws Exception {
		gamMechFcltySpecMngDao.insertMechFcltySpecFileDetail(vo);
	}
	
	/**
	 * 기계시설재원관리 첨부파일 데이터를 수정한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */		
	public void updateMechFcltySpecFileDetail(Map<String, Object> vo) throws Exception {
		gamMechFcltySpecMngDao.updateMechFcltySpecFileDetail(vo);
	}

	/**
	 * 기계시설재원관리 첨부파일 데이터를 삭제한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */			
	public void deleteMechFcltySpecFileDetail(Map<String, Object> vo) throws Exception {
		gamMechFcltySpecMngDao.deleteMechFcltySpecFileDetail(vo);
	}

	/**
	 * 기계시설재원관리 첨부파일 목록을 삭제한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */			
	public void deleteMechFcltySpecFileList(Map<String, Object> vo) throws Exception {
		gamMechFcltySpecMngDao.deleteMechFcltySpecFileList(vo);
	}
	
	/**
	 * 기계시설재원관리 첨부파일목록을 병합하여 저장한다.
	 * @param vo
	 * @return 
	 * @throws Exception
	 */			
	public List mergeFcltyFileMngt(Map mergeMap) throws Exception{
		return gamMechFcltySpecMngDao.mergeFcltyFile(mergeMap);
	}
	
}