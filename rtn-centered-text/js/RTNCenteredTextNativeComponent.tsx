import {
  BubblingEventHandler,
  DirectEventHandler,
} from "react-native/Libraries/Types/CodegenTypes";
import codegenNativeComponent from "react-native/Libraries/Utilities/codegenNativeComponent";

import type { HostComponent, ViewProps } from "react-native";

interface TestDirectEvent {
  message: string;
}

interface TestBubblingEvent {
  message: string;
}

export interface NativeProps extends ViewProps {
  text?: string;
  other?: string;

  // events
  onTestDirectEvent?: DirectEventHandler<TestDirectEvent>;
  onTestBubblingEvent?: BubblingEventHandler<TestBubblingEvent>;
}

export default codegenNativeComponent<NativeProps>(
  "RTNCenteredText"
) as HostComponent<NativeProps>;
