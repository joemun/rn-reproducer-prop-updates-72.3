package com.rtncenteredtext

import android.util.Log

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContext
import com.facebook.react.common.MapBuilder
import com.facebook.react.module.annotations.ReactModule
import com.facebook.react.uimanager.SimpleViewManager
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.ViewManagerDelegate
import com.facebook.react.uimanager.annotations.ReactProp
import com.facebook.react.viewmanagers.RTNCenteredTextManagerInterface
import com.facebook.react.viewmanagers.RTNCenteredTextManagerDelegate

@ReactModule(name = CenteredTextManager.NAME)
class CenteredTextManager(context: ReactApplicationContext) :
    SimpleViewManager<CenteredText>(),
    RTNCenteredTextManagerInterface<CenteredText> {

    private val mDelegate: ViewManagerDelegate<CenteredText>
    private lateinit var mThemedReactContext: ThemedReactContext

    companion object {
        const val NAME = "RTNCenteredText"
    }

    init {
        mDelegate = RTNCenteredTextManagerDelegate(this)
    }

    override fun getDelegate(): ViewManagerDelegate<CenteredText> {
        return mDelegate
    }

    override fun getName(): String {
        return NAME
    }

    override fun createViewInstance(context: ThemedReactContext): CenteredText {
        mThemedReactContext = context
        return CenteredText(context)
    }

    @ReactProp(name = "text")
    override fun setText(view: CenteredText, text: String?) {
        view.text = text

        Log.i("CenteredText", "setText called. view: " + view.id + ", text: " + text)
        ReactEventDispatcher.sendTestDirectEvent(view.id, "Called setText(" + text + ")", mThemedReactContext)
        // ReactEventDispatcher.sendTestBubblingEvent(view.id, "Bubbling event from native!", mThemedReactContext)
    }

    @ReactProp(name = "other")
    override fun setOther(view: CenteredText, other: String?) {
        Log.i("CenteredText", "setOther called. view: " + view.id + ", other: " + other)
        ReactEventDispatcher.sendTestDirectEvent(view.id, "Called setOther(" + other + ")", mThemedReactContext)
        // ReactEventDispatcher.sendTestBubblingEvent(view.id, "Bubbling event from native!", mThemedReactContext)
    }

    override fun getExportedCustomDirectEventTypeConstants(): Map<String, Any>? {
        val baseEventTypeConstants: Map<String, Any>? = super.getExportedCustomDirectEventTypeConstants()
        val eventTypeConstants: MutableMap<String, Any> =
            baseEventTypeConstants?.toMutableMap() ?: HashMap<String, Any>()
        eventTypeConstants.putAll(
            MapBuilder.of(
                "topOnTestDirectEvent",
                MapBuilder.of("registrationName", "onTestDirectEvent")
            )
        )

        // Log.i("CenteredText", "getExportedCustomDirectEventTypeConstants(): " + eventTypeConstants)

        return eventTypeConstants
    }

    override fun getExportedCustomBubblingEventTypeConstants(): Map<String, Any> {
        val eventTypeConstants: MutableMap<String, Any> = HashMap()
        eventTypeConstants.putAll(
            MapBuilder.of(
                "onTestBubblingEvent",
                MapBuilder.of(
                    "phasedRegistrationNames",
                    MapBuilder.of("bubbled", "onTestBubblingEvent")
                )
            )
        )

        // Log.i("CenteredText", "getExportedCustomBubblingEventTypeConstants(): " + eventTypeConstants)

        return eventTypeConstants
    }
}
