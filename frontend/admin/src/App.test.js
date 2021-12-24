import { render, screen } from "@testing-library/react";
import App from "./App";
import { Provider } from "react-redux";

const lawl = {};

test("renders learn react link", () => {
  const { container } = render(
    <Provider store={lawl}>
      <App />
    </Provider>
  );
  // const linkElement = screen.getByText(/learn react/i);
  expect(container.getElementsByClassName("wrapper")[0]).toBeInTheDocument();
});
