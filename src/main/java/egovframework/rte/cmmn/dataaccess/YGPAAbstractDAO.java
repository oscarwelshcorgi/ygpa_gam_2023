package egovframework.rte.cmmn.dataaccess;

import javax.annotation.Resource;

import com.ibatis.sqlmap.client.SqlMapClient;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

public class YGPAAbstractDAO extends EgovAbstractDAO {

	/**
	 * DB 별 sqlMapClient 지정
	 */
	@Resource(name = "sqlMapClient")
	public void setSuperSqlMapClient(SqlMapClient sqlMapClient) {
        super.setSuperSqlMapClient(sqlMapClient);
    }
	
    /**
     * 데이터를 병합한다.
     * @param map   insert, update 레코드는 CU 에 delete 레코드는 D 에 사용자 정보는 USER 에 리스트로 각각 저장된다.
     * @param insertQuery 실제 삽입 할 쿼리 아이디
     * @param updateQuery 실제 갱신 할 쿼리 아이디
     * @param deleteQuery 실제 삭제 할 쿼리 아이디
     * @return 적용된 결과가 cntC, cntU, cntD 에 저장되어 리스트로 리턴한다.
    public List merge(Map<String, Object> map, String insertQuery, String updateQuery, String deleteQuery) throws Exception {
        ArrayList arraylistD = (ArrayList)map.get("D");
        ArrayList arraylistCU = (ArrayList)map.get("CU");
        ArrayList arraylistUSER = (ArrayList)map.get("USER");
        HashMap[] hmD = (HashMap[])arraylistD.toArray(new HashMap[arraylistD.size()]);
        HashMap[] hmCU = (HashMap[])arraylistCU.toArray(new HashMap[arraylistCU.size()]);
        HashMap hmUSER = arraylistUSER.size() > 0 ? (HashMap)arraylistUSER.get(0) : new HashMap();
        //if(arraylistUser.size() > 0) hmUSER = (HashMap)arraylistUser.get(0);

        //처리건수
        int cntD = 0;
        int cntU = 0;
        int cntC = 0;

        //삭제처리
        for (int i=0; i<hmD.length; i++) {
            hmD[i].put("upid", hmUSER.get("id"));
            cntD += this.delete(deleteQuery, hmD[i]);
        }
        
        logger.debug("Create update length = " + hmCU.length + " (delete "+ cntD + " records)");
        //수정처리 & 입력처리
        for (int i=0; i<hmCU.length; i++) {
            if ("update".equals(hmCU[i].get("setRowStatus"))) {
                logger.debug("update data : "+i);
                hmCU[i].put("upid", hmUSER.get("id"));
                cntU += this.update(updateQuery, hmCU[i]);

            } else if ("insert".equals(hmCU[i].get("setRowStatus"))) {
                logger.debug("insert data : "+i);
                hmCU[i].put("upid", hmUSER.get("id"));
                logger.debug("columns : "+hmCU[i].keySet().toString());
                logger.debug("action : " + hmCU[i].values().toString());
                insert(insertQuery, hmCU[i]);
                logger.debug("insert data done.");
                cntC++;
            }
            else {
                logger.debug("unknown RowStatus ["+i+"] : "+hmCU[i].get("setRowStatus"));
            }
        }

        List list = new ArrayList();
        HashMap hm = new HashMap();
        hm.put("cntD", cntD);
        hm.put("cntU", cntU);
        hm.put("cntC", cntC);
        list.add(hm);

        return list;
    }
         */

}