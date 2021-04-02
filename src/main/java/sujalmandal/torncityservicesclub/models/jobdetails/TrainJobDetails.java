package sujalmandal.torncityservicesclub.models.jobdetails;

import lombok.Data;
import lombok.EqualsAndHashCode;
import sujalmandal.torncityservicesclub.annotations.HighlightField;
import sujalmandal.torncityservicesclub.annotations.JobDetailFieldLabel;
import sujalmandal.torncityservicesclub.annotations.JobDetailFieldType;
import sujalmandal.torncityservicesclub.annotations.JobDetailTemplateKey;
import sujalmandal.torncityservicesclub.enums.JobDetailFieldTypeValue;
import sujalmandal.torncityservicesclub.enums.JobDetailTemplateValue;
import sujalmandal.torncityservicesclub.models.JobDetails;

@Data
@EqualsAndHashCode(callSuper = false)
@JobDetailTemplateKey(JobDetailTemplateValue.TRAINS)
public class TrainJobDetails extends JobDetails {

    @JobDetailFieldType(JobDetailFieldTypeValue.NUMBER)
    @JobDetailFieldLabel("How many trains in total")
    private Integer totalTrains;

    @HighlightField
    @JobDetailFieldType(JobDetailFieldTypeValue.NUMBER)
    @JobDetailFieldLabel("Money per train")
    private Integer amountPerTrain;
}