import SpanishWordTypes from "./spanish-word.types";

const INITIAL_STATE = {
  type: "",
  word: "",
  propagate: false,
};

const spanishWordReducer = (state = INITIAL_STATE, action) => {
  switch (action.type) {
    case SpanishWordTypes.UPDATING_SPANISH_WORD:
    case SpanishWordTypes.UPDATING_SPANISH_PROPAGATE:
      return { ...state, ...action.payload };
    default:
      return state;
  }
};

export default spanishWordReducer;
