package com.rtncenteredtext;

import androidx.annotation.Nullable;

import android.util.Log;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.common.ViewUtil;
import com.facebook.react.uimanager.events.Event;


public class TestDirectEvent extends Event {

    public static final String EVENT_NAME = "onTestDirectEvent";

    private final String mMessage;

    @Deprecated
    public TestDirectEvent(int viewId, String message) {
      this(-1, viewId, message);
    }
  
    public TestDirectEvent(int surfaceId, int viewId, String message) {
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
      Log.i("CenteredText", "TestDirectEvent.getEventData(), target: " + getViewTag() + ", message: " + getMessage());

      WritableMap eventData = Arguments.createMap();
      // eventData.putInt("target", getViewTag());
      eventData.putString("message", getMessage());
      return eventData;
    }
}