import React from 'react';

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
      <div>
        <label htmlFor="word-search"></label>
        <input type="text" id="word-search" onChange={this.search}></input>
      </div>
    );
  }
}

export default WordSearch;
