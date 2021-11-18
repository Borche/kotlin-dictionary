import React from 'react';

import './word-search.styles.scss';

class WordSearch extends React.Component {
  async search(event) {
    const { value } = event.target;

    const response = await fetch(`/admin/api/words/${value}`);

    console.log(response);

    const data = await response.json();

    console.log(data);
  }

  render() {
    return (
      <div className="c-word-search">
        <label htmlFor="word-search">Existing word</label><br />
        <input type="text" id="word-search" onChange={this.search}></input>
      </div>
    );
  }
}

export default WordSearch;
