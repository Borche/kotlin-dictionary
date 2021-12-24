import SwedishWordTypes from "./swedish-word.types";

export const updateSwedishWord = (obj) => ({
  type: SwedishWordTypes.UPDATING_SWEDISH_WORD,
  payload: obj,
});

export const updateSwedishType = (obj) => ({
  type: SwedishWordTypes.UPDATING_SWEDISH_TYPE,
  payload: obj,
});
