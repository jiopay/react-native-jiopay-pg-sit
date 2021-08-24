package com.reactnativejiopay;

import static com.reactnativejiopay.JiopayEventEmitter.emitEvent;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import androidx.annotation.NonNull;
import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.module.annotations.ReactModule;
import com.jfs.webinterface.callbacks.PaymentResultListener;
import com.jfs.webinterface.fragment.Checkout;
import org.json.JSONException;
import org.json.JSONObject;

@ReactModule(name = JiopayModule.NAME)
public class JiopayModule
  extends ReactContextBaseJavaModule
  implements PaymentResultListener, ActivityEventListener {
  public static final String NAME = "RNJiopayCheckout";
  public static final String MAP_KEY_PAYMENT_ID = "jiopay_payment_id";
  public static final String MAP_KEY_ERROR_CODE = "code";
  public static final String MAP_KEY_ERROR_DESC = "description";

  private final ReactApplicationContext reactContext;

  public JiopayModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  @NonNull
  public String getName() {
    return NAME;
  }

  @ReactMethod
  public void open(ReadableMap options) {
    Activity currentActivity = getCurrentActivity();
    if (currentActivity != null) {
      try {
        JSONObject optionsJSON = Utils.readableMapToJson(options);
        new Checkout().open(currentActivity, this, optionsJSON);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  private void sendEvent(String eventName, WritableMap params) {
    emitEvent(eventName, params);
  }

  @Override
  public void onPaymentSuccess(String jfsPaymentID) {
    JSONObject jsonObject = new JSONObject();
    try {
      jsonObject.put(MAP_KEY_PAYMENT_ID, jfsPaymentID);
      sendEvent("Jiopay::PAYMENT_SUCCESS", Utils.jsonToWritableMap(jsonObject));
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void onPaymentError(String code, String error) {
    JSONObject paymentDataJson = new JSONObject();
    try {
      paymentDataJson.put(MAP_KEY_ERROR_CODE, code);
      paymentDataJson.put(MAP_KEY_ERROR_DESC, error);
    } catch (Exception e) {}
    sendEvent("Jiopay::PAYMENT_ERROR", Utils.jsonToWritableMap(paymentDataJson));
  }

  @Override
  public void onActivityResult(
    Activity activity,
    int requestCode,
    int resultCode,
    Intent data
  ) {
    Log.e("HTAG", data.getData().toString());
  }

  @Override
  public void onNewIntent(Intent intent) {}
}

