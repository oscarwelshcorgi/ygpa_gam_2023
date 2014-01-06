package egovframework.com.ygpa.cmm.miplatform;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;

public class YGMiplatformArgumentResolver implements WebArgumentResolver {

	private static final Logger logger = Logger.getLogger(YGMiplatformArgumentResolver.class);

	@Override
	public Object resolveArgument(MethodParameter methodParameter, NativeWebRequest webRequest)
			throws Exception {
        Class<?> type = methodParameter.getParameterType();
//      Method method = methodParameter.getMethod();
      if (type.equals(YGMiplatformRequest.class)) {
          HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
//          if (logger.isDebugEnabled()) {
//              logger.debug(request.getRequestURI());
//          }
          YGMiplatformRequest iSMiPlatformRequest = new YGMiplatformRequest(request);

//          if (logger.isDebugEnabled()) {
//              logger.debug("resolveArgument(MethodParameter, NativeWebRequest) - end"+iSMiPlatformRequest.getClass()); //$NON-NLS-1$
//          }
          return iSMiPlatformRequest;
      }

//      if (logger.isDebugEnabled()) {
//          logger.debug("resolveArgument(MethodParameter, NativeWebRequest) - end:UNRESOLVED"); //$NON-NLS-1$
//      }
      return UNRESOLVED;
	}

}
