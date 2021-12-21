import { createSelector } from "reselect";

const selectCurrent = (state) => state.current;

export const selectCurrentAction = createSelector(
  [selectCurrent],
  (current) => {
    console.log("current action from selector", current);
    console.log("current action from selector", current.action);
    return current.action;
  }
);
