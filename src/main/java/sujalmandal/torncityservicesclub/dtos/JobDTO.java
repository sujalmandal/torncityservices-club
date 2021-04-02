package sujalmandal.torncityservicesclub.dtos;

import java.time.LocalDateTime;

import lombok.Data;
import sujalmandal.torncityservicesclub.enums.ServiceType;
import sujalmandal.torncityservicesclub.models.Job;
import sujalmandal.torncityservicesclub.utils.PojoUtils;


@Data
public class JobDTO {

    private String id;
    private ServiceType serviceType;
    private int amount;
    private Long pay;
    private LocalDateTime postedDate;

    public static JobDTO fromJob(Job job){
        JobDTO JobDTO = new JobDTO();
        PojoUtils.getModelMapper().map(job, JobDTO);
        return JobDTO;
    }

}