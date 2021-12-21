import React from "react";
import { connect } from "react-redux";

import { updateSwedishWord } from "../../redux/swedish-word/swedish-word.actions";

class NewSwedishWord extends React.Component {
  componentDidMount() {}

  updateWord = (event) => {
    this.props.updateWord({ word: event.target.value });
  };

  render() {
    return (
      <table>
        <tbody>
          <tr>
            <td>
              <label htmlFor="swe-type">Word</label>
            </td>
            <td>
              <select id="swe-type">
                <option value="NOUN">Noun</option>
                <option value="VERB">Verb</option>
                <option value="ADJECTIVE">Adjective</option>
              </select>
            </td>
          </tr>
          <tr>
            <td>
              <label htmlFor="swe-word">Word</label>
            </td>
            <td>
              <input type="text" id="swe-word" onChange={this.updateWord} />
            </td>
          </tr>
        </tbody>
      </table>
    );
  }
}

const mapDispatchToProps = (dispatch) => ({
  updateWord: (word) => dispatch(updateSwedishWord(word)),
});

export default connect(null, mapDispatchToProps)(NewSwedishWord);
