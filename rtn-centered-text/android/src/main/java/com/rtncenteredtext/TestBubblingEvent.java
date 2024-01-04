package com.rtncenteredtext;

import androidx.annotation.Nullable;

import android.util.Log;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.common.ViewUtil;
import com.facebook.react.uimanager.events.Event;


public class TestBubblingEvent extends Event {

    public static final String EVENT_NAME = "onTestBubblingEvent";

    private final String mMessage;

    @Deprecated
    public TestBubblingEvent(int viewId, String message) {
      this(-1, viewId, message);
    }
  
    public TestBubblingEvent(int surfaceId, int viewId, String message) {
      super(surfaceId, viewId);
      mMessage = message;
    }
  
    public String getMessage() {
        return mMessage;
    }

    @Override
    public String getEventName() {
      return EVENT_NAME;
    }

    @Nullable
    @Override
    protected WritableMap getEventData() {
      Log.i("CenteredText", "TestBubblingEvent.getEventData(), target: " + getViewTag() + ", message: " + getMessage());

      WritableMap eventData = Arguments.createMap();
      // eventData.putInt("target", getViewTag());
      eventData.putString("message", getMessage());
      return eventData;
    }
}