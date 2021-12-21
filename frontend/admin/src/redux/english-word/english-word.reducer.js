import EnglishWordTypes from "./english-word.types";

const INITIAL_STATE = {
  type: "",
  word: "",
};

const englishWordReducer = (state = INITIAL_STATE, action) => {
  switch (action.type) {
    case EnglishWordTypes.UPDATING_ENGLISH_WORD:
      return { ...state, ...action.payload };
    default:
      return state;
  }
};

export default englishWordReducer;
