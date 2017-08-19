package cn.smbms.dao.bill;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.smbms.pojo.Bill;

public interface BillMapper {
	/**
	 * 返回记录个数
	 * 
	 * @param queryProductName
	 * @param queryProviderId
	 * @return
	 * @throws Exception
	 */
	public int getBillCount(@Param("productName") String productName, @Param("providerId") Integer providerId,
			@Param("queryIsPayment") Integer queryIsPayment) throws Exception;

	/**
	 * 返回全部信息
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
	 * 通过billId获取Bill
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Bill getBillById(@Param("id") Integer id);
}
