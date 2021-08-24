'use strict';

import { NativeEventEmitter, NativeModules } from 'react-native';

const jiopayEvents = new NativeEventEmitter(NativeModules.JiopayEventEmitter);

const removeSubscriptions = () => {
  jiopayEvents.removeAllListeners('Jiopay::PAYMENT_SUCCESS');
  jiopayEvents.removeAllListeners('Jiopay::PAYMENT_ERROR');
};

class JiopayCheckout {
  static open(options: any, successCallback: any, errorCallback: any) {
    return new Promise(function (resolve, reject) {
      jiopayEvents.addListener('Jiopay::PAYMENT_SUCCESS', (data) => {
        let resolveFn = successCallback || resolve;
        resolveFn(data);
        removeSubscriptions();
      });
      jiopayEvents.addListener('Jiopay::PAYMENT_ERROR', (data) => {
        let rejectFn = errorCallback || reject;
        rejectFn(data);
        removeSubscriptions();
      });
      NativeModules.RNJiopayCheckout.open(options);
    });
  }
}

export default JiopayCheckout;
