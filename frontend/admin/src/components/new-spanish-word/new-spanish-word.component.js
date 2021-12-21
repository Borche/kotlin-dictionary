import React from "react";
import { connect } from "react-redux";

import { updateSpanishWord } from "../../redux/spanish-word/spanish-word.actions";

class NewSpanishWord extends React.Component {
  updateWord = (event) => {
    this.props.updateWord({ word: event.target.value });
  };

  render() {
    return (
      <table>
        <tbody>
          <tr>
            <td>
              <label htmlFor="swe-type">Propagate</label>
            </td>
            <td>
              <input type="checkbox" defaultChecked />
            </td>
          </tr>
          <tr>
            <td>
              <label htmlFor="spa-word">Word</label>
            </td>
            <td>
              <input type="text" id="spa-word" onChange={this.updateWord} />
            </td>
          </tr>
        </tbody>
      </table>
    );
  }
}

const mapDispatchToProps = (dispatch) => ({
  updateWord: (word) => dispatch(updateSpanishWord(word)),
});

export default connect(null, mapDispatchToProps)(NewSpanishWord);
