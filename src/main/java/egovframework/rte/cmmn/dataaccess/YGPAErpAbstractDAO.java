package egovframework.rte.cmmn.dataaccess;

import javax.annotation.Resource;

import com.ibatis.sqlmap.client.SqlMapClient;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

public class YGPAErpAbstractDAO extends EgovAbstractDAO {

	/**
	 * DB 별 sqlMapClient 지정
	 */
	@Resource(name = "sqlMapClient-erp")
	public void setSuperSqlMapClient(SqlMapClient sqlMapClient) {
        super.setSuperSqlMapClient(sqlMapClient);
    }
	
}