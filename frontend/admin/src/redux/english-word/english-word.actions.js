import EnglishWordTypes from "./english-word.types";

export const updateEnglishWord = (word) => ({
  type: EnglishWordTypes.UPDATING_ENGLISH_WORD,
  payload: word,
});
