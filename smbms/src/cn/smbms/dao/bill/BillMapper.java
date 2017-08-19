package cn.smbms.dao.bill;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.smbms.pojo.Bill;

public interface BillMapper {
	/**
	 * ���ؼ�¼����
	 * 
	 * @param queryProductName
	 * @param queryProviderId
	 * @return
	 * @throws Exception
	 */
	public int getBillCount(@Param("productName") String productName, @Param("providerId") Integer providerId,
			@Param("queryIsPayment") Integer queryIsPayment) throws Exception;

	/**
	 * ����ȫ����Ϣ
	 * 
	 * @param queryProductName
	 * @param queryProviderId
	 * @return
	 * @throws Exception
	 */
	public List<Bill> getBillList(@Param("productName") String productName, @Param("providerId") Integer providerId,
			@Param("queryIsPayment") Integer queryIsPayment, @Param("from") Integer currentPageNo,
			@Param("pageSize") Integer pageSize) throws Exception;

	/**
	 * ͨ��billId��ȡBill
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Bill getBillById(@Param("id") Integer id);
}
