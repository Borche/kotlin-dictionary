import { combineReducers } from "redux";

import currentStateReducer from "./current-state/current-state.reducer";
import swedishWordReducer from "./swedish-word/swedish-word.reducer";
import englishWordReducer from "./english-word/english-word.reducer";
import spanishWordReducer from "./spanish-word/spanish-word.reducer";

const rootReducer = combineReducers({
  current: currentStateReducer,
  swedishWord: swedishWordReducer,
  englishWord: englishWordReducer,
  spanishWord: spanishWordReducer,
});

export default rootReducer;
