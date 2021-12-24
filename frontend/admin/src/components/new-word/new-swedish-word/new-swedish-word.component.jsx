import React from "react";
import { connect } from "react-redux";

import {
  updateSwedishWord,
  updateSwedishType,
} from "../../../redux/swedish-word/swedish-word.actions";

import "../new-word.styles.scss";

class NewSwedishWord extends React.Component {
  DEAULT_TYPE = "VERB";

  componentDidMount() {
    this.props.updateType({ type: this.DEAULT_TYPE });
  }

  updateWord = (event) => {
    this.props.updateWord({ word: event.target.value });
  };

  updateType = (event) => {
    this.props.updateType({ type: event.target.value });
  };

  render() {
    return (
      <table>
        <tbody>
          <tr className="title-row">
            <td colSpan="2">Swedish</td>
            <td></td>
          </tr>
          <tr>
            <td>
              <label htmlFor="swe-type">Word</label>
            </td>
            <td>
              <select
                id="swe-type"
                defaultValue={this.DEAULT_TYPE}
                onChange={this.updateType}
              >
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
  updateWord: (obj) => dispatch(updateSwedishWord(obj)),
  updateType: (obj) => dispatch(updateSwedishType(obj)),
});

export default connect(null, mapDispatchToProps)(NewSwedishWord);
