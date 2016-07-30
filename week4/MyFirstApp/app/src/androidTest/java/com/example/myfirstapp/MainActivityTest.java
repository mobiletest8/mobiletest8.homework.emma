package com.example.myfirstapp;

import android.app.Activity;
import android.app.Application;
import android.app.Instrumentation;
import android.test.ApplicationTestCase;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.UiThreadTest;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myfirstapp.MainActivity;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */


public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private static final  int TIMEOUT_IN_MS = 4000 ;
    private static final String TEST_MESSAGE = "Hello Emma";
    private MainActivity mActivity;
    private EditText mEditText;
    private Button mButton;
    private ViewGroup mView;
    String  menterText;

    public MainActivityTest() {

        super("com.example.myfirstapp",MainActivity.class);
    }
    @Override
    protected void setUp() throws Exception {

        super.setUp();

        mActivity = this.getActivity();

        mEditText =(EditText) mActivity.findViewById(R.id.edit_message);

        mButton =(Button) mActivity.findViewById(R.id.btn_send);


    }

    @Override
    protected void tearDown() throws Exception{
        super.tearDown();
    }


    public void testPreconditions() {
        assertNotNull("activity should be launched successfully", getActivity());
        assertNotNull(mEditText);
        assertNotNull(mButton);


    }



    public void testSend(){

        final Button sendToReceiverButton = (Button)
                mActivity.findViewById(R.id.btn_send);

        final EditText senderMessageEditText = (EditText)
                mActivity.findViewById(R.id.edit_message);

        // Set up an ActivityMonitor
        Instrumentation.ActivityMonitor receiverActivityMonitor =
                getInstrumentation().addMonitor(DisplayMessageActivity.class.getName(),
                        null, false);

        // Send string input value

        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                mEditText.requestFocus();
            }
        });


        getInstrumentation().waitForIdleSync();
        getInstrumentation().sendStringSync(TEST_MESSAGE);
        getInstrumentation().waitForIdleSync();

        // Validate that ReceiverActivity is started
        TouchUtils.clickView(this, sendToReceiverButton);
        DisplayMessageActivity receiverActivity = (DisplayMessageActivity)
                receiverActivityMonitor.waitForActivityWithTimeout(TIMEOUT_IN_MS);
        assertNotNull("DisplayMessageActivity is null", receiverActivity);
        assertEquals("Monitor for DisplayMessageActivity has not been called",
                1, receiverActivityMonitor.getHits());
        assertEquals("Activity is of wrong type",
                DisplayMessageActivity.class, receiverActivity.getClass());

        // Validate that ReceiverActivity has the correct data
        final ViewGroup receivedMessage =(ViewGroup)  receiverActivity.findViewById(R.id.activity_display_message);
        assertNotNull(receivedMessage.getChildAt(0));
        TextView textView=((TextView)receivedMessage.getChildAt(0));
        assertEquals("Wrong received message",TEST_MESSAGE,textView.getText().toString());



        // Remove the ActivityMonitor
        getInstrumentation().removeMonitor(receiverActivityMonitor);



    }


}

