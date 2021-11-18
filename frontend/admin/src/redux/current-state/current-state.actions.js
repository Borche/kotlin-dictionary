import CurrentStateTypes from "./current-state.types";

export const addSwedishWord = () => ({
  type: CurrentStateTypes.ADDING_SWEDISH_WORD,
  payload: {
    name: "ADDING_SWEDISH_WORD",
    description: "Logged in admin is currently creating a new swedish word",
  },
});
