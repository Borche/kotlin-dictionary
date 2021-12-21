import React from "react";
import { connect } from "react-redux";

import { updateEnglishWord } from "../../redux/english-word/english-word.actions";

class NewEnglishWord extends React.Component {
  updateWord = (event) => {
    this.props.updateWord({ word: event.target.value });
  };

  render() {
    return (
      <table>
        <tbody>
          <tr>
            <td>
              <label htmlFor="eng-type">Propagate</label>
            </td>
            <td>
              <input type="checkbox" defaultChecked />
            </td>
          </tr>
          <tr>
            <td>
              <label htmlFor="eng-word">Word</label>
            </td>
            <td>
              <input type="text" id="eng-word" onChange={this.updateWord} />
            </td>
          </tr>
        </tbody>
      </table>
    );
  }
}

const mapDispatchToProps = (dispatch) => ({
  updateWord: (word) => dispatch(updateEnglishWord(word)),
});

export default connect(null, mapDispatchToProps)(NewEnglishWord);
