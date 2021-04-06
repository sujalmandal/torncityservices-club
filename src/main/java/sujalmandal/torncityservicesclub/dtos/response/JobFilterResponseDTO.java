package sujalmandal.torncityservicesclub.dtos.response;

import java.util.List;

import lombok.Data;

@Data
public class JobFilterResponseDTO {

    List<JobResponseDTO> jobs;
    private int pageSize = 25;
    private int pageNumber = 0;

}
