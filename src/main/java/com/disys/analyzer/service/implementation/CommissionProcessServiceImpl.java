/**
 * 
 */
package com.disys.analyzer.service.implementation;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.disys.analyzer.model.CommissionProcess;
import com.disys.analyzer.repository.CommissionProcessRepository;
import com.disys.analyzer.service.CommissionProcessService;
import com.disys.analyzer.thread.CommissionProcessExecutionThread;

/**
 * @author Sajid
 * 
 *
 */
@Service
@Transactional(readOnly = true)
public class CommissionProcessServiceImpl implements CommissionProcessService, Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -6539895361911264289L;

	@Autowired
	CommissionProcessRepository repository;
	
	@Autowired
    private TaskExecutor taskExecutor;
	
    @Autowired
    private ApplicationContext applicationContext;

	/* (non-Javadoc)
	 * @see com.disys.analyzer.service.CommissionProcessService#findAll()
	 */
	@Override
	public List<Map<String, Object>> findAllOrderByAccountingPeriod() {
		try {
			return repository.findAllOrderByAccountingPeriod();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Map<String, Object>> findAccountingPeriod() {
		try {
			return repository.findAccountingPeriod();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public CommissionProcess findByAccountingPeriodAndFiscalYear(
			Integer accountingPeriod, Integer fiscalYear) {
		return repository.findByAccountingPeriodAndFiscalYear(accountingPeriod,
				fiscalYear);
	}

	@Override
	public Integer saveCommissionProcess(CommissionProcess commissionProcess) {
		return repository.saveCommissionProcess(commissionProcess);
	}

	@Override
	public Integer updateCommissionProcess(CommissionProcess commissionProcess) {
		return repository.updateCommissionProcess(commissionProcess);
	}

	@Override
	public String executeCommissionProcess(CommissionProcess commissionProcess) {
		return repository.executeCommissionProcess(commissionProcess);
	}

	@Override
	public String finalizeCommissionProcess(Integer accountingPeriod,
			Integer fiscalYear) {
		return repository.finalizeCommissionProcess(accountingPeriod, fiscalYear);
	}
	
	@Override
    public void executeAsynchronously(CommissionProcess commissionProcess) {
    	CommissionProcessExecutionThread myThread = applicationContext.getBean(CommissionProcessExecutionThread.class);
    	
    	//CommissionProcessRepositoryImpl myThread = applicationContext.getBean(CommissionProcessRepositoryImpl.class);
    	myThread.setCommissionProcess(commissionProcess);
        taskExecutor.execute(myThread);
    }
}
