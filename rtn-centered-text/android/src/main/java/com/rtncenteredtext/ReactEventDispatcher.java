package com.rtncenteredtext;

import androidx.annotation.NonNull;
import android.view.View;

import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.events.EventDispatcher;

public class ReactEventDispatcher {
    
    private static final String TAG = "ReactEventDispatcher";

    public static void sendTestDirectEvent(@NonNull int viewId, String message, ReactContext context) {
        EventDispatcher eventDispatcher =
            UIManagerHelper.getEventDispatcherForReactTag(context, viewId);
        if (eventDispatcher != null) {
          eventDispatcher.dispatchEvent(
              new TestDirectEvent(UIManagerHelper.getSurfaceId(context), viewId, message));
        }
    }

    public static void sendTestBubblingEvent(@NonNull int viewId, String message, ReactContext context) {
        EventDispatcher eventDispatcher =
            UIManagerHelper.getEventDispatcherForReactTag(context, viewId);
        if (eventDispatcher != null) {
          eventDispatcher.dispatchEvent(
              new TestBubblingEvent(UIManagerHelper.getSurfaceId(context), viewId, message));
        }
    }
}
