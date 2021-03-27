package sujalmandal.torncityservicesclub.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.CollectionUtils;

import sujalmandal.torncityservicesclub.dtos.JobFilterRequestDTO;
import sujalmandal.torncityservicesclub.enums.JobType;

public class MongoUtil {

    public static Criteria getCriteriaForJobFilterRequest(JobFilterRequestDTO jobFilterRequestDTO) {
        List<Criteria> criteriaList = new ArrayList<>();
        if (CollectionUtils.isEmpty(jobFilterRequestDTO.getJobTypes())) {
            criteriaList.add(new Criteria("jobType").in(
                    jobFilterRequestDTO.getJobTypes().stream().map(JobType::toString).collect(Collectors.toList())));
        }

        if (jobFilterRequestDTO.getAmountGreaterThan() != null) {
            criteriaList.add(new Criteria("amount").gte(jobFilterRequestDTO.getAmountGreaterThan()));
        }

        if (jobFilterRequestDTO.getAmountLessThan() != null) {
            criteriaList.add(new Criteria("amount").lte(jobFilterRequestDTO.getAmountLessThan()));
        }

        if (jobFilterRequestDTO.getPayGreaterThan() != null) {
            criteriaList.add(new Criteria("pay").gte(jobFilterRequestDTO.getPayGreaterThan()));
        }

        if (jobFilterRequestDTO.getPayLessThan() != null) {
            criteriaList.add(new Criteria("pay").lte(jobFilterRequestDTO.getPayLessThan()));
        }

        if (jobFilterRequestDTO.getPostedDateAfter() != null) {
            criteriaList.add(new Criteria("postedDate").gte(jobFilterRequestDTO.getPostedDateAfter()));
        }

        if (jobFilterRequestDTO.getPostedDateBefore() != null) {
            criteriaList.add(new Criteria("postedDate").lte(jobFilterRequestDTO.getPostedDateBefore()));
        }

        return new Criteria().andOperator(criteriaList.toArray(new Criteria[criteriaList.size()]));
    }

    public static void paginateQuery(Query query,int pageNumber, int pageSize){
        query.skip(pageNumber*pageSize);
        query.limit(pageSize);
    }
}
