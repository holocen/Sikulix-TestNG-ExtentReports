import org.sikuli.script.*;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.File;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;


import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

    public class ExtentReportClass{
        ExtentReports extent;
        ExtentTest logger;
        private Screen screen;

        @BeforeTest
        public void startReport(){

            extent = new ExtentReports (System.getProperty("user.dir") +"/test-output/STMExtentReport.html", true);
            extent
                    .addSystemInfo("Host Name", "CYBG")
                    .addSystemInfo("Environment", "Automation Testing")
                    .addSystemInfo("User Name", "Ognjen Ninic");
            extent.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));
            screen = new Screen();
            ImagePath.add("src/test/resources");
        }

        @Test
        public void passTest() throws FindFailed{
            screen.click("windows-start.png");
            screen.wait(1.5);
            screen.type("word 2");
            screen.wait(1.5);
            screen.click("WordIcon.png");
            screen.wait(1.5);
            screen.type("This is a test in Sikuli automation");
            screen.type("s", KeyModifier.CTRL);
            screen.type("sikuliTestDocument");
            screen.click("btn-save.png");
            screen.wait(0.5);
            screen.click("button-ok.png");
            screen.wait(0.5);
            screen.type(Key.F4, KeyModifier.ALT);
            logger = extent.startTest("passTest");
            logger.log(LogStatus.PASS, "Test Case Passed is passTest");
        }

        @Test
        public void failTest() throws FindFailed {
                screen.click("windows-start.png");
                screen.wait(1.5);
                screen.type("word 2");
                screen.wait(1.5);
                screen.click("WordIcon.png");
                screen.wait(1.5);
                screen.type("This is a test in Sikuli automation");
                screen.type("s", KeyModifier.CTRL);
                screen.type("sikuliTestDocument2");
                screen.click("btn-save.png");
                screen.wait(0.5);
                screen.click("button-ok.png");
                screen.wait(0.5);
                screen.type(Key.F4, KeyModifier.ALT);
            logger = extent.startTest("failTest");
            logger.log(LogStatus.PASS, "Test Case (failTest) Status is passed");
        }

      @Test
      public void skipTest() throws FindFailed {
          screen.click("windows-start.png");
          screen.wait(1.5);
          screen.type("word 2");
          screen.wait(1.5);
          screen.click("WordIcon.png");
          screen.wait(1.5);
          screen.type("This is a test in Sikuli automation");
          screen.type("s", KeyModifier.CTRL);
          screen.type("sikuliTestDocument2");
          screen.click("btn-save.png");
          screen.wait(0.5);
          screen.click("button-ok.png");
          screen.wait(0.5);
          screen.type(Key.F4, KeyModifier.ALT);
          logger = extent.startTest("skipTest");
          throw new SkipException("Skipping - This is not ready for testing ");
      }

        @AfterMethod
        public void getResult(ITestResult result){
            if(result.getStatus() == ITestResult.FAILURE){
                logger.log(LogStatus.FAIL, "Test Case Failed is "+result.getName());
                logger.log(LogStatus.FAIL, "Test Case Failed is "+result.getThrowable());
            }else if(result.getStatus() == ITestResult.SKIP){
                logger.log(LogStatus.SKIP, "Test Case Skipped is "+result.getName());
            }
            extent.endTest(logger);
        }

        @AfterTest
        public void endReport(){
            // writing everything to document
            //flush() - to write or update test information to your report.
            extent.flush();
            extent.close();
        }
    }
