import EnglishWordTypes from "./english-word.types";

export const updateEnglishWord = (obj) => ({
  type: EnglishWordTypes.UPDATING_ENGLISH_WORD,
  payload: obj,
});

export const updateEnglishPropagate = (obj) => ({
  type: EnglishWordTypes.UPDATING_ENGLISH_PROPAGATE,
  payload: obj,
});
