import SpanishWordTypes from "./spanish-word.types";

export const updateSpanishWord = (obj) => ({
  type: SpanishWordTypes.UPDATING_SPANISH_WORD,
  payload: obj,
});

export const updateSpanishPropagate = (obj) => ({
  type: SpanishWordTypes.UPDATING_SPANISH_PROPAGATE,
  payload: obj,
});
