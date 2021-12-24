import SwedishWordTypes from "./swedish-word.types";

const INITIAL_STATE = {
  type: "",
  word: "",
};

const swedishWordReducer = (state = INITIAL_STATE, action) => {
  switch (action.type) {
    case SwedishWordTypes.UPDATING_SWEDISH_WORD:
    case SwedishWordTypes.UPDATING_SWEDISH_TYPE:
      return { ...state, ...action.payload };
    default:
      return state;
  }
};

export default swedishWordReducer;
