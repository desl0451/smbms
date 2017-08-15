package cn.smbms.aop;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;

public class DaoLogger {
	private static final Logger log = Logger.getLogger(DaoLogger.class);

	public void before(JoinPoint jp) {
		log.info("---------------------------------��̨��־-----------------------------------------------------------------");
		log.info("��  ��  ��:" + jp.getTarget());
		log.info("���÷���:" + jp.getSignature());
		log.info("��  ��  ��:" + jp.getSignature().getName());
		log.info("��       ��:" + Arrays.toString(jp.getArgs()));
		log.info(
				"-------------------------------------------------------------------------------------------------------");
	}

	public void afterReturning(JoinPoint jp, Object result) {
		log.info(
				"-------------------------------------------------------------------------------------------------------");
		log.info("��  ��  ��:" + jp.getTarget());
		log.info("���÷���:" + jp.getSignature());
		log.info("��  ��  ��:" + jp.getSignature().getName());
		log.info("��  ��  ֵ:" + result);
		log.info("---------------------------------------��̨��־����-------------------------------------------------------");
	}
}
