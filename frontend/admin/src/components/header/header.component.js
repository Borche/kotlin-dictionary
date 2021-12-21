import React from "react";
import WordSearch from "../word-search/word-search.component";
import NewWordButton from "../new-word-button/new-word-button.component";

import "./header.styles.scss";

class Header extends React.Component {
  render() {
    return (
      <header className="header-main">
        <WordSearch></WordSearch>
        <NewWordButton language="SWEDISH"></NewWordButton>
      </header>
    );
  }
}

export default Header;
