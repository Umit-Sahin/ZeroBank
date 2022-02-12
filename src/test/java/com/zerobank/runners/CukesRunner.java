package com.zerobank.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
      plugin = {"json:target/cucumber.json",
                "rerun:target/rerun.txt",
                "html:target/SystemTestReports/html",
                },
      features = "src/test/resources/features",
      glue = "com/zerobank/stepdefinations",
      dryRun = false,
      tags = "@Regression"

)
public class CukesRunner {

}
