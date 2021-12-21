import SpanishWordTypes from "./spanish-word.types";

const INITIAL_STATE = {
  type: "",
  word: "",
};

const spanishWordReducer = (state = INITIAL_STATE, action) => {
  switch (action.type) {
    case SpanishWordTypes.UPDATING_SPANISH_WORD:
      return { ...state, ...action.payload };
    default:
      return state;
  }
};

export default spanishWordReducer;
