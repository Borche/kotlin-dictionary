import React from "react";
import { connect } from "react-redux";

import {
  updateEnglishWord,
  updateEnglishPropagate,
} from "../../../redux/english-word/english-word.actions";

import "../new-word.styles.scss";

class NewEnglishWord extends React.Component {
  DEAULT_PROPAGATE = true;

  componentDidMount() {
    this.props.updatePropagate({ propagate: this.DEAULT_PROPAGATE });
  }

  updateWord = (event) => {
    this.props.updateWord({ word: event.target.value });
  };

  updateType = (event) => {
    this.props.updatePropagate({ propagate: this.DEAULT_PROPAGATE });
  };

  render() {
    return (
      <table>
        <tbody>
          <tr className="title-row">
            <td colSpan="2">English</td>
            <td></td>
          </tr>
          <tr>
            <td>
              <label htmlFor="eng-type">Propagate</label>
            </td>
            <td>
              <input type="checkbox" defaultChecked={this.DEAULT_PROPAGATE} />
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
  updateWord: (obj) => dispatch(updateEnglishWord(obj)),
  updatePropagate: (obj) => dispatch(updateEnglishPropagate(obj)),
});

export default connect(null, mapDispatchToProps)(NewEnglishWord);
