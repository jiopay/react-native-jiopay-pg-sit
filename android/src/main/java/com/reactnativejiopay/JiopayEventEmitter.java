package com.reactnativejiopay;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

import java.util.HashMap;
import java.util.Map;

/**
 * Supports sending events to JavaScript.
 */
final class JiopayEventEmitter extends ReactContextBaseJavaModule {

    private static DeviceEventManagerModule.RCTDeviceEventEmitter eventEmitter = null;

    JiopayEventEmitter(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public void initialize() {
        super.initialize();
        eventEmitter = getReactApplicationContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class);
    }

    /**
     * @return the name of this module. This will be the name used to {@code require()} this module
     * from JavaScript.
     */
    @Override
    public String getName() {
        return "JiopayEventEmitter";
    }



    /**
     * To pass a JavaScript object instead of a simple string, create a {@link WritableNativeMap} and populate it.
     */
    static void emitEvent( String eventName,@NonNull WritableMap message) {
        eventEmitter.emit(eventName, message);
    }
}
