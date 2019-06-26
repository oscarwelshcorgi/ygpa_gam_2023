package egovframework.rte.ygpa.gam.code.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.code.service.GamBuldMktcStdAmManageService;
import egovframework.rte.ygpa.gam.code.service.GamBuldMktcStdAmVO;



/**
 * 
 * 건물시가표준액에 대한 데이터 접근 클래스를 정의
 * 
 */

@Repository("gamBuldMktcStdAmManageDAO")
public class GamBuldMktcStdAmManageDAO extends YGPAAbstractDAO {

    /**
	 * 건출물시가표준액 목록을 조회한다.
     * @param searchVO
     * @return List(건출물시가표준액 목록)
     * @throws Exception
     */
	
    public List selectBuldMktcStdAmList(GamBuldMktcStdAmVO searchVO) throws Exception {
        return list("gamBuldMktcStdAmManageDAO.selectBuldMktcStdAmList", searchVO);
    }

    /**
	 * 건출물시가표준액 총 갯수를 조회한다.
     * @param searchVO
     * @return int(건출물시가표준액 총 갯수)
     */
    public int selectBuldMktcStdAmListTotCnt(GamBuldMktcStdAmVO searchVO) throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamBuldMktcStdAmManageDAO.selectBuldMktcStdAmListTotCnt", searchVO);
    }

	/**
	 * 건출물시가표준액를 등록한다.
	 * @param inputVO
	 * @throws Exception
	 */
	public void insertBuldMktcStdAm(GamBuldMktcStdAmVO inputVO) throws Exception {
		insert("gamBuldMktcStdAmManageDAO.insertBuldMktcStdAmList", inputVO);
	}

	/**
	 * 건출물시가표준액를 수정한다.
	 * @param inputVO
	 * @throws Exception
	 */
	public void updateBuldMktcStdAm(GamBuldMktcStdAmVO inputVO) {
		update("gamBuldMktcStdAmManageDAO.updateBuldMktcStdAm", inputVO);		
	}

	/**
	 * 건출물시가표준액를 삭제한다.
	 * @param deleteVO
	 * @throws Exception
	 */
	public void deleteBuldMktcStdAm(GamBuldMktcStdAmVO deleteVO) throws Exception {
		delete("gamBuldMktcStdAmManageDAO.deleteBuldMktcStdAm", deleteVO);		
	}
		
}
	
