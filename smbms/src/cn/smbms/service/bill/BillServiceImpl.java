package cn.smbms.service.bill;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import cn.smbms.dao.bill.BillMapper;
import cn.smbms.pojo.Bill;

@Service
public class BillServiceImpl implements BillService {

	@Resource
	private BillMapper billMapper;
	
	/**
	 * 返回指定订单的记录个数
	 */
	@Override
	public int getBillCount(String queryProductName, Integer queryProviderId, Integer queryIsPayment) throws Exception {
		// TODO Auto-generated method stub
		return billMapper.getBillCount(queryProductName, queryProviderId, queryIsPayment);
	}
	/**
	 * 返回订单列表
	 */
	@Override
	public List<Bill> getBillList(String queryProductName, Integer queryProviderId, Integer queryIsPayment,
			Integer currentPageNo, Integer pageSize) throws Exception {
		// TODO Auto-generated method stub
		currentPageNo = (currentPageNo - 1) * pageSize;
		return billMapper.getBillList(queryProductName, queryProviderId, queryIsPayment, currentPageNo, pageSize);
	}

	/**
	 * 根据ID读取Bill信息
	 */
	@Override
	public Bill getBillById(Integer id) {
		// TODO Auto-generated method stub
		return billMapper.getBillById(id);
	}

	/**
	 * 添加订单信息
	 */
	@Override
	public boolean addBill(Bill bill) {
		// TODO Auto-generated method stub
		if (billMapper.insert(bill) > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 修改订单信息
	 */
	@Override
	public boolean updateBill(Bill bill) {
		// TODO Auto-generated method stub
		if (billMapper.update(bill) > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 删除订单信息
	 */
	@Override
	public boolean deleteBill(Integer id) {
		// TODO Auto-generated method stub
		if (billMapper.delete(id) > 0) {
			return true;
		}
		return false;
	}

}
