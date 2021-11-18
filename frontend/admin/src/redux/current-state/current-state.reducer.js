import CurrentStateTypes from "./current-state.types";

const INITIAL_STATE = {
  action: {},
};

const currentActionReducer = (state = INITIAL_STATE, action) => {
  switch (action.type) {
    case CurrentStateTypes.ADDING_SWEDISH_WORD:
      return { ...state, action: action.payload };
    default:
      return state;
  }
};

export default currentActionReducer;
