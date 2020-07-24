package com.ucpeo.test;

import com.ucpeo.activity.Main;
import com.ucpeo.activity.service.DataAnalysisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = Main.class)
@RunWith(SpringRunner.class)
public class Mytest {
  @Autowired  DataAnalysisService dataAnalysisService;
   @Test
   public void  test(){
       dataAnalysisService.formItemAnalysis(46).forEach(System.out::println);
   }
}
