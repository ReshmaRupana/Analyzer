/**
 * 
 */
package com.disys.analyzer.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.disys.analyzer.config.util.FacesUtils;
import com.disys.analyzer.model.dto.AnalyserCategory2DTO;
import com.disys.analyzer.model.dto.AnalyserCommisionPersonDTO;
import com.disys.analyzer.model.dto.AnalyserLiaisonDTO;
import com.disys.analyzer.model.dto.AnalyzerCategory1DTO;
import com.disys.analyzer.model.dto.BonusPercentageDTO;
import com.disys.analyzer.model.dto.CoSellStatusDTO;
import com.disys.analyzer.model.dto.CommissionModelDTO;
import com.disys.analyzer.model.dto.DealTypeDTO;
import com.disys.analyzer.model.dto.EmployeeCategoryDTO;
import com.disys.analyzer.model.dto.EmployeeClassDTO;
import com.disys.analyzer.model.dto.EmployeeTypeDTO;
import com.disys.analyzer.model.dto.EntityDTO;
import com.disys.analyzer.model.dto.FLSAStatusDTO;
import com.disys.analyzer.model.dto.GenderDTO;
import com.disys.analyzer.model.dto.HolidayDTO;
import com.disys.analyzer.model.dto.JobBoardDTO;
import com.disys.analyzer.model.dto.JobCategoryDTO;
import com.disys.analyzer.model.dto.MajorityWorkPerformedDTO;
import com.disys.analyzer.model.dto.OfficeDTO;
import com.disys.analyzer.model.dto.ProductDTO;
import com.disys.analyzer.model.dto.PtoDTO;
import com.disys.analyzer.model.dto.RecruitingClassificationDTO;
import com.disys.analyzer.model.dto.SickLeaveTypeDTO;
import com.disys.analyzer.model.dto.SkillCategoryDTO;
import com.disys.analyzer.model.dto.SplitPercentageDTO;
import com.disys.analyzer.model.dto.TravelStatusDTO;
import com.disys.analyzer.model.dto.UnemployedStatusDTO;
import com.disys.analyzer.model.dto.VerticalDTO;
import com.disys.analyzer.model.dto.VeteranStatusDTO;
import com.disys.analyzer.model.dto.WorkFromSourceDTO;

/**
 * @author Sajid
 * @since Oct 3, 2020
 */
@Configuration
@EnableScheduling
@ComponentScan(basePackages = { "com.disys.analyzer" })
@Order(1)
public class WebApplicationInitializer extends
		AbstractAnnotationConfigDispatcherServletInitializer {
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { WebApplicationInitializer.class };
	}

	/**
	 * {@link WebApplicationConfigurerAdapter} and
	 * {@link WebApplicationSecurityConfigurerAdapter} already being scanned.
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] {};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
	
    @Bean
    public TaskScheduler taskScheduler() {
        final ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(FacesUtils.THREAD_POOL_SIZE);
        return scheduler;
    }
    
    @Bean
    @ApplicationScope
    public Map<String, List<OfficeDTO>> officeDropDownBean() {
        return new HashMap<String, List<OfficeDTO>>();
    }
    
    @Bean
    @ApplicationScope
    public Map<String, List<VerticalDTO>> verticalDropDownBean() {
        return new HashMap<String, List<VerticalDTO>>();
    }
    
    @Bean
    @ApplicationScope
    public Map<String, List<AnalyserCommisionPersonDTO>> mdCommissionPersonDropDownBean() {
        return new HashMap<String, List<AnalyserCommisionPersonDTO>>();
    }
    
    @Bean
    @ApplicationScope
    public Map<String, List<AnalyserCommisionPersonDTO>> mspClientPartnerDropDownBean() {
        return new HashMap<String, List<AnalyserCommisionPersonDTO>>();
    }
    
    @Bean
    @ApplicationScope
    public Map<String, List<AnalyserCommisionPersonDTO>> allCommissionPersonDropDownBean() {
        return new HashMap<String, List<AnalyserCommisionPersonDTO>>();
    }
    
    @Bean
    @ApplicationScope
    public Map<String, List<AnalyserCommisionPersonDTO>> commissionPersonDropDownBean() {
        return new HashMap<String, List<AnalyserCommisionPersonDTO>>();
    }
    
    
    @Bean
    @ApplicationScope
    public Map<String, List<DealTypeDTO>> dealTypeDropDownBean() {
        return new HashMap<String, List<DealTypeDTO>>();
    }
    
    @Bean
    @ApplicationScope
    public Map<String, List<CommissionModelDTO>> commissionModelDropDownBean() {
        return new HashMap<String, List<CommissionModelDTO>>();
    }
    
    @Bean
    @ApplicationScope
    public Map<String, List<SplitPercentageDTO>> splitPercentageDropDownBean() {
        return new HashMap<String, List<SplitPercentageDTO>>();
    }
    
    @Bean
    @ApplicationScope
    public Map<String, List<AnalyserCategory2DTO>> analyserCategory2DropDownBean() {
        return new HashMap<String, List<AnalyserCategory2DTO>>();
    }
    
    @Bean
    @ApplicationScope
    public Map<String, List<EmployeeCategoryDTO>> employeeCategoryDropDownBean() {
        return new HashMap<String, List<EmployeeCategoryDTO>>();
    }
    
    @Bean
    @ApplicationScope
    public Map<String, List<RecruitingClassificationDTO>> recruitingClassificationDropDownBean() {
        return new HashMap<String, List<RecruitingClassificationDTO>>();
    }

    @Bean
    @ApplicationScope
    public Map<String, List<EmployeeTypeDTO>> employeeTypeDropDownBean() {
        return new HashMap<String, List<EmployeeTypeDTO>>();
    }
    
    @Bean
    @ApplicationScope
    public Map<String, List<CoSellStatusDTO>> coSellStatusDropDownBean() {
        return new HashMap<String, List<CoSellStatusDTO>>();
    }
    
    @Bean
    @ApplicationScope
    public Map<String, List<GenderDTO>> genderDropDownBean() {
        return new HashMap<String, List<GenderDTO>>();
    }
    
    @Bean
    @ApplicationScope
    public Map<String, List<JobCategoryDTO>> jobCategoryDownBean() {
        return new HashMap<String, List<JobCategoryDTO>>();
    }
    
    @Bean
    @ApplicationScope
    public Map<String, List<FLSAStatusDTO>> flsaStatusDownBean() {
        return new HashMap<String, List<FLSAStatusDTO>>();
    }
    
    /*
	 * Added as part of New Projects Specification
	 */
    
    @Bean
    @ApplicationScope
    public Map<String, List<MajorityWorkPerformedDTO>> majorityWorkPerformedDownBean() {
        return new HashMap<String, List<MajorityWorkPerformedDTO>>();
    }
    
    /*
   	 * Added as part of New Projects Specification
   	 */
    @Bean
    @ApplicationScope
    public Map<String, List<WorkFromSourceDTO>> workFromSourceDownBean() {
        return new HashMap<String, List<WorkFromSourceDTO>>();
    }
    
   
    @Bean
    @ApplicationScope
    public Map<String, List<EmployeeClassDTO>> employeeClassDownBean() {
        return new HashMap<String, List<EmployeeClassDTO>>();
    }
    
    @Bean
    @ApplicationScope
    public Map<String, List<SkillCategoryDTO>> skillCategoryDownBean() {
        return new HashMap<String, List<SkillCategoryDTO>>();
    }
    
    @Bean
    @ApplicationScope
    public Map<String, List<TravelStatusDTO>> travelStatusDownBean() {
        return new HashMap<String, List<TravelStatusDTO>>();
    }
    
    @Bean
    @ApplicationScope
    public Map<String, List<VeteranStatusDTO>> veteranStatusDownBean() {
        return new HashMap<String, List<VeteranStatusDTO>>();
    }
    
    @Bean
    @ApplicationScope
    public Map<String, List<JobBoardDTO>> jobBoardDownBean() {
        return new HashMap<String, List<JobBoardDTO>>();
    }
    
    @Bean
    @ApplicationScope
    public Map<String, List<UnemployedStatusDTO>> unemployedStatusDownBean() {
        return new HashMap<String, List<UnemployedStatusDTO>>();
    }
    
    @Bean
    @ApplicationScope
    public Map<String, List<SickLeaveTypeDTO>> sickLeaveTypeDownBean() {
        return new HashMap<String, List<SickLeaveTypeDTO>>();
    }
    
    @Bean
    @ApplicationScope
    public Map<String, List<HolidayDTO>> holidayDownBean() {
        return new HashMap<String, List<HolidayDTO>>();
    }
    
    @Bean
    @ApplicationScope
    public Map<String, List<AnalyzerCategory1DTO>> analyzerCategory1DownBean() {
        return new HashMap<String, List<AnalyzerCategory1DTO>>();
    }
    
    @Bean
    @ApplicationScope
    public Map<String, List<BonusPercentageDTO>> bonusPercentageDownBean() {
        return new HashMap<String, List<BonusPercentageDTO>>();
    }
    
    @Bean
    @ApplicationScope
    public Map<String, List<ProductDTO>> productDownBean() {
        return new HashMap<String, List<ProductDTO>>();
    }
    
    @Bean
    @ApplicationScope
    public Map<String, List<PtoDTO>> ptoDownBean() {
        return new HashMap<String, List<PtoDTO>>();
    }
    
    @Bean
    @ApplicationScope
    public Map<String, List<EntityDTO>> entityDownBean() {
        return new HashMap<String, List<EntityDTO>>();
    }
    
    @Bean
    @ApplicationScope
    public Map<String, List<AnalyserLiaisonDTO>> analyserLiaisonDownBean() {
        return new HashMap<String, List<AnalyserLiaisonDTO>>();
    }
  
}
