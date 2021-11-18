import { createStore, applyMiddleware } from "redux";

import { composeWithDevTools } from "redux-devtools-extension";

import rootReducer from "./root.reducer";

const middlewares = [];

/*if (process.env.NODE_ENV === 'developm ent') {
  middlewares.push(logger);
}*/

export const store = createStore(
  rootReducer,
  composeWithDevTools(applyMiddleware(...middlewares))
);

export default { store };
