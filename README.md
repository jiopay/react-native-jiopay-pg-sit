# react-native-jiopay

//@TODO add description of product here

## Installation

```sh
npm install react-native-jiopay-pg-sit
```

## Usage

```js
import JiopayCheckout from "react-native-jiopay-pg-sit";

// ...
const data = {
        appaccesstoken: 'ENTER_APP_ACCESS_TOKEN_HERE',
        appidtoken: 'ENTER_APP_ID_TOKEN_HERE',
        intentid: 'ENTER_INTENT_ID_HERE',
        theme: {
          brandColor: 'BRAND_COLOR(OPTIONAL)',
          bodyBgColor: 'BODY_BG_COLOR(OPTIONAL)',
          bodyTextColor: 'BODY_TEXT_COLOR(OPTIONAL)',
          headingText: 'HEADING_TEXT(OPTIONAL)',
        },
      }

  JiopayCheckout.open(JSON.stringify(data)).then((res)=>{
           alert(`Success: ${data.jiopay_payment_id}`);
        }).catch((error)=>{
           alert(`Error: ${error.code} | ${error.description}`);
        })
        
```

## Contributing

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## License

MIT
