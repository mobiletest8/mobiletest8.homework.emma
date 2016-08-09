package com.example.myfirstapp.test;

import android.test.ActivityInstrumentationTestCase2;

import com.example.myfirstapp.MainActivity;
import com.robotium.solo.Solo;
import android.test.ActivityInstrumentationTestCase2;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static org.junit.Assert.*;

/**
 * Created by huangxiaoshi on 8/5/16.
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2 {

    private static final  int TIMEOUT_IN_MS = 4000 ;
    private static final String TEST_MESSAGE = "Hello Emma";
    private MainActivity mActivity;
    private EditText mEditText;
    private Button mButton;
    private ViewGroup mView;
    String  menterText;

    private Solo solo;

    public MainActivityTest() {
        super(MainActivity.class);
    }


    @Before
    public void setUp() throws Exception {
            super.setUp();
            solo = new Solo(getInstrumentation(),getActivity());

    }

    @After
    public void tearDown() throws Exception {
            solo.finishOpenedActivities();
    }


        @Test
        public void testSend(){

            solo.unlockScreen();

            solo.clickOnView(solo.getView(com.example.myfirstapp.R.id.edit_message));

            solo.enterText(0,TEST_MESSAGE);

            solo.clickOnView(solo.getView(com.example.myfirstapp.R.id.btn_send));


            solo.assertCurrentActivity("Activity jump failed", "DisplayMessageActivity",true);
            solo.waitForActivity("DisplayMessageActivity");

            solo.takeScreenshot();

            boolean messageFound=solo.searchText(TEST_MESSAGE);

            assertTrue("Message is not found",messageFound);

        }
    }
