package egovframework.rte.ygpa.gam.code.service;

import java.util.List;
import java.util.Map;

/**
 * 
 * 건축물시가표준액에 관한 서비스 인터페이스 클래스를 정의한다
 * 
 */

public interface GamBuldMktcStdAmManageService {
	    

	/**
	 * 건축물시가표준액 목록을 조회한다.
	 * @param searchVO
	 * @return List(건축물시가표준액 목록)
	 * @throws Exception
	 */
	List selectBuldMktcStdAmList(GamBuldMktcStdAmVO searchVO) throws Exception;

    /**
	 * 건축물시가표준액 총 갯수를 조회한다.
     * @param searchVO
     * @return int(건축물시가표준액 총 갯수)
     */
    int selectBuldMktcStdAmListTotCnt(GamBuldMktcStdAmVO searchVO) throws Exception;
	
	/**
	 * 건축물시가표준액를 등록한다.
	 * @param buldMktcStdAm
	 * @throws Exception
	 */
	void insertBuldMktcStdAm(GamBuldMktcStdAmVO inputVO) throws Exception;

	/**
	 * 건축물시가표준액를 수정한다.
	 * @param buldMktcStdAm
	 * @throws Exception
	 */
	void updateBuldMktcStdAm(GamBuldMktcStdAmVO inputVO) throws Exception;
    
	/**
	 * 건축물시가표준액를 삭제한다.
	 * @param buldMktcStdAm
	 * @throws Exception
	 */
	void deleteBuldMktcStdAm(GamBuldMktcStdAmVO deleteVO) throws Exception;


}
