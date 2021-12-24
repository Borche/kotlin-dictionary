import EnglishWordTypes from "./english-word.types";

const INITIAL_STATE = {
  type: "",
  word: "",
  propagate: false,
};

const englishWordReducer = (state = INITIAL_STATE, action) => {
  switch (action.type) {
    case EnglishWordTypes.UPDATING_ENGLISH_WORD:
    case EnglishWordTypes.UPDATING_ENGLISH_PROPAGATE:
      return { ...state, ...action.payload };
    default:
      return state;
  }
};

export default englishWordReducer;
