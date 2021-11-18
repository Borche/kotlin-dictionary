import React from "react";
import WordSearch from "../word-search/word-search.component";
import AddWord from "../addword/addword.component";

import "./header.styles.scss";

class Header extends React.Component {
  render() {
    return (
      <header className="header-main">
        <WordSearch></WordSearch>
        <AddWord language="SWEDISH"></AddWord>
      </header>
    );
  }
}

export default Header;
