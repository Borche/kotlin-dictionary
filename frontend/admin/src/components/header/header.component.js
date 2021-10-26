import React from 'react';
import WordSearch from '../word-search/word-search.component';

class Header extends React.Component {
  render() {
    return (
      <header>
        <WordSearch></WordSearch>
      </header>
    );
  }
}

export default Header;
