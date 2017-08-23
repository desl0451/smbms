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
	 * ����ָ�������ļ�¼����
	 */
	@Override
	public int getBillCount(String queryProductName, Integer queryProviderId, Integer queryIsPayment) throws Exception {
		// TODO Auto-generated method stub
		return billMapper.getBillCount(queryProductName, queryProviderId, queryIsPayment);
	}
	/**
	 * ���ض����б�
	 */
	@Override
	public List<Bill> getBillList(String queryProductName, Integer queryProviderId, Integer queryIsPayment,
			Integer currentPageNo, Integer pageSize) throws Exception {
		// TODO Auto-generated method stub
		currentPageNo = (currentPageNo - 1) * pageSize;
		return billMapper.getBillList(queryProductName, queryProviderId, queryIsPayment, currentPageNo, pageSize);
	}

	/**
	 * ����ID��ȡBill��Ϣ
	 */
	@Override
	public Bill getBillById(Integer id) {
		// TODO Auto-generated method stub
		return billMapper.getBillById(id);
	}

	/**
	 * ��Ӷ�����Ϣ
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
	 * �޸Ķ�����Ϣ
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
	 * ɾ��������Ϣ
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
