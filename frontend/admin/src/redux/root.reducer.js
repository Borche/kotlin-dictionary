import { combineReducers } from "redux";

import currentStateReducer from "./current-state/current-state.reducer";

const rootReducer = combineReducers({
  current: currentStateReducer,
});

export default rootReducer;
