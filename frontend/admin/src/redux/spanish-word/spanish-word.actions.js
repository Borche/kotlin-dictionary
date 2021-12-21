import SpanishWordTypes from "./spanish-word.types";

export const updateSpanishWord = (word) => ({
  type: SpanishWordTypes.UPDATING_SPANISH_WORD,
  payload: word,
});
