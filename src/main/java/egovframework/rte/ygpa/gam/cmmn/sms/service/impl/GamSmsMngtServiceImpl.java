package egovframework.rte.ygpa.gam.cmmn.sms.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.cmmn.sms.service.GamSmsMngtService;

@Service("gamSmsMngtService")
public class GamSmsMngtServiceImpl extends AbstractServiceImpl
		implements GamSmsMngtService {

    @Resource(name="gamSmsMngtDAO")
    private GamSmsMngtDAO gamSmsMngtDAO;

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.cmmn.sms.service.GamSmsMngtService#sendSmsMessage(java.util.Map)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void sendSmsMessage(Map<String, Object> vo) throws Exception {
		Map map = new HashMap();
		Map charger = null;
		Map employ = null;
		String phoneNo;
		String pn[];

		try {
			charger = gamSmsMngtDAO.selectChargerInfo(vo);
			phoneNo = (String)charger.get("phoneNo");
			pn = phoneNo.split("-");
			map.put("recptnNo1", pn[0]);
			map.put("recptnNo2", pn[1]);
			map.put("recptnNo3", pn[2]);
			map.put("recptnNm",  charger.get("dispNm"));

			map.put("recptnNo", phoneNo);
			map.put("replyNm", employ.get("dispNm"));

			employ = gamSmsMngtDAO.selectEmployInfo((String)vo.get("regUsr"));
			phoneNo = (String)employ.get("phoneNo");
			pn = phoneNo.split("-");
			map.put("replyNo1", pn[0]);
			map.put("replyNo2", pn[1]);
			map.put("replyNo3", pn[2]);
			map.put("sendNm",  employ.get("dispNm"));

			map.put("rdate", "00000000");
			map.put("rtime", "00000000");

			map.put("replyNo", phoneNo);
			map.put("replyNm", employ.get("dispNm"));

			gamSmsMngtDAO.sendSmsMessage(map);		// 전송이력을 저장한다.
			map.putAll(vo);

			gamSmsMngtDAO.sendSmsMessageServer(map);	// 실제로 전송한다.
		}
		catch(Exception e) {
			throw processException("gam.sms.charger.notfound");
		}
	}

}
