package cn.smbms.aop;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;

public class DaoLogger {
	private static final Logger log = Logger.getLogger(DaoLogger.class);

	public void before(JoinPoint jp) {
		log.info("---------------------------------后台日志-----------------------------------------------------------------");
		log.info("调  用  类:" + jp.getTarget());
		log.info("调用方法:" + jp.getSignature());
		log.info("方  法  名:" + jp.getSignature().getName());
		log.info("参       数:" + Arrays.toString(jp.getArgs()));
		log.info(
				"-------------------------------------------------------------------------------------------------------");
	}

	public void afterReturning(JoinPoint jp, Object result) {
		log.info(
				"-------------------------------------------------------------------------------------------------------");
		log.info("调  用  类:" + jp.getTarget());
		log.info("调用方法:" + jp.getSignature());
		log.info("方  法  名:" + jp.getSignature().getName());
		log.info("返  回  值:" + result);
		log.info("---------------------------------------后台日志结束-------------------------------------------------------");
	}
}
