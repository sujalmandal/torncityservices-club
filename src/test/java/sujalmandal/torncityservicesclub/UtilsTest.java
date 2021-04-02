package sujalmandal.torncityservicesclub;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import com.fasterxml.jackson.core.JsonProcessingException;

import sujalmandal.torncityservicesclub.models.JobDetailTemplate;
import sujalmandal.torncityservicesclub.utils.AppUtils;

public class UtilsTest {

    @Test
    public void testFormDataGeneration() throws JsonProcessingException {
	Set<JobDetailTemplate> jsonFormData = AppUtils.generateJobDetailTemplates();
	Assert.notEmpty(jsonFormData, "Form data can't be empty!");
    }

}