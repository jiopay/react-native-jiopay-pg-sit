'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.default = void 0;

var _reactNative = require("react-native");

const jiopayEvents = new _reactNative.NativeEventEmitter(_reactNative.NativeModules.JiopayEventEmitter);

const removeSubscriptions = () => {
  jiopayEvents.removeAllListeners('Jiopay::PAYMENT_SUCCESS');
  jiopayEvents.removeAllListeners('Jiopay::PAYMENT_ERROR');
};

class JiopayCheckout {
  static open(options, successCallback, errorCallback) {
    return new Promise(function (resolve, reject) {
      jiopayEvents.addListener('Jiopay::PAYMENT_SUCCESS', data => {
        let resolveFn = successCallback || resolve;
        resolveFn(data);
        removeSubscriptions();
      });
      jiopayEvents.addListener('Jiopay::PAYMENT_ERROR', data => {
        let rejectFn = errorCallback || reject;
        rejectFn(data);
        removeSubscriptions();
      });

      _reactNative.NativeModules.RNJiopayCheckout.open(options);
    });
  }

}

var _default = JiopayCheckout;
exports.default = _default;
//# sourceMappingURL=index.js.map