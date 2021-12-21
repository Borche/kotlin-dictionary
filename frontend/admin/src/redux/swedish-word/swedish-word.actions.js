import SwedishWordTypes from "./swedish-word.types";

export const updateSwedishWord = (word) => ({
  type: SwedishWordTypes.UPDATING_SWEDISH_WORD,
  payload: word,
});
